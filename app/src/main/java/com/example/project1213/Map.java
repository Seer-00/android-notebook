package com.example.project1213;


import android.widget.ImageView;
import android.widget.TextView;

public class Map {
    public int image;
    public String title;
    public String info;
    public Map(int image,String title,String info)
    {
        this.image=image;
        this.title=title;
        this.info=info;
    }
    public int getImageId(){
        return image;
    }
    public String getTitle(){
        return title;
    }
    public String getInfo(){
        return info;
    }
}
