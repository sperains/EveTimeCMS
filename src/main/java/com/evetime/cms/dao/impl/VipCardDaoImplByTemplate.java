package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.VipCardDao;
import com.evetime.cms.entity.VipCard;
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
@Repository("VipCardDao")
public class VipCardDaoImplByTemplate implements VipCardDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(final VipCard vipCard) {

        String sql  = " INSERT INTO ms_pc_base_vipcardinfo " +
                            " (Id , CloudId , VipCardCode , VipCardName , BaseScore , LevelScore , DefaultDiscount , DelFlag ) " +
                            " VALUES( ? , ? , ? , ? , ? , ? , ? , ?  )";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1 , vipCard.getId());
                ps.setString(2 , vipCard.getCloudId());
                ps.setString(3 , vipCard.getVipCardCode());
                ps.setString(4 , vipCard.getVipCardName());
                ps.setInt(5 , vipCard.getBaseScore() );
                ps.setInt(6 , vipCard.getLevelScore());
                ps.setDouble(7 , vipCard.getDefaultDiscount());
                ps.setInt(8 , vipCard.getDelFlag());
            }
        });
    }

    @Override
    public int update(VipCard vipCard) {

        String sql  = " UPDATE ms_pc_base_vipcardinfo SET " +
                            " " ;

        return 0;
    }

    @Override
    public int delete(String vipCardId) {
        return 0;
    }

    @Override
    public List<VipCard> queryAll(String cloudId) {

        String sql  =  " SELECT * FROM ms_pc_base_vipcardinfo " +
                            " WHERE CloudId = ? " ;
        return jdbcTemplate.query(sql, new Object[]{cloudId}, new RowMapper<VipCard>() {
            @Override
            public VipCard mapRow(ResultSet rs, int i) throws SQLException {
                VipCard vipCard = new VipCard();
                vipCard.setId(rs.getString("Id"));
                vipCard.setCloudId(rs.getString("CloudId"));
                vipCard.setVipCardCode(rs.getString("VipCardCode"));
                vipCard.setVipCardName(rs.getString("VipCardName"));
                vipCard.setBaseScore(rs.getInt("BaseScore"));
                vipCard.setLevelScore(rs.getInt("LevelScore"));
                vipCard.setDefaultDiscount(rs.getDouble("DefaultDiscount"));
                vipCard.setIsSynchronization(rs.getInt("IsSynchronization"));
                vipCard.setDelFlag(rs.getInt("DelFlag"));
                return vipCard;
            }
        });
    }
}
