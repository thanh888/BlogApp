package com.poinle.blogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.poinle.blogapp.adapters.ViewPagerAdapter;

public class OnBoardActivity extends AppCompatActivity {

    ViewPager viewPager;
    Button btnLeft, btnRight;
    ViewPagerAdapter viewPagerAdapter;
    LinearLayout dostLayout;
    TextView[] dost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_activiry);

        init();
    }

    private void init(){
        viewPager = findViewById(R.id.view_Pager);
        btnLeft = findViewById(R.id.btn_left);
        btnRight  = findViewById(R.id.btnRight);
        dostLayout = findViewById(R.id.dotsLayout);
        viewPagerAdapter = new ViewPagerAdapter(this);

        addDost(0);
        viewPager.addOnPageChangeListener(listener);
        viewPager.setAdapter(viewPagerAdapter);

        btnRight.setOnClickListener(view -> {
            if (btnRight.getText().equals("Next")){
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }else {
                startActivity(new Intent(OnBoardActivity.this, AuthActivity.class));

            }
        });
        btnLeft.setOnClickListener(view -> {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+2);
        });

    }

    private void addDost(int position){
        dostLayout.removeAllViews();
        dost = new TextView[3];
        for (int i = 0; i<dost.length; i++){
            dost[i] = new TextView(this);
            dost[i].setText(Html.fromHtml("&#8226"));
            dost[i].setTextSize(35);
            dost[i].setTextColor(getResources().getColor(R.color.colorLightGrey));
            dostLayout.addView(dost[i]);

        }

        if (dost.length>0){
            dost[position].setTextColor(getResources().getColor(R.color.colorGrey));
        }


    }
    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if (position==0){
                btnLeft.setVisibility(View.VISIBLE);
                btnLeft.setEnabled(true);
                btnRight.setText("Next");
            }
            else if(position ==1){
                btnLeft.setVisibility(View.GONE);
                btnLeft.setEnabled(false);
                btnRight.setText("Next");
            }
            else {
                btnLeft.setVisibility(View.GONE);
                btnLeft.setEnabled(false);
                btnRight.setText("Finish");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
