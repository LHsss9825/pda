package com.qf.getapplyfromqueue.controller;

/**
 * @author ：lh
 * @date ：Created in 2022/3/14 14:16
 */

import com.qf.getapplyfromqueue.service.GetMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/get")
public class ConsumerController {
    @Autowired
    private GetMessageService getMessageService;
    @GetMapping("/getMeg")
    public String getMessage(){
         getMessageService.getMegFromMq();
         return "success";
    }
}
