package online.profsoft.love2gether.message;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.Calendar;
import java.util.Date;

import online.profsoft.love2gether.R;
import online.profsoft.love2gether.databinding.ActivityMessageBinding;
import online.profsoft.love2gether.models.Dialog;
import online.profsoft.love2gether.models.Message;

public class MessageActivity extends AppCompatActivity {
 private ActivityMessageBinding binding;
    private String senderId;
    private MessagesListAdapter<Message> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_message);
        final Dialog dialog = getIntent().getParcelableExtra("dialog");
        adapter = new MessagesListAdapter<>(dialog.getId(), null);
        adapter.addToEnd(dialog.getMessages(), false);
        binding.messagesList.setAdapter(adapter);
        binding.input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                Message message = new Message("4535", input.toString(), dialog.getUsers().get(0), new Date(Calendar.getInstance().getTimeInMillis()));
                adapter.addToStart(message, true);
                dialog.getMessages().add(message);
                return true;
            }
        });
    }
}
