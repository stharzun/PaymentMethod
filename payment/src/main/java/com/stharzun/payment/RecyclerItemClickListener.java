    package com.stharzun.payment;

import android.view.View;

    /**
     * Created by arzun on 6/7/17.
     */

    public interface RecyclerItemClickListener {
        void onItemClick(View view, int position);

        void onLongClick(View view, int position);
    }
