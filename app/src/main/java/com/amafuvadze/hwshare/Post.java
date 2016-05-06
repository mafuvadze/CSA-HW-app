package com.amafuvadze.hwshare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post extends AppCompatActivity implements View.OnClickListener, SaveCallback{

    EditText name, subject, teacher, comment;
    FloatingActionButton add_pic_fab;
    RecyclerView pages_list;
    TextView pages_count;
    SearchView search;
    List<Page> pics;
    List<ParseObject> pages;

    final int CAMERA_RESULT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        getSupportActionBar().setTitle("Post assignment");

        initUi();
        initListeners();
    }

    private void initUi(){
        name = (EditText) findViewById(R.id.name);
        subject = (EditText) findViewById(R.id.subject);
        teacher = (EditText) findViewById(R.id.teacher);
        comment = (EditText) findViewById(R.id.comment);
        add_pic_fab = (FloatingActionButton) findViewById(R.id.hw_add_fab);
        pages_list = (RecyclerView) findViewById(R.id.pages_list);
        pages_count = (TextView) findViewById(R.id.pages);
        pics = new ArrayList<Page>();
        search = (SearchView) findViewById(R.id.file_search);

        pages_count.setText("No pages uploaded :(");
        search.setVisibility(View.GONE);
    }

    private void initListeners(){
        add_pic_fab.setOnClickListener(this);
    }

    private void takePicture(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_RESULT){
            if(resultCode == RESULT_OK){
                Bitmap image;
                try{
                    image = (Bitmap) data.getExtras().get("data");
                    addPic(image);
                }catch(Exception ex){
                    showToast(ex.toString());
                }
            }
        }
    }

    private void addPic(Bitmap image){
        String page_num = ("page " + (pics.size() + 1)) + "";
        Page page = new Page(page_num, image, new Date());
        pics.add(page);

        search.setVisibility(View.VISIBLE);

        if(pics.size() == 1){
            pages_count.setText(pics.size() + " page");
        }else {
            pages_count.setText(pics.size() + " pages");
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        pages_list.setLayoutManager(layoutManager);
        RVAdapter adapter = new RVAdapter(pics, this);
        showToast(pics.size() + "");
        pages_list.setAdapter(adapter);
        //getParseObjectData(image, pics.size() - 1);
    }

    private ParseObject getParseObjectData(Bitmap image, int index){
        String teacher = this.teacher.getText().toString();
        String subject = this.subject.getText().toString();
        String name = this.name.getText().toString();
        String comment = this.comment.getText().toString();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytearray = stream.toByteArray();
        ParseFile file = new ParseFile(String.format("%s_%d", name, (index + 1)), bytearray);

        ParseObject post = new ParseObject(name + School.school_name);
        post.add("name", name);
        post.add("teacher", teacher);
        post.add("subject", subject);
        post.add("comment", comment);
        post.add("image", file);

        //post.saveInBackground(this);
        //showToast("saved");

        return post;
    }

    private void saveData(String name){
        /*
        //ParseObject[] assignments = new ParseObject[pics.size()];
       // for(int i = 0; i < pics.size(); i++){
        //    assignments[i] = getParseObjectData(pics.get(i).getImage(), i);
        //}

        ParseObject post = new ParseObject("Posts");
        post.add(name + School.school_name, assignments);
        post.saveInBackground(this);
        showToast("saved");
        */
    }

    @Override
    public void done(ParseException e) {
        showToast(e.getMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hw_add_fab:
                takePicture();
                break;
        }
    }

    private void showToast(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post, menu);
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

        if(id == R.id.menu_save){
            saveData(name.getText().toString());
        }

        return super.onOptionsItemSelected(item);
    }

}
