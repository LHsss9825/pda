package com.qf.pdaconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qf.pdaCommon.Apply;
import com.qf.pdaconsumer.feigns.ApplyFeign;
import com.qf.pdaconsumer.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：lh
 * @date ：Created in 2022/3/7 16:16
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApplyFeign applyFeign;

    @PostMapping("/add")
    public String addApply(@RequestBody Apply apply) {
        String url = "http://APPLYPROVIDER/apply/add";
        String reslut = restTemplate.postForObject(url, apply, String.class);
        return reslut;
    }

    @GetMapping("/find1")
    @HystrixCommand(fallbackMethod = "findApplyFallback")
    public List<Apply> findApply1() {
//        int result = 1 / 0;
        String url = "http://APPLYPROVIDER/apply/find";
        List<Apply> list = restTemplate.getForObject(url, List.class);
        return list;
    }

    //编写apply降级方法
    public List<Apply> findApplyFallback() {
        return new ArrayList<Apply>();
    }


    @GetMapping("/find2")
    public List<Apply> findApply2() {
        List<Apply> list = applyFeign.findApply();
        return list;

    }

    @GetMapping("/find3")
    public List<Apply> findApply3(String applyName) {
        String url = "http://APPLYPROVIDER/apply/find3?applyName=" + applyName;
        System.out.println(applyName);
        List<Apply> list = restTemplate.getForObject(url, List.class);
        return list;
    }

    @GetMapping("/find4/{applyName}")
    public List<Apply> find4(@PathVariable String applyName) {
        System.out.println(applyName);
        List<Apply> list = applyFeign.findApply4(applyName);
        List<Apply> list2 = applyFeign.findApply4("lh");
        return list;
    }


    @GetMapping("/find5")
    public List<Apply> find5(@RequestParam String applyName) {
        return applyFeign.findApply5(applyName);
    }

    //   ---------------------请求缓存---------------
    @GetMapping("/find6")
    public List<Apply> find6(String applyName) {
        List<Apply> list1 = applyService.findApplyByName(applyName);
        System.out.println("再调用一次");
        List<Apply> list2 = applyService.findApplyByName(applyName);
        list1.addAll(list2);
        return list1;
    }
}
