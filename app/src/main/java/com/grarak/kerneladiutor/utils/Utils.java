package com.grarak.kerneladiutor.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by willi on 30.11.14.
 */
public class Utils implements Constants {

    public static int getScreenOrientation(Context context) {
        Display getOrient = ((Activity) context).getWindowManager().getDefaultDisplay();
        int orientation = getOrient.getWidth() < getOrient.getHeight() ?
                Configuration.ORIENTATION_PORTRAIT : Configuration.ORIENTATION_LANDSCAPE;
        return orientation;
    }

    public static void toast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static int getInteger(String name, int defaults, Context context) {
        Log.i(TAG, "getting " + name);
        return context.getSharedPreferences(PREF_NAME, 0)
                .getInt(name, defaults);
    }

    public static void saveInteger(String name, int value, Context context) {
        Log.i(TAG, "saving " + name + " as " + value);
        context.getSharedPreferences(PREF_NAME, 0).edit().putInt(name, value)
                .apply();
    }

    public static boolean existFile(String file) {
        return new File(file).exists();
    }

    public static String readFile(String filepath) {
        try {
            BufferedReader buffreader = new BufferedReader(new FileReader(
                    filepath), 256);
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = buffreader.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }
            buffreader.close();
            return text.toString().trim();
        } catch (FileNotFoundException e) {
            Log.e(TAG, filepath + " does not exist");
        } catch (IOException e) {
            Log.e(TAG, "I/O read error: " + filepath);
        }
        return null;
    }

}
