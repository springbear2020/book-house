package com.bear.bookhouse.dao;

import com.bear.bookhouse.pojo.Download;
import com.bear.bookhouse.pojo.Record;
import com.bear.bookhouse.pojo.Upload;

import java.util.List;

/**
 * @author Spring-_-Bear
 * @datetime 2022/3/26 10:29
 */
public interface UploadDao {
   /**
    * 保存用户图书上传记录
    *
    * @param upload 上传记录
    * @return 1 - 保存成功
    */
   int saveUpload(Upload upload);

   /**
    * 通过用户 id 查询用户上传记录
    *
    * @param userId 用户 id
    * @return Upload or null
    */
   List<Upload> listUploadByUserId(int userId);
}