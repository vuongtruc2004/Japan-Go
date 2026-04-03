package com.japan_go_be;

public class TestMain {
    public static void main(String[] args) {
        Integer a = 100;
        Integer b = 100;
        System.out.println(a == b);      // true
        System.out.println(a.equals(b)); // true

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);      // false
        System.out.println(c.equals(d)); // true

        Integer e = 128;
        int f = 128;
        System.out.println(e == f);      // true (e unbox thành int)
    }
}
