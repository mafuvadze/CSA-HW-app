package com.amafuvadze.hwshare;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Feed extends AppCompatActivity implements View.OnClickListener, FindCallback<ParseObject> {

    FloatingActionButton add_fab;
    ListView post_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "Vyvz56RWj3h0BSdJWZdC81OsJSzp0H1NmnbGbNYK", "PDTV79SKssCQT6uM9wzRbZnTqKEuqpHc5uwdoHtB");

        add_fab = (FloatingActionButton) findViewById(R.id.add_fab);
        post_list = (ListView) findViewById(R.id.feed);

        getPostsFromSchool();
        add_fab.setOnClickListener(this);

    }

    private void getPostsFromSchool(){
        ParseQuery query = new ParseQuery(School.school_name);
        query.findInBackground(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_fab:
                Intent intent = new Intent(this, Post.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void done(List<ParseObject> objects, ParseException e) {
        LVAdapter adapter = new LVAdapter(this, R.layout.post_template, objects);
        post_list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
