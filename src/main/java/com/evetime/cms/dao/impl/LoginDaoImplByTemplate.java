package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.LoginDao;
import com.evetime.cms.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016-04-18.
 */
@Repository("loginDaoImplByTemplate")
public class LoginDaoImplByTemplate implements LoginDao {

    @Resource
    private JdbcTemplate  jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findUserByUserName(String userName) {

        String sql = "SELECT * FROM ms_cms_user " +
                          "WHERE userName = ? ";

        return jdbcTemplate.query(sql, new Object[]{userName}, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
    }
}
