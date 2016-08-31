package com.evetime.app.dao.impl;

import com.evetime.app.dao.BrandSaleDateDao;
import com.evetime.app.entity.Branch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-07-09.
 */
@Repository("AppBrandSaleDataDao")
public class BrandSaleDataDaoImpl implements BrandSaleDateDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> querySaleOfWeek(String userId) {

        String sql =  " SELECT SUM(if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)) sale , DATE_FORMAT(a.OrderTime,'%m-%d') date FROM ms_pc_orderinfo a " +
                " JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " AND TO_DAYS(NOW()) - TO_DAYS(a.OrderTime) < 7 " +
                " WHERE a.CloudId IN (SELECT CloudId FROM ms_web_companyuserrole  WHERE UserId= ? ) " +
                " GROUP BY DATE_FORMAT(a.OrderTime , '%y-%m-%d') " ;
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("sale" , rs.getDouble("sale"));
                map.put("date" , rs.getString("date"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> queryCurWeekSale(String userId) {

        String sql =  " SELECT SUM(if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)) sale , DATE_FORMAT(a.OrderTime,'%m-%d') date FROM ms_pc_orderinfo a " +
                " JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " AND WEEK(OrderTime , 1) = WEEK(NOW(),  1) " +
                " WHERE a.CloudId IN (SELECT CloudId FROM ms_web_companyuserrole  WHERE UserId= ? ) " +
                " GROUP BY DATE_FORMAT(a.OrderTime , '%y-%m-%d') " ;
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("sale" , rs.getDouble("sale"));
                map.put("date" , rs.getString("date"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> queryCurMonthSale(String userId) {

        String sql =  " SELECT SUM(if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)) sale , DATE_FORMAT(a.OrderTime,'%m-%d') date FROM ms_pc_orderinfo a " +
                " JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " AND MONTH(OrderTime) = MONTH(NOW() ) " +
                " WHERE a.CloudId IN (SELECT CloudId FROM ms_web_companyuserrole  WHERE UserId= ? ) " +
                " GROUP BY DATE_FORMAT(a.OrderTime , '%y-%m-%d') " ;
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("sale" , rs.getDouble("sale"));
                map.put("date" , rs.getString("date"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> queryCurYearSale(String userId) {
        String sql =  " SELECT SUM(if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)) sale , DATE_FORMAT(a.OrderTime,'%m') date FROM ms_pc_orderinfo a " +
                " JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " AND YEAR (OrderTime) = YEAR (NOW() ) " +
                " WHERE a.CloudId IN (SELECT CloudId FROM ms_web_companyuserrole  WHERE UserId= ? ) " +
                " GROUP BY DATE_FORMAT(a.OrderTime , '%y-%m') " ;
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("sale" , rs.getDouble("sale"));
                map.put("date" , rs.getString("date"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> queryMonthSale(String userId, int month) {
        String sql =  " SELECT SUM(if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)) sale , DATE_FORMAT(a.OrderTime,'%m-%d') date FROM ms_pc_orderinfo a " +
                " JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " AND MONTH(a.OrderTime) = ? AND YEAR(a.OrderTime) = YEAR(NOW()) " +
                " WHERE a.CloudId IN (SELECT CloudId FROM ms_web_companyuserrole  WHERE UserId= ? ) " +
                " GROUP BY DATE_FORMAT(a.OrderTime , '%y-%m-%d') " ;
        return jdbcTemplate.query(sql, new Object[]{ month , userId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("sale" , rs.getDouble("sale"));
                map.put("date" , rs.getString("date"));
                return map;
            }
        });
    }

    @Override
    public List<Branch> queryCurWeekBranchList(String userId) {

        String sql =  " SELECT a.CloudId , a.BranchName , m.sale FROM ms_web_companyuserrole r " +
                            " JOIN ms_pc_restaurantinfo a ON r.CloudId = a.CloudId " +
                            " LEFT JOIN ( " +
                            " SELECT a.CloudId , a.OrderTime , SUM(if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)) sale FROM ms_pc_orderinfo a JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                            " WHERE WEEK(a.OrderTime,1 ) = WEEK(NOW(), 1 ) " +
                            " GROUP BY a.CloudId " +
                            " ) m ON r.CloudId = m.CloudId " +
                            " WHERE UserId = ? " +
                            " ORDER BY sale DESC ";
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Branch>() {
            @Override
            public Branch mapRow(ResultSet rs, int i) throws SQLException {
                Branch branch = new Branch();
                branch.setSystemId(rs.getString("CloudId"));
                branch.setName(rs.getString("BranchName"));
                branch.setSale(rs.getDouble("sale"));
                return branch;
            }
        });
    }

    @Override
    public List<Branch> queryCurMonthBranchList(String userId) {
        String sql =  " SELECT a.CloudId , a.BranchName , m.sale FROM ms_web_companyuserrole r " +
                " JOIN ms_pc_restaurantinfo a ON r.CloudId = a.CloudId " +
                " LEFT JOIN ( " +
                " SELECT a.CloudId , a.OrderTime , SUM(if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)) sale FROM ms_pc_orderinfo a JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " WHERE MONTH (a.OrderTime ) = MONTH (NOW()) " +
                " GROUP BY a.CloudId " +
                " ) m ON r.CloudId = m.CloudId " +
                " WHERE UserId = ? " +
                " ORDER BY sale DESC ";
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Branch>() {
            @Override
            public Branch mapRow(ResultSet rs, int i) throws SQLException {
                Branch branch = new Branch();
                branch.setSystemId(rs.getString("CloudId"));
                branch.setName(rs.getString("BranchName"));
                branch.setSale(rs.getDouble("sale"));
                return branch;
            }
        });
    }

    @Override
    public List<Branch> queryCurYearBranchList(String userId) {
        String sql =  " SELECT a.CloudId , a.BranchName , m.sale FROM ms_web_companyuserrole r " +
                " JOIN ms_pc_restaurantinfo a ON r.CloudId = a.CloudId " +
                " LEFT JOIN ( " +
                " SELECT a.CloudId , a.OrderTime , SUM(if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)) sale FROM ms_pc_orderinfo a JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " WHERE YEAR (a.OrderTime) = YEAR (NOW()) " +
                " GROUP BY a.CloudId " +
                " ) m ON r.CloudId = m.CloudId " +
                " WHERE UserId = ? " +
                " ORDER BY sale DESC ";
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Branch>() {
            @Override
            public Branch mapRow(ResultSet rs, int i) throws SQLException {
                Branch branch = new Branch();
                branch.setSystemId(rs.getString("CloudId"));
                branch.setName(rs.getString("BranchName"));
                branch.setSale(rs.getDouble("sale"));
                return branch;
            }
        });
    }

    @Override
    public List<Branch> queryMonthBranchList(String userId , int month) {

        String sql =  " SELECT a.CloudId , a.BranchName , m.sale FROM ms_web_companyuserrole r " +
                " JOIN ms_pc_restaurantinfo a ON r.CloudId = a.CloudId " +
                " LEFT JOIN ( " +
                " SELECT a.CloudId , a.OrderTime , SUM(if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)) sale FROM ms_pc_orderinfo a JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " WHERE MONTH (a.OrderTime ) = ? AND YEAR(a.OrderTime) = YEAR(now()) " +
                " GROUP BY a.CloudId " +
                " ) m ON r.CloudId = m.CloudId " +
                " WHERE UserId = ? " +
                " ORDER BY sale DESC ";
        return jdbcTemplate.query(sql, new Object[]{month , userId}, new RowMapper<Branch>() {
            @Override
            public Branch mapRow(ResultSet rs, int i) throws SQLException {
                Branch branch = new Branch();
                branch.setSystemId(rs.getString("CloudId"));
                branch.setName(rs.getString("BranchName"));
                branch.setSale(rs.getDouble("sale"));
                return branch;
            }
        });
    }

    @Override
    public double queryCurDaySale(String userId) {

        String sql =  " SELECT IFNULL(SUM( if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)  ), 0 ) todaySale FROM ms_pc_orderinfo a " +
                            " JOIN ms_pc_orderpayinfo b ON a.Id = b.OrderId " +
                            " AND a.OrderStatusId = 1 " +
                            " AND TO_DAYS(NOW()) - TO_DAYS(a.OrderTime) = 0 " +
                            " WHERE a.CloudId IN " +
                            "( SELECT CloudId FROM ms_web_companyuserrole WHERE UserId = ? )";
        return jdbcTemplate.queryForObject(sql , new Object[]{userId} , double.class);
    }


}
