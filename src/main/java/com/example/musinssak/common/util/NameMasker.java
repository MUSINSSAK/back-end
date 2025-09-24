package com.example.musinssak.common.util;

import java.util.Objects;


// 작성자 이름 마스킹 유틸
public final class NameMasker {

    private NameMasker() {}

    /**
     * 간단 규칙(명세 예시 "김**"):
     * - 길이 1: 그대로 반환 (ex. "김")
     * - 길이 2: 첫 글자 + "*" (ex. "김철" -> "김*")
     * - 길이 ≥3: 앞 1~2글자 노출, 나머지 "*" 채움
     *   - 한글/영문 모두 동일하게 처리
     */
    public static String mask(String displayName) {
        if (displayName == null) return "";
        String name = displayName.trim();
        if (name.isEmpty()) return "";

        int len = name.length();
        if (len == 1) return name;
        if (len == 2) return name.charAt(0) + "*";

        int visible = Math.min(2, len - 1); // 최대 2글자 노출
        StringBuilder sb = new StringBuilder();
        sb.append(name, 0, visible);
        for (int i = visible; i < len; i++) sb.append('*');
        return sb.toString();
    }
}