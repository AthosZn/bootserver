package com.zn.biz.controller;

import com.alibaba.fastjson.JSON;
import com.zn.biz.dao.AccountDao;
import com.zn.biz.entity.AccountEntity;
import com.zn.biz.entity.User;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class EsController {

    @Autowired
    AccountDao accountDao;

    @Resource
    RestHighLevelClient restHighLevelClient;



    @ResponseBody
    @RequestMapping(value = "/query")
    String redis() {

        return "sucess";
    }


    @ResponseBody
    @RequestMapping(value = "/insert")
    AccountEntity fuckyourself() {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        User user = new User();
        user.setUserName("张三");
        user.setAge(18);
        user.setSex(true);
        String json =  JSON.toJSONString(user);
        indexRequest.source(json, XContentType.JSON);
//        restHighLevelClient.index(indexRequest, );
    }

}


