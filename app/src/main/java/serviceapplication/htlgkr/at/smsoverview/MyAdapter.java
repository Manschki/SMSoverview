package serviceapplication.htlgkr.at.smsoverview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private int layoutId;
    private LayoutInflater inflater;
    private List<Message> messages;

    public MyAdapter(Context ctx, int layoutId, List<Message> messages) {
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Message message = messages.get(position);

        View listItem = (view == null) ? inflater.inflate(this.layoutId, null) : view;
        ((TextView) listItem.findViewById(R.id.txt_number)).setText(message.getNumber());
        ((TextView) listItem.findViewById(R.id.txt_time)).setText(message.getTime());
        ((TextView) listItem.findViewById(R.id.txt_msg)).setText(message.getMessage());

        return listItem;
    }
}
