package online.profsoft.love2gether.models;

import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.ArrayList;

public class MyDialog {

    private String id;
    private String dialogPhoto;
    private String dialogName;
    private MyMessage lastMessage;
    private ArrayList<MyMessage> messages;
    private int unreadCount;
    private String userId1;
    private String userId2;
    private ArrayList<User> users;


    public MyDialog() {
    }

    public MyDialog(String id, String dialogPhoto, String dialogName, MyMessage lastMessage, ArrayList<MyMessage> messages, int unreadCount, String userId1, String userId2, ArrayList<User> users) {
        this.id = id;
        this.dialogPhoto = dialogPhoto;
        this.dialogName = dialogName;
        this.lastMessage = lastMessage;
        this.messages = messages;
        this.unreadCount = unreadCount;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public String getDialogPhoto() {
        return dialogPhoto;
    }

    public void setDialogPhoto(String dialogPhoto) {
        this.dialogPhoto = dialogPhoto;
    }

    public String getDialogName() {
        return dialogName;
    }

    public void setDialogName(String dialogName) {
        this.dialogName = dialogName;
    }

    public MyMessage getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MyMessage lastMessage) {
        this.lastMessage = lastMessage;
    }

    public ArrayList<MyMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MyMessage> messages) {
        this.messages = messages;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }
}
