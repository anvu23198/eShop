package com.example.eshop;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {

    private List<Products> mShopList;
    private  AppViewHolder vh;
    private static ArrayList<Integer> indexArr = new ArrayList<>();

    public AppAdapter(List<Products> mShopList) {
        this.mShopList = mShopList;
    }

    public ArrayList<Integer> getIndexArr(){
        return indexArr;
    }

    public void emptyIndexArr(){
        indexArr = new ArrayList<>();
    }

    public void updateIndexArr(ArrayList<Integer> arrayList){
        indexArr = arrayList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        vh = new AppViewHolder(inflater, viewGroup);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder appViewHolder, int i){
        appViewHolder.bind(mShopList.get(i));
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mProduct, mDescription;
        private Products products;
        private ImageView imageView;
        private Button mButton;
        private boolean collapse = true;

        public AppViewHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.item_layout, parent, false));

            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageView);
            mProduct = itemView.findViewById(R.id.text_product);
            mDescription = itemView.findViewById(R.id.text_description);
            mButton = itemView.findViewById(R.id.button);
            mButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Toast toast = Toast.makeText(itemView.getContext(), "added " + products.getPrice(),
                            Toast.LENGTH_LONG);
                    indexArr.add(products.getIndex());
                    toastModify(toast);
                }
            });
        }

        public ArrayList<Integer> getIndexArr(){
            return indexArr;
        }

        public void bind(Products products){
            this.products = products;
            imageView.setImageResource(products.getImage());
            mProduct.setText(products.getProduct() + " $" + products.getPrice());
            mDescription.setText(products.getDescription());
            collapse(mDescription);
        }

        @Override
        public void onClick(View v){
            Toast toast = Toast.makeText(itemView.getContext(), "Clicked " + products.getProduct(),
                    Toast.LENGTH_LONG);
            toastModify(toast);
            if(collapse)
                expand(mDescription);
            else
                collapse(mDescription);

            collapse = !collapse;
        }




        public void toastModify(Toast toast){
            //Gravity
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 700);
            View view = toast.getView();

            //Background
            view.setBackgroundResource(R.drawable.rounded);
            GradientDrawable drawable = (GradientDrawable) view.getBackground();

            drawable.setColor(Color.GREEN);

            TextView text = (TextView) view.findViewById(android.R.id.message);

            //Text
            text.setTextColor(Color.BLACK);
            text.setGravity(Gravity.CENTER);
            text.setTextSize(20);
            toast.show();
        }

        public void expand(final View v) {
            v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final int targetHeight = v.getMeasuredHeight();
            v.getLayoutParams().height = 1;
            v.setVisibility(View.VISIBLE);
            Animation a = new Animation()
            {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? ViewGroup.LayoutParams.WRAP_CONTENT
                            : (int)(targetHeight * interpolatedTime);
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            // 1dp/ms
            a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
            v.startAnimation(a);
        }

        public void collapse(final View v) {
            final int initialHeight = v.getMeasuredHeight();

            Animation a = new Animation()
            {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    if(interpolatedTime == 1){
                        v.setVisibility(View.GONE);
                    }else{
                        v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                        v.requestLayout();
                    }
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            // 1dp/ms
            a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
            v.startAnimation(a);
        }
    }

    public int getItemCount(){
        return mShopList.size();
    }



}
