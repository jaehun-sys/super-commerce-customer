package com.bestcommerce.customer.util;

import java.time.format.DateTimeFormatter;

public class TimeFormat {
    public static final DateTimeFormatter orderLogDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
}
