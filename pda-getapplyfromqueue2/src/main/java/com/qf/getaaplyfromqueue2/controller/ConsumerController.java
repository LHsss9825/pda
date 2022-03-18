package com.qf.getaaplyfromqueue2.controller;

/**
 * @author ：lh
 * @date ：Created in 2022/3/14 14:16
 */

import com.qf.getaaplyfromqueue2.service.GetMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get2")
public class ConsumerController {
    @Autowired
    private GetMessageService getMessageService;
    @GetMapping("/getMeg2")
    public String getMessage(){
         getMessageService.getMegFromMq();
         return "success";
    }
}
