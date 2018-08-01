package online.profsoft.love2gether.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import online.profsoft.love2gether.Start.BaseActivity;
import online.profsoft.love2gether.MainActivity;
import online.profsoft.love2gether.MyApplication;
import online.profsoft.love2gether.R;
import online.profsoft.love2gether.database.Provider;
import online.profsoft.love2gether.databinding.ActivityRegistryBinding;
import online.profsoft.love2gether.models.User;

public class RegistryActivity extends BaseActivity {

    private ActivityRegistryBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_registry);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registry);

        if (firebaseUser != null) {

            binding.name.setText(firebaseUser.getDisplayName());
        }

        binding.button.setOnClickListener(view -> {
            if (binding.name.getText().toString().equals("")) {
                showToast("Заполните все поля");
                return;
            }
            String name = binding.name.getText().toString();
            User registerUser = new User(firebaseUser.getUid(), name, "https://www.gstatic.com/webp/gallery/4.sm.jpg");

            Provider.getInstance().setUser(registerUser);
            MyApplication.getInstance().setUser(registerUser);

            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

    }
}
