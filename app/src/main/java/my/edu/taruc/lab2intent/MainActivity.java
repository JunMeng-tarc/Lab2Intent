package my.edu.taruc.lab2intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_MESSAGE = "my.edu.taruc.lab2intent.message";
    private static final int REQUEST_REPLY_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view){
        EditText editTextMessage;
        //Link to UI
        editTextMessage = findViewById(R.id.editTextMessgae);
        //Input validation here
        if(TextUtils.isEmpty(editTextMessage.getText())){
            editTextMessage.setError(getString(R.string.error_message));
        }

        Intent intent = new Intent(this, SecondActivity.class);
        String stringMessage;
        stringMessage = editTextMessage.getText().toString();

        //Put extra things into the intent
        intent.putExtra(TAG_MESSAGE,stringMessage);

        // Expected to return something
        startActivityForResult(intent, REQUEST_REPLY_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_REPLY_CODE){
            if(resultCode == RESULT_OK){

                //Link the UI
                TextView textViewReply;
                textViewReply = findViewById(R.id.editTextReply);

                String stringReply = data.getStringExtra(SecondActivity.TAG_REPLY);
                textViewReply.setText(stringReply);
            }
        }
    }
}
