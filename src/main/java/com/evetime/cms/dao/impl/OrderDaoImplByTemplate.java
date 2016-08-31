package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.OrderDao;
import com.evetime.cms.entity.PayOrder;
import org.apache.http.util.TextUtils;
import org.springframework.dao.DataAccessException;
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
 * on 2016-04-21.
 */
@Repository("OrderDao")
public class OrderDaoImplByTemplate implements OrderDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 查询某时间段的支付信息
     */
    public List<PayOrder> findPayInfoByTime(String startTime, String endTime, String start, String limit, String cloudId) {

        String sql = "SELECT a.OrderNum , b.PayDate , b.PayTypeName  , b.PayMoney  , b.Id " +
                "FROM ms_pc_orderinfo a , ms_pc_orderpayinfo b WHERE a.Id = b.OrderId " +
                "AND b.PayDate BETWEEN  ? AND ? " +
                "AND a.CloudId = ? " +
                "AND a.PayStatus = 1";
        sql += " ORDER BY b.PayDate ASC ";

        if (start != null && !"".equals(start) && limit != null && !"".equals(limit)) {
            sql += "LIMIT " + start + "," + limit;
        }

        return jdbcTemplate.query(sql, new Object[]{startTime, endTime, cloudId}, new PayOrderMapper());
    }

    public Map<String, Object> findCountOfPayInfo(String startTime, String endTime , String cloudId) {

        String sql = "SELECT COUNT(*) count, COUNT(b.PayMoney) totalMoney  " +
        "FROM ms_pc_orderinfo a , ms_pc_orderpayinfo b WHERE a.Id = b.OrderId " +
                "AND b.PayDate BETWEEN  ? AND ? " +
                "AND a.CloudId = ? " +
                "AND a.PayStatus = 1";

        return jdbcTemplate.queryForMap(sql , startTime, endTime, cloudId);
    }

    public PayOrder findPayOrderByOrderNo(String orderNo) {

        String sql = "SELECT a.OrderNum , b.PayDate , b.PayTypeName  , if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney) b.PayMoney  , b.Id " +
                "FROM ms_pc_orderinfo a , ms_pc_orderpayinfo b WHERE a.Id = b.OrderId " +
                "AND a.OrderNum = ? " ;
        try {
            return jdbcTemplate.queryForObject(sql , new Object[]{orderNo} ,new PayOrderMapper() );
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Map<String, String>> findDayAmount(String startTime, String endTime, String cloudId) {

        /*String sql = "SELECT SUM(m.PayMoney) sum ,DATE_FORMAT(m.PayDate,'%y-%m-%d') as dayTime from ms_pc_orderpayinfo m " +
                         "WHERE DATE_FORMAT(m.PayDate,'%Y-%m-%d') >= ? AND  DATE_FORMAT(m.PayDate,'%Y-%m-%d') <= ?  AND m.CloudId = ? " +
                         "GROUP BY dayTime";*/
        String sql =  " select SUM(if(b.PayTypeId = 1 , b.PayMoney - a.CashChange  , b.PayMoney)) sum ,DATE_FORMAT(a.OrderTime,'%y-%m-%d') dayTime from ms_pc_orderinfo a " +
                            " JOIN ms_pc_orderpayinfo b " +
                            " on a.Id = b.OrderId AND a.OrderStatusId = 1  " +
                            " WHERE DATE_FORMAT(a.OrderTime,'%Y-%m-%d') >= ? AND  DATE_FORMAT(a.OrderTime,'%Y-%m-%d') <= ? " +
                            " AND a.CloudId = ? " +
                            " GROUP BY dayTime" ;

        return jdbcTemplate.query(sql, new Object[]{startTime, endTime, cloudId}, new RowMapper<Map<String,String>>() {
            @Override
            public Map<String, String> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, String> map = new HashMap<String, String>();
                map.put("sum" , rs.getString("sum"));
                map.put("dayTime" , rs.getString("dayTime"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, String>> findMonthAmountAmount(String year , String cloudId) {

        String sql  = "SELECT SUM(if(m.PayTypeId = 1 , m.PayMoney - o.CashChange  , m.PayMoney)) sum,DATE_FORMAT(o.OrderTime,'%m') as month from ms_pc_orderpayinfo m " +
                            " JOIN ms_pc_orderinfo o ON o.Id = m.OrderId AND o.OrderStatusId = 1 " +
                            " WHERE DATE_FORMAT(o.OrderTime,'%Y')= ? " +
                            " AND o.CloudId = ?  "  +
                            " GROUP BY month " ;
        return jdbcTemplate.query(sql, new Object[]{ year , cloudId }, new RowMapper<Map<String,String>>() {
            @Override
            public Map<String, String> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, String> map = new HashMap<String, String>();
                map.put("sum" , rs.getString("sum"));
                map.put("month" , rs.getString("month"));
                return map;
            }
        });
    }


    @Override
    public List<Map<String, Object>> findOrderCountOfDay(String startTime, String endTime, String cloudId) {

        String sql = "SELECT COUNT(*) count, COUNT(IF(OrderStatusId=1, true ,null)) finished ,DATE_FORMAT(OrderTime,'%y-%m-%d') time FROM ms_pc_orderinfo " +

                    "WHERE CloudId = ? " ;

                if(!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
                    sql += "AND DATE_FORMAT(OrderTime,'%Y-%m-%d') >= '" + startTime  + "' AND DATE_FORMAT(OrderTime,'%Y-%m-%d')<='" + endTime + "'" ;
                }

                sql += "GROUP BY  time" ;
        return jdbcTemplate.query(sql, new Object[]{cloudId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("all" , rs.getString("count"));
                map.put("finished" , rs.getString("finished"));
                map.put("dayTime" , rs.getString("time"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> findAccountInfo(String month) {

        String sql = "SELECT a.CloudId ,b.RestaurantName , b.BranchName  ,sum(if(a.PayTypeId IN (4) ,a.PayMoney , 0)) aliPay ," +
                " sum(if(a.PayTypeId = 5 ,a.PayMoney , 0)) wechatPay ," +
                " sum(if(a.PayTypeId = 1 ,a.PayMoney , 0)) cashPay " +
                " FROM ms_pc_orderpayinfo a " +
                " RIGHT JOIN ms_pc_restaurantinfo b ON a.CloudId = b.CloudId "  ;
            if(month !=null && !"".equals(month)){
                sql +=  "WHERE DATE_FORMAT( a.PayDate ,'%m') = '" + month + "'" ;
            }

                sql +=" GROUP BY a.CloudId";

        return jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("cloudId" , rs.getString("CloudId") );
                map.put("restaurantName" , rs.getString("RestaurantName"));
                map.put("branchName" , rs.getString("BranchName"));
                map.put("aliPay" , rs.getDouble("aliPay")) ;
                map.put("wechatPay" , rs.getDouble("wechatPay"));
                map.put("cashPay" , rs.getDouble("cashPay"));
                return map;
            }
        });
    }




    @Override
    public List<Map<String, Object>> findAccountInfo(String month, String userId) {
        /*String sql = "SELECT a.cloudId , a.RestaurantName , a.BranchName  ,sum(if(b.PayTypeId IN (4) ,b.PayMoney , 0)) aliPay ," +
                " sum(if(b.PayTypeId = 5 ,b.PayMoney , 0)) wechatPay FROM ms_pc_restaurantinfo a " +
                " LEFT JOIN ms_pc_orderpayinfo b ON a.CloudId = b.CloudId AND  DATE_FORMAT(b.PayDate,'%m') = ? " +
                " WHERE a.CompanyId =  ? " +
                "GROUP BY a.CloudId";*/

        String sql =  " select a.CloudId , a.RestaurantName , a.BranchName , m.aliPay , m.wechatPay , m.cashPay " +
                            " from ms_web_companyuserrole c " +
                            " JOIN ms_pc_restaurantinfo a ON c.CloudId = a.CloudId " +
                            " LEFT JOIN ( " +
                            " select o.CloudId ,sum(if(p.PayTypeId = 4 ,p.PayMoney , 0)) aliPay , sum(if(p.PayTypeId = 5 ,p.PayMoney , 0)) wechatPay , " +
                            " sum(if(p.PayTypeId = 1 ,p.PayMoney - o.CashChange  , 0)) cashPay from ms_pc_orderinfo o " +
                            " JOIN ms_pc_orderpayinfo p ON o.Id = p.OrderId " +
                            " WHERE o.OrderStatusId = 1 AND MONTH(o.OrderTime) = ? " +
                            " GROUP BY o.CloudId " +
                            " ) m ON c.CloudId = m.CloudId " +
                            " WHERE c.UserId =  ? " ;

        return jdbcTemplate.query(sql, new Object[]{month , userId} ,new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("cloudId" , rs.getString("CloudId") );
                map.put("restaurantName" , rs.getString("RestaurantName"));
                map.put("branchName" , rs.getString("BranchName"));
                map.put("aliPay" , rs.getDouble("aliPay")) ;
                map.put("wechatPay" , rs.getDouble("wechatPay"));
                map.put("cashPay" , rs.getDouble("cashPay"));
                return map;
            }
        });
    }

    /**
     * 统计各菜品销售数量
     * @param cloudId
     * @return
     */
    @Override
    public List<Map<String, Object>> findAmountByFoodType(String cloudId) {

        String sql = "SELECT c.CloudId , IFNULL(SUM(a.FoodPrice * a.FoodCount),0) sum ,c.FoodTypeName  FROM ms_pc_orderdetailsinfo a " +
                        "JOIN ms_pc_foodinfo b ON a.FoodId = b.Id AND a.CloudId = b.CloudId " +
                "RIGHT JOIN ms_pc_base_foodtypeinfo c ON c.Id = b.FoodTypeId and a.CloudId = c.CloudId " +
                "WHERE c.CloudId =  ? " +
                "GROUP BY c.CloudId ,c.Id";
        return jdbcTemplate.query(sql, new Object[]{cloudId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String , Object> map = new HashMap<String, Object>();
                map.put("sum" , rs.getString("sum"));
                map.put("foodTypeName" , rs.getString("FoodTypeName"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> findFoodSellInfo(String cloudId, String limit) {


        String sql =  " SELECT  a.CloudId ,a.FoodId  ,a.FoodName ,SUM(a.FoodCount) sum FROM ms_pc_orderdetailsinfo a " +
                            " JOIN ms_pc_orderinfo b ON b.Id = a.OrderId AND b.OrderStatusId = 1 " +
                            "  WHERE a.CloudId = ? " +
                            "  GROUP BY a.FoodId  " +
                            "  ORDER BY sum DESC " ;

        if(!TextUtils.isEmpty(limit)){
            sql += "limit 0 , " + limit;
        }
        return jdbcTemplate.query(sql, new Object[]{cloudId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("foodName", rs.getString("FoodName"));
                map.put("count" , rs.getString("sum") );
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> findFootFall(String cloudId, String startTime, String endTime) {

        String sql      = " SELECT " +
                                " IFNULL(sum(PeopleCount),0) count, " +
                                " DATE_FORMAT(OrderTime, '%y-%m-%d') dayTime " +
                                " FROM " +
                                " ms_pc_orderinfo " +
                                " WHERE " +
                                " cloudId = ? " +
                                " AND OrderStatusId = 1 " +
                                " AND DATE_FORMAT(OrderTime, '%Y-%m-%d') >= ?  AND DATE_FORMAT(OrderTime, '%Y-%m-%d')<= ? " +
                                " GROUP BY " +
                                " DATE_FORMAT(OrderTime, '%y-%m%-%d') " +
                                " ORDER BY " +
                                " dayTime ASC ";
        return jdbcTemplate.query(sql, new Object[]{cloudId, startTime, endTime}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String , Object> map = new HashMap<String, Object>();
                map.put("count" , rs.getString("count"));
                map.put("dayTime" , rs.getString("dayTime"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> findAvgCost(String cloudId) {

        String sql = " SELECT sum(if(a.PayTypeId = 1 , a.PayMoney - b.CashChange  , a.PayMoney)) sum, sum(b.PeopleCount) count ,ROUND(sum(if(a.PayTypeId = 1 , a.PayMoney - b.CashChange  , a.PayMoney))/sum(b.PeopleCount)) avg ,DATE_FORMAT(b.OrderTime,'%y-%m') month from ms_pc_orderpayinfo  a " +
                          " JOIN ms_pc_orderinfo b ON b.Id = a.OrderId " +
                          " WHERE a.CloudId = ? AND b.OrderStatusId = 1 " +
                          " GROUP BY DATE_FORMAT(b.OrderTime , '%y-%m') " ;
        return jdbcTemplate.query(sql, new Object[]{cloudId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String ,Object> map = new HashMap<String, Object>();
                map.put("sum" , rs.getDouble("sum"));
                map.put("count" , rs.getDouble("count"));
                map.put("avg" , rs.getDouble("avg"));
                map.put("month" , rs.getString("month"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> findCostCount(String cloudId, String month) {

        /*String sql =   " SELECT count(*) count , a.count  costTime from ( " +
                            " SELECT count(*) count ,CloudId , tel from ms_pc_orderinfo " +
                            " WHERE CloudId = ? " +
                            " AND OrderStatusId = 1 " +
                            " AND DATE_FORMAT(OrderTime,'%Y-%m') = ? " +
                            " AND tel IS NOT NULL " +
                            " GROUP BY   tel ) a " +
                            " GROUP BY costTime ";*/

        String sql = " SELECT b.count / c.count * 100 percent, b.costTime FROM " +
                            "( SELECT count(*) count , a.count costTime FROM " +
                            " ( SELECT count(*) count FROM ms_pc_orderinfo WHERE CloudId = ? " +
                            " AND OrderStatusId = 1 " +
                            " AND tel IS NOT NULL " +
                            " AND DATE_FORMAT(OrderTime, '%Y-%m') = ? " +
                            " GROUP BY " +
                            " tel ) a GROUP BY costTime) b ," +
                            " ( SELECT count(*) count FROM " +
                            "( SELECT count(*)  FROM ms_pc_orderinfo " +
                            " WHERE CloudId = ? " +
                            " AND OrderStatusId = 1 " +
                            " AND tel IS NOT NULL" +
                            " AND DATE_FORMAT(OrderTime, '%Y-%m') = ? " +
                            " GROUP BY tel ) a ) c ";
        return jdbcTemplate.query(sql, new Object[]{cloudId, month , cloudId , month}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String , Object> map = new HashMap<String, Object>();
                map.put("percent" , rs.getDouble("percent"));
                map.put("costTimes" , rs.getInt("costTime"));
                return map;
            }
        });
    }

    @Override
    public List<Map<String, Object>> findTableTurnoverRate(String cloudId, String startTime, String endTime) {

        String sql =  " SELECT ROUND(b.count/a.count*100,2) tableTurnoverRate, b.dayTime  from " +
                            " (SELECT count(*) count ,CloudId from ms_pc_tableinfo where DelFlag = 0 GROUP BY CloudId) a JOIN " +
                            " (SELECT count(*) count ,DATE_FORMAT(OrderTime,'%Y-%m-%d') dayTime , CloudId from ms_pc_orderinfo " +
                            " WHERE OrderStatusId = 1 " +
                            " GROUP BY CloudId ,DATE_FORMAT(OrderTime,'%Y-%m-%d') ) b ON a.CloudId = b.CloudId " +
                            " WHERE a.CloudId = ? " +
                            " AND DATE_FORMAT(b.dayTime,'%Y-%m-%d') >= ? AND DATE_FORMAT(b.dayTime,'%Y-%m-%d') <= ? " +
                            " ORDER BY b.dayTime " ;
        return jdbcTemplate.query(sql, new Object[]{cloudId, startTime, endTime}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("tableTurnoverRate" , rs.getDouble( "tableTurnoverRate") );
                map.put("dayTime" , rs.getString( "dayTime") ) ;
                return map ;
            }
        });
    }


    /**
     * PayOrder隐射类
     */
    class PayOrderMapper implements RowMapper<PayOrder>{

        public PayOrder mapRow(ResultSet rs, int i) throws SQLException {
            PayOrder payOrder = new PayOrder();
            payOrder.setId(rs.getString("Id"));
            payOrder.setOrderNo(rs.getString("OrderNum"));
            payOrder.setPayDate(rs.getString("PayDate"));
            payOrder.setPayMoney(rs.getString("PayMoney"));
            payOrder.setPayTypeName(rs.getString("PayTypeName"));
            return payOrder;
        }
    }

}
