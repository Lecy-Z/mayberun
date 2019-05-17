package com.finalexam.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FrameLayout Fragment_context;
    private TextView txt_count;
    private TextView txt_run;
    private TextView txt_user;
    private LinearLayout tab_menu;
    private Fgone fgone;
    private Fgtwo fgtwo;
    private Fgthree fgthree;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView();
    }
    private void initView(){

        txt_count=(TextView)findViewById(R.id.txt_count);
        txt_run=(TextView)findViewById(R.id.txt_run);
        txt_user=(TextView)findViewById(R.id.txt_user);
        Fragment_context=(FrameLayout)findViewById(R.id.fragment_context);
        tab_menu=(LinearLayout)findViewById(R.id.tab_menu);
        txt_count.setOnClickListener(this);
        txt_run.setOnClickListener(this);
        txt_user.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        switch (v.getId()){
            case R.id.txt_count:
                setSelectStatus(0);
                if (fgone == null) {
                    fgone = new Fgone();
                    fragmentTransaction.add(R.id.fragment_context, fgone);
                } else {
                    fragmentTransaction.show(fgone);
                }
                break;
            case R.id.txt_run:
                setSelectStatus(1);
                if (fgtwo == null) {
                    fgtwo = new Fgtwo();
                    fragmentTransaction.add(R.id.fragment_context, fgtwo);
                } else {
                    fragmentTransaction.show(fgtwo);
                }
                break;
            case R.id.txt_user:
                setSelectStatus(2);
                if (fgthree == null) {
                    fgthree = new Fgthree();
                    fragmentTransaction.add(R.id.fragment_context, fgthree);
                } else {
                    fragmentTransaction.show(fgthree);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }
    private void setSelectStatus(int index) {
        switch (index){
            case 0:
                txt_count.setBackgroundColor(Color.parseColor("#3232CD"));
                txt_run.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txt_user.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 1:
                txt_count.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txt_run.setBackgroundColor(Color.parseColor("#3232CD"));
                txt_user.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 2:
                txt_count.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txt_run.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txt_user.setBackgroundColor(Color.parseColor("#3232CD"));
                break;
        }
    }
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (fgone != null) {
            fragmentTransaction.hide(fgone);
        }
        if (fgtwo != null) {
            fragmentTransaction.hide(fgtwo);
        }
        if (fgthree != null) {
            fragmentTransaction.hide(fgthree);
        }
    }


}
