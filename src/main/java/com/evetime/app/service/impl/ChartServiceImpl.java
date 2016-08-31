package com.evetime.app.service.impl;

import com.evetime.app.dao.*;
import com.evetime.app.dto.BrandSaleInfo;
import com.evetime.app.dto.ResultData;
import com.evetime.app.entity.Branch;
import com.evetime.app.entity.Brand;
import com.evetime.app.entity.User;
import com.evetime.app.service.ChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Rains
 * on 2016-07-04.
 */
@Service("AppChartService")
public class ChartServiceImpl implements ChartService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "AppBranchDao")
    private BranchDao branchDao;

    @Resource(name = "AppBrandDao")
    private BrandDao brandDao;

    @Resource(name = "AppUserDao")
    private UserDao userDao;
    
    @Resource(name = "AppBranchSaleDataDao")
    private BranchSaleDataDao branchSaleDataDao;

    @Resource(name = "AppBrandSaleDataDao")
    private BrandSaleDateDao brandSaleDataDao;

    /**
     * 加载首页品牌数据
     * @param userName
     * @param password
     * @return
     */
    @Override
    public ResultData loadBrandInfo(String userName , String password) {

        ResultData result = new ResultData() ;

        if(userName == null || "".equals(userName) || password == null || "".equals(password)){
            return new ResultData(false , "参数传递错误");
        }

        User user = null;
        try {
            user = userDao.query(userName, password);
        } catch (Exception e) {
            result = new ResultData(false , "用户名或者密码错误");
            return result ;
        }

        //根据userId查询品牌信息
        Brand brand = brandDao.queryByUserId(user.getId());
        double todaySale = 0;
        try {
            todaySale = brandSaleDataDao.queryCurDaySale(brand.getSystemId());
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
        }
        brand.setTodaySale(todaySale);

        List<Branch> branchList = branchDao.queryByUserId(user.getId());

        List<Map<String, Object>> weekSaleList = brandSaleDataDao.querySaleOfWeek(user.getId());

        List<Map<String, Object>> list = getWeekSaleList(weekSaleList);

        BrandSaleInfo brandSaleInfo = new BrandSaleInfo(brand , branchList , list);

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(brandSaleInfo);
        return result ;

    }

    @Override
    public ResultData loadBranchInfo(String cloudId) {

        ResultData result = new ResultData();

        if(cloudId == null || "".equals(cloudId)){
            return new ResultData(false , "参数错误");
        }
        List<Map<String ,Object>> weekSaleList = null;
        Map<String ,Object> branchSaleData = null ;

            //获取客单数
        int todayOrderCount = 0;
        try {
            todayOrderCount = branchSaleDataDao.queryTodayOrderCount(cloudId);
        } catch (Exception e) {
            logger.error(e.getMessage() ,e);
        }

        int yesterdayOrderCount = 0;
        try {
            yesterdayOrderCount = branchSaleDataDao.queryYesterdayOrderCount(cloudId);
        } catch (Exception e) {
            logger.error(e.getMessage() ,e);
        }

        //获取菜品销量
        int todaySellCount = 0;
        try {
            todaySellCount = branchSaleDataDao.queryTodayFoodSellCount(cloudId);
        } catch (Exception e) {
            logger.error(e.getMessage() ,e);
        }
        int yesterdaySellCount = 0;
        try {
            yesterdaySellCount = branchSaleDataDao.queryYesterDayFoodSellCount(cloudId);
        } catch (Exception e) {
            logger.error(e.getMessage() ,e);
        }

        //获取人均消费
        double todayAverageCost = 0;
        try {
            todayAverageCost = branchSaleDataDao.queryTodayAverageCost(cloudId);
        } catch (Exception e) {
            logger.error(e.getMessage() ,e);
        }
        double yesterdayAverageCost = 0;
        try {
            yesterdayAverageCost = branchSaleDataDao.queryYesterDayAverageCost(cloudId);
        } catch (Exception e) {
            logger.error(e.getMessage() ,e);
        }

        //获取翻台率
        double todayTableTurnoverRate = 0;
        try {
            todayTableTurnoverRate = branchSaleDataDao.queryTodayTableTurnOverRate(cloudId);
        } catch (Exception e) {
            logger.error(e.getMessage() ,e);
        }
        double yesterdayTableTurnoverRate = 0;
        try {
            yesterdayTableTurnoverRate = branchSaleDataDao.queryYesterDayTableTurnOverRate(cloudId);
        } catch (Exception e) {
            logger.error(e.getMessage() ,e);
        }

        //获取分店七日营收
            weekSaleList = getWeekSaleList(branchSaleDataDao.queryWeekSale(cloudId));

            branchSaleData = new HashMap<String, Object>();
            branchSaleData.put("todayOrderCount" ,todayOrderCount);
            branchSaleData.put("yesterdayOrderCount" , yesterdayOrderCount);
            branchSaleData.put("todaySellCount" , todaySellCount);
            branchSaleData.put("yesterdaySellCount" , yesterdaySellCount);
            branchSaleData.put("todayAverageCost" , todayAverageCost);
            branchSaleData.put("yesterdayAverageCost" , yesterdayAverageCost);
            branchSaleData.put("todayTableTurnoverRate" , todayTableTurnoverRate);
            branchSaleData.put("yesterdayTableTurnoverRate" , yesterdayTableTurnoverRate);
            branchSaleData.put("weekSaleList" , weekSaleList);

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(branchSaleData);
        return result;
    }

    @Override
    public ResultData loadFoodSellInfo(String cloudId, int range) {

        ResultData result = new ResultData();
        if(cloudId == null || "".equals(cloudId) ){
            return new ResultData(false,"参数错误");
        }

        List<Map<String, Object>> foodSellInfo = null;
        try {
            foodSellInfo = branchSaleDataDao.queryFoodSellInfo(cloudId, range);
        } catch (Exception e) {
            logger.error("range为0,1,2,3",e);
            return new ResultData(false , "range参数错误或者服务器异常");
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(foodSellInfo);

        return result;
    }

    @Override
    public ResultData loadBrandSaleDetail(String userId, int index) {

        ResultData result= new ResultData();

        if(userId == null || "".equals(userId)){
            return  new ResultData(false , "品牌Id不能为空" );
        }

        List<Map<String, Object>> brandSaleDetail = null ;
        try {
            switch (index){
                case 0 :
                    brandSaleDetail = getCurWeekSaleList(brandSaleDataDao.queryCurWeekSale(userId)) ;
                    break;
                case 1 :
                    brandSaleDetail = getMonthSaleList(brandSaleDataDao.queryCurMonthSale(userId) , index-2 ) ;
                    break;
                case 2 :
                    brandSaleDetail = getCurYearSaleList(brandSaleDataDao.queryCurYearSale(userId)) ;
                    break;
                default  :
                    brandSaleDetail = getMonthSaleList(brandSaleDataDao.queryMonthSale(userId, index-2) , index-2) ;
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
            return new ResultData(false , "查询失败,请稍后再试");
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(brandSaleDetail);
        return result;
    }

    @Override
    public ResultData loadBranchSaleDetail(String cloudId, int index) {

        ResultData result = new ResultData();
        if(cloudId == null || "".equals(cloudId)){
            return new ResultData(false , "分店Id不能为空");
        }
        List<Map<String, Object>> branchSaleDetail = null ;
        try {
            switch (index){
                case 0 :
                    branchSaleDetail = getCurWeekSaleList(branchSaleDataDao.queryCurWeekSale(cloudId)) ;
                    break;
                case 1 :
                    branchSaleDetail = getMonthSaleList(branchSaleDataDao.queryCurMonthSale(cloudId) , index-2 ) ;
                    break;
                case 2 :
                    branchSaleDetail = getCurYearSaleList(branchSaleDataDao.queryCurYearSale(cloudId)) ;
                    break;
                default  :
                    branchSaleDetail = getMonthSaleList(branchSaleDataDao.queryMonthSale(cloudId, index-2) , index-2) ;
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
            return new ResultData(false , "查询失败,请稍后再试");
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(branchSaleDetail);
        return result;
    }

    @Override
    public ResultData loadBranchList(String id, int index) {

        ResultData result = new ResultData();
        if(id == null || "".equals(id)){
            return new ResultData(false , "id不能为空");
        }

        List<Branch> branchList = null ;
        try {
            switch (index){
                case 0 :
                    branchList =  brandSaleDataDao.queryCurWeekBranchList(id);
                    break;
                case 1 :
                    branchList =  brandSaleDataDao.queryCurMonthBranchList(id);
                    break;
                case 2 :
                    branchList = brandSaleDataDao.queryCurYearBranchList(id) ;
                    break;
                default  :
                    branchList = brandSaleDataDao.queryMonthBranchList(id , index-2) ;
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
            return new ResultData(false , "查询失败");
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(branchList);


        return result;
    }

    /**
     *
     *  组装当前周数据
     *  由于从数据库中查询出的数据有可能是不连续的,比如某一天没有数据则会少一条记录
     *
     *  组装数据,若某一天没有数据则显示0.
     *
     *  保证始终有一周数据.
     * @param brandSaleDetail
     * @return
     */
    private List<Map<String, Object>> getCurWeekSaleList(List<Map<String, Object>> brandSaleDetail){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        //获取当天是本周的第几天
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        //如果是第一天(周日),后退一天.以周六所在的周为基准
        if(1 == dayOfWeek){
            calendar.add(Calendar.DAY_OF_MONTH , -1 );
        }

        //第一天为周日,按照国内标准,周1为一周的第一天.
        dayOfWeek  = (dayOfWeek == 1 ? 7 : (dayOfWeek-1) );
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        //设置日历为当前周周1(当天时间与第几天的差值)
        calendar.add(Calendar.DATE , calendar.getFirstDayOfWeek() - day);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int i  = 0 ;


        while( i< dayOfWeek){
            String time = sdf.format(calendar.getTime());
            Map<String ,Object> map = new HashMap<String ,Object>();
            map.put("date", time);
            map.put("sale" , 0);
            for(int j = 0 ; j < brandSaleDetail.size() ; j++){
                Boolean flag = time.equals(brandSaleDetail.get(j).get("date"));
                if (flag){
                    map.put("sale" , brandSaleDetail.get(j).get("sale"));
                }
            }

            calendar.add(Calendar.DATE , +1);
            i++;
            list.add(map);
        }


        return list ;
    }

    /**
     * 组装近期一周数据
     * @param weekSaleList
     * @return
     */
    private List<Map<String, Object>> getWeekSaleList(List<Map<String, Object>> weekSaleList) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH , 1);


        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0 ; i <7 ; i++){
            calendar.add(Calendar.DAY_OF_MONTH , -1);
            String time =  sdf.format(calendar.getTime());
            Map<String ,Object> map = new HashMap<String ,Object>();
            map.put("date", time);
            map.put("sale" , 0);
            for (int j = 0 ; j < weekSaleList.size() ; j++){
                Boolean flag = time.equals(weekSaleList.get(j).get("date"));
                if (flag){
                    map.put("sale" , weekSaleList.get(j).get("sale"));
                }
            }
            list.add(0 , map);
        }
        return list;
    }

    private List<Map<String , Object >> getMonthSaleList(List<Map<String , Object>> monthSaleList , int month){

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        //month == -1 表示当前月
        //获取该月份的天数
        int days ;
        if(month == -1 || month == calendar.get(Calendar.MONTH) + 1 ){
            calendar.set(Calendar.MONTH , calendar.get(Calendar.MONTH));
            days = calendar.get(Calendar.DAY_OF_MONTH) ;
        }else {
            calendar.set(Calendar.MONTH , month-1 );
            days =  calendar.getActualMaximum(Calendar.DATE);
        }


        //如何该月份为当前月则只显示1号到当天的数据
        //设置日历到当前月份的第一天
        calendar.set(Calendar.DAY_OF_MONTH , 1);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(int i = 0 ; i < days ; i++){
            String time = sdf.format(calendar.getTime());
            Map<String ,Object> map = new HashMap<String ,Object>();
            map.put("date", time);
            map.put("sale" , 0);
            for(int j = 0 ; j < monthSaleList.size() ; j++){
                Boolean hasSale = time.equals(monthSaleList.get(j).get("date"));
                if(hasSale){
                    map.put("sale" , monthSaleList.get(j).get("sale"));
                }
            }
            list.add(map);
            calendar.add(Calendar.DATE , 1);
        }

        return list ;
    }

    private List<Map<String , Object>> getCurYearSaleList(List<Map<String , Object>> yearSaleList){

        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        //获取当天是几月
        int month = calendar.get(Calendar.MONTH);

        //将日历设置为1月
        calendar.set(Calendar.MONTH , 0);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(int i = 0 ; i <= month ; i++ ){
            String time = sdf.format(calendar.getTime());
            Map<String ,Object> map = new HashMap<String ,Object>();
            map.put("date", time);
            map.put("sale" , 0);
            for(int j = 0 ; j < yearSaleList.size() ; j++ ){
                boolean hasSale = time.equals(yearSaleList.get(j).get("date"));
                if(hasSale){
                    map.put("sale" , yearSaleList.get(j).get("sale"));
                }
            }
            list.add(map);

            calendar.add(Calendar.MONTH , 1);

        }

        return list;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        //将日历设置到指定月份
        calendar.set(Calendar.MONTH , 1);
        //日历设置到该月的第一天
        calendar.set(Calendar.DATE , 1);
        //获取该月的总天数
        int days = calendar.getActualMaximum(Calendar.DATE);

        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(days);

        for(int i = 0 ; i < days ; i++){
            String time = sdf.format(calendar.getTime());
            Map<String ,Object> map = new HashMap<String ,Object>();
            map.put("date", time);
            map.put("sale" , 0);

            System.out.println(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH , 1);

        }
    }

}
