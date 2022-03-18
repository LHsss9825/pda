package com.qf.pdaprovider.controller;

import com.qf.pdaCommon.Apply;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：lh
 * @date ：Created in 2022/3/8 13:34
 */
@RestController
@RequestMapping("apply")
public class ProviderController {


    @PostMapping("add")
    public String add(@RequestBody Apply apply) {
        System.out.println(apply);
        return "success";
    }

    @GetMapping("/find")
    public List<Apply> find() {
        List<Apply> list = new ArrayList<Apply>();
     Apply apply1= new Apply("aaa", "19970101", "2022/3/8", "nana", "sdaf", 9);
     Apply apply2= new Apply("bbb", "19970101", "2022/3/8", "nana", "sdaf", 9);
     Apply apply3= new Apply("ccc", "19970101", "2022/3/8", "nana", "sdaf", 9);
     Apply apply4= new Apply("ddd", "19970101", "2022/3/8", "nana", "sdaf", 9);
        list.add(apply1);
        list.add(apply2);
        list.add(apply3);
        list.add(apply4);
        return list;
    }

    @GetMapping("/find2")
    public List<Apply> find2(String applyName) {
        List<Apply> list = new ArrayList<Apply>();
        System.out.println("条件：" + applyName);
        list.add(new Apply("aaa", "19970101", "2022/3/8", "nana", "ss", 9));
        list.add(new Apply("bbb", "19970101", "2022/3/8", "nana", "ggg", 19));
        list.add(new Apply("ccc", "19970101", "2022/3/8", "nana", "ggg", 29));
        list.add(new Apply("ddd", "19970101", "2022/3/8", "nana", "jjj", 39));
        list.add(new Apply("eee", "19970101", "2022/3/8", "nana", "kkk", 49));
        return list;
    }

    @GetMapping("/find3")
    public List<Apply> find3(String applyName) {
        List<Apply> list = new ArrayList<Apply>();
        System.out.println("条件：" + applyName);
        list.add(new Apply("lh", "19970101", "2022/3/8", "nana", "ss", 9));
        list.add(new Apply("zxc", "19970101", "2022/3/8", "nana", "ggg", 19));
        list.add(new Apply("ccc", "19970101", "2022/3/8", "nana", "ggg", 29));
        list.add(new Apply("ddd", "19970101", "2022/3/8", "nana", "jjj", 39));
        list.add(new Apply("eee", "19970101", "2022/3/8", "nana", "kkk", 49));
        return list;
    }

    @GetMapping("/find4/{applyName}")
    public List<Apply> find4(@PathVariable String applyName) {
        System.out.println("调用了一次" + applyName);
        List<Apply> list = new ArrayList<Apply>();
        System.out.println("条件：" + applyName);
        list.add(new Apply("lh", "19970101", "2022/3/8", "nana", "ss", 9));
        list.add(new Apply("zxc", "19970101", "2022/3/8", "nana", "ggg", 19));
        list.add(new Apply("ccc", "19970101", "2022/3/8", "nana", "ggg", 29));
        list.add(new Apply("ddd", "19970101", "2022/3/8", "nana", "jjj", 39));
        list.add(new Apply("eee", "19970101", "2022/3/8", "nana", "kkk", 49));
        return list;

    }

    @GetMapping("/find5")
    public List<Apply> find5(@RequestParam String applyName) {
        List<Apply> list = new ArrayList<>();
        System.out.println("条件1：" + applyName);
        list.add(new Apply("lh", "19970101", "2022/3/8", "nana", "ss", 9));
        list.add(new Apply("zxc", "19970101", "2022/3/8", "nana", "ggg", 19));
        list.add(new Apply("ccc", "19970101", "2022/3/8", "nana", "ggg", 29));
        list.add(new Apply("ddd", "19970101", "2022/3/8", "nana", "jjj", 39));
        list.add(new Apply("eee", "19970101", "2022/3/8", "nana", "kkk", 49));
        return list;
    }

}
