package com.bear.bookhouse.util;


/**
 * @author Spring-_-Bear
 * @datetime 2022/3/15 17:25
 */
public class EmailUtilTest {
    public static void main(String[] args) {
        try {
            EmailUtil instance = EmailUtil.getInstance();
            instance.sendEmail("394987816@qq.com");
            String verifyCode = instance.getVerifyCode();
            System.out.println(verifyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}