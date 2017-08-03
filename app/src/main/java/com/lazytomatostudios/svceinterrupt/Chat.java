package com.lazytomatostudios.svceinterrupt;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.RequestExtras;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIContext;
import ai.api.model.AIError;
import ai.api.model.AIEvent;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

import com.google.gson.JsonElement;

/**
 * A simple {@link Fragment} subclass.
 */

public class Chat extends Fragment implements MyInterface, AIListener {

    TextView resultTextView;

    static AIService aiService;
    ChatView chatView;


    public Chat() {
        // Required empty public consructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Debug", "TESTING");

        final AIConfiguration aiConfig = new AIConfiguration("324d16eb787f4550a42a8c728b1dd3e1",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiService = AIService.getService(getContext(), aiConfig);
        aiService.setListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        chatView = (ChatView) view.findViewById(R.id.chat_view);

        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener(){
            @Override
            public boolean sendMessage(ChatMessage chatMessage){
                Log.d("TEST", String.valueOf(chatMessage.getTimestamp()));
                new Fetch().execute(chatMessage.getMessage());
                return true;
            }
        });

        return view;
    }

    @Override
    public void fragmentNowVisible() {
        Log.d("Debug", "Chat Screen visible");

    }

    public void onResult(final AIResponse response) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Result result = response.getResult();

        chatView.addMessage(new ChatMessage(result.getFulfillment().getSpeech(), timestamp.getTime(), ChatMessage.Type.RECEIVED));
    }

    public class Fetch extends AsyncTask<String, Void, AIResponse> {

        private AIError aiError;

        @Override
        protected AIResponse doInBackground(final String... params) {
            final AIRequest request = new AIRequest();
            String query = params[0];
            request.setQuery(query);

            try {
                return Chat.aiService.textRequest(request);
            } catch (final AIServiceException e) {
                aiError = new AIError(e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(final AIResponse response) {
            if (response != null) {
                onResult(response);
            } else {
                onError(aiError);
            }
        }
    }

    @Override
    public void onError(final AIError error) {
        resultTextView.setText(error.toString());
    }

    @Override
    public void onListeningStarted() {}

    @Override
    public void onListeningCanceled() {}

    @Override
    public void onListeningFinished() {}

    @Override
    public void onAudioLevel(final float level) {}

}