package com.jason.tndgdemo.VlayoutUtils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jason.tndgdemo.ViewPagerAdapterForView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jason_syf on 2017/5/22.
 * Email: jason_sunyf@163.com
 */

public class VLayoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SparseArray<View> mSparseArray = new SparseArray<>();

    private View mItemView;

    private MyItemClickListener mOnClickBack;

    public VLayoutViewHolder(View itemView, MyItemClickListener onClickBack) {
        super(itemView);
        mItemView = itemView;
        if (mItemView != null) {
            mItemView.setTag(this);
        }
        mOnClickBack = onClickBack;
    }

    private <T extends View> T findView(int viewId) {
        View view = null;
        if (mItemView != null) {
            view = mItemView.findViewById(viewId);
        }
        if (view != null) {
            mSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public <T extends View> T getView(int viewId) {
        View view = mSparseArray.get(viewId);
        if (view != null) {
            return (T) view;
        }
        view = findView(viewId);
        return (T) view;
    }

    public VLayoutViewHolder setText(int viewId, CharSequence sequence) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(sequence);
        }
        return this;
    }

    public VLayoutViewHolder setImageRes(int viewId, int imgResId) {
        ImageView view = getView(viewId);
        if (view != null) {
            view.setImageResource(imgResId);
        }
        return this;
    }

    public VLayoutViewHolder setSwitchState(int viewId, boolean isChecked) {
        Switch sw = getView(viewId);
        if (sw != null) {
            sw.setChecked(!isChecked);
        }
        return this;
    }


    public VLayoutViewHolder setOnClickListener(int viewId) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(this);
        }
        return this;
    }

    public VLayoutViewHolder setLineLayoutListener(int viewId) {
        LinearLayout layout = getView(viewId);
        if (layout != null) {
            layout.setOnClickListener(this);
        }
        return this;
    }

    @Override
    public void onClick(View v) {
        if (mOnClickBack != null) {
            mOnClickBack.onItemClick(v,getPosition(),this);
        }
    }

    //给一个imageView设置bitMap
    public VLayoutViewHolder setImageBipmap(Bitmap bitmap, int viewId) {
        if (bitmap == null) return this;
        View view = getView(viewId);
        if (view != null && view instanceof ImageView) {
            ((ImageView) view).setImageBitmap(bitmap);
        }
        return this;
    }

    public VLayoutViewHolder setHint(int viewId, String var) {
        EditText editText = getView(viewId);
        if (editText != null) {
            editText.setHint(var);
        }
        return this;
    }

    //给一个imageView设置显示隐藏
    public VLayoutViewHolder setVisible(int viewId, boolean bool) {
        ImageView view = getView(viewId);
        if (bool) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
        return this;
    }



    public VLayoutViewHolder setTvVisible(int viewId, boolean bool) {
        TextView tv = getView(viewId);
        if (bool) {
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
        }
        return this;
    }


    //给一个textView设置text
    public VLayoutViewHolder setTextViewText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    //给一个textView加drawable
    public VLayoutViewHolder setTvDrawable(int textId, Drawable d, int location){
        TextView tv =getView(textId);

        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        //左边 上边 右边 下边
        tv.setCompoundDrawables(d, null, null, null);
        return this;
    }

    public VLayoutViewHolder setTextViewTextColor(int viewId, int viewColor) {
        TextView textView = getView(viewId);
        textView.setTextColor(viewColor);
        return this;
    }

    public VLayoutViewHolder setBannerViewPic(int viewId, int picId) {

        return this;
    }
    public String getEdTextString(int viewId) {
        EditText editText = getView(viewId);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return editText.getText().toString();
    }

    public VLayoutViewHolder setBgColor(int LayoutId, int LayoutColor) {
        LinearLayout layout = getView(LayoutId);
        layout.setBackgroundResource(LayoutColor);
        return this;
    }

    public VLayoutViewHolder setTextViewBgColor(int LayoutId, int color){
        TextView textView =getView(LayoutId);
        textView.setBackgroundColor(color);
        return this;
    }

    public VLayoutViewHolder setOnclickListener(int viewId) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(this);
        }
        return this;
    }
}
