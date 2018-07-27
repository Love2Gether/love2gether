package online.profsoft.love2gether.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.List;

public class Dialog implements IDialog, Parcelable {

    private String id;
    private String dialogPhoto;
    private String dialogName;
    private ArrayList<User> users;
    private Message lastMessage;
    private ArrayList<Message> messages;
    private int unreadCount;

    protected Dialog(Parcel in) {
        id = in.readString();
        dialogPhoto = in.readString();
        dialogName = in.readString();
        unreadCount = in.readInt();
        messages = in.readArrayList(new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        });
    }

    public static final Creator<Dialog> CREATOR = new Creator<Dialog>() {
        @Override
        public Dialog createFromParcel(Parcel in) {
            return new Dialog(in);
        }

        @Override
        public Dialog[] newArray(int size) {
            return new Dialog[size];
        }
    };

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
        return users;
    }

    @Override
    public Message getLastMessage() {
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(dialogPhoto);
        dest.writeString(dialogName);
        dest.writeInt(unreadCount);
        dest.writeList(messages);
    }
}
