package com.evetime.cms.controller;

import com.evetime.cms.dao.ShopDao;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.Shop;
import com.evetime.cms.service.ShopService;
import com.evetime.cms.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Rains
 * on 2016-06-07.
 */
@Controller("ShopController")
@RequestMapping("/shop")
public class ShopController {

    @Resource(name = "ShopService")
    private ShopService shopService;

    public ShopService getShopService() {
        return shopService;
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping("loadShops")
    @ResponseBody
    public ResultData loadShops(String userId){

        ResultData result = shopService.findShops(userId);

        return result;
    }

    @RequestMapping("addShop")
    @ResponseBody
    public ResultData add(String key , String userId){

        ResultData result ;

        System.out.println(key);

        Shop shop = JsonUtil.JsonToObject(key, Shop.class);
        if(shop == null){
            result = new ResultData(false, "参数错误");
            return result;
        }

        result = shopService.insertShop(shop);




        return result;
    }

    @RequestMapping(value = "loadTypes" , method = RequestMethod.GET)
    @ResponseBody
    public ResultData loadTypes(){
        ResultData result = shopService.findTypes();
        return result;
    }

    @RequestMapping(value = "edit" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData update(String key){

        ResultData result ;
        Shop shop = JsonUtil.JsonToObject(key, Shop.class);

        if(shop == null){
            result = new ResultData(false,"参数错误");
            return result;
        }

        result = shopService.updateShop(shop);
        return result;
    }

    @RequestMapping(value = "recycle" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData recycle(String id){
        ResultData result = shopService.recycle(id);
        return result;
    }

    @RequestMapping(value = "loadUnbindShops" )
    @ResponseBody
    public ResultData loadUnbindShops(){

        ResultData result = shopService.findUnbindShops();

        return result ;
    }

    @RequestMapping(value = "loadAll")
    @ResponseBody
    public ResultData loadAll(){

        ResultData result = shopService.findAll();
        return result ;
    }


    @RequestMapping(value = "shopBind")
    @ResponseBody
    public ResultData shopBind(String cloudId , String companyId , String brandId){

        ResultData result = shopService.shopBind(cloudId , companyId , brandId);

        return result;
    }

    @RequestMapping(value="loadFoodsInfo")
    @ResponseBody
    public ResultData loadFoodsInfo(String cloudId){

        ResultData result = shopService.loadFoodsInfo(cloudId);
        return result;
    }
}
