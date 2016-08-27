package com.evetime.cms.controller;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.VipCardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Rains
 * on 2016-06-23.
 */
@Controller
@RequestMapping("/vipCard")
public class VipCardController {

    @Resource(name = "VipCardService")
    private VipCardService vipCardService;

    @RequestMapping("/loadAll")
    @ResponseBody
    public ResultData loadAll(String cloudId){

        ResultData result = vipCardService.loadAll(cloudId);
        return result ;
    }

}
