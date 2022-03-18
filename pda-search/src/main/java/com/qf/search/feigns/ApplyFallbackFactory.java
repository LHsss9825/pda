package com.qf.search.feigns;

import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：lh
 * @date ：Created in 2022/3/9 13:40
 */
@Component
public class ApplyFallbackFactory implements FallbackFactory<ApplyFeign> {
    @Autowired
    private  ApplyFeignFallback applyFeignFallback;
    @Override
    public ApplyFeign create(Throwable throwable) {
        System.out.println("出现异常："+throwable.getMessage());

        throwable.printStackTrace();
        return null;
    }
}
