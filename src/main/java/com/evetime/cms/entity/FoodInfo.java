package com.evetime.cms.entity;

import java.util.List;

/**
 * Created by Rains
 * on 2016-08-16.
 */
public class FoodInfo {


    public class FoodInfoModel {

        private List<FoodChoice> foodChoice;//子菜品信息

        private String foodDescrip;//菜品描述

        private String foodImage;//菜品图片

        private String bigImgPath; // 菜品大图片

        private String midImgPath; // 菜品中图片

        private String foodName;//菜品名称

        private double foodPrice;//菜品价格

        private double presentPrice;//菜品现价

        private int foodNum;//选择数量 0

        private String id;//菜品id

        private String foodtype;//菜品类别

        private String foodtypeName; //菜品类别名称

        private String foodGroupId; // 菜品分组

        private int foodGroupStatus; // 0 无子菜品   1主菜品   2子菜品

        private String foodCode; // 菜品编号

        private String foodShort;// 菜品简码

        private int estimate; // 估清

        private String tasteDesc;//套餐信息

        private SuitInfo suitInfo; // 套餐信息

        private int isSuit; // 是否套餐

        private int autoEstimateClear; //是否启用自动估清

        private int remainingFoodNum; //剩余份数

        private int warnEstimateClearNum; // 估清预警数量

        private int monthlySales; //月销售

        private int praise; // 点赞


    }


}
