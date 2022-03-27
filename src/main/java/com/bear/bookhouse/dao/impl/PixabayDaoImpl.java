package com.bear.bookhouse.dao.impl;

import com.bear.bookhouse.dao.BaseDao;
import com.bear.bookhouse.dao.PixabayDao;
import com.bear.bookhouse.pojo.Pixabay;
import com.bear.bookhouse.util.NumberUtil;

/**
 * @author Spring-_-Bear
 * @datetime 2022/3/26 23:21
 */
public class PixabayDaoImpl extends BaseDao implements PixabayDao {
    @Override
    public Pixabay getPixabayById(int id) {
        String sql = "SELECT `id`,`condition`,`tags`,`views`,`downloads`,`collections`,`likes`,`comments`,`large_image_url` url, `add_time` addTime FROM `t_pixabay` WHERE `id` = ?;";
        return getRecord(Pixabay.class, sql, id);
    }

    @Override
    public int getPixabayCounts() {
        String sql = "SELECT COUNT(`id`) FROM `t_pixabay`;";
        return NumberUtil.objectToInteger(getSingleValue(sql), -1);
    }

    @Override
    public int deletePixabayById(int id) {
        String sql = "DELETE FROM `t_pixabay` WHERE `id` = ?;";
        return update(sql, id);
    }
}