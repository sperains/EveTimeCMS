package com.evetime.cms.dao.impl;

import com.evetime.cms.entity.Brand;
import com.evetime.cms.util.UUIDGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-06-02.
 */
@Repository("BrandDao")
public class BrandDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(final Brand brand){

        String sql   = "INSERT INTO ms_web_brandinfo ( Id , BrandName , ImgPath , CreateTime , DelFlag )" +
                            "VALUES ( ? , ? , ? , now() , ? )";

        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1 , UUIDGenerator.getUUID());
                ps.setString(2 , brand.getBrandName());
                ps.setString(3 , brand.getImgPath() );
                ps.setInt( 4 , 0 );
            }
        });
    }

    public int recycle(String  brandId){

        String sql = "UPDATE ms_web_brandinfo SET DelFlag = ?  WHERE Id = ? ";

        return jdbcTemplate.update(sql , new Object[]{1 , brandId});
    }


    public int update(Brand brand){

        String sql = " UPDATE ms_web_brandinfo SET " +
                          " BrandName = ? ," +
                          " ImgPath = ? " +
                          " WHERE Id = ? ";
        return jdbcTemplate.update(sql , new Object[]{brand.getBrandName() , brand.getImgPath() ,brand.getBrandId()}) ;
    }

    public List<Brand> findAll(){

        String sql = "SELECT * FROM ms_web_brandinfo WHERE DelFlag = 0 " ;

        return jdbcTemplate.query(sql, new RowMapper<Brand>() {
            @Override
            public Brand mapRow(ResultSet rs, int i) throws SQLException {
                Brand brand = new Brand();
                brand.setBrandId(rs.getString("Id"));
                brand.setBrandName(rs.getString("BrandName"));
                brand.setCreateTime(rs.getString("CreateTime"));
                brand.setDelFlag(rs.getInt("DelFlag"));
                brand.setImgPath(rs.getString("ImgPath"));
                return brand;
            }
        });
    }

    public List<Brand> findBrands(String companyId){

        String sql = "SELECT c.BrandId ,b.BrandName  FROM ms_web_companywithbrandinfo c " +
                "JOIN ms_web_brandinfo b ON c.BrandId = b.Id " +
                "WHERE CompanyId = ? ";

        return jdbcTemplate.query(sql, new Object[]{companyId}, new RowMapper<Brand>() {
            @Override
            public Brand mapRow(ResultSet rs, int i) throws SQLException {
                Brand brand = new Brand();
                brand.setBrandId(rs.getString("BrandId"));
                brand.setBrandName(rs.getString("BrandName"));
                return brand;
            }
        });
    }


}
