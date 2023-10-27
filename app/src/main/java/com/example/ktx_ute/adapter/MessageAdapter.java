package com.example.ktx_ute.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktx_ute.model.Message;
import com.example.ktx_ute.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int TYPE_LEFT_START = 0;
    public static final int TYPE_LEFT = 1;
    public static final int TYPE_RIGHT = 2;

    private Context context;
    private List<Message> messages;
    private Message message;
    private String fakeUser = "Sinh viên A";

    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType)
        {
            case TYPE_LEFT_START:
                view = LayoutInflater.from(context).inflate(R.layout.item_message_start_left, parent, false);
                return new ViewHolder(view, true);
            case TYPE_LEFT:
                view = LayoutInflater.from(context).inflate(R.layout.item_message_left, parent, false);
                return new ViewHolder(view, false);
            case TYPE_RIGHT:
                view = LayoutInflater.from(context).inflate(R.layout.item_message_right, parent, false);
                return new ViewHolder(view, false);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        message = messages.get(position);
        if (message == null)
            return;

        holder.setUsername(message.getUsername());
        holder.textViewMessage.setText(message.getMessage());
        holder.textViewTime.setText(message.getTime());
    }

    @Override
    public int getItemCount() {
        if (messages != null)
            return messages.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        message = messages.get(position);
        Log.d("My Debug", "" + position);
        if (message.getUsername().equals(fakeUser))
            return 2;
        if (position == 0)
            return 0;
        if (message.getUsername().equals(messages.get(position-1).getUsername()))
            return 1;
        return 0;
//        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewUsername;
        public TextView textViewMessage;
        public TextView textViewTime;
        public boolean hasUsername;

        public ViewHolder(@NonNull View itemView, boolean hasUsername) {
            super(itemView);
            this.hasUsername = hasUsername;
            if (hasUsername) {
                this.textViewUsername = itemView.findViewById(R.id.textView_User);
            }
            this.textViewMessage = itemView.findViewById(R.id.textView_Message);
            this.textViewTime = itemView.findViewById(R.id.textView_Time);
        }

        public void setUsername(String username) {
            if (hasUsername) {
                textViewUsername.setText(username);
            }
        }

    }

}
