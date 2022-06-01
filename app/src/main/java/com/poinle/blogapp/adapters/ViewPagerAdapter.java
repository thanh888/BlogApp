package com.poinle.blogapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.poinle.blogapp.R;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    private int images[]={
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
    };

    private String titles[]={
            "Learn",
            "Create",
            "Enjoy",
    };
    private String desc[]={
            "lorem ipsum dolor contraint spaces dolor ipsum loremters termainal lorem ispsum contanirnts.",
            "lorem ipsum dolor contraint spaces dolor ipsum loremters termainal lorem ispsum contanirnts.",
            "lorem ipsum dolor contraint spaces dolor ipsum loremters termainal lorem ipsum contanirnts.",
    };

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == (LinearLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.item_row, container, false);


        ImageView imageView = v.findViewById(R.id.image_ViewPager);
        TextView txtTitle = v.findViewById(R.id.txt_Title);
        TextView txtDesc = v.findViewById(R.id.txt_DescViewPager);

        imageView.setImageResource(images[position]);
        txtTitle.setText(titles[position]);
        txtDesc.setText(desc[position]);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
