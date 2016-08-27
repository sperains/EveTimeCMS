package com.evetime.cms.dao;

import com.evetime.cms.entity.Shop;
import com.evetime.cms.entity.ShopType;

import java.util.List;

/**
 * Created by Rains
 * on 2016-04-21.
 */
public interface ShopDao {

    List<Shop> findAll();

    List<Shop> findShops(String userId);

    List<ShopType>  findTypes();

    int updateShop(Shop shop);

    int insertShop(Shop shop);

    int recycle(String id);

    List<Shop> findUnbindShops();

    List<Shop> findSettingShops(String userId);

    int updateCompanyId(String userId);

    int updateCompanyIdAndBrandId(String cloudId , String companyId , String brandId);

    List<Shop> findbyCompanyId(String companyId);

}
