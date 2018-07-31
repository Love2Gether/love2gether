package online.profsoft.love2gether.database;

import android.os.Build;

import java.util.Calendar;
import java.util.Random;

import online.profsoft.love2gether.MyApplication;

public class IDrandom {

    private static String randomString(int n) {
        String all = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int count = all.length();
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            result.append(all.charAt(random.nextInt(count)));
        }
        return new String(result);
    }

    private static String getPseudoID() {
        Random random = new Random();
        return "" + (100 + random.nextInt(900)) + Build.BOARD.length()%10 + Build.BRAND.length()%10 +
                Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 +
                Build.DISPLAY.length()%10 + Build.HOST.length()%10 +
                Build.ID.length()%10 + Build.MANUFACTURER.length()%10 +
                Build.MODEL.length()%10 + Build.PRODUCT.length()%10 +
                Build.TAGS.length()%10 + Build.TYPE.length()%10 +
                Build.USER.length()%10;
    }

    private static String getUniqueDeviceID() {
        return getPseudoID();
    }
}