package online.profsoft.love2gether.models;

import com.stfalcon.chatkit.commons.models.IUser;

import java.io.Serializable;

public class User implements IUser, Serializable {

    private String id;
    private String name;
    private String avatar;

    public User() {
    }

    public User(String id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
