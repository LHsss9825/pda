package com.qf.pdaconsumer.service;

import com.qf.pdaCommon.Apply;

import java.util.List;
import java.util.ListIterator;

/**
 * @author ：lh
 * @date ：Created in 2022/3/10 13:17
 */

public interface ApplyService {
    //存入请求缓存
    List<Apply> findApplyByName(String applyName);
//    删除缓存
    void  deleteApplyName(String applyName);
}
