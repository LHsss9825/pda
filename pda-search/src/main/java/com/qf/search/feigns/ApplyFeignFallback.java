package com.qf.search.feigns;

import com.qf.pdaCommon.Apply;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：lh
 * @date ：Created in 2022/3/9 13:26
 */
@Component
public class ApplyFeignFallback  implements ApplyFeign{
    @Override
    public List<Apply> findApply() {
        List<Apply> list =new ArrayList<>();
        return list;
    }

    @Override
    public String addApply(Apply apply) {
        return null;
    }

    @Override
    public List<Apply> findApply4(String applyName) {
        //创建托底数据
        List<Apply> list =new ArrayList<>();
        System.out.println("服务降级了："+list);
        return list;
    }

    @Override
    public List<Apply> findApply5(String applyName) {
        return null;
    }
}
