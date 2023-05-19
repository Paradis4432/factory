package me.paradis.factory.tools;

import me.paradis.factory.Factory;

// credits to https://gist.github.com/WesJD/36e53368e68a37f797a9
public class Logging {

    private static final String PREFIX = "Factory V" + Factory.version;

    public static void Statistic(String message) {
        logWithExtra("Statistic", message);
    }

    public static void Debug(String message) {
        logWithExtra("Debug", message);
    }

    public static void Error(String message) {
        logWithExtra("Error", message);
    }

    public static void Warning(String message) {
        logWithExtra("Warning", message);
    }

    public static void Info(String message) {
        logWithExtra("Info", message);
    }

    public static void logWithExtra(String extra, String message) {
        System.out.println(PREFIX + ": " + extra + " | " + message);
    }

    public static void log(String message) {
        System.out.println(PREFIX + " | " + message);
    }

}
