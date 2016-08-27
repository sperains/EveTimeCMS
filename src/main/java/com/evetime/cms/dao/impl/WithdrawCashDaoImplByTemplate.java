package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.WithdrawCashDao;
import com.evetime.cms.entity.WithdrawCash;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016-04-25.
 */
@Repository
public class WithdrawCashDaoImplByTemplate implements WithdrawCashDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WithdrawCash> findApplyOrder() {

        String sql  = "SELECT * FROM ms_pc_withdrawcash WHERE Cashstatus = 0 ";
        return jdbcTemplate.query(sql, new RowMapper<WithdrawCash>() {
            public WithdrawCash mapRow(ResultSet rs, int i) throws SQLException {
                WithdrawCash model = new WithdrawCash();
                model.setOrderNo(rs.getString("orderNo"));
                model.setRealName(rs.getString("ActualName"));
                model.setCardNumber(rs.getString("CardNumber"));
                model.setMoney(rs.getDouble("UserMoney"));
                model.setCashTime(rs.getString("CashTime"));
                model.setCloudId(rs.getString("CloudId"));
                model.setBankName(rs.getString("BankName"));
                model.setCashStatus(rs.getInt("Cashstatus"));
                model.setApplyDate(rs.getString("ApplyDate"));
                model.setOpDate(rs.getString("OpDate"));
                model.setRemark(rs.getString("Remark"));
                return model;
            }
        });
    }
}
