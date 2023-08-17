package com.KoreaIT.java.AM.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
  public static String getNowDateStr() {
    //현재 날짜 시간
    SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");

    Date time = new Date();

    String time1 = format.format(time);

    return format.format(time);
  }
}
