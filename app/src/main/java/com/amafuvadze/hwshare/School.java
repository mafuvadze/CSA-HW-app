package com.amafuvadze.hwshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class School extends AppCompatActivity implements View.OnClickListener{

    public static String school_name;
    AutoCompleteTextView school_search;
    Button next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        school_search = (AutoCompleteTextView) findViewById(R.id.schoolsearch);
        next_btn = (Button) findViewById(R.id.start_btn);

        next_btn.setOnClickListener(this);
        try {
            initSchoollist();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initSchoollist() throws IOException {
        List<String> school_list = new ArrayList<>();
        Scanner in = new Scanner(getAssets().open("KC_schools.txt"));
        while(in.hasNext()){
            school_list.add(in.nextLine());
        }

        Toast.makeText(getBaseContext(), school_list.size() + "", Toast.LENGTH_LONG).show();
        school_search.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getArray(school_list)));
    }

    private String[] getArray(List<String> list){
        String[] arr = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            arr[i] = list.get(i);
        }

        return arr;
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
