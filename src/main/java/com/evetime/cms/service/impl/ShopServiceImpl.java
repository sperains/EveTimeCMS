package com.evetime.cms.service.impl;

import com.evetime.cms.dao.CompanyUserRoleDao;
import com.evetime.cms.dao.ShopDao;
import com.evetime.cms.dao.impl.UserDao;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.Shop;
import com.evetime.cms.entity.ShopType;
import com.evetime.cms.entity.User;
import com.evetime.cms.service.ShopService;
import com.evetime.cms.util.UUIDGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Rains
 * on 2016-06-07.
 */
@Service("ShopService")
public class ShopServiceImpl implements ShopService {

    @Resource(name = "ShopDao")
    private ShopDao shopDao;

    public ShopDao getShopDao() {
        return shopDao;
    }

    public void setShopDao(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Resource(name = "CompanyUserRoleDao")
    private CompanyUserRoleDao companyUserRoleDao;

    public CompanyUserRoleDao getCompanyUserRoleDao() {
        return companyUserRoleDao;
    }

    public void setCompanyUserRoleDao(CompanyUserRoleDao companyUserRoleDao) {
        this.companyUserRoleDao = companyUserRoleDao;
    }

    @Resource(name="UserDao")
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public ResultData findAll() {

        ResultData result = new ResultData() ;

        List<Shop> shops = shopDao.findAll();

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(shops);

        return result;
    }

    @Override
    public ResultData findShops(String userId) {

        ResultData result = new ResultData();

        List<User> userList = userDao.findById(userId);

        User user = userList.get(0);

        int groupId = user.getGroupId();
        List<Shop> shops = null ;

        if(groupId == 1 ){
             shops = shopDao.findAll();
        }else{
            shops = shopDao.findShops(userId);
        }




        result.setSuccess(true);
        result.setMessage("加载门店列表成功");
        result.setData(shops);

        return result;
    }

    @Override
    public ResultData findTypes() {
        ResultData result = new ResultData();

        List<ShopType> typeList = shopDao.findTypes();

        result.setSuccess(true);
        result.setMessage("加载餐厅类型成功");
        result.setData(typeList);
        return result ;
    }

    @Override
    public ResultData updateShop(Shop shop) {

        ResultData result;
        int n =  shopDao.updateShop(shop);

        if(n == 0) {
            result = new ResultData(false,"更新失败");
            return result;
        }

        System.out.println(shop.getBrandId());
        System.out.println(shop.getCloudId());
        companyUserRoleDao.update(shop.getBrandId() , shop.getCloudId());

        result = new ResultData(true , n);
        result.setMessage("修改成功");
        return result;
    }

    @Override
    public ResultData insertShop(Shop shop) {

        ResultData result = new ResultData();

        shop.setId(UUIDGenerator.getUUID());
        shop.setCloudId(UUIDGenerator.getUUID());
        int n = shopDao.insertShop(shop);

        if(n == 0 ){
            result.setSuccess(false);
            result.setMessage("添加失败");
            return result;
        }



        result.setSuccess(true);
        result.setMessage("添加成功");
        result.setData(n);
        return result;
    }

    @Override
    public ResultData recycle(String id) {

        int n = shopDao.recycle(id);

        ResultData result = new ResultData();
        result.setSuccess(true);
        result.setMessage("删除成功");
        return result;
    }

    @Override
    public ResultData findUnbindShops() {

        ResultData result = new ResultData();

        List<Shop> unbindShops = shopDao.findUnbindShops();

        if(unbindShops.size() == 0){
            result.setSuccess(false);
            result.setMessage("没有未绑定的门店");
            return result ;
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(unbindShops);
        return result;
    }

    @Override
    public ResultData shopBind(String cloudId, String companyId, String brandId) {

        ResultData result = new ResultData();


        int n = shopDao.updateCompanyIdAndBrandId(cloudId , companyId , brandId);

        result.setSuccess(true);
        result.setMessage("更新成功");

        return result;
    }

    @Override
    public ResultData loadFoodsInfo(String cloudId) {
        return null;
    }
}
