package online.profsoft.love2gether.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import online.profsoft.love2gether.models.User;


public class Provider {
    private static Provider instance;
    private FirebaseDatabase database;
    private String USERS = "/users/";
    private String DIALOG = "/dialog/";
    private String MESSAGE = "/dialog/message/";

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

}
