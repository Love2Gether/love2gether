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
import online.profsoft.love2gether.database.ReverseDialog;
import online.profsoft.love2gether.databinding.FragmentDialogBinding;
import online.profsoft.love2gether.models.Dialog;
import online.profsoft.love2gether.models.Message;
import online.profsoft.love2gether.models.MyDialog;
import online.profsoft.love2gether.models.User;

public class DialogFragment extends Fragment {

private FragmentDialogBinding binding;
private ArrayList<Dialog> dialogs = new ArrayList<>();
private DialogsListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog, container, false);
        adapter = new DialogsListAdapter<>(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(binding.getRoot().getContext()).load(url).into(imageView);
            }
        });
        binding.dialogsList.setAdapter(adapter);
        load();
        Provider.getInstance().getDialogs(MyApplication.getInstance().getUser().getId(),
                dial-> {

            for (MyDialog myDialog : dial) {
                Dialog dialog = ReverseDialog.changeMyDialogInDialog(myDialog);
                if (!dialogs.contains(dialog))
                dialogs.add(dialog);
            }

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
                            myDialogAdd -> {
                                Dialog dialog = ReverseDialog.changeMyDialogInDialog(myDialogAdd);
                                if (!dialogs.contains(dialog)) {
                                    dialogs.add(0, dialog);
                                    adapter.setItems(dialogs);
                                }
                            }, MyDialogChange -> {
                                for (Dialog d : dialogs)
                                    if (d.getId().equals(MyDialogChange.getId())) {
                                        Dialog dialog = ReverseDialog.changeMyDialogInDialog(MyDialogChange);
                                        dialogs.remove(d);
                                        dialogs.add(0, dialog);
                                        adapter.setItems(dialogs);
                                        break;
                                    }
                            });
                });

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
