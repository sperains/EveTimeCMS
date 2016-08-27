package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.CompanyDao;
import com.evetime.cms.entity.Company;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-06-04.
 */
@Repository("CompanyDao")
public class CompanyDaoImplByTemplate implements CompanyDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Company> findAll() {

        String sql = "SELECT c.Id , c.CompanyName , LPAD(c.ProvinceId , 2 , '0') ProvinceId, LPAD(c.CityId , 3 , '0') CityId  , c.DistrictId , c.Address ,c.CreateTime , c.DelFlag , p.ProvinceName , ci.CityName , d.DistrictName  FROM ms_web_companyinfo c " +
                "LEFT JOIN ms_pc_dic_provinceinfo p ON c.ProvinceId = p.ProvinceId " +
                "LEFT JOIN ms_pc_dic_cityinfo ci ON c.CityId = ci.CityId " +
                "LEFT JOIN ms_pc_dic_districtinfo d ON c.DistrictId = d.DistrictId " +
                "WHERE c.DelFlag = 0 ";
        return jdbcTemplate.query(sql, new CompanyRowMapper());
    }

    @Override
    public List<Company> findByCompanyId(String companyId) {

        String sql = "SELECT * FROM ms_web_companyinfo WHERE Id = ? ";
        return jdbcTemplate.query(sql , new Object[]{companyId} , new CompanyRowMapper());
    }

    @Override
    public int insert(final Company company) {

        String sql = "INSERT INTO ms_web_companyinfo " +
                        "(Id, CompanyName , ProvinceId , CityId , DistrictId , " +
                        "Address , CreateTime , DelFlag )" +
                        "VALUES( ? , ? , ? , ? , ? ," +
                        " ? , now() , ? )";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1 , company.getCompanyId());
                ps.setString(2 , company.getCompanyName());
                ps.setString(3 , company.getProvinceId());
                ps.setString(4 , company.getCityId());
                ps.setString(5 , company.getDistrictId());
                ps.setString(6 , company.getAddress());
                ps.setInt(7 , 0 );

            }
        });
    }

    @Override
    public int recycle(String companyId) {

        String sql = "UPDATE ms_web_companyinfo SET DelFlag = ?  WHERE Id = ? ";

        return jdbcTemplate.update(sql , new Object[]{1 , companyId});
    }

    @Override
    public int update(final Company company) {

        String sql = " UPDATE ms_web_companyinfo SET " +
                        " CompanyName = ? ," +
                        " ProvinceId = ? , " +
                        " CityId = ? , " +
                        " DistrictId = ? ," +
                        " Address = ? " +
                        " WHERE Id = ? ";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString( 1 , company.getCompanyName());
                ps.setString( 2 , company.getProvinceId());
                ps.setString( 3 , company.getCityId());
                ps.setString( 4 , company.getDistrictId());
                ps.setString( 5 , company.getAddress());
                ps.setString( 6 , company.getCompanyId());
            }
        });
    }

    @Override
    public void insert(final List<Map<String, String>> ralations) {

        String sql = "INSERT INTO ms_web_companywithbrandInfo " +
                "VALUES(? , ? )" ;
        jdbcTemplate.batchUpdate(sql , new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Map<String,String> ralation = ralations.get(i);
                ps.setString(1 , ralation.get("companyId"));
                ps.setString(2 , ralation.get("brandId"));
            }

            @Override
            public int getBatchSize() {
                return ralations.size();
            }
        });
    }

    @Override
    public int removeRalations(String companyId) {

        String sql = "DELETE FROM ms_web_companywithbrandInfo WHERE CompanyId = ? ";
        return jdbcTemplate.update(sql , companyId);
    }

    @Override
    public void delete(final List<Map<String, String>> ralations) {
        String sql = "DELETE FROM ms_web_companywithbrandInfo WHERE CompanyId = ? AND BrandId = ? ";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Map<String,String> ralation = ralations.get(i);
                ps.setString(1 , ralation.get("companyId"));
                ps.setString(2 , ralation.get("brandId"));
            }

            @Override
            public int getBatchSize() {
                return ralations.size();
            }
        });
    }

    @Override
    public List<String> findBrandId(String companyId) {

        String sql = "SELECT BrandId FROM ms_web_companywithbrandinfo WHERE CompanyId = ? ";
        return jdbcTemplate.queryForList(sql , new Object[]{companyId} , String.class);
    }


    class CompanyRowMapper implements  RowMapper<Company>{

        @Override
        public Company mapRow(ResultSet rs, int i) throws SQLException {
            Company company = new Company();
            company.setCompanyId(rs.getString("Id"));
            company.setCompanyName(rs.getString("CompanyName"));
            company.setProvinceId(rs.getString("ProvinceId"));
            company.setProvinceName(rs.getString("ProvinceName"));
            company.setCityId(rs.getString("CityId"));
            company.setCityName(rs.getString("CityName"));
            company.setDistrictId(rs.getString("DistrictId"));
            company.setDistrictName(rs.getString("DistrictName"));
            company.setAddress(rs.getString("Address"));
            company.setCreateTime(rs.getString("CreateTime"));
            company.setDelFlag(rs.getInt("DelFlag"));
            return company;
        }
    }
}
