package com.evetime.cms.dao.impl;

import com.evetime.cms.controller.Business;
import com.evetime.cms.dao.BusinessDao;
import com.evetime.cms.entity.Branch;
import com.evetime.cms.entity.Brand;
import com.evetime.cms.entity.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rains
 * on 2016-04-28.
 */
@Repository
public class BusinessDaoImplByTemplate implements BusinessDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Company> findAllCompanys() {

        String sql = "SELECT * FROM ms_web_companyinfo ";
        return jdbcTemplate.query(sql, new RowMapper<Company>() {
            public Company mapRow(ResultSet rs, int i) throws SQLException {
                Company company = new Company();
                company.setCompanyId(rs.getString("Id"));
                company.setCompanyName(rs.getString("CompanyName"));
                return company;
            }
        });
    }

    public List<Brand> findAllBrands() {

        String sql = "SELECT * FROM ms_web_companybrandinfo ";
        return jdbcTemplate.query(sql, new BrandRowMapper() );
    }

    public List<Brand> findBrandsByCompanyId(String companyId) {

        String sql = "SELECT * FROM ms_web_companybrandinfo WHERE CompanyId = ? ";

        return jdbcTemplate.query(sql , new Object[]{companyId} , new BrandRowMapper());
    }

    public List<Branch> findBranchsByBrandId(String brandId) {

        String sql = "SELECT * FROM ms_pc_restaurantinfo  a WHERE EXISTS " +
                "(SELECT  b.CloudId FROM ms_web_brandwithrestaurantinfo b WHERE BrandId = ? AND a.CloudId = b.CloudId)";
        return jdbcTemplate.query(sql, new Object[]{brandId}, new RowMapper<Branch>() {
            public Branch mapRow(ResultSet rs, int i) throws SQLException {
                Branch branch = new Branch();
                branch.setBranchId(rs.getString("CloudId"));
                branch.setBranchName(rs.getString("RestaurantName") +  "(" +rs.getString("BranchName") + ")");
                return branch;
            }
        });
    }


    class BrandRowMapper implements RowMapper<Brand>{

        public Brand mapRow(ResultSet rs, int i) throws SQLException {
                    Brand brand = new Brand();
                    brand.setBrandId(rs.getString("Id"));
                    brand.setBrandName(rs.getString("BrandName"));
                    return brand;
        }
    }
}
