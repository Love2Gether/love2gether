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
import online.profsoft.love2gether.database.ReverseDialog;
import online.profsoft.love2gether.databinding.ActivityMessageBinding;
import online.profsoft.love2gether.models.Dialog;
import online.profsoft.love2gether.models.Message;
import online.profsoft.love2gether.models.MyDialog;
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
        binding.messagesList.setAdapter(adapter);
        if (getIntent().hasExtra("userID")) {
            id = getIntent().getStringExtra("userID");
            Provider.getInstance().getUser(id, user -> {
                this.user = user;
            });
            Provider.getInstance().getDialog(id.compareTo(MyApplication.getInstance().getUser().getId()) <= 0 ? id + MyApplication.getInstance().getUser().getId()
                            :MyApplication.getInstance().getUser().getId() + id,
                    dial -> {
                        if (dial != null ) {
                            dialog = ReverseDialog.changeMyDialogInDialog(dial);
                            adapter.addToEnd(dialog.getMessages(), true);
                        }
                    });
        } else {
            id = getIntent().getStringExtra("dialogID");
            Provider.getInstance().getDialogID(id,
                    myDialog -> {
                        dialog = ReverseDialog.changeMyDialogInDialog(myDialog);
                        adapter.addToEnd(dialog.getMessages(), true);
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

                    dialog = new Dialog(user.getId().compareTo(MyApplication.getInstance().getUser().getId()) <= 0 ? user.getId() + MyApplication.getInstance().getUser().getId()
                            :MyApplication.getInstance().getUser().getId() + user.getId(),
                            user.getAvatar(), user.getName(), users, message, messages, 1);
                    Provider.getInstance().setDialog(ReverseDialog.changeDialogInMyDialog(dialog));
                } else
                    dialog.setLastMessage(message);
                    dialog.getMessages().add(message);
                    Provider.getInstance().setDialog(ReverseDialog.changeDialogInMyDialog(dialog));
                return true;
            }
        });
    }
}