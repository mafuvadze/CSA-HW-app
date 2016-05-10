package com.amafuvadze.hwshare;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Anesu on 5/9/2016.
 */
public class LVAdapter extends ArrayAdapter {
    Context context;
    List<ParseObject> result;
    public LVAdapter(Context context, int resource, List<ParseObject> result) {
        super(context, resource);
        this.context = context;
        this.result = result;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
           convertView = LayoutInflater.from(context).inflate(R.layout.post_template, parent, false);
        }

        ParseObject post = result.get(position);

        TextView name, subject, date_posted, teacher;
        name = (TextView) convertView.findViewById(R.id.hw_name);
        subject = (TextView) convertView.findViewById(R.id.hw_subject);
        teacher = (TextView) convertView.findViewById(R.id.hw_teacher);
        date_posted = (TextView) convertView.findViewById(R.id.day_posted);

        try {
            name.setText(post.getJSONArray("name").getString(0));
            subject.setText(post.getJSONArray("subject").getString(0));
            teacher.setText(post.getJSONArray("teacher").getString(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        Date date = post.getDate("date");
        Date today = new Date();
        DateCalc.TimeStamp stamp = DateCalc.getTimeStamp(DateCalc.daysBetween(date,today));
        if(stamp.type == "Today"){
            date_posted.setText(stamp.type);
        }else{
            date_posted.setText(stamp.num + " " + stamp.type);
        }
        */

        return convertView;
    }


}
