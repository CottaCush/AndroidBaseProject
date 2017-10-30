package com.cottacush.android.libraries.utils;

import android.util.Log;

/**
 * Created by rasheed on 10/23/17.
 */
public class LogHelper {

    public static final boolean isDebugEnabled = true;

    public static enum LEVEL {
        DEBUG, INFO, ERROR, VERBOSE, WARN
    }

    public static void log(Object o, String message, LEVEL level) {
        if (isDebugEnabled) {
            String simpleName = o.getClass().getSimpleName();
            switch (level) {
                case DEBUG:
                    Log.d(simpleName, message);
                    break;
                case INFO:
                    Log.i(simpleName, message);
                    break;
                case ERROR:
                    Log.e(simpleName, message);
                    break;
                case VERBOSE:
                    Log.v(simpleName, message);
                    break;
                case WARN:
                    Log.w(simpleName, message);
                    break;
                default:
            }
        }
    }
}
