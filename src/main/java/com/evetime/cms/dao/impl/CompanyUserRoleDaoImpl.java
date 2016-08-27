package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.CompanyUserRoleDao;
import com.evetime.cms.entity.CompanyUserRole;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rains
 * on 2016-07-18.
 */
@Repository("CompanyUserRoleDao")
public class CompanyUserRoleDaoImpl implements CompanyUserRoleDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(final List<CompanyUserRole> companyUserRoleList) {
        String sql = "INSERT INTO ms_web_companyuserrole(UserId , BrandId , CloudId , RestaurantId ) VALUES( ? , ? , ? , ? ) ";

        int[] n = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String userId =  companyUserRoleList.get(i).getUserId();
                String brandId = companyUserRoleList.get(i).getBrandId();
                String cloudId = companyUserRoleList.get(i).getCloudId();
                String restaurantId = companyUserRoleList.get(i).getRestaurantId();
                ps.setString(1,userId);
                ps.setString(2,brandId);
                ps.setString(3,cloudId);
                ps.setString(4,restaurantId);
            }

            @Override
            public int getBatchSize() {
                return companyUserRoleList.size();
            }

        });

        int count = 0 ;
        for(int i : n){
            count += i ;
        }
        return count ;
    }

    @Override
    public int delete(final List<CompanyUserRole> companyUserRoleList) {

        String sql =  " DELETE FROM ms_web_companyuserrole WHERE UserId = ? " +
                            " AND CloudId = ? " ;
        int[] n = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String userId = companyUserRoleList.get(i).getUserId();
                String cloudId = companyUserRoleList.get(i).getCloudId();
                ps.setString(1 , userId);
                ps.setString(2, cloudId);
            }

            @Override
            public int getBatchSize() {
                return companyUserRoleList.size();
            }
        });

        int count = 0 ;
        for(int i : n){
            count += i ;
        }
        return count ;
    }

    @Override
    public int delete(String userId) {

        String sql = "DELETE FROM ms_web_companyuserrole WHERE UserId = ? " ;
        return jdbcTemplate.update(sql , new Object[]{userId});
    }

    @Override
    public List<CompanyUserRole> findAll(String userId) {

        String sql = "SELECT * FROM ms_web_companyuserrole WHERE UserId = ? " ;
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<CompanyUserRole>() {
            @Override
            public CompanyUserRole mapRow(ResultSet rs, int i) throws SQLException {
                CompanyUserRole companyUserRole = new CompanyUserRole();
                companyUserRole.setUserId(rs.getString("UserId"));
                companyUserRole.setBrandId(rs.getString("BrandId"));
                companyUserRole.setRestaurantId(rs.getString("RestaurantId"));
                companyUserRole.setCloudId(rs.getString("CloudId"));
                return companyUserRole;
            }
        });
    }

    @Override
    public int update(String brandId , String cloudId) {

        String sql =  " UPDATE ms_web_companyuserrole SET BrandId = ? " +
                            " WHERE CloudId = ? " ;
        return jdbcTemplate.update(sql , new Object[]{brandId , cloudId});
    }

    public static void main(String[] args) {


        List<String> list1 =  new ArrayList<String>();

        List<String> list2= new ArrayList<String>();

        list1.add("a") ; list1.add("b") ; list1.add("c");
        list2.add("b") ; list2.add("c") ; list2.add("d");

        List<String> list3 = new ArrayList<String>(list1);
        List<String> list4 = new ArrayList<String>(list2);

        list3.removeAll(list4);
        System.out.println(list3);
        System.out.println(list1);


    }


}
