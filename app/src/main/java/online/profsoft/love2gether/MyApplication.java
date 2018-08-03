package online.profsoft.love2gether;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.Calendar;

import online.profsoft.love2gether.models.User;

public class MyApplication extends Application {
    private static MyApplication sInstance;
    private User user;
    private long timeCreate;


    public static MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        SharedPreferences sharedPreferences = getSharedPreferences("app_info", MODE_PRIVATE);
        if (sharedPreferences.contains("timeCreate")) {
            timeCreate = sharedPreferences.getLong("timeCreate", 0);
        } else {
            long time = Calendar.getInstance().getTimeInMillis();
            sharedPreferences.edit().putLong("timeCreate", time).commit();
            timeCreate = time;
        }

    }

    public long getTimeCreate() {
        return timeCreate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

