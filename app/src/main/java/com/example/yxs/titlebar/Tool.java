package com.example.yxs.titlebar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yxs on 16/3/19.
 */
public class Tool {

    public static String dateChange(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh时:mm分:ss秒");

        return simpleDateFormat.format(date);
    }
}
