package online.profsoft.love2gether.swipecard;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import online.profsoft.love2gether.MainActivity;
import online.profsoft.love2gether.R;
import online.profsoft.love2gether.databinding.ItemSwipeBinding;
import online.profsoft.love2gether.message.MessageActivity;
import online.profsoft.love2gether.models.User;

public class SwipeAdapter extends  RecyclerView.Adapter<SwipeAdapter.ListViewHolder> {

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemSwipeBinding binding;

        public ListViewHolder(ItemSwipeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.enterMessag.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(binding.getRoot().getContext(), MessageActivity.class);
            intent.putExtra("userID", users.get(getAdapterPosition()).getId());
            binding.getRoot().getContext().startActivity(intent);
        }
    }

    private ArrayList<User> users;


    public SwipeAdapter(ArrayList<User> users) {
        this.users = users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public SwipeAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSwipeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_swipe, parent, false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SwipeAdapter.ListViewHolder holder, int position) {
        holder.binding.userName.setText(users.get(position).getName());
        if (users.get(position).getAvatar() != null)
        Picasso.with(holder.binding.getRoot().getContext()).load(users.get(position).getAvatar()).into(holder.binding.userAvatar);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
