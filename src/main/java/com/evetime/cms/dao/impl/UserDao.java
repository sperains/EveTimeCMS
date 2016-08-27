package com.evetime.cms.dao.impl;


import com.evetime.cms.entity.Group;
import com.evetime.cms.entity.User;
import com.evetime.cms.entity.Permission;
import com.evetime.cms.util.UUIDGenerator;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rains
 * on 2016-05-13.
 */
@Repository("UserDao")
public class UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAllUsers() {

        String sql = "SELECT  u.id , u.companyId , u.userName , u.name , u.group_id ,  g.name groupName , u.pic , u.createTime , u.delFlag , u.tel , c.CompanyName " +
                " FROM ms_cms_user u " +
                " LEFT JOIN ms_web_companyinfo  c ON u.companyId = c.Id " +
                " JOIN ms_cms_groups g ON u.group_id = g.id" +
                " WHERE u.DelFlag = 0 ";
        return jdbcTemplate.query(sql, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setCompanyId(rs.getString("companyId"));
                user.setUserName(rs.getString("userName"));
                user.setGroupId(rs.getInt("group_id"));
                user.setGroupName(rs.getString("groupName"));
                user.setName(rs.getString("name"));
                user.setCompanyName(rs.getString("CompanyName"));
                user.setTel(rs.getString("tel"));
                user.setCreateTime(rs.getString("createTime"));
                return user;
            }
        });
    }

    public List<User> findUser(String userName){

        String sql = "SELECT  u.id , u.companyId , u.userName , u.password,  u.name , u.group_id ,  g.name groupName , u.pic , u.createTime , u.delFlag , u.tel , c.CompanyName " +
                " FROM ms_cms_user u " +
                " LEFT JOIN ms_web_companyinfo  c ON u.companyId = c.Id " +
                " JOIN ms_cms_groups g ON u.group_id = g.id" +
                " WHERE u.DelFlag = 0 AND u.userName = ? ";
        return jdbcTemplate.query(sql, new Object[]{userName} ,new RowMapper<User>() {
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setCompanyId(rs.getString("companyId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setGroupId(rs.getInt("group_id"));
                user.setGroupName(rs.getString("groupName"));
                user.setName(rs.getString("name"));
                user.setCompanyName(rs.getString("CompanyName"));
                user.setTel(rs.getString("tel"));
                user.setCreateTime(rs.getString("createTime"));
                return user;
            }
        });
    }

    public int insert(final User user){

        String sql = "INSERT INTO ms_cms_user " +
                          "(id , companyId , userName , password , name , " +
                          " email , group_id , pic , createTime , tel , delFlag) " +
                          "VALUES (? , ? , ? ,? , ? , " +
                          "? ,? ,? ,now() ,? , ?  )";

        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,UUIDGenerator.getUUID());
                ps.setString(2,user.getCompanyId());
                ps.setString(3,user.getUserName());
                ps.setString(4,user.getPassword());
                ps.setString(5,user.getName());
                ps.setString(6, user.getEmail());
                ps.setInt(7, user.getGroupId());
                ps.setString(8,user.getPic());
                ps.setString(9,user.getTel());
                ps.setInt(10,0);
            }
        });
    }

    public int recycle(String userId){

        String sql = "UPDATE ms_cms_user " +
                          "SET DelFlag = ? " +
                          "WHERE id = ? ";
        return jdbcTemplate.update(sql , new Object[]{1 , userId });
    }

    public void insertPermission(final List<Permission> list){

        String sql = "INSERT INTO ms_web_companyuserrole(UserId , BrandId , CloudId , RestaurantId ) VALUES( ? , ? , ? , ? ) ";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String userId =  list.get(i).getUserId();
                String brandId = list.get(i).getBrandId();
                String cloudId = list.get(i).getCloudId();
                String restaurantId = list.get(i).getRestaurantId();
                ps.setString(1,userId);
                ps.setString(2,brandId);
                ps.setString(3,cloudId);
                ps.setString(4,restaurantId);
            }

            public int getBatchSize() {
                return list.size();
            }
        });
    }

    public int deletePermission(String userId){

        String sql = "DELETE FROM  ms_web_companyuserrole WHERE UserId = ? ";

        return jdbcTemplate.update(sql ,new Object[]{userId});
    }

    public List<Permission> loadPermission(String userId){

        String sql = "SELECT * FROM ms_web_companyuserrole WHERE UserId = ? ";
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Permission>() {
            public Permission mapRow(ResultSet rs, int i) throws SQLException {

                Permission p = new Permission();
                p.setUserId(rs.getString("UserId"));
                p.setBrandId(rs.getString("BrandId"));
                p.setCloudId(rs.getString("CloudId"));
                p.setRestaurantId(rs.getString("RestaurantId"));
                return p;
            }
        });
    }

    public List<Group> loadGroups(){

        String sql = "SELECT * FROM ms_cms_groups " ;
        return jdbcTemplate.query(sql, new RowMapper<Group>() {
            @Override
            public Group mapRow(ResultSet rs, int i) throws SQLException {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                return group;
            }
        });
    }


    public int update(final User user){

        String sql = "UPDATE ms_cms_user SET " +
                        " companyId = ? ," +
                        " email = ? ," +
                        " group_id = ? , " +
                        " name = ? , " +
                        " tel = ? " +
                        " WHERE id = ? ";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString( 1 , user.getCompanyId());
                ps.setString( 2 , user.getEmail());
                ps.setInt( 3 , user.getGroupId());
                ps.setString( 4 , user.getName());
                ps.setString( 5 , user.getTel());
                ps.setString( 6 , user.getId());
            }
        });
    }

    public List<User> findById(String id){

        String sql = "SELECT  u.id , u.companyId , u.userName , u.password,  u.name , u.group_id ,  g.name groupName , u.pic , u.createTime , u.delFlag , u.tel , c.CompanyName " +
                " FROM ms_cms_user u " +
                " LEFT JOIN ms_web_companyinfo  c ON u.companyId = c.Id " +
                " JOIN ms_cms_groups g ON u.group_id = g.id " +
                " WHERE u.DelFlag = 0 AND u.id = ? ";

        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setCompanyId(rs.getString("companyId"));
                user.setUserName(rs.getString("userName"));
                user.setGroupId(rs.getInt("group_id"));
                user.setGroupName(rs.getString("groupName"));
                user.setName(rs.getString("name"));
                user.setCompanyName(rs.getString("CompanyName"));
                user.setTel(rs.getString("tel"));
                user.setCreateTime(rs.getString("createTime"));
                return user;
            }
        });
    }

}
