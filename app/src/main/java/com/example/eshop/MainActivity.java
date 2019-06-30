package com.example.eshop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView mCart;
    private RecyclerView mShoppingList;
    private LinearLayoutManager mLinearLayoutManager;
    private AppAdapter mAppAdapter;
    public ArrayList<Integer> indexArr;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                int info = Integer.parseInt(result);
                if(info == -1) mAppAdapter.emptyIndexArr();
            }
            else if(resultCode == 5){
                ArrayList<Integer> newIndexArr = (ArrayList<Integer>) data.getIntegerArrayListExtra("result");
                mAppAdapter.updateIndexArr(newIndexArr);
            }
        }
    }//onActivityResult

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCart = findViewById(R.id.cartIcon);
        mCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                indexArr = mAppAdapter.getIndexArr();
                Intent intent = new Intent(MainActivity.this, Cart.class);
                intent.putExtra("list",indexArr);
                startActivityForResult(intent, 1);
            }
        });

        for(int i = 0; i < 6; i++) {
            Products products = new Products(i);
            ShopList.get().getArrList().add(products);
        }


        mShoppingList = findViewById(R.id.shopping_list);
        mShoppingList.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mShoppingList.setLayoutManager(mLinearLayoutManager);
        mAppAdapter = new AppAdapter(ShopList.get().getArrList());
        mShoppingList.setAdapter(mAppAdapter);
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
}
