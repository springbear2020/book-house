package com.bear.bookhouse.service;

import com.bear.bookhouse.pojo.Download;
import com.bear.bookhouse.service.impl.RecordServiceImpl;
import org.junit.Test;

import java.util.Date;


/**
 * @author Spring-_-Bear
 * @datetime 2022/3/27 8:02
 */
public class RecordServiceTest {
    private final RecordService recordService = new RecordServiceImpl();

    @Test
    public void saveDownload() {
        System.out.println(recordService.saveDownload(new Download(null, 2, "下载图书", "+10", new Date(), "浪潮之巅")));
    }
}