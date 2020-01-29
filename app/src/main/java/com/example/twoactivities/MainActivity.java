package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE"; // Constant that defines the key for the Intent extra
    private EditText mMessageEditText; // Holds the EditText
    public static final int TEXT_REQUEST = 1; // Defines the key
    private TextView mReplyHeadTextView; // Holds the reply header and reply
    private TextView mReplyTextView; // Holds the reply

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "-------"); // Log the start of the onCreate() method.
        Log.d(LOG_TAG, "onCreate");
        mMessageEditText = findViewById(R.id.editText_main); // References the EditText
        mReplyHeadTextView = findViewById(R.id.text_header_reply); // References the reply header
        mReplyTextView = findViewById(R.id.text_message_reply); // References the reply message

        // Restore the state.
        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible) {
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }


    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class); // Creates a new intent with this as the context for the SecondActivity class to receive it
        String message = mMessageEditText.getText().toString(); // Gets text from EditText
        intent.putExtra(EXTRA_MESSAGE, message); // Puts extra to the intent. Constant key and String value
        startActivityForResult(intent, TEXT_REQUEST); // Sends the Intent to launch SecondActivity. TEXT_REQUEST is the key
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { // resultCode usually returns RESULT_OK or RESULT_CANCELED. Intent data contains the data returned
        super.onActivityResult(requestCode, resultCode, data); // Calls super.onActivityResult()
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY); // Gets the Intent extra from the response. EXTRA_REPLY is the key.
                mReplyHeadTextView.setVisibility(View.VISIBLE); // Sets reply header to be visible
                mReplyTextView.setText(reply); // Sets the text to reply
                mReplyTextView.setVisibility(View.VISIBLE); // Sets the text visible
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",mReplyTextView.getText().toString());
        }
    }
}
