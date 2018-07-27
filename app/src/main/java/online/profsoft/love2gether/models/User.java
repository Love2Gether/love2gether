package online.profsoft.love2gether.models;

import com.stfalcon.chatkit.commons.models.IUser;

public class User implements IUser {

    private String id;
    private String name;
    private String avatar;

    public User(String id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public  String  getId () {
        return id;
    }

    @Override
    public  String  getName () {
        return name;
    }

    @Override
    public  String  getAvatar () {
        return avatar;
    }
}
