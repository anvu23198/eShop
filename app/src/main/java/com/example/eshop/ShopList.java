package com.example.eshop;

import java.util.ArrayList;
import java.util.List;

public class ShopList {
    private static ShopList mShopList;

    private List<Products> arrList;

    public static ShopList get(){
        if(mShopList == null){
            mShopList = new ShopList();
        }
        return mShopList;
    }

    private ShopList(){
        arrList = new ArrayList<>();
    }

    public List<Products> getArrList(){
        return  arrList;
    }
}
