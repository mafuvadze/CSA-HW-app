package com.amafuvadze.hwshare;

import android.graphics.Bitmap;
import android.text.style.UpdateAppearance;

import java.util.Date;

/**
 * Created by Anesu on 5/5/2016.
 */
public class Page {
    private String assignment_name;
    private Bitmap image;
    private Date date_posted;

    public Page(String assignment_name, Bitmap image, Date date_posted){
        this.assignment_name = assignment_name;
        this.image = image;
        this.date_posted = date_posted;
    }

    public String getAssignment_name() {
        return assignment_name;
    }

    public void setAssignment_name(String assignment_name) {
        this.assignment_name = assignment_name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Date getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(Date date_posted) {
        this.date_posted = date_posted;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Page){
            if(((Page) o).getImage() == this.getImage()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getImage().hashCode();
    }
}
