package com.stharzun.payment;

/**
 * Created by Arjun Shrestha on 4/8/19.
 * arjunshrestha.com.np
 * stharzun@gmail.com
 */
public class Item {
    private final String title;
    private final int image;

    Item(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
