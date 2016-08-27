package com.evetime.cms.controller;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.Vip;
import com.evetime.cms.service.VipService;
import com.evetime.cms.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Rains
 * on 2016-06-22.
 */
@Controller
@RequestMapping("/vip")
public class VipController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "VipService")
    private VipService vipService;

    @RequestMapping(value = "loadAll" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadByUserId(String userId){

        ResultData result = vipService.loadAll(userId);
        return result;
    }

    @RequestMapping(value = "addVip" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData addVip(String key){

        Vip vip = JsonUtil.JsonToObject(key,Vip.class);
        logger.info(key);
        logger.info("vip:{}" , vip);

        return null;
    }

    @RequestMapping(value = "deleteVip" , method = RequestMethod.POST )
    @ResponseBody
    public ResultData deleteVip(@RequestParam(value = "vipList")List<String> vipList ){

        String vipId = "";
        for(String s : vipList){
            vipId += "'" + s + "'," ;
        }
        vipId = vipId.substring(0 , vipId.lastIndexOf(","));
        System.out.println(vipId);
        return vipService.delete(vipId);
    }

    @RequestMapping(value = "editVip" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData editVip(String key ){
        ResultData result = new ResultData();
        Vip vip = JsonUtil.JsonToObject(key , Vip.class);
        if(vip == null){
            result.setSuccess(false);
            result.setMessage("参数有误");
            return result ;
        }
        return vipService.update(vip);
    }

}
