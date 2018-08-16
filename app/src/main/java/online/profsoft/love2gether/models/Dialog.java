package online.profsoft.love2gether.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dialog implements IDialog {

    private String id;
    private String dialogPhoto;
    private String dialogName;
    private ArrayList<User> users;
    private Message lastMessage;
    private ArrayList<Message> messages;
    private int unreadCount;


    public Dialog() {
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }


    public Dialog(String id, String dialogPhoto, String dialogName, ArrayList<User> users, Message lastMessage, ArrayList<Message> messages, int unreadCount) {
        this.id = id;
        this.dialogPhoto = dialogPhoto;
        this.dialogName = dialogName;
        this.users = users;
        this.lastMessage = lastMessage;
        this.messages = messages;
        this.unreadCount = unreadCount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDialogName(String dialogName) {
        this.dialogName = dialogName;
    }

    public void setDialogPhoto(String dialogPhoto) {
        this.dialogPhoto = dialogPhoto;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return dialogPhoto;
    }

    @Override
    public String getDialogName() {
        return dialogName;
    }

    @Override
    public ArrayList<User> getUsers() {
        return  users;
    }

    @Override
    public IMessage getLastMessage() {
        return lastMessage;
    }
    @Override
    public void setLastMessage(IMessage lastMessage) {
        this.lastMessage = (Message)lastMessage;
    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dialog dialog = (Dialog) o;
        return Objects.equals(id, dialog.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
