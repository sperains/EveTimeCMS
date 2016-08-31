package com.evetime.app.dao.impl;

import com.evetime.app.dao.BranchSaleDataDao;
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
 * on 2016-07-05.
 */
@Repository("AppBranchSaleDataDao")
public class BranchSaleDataDaoImpl implements BranchSaleDataDao {

    @Resource
    private JdbcTemplate jdbcTemplate;


    @Override
    public int queryTodayOrderCount(String cloudId) {
        String sql =  " SELECT count(*) from ms_pc_orderinfo " +
                            " WHERE CloudId = ? " +
                            " AND OrderStatusId = 1 " +
                            " AND DATE(OrderTime) = Date(NOW())" ;
        return jdbcTemplate.queryForObject(sql , new Object[]{cloudId} , int.class );
    }

    @Override
    public int queryYesterdayOrderCount(String cloudId) {
        String sql =  " SELECT count(*) from ms_pc_orderinfo " +
                            " WHERE CloudId = ? " +
                            " AND OrderStatusId = 1 " +
                            " AND TO_DAYS(now()) - TO_DAYS(OrderTime) = 1 " ;
        return jdbcTemplate.queryForObject(sql , new Object[]{cloudId} , int.class );
    }

    @Override
    public int queryTodayFoodSellCount(String cloudId) {

        String sql =  " SELECT IFNULL(SUM(a.FoodCount),0) FROM ms_pc_orderdetailsinfo a " +
                            " JOIN ms_pc_orderinfo b ON a.OrderId = b.Id " +
                            " WHERE a.CloudId = ? " +
                            " AND b.OrderStatusId = 1 " +
                            " AND DATE(b.OrderTime) = Date(NOW())";
        return jdbcTemplate.queryForObject(sql , new Object[]{cloudId} , int.class);
    }

    @Override
    public int queryYesterDayFoodSellCount(String cloudId) {

        String sql =  " SELECT IFNULL(SUM(a.FoodCount),0) FROM ms_pc_orderdetailsinfo a " +
                            " JOIN ms_pc_orderinfo b ON a.OrderId = b.Id " +
                            " WHERE a.CloudId = ? " +
                            " AND b.OrderStatusId = 1 " +
                            " AND TO_DAYS(now()) - TO_DAYS(OrderTime) = 1 ";
        return jdbcTemplate.queryForObject(sql , new Object[]{cloudId} , int.class);
    }

    @Override
    public double queryTodayAverageCost(String cloudId) {

        String sql =  "SELECT ROUND(IFNULL(SUM(a.PayMoney)/IFNULL(SUM(b.PeopleCount),0),0)) FROM ms_pc_orderpayinfo a " +
                            "JOIN ms_pc_orderinfo b " +
                            "ON a.OrderId = b.Id " +
                            "WHERE a.CloudId = ? " +
                            "AND b.OrderStatusId = 1 " +
                            "AND DATE(b.OrderTime) = Date(NOW())" ;
        return jdbcTemplate.queryForObject(sql , new Object[]{cloudId} , double.class);
    }

    @Override
    public double queryYesterDayAverageCost(String cloudId) {

        String sql =  "SELECT ROUND(IFNULL(SUM(a.PayMoney)/IFNULL(SUM(b.PeopleCount),0),0)) FROM ms_pc_orderpayinfo a " +
                "JOIN ms_pc_orderinfo b " +
                "ON a.OrderId = b.Id " +
                "WHERE a.CloudId = ? " +
                "AND b.OrderStatusId = 1 " +
                "AND TO_DAYS(now()) - TO_DAYS(OrderTime) = 1 " ;
        return jdbcTemplate.queryForObject(sql , new Object[]{cloudId} , double.class);
    }

    @Override
    public double queryTodayTableTurnOverRate(String cloudId) {

        /*String sql =  " SELECT ROUND(b.count/a.count*100,2) tableTurnoverRate   from " +
                            " (SELECT count(*) count ,CloudId from ms_pc_tableinfo where DelFlag = 0) a ," +
                            " (SELECT count(*) count ,DATE_FORMAT(OrderTime,'%Y-%m-%d') dayTime , CloudId from ms_pc_orderinfo " +
                            " WHERE OrderStatusId = 0 " +
                            " GROUP BY DATE_FORMAT(OrderTime,'%Y-%m-%d') ) b " +
                            " WHERE  a.CloudId = ? AND DATE(b.dayTime) = DATE(NOW()) ";*/

        String sql =  " select ROUND(a.count/b.count*100,1) tableTurnoverRate from ( " +
                            " SELECT COUNT(*)  count ,DATE_FORMAT(OrderTime,'%y-%m-%d'),CloudId FROM ms_pc_orderinfo WHERE  " +
                            " TO_DAYS(OrderTime) = TO_DAYS(NOW()) AND OrderStatusId = 1 " +
                            " GROUP BY CloudId , DATE_FORMAT(OrderTime,'%y-%m-%d') " +
                            " ) a JOIN ( " +
                            " select count(*) count , CloudId from ms_pc_tableinfo WHERE DelFlag = 0 GROUP BY CloudId " +
                            " ) b ON a.CloudId = b.CloudId " +
                            " WHERE a.CloudId = ? " ;
        return jdbcTemplate.queryForObject(sql , new Object[]{cloudId} , double.class);
    }

    @Override
    public double queryYesterDayTableTurnOverRate(String cloudId) {

        String sql =  " select ROUND(a.count/b.count*100,1) tableTurnoverRate  from ( " +
                            " SELECT COUNT(*) count ,DATE_FORMAT(OrderTime,'%y-%m-%d'),CloudId FROM ms_pc_orderinfo WHERE  " +
                            " TO_DAYS(NOW()) - TO_DAYS(OrderTime) = 1 AND  OrderStatusId = 1 " +
                            " GROUP BY CloudId , DATE_FORMAT(OrderTime,'%y-%m-%d') " +
                            " ) a JOIN ( " +
                            " select count(*) count , CloudId from ms_pc_tableinfo WHERE DelFlag = 0 GROUP BY CloudId " +
                            " ) b ON a.CloudId = b.CloudId " +
                            " WHERE a.CloudId = ? " ;
        return jdbcTemplate.queryForObject(sql , new Object[]{cloudId} , double.class);
    }

    @Override
    public List<Map<String, Object>> queryWeekSale(String cloudId) {

        String sql =  " SELECT SUM(a.PayMoney) sale ,DATE_FORMAT(b.OrderTime,'%m-%d') date FROM ms_pc_orderpayinfo a " +
                            " JOIN ms_pc_orderinfo b " +
                            " ON a.OrderId = b.Id AND b.OrderStatusId = 1 " +
                            " WHERE a.CloudId = ? " +
                            " AND TO_DAYS(now()) - TO_DAYS(b.OrderTime) < 7 " +
                            " GROUP BY DATE_FORMAT(b.OrderTime,'%y-%m-%d')"  ;
        return jdbcTemplate.query(sql, new Object[]{cloudId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date" , rs.getString("date"));
                map.put("sale" , rs.getDouble("sale"));
                return map;
            }
        });
    }


    @Override
    public List<Map<String, Object>> queryFoodSellInfo(String cloudId, int range) {

        String sql =  " SELECT a.FoodName name, a.Id , d.count count FROM ms_pc_foodinfo a " +
                            " LEFT JOIN (SELECT sum(b.FoodCount) count  , b.FoodId from ms_pc_orderdetailsinfo b " +
                            " LEFT JOIN ms_pc_orderinfo c ON b.OrderId = c.Id " +
                            " AND c.OrderStatusId = 1 WHERE " ;
        switch (range){
            case 0 ://当天
                sql += "  DATE(c.OrderTime) = DATE(NOW()) " ;
                break;
            case 1 ://本周 ,周1开始
                sql += "  WEEK(c.OrderTime , 1 ) = WEEK(NOW() , 1 ) "  ;
                break;
            case 2 ://本月
                sql += "  MONTH(c.OrderTime) = MONTH(NOW() ) " ;
                break;
            case 3 ://本年度
                sql +="  YEAR(c.OrderTime) = YEAR(NOW() ) " ;
                break;
        }

                 sql += " GROUP BY b.CloudId,b.FoodId ) d ON a.Id = d.FoodId " +
                            " WHERE a.CloudId = ? ORDER BY count desc"  ;

        return jdbcTemplate.query(sql, new Object[]{cloudId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String ,Object> map = new HashMap<String, Object>();
                map.put("name" , rs.getString("name") ) ;
                map.put("count" , rs.getInt("count")) ;
                return map ;
            }
        });
    }

    @Override
    public List<Map<String, Object>> queryCurWeekSale(String cloudId) {

        String sql =  " SELECT sum(b.PayMoney) sale , DATE_FORMAT(a.OrderTime , '%m-%d')  date from ms_pc_orderinfo a " +
                            " JOIN ms_pc_orderpayinfo b " +
                            " ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                            " WHERE a.CloudId = ? " +
                            " AND WEEK(a.OrderTime ,1 ) = WEEK(NOW() , 1) " +
                            " GROUP BY DATE_FORMAT(a.OrderTime , '%y-%m-%d')" ;
        return jdbcTemplate.query(sql, new Object[]{cloudId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date" , rs.getString("date"));
                map.put("sale" , rs.getDouble("sale"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> queryCurMonthSale(String cloudId) {

        String sql =  " SELECT sum(b.PayMoney) sale , DATE_FORMAT(a.OrderTime , '%m-%d')  date from ms_pc_orderinfo a " +
                " JOIN ms_pc_orderpayinfo b " +
                " ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " WHERE a.CloudId = ? " +
                " AND MONTH (a.OrderTime) = MONTH (NOW() ) " +
                " GROUP BY DATE_FORMAT(a.OrderTime , '%y-%m-%d')" ;
        return jdbcTemplate.query(sql, new Object[]{cloudId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date" , rs.getString("date"));
                map.put("sale" , rs.getDouble("sale"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> queryCurYearSale(String cloudId) {

        String sql =  " SELECT sum(b.PayMoney) sale , DATE_FORMAT(a.OrderTime , '%m')  date from ms_pc_orderinfo a " +
                " JOIN ms_pc_orderpayinfo b " +
                " ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " WHERE a.CloudId = ? " +
                " AND YEAR(a.OrderTime) = YEAR(NOW() ) " +
                " GROUP BY DATE_FORMAT(a.OrderTime , '%y-%m')" ;
        return jdbcTemplate.query(sql, new Object[]{cloudId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date" , rs.getString("date"));
                map.put("sale" , rs.getDouble("sale"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> queryMonthSale(String cloudId, int month) {

        String sql =  " SELECT sum(b.PayMoney) sale , DATE_FORMAT(a.OrderTime , '%m-%d')  date from ms_pc_orderinfo a " +
                " JOIN ms_pc_orderpayinfo b " +
                " ON a.Id = b.OrderId AND a.OrderStatusId = 1 " +
                " WHERE a.CloudId = ? " +
                " AND MONTH (a.OrderTime) =  ? AND YEAR(a.OrderTime) = YEAR(NOW()) " +
                " GROUP BY DATE_FORMAT(a.OrderTime , '%y-%m-%d')" ;
        return jdbcTemplate.query(sql, new Object[]{cloudId , month}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date" , rs.getString("date"));
                map.put("sale" , rs.getDouble("sale"));
                return map;
            }
        });
    }
}
