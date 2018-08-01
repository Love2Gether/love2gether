package online.profsoft.love2gether;

import android.app.ActionBar;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import online.profsoft.love2gether.databinding.ActivityMainBinding;
import online.profsoft.love2gether.message.DialogFragment;
import online.profsoft.love2gether.swipecard.SwipeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SwipeFragment swipeFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           switch (item.getItemId()) {
               case R.id.action_swipecard:
                   getFragmentManager()
                           .beginTransaction()
                           .replace(R.id.container, new SwipeFragment())
                           .commit();
                   return true;
               case R.id.action_chat:
                   getFragmentManager()
                           .beginTransaction()
                           .replace(R.id.container, new DialogFragment())
                           .commit();
                   return true;
               case R.id.action_profile:
                   return true;
           }
           return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        swipeFragment = new SwipeFragment();
        binding.navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SwipeFragment())
                .commit();
        if(getActionBar() != null){
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.abs_layout);
        }
    }
}
