package com.example.mcmor.simpleui;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mcmor on 2016-08-15.
 */
public class Utils {

    public static void writeFile(Context context, String fileName, String content) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            byte[] bytes = new byte[2048];
            fis.read(bytes, 0, bytes.length);
            fis.close();

            String content = new String(bytes);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
