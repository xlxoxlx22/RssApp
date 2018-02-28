package com.test.rssapp.helpers;
import android.support.annotation.NonNull;
import android.util.Log;
import android.content.Context;
import android.support.compat.BuildConfig;

public class LogHelper {

    private static void logInfo(String logTag, String text) {
        Log.i(logTag, text);
    }
    private static void logError(String logTag, String text) {
        Log.e(logTag, text);
    }
    private static void logDebug(String logTag, String text) {
        Log.d(logTag, text);
    }
    private static void logVerbose(String logTag, String text) {
        Log.v(logTag, text);
    }


    public static void showLogMessage(Context context, LogType type, @NonNull String text) {
        if (BuildConfig.DEBUG) {
            String logTag = context.getClass().getSimpleName();
            switch (type){
                case INFO:
                    logInfo(logTag, text);
                    break;
                case ERROR:
                    logError(logTag, text);
                    break;
                case DEBUG:
                    logDebug(logTag, text);
                    break;
                case VERBOSE:
                    logVerbose(logTag, text);
                    break;
            }
        }
    }

    public enum LogType {
        INFO,
        ERROR,
        DEBUG,
        VERBOSE
    }
}
