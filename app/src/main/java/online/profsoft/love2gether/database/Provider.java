package online.profsoft.love2gether.database;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import online.profsoft.love2gether.models.Dialog;
import online.profsoft.love2gether.models.Message;
import online.profsoft.love2gether.models.MyDialog;
import online.profsoft.love2gether.models.User;


public class Provider {
    private static Provider instance;
    private FirebaseDatabase database;
    private String USERS = "/users/";
    private String DIALOGS = "/dialogs/";

    public static Provider getInstance() {
        if (instance != null) return instance;
        else {
            return instance = new Provider();
        }
    }

    private Provider() {
        database = FirebaseDatabase.getInstance();
    }

    interface ResponseOnComplete {
        public void run(DataSnapshot dataSnapshot);
    }

    interface ResponseOnError {
        public void run(DatabaseError databaseError);
    }

    private void getServerOnce(DatabaseReference reference, ResponseOnComplete onComplete, ResponseOnError onError) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onComplete.run(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onError.run(databaseError);
            }
        });
    }

    private void getServerOnce(Query reference, ResponseOnComplete onComplete, ResponseOnError onError) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onComplete.run(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onError.run(databaseError);
            }
        });
    }

    private void getServerOnce(DatabaseReference reference, ResponseOnComplete onComplete) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onComplete.run(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void getServerOnce(Query reference, ResponseOnComplete onComplete) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onComplete.run(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void getServerAddChildListener(Query reference, ResponseOnComplete onCompleteAdd, ResponseOnComplete onCompleteChange) {
        reference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                onCompleteAdd.run(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                onCompleteChange.run(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface FireBaseResponse<T> {
        public void onResponse(T model);
    }

    public interface FireBaseResponseList<T> {
        public void onResponse(ArrayList<T> models);
    }

    public void getUser(FireBaseResponseList<User> user) {
        getServerOnce(database.getReference(USERS), dataSnapshot -> {
            ArrayList<User> users = new ArrayList<>();
            for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                users.add(postSnapshot.getValue(User.class));
            }
            user.onResponse(users);
        });
    }

    public void getUser(String userId, FireBaseResponse<User> user) {
        getServerOnce(database.getReference(USERS + userId), dataSnapshot -> {
            user.onResponse(dataSnapshot.getValue(User.class));
        });
    }
    public void setUser(User user) {
        database.getReference(USERS + user.getId()).setValue(user);
    }

    public void getDialogs(String userID, FireBaseResponseList<MyDialog> dialogFireBaseResponseList) {
        ArrayList<MyDialog> dialogs = new ArrayList<>();
        getServerOnce(database.getReference(DIALOGS)
                        .orderByChild("userId1").equalTo(userID),
                dataSnapshot -> {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        dialogs.add(postSnapshot.getValue(MyDialog.class));
                    }
                    dialogFireBaseResponseList.onResponse(dialogs);
                });
        getServerOnce(database.getReference(DIALOGS)
                        .orderByChild("userId2").equalTo(userID),
                dataSnapshot -> {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        dialogs.add(postSnapshot.getValue(MyDialog.class));
                    }
                    dialogFireBaseResponseList.onResponse(dialogs);
                });
    }
    public void getDialogID(String dialogId, FireBaseResponse<MyDialog> dialogFireBaseResponse) {
        getServerOnce(database.getReference(DIALOGS + dialogId), dataSnapshot -> {
            dialogFireBaseResponse.onResponse(dataSnapshot.getValue(MyDialog.class));
        });
    }

    public void getDialog(String ID, FireBaseResponse<MyDialog> dialogFireBaseResponseList) {
        getServerOnce(database.getReference(DIALOGS + ID),
                dataSnapshot -> {
                    dialogFireBaseResponseList.onResponse (dataSnapshot.getValue(MyDialog.class));

                });
    }

    public void setDialog(MyDialog dialog) {
        database.getReference(DIALOGS + dialog.getId()).setValue(dialog);
    }

    public void getDialogsByUserId(String userID, FireBaseResponse<MyDialog> dialogAdd, FireBaseResponse<MyDialog> dialogChange) {

        getServerAddChildListener(
                database.getReference(DIALOGS)
                        .orderByChild("userId1").equalTo(userID),
                dataSnapshotAdd -> {
                    dialogAdd.onResponse(dataSnapshotAdd.getValue(MyDialog.class));
                },
                dataSnapshotChange -> {
                    dialogChange.onResponse(dataSnapshotChange.getValue(MyDialog.class));

                });
        getServerAddChildListener(
                database.getReference(DIALOGS)
                        .orderByChild("userId2").equalTo(userID),
                dataSnapshotAdd -> {
                    MyDialog dialog = dataSnapshotAdd.getValue(MyDialog.class);
                    dialogAdd.onResponse(dialog);
                },
                dataSnapshotChange -> {
                    MyDialog dialog = dataSnapshotChange.getValue(MyDialog.class);
                    dialogChange.onResponse(dialog);
                });
    }
    public void getMessage(String dialogId, FireBaseResponse<Message> messageFireBaseResponse) {

        getServerAddChildListener(database.getReference(DIALOGS + dialogId + "/messages/"),
                dataSnapshot -> {
                    Message message = dataSnapshot.getValue(Message.class);
                    messageFireBaseResponse.onResponse(message);
                },
                dataSnapshotChange -> {
                });
    }
}
