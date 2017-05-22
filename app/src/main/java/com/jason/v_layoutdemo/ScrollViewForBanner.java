package com.jason.v_layoutdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jason_syf on 2017/5/22.
 * Email: jason_sunyf@163.com
 */
public  abstract class ScrollViewForBanner {
    private int i = 0;
    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (i == banners - 1) {
                i = 0;
            } else {
                i++;
            }
            mBanner.setCurrentItem(i);
            DefaultTips(i);
            return true;
        }
    });

    //带网络请求的banner
    public ScrollViewForBanner(ViewPager banner,
                               List<String> urls,
                               LinearLayout linearLayout,
                               Context context,
                               boolean isXy) {
        mBanner = banner;
        mLinearLayout = linearLayout;
        List<View> views = new ArrayList<>();
        for (int j = 0; j < urls.size(); j++) {
            ImageView img = new ImageView(context);
            if (!isXy) {
                img.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            Glide.with(context)
                    .load(urls.get(j))
                    .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.DATA))
                    .into(img);
            views.add(img);
            int finalJ = j;
            img.setOnClickListener(v -> imagesClick(finalJ));
        }
        initBanner(context,views);
    }

    private ImageView[] tips;
    private int banners;
    private ViewPager mBanner;
    private LinearLayout mLinearLayout;

    public ScrollViewForBanner(Context context,
                               List<Integer> images,
                               LinearLayout linearLayout,
                               ViewPager viewPager) {
        mBanner = viewPager;
        mLinearLayout = linearLayout;
        List<View> views = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            ImageView img = new ImageView(context);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setImageResource(images.get(i));
            final int finalI = i;
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imagesClick(finalI);
                }
            });
            views.add(img);
        }
        initBanner(context, views);
    }

    private void initBanner(Context context, List<View> views) {
        mBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                DefaultTips(position);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //设置点点
        banners = views.size();
        tips = new ImageView[banners];
        for (int i = 0; i < banners; i++) {
            ImageView img = new ImageView(context);
            img.setLayoutParams(new LinearLayout.LayoutParams(6, 6));
            tips[i] = img;
            if (i == 0) {
                img.setBackgroundResource(R.drawable.tips_selector);
            } else {
                img.setBackgroundResource(R.drawable.tips_nomal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 5;
            params.rightMargin = 5;
            params.height = 20;
            params.width = 20;
            mLinearLayout.addView(img, params);
        }
        ViewPagerAdapterForView mPagerAdapter = new ViewPagerAdapterForView(views);
        mBanner.setAdapter(mPagerAdapter);
    }

    public void adder() {
        addBannerTimer();
    }

    //banner定时器
    private void addBannerTimer() {
        Timer mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }
        };
        mTimer.schedule(mTimerTask, 1000, 3000);
    }

    private void DefaultTips(int position) {
        for (int i = 0; i < banners; i++) {
            if (i == position) {
                ImageView img = tips[i];
                img.setBackgroundResource(R.drawable.tips_selector);
            } else {
                ImageView img = tips[i];
                img.setBackgroundResource(R.drawable.tips_nomal);
            }
        }
    }


    public abstract void imagesClick(int position);
}
