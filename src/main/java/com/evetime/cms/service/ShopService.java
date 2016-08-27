package com.evetime.cms.service;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.Shop;

/**
 * Created by Rains
 * on 2016-06-07.
 */
public interface ShopService {

    ResultData findAll();

    ResultData findShops(String companyId);

    ResultData findTypes();

    ResultData updateShop(Shop shop);

    ResultData insertShop(Shop shop);

    ResultData recycle(String id);

    ResultData findUnbindShops();

    ResultData shopBind(String cloudId , String companyId , String brandId);


    /**
     * 加载门店的所有菜品信息
     * @param cloudId
     * @return
     */
    ResultData loadFoodsInfo(String cloudId);
}
