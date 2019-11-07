package com.example.social_event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class galleryIntent extends AppCompatActivity {
   ViewPager viewPager;
   CustomSwipeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gallery_intent );
        viewPager = (ViewPager)findViewById( R.id.view_pager );
        adapter =new CustomSwipeAdapter( this );
        viewPager.setAdapter(adapter);
    }
}
