package com.dbexercise;

import java.util.Map;
// 시스템 환경 변수 확인하기
public class SystemEnv {
    public static void main(String[] args) {
        Map<String, String> env = System.getenv();

        for (String key : env.keySet()) {
            System.out.printf("key : %s \nvalue : %s\n\n", key, env.get(key));
        }
    }
}
