package com.qf.publisher.controller;

import com.qf.publisher.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：lh
 * @date ：Created in 2022/3/14 13:49
 */
@RestController
@RequestMapping("/pmq")
public class PublisherController {
@Autowired
private PublishService publishService;
@GetMapping("/save")
    public String publisher(String name){
      String reslut= publishService.saveTo(name);
return reslut;
}
@GetMapping("save2")
    public String saveToMq2(String name){
    return publishService.saveToMqFanout(name);
}

    @GetMapping("save3")
    public String saveToMq3(String name,String addr,String phone){
        return publishService.saveToMyByRouting(name,addr,phone);
    }
    @GetMapping("save4")
    public String saveToMq4(String s1,String s2,String s3){
        return publishService.saveToMqByTopic(s1,s2);
    }
}
