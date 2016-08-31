package com.evetime.app.controller;

import com.evetime.app.dto.ResultData;
import com.evetime.app.service.ChartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Rains
 * on 2016-07-05.
 */
@Controller("AppChartController")
@RequestMapping("/chart")
public class ChartController {

    @Resource
    private ChartService chartService;

    /**
     * app登录入口,并返回品牌相关数据
     * @param userName
     * @param password
     *
     *返回数据格式如下
     * {
    "   success": true,
    "   message": "加载成功",
    "   data": {
    "       brand": {
    "       systemId": "c8408ea9-b72f-412b-8e11-b2c1a11da79a",
    "       name": "黄记煌",
    "       todaySale": 0,
    "       imgUrl": "http://bj-evetime.oss-cn-shenzhen.aliyuncs.com/shopimage/黄记煌logo160.png"
            },
    "       branchList": [
                {
                "systemId": "2fc1efb7-ce1e-4b9a-bc99-13ae94ec6e74",
                "name": "光谷天地店",
                "sale": 0
                }
            ],
            "weekSaleList": [
                {
                "date": "08-23",
                "sale": 0
                },
                {
                "date": "08-24",
                "sale": 0
                },
                {
                "date": "08-25",
                "sale": 0
                },
                {
                "date": "08-26",
                "sale": 0
                },
                {
                "date": "08-27",
                "sale": 0
                },
                {
                "date": "08-28",
                "sale": 0
                },
                {
                "date": "08-29",
                "sale": 0
                }
             ]
         }
     * }
     * @return
     */
    @RequestMapping("/loadBrandData")
    @ResponseBody
    public ResultData loadBrandData(String userName , String password){

        ResultData result = chartService.loadBrandInfo(userName , password);
        return result ;
    }


    /**
     * 加载指定分店信息
     *当日/昨日 订单数量,菜品销售数量,平均消费,翻台率
     * @param cloudId
     * @return
     *
     * {
    "success": true,
    "message": "加载成功",
    "data": {
        "yesterdayAverageCost": 0,
        "todayAverageCost": 0,
        "todayTableTurnoverRate": 0,
        "todaySellCount": 0,
        "yesterdaySellCount": 0,
        "weekSaleList": [
            {
            "date": "08-23",
            "sale": 0
            },
            {
            "date": "08-24",
            "sale": 0
            },
            {
            "date": "08-25",
            "sale": 0
            },
            {
            "date": "08-26",
            "sale": 0
            },
            {
            "date": "08-27",
            "sale": 0
            },
            {
            "date": "08-28",
            "sale": 0
            },
            {
            "date": "08-29",
            "sale": 0
            }
        ],
        "yesterdayOrderCount": 0,
        "yesterdayTableTurnoverRate": 0,
        "todayOrderCount": 0
        }
    }
     */
    @RequestMapping("/loadBranchData")
    @ResponseBody
    public ResultData loadBranchData(String cloudId){

        ResultData result = chartService.loadBranchInfo(cloudId);
        return result ;
    }


    /**
     * 加载指定分店 菜品销售详情
     * @param cloudId
     * @param range
     *              0    当天
     *              1    本周
     *              2    本月
     *              3     本年度
     * @return
     *
     * {
    "success": true,
    "message": "加载成功",
    "data": [
        {
        "name": "人参乌龙",
        "count": 3
        },
        {
        "name": "咖喱牛肉",
        "count": 2
        },
        {
        "name": "普洱/壶",
        "count": 1
        }
    ]
     */
    @RequestMapping("/loadFoodSellInfo")
    @ResponseBody
    public ResultData loadFoodSellInfo(String cloudId , int range){

        ResultData result = chartService.loadFoodSellInfo(cloudId , range);
        return result ;
    }


    /**
     *  加载品牌销售详情
     * @param id
     * @param index
     *                  0       本周
     *                  1       本月
     *                  2       本年度
     *                  3       1月
     *                  4       2月
     *                  --      12月
     * @return
     */
    @RequestMapping("/loadBrandSaleDetail")
    @ResponseBody
    public ResultData loadBrandSaleDetail(String id , int index){

        ResultData result = chartService.loadBrandSaleDetail(id , index);
        return result ;
    }


    /**
     *  加载指定分店的销售总额
     *
     * @param cloudId
     * @param index
     *                  0       本周
     *                  1       本月
     *                  2       本年度
     *                  3       1月
     *                  4       2月
     *                  --      12月
     * @return
     */
    @RequestMapping("loadBranchSaleDetail")
    @ResponseBody
    public ResultData loadBranchSaleDetail(String cloudId , int index){

        ResultData result = chartService.loadBranchSaleDetail(cloudId , index);
        return result ;
    }


    /**
     * 加载分店详情
     *      cloudId
     *      name
     *      sale            营业额
     * @param id
     * @param index
     *                  0       本周
     *                  1       本月
     *                  2       本年度
     *                  3       1月
     *                  4       2月
     *                  --      12月
     * @return
     */
    @RequestMapping("loadBranchList")
    @ResponseBody
    public ResultData loadBranchList(String id , int index ){

        ResultData result = chartService.loadBranchList(id , index);
        return result ;
    }

}
