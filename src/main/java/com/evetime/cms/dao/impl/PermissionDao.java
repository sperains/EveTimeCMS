package com.evetime.cms.dao.impl;

import com.evetime.cms.entity.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rains
 * on 2016-05-19.
 */
@Repository("securityPermissionDao")
public class PermissionDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Group> loadGroup(){

        String sql = "SELECT * FROM ms_cms_groups ";
        return jdbcTemplate.query(sql, new RowMapper<Group>() {
            public Group mapRow(ResultSet rs, int i) throws SQLException {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                return group;
            }
        });
    }
}
