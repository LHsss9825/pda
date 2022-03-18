package com.qf.search.feigns;
import com.qf.pdaCommon.Apply;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：lh
 * @date ：Created in 2022/3/8 19:14
 */
//@FeignClient(value = "APPLYPROVIDER",fallback = ApplyFeignFallback.class) //被调用的服务的名字
@FeignClient(value = "APPLYPROVIDER",fallbackFactory = ApplyFallbackFactory.class) //工厂方式
//@FeignClient(value = "APPLYPROVIDER")
public interface ApplyFeign {
    @RequestMapping(value = "/apply/find",method = RequestMethod.GET)
    List<Apply> findApply();
    @RequestMapping(value = "/apply/add",method = RequestMethod.POST)
    String addApply(@RequestBody Apply apply);
    @RequestMapping(value="/apply/find4/{applyName}",method = RequestMethod.GET)
    List<Apply> findApply4(@PathVariable String applyName);
    @RequestMapping(value = "/apply/find5",method = RequestMethod.GET)
    List<Apply> findApply5(@RequestParam String applyName);
}
