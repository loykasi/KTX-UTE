package com.firstapp.ql_ktx.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.firstapp.ql_ktx.model.Message;
import com.firstapp.ql_ktx.adapter.MessageAdapter;
import com.firstapp.ql_ktx.R;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.EmojiPopup;
import com.vanniktech.emoji.google.GoogleEmojiProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText messageInput;
    private MessageAdapter messageAdapter;
    private List<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_chat);
        EmojiManager.install(new GoogleEmojiProvider());

        recyclerView = findViewById(R.id.recyclerView);
        messageInput = findViewById(R.id.editTextMessage);
        FrameLayout emojiButton = findViewById(R.id.buttonEmoji);
        FrameLayout sendButton = findViewById(R.id.buttonSend);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, messages);
        recyclerView.setAdapter(messageAdapter);

        EmojiPopup emojiPopup = EmojiPopup.Builder.fromRootView(
                findViewById(R.id.root)
        ).build(messageInput);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        emojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emojiPopup.toggle();
            }
        });

        

        generateFakeChat();
    }

    private void generateFakeChat() {
        addMessage("Sinh viên B", "Praesent lacinia.", "06:00");
        addMessage("Sinh viên B", "Suspendisse ultrices et nunc at.", "06:00");
        addMessage("Sinh viên B", "Donec at maximus.", "06:01");
        addMessage("Sinh viên C", "Nam iaculis libero sagittis.", "07:15");
        addMessage("Sinh viên C", "Praesent non.", "07:16");
    }

    private void sendMessage() {
        String messageText = messageInput.getText().toString().trim();

        if (messageText.equals("")) {
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String time = sdf.format(date);
        String username = "Sinh viên A";

        addMessage(username, messageText, time);
//        messageAdapter.notifyDataSetChanged();

        messageInput.setText("");
    }

    private void addMessage(String username, String message, String time) {
        messages.add(new Message(username, message, time));
        messageAdapter.notifyItemInserted(messages.size() - 1);
        recyclerView.scrollToPosition(messages.size() - 1);
    }
}