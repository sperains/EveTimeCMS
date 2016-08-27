package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.BusinessInfoDao;
import com.evetime.cms.entity.BusinessInfo;
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
 * on 2016-07-26.
 */
@Repository("BusinessInfoDao")
public class BusinessInfoDaoImpl implements BusinessInfoDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(final BusinessInfo businessInfo) {

        String sql  = " INSERT INTO ms_cms_businessinformation " +
                            " ( Id , Name ,  Email , BusinessShortName ,Category , " +
                            " Bigcategory , Smallcategory , ServiceTel , Website ,BusinessFullName, " +
                            " RegistAddress , BusinessScope , BusinessLicenseStartTime , BusinessLicenseEndTime ,CertificateHolderType , " +
                            " CertificateHolderName , CertificateType , CertificateStartTime , CertificateEndTime , CertificateNo , " +
                            " AccountType , AccountName , AccountBank , AccountBankCity , AccountBranchBank , " +
                            " AccountNo , BusinessRate , ContractNo , ContractStartTime , ContractEndTime , Phone ," +
                            " BusinessLicense , IdCardFrontPage ,  IdCardBackPage ,  ContractPhoto , EvetimeContractImage , RegistCode  " +
                            " )VALUES" +
                            " ( ? , ? , ? , ? , ? , " +
                            " ? , ? , ? , ? , ? , " +
                            " ? , ? , ? , ? , ? , " +
                            " ? , ? , ? , ? , ? , " +
                            " ? , ? , ? , ? , ? , " +
                            " ? , ? , ? , ? , ? , ? , " +
                            " ? , ? , ? , ? , ? , ?  ) " ;
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

                ps.setString( 1 , businessInfo.getId());
                ps.setString( 2 , businessInfo.getName());
                ps.setString( 3 , businessInfo.getEmail());
                ps.setString( 4 , businessInfo.getBusinessShortName());
                ps.setString( 5 , businessInfo.getCategory());
                ps.setString( 6 , businessInfo.getBigCategory());
                ps.setString( 7 , businessInfo.getSmallCategory());
                ps.setString( 8 , businessInfo.getServiceTel());
                ps.setString( 9 , businessInfo.getWebsite());
                ps.setString( 10 , businessInfo.getBusinessFullName());

                ps.setString( 11 , businessInfo.getRegistAddress());
                ps.setString( 12 , businessInfo.getBusinessScope());
                ps.setString( 13 , businessInfo.getBusinessLicenseStartTime());
                ps.setString( 14 , businessInfo.getBusinessLicenseEndTime());
                ps.setString( 15 , businessInfo.getCertificateHolderType());
                ps.setString( 16 , businessInfo.getCertificateHolderName());
                ps.setString( 17 , businessInfo.getCertificateType());
                ps.setString( 18 , businessInfo.getCertificateStartTime());
                ps.setString( 19 , businessInfo.getCertificateEndTime());
                ps.setString( 20 , businessInfo.getCertificateNo());

                ps.setString( 21 , businessInfo.getAccountType());
                ps.setString( 22 , businessInfo.getAccountName());
                ps.setString( 23 , businessInfo.getAccountBank());
                ps.setString( 24 , businessInfo.getAccountBankCity());
                ps.setString( 25 , businessInfo.getAccountBranchBank());
                ps.setString( 26 , businessInfo.getAccountNo());
                ps.setString( 27 , businessInfo.getBusinessRate());
                ps.setString( 28 , businessInfo.getContractNo());
                ps.setString( 29 , businessInfo.getContractStartTime());
                ps.setString( 30 , businessInfo.getContractEndTime());
                ps.setString( 31 , businessInfo.getPhone());

                ps.setString( 32 , businessInfo.getBusinessLicense() );
                ps.setString( 33 , businessInfo.getIdCardFrontPage());
                ps.setString( 34 , businessInfo.getIdCardBackPage());
                ps.setString( 35 , businessInfo.getContractPhoto());
                ps.setString( 36 , businessInfo.getEvetimeContractImage());
                ps.setString( 37 , businessInfo.getRegistCode());

            }
        });
    }

    @Override
    public List<BusinessInfo> queryAll() {

        String sql = " SELECT * from ms_cms_businessinformation WHERE DelFlag = 0 " ;
        return jdbcTemplate.query(sql, new RowMapper<BusinessInfo>() {
            @Override
            public BusinessInfo mapRow(ResultSet rs, int i) throws SQLException {
                BusinessInfo businessInfo = new BusinessInfo();
                businessInfo.setId(rs.getString("Id"));
                businessInfo.setName(rs.getString("Name"));
                businessInfo.setEmail(rs.getString("Email"));
                businessInfo.setBusinessShortName(rs.getString("BusinessShortName"));
                businessInfo.setCategory(rs.getString("Category"));
                businessInfo.setBigCategory(rs.getString("BigCategory"));
                businessInfo.setSmallCategory(rs.getString("SmallCategory"));
                businessInfo.setServiceTel(rs.getString("ServiceTel"));
                businessInfo.setWebsite(rs.getString("Website"));
                businessInfo.setBusinessFullName(rs.getString("BusinessFullName"));

                businessInfo.setRegistAddress(rs.getString("RegistAddress"));
                businessInfo.setBusinessScope(rs.getString("BusinessScope"));
                businessInfo.setBusinessLicenseStartTime(rs.getString("BusinessLicenseStartTime"));
                businessInfo.setBusinessLicenseEndTime(rs.getString("BusinessLicenseEndTime"));
                businessInfo.setCertificateHolderType(rs.getString("CertificateHolderType"));
                businessInfo.setCertificateHolderName(rs.getString("CertificateHolderName"));
                businessInfo.setCertificateType(rs.getString("CertificateType"));
                businessInfo.setCertificateStartTime(rs.getString("CertificateStartTime"));
                businessInfo.setCertificateEndTime(rs.getString("CertificateEndTime"));
                businessInfo.setCertificateNo(rs.getString("CertificateNo"));

                businessInfo.setAccountType(rs.getString("AccountType"));
                businessInfo.setAccountName(rs.getString("AccountName"));
                businessInfo.setAccountBank(rs.getString("AccountBank"));
                businessInfo.setAccountBankCity(rs.getString("AccountBankCity"));
                businessInfo.setAccountBranchBank(rs.getString("AccountBranchBank"));
                businessInfo.setAccountNo(rs.getString("AccountNo"));
                businessInfo.setBusinessRate(rs.getString("BusinessRate"));
                businessInfo.setContractNo(rs.getString("ContractNo"));
                businessInfo.setContractStartTime(rs.getString("ContractStartTime"));
                businessInfo.setContractEndTime(rs.getString("ContractEndTime"));

                businessInfo.setPhone(rs.getString("Phone"));

                businessInfo.setBusinessLicense(rs.getString("BusinessLicense")) ;
                businessInfo.setIdCardFrontPage(rs.getString("IdCardFrontPage"));
                businessInfo.setIdCardBackPage(rs.getString("IdCardBackPage"));
                businessInfo.setContractPhoto(rs.getString("ContractPhoto"));
                businessInfo.setEvetimeContractImage(rs.getString("EvetimeContractImage"));

                businessInfo.setRegistCode(rs.getString("RegistCode"));
                businessInfo.setContractStatus(rs.getString("ContractStatus"));


                return businessInfo;
            }
        });
    }

    /*
    * " ( Id , Name ,  Email , BusinessShortName ,Category , " +
                            " Bigcategory , Smallcategory , ServiceTel , Website ,BusinessFullName, " +
                            " RegistAddress , BusinessScope , BusinessLicenseStartTime , BusinessLicenseEndTime ,CertificateHolderType , " +
                            " CertificateHolderName , CertificateType , CertificateStartTime , CertificateEndTime , CertificateNo , " +
                            " AccountType , AccountName , AccountBank , AccountBankCity , AccountBranchBank , " +
                            " AccountNo , BusinessRate , ContractNo , ContractStartTime , ContractEndTime , Phone ," +
                            " BusinessLicense , IdCardFrontPage ,  IdCardBackPage ,  ContractPhoto , EvetimeContractImage , RegistCode  " +
    *
    * */

    @Override
    public int update(final BusinessInfo businessInfo) {

        String sql = " UPDATE  ms_cms_businessinformation SET " +
                          " Name = ? , Email = ? , BusinessShortName = ? , Category = ? ," +
                          " Bigcategory = ? , Smallcategory = ? , ServiceTel = ? , Website = ? , BusinessFullName = ? , " +
                          " RegistAddress = ? ,BusinessScope = ? ,BusinessLicenseStartTime = ? ,BusinessLicenseEndTime = ? ,CertificateHolderType = ? ," +
                          " CertificateHolderName = ? ,CertificateType = ? ,CertificateStartTime = ? ,CertificateEndTime = ? ,CertificateNo = ? ," +
                          " AccountType = ? ,AccountName = ? ,AccountBank = ? ,AccountBankCity = ? ,AccountBranchBank = ? ," +
                          " AccountNo = ? ,BusinessRate = ? ,ContractNo = ? ,ContractStartTime = ? ,ContractEndTime = ? , Phone = ? ," +
                          " BusinessLicense = ? ,IdCardFrontPage = ? ,IdCardBackPage = ? ,ContractPhoto = ? ,EvetimeContractImage = ? ,RegistCode = ? " +
                          " WHERE Id = ? ";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {


                ps.setString( 1 , businessInfo.getName());
                ps.setString( 2 , businessInfo.getEmail());
                ps.setString( 3 , businessInfo.getBusinessShortName());
                ps.setString( 4 , businessInfo.getCategory());

                ps.setString( 5 , businessInfo.getBigCategory());
                ps.setString( 6 , businessInfo.getSmallCategory());
                ps.setString( 7 , businessInfo.getServiceTel());
                ps.setString( 8 , businessInfo.getWebsite());
                ps.setString( 9 , businessInfo.getBusinessFullName());

                ps.setString( 10 , businessInfo.getRegistAddress());
                ps.setString( 11 , businessInfo.getBusinessScope());
                ps.setString( 12 , businessInfo.getBusinessLicenseStartTime());
                ps.setString( 13 , businessInfo.getBusinessLicenseEndTime());
                ps.setString( 14 , businessInfo.getCertificateHolderType());
                ps.setString( 15 , businessInfo.getCertificateHolderName());
                ps.setString( 16 , businessInfo.getCertificateType());
                ps.setString( 17 , businessInfo.getCertificateStartTime());
                ps.setString( 18 , businessInfo.getCertificateEndTime());
                ps.setString( 19 , businessInfo.getCertificateNo());


                ps.setString( 20 , businessInfo.getAccountType());
                ps.setString( 21 , businessInfo.getAccountName());
                ps.setString( 22 , businessInfo.getAccountBank());
                ps.setString( 23 , businessInfo.getAccountBankCity());
                ps.setString( 24 , businessInfo.getAccountBranchBank());
                ps.setString( 25 , businessInfo.getAccountNo());
                ps.setString( 26 , businessInfo.getBusinessRate());
                ps.setString( 27 , businessInfo.getContractNo());
                ps.setString( 28 , businessInfo.getContractStartTime());
                ps.setString( 29 , businessInfo.getContractEndTime());
                ps.setString( 30 , businessInfo.getPhone());

                ps.setString( 31 , businessInfo.getBusinessLicense() );
                ps.setString( 32 , businessInfo.getIdCardFrontPage());
                ps.setString( 33 , businessInfo.getIdCardBackPage());
                ps.setString( 34 , businessInfo.getContractPhoto());
                ps.setString( 35 , businessInfo.getEvetimeContractImage());
                ps.setString( 36 , businessInfo.getRegistCode());

                ps.setString( 37 , businessInfo.getId());

            }
        });
    }

    @Override
    public BusinessInfo queryById(String id) {

        String sql = " SELECT * from ms_cms_businessinformation WHERE Id = ? AND DelFlag = 0 " ;
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<BusinessInfo>() {
            @Override
            public BusinessInfo mapRow(ResultSet rs, int i) throws SQLException {
                BusinessInfo businessInfo = new BusinessInfo();
                businessInfo.setId(rs.getString("Id"));
                businessInfo.setName(rs.getString("Name"));
                businessInfo.setEmail(rs.getString("Email"));
                businessInfo.setBusinessShortName(rs.getString("BusinessShortName"));
                businessInfo.setCategory(rs.getString("Category"));
                businessInfo.setBigCategory(rs.getString("BigCategory"));
                businessInfo.setSmallCategory(rs.getString("SmallCategory"));
                businessInfo.setServiceTel(rs.getString("ServiceTel"));
                businessInfo.setWebsite(rs.getString("Website"));
                businessInfo.setBusinessFullName(rs.getString("BusinessFullName"));

                businessInfo.setRegistAddress(rs.getString("RegistAddress"));
                businessInfo.setBusinessScope(rs.getString("BusinessScope"));
                businessInfo.setBusinessLicenseStartTime(rs.getString("BusinessLicenseStartTime"));
                businessInfo.setBusinessLicenseEndTime(rs.getString("BusinessLicenseEndTime"));
                businessInfo.setCertificateHolderType(rs.getString("CertificateHolderType"));
                businessInfo.setCertificateHolderName(rs.getString("CertificateHolderName"));
                businessInfo.setCertificateType(rs.getString("CertificateType"));
                businessInfo.setCertificateStartTime(rs.getString("CertificateStartTime"));
                businessInfo.setCertificateEndTime(rs.getString("CertificateEndTime"));
                businessInfo.setCertificateNo(rs.getString("CertificateNo"));

                businessInfo.setAccountType(rs.getString("AccountType"));
                businessInfo.setAccountName(rs.getString("AccountName"));
                businessInfo.setAccountBank(rs.getString("AccountBank"));
                businessInfo.setAccountBankCity(rs.getString("AccountBankCity"));
                businessInfo.setAccountBranchBank(rs.getString("AccountBranchBank"));
                businessInfo.setAccountNo(rs.getString("AccountNo"));
                businessInfo.setBusinessRate(rs.getString("BusinessRate"));
                businessInfo.setContractNo(rs.getString("ContractNo"));
                businessInfo.setContractStartTime(rs.getString("ContractStartTime"));
                businessInfo.setContractEndTime(rs.getString("ContractEndTime"));

                businessInfo.setPhone(rs.getString("Phone"));

                businessInfo.setBusinessLicense(rs.getString("BusinessLicense")) ;
                businessInfo.setIdCardFrontPage(rs.getString("IdCardFrontPage"));
                businessInfo.setIdCardBackPage(rs.getString("IdCardBackPage"));
                businessInfo.setContractPhoto(rs.getString("ContractPhoto"));
                businessInfo.setEvetimeContractImage(rs.getString("EvetimeContractImage"));

                businessInfo.setRegistCode(rs.getString("RegistCode"));
                businessInfo.setContractStatus(rs.getString("ContractStatus"));

                return businessInfo;
            }
        });
    }

    @Override
    public int delete(String id) {
        String sql  = " UPDATE  ms_cms_businessinformation SET DelFlag = 1  WHERE Id = ?  " ;
        return jdbcTemplate.update(sql , new Object[]{id});
    }

    @Override
    public int updateContractStatus(String id, int status) {

        String sql = " UPDATE  ms_cms_businessinformation SET ContractStatus = ?  WHERE Id = ? " ;
        return jdbcTemplate.update(sql , new Object[]{status , id});
    }
}
