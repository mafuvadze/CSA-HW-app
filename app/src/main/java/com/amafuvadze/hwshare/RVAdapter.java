package com.amafuvadze.hwshare;

/**
 * Created by Anesu on 5/6/2016.
 */

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Angellar Manguvo on 10/9/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    List<Page> pages;
    Context context;

    public static class PersonViewHolder extends RecyclerView.ViewHolder
    {

        TextView name;
        ImageView image;

        PersonViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.page_name);
            image = (ImageView) itemView.findViewById(R.id.page_image);
        }
    }

    RVAdapter(List<Page> pages, Context context)
    {
        this.pages = pages;
        this.context = context;
    }


    @Override
    public RVAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.assignments_enter_template, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RVAdapter.PersonViewHolder holder, int position)
    {
        Page page = pages.get(position);
        holder.name.setText(page.getAssignment_name());
        holder.image.setImageBitmap(page.getImage());

    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
