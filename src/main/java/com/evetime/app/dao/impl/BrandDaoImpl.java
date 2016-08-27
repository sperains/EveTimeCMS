package com.evetime.app.dao.impl;

import com.evetime.app.dao.BrandDao;
import com.evetime.app.entity.Brand;
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
 * on 2016-07-04.
 */
@Repository("AppBrandDao")
public class BrandDaoImpl implements BrandDao{

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Brand queryByUserId(String userId) {

        /*String sql =  " SELECT sum(b.PayMoney) todaySale , d.Id , d.BrandName ,d.ImgPath FROM ms_pc_orderinfo a " +
                            " JOIN ms_pc_orderpayinfo b " +
                            " ON a.Id = b.OrderId " +
                            " JOIN ms_web_companyuserrole c " +
                            " ON c.CloudId = a.CloudId " +
                            " JOIN ms_web_brandinfo d ON d.Id = c.BrandId " +
                            " WHERE " +
                            " a.CloudId In (select CloudId from ms_web_companyuserrole WHERE UserId = ? ) " +
                            " AND a.OrderStatusId = 0 " +
                            " AND TO_DAYS(b.PayDate) = TO_DAYS(now()) " ;*/

        String sql =   "select DISTINCT(a.UserId)  , b.BrandName , b.ImgPath from ms_web_companyuserrole a " +
                            "LEFT JOIN ms_web_brandinfo b ON a.BrandId = b.Id " +
                            "where UserId = ? " ;
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new RowMapper<Brand>() {
            @Override
            public Brand mapRow(ResultSet rs, int i) throws SQLException {
                Brand brand = new Brand();
                brand.setSystemId(rs.getString("UserId"));
                brand.setName(rs.getString("BrandName"));
                brand.setImgUrl(rs.getString("ImgPath"));
                return brand;
            }
        });
    }


}
