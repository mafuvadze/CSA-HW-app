package com.amafuvadze.hwshare;

/**
 * Created by Anesu on 5/6/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Angellar Manguvo on 10/9/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    List<Page> pages;
    Context context;
    PersonViewHolder holder;

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener
    {

        ImageView image;
        FrameLayout wrapper;
        Context context;
        View view;
        Bitmap img;

        PersonViewHolder(View itemView, Context context) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.page_image);
            wrapper = (FrameLayout) itemView.findViewById(R.id.frame);

            this.context = context;

            image.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            view = LayoutInflater.from(context).inflate(R.layout.quick_options_pic, null, false);
            wrapper.addView(view);

            img =  ((BitmapDrawable)image.getDrawable()).getBitmap();
            image.setImageBitmap(ImageEffects.blurRenderScript(context, img, 20));

            RelativeLayout open, back, delete;
            open = (RelativeLayout) view.findViewById(R.id.open_opt);
            back = (RelativeLayout) view.findViewById(R.id.back_opt);
            delete = (RelativeLayout) view.findViewById(R.id.delete_opt);

            open.setOnClickListener(this);
            back.setOnClickListener(this);
            delete.setOnClickListener(this);

            return true;
        }

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.open_opt:
                    Intent intent = new Intent(context, ViewPostImage.class);
                   context.startActivity(intent);
                    break;
                case R.id.delete_opt:
                    break;
                case R.id.back_opt:
                    wrapper.removeView(view);
                    image.setImageBitmap(img);
                    break;
            }
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
        PersonViewHolder pvh = new PersonViewHolder(view, context);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RVAdapter.PersonViewHolder holder, int position)
    {
        Page page = pages.get(position);
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
