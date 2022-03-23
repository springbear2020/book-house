package com.bear.bookhouse.dao.impl;

import com.bear.bookhouse.dao.FavoriteDao;
import com.bear.bookhouse.pojo.Favorite;
import com.bear.bookhouse.pojo.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Spring-_-Bear
 * @datetime 2022/3/23 16:33
 */
public class FavoriteDaoImplTest {
    private final FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Test
    public void queryFavoriteByBookId() {
        Favorite favorite = favoriteDao.queryFavoriteByUserIdAndBookId(1, 5);
        System.out.println(favorite);
    }

    @Test
    public void queryFavoritesByUserId() {
        List<Favorite> favorites = favoriteDao.queryFavoritesByUserId(1);
        for (Favorite favorite : favorites) {
            System.out.println(favorite);
        }
    }

    @Test
    public void deleteUserFavoriteByUserIdAndBookId() {
        int i = favoriteDao.deleteUserFavoriteByUserIdAndBookId(1, 2);
        System.out.println(i);
    }
}