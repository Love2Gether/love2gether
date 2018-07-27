package online.profsoft.love2gether.message;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import online.profsoft.love2gether.MainActivity;
import online.profsoft.love2gether.R;
import online.profsoft.love2gether.databinding.FragmentDialogBinding;
import online.profsoft.love2gether.models.Dialog;
import online.profsoft.love2gether.models.Message;
import online.profsoft.love2gether.models.User;

public class DialogFragment extends Fragment {

private FragmentDialogBinding binding;
private ArrayList<Dialog> dialogs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog, container, false);
        User user = new User("231", "Леша", "https://www.gstatic.com/webp/gallery/4.sm.jpg");
        User user1 = new User("23", "Миша", "https://www.gstatic.com/webp/gallery/5.sm.jpg");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        Message message = new Message("321", "Hi", user, new Date(600851475143L));
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(message);
        Dialog dialog = new Dialog("123", "https://www.gstatic.com/webp/gallery/1.sm.jpg", "1 dialog", users, message,messages, 1 );
        dialogs.add(dialog);
        DialogsListAdapter dialogsListAdapter = new DialogsListAdapter<>(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(binding.getRoot().getContext()).load(url).into(imageView);
            }
        });
        dialogsListAdapter.setItems(dialogs);

        binding.dialogsList.setAdapter(dialogsListAdapter);
        dialogsListAdapter.setOnDialogClickListener(new DialogsListAdapter.OnDialogClickListener<Dialog>() {
            @Override
            public void onDialogClick(Dialog dialog) {
                startActivity(new Intent(getContext(), MessageActivity.class).putExtra("dialog", dialog));
            }
        });
        return binding.getRoot();
    }

}
