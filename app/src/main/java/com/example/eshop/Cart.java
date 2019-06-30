package com.example.eshop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Integer> indexArr;
    private ArrayList<String> itemList = new ArrayList<>();
    private Products products = new Products();
    private double total = 0;
    private TextView mTotal;

    public ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);

        lv = (ListView) findViewById(R.id.cart_items);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            indexArr = extras.getIntegerArrayList("list");
            for(Integer index : indexArr){
                products.setIndex(index);
                total += products.getPrice();
                itemList.add(products.getProduct() + "\n$" + products.getPrice());
            }
        }

        mTotal = findViewById(R.id.total);
        mTotal.setText("Total: $ " + total);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                products.setIndex(position);

                AlertDialog.Builder adb=new AlertDialog.Builder(Cart.this);
                adb.setTitle("Remove from cart?");
                adb.setMessage("Are you sure you want to remove\n" + products.getProduct() + "\nfrom cart?");
                final int positionToRemove = position;
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        indexArr.remove(positionToRemove); //remove original index arr
                        itemList.remove(positionToRemove); //remove listview for immediately effect

                        total = 0;
                        for(Integer index : indexArr){
                            products.setIndex(index);
                            total += products.getPrice();
                        }
                        mTotal.setText("Total: $ " + total);

                        lv.invalidateViews();

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",indexArr);
                        setResult(5,returnIntent);
                    }});
                adb.show();

            }
        });

        ImageView mBack = findViewById(R.id.backIcon);
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        Button mClearCart = findViewById(R.id.clear_cart);
        mClearCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder adb=new AlertDialog.Builder(Cart.this);
                adb.setTitle("Remove all items?");
                adb.setMessage("Are you sure you want to all the items from cart?");
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        indexArr.clear();
                        itemList.clear();
                        mTotal.setText("Total: $ 0.0");

                        lv.invalidateViews();

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result","-1");
                        setResult(Activity.RESULT_OK,returnIntent);
                    }});
                adb.show();

            }
        });
    }


}
