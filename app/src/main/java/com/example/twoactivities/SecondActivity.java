package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY"; // Key
    private EditText mReply; //The variable holds the EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mReply = findViewById(R.id.editText_second); // Gets reference to the EditText and assigns it to the variable
        Intent intent = getIntent(); // Gets the intent
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); // Gets the string containing the message from Intent extras
        TextView textView = findViewById(R.id.text_message); // References the message TextView
        textView.setText(message); // Sets the text of the TextView to the string from the Intent extra

    }

    public void returnReply(View view) {
        String reply = mReply.getText().toString(); // Gets the text of the EditText as a string
        Intent replyIntent = new Intent(); // A new intent for the response
        replyIntent.putExtra(EXTRA_REPLY, reply); // The reply string from the EditText is added to the new intent as an Intent extra. Extras are key/value pairs, here the key is EXTRA_REPLY, and the value is the reply.
        setResult(RESULT_OK,replyIntent); // Sets the result to RESULT_OK to indicate that the response was successful. The Activity class defines the result codes, including RESULT_OK and RESULT_CANCELLED.
        finish(); // Closes the Activity and returns to MainActivity.

    }
}
