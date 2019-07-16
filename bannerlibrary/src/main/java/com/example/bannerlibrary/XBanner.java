package com.example.bannerlibrary;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import java.util.List;


public class XBanner extends ConstraintLayout {
    private ViewPager mViewpager;
    Jbanner jbanner;
    private ImageView mImageiew;

    public void setJbanner(Jbanner jbanner) {
        this.jbanner = jbanner;
    }

    private TextView mTextview;
    private List<? extends Object> mBanners;

    public XBanner(Context context) {
        super(context);
        initView();
    }

    public XBanner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public XBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    int id = 1;

    private void initView() {
        mViewpager = new ViewPager(getContext());


        ConstraintSet constraintSet = new ConstraintSet();


        mViewpager.setId(id++);
        mViewpager.setBackgroundColor(Color.RED);

        constraintSet.connect(mViewpager.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraintSet.connect(mViewpager.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(mViewpager.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        constraintSet.connect(mViewpager.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        addView(mViewpager);

        mImageiew = new ImageView(getContext());
        mImageiew.setId(id++);
        mImageiew.setBackgroundColor(Color.parseColor("#20000000"));

        constraintSet.connect(mImageiew.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraintSet.connect(mImageiew.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.constrainWidth(mImageiew.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mImageiew.getId(), SystemFacade.dp2px(getContext(),33));
        addView(mImageiew);

        mTextview = new TextView(getContext());
        mTextview.setId(id++);
        mTextview.setText("haha");
        constraintSet.connect(mTextview.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(mTextview.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSet.connect(mTextview.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.constrainWidth(mTextview.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mTextview.getId(), ConstraintSet.WRAP_CONTENT);
        addView(mTextview);
    }

    public class BannerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return mBanners == null ? 0 : Integer.MAX_VALUE;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

//            jbanner.filData(container, );
//            Glide.with(container).load(mBanners.get(position % mBanners.size()).getImgUrl()).into(imageView);
//            container.addView(v);
            return null;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }


}
