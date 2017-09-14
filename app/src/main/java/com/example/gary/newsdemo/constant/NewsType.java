package com.example.gary.newsdemo.constant;

/**
 * Created by Gary on 2017/9/12.
 */

public class NewsType {
    public static String getNewsType(int i){
        switch (i){
            case 0:
                return "social";
            case 1:
                return "guonei";
            case 2:
                return "world";
            case 3:
                return "huabian";
            case 4:
                return "tiyu";
            case 5:
                return "nba";
            case 6:
                return "keji";
            default:
                return "social";
        }
    }
}
