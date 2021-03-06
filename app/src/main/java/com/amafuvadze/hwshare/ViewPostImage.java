package com.amafuvadze.hwshare;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class ViewPostImage extends AppCompatActivity {

    ViewPager image_slide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        image_slide = (ViewPager) findViewById(R.id.picture_slide);
        VPAdapter adapter = new VPAdapter(Post.pics, this);
        image_slide.setAdapter(adapter);

    }
}
