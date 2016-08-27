package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.ShopDao;
import com.evetime.cms.entity.Shop;
import com.evetime.cms.entity.ShopType;
import com.evetime.cms.util.UUIDGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rains
 * on 2016-04-21.
 */

@Repository("ShopDao")
public class ShopDaoImplByTemplate implements ShopDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Shop> findAll() {

        String sql =  " SELECT r.Id , r.CloudId , r.RestaurantName , r.BranchName , r.Address , r.Tel , r.Phone , r.BrandId , r.CompanyId , c.CompanyName ,r.RestaurantType, " +
                            " b.BrandName , a.AuditStatus ,r.StartTime , r.EndTime , r.PersonPrice , LPAD(r.ProvinceId,2,'0') ProvinceId , " +
                            " LPAD(r.CityId , 3 , '0') CityId , r.DistrictId " +
                            " FROM ms_pc_restaurantinfo  r " +
                            " LEFT JOIN ms_web_brandinfo b  ON r.BrandId = b.Id " +
                            " LEFT JOIN ms_web_companyinfo c ON c.Id = r.CompanyId " +
                            " LEFT JOIN ms_pc_cloudaccountinfo a ON a.Id = r.CloudId " +
                            " WHERE r.DelFlag = 0 " ;
        return jdbcTemplate.query(sql,  new RowMapper<Shop>() {
            @Override
            public Shop mapRow(ResultSet rs, int i) throws SQLException {
                Shop shop = new Shop();
                shop.setId(rs.getString("Id"));
                shop.setCloudId(rs.getString("CloudId"));
                shop.setRestaurantName(rs.getString("RestaurantName"));
                shop.setBranchName(rs.getString("BranchName"));
                shop.setBrandId(rs.getString("BrandId"));
                shop.setCompanyId(rs.getString("CompanyId"));
                shop.setBrandName(rs.getString("BrandName"));
                shop.setAuditStatus(rs.getInt("AuditStatus"));
                shop.setStartTime(rs.getString("StartTime"));
                shop.setEndTime(rs.getString("EndTime"));
                shop.setPersonPrice(rs.getString("PersonPrice"));
                shop.setProvinceId(rs.getString("ProvinceId"));
                shop.setCityId(rs.getString("CityId"));
                shop.setDistrictId(rs.getString("DistrictId"));
                shop.setAddress(rs.getString("Address"));
                shop.setRestaurantType(rs.getInt("RestaurantType"));
                shop.setTel(rs.getString("Tel"));
                shop.setPhone(rs.getString("Phone"));
                return shop;
            }
        });
    }

    @Override
    public List<Shop> findShops(String userId) {

        /*String sql = "SELECT r.Id , r.CloudId , r.RestaurantName , r.BranchName  , r.Address ,r.Tel , r.Phone,  r.BrandId ,  b.BrandName , a.AuditStatus , r.StartTime , r.EndTime  , r.PersonPrice ,r.RestaurantType , LPAD(r.ProvinceId,2,'0') ProvinceId , LPAD(r.CityId , 3 , '0') CityId , r.DistrictId  FROM ms_web_companyuserrole r " +
                " LEFT JOIN ms_web_brandinfo b ON r.BrandId = b.Id " +
                " LEFT JOIN ms_pc_cloudaccountinfo a ON a.Id = r.CloudId " +
                *//*" WHERE r.BrandId IN " +
                " (SELECT c.BrandId FROM ms_web_companywithbrandinfo c " +*//*
                " WHERE r.CompanyId = ?  " +
                " AND r.DelFlag = 0 " ;*/
        String sql =  " SELECT r.Id , r.CloudId , r.RestaurantName , r.BranchName , r.Address , r.Tel , r.Phone , b.Id BrandId , r.RestaurantType, " +
                            " b.BrandName , a.AuditStatus ,r.StartTime , r.EndTime , r.PersonPrice , LPAD(r.ProvinceId,2,'0') ProvinceId , " +
                            " LPAD(r.CityId , 3 , '0') CityId , r.DistrictId " +
                            " FROM ms_web_companyuserrole c " +
                            " LEFT JOIN ms_pc_restaurantinfo r ON c.CloudId = r.CloudId " +
                            " LEFT JOIN ms_web_brandinfo b ON r.BrandId = b.Id " +
                            " LEFT JOIN ms_pc_cloudaccountinfo a ON a.Id = r.CloudId " +
                            " WHERE UserId = ? " ;

        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Shop>() {
            @Override
            public Shop mapRow(ResultSet rs, int i) throws SQLException {
                Shop shop = new Shop();
                shop.setId(rs.getString("Id"));
                shop.setCloudId(rs.getString("CloudId"));
                shop.setRestaurantName(rs.getString("RestaurantName"));
                shop.setBranchName(rs.getString("BranchName"));
                shop.setBrandId(rs.getString("BrandId"));
                shop.setBrandName(rs.getString("BrandName"));
                shop.setAuditStatus(rs.getInt("AuditStatus"));
                shop.setStartTime(rs.getString("StartTime"));
                shop.setEndTime(rs.getString("EndTime"));
                shop.setPersonPrice(rs.getString("PersonPrice"));
                shop.setProvinceId(rs.getString("ProvinceId"));
                shop.setCityId(rs.getString("CityId"));
                shop.setDistrictId(rs.getString("DistrictId"));
                shop.setAddress(rs.getString("Address"));
                shop.setRestaurantType(rs.getInt("RestaurantType"));
                shop.setTel(rs.getString("Tel"));
                shop.setPhone(rs.getString("Phone"));
                return shop;
            }
        });
    }

    @Override
    public List<ShopType> findTypes() {

        String sql = "SELECT * FROM ms_pc_dic_restauranttype ";
        return jdbcTemplate.query(sql, new RowMapper<ShopType>() {
            @Override
            public ShopType mapRow(ResultSet rs, int i) throws SQLException {
                ShopType type = new ShopType();
                type.setTypeId(rs.getInt("Id"));
                type.setTypeName(rs.getString("RestaurantType"));
                return type;
            }
        });
    }

    @Override
    public int updateShop(Shop shop) {

        String sql = "UPDATE ms_pc_restaurantinfo " +
                "SET " +
                "BrandId = ? ," +
                "RestaurantName = ? ," +
                "BranchName = ? ," +
                "RestaurantType = ? ," +
                "Tel = ? , " +
                "StartTime = ? , " +
                "EndTime = ? ," +
                "PersonPrice = ? ," +
                "ProvinceId = ? ," +
                "CityId = ? ," +
                "DistrictId = ? " +
                "WHERE Id = ? ";
        return jdbcTemplate.update(sql , new Object[]{shop.getBrandId() , shop.getRestaurantName() , shop.getBranchName() , shop.getRestaurantType() , shop.getTel() , shop.getStartTime() ,
        shop.getEndTime(), shop.getPersonPrice() , shop.getProvinceId() , shop.getCityId() , shop.getDistrictId() , shop.getId()});
    }

    @Override
    public int insertShop(final Shop shop) {

        String sql = "INSERT INTO ms_pc_restaurantinfo " +
                "(Id , CloudId , BrandId , CompanyId , RestaurantName , " +
                "BranchName , ProvinceId , CityId , DistrictId , Address , " +
                "StartTime , EndTime , RestaurantType , Tel , PersonPrice , DelFlag )" +
                "VALUES" +
                "(? , ? , ? , ? , ? ," +
                "? , ? , ? , ? , ? ," +
                "? , ? , ? , ? , ? , ? )";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1 , shop.getId());
                ps.setString(2 , shop.getCloudId());
                ps.setString(3 , shop.getBrandId());
                ps.setString(4 , shop.getCompanyId());
                ps.setString(5 , shop.getRestaurantName());
                ps.setString(6 , shop.getBranchName());
                ps.setString(7 , shop.getProvinceId());
                ps.setString(8 , shop.getCityId());
                ps.setString(9 , shop.getDistrictId());
                ps.setString(10 , shop.getAddress());
                ps.setString(11 , shop.getStartTime());
                ps.setString(12 , shop.getEndTime());
                ps.setInt(13 , shop.getRestaurantType());
                ps.setString(14 , shop.getTel());
                ps.setString(15 , shop.getPersonPrice());
                ps.setInt(16 , 0);
            }
        });
    }

    @Override
    public int recycle(String id) {

        String sql = "UPDATE ms_pc_restaurantinfo SET DelFlag = 1 " +
                "WHERE id = ? ";
        return jdbcTemplate.update(sql , id);
    }

    @Override
    public List<Shop> findUnbindShops() {

        String sql = " SELECT * from ms_pc_restaurantinfo WHERE DelFlag  = 0 AND BrandId IS NULL " ;
        return jdbcTemplate.query(sql, new RowMapper<Shop>() {
            @Override
            public Shop mapRow(ResultSet rs, int i) throws SQLException {
                Shop shop = new Shop();
                shop.setRestaurantName(rs.getString("RestaurantName"));
                shop.setBranchName(rs.getString("BranchName"));
                shop.setAddress(rs.getString("Address"));
                return shop;
            }
        });
    }

    @Override
    public List<Shop> findSettingShops(String userId) {
        String sql =  " SELECT * from ms_pc_restaurantinfo r " +
                            " LEFT JOIN ms_web_brandinfo b  ON r.BrandId = b.Id " +
                            " LEFT JOIN ms_web_companyinfo c ON c.Id = r.CompanyId " +
                            " WHERE r.CloudId IN " +
                            " (SELECT CloudId FROM ms_web_companyuserrole WHERE UserId = ? ) " +
                            " OR BrandId IS NULL " +
                            " AND  r.DelFlag = 0 " ;
        return jdbcTemplate.query(sql , new Object[]{userId} , new ShopRowMapper());
    }

    @Override
    public int updateCompanyId(String userId) {

        String sql =  " UPDATE ms_pc_restaurantinfo SET CompanyId = (SELECT CompanyId from ms_cms_user WHERE id = ? ) " +
                            " WHERE CloudId IN (SELECT CloudId FROM ms_web_companyuserrole WHERE UserId = ? ) ";
        return jdbcTemplate.update(sql , new Object[]{userId , userId});
    }

    @Override
    public int updateCompanyIdAndBrandId(String cloudId , String companyId , String brandId) {

        String sql = " UPDATE ms_pc_restaurantinfo SET CompanyId = ? ," +
                          " BrandId = ? WHERE CloudId = ?  " ;
        return jdbcTemplate.update(sql , new Object[]{companyId , brandId , cloudId});
    }

    @Override
    public List<Shop> findbyCompanyId(String companyId) {

        String sql =   " SELECT * FROM ms_pc_restaurantinfo  r " +
                            "LEFT JOIN ms_web_brandinfo b  ON r.BrandId = b.Id " +
                            "LEFT JOIN ms_web_companyinfo c ON c.Id = r.CompanyId " +
                            "WHERE r.DelFlag = 0  AND companyId = ? " ;
        return jdbcTemplate.query(sql , new Object[]{companyId} , new ShopRowMapper());
    }


    class ShopRowMapper implements RowMapper<Shop>{

        public Shop mapRow(ResultSet rs, int i) throws SQLException {
            Shop shop = new Shop();
            shop.setId(rs.getString("Id"));
            shop.setCloudId(rs.getString("CloudId"));
            shop.setBrandId(rs.getString("BrandId"));
            shop.setBrandName(rs.getString("BrandName"));
            shop.setCompanyId(rs.getString("CompanyId"));
            shop.setCompanyName(rs.getString("CompanyName"));
            shop.setRestaurantName(rs.getString("RestaurantName"));
            shop.setBranchName(rs.getString("BranchName"));
            shop.setProvinceId(rs.getString("ProvinceId"));
            shop.setCityId(rs.getString("CityId"));
            shop.setDistrictId(rs.getString("DistrictId"));
            shop.setAddress(rs.getString("Address"));
            shop.setLon(rs.getDouble("Longitude"));
            shop.setLat(rs.getDouble("Latitude"));
            shop.setStartTime(rs.getString("StartTime"));
            shop.setEndTime(rs.getString("EndTime"));
            shop.setIsAllDay(rs.getInt("IsAllDay"));
            shop.setRestaurantType(rs.getInt("RestaurantType"));
            shop.setPhone(rs.getString("Phone"));
            shop.setTel(rs.getString("Tel"));
            return shop;
        }
    }
}
