package com.qf.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * @author ：lh
 * @date ：Created in 2022/3/18 13:36
 */
public interface ESMageService {
//    创建索引
    void creatIndex() throws IOException;
//    检查索引是否存在
    boolean indexExists() throws IOException;
//    删除索引
    boolean indexDelete() throws IOException;
//    添加文档
    String addDocument() throws IOException;
}
