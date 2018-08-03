package online.profsoft.love2gether.message;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import online.profsoft.love2gether.MyApplication;
import online.profsoft.love2gether.R;
import online.profsoft.love2gether.database.Provider;
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
//        User user = new User("231", "Леша", "https://www.gstatic.com/webp/gallery/4.sm.jpg");
////        User user1 = new User("23", "Миша", "https://www.gstatic.com/webp/gallery/5.sm.jpg");
////        ArrayList<User> users = new ArrayList<>();
////        users.add(user);
////        users.add(user1);
////        Message message = new Message("321", "Hi", user, new Date(600851475143L));
////        ArrayList<Message> messages = new ArrayList<>();
////        messages.add(message);
////        Dialog dialog = new Dialog("123", "https://www.gstatic.com/webp/gallery/1.sm.jpg", "1 dialog", users, message,messages, 1 );
////        dialogs.add(dialog);
        DialogsListAdapter adapter = new DialogsListAdapter<>(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(binding.getRoot().getContext()).load(url).into(imageView);
            }
        });
        load();
        Provider.getInstance().getDialogs(MyApplication.getInstance().getUser().getId(),
                dial-> {
                    dialogs = dial;
                    Collections.sort(dialogs, new Comparator<Dialog>() {
                        @Override
                        public int compare(Dialog o1, Dialog o2) {
                            long t1 = o1.getLastMessage().getCreatedAt().getTime();
                            long t2 = o2.getLastMessage().getCreatedAt().getTime();
                            if (t1 < t2)
                                return 1;
                            else if (t1 > t2)
                                return -1;
                            else
                                return 0;
                        }
                    });
                    adapter.setItems(dialogs);
                    show();
                    Provider.getInstance().getDialogsByUserId(MyApplication.getInstance().getUser().getId(),
                            dialogAdd -> {
                                if (!dialogs.contains(dialogAdd)) {
                                    dialogs.add(0, dialogAdd);
                                    adapter.setItems(dialogs);
                                }
                            }, dialogChange -> {
                                for (Dialog d : dialogs)
                                    if (d.getId().equals(dialogChange.getId())) {
                                        dialogs.remove(d);
                                        dialogs.add(0, dialogChange);
                                        adapter.setItems(dialogs);
                                        break;
                                    }
                            });
                });

        binding.dialogsList.setAdapter(adapter);
        adapter.setOnDialogClickListener(new DialogsListAdapter.OnDialogClickListener<Dialog>() {
            @Override
            public void onDialogClick(Dialog dialog) {
                startActivity(new Intent(getContext(), MessageActivity.class).putExtra("dialogID", dialog.getId()));
            }
        });
        return binding.getRoot();
    }

    private void load() {
        binding.loadWrap.setVisibility(View.VISIBLE);
        binding.dialogsList.setVisibility(View.GONE);
    }

    private void show() {
        binding.loadWrap.setVisibility(View.GONE);
        binding.dialogsList.setVisibility(View.VISIBLE);
    }
}
