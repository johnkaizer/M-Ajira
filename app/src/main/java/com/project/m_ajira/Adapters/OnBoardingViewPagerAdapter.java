package com.project.m_ajira.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.project.m_ajira.R;


public class OnBoardingViewPagerAdapter extends PagerAdapter {

    Context context;
    int images [] = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
    };
    int headings []={
            R.string.heading1,
            R.string.heading2,
            R.string.heading3,
            R.string.heading4

    };
    int descriptions []={
            R.string.desc1,
            R.string.desc2,
            R.string.desc3,
            R.string.desc4

    };

    public OnBoardingViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboarding_layout,container,false);
        ImageView image = (ImageView) view.findViewById(R.id.onboard_image);
        TextView  heading = (TextView) view.findViewById(R.id.heading_text);
        TextView  description = (TextView) view.findViewById(R.id.description_text);

        image.setImageResource(images[position]);
        heading.setText(headings[position]);
        description.setText(descriptions[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);

    }
}
