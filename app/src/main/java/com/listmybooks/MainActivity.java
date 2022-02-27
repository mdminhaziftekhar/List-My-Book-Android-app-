package com.listmybooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.listmybooks.Fragments.FirstFragment;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ImageButton btn;
    public static String text;

    //url
    public static String Request_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        btn = (ImageButton) findViewById(R.id.imageButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Request_URL = "https://www.googleapis.com/books/v1/volumes?q=";
                text = editText.getText().toString();
                Request_URL+=text;

                FirstFragment firstFragment = new FirstFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linear, firstFragment);
                transaction.commit();
            }
        });

    }
}