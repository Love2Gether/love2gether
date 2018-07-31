package online.profsoft.love2gether;

import android.app.Application;

import online.profsoft.love2gether.models.User;

public class MyApplication extends Application {
    private static MyApplication sInstance;
    private User user;


    public static MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

