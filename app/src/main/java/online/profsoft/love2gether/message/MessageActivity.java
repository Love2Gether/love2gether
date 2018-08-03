package online.profsoft.love2gether.message;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import online.profsoft.love2gether.MyApplication;
import online.profsoft.love2gether.R;
import online.profsoft.love2gether.database.IDrandom;
import online.profsoft.love2gether.database.Provider;
import online.profsoft.love2gether.databinding.ActivityMessageBinding;
import online.profsoft.love2gether.models.Dialog;
import online.profsoft.love2gether.models.Message;
import online.profsoft.love2gether.models.User;

public class MessageActivity extends AppCompatActivity {

    private ActivityMessageBinding binding;
    private MessagesListAdapter<Message> adapter;
    private User user;
    private String id;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_message);
        adapter = new MessagesListAdapter<>(MyApplication.getInstance().getUser().getId(), null);
        if (getIntent().hasExtra("userID")) {
            id = getIntent().getStringExtra("userID");
            Provider.getInstance().getUser(id, user -> {
                this.user = user;
            });
            Provider.getInstance().getDialog(id, MyApplication.getInstance().getUser().getId(),
                    dial -> {
                        if (dial != null) {
                            adapter.addToEnd(dialog.getMessages(), false);
                            binding.messagesList.setAdapter(adapter);
                        }
                    });
        } else {
            id = getIntent().getStringExtra("dialogID");
            Provider.getInstance().getDialogID(id,
                    dial -> {
                        dialog = dial;
                        adapter.addToEnd(dialog.getMessages(), false);
                        binding.messagesList.setAdapter(adapter);
                    });
        }
        binding.input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                Message message = new Message(IDrandom.generateStringID(), input.toString(), MyApplication.getInstance().getUser(), new Date(Calendar.getInstance().getTimeInMillis()));
                adapter.addToStart(message, true);
                if (dialog == null) {
                    ArrayList<User> users = new ArrayList<>();
                    users.add(user);
                    users.add(MyApplication.getInstance().getUser());
                    ArrayList<Message> messages = new ArrayList<>();
                    messages.add(message);
                    dialog = new Dialog(IDrandom.generateStringID(), user.getAvatar(), user.getName(), users, message, messages, 1, users.get(0).getId(), users.get(1).getId());
                    Provider.getInstance().setDialog(dialog);
                } else
                    Provider.getInstance().setDialog(dialog);
                return true;
            }
        });
    }
}