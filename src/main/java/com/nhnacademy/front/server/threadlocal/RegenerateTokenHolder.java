package com.nhnacademy.front.server.threadlocal;

public class RegenerateTokenHolder {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void setData(String data){
        context.set(data);
    }

    public static String getData(){
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
