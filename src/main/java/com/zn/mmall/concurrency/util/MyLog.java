package com.zn.mmall.concurrency.util;

/**
 * Created by zn on 2019/5/22.
 */
public class MyLog {

    public static void info(String info, Object... objects){
        String str=  String.format(info, objects);
        System.out.println(str);
    }

    public  static void info(String info){
        System.out.println(info);
    }
}
