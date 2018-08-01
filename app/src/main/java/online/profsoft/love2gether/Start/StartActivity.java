package online.profsoft.love2gether.Start;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

import online.profsoft.love2gether.MainActivity;
import online.profsoft.love2gether.MyApplication;
import online.profsoft.love2gether.database.Provider;
import online.profsoft.love2gether.login.LoginActivity;
import online.profsoft.love2gether.login.RegistryActivity;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        } else {
            Provider.getInstance().getUser(firebaseUser.getUid(), user -> {
                if (user != null) {
                    if (user.getId() == null) {
                        user.setId(firebaseUser.getUid());
                        Provider.getInstance().setUser(user);
                    }
                    MyApplication.getInstance().setUser(user);
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(StartActivity.this, RegistryActivity.class));
                    finish();
                }
            });
        }
    }
}
