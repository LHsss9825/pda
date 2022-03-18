package com.qf.pdaconsumer.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.qf.pdaCommon.Apply;
import com.qf.pdaconsumer.feigns.ApplyFeign;
import com.qf.pdaconsumer.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：lh
 * @date ：Created in 2022/3/10 13:18
 */
@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyFeign applyFeign;

    //配置请求缓存
    @Override
    @CacheResult//缓存该方法的返回结果
    @HystrixCommand(commandKey ="findApplyByName")
    public List<Apply> findApplyByName(@CacheKey String applyName) {

        return applyFeign.findApply5(applyName);
    }
//删除缓存
    @Override
    @CacheRemove(commandKey = "deleteApplyName")
    @HystrixCommand
    public void deleteApplyName(@CacheKey String applyName) {
        System.out.println("成功删除请求缓存!!!!");
    }
}
