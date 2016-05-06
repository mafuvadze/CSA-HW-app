package com.amafuvadze.hwshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class School extends AppCompatActivity implements View.OnClickListener{

    public static String school_name;
    EditText school_search;
    Button next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        school_search = (EditText) findViewById(R.id.schoolsearch);
        next_btn = (Button) findViewById(R.id.start_btn);

        next_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_btn:
                school_name = school_search.getText().toString();
                Intent intent = new Intent(getBaseContext(), Feed.class);
                startActivity(intent);
        }
    }
}
