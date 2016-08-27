package com.evetime.app.dao.impl;

import com.evetime.app.dao.UserDao;
import com.evetime.app.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Rains
 * on 2016-07-04.
 */
@Repository("AppUserDao")
public class UserDaoImpl implements UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public User query(String userName , String password) {

        String sql = "SELECT * FROM ms_cms_user WHERE userName = ? AND password = ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{userName, password}, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setCompanyId(rs.getString("companyId"));
                user.setUserName(rs.getString("userName"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setCreateTime(rs.getDate("createTime"));
                user.setDelFlag(rs.getInt("delFlag"));
                user.setTel(rs.getString("tel"));
                user.setPic(rs.getString("pic"));
                return user;
            }
        });
    }
}
