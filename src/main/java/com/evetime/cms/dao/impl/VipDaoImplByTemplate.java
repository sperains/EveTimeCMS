package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.VipDao;
import com.evetime.cms.entity.Vip;
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
 * on 2016-06-21.
 */
@Repository("VipDao")
public class VipDaoImplByTemplate implements VipDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Vip> queryAll(){

        String  sql  = "SELECT * FROM ms_pc_base_vippersoninfo " ;
        return jdbcTemplate.query(sql, new RowMapper<Vip>() {
            @Override
            public Vip mapRow(ResultSet rs, int i) throws SQLException {
                Vip vip = new Vip();
                vip.setId(rs.getString("ID"));
                vip.setCloudId(rs.getString("CloudId"));
                vip.setVipCode(rs.getString("VipCode"));
                vip.setVipName(rs.getString("VipName"));
                vip.setVipCardType(rs.getString("VipCardType"));
                vip.setSex(rs.getString("sex"));
                vip.setBirthday(rs.getString("Birthdate"));
                vip.setMobilePhone(rs.getString("MobilePhone"));
                vip.setAddress(rs.getString("Address"));
                vip.setScore(rs.getInt("Score"));
                vip.setRemarks(rs.getString("Remarks"));
                vip.setIsUsed(rs.getInt("IsUsed"));
                vip.setDelFlag(rs.getInt("DelFlag"));
                vip.setWorkAddress(rs.getString("WorkAddress"));
                vip.setPhone(rs.getString("Phone"));
                vip.setMemberValidityDate(rs.getString("MemberValidityDate"));
                vip.setIsLongValidity(rs.getInt("IsLongValidity"));
                return vip;
            }
        });
    }

    @Override
    public List<Vip> queryByUserId(String userId) {

        String  sql  = " SELECT * FROM ms_pc_base_vippersoninfo  a " +
                            " JOIN ms_pc_base_vipcardinfo c ON a.VipCardType = c.Id" +
                            " WHERE EXISTS (SELECT  * FROM ms_web_companyuserrole b WHERE  b.UserId = ? AND b.CloudId = a.CloudId )" +
                            " AND a.DelFlag = 0 " ;
        return jdbcTemplate.query(sql, new Object[]{userId} ,new RowMapper<Vip>() {
            @Override
            public Vip mapRow(ResultSet rs, int i) throws SQLException {
                Vip vip = new Vip();
                vip.setId(rs.getString("ID"));
                vip.setCloudId(rs.getString("CloudId"));
                vip.setVipCode(rs.getString("VipCode"));
                vip.setVipName(rs.getString("VipName"));
                vip.setVipCardType(rs.getString("VipCardType"));
                vip.setVipCardName(rs.getString("VipCardName"));
                vip.setDefaultDiscount(rs.getString("DefaultDiscount"));
                vip.setSex(rs.getString("sex"));
                vip.setBirthday(rs.getString("Birthdate"));
                vip.setMobilePhone(rs.getString("MobilePhone"));
                vip.setAddress(rs.getString("Address"));
                vip.setScore(rs.getInt("Score"));
                vip.setRemarks(rs.getString("Remarks"));
                vip.setIsUsed(rs.getInt("IsUsed"));
                vip.setDelFlag(rs.getInt("DelFlag"));
                vip.setWorkAddress(rs.getString("WorkAddress"));
                vip.setPhone(rs.getString("Phone"));
                vip.setMemberValidityDate(rs.getString("MemberValidityDate"));
                vip.setIsLongValidity(rs.getInt("IsLongValidity"));
                return vip;
            }
        });
    }

    @Override
    public Vip queryById(String vipId) {

        String  sql  = " SELECT * FROM ms_pc_base_vippersoninfo  a " +
                " JOIN ms_pc_base_vipcardinfo c ON a.VipCardType = c.Id " +
                " WHERE a.ID = ? AND a.DelFlag = 0 " ;

        return jdbcTemplate.queryForObject(sql, new Object[]{vipId}, new RowMapper<Vip>() {
            @Override
            public Vip mapRow(ResultSet rs, int i) throws SQLException {
                Vip vip = new Vip();
                vip.setId(rs.getString("ID"));
                vip.setCloudId(rs.getString("CloudId"));
                vip.setVipCode(rs.getString("VipCode"));
                vip.setVipName(rs.getString("VipName"));
                vip.setVipCardType(rs.getString("VipCardType"));
                vip.setVipCardName(rs.getString("VipCardName"));
                vip.setDefaultDiscount(rs.getString("DefaultDiscount"));
                vip.setSex(rs.getString("sex"));
                vip.setBirthday(rs.getString("Birthdate"));
                vip.setMobilePhone(rs.getString("MobilePhone"));
                vip.setAddress(rs.getString("Address"));
                vip.setScore(rs.getInt("Score"));
                vip.setRemarks(rs.getString("Remarks"));
                vip.setIsUsed(rs.getInt("IsUsed"));
                vip.setDelFlag(rs.getInt("DelFlag"));
                vip.setWorkAddress(rs.getString("WorkAddress"));
                vip.setPhone(rs.getString("Phone"));
                vip.setMemberValidityDate(rs.getString("MemberValidityDate"));
                vip.setIsLongValidity(rs.getInt("IsLongValidity"));
                return vip;
            }
        });
    }

    @Override
    public Vip queryByVipCode() {
        return null;
    }

    @Override
    public int add(Vip vip) {

        String sql  =  " INSERT INTO ms_pc_base_vippersoninfo " +
                            " (ID , CloudId , VipCode , VipName , VipCardType , sex , Birthdate , MobilePhone , Address , " +
                            " Remarks , IsUsed , DelFlag , WorkAddress , Phone , MemberValidityDate , IsLongValidity ) " +
                            " VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , " +
                            " ? , ? , ? , ? , ? , ? , ? ) ";

        return 0;
    }

    @Override
    public int delete(String vipId) {

        String sql      =    " UPDATE ms_pc_base_vippersoninfo SET DelFlag = 1 " +
                                "WHERE ID IN (  "+vipId + " ) ";
        return jdbcTemplate.update(sql );
    }

    @Override
    public int update(final Vip vip) {

        String sql  =  " UPDATE ms_pc_base_vippersoninfo SET " +
                            " VipName = ? , " +
                            " sex = ? ," +
                            " Birthdate = ? , " +
                            " MobilePhone = ? ," +
                            " Address = ? ," +
                            " Remarks = ? ," +
                            " IsUsed = ? ," +
                            " WorkAddress = ? ," +
                            " Phone = ? ," +
                            " IsLongValidity = ? ," +
                            " VipCardType = ? " +
                            " WHERE ID = ? ";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

                ps.setString( 1 , vip.getVipName());
                ps.setString( 2 , vip.getSex() );
                ps.setString( 3 , vip.getBirthday());
                ps.setString( 4 , vip.getMobilePhone());
                ps.setString( 5 , vip.getAddress());
                ps.setString( 6 , vip.getRemarks());
                ps.setInt( 7 , vip.getIsUsed());
                ps.setString( 8 , vip.getWorkAddress());
                ps.setString( 9 , vip.getPhone());
                ps.setInt( 10 , vip.getIsLongValidity());
                ps.setString( 11 , vip.getVipCardType());
                ps.setString( 12 , vip.getId());
            }
        });
    }
}
