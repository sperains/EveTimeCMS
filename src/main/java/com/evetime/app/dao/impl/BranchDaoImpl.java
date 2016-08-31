package com.evetime.app.dao.impl;

import com.evetime.app.dao.BranchDao;
import com.evetime.app.entity.Branch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rains
 * on 2016-07-04.
 */
@Repository("AppBranchDao")
public class BranchDaoImpl implements BranchDao{

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Branch> queryByUserId(String userId) {

        String sql =  " SELECT m.CloudId, r.BranchName  , " +
                            " IFNULL(n.todaySale, 0) todaySale " +
                            " FROM (SELECT r.CloudId FROM " +
                            " ms_web_companyuserrole r " +
                            " JOIN ms_cms_user u ON r.UserId = u.Id " +
                            " WHERE u.id = ? ) m " +
                            " LEFT JOIN ( " +
                            " SELECT sum(b.PayMoney) todaySale, a.CloudId " +
                            " FROM ms_pc_orderinfo a " +
                            " LEFT JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId " +
                            " WHERE a.OrderStatusId = 1 " +
                            " AND TO_DAYS(a.OrderTime) = TO_DAYS(now()) " +
                            " GROUP BY a.CloudId ) n ON m.CloudId = n.CloudId" +
                            " JOIN ms_pc_restaurantinfo r " +
                            " ON m.CloudId = r.CloudId " +
                            " ORDER BY todaySale DESC " ;
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Branch>() {
            @Override
            public Branch mapRow(ResultSet rs, int i) throws SQLException {
                Branch branch = new Branch();
                branch.setSystemId(rs.getString("CloudId"));
                branch.setName(rs.getString("BranchName"));
                branch.setSale(rs.getDouble("todaySale"));
                return branch;
            }
        });
    }



}
