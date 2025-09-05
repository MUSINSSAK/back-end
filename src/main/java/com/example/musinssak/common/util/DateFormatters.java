package com.example.musinssak.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

// 날짜 포맷 유틸 (yyyy-MM-dd)
public final class DateFormatters {

    private DateFormatters() {}

    private static final ZoneId ZONE = ZoneId.systemDefault(); // 필요시 KST로 고정 가능
    private static final DateTimeFormatter YMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String toYMD(LocalDateTime time) {
        if (time == null) return null;
        return time.atZone(ZONE).format(YMD);
    }
}