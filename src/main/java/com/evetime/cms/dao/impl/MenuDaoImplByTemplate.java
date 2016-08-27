package com.evetime.cms.dao.impl;


import com.evetime.cms.dao.MenuDao;
import com.evetime.cms.entity.Menu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class MenuDaoImplByTemplate implements MenuDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

/*    public List<Integer> findMenusByUserName(String userName) {

        String sql = "SELECT p.menu_id FROM ms_cms_user u " +
                          "INNER JOIN ms_cms_permissions p ON u.group_Id = p.group_Id " +
                          "INNER JOIN ms_cms_menu m ON m.id = p.menu_id " +
                          "WHERE userName = ? " ;
        return jdbcTemplate.queryForList(sql, new Object[]{userName} , Integer.class);
    }*/

    public List<Menu> findRootMenu(String userName) {

        String sql = "SELECT * FROM ms_cms_menu WHERE  id IN " +
                "(SELECT p.menu_id FROM ms_cms_permissions p" +
                " INNER JOIN ms_cms_menu m ON m.id = p.menu_id  " +
                " INNER JOIN ms_cms_user u ON u.group_id = p.group_id " +
                " WHERE u.userName = ? ) " +
                " AND parent_id IS NULL " ;

        return jdbcTemplate.query(sql, new Object[]{userName}, new MenuRowMapper());
    }

    public List<Menu> findMenusByParentId(int parentId ,String userName){

        String sql = "SELECT * FROM ms_cms_menu WHERE  id IN " +
                          "(SELECT p.menu_id FROM ms_cms_permissions p" +
                          " INNER JOIN ms_cms_menu m ON m.id = p.menu_id  " +
                          " INNER JOIN ms_cms_user u ON u.group_id = p.group_id " +
                          " WHERE u.userName = ? ) " +
                          " AND parent_id = ? ";
        return jdbcTemplate.query(sql, new Object[]{userName , parentId}, new MenuRowMapper());
    }



    class MenuRowMapper implements RowMapper<Menu>{

        public Menu mapRow(ResultSet rs, int i) throws SQLException {
            Menu menu = new Menu();
            menu.setId(rs.getInt("id"));
            menu.setText(rs.getString("text"));
            menu.setIconCls(rs.getString("iconCls"));
            menu.setParent_id(rs.getInt("parent_id"));
            menu.setViewType(rs.getString("viewType"));
            menu.setRouteId(rs.getString("routeId"));
            return  menu;
        }
    }

}
