package com.jrs.patterns;

public class LoggerSingleton {
    private static LoggerSingleton instance;

    private LoggerSingleton() {} // Private constructor

    public static LoggerSingleton getInstance() {
        if (instance == null) {
            instance = new LoggerSingleton();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[SINGLETON LOG]: " + message);
    }
}