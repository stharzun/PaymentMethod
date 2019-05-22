package com.stharzun.payment;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arjun Shrestha on 4/8/19.
 * arjunshrestha.com.np
 * stharzun@gmail.com
 */
public class PaymentOption {
    public static final int PAYMENT_OPTION_NON = 0;
    public static final int PAYMENT_OPTION_ESEWA = 2;
    public static final int PAYMENT_OPTION_KHALTI = 3;
    public static final int PAYMENT_OPTION_PAYPAL = 1;
    public static final int PAYMENT_OPTION_VISA = 4;
    public static int PAYMENT_OPTION_COD = 5;

    private static final String OPTION_ESEWA = "eSewa";
    private static final String OPTION_KHALTI = "Khalti";
    private static final String OPTION_PAYPAL = "Paypal";
    private static String OPTION_VISA = "Visa";
    private static String OPTION_COD = "Cash on Deliver";

    private final Context context;
    private final Dialog mDialog;

    private final RecyclerView mRecyclerView;

    public PaymentOption(Context context) {
        this.context = context;
        this.mDialog = new Dialog(this.context);
        this.mDialog.setContentView(R.layout.dailog_select_option);
        this.mRecyclerView = mDialog.findViewById(R.id.payment_dialog_payment_list);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.context, 3));
    }

    public void selectPaymentMethod(final PaymentSelect paymentSelect) {
        final ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.clear();
        arrayList.add(new Item(OPTION_PAYPAL, R.drawable.paypal));
        arrayList.add(new Item(OPTION_ESEWA, R.drawable.esewa));
        arrayList.add(new Item(OPTION_KHALTI, R.drawable.khalti_full_logo));
//        arrayList.add(new Item(OPTION_VISA, R.drawable.visa));
        this.mRecyclerView.setAdapter(new PaymentListing(arrayList));
        this.mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this.context, mRecyclerView, new RecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (arrayList.get(position).getTitle().equals(OPTION_ESEWA))
                    paymentSelect.OnMethodSelected(PAYMENT_OPTION_ESEWA);
                else if (arrayList.get(position).getTitle().equals(OPTION_KHALTI))
                    paymentSelect.OnMethodSelected(PAYMENT_OPTION_KHALTI);
                else if (arrayList.get(position).getTitle().equals(OPTION_PAYPAL))
                    paymentSelect.OnMethodSelected(PAYMENT_OPTION_PAYPAL);
                else
                    paymentSelect.OnMethodSelected(PAYMENT_OPTION_NON);
                mDialog.dismiss();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        this.mDialog.findViewById(R.id.payment_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentSelect.OnMethodSelected(PAYMENT_OPTION_NON);
                mDialog.dismiss();
            }
        });
        showDialog();
    }

    private void showDialog() {
        WindowManager.LayoutParams wmlp = mDialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.BOTTOM;
        Window window = this.mDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.getAttributes().windowAnimations = R.style.DialogAnimation;
        this.mDialog.show();
    }


    private class PaymentListing extends RecyclerView.Adapter<PaymentListing.ViewHolder> {
        private final ArrayList<Item> arrayList;

        PaymentListing(ArrayList<Item> arrayList) {
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.title.setText(this.arrayList.get(i).getTitle());
            viewHolder.image.setImageResource(this.arrayList.get(i).getImage());
        }

        @Override
        public int getItemCount() {
            return this.arrayList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private final ImageView image;
            private final TextView title;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.item_image);
                title = itemView.findViewById(R.id.item_title);
            }
        }
    }
}
