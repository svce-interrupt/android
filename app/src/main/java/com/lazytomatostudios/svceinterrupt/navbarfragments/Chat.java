package com.lazytomatostudios.svceinterrupt.navbarfragments;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Timestamp;


import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

import com.lazytomatostudios.svceinterrupt.dashactivities.EventActivity;
import com.lazytomatostudios.svceinterrupt.dashactivities.TransportActivity;
import com.lazytomatostudios.svceinterrupt.interfaces.MyInterface;
import com.lazytomatostudios.svceinterrupt.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class Chat extends Fragment implements MyInterface, AIListener {

    static AIService aiService;
    ChatView chatView;
    String mapsUri = "http://maps.google.com/maps?daddr=12.983120,79.971160 (Interrupt)";


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

        chatView = view.findViewById(R.id.chat_view);

        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener(){
            @Override
            public boolean sendMessage(ChatMessage chatMessage){
                Log.d("TEST", String.valueOf(chatMessage.getTimestamp()));
                new Fetch().execute(chatMessage.getMessage());
                return true;
            }
        });

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        chatView.addMessage(new ChatMessage("Hello! I am qT3.14.", timestamp.getTime(), ChatMessage.Type.RECEIVED));
        chatView.addMessage(new ChatMessage("How can I help you?", timestamp.getTime(), ChatMessage.Type.RECEIVED));

        Log.d("TEST", "fragment chat");

        return view;
    }

    @Override
    public void fragmentNowVisible() {
        Log.d("Debug", "Chat Screen visible");

    }

    private class Fetch extends AsyncTask<String, Void, AIResponse> {

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

    public void onResult(final AIResponse response) {
        Intent intent;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Result result = response.getResult();

        chatView.addMessage(new ChatMessage(result.getFulfillment().getSpeech(), timestamp.getTime(), ChatMessage.Type.RECEIVED));

        Log.d("debug", "" + result.getAction());
        Log.d("debug", "" + result.getStringParameter("event"));

        switch (result.getAction()) {
            case "maps":
                showMap(Uri.parse(mapsUri));
                break;
            case "bus":
                intent = new Intent(getActivity(), TransportActivity.class);
                startActivity(intent);
                break;
            case "events":
                intent = new Intent(getActivity(), EventActivity.class);
                String extra = result.getStringParameter("event");
                Log.d("TAG", extra);
                if(extra != null)
                    intent.putExtra("event", extra);
                startActivity(intent);
                break;
            default:
                Log.d("Action", "NULL");
                break;
        }
    }

    @Override
    public void onError(final AIError error) {
        Log.d("Error", error.toString());
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
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