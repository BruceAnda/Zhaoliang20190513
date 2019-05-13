package com.bawei.zhaoliang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.zhaoliang.R;
import com.bawei.zhaoliang.bean.ItemsResponse;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/13 3:37 PM
 * Description:
 */
public class MainListAdapter extends BaseAdapter {

    private Context context;
    private List<ItemsResponse.Item> items;

    private final int ITEM_TYPE_ONE = 0;
    private final int ITEM_TYPE_TWO = 1;
    private final int ITEM_TYPE_THREE = 2;

    public MainListAdapter(Context context, List<ItemsResponse.Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case ITEM_TYPE_ONE:
                ViewHolderOne holderOne = null;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.item_type_one, null);
                    holderOne = new ViewHolderOne();
                    holderOne.icon = convertView.findViewById(R.id.icon);
                    holderOne.title = convertView.findViewById(R.id.tv_title);
                    holderOne.desc = convertView.findViewById(R.id.tv_desc);
                    convertView.setTag(holderOne);
                } else {
                    holderOne = (ViewHolderOne) convertView.getTag();
                }
                ItemsResponse.Item item = items.get(position);
                Glide.with(context).load(item.image).into(holderOne.icon);
                holderOne.title.setText(item.title);
                holderOne.desc.setText(item.desc);
                break;
            case ITEM_TYPE_TWO:
                ViewHolderTwo holderTwo = null;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.item_type_two, null);
                    holderTwo = new ViewHolderTwo();
                    holderTwo.icon = convertView.findViewById(R.id.icon);
                    holderTwo.icon2 = convertView.findViewById(R.id.icon2);
                    holderTwo.icon3 = convertView.findViewById(R.id.icon3);
                    convertView.setTag(holderTwo);
                } else {
                    holderTwo = (ViewHolderTwo) convertView.getTag();
                }
                List<ItemsResponse.Image> images = items.get(position).images;
                Glide.with(context).load(images.get(0).pic).into(holderTwo.icon);
                Glide.with(context).load(images.get(1).pic).into(holderTwo.icon2);
                Glide.with(context).load(images.get(2).pic).into(holderTwo.icon3);
                break;
            case ITEM_TYPE_THREE:
                ViewHolderThree viewHolderThree = null;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.item_type_three, null);
                    viewHolderThree = new ViewHolderThree();
                    viewHolderThree.title = convertView.findViewById(R.id.tv_title);
                    convertView.setTag(viewHolderThree);
                } else {
                    viewHolderThree = (ViewHolderThree) convertView.getTag();
                }
                viewHolderThree.title.setText(items.get(position).title);
                break;
        }
        return convertView;
    }

    class ViewHolderOne {
        ImageView icon;
        TextView title;
        TextView desc;
    }

    class ViewHolderTwo {
        ImageView icon;
        ImageView icon2;
        ImageView icon3;
    }

    class ViewHolderThree {
        TextView title;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).type;
       // return 2;
    }
}
