package cn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String date1(Date date,String patt){
        SimpleDateFormat sdf =new SimpleDateFormat();
        String format = sdf.format(date);
        return format;
    }
    public static Date string1(String string , String patt) throws ParseException {
        SimpleDateFormat sdf =new SimpleDateFormat();
        Date parse = sdf.parse(string);
        return parse;
    }
}
