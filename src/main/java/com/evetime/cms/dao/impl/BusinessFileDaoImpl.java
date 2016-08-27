package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.BusinessFileDao;
import com.evetime.cms.entity.BusinessFile;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rains
 * on 2016-07-25.
 */
@Repository("BusinessFileDao")
public class BusinessFileDaoImpl implements BusinessFileDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(final BusinessFile businessFile) {

        String sql = " INSERT INTO ms_cms_businessfile ( Id , BusinessInfoId , FileName , FieldName ,  Content , FileSize  )VALUES ( ? , ? , ? , ? , ? ) " ;
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString( 1 , businessFile.getId());
                ps.setString( 2 , businessFile.getBusinessInfoId());
                ps.setString( 3,  businessFile.getFileName());
                ps.setString( 4 , businessFile.getFieldName());
                ps.setBinaryStream( 5 , businessFile.getContent());
                ps.setLong( 6 , businessFile.getFileSize());
            }
        });
    }

    @Override
    public int insert(final List<BusinessFile> businessFileList) {

        String sql = " INSERT INTO ms_cms_businessfile ( Id , BusinessInfoId , FileName , FieldName , Content , FileSize  )VALUES ( ? , ? , ? , ? , ? , ?  ) " ;

        int[] n = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                BusinessFile businessFile = businessFileList.get(i);
                ps.setString( 1 , businessFile.getId());
                ps.setString( 2 , businessFile.getBusinessInfoId());
                ps.setString( 3,  businessFile.getFileName());
                ps.setString( 4 , businessFile.getFieldName());
                ps.setBinaryStream( 5 , businessFile.getContent());
                ps.setLong( 6 , businessFile.getFileSize());
            }

            @Override
            public int getBatchSize() {
                return businessFileList.size();
            }
        });

        int count = 0 ;
        for(int i : n){
            count += i ;
        }
        return count ;
    }

    @Override
    public BusinessFile queryByBusinessInfoIdAndFileName(String businessInfoId , String fileName) {

        String sql = "SELECT * from ms_cms_businessfile WHERE BusinessInfoId = ? AND fileName = ? " ;
        return null;
    }

    @Override
    public BusinessFile queryById(String id) {

        String sql = "SELECT * from ms_cms_businessfile WHERE Id = ? " ;
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<BusinessFile>() {
            @Override
            public BusinessFile mapRow(ResultSet rs, int i) throws SQLException {

                BusinessFile businessFile = new BusinessFile();
                businessFile.setId(rs.getString("Id"));
                businessFile.setBusinessInfoId(rs.getString("BusinessInfoId"));
                businessFile.setFileName(rs.getString("FileName"));
                businessFile.setContent(rs.getBinaryStream("Content"));
                businessFile.setFileSize(rs.getLong("FileSize"));
                return businessFile;
            }
        }) ;
    }

    @Override
    public int update(final List<BusinessFile> businessFileList) {

        String sql = " UPDATE ms_cms_businessfile SET " +
                " FileName = ? , Content = ? , FileSize = ? " +
                " WHERE BusinessInfoId = ? AND FieldName = ? " ;
        int[] n = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                BusinessFile businessFile = businessFileList.get(i);
                ps.setString(1 , businessFile.getFileName());
                ps.setBinaryStream( 2 , businessFile.getContent());
                ps.setLong( 3 , businessFile.getFileSize());
                ps.setString( 4 , businessFile.getBusinessInfoId());
                ps.setString( 5 , businessFile.getFieldName());
            }

            @Override
            public int getBatchSize() {
                return businessFileList.size();
            }
        });

        int count = 0 ;
        for(int i : n){
            count += i ;
        }
        return count ;
    }

    @Override
    public List<BusinessFile> queryAppendFile(String businessInfoId) {

        String sql =   "SELECT * FROM ms_cms_businessfile  " +
                            "WHERE  BusinessInfoId = ?  AND FieldName LIKE '%File' " ;
        return jdbcTemplate.query(sql, new Object[]{businessInfoId}, new RowMapper<BusinessFile>() {
            @Override
            public BusinessFile mapRow(ResultSet rs, int i) throws SQLException {
                BusinessFile file = new BusinessFile();
                file.setId(rs.getString("Id"));
                file.setBusinessInfoId(rs.getString("BusinessInfoId"));
                file.setFieldName(rs.getString("FieldName"));
                file.setFileName(rs.getString("FileName"));
                file.setFileSize(rs.getLong("FileSize"));
                return file;
            }
        });
    }


}
