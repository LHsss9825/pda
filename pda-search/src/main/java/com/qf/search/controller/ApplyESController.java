package com.qf.search.controller;

import com.qf.search.service.ESMageService;
import com.qf.search.service.impl.ESMageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author ：lh
 * @date ：Created in 2022/3/18 14:08
 */
@RestController
@RequestMapping("/search")
public class ApplyESController {

    @Autowired
    private ESMageService esMageService = new ESMageServiceImpl();

    @GetMapping("/create")
    public String createIndex() throws IOException {
        String result = "";
        try {
            esMageService.creatIndex();
            result = "crete success for esService";
        } catch (IOException e) {
            e.printStackTrace();
            result = "failed";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(result);
            result = "failed";
        }
        return result;
    }

    @GetMapping("/exists")
    public String exists() {
        String meg = "";
        try {
            boolean result = esMageService.indexExists();
            if (result) {
                meg = "该索引pda已被创建";
            } else {
                meg = "该索引pda不存在";
            }
        } catch (IOException e) {
            e.printStackTrace();
            meg = "判断该索引是否发生了异常";
        }
        return meg;
    }

    @GetMapping("/save")
    public String save() {
        String meg = "";
        try {
            esMageService.addDocument();
            meg = "写道ES服务成功";
        } catch (IOException e) {
            e.printStackTrace();
            meg = "写入ES失败";
        }
        return meg;
    }
}
