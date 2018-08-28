package com.hosseinkurd.googleroom.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Helper {

    private static Helper instance;

    public static Helper getInstance() {
        if (instance == null) instance = new Helper();
        return instance;
    }

    private Helper () {

    }

    public String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}