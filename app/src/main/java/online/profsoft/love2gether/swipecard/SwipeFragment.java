package online.profsoft.love2gether.swipecard;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import online.profsoft.love2gether.R;
import online.profsoft.love2gether.database.Provider;
import online.profsoft.love2gether.databinding.FragmentSwipeBinding;
import online.profsoft.love2gether.models.User;


public class SwipeFragment extends Fragment {

    private FragmentSwipeBinding binding;
    private SwipeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_swipe, container, false);
        ArrayList<User> users = new ArrayList<>();
        adapter = new SwipeAdapter(new ArrayList<>());
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recycler.setAdapter(adapter);
        load();
        Provider.getInstance().getUser(user->{
            users.addAll(user);
            adapter.setUsers(users);
            show();
        });
        return binding.getRoot();
    }
    private void load() {
        binding.loadWrap.setVisibility(View.VISIBLE);
        binding.recycler.setVisibility(View.GONE);
    }

    private void show() {
        binding.loadWrap.setVisibility(View.GONE);
        binding.recycler.setVisibility(View.VISIBLE);
    }
}
