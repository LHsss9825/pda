package com.qf.pdaconsumer.controller;
import com.qf.pdaCommon.Apply;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：lh
 * @date ：Created in 2022/3/7 16:16
 */
@RestController
@RequestMapping("apply")
public class ApplyController {

    @RequestMapping("add")
    public String addApply(Apply apply){
        return "";
    }
}
