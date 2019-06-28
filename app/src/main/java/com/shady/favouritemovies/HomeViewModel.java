package com.shady.favouritemovies;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shady.favouritemovies.model.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<List<Product>> mutableProductList = new MutableLiveData();
   private List<Product> productList = new ArrayList<>();

    void getProducts(){
        if (!productList.isEmpty()){
            mutableProductList.postValue(productList);
            return;
        }


        Product product = new Product();
        product.setTitle("Glad Clingwrap");
        product.setImageUrl("http://i.walmartimages.com/i/p/00/01/25/87/00/0001258700020_500X500.jpg");
        productList.add(product);

        Product product1 = new Product();
        product1.setTitle("Suave Kids Double");
        product1.setImageUrl("http://i.walmartimages.com/i/p/00/07/94/00/81/0007940081270_500X500.jpg");
        productList.add(product1);

        Product product2 = new Product();
        product2.setTitle("Revlon Eye");
        product2.setImageUrl("http://img.rakuten.com/PIC/49971627/0/1/300/49971627.jpg");
        productList.add(product2);

        Product product3 = new Product();
        product3.setTitle("Good Earth Origina");
        product3.setImageUrl("http://img.rakuten.com/PIC/50057565/0/1/300/50057565.jpg");
        productList.add(product3);

        Product product4 = new Product();
        product4.setTitle("Maybelline Great");
        product4.setImageUrl("http://img.rakuten.com/PIC/49899801/0/1/300/49899801.jpg");
        productList.add(product4);

        Product product5 = new Product();
        product5.setTitle("Biore Deep Cleansing");
        product5.setImageUrl("http://pics1.ds-static.com/prodimg/92372/300.jpg");
        productList.add(product5);

        Product product6 = new Product();
        product6.setTitle("Swiffer Dusters");
        product6.setImageUrl("http://pics1.ds-static.com/prodimg/193587/300.JPG");
        productList.add(product6);

        Product product7 = new Product();
        product7.setTitle("Upper Canada");
        product7.setImageUrl("http://pics1.ds-static.com/prodimg/139535/150.jpg");
        productList.add(product7);

        Product product8 = new Product();
        product8.setTitle("Starbucks Espresso");
        product8.setImageUrl("http://pics1.ds-static.com/prodimg/255066/150.jpg");
        productList.add(product8);

        Product product9 = new Product();
        product9.setTitle("Air Wick Scented Oil");
        product9.setImageUrl("http://pics1.ds-static.com/prodimg/233172/150.jpg");
        productList.add(product9);

        mutableProductList.postValue(productList);
    }

}
