package com.bawei.zhaoliang.bean;

import java.util.List;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/13 3:33 PM
 * Description:
 */
public class ItemsResponse {

    public List<Item> items;

    public static class Item {
        public int type;
        public String title;
        public String desc;
        public String image;
        public List<Image> images;
    }

    public static class Image {
        public String pic;
    }
}
