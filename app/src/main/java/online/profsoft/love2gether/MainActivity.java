package online.profsoft.love2gether;

import android.app.ActionBar;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import online.profsoft.love2gether.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

//    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//           switch (item.getItemId()) {
//               case R.id.action_swipecard:
//                   return true;
//               case R.id.action_chat:
//                   return true;
//               case R.id.action_profile:
//                   return true;
//           }
//           return false;
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
       // binding.navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(null, R.drawable.ic_swipecard, R.color.colorWhite);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(null, R.drawable.ic_notifications, R.color.colorWhite);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(null, R.drawable.ic_chat, R.color.colorWhite);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(null, R.drawable.ic_profile,R.color.colorWhite);

        binding.bottomNavigation.addItem(item1);
        binding.bottomNavigation.addItem(item2);
        binding.bottomNavigation.addItem(item3);
        binding.bottomNavigation.addItem(item4);
        binding.bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);


        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.abs_layout);
        }
    }
}
