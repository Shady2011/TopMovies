package com.shady.favouritemovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.shady.favouritemovies.model.Product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnProductListsner {

    private RecyclerView productRecycler;
    private ProductAdapter adapter;
    private TextView rateTxt;
    private int rate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(configuration);
        productRecycler = findViewById(R.id.products_recycler);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        rateTxt = findViewById(R.id.rate);
        rateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortProducts();
            }
        });

        HomeViewModel viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.mutableProductList.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setProductRecyclerView(products);
                if (rate == 0){
                    Collections.sort(products, new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            return (int)(o2.getRate() - o1.getRate());
                        }
                    });
                }else{
//                    Collections.shuffle(products);
                }
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.getProducts();
    }

//    @Test
    private void incrementVictoryCountCallsRepository() {
//        stubVictoryRepositoryGetVictoryCount(5) // Arrange
//        viewModel.incrementVictoryCount() // Act
//        verify(mockVictoryRepository).getVictoryCount() // Assert
    }

    private void sortProducts() {
        List<Product> products = adapter.getProductList();
        if (rate == 0){
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return (int)(o2.getRate() - o1.getRate());
                }
            });

            rateTxt.setText(getString(R.string.random));
            rate = 1;
        }else{
            Collections.shuffle(products);
            rateTxt.setText(getString(R.string.rate));
            rate = 0;
        }
        adapter.notifyDataSetChanged();
    }

    private void setProductRecyclerView(List<Product> dataList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ProductAdapter(this);
        productRecycler.setLayoutManager(layoutManager);
        adapter.setProductList(dataList);
        productRecycler.setAdapter(adapter);
    }

    @Override
    public void onProductClick(Product product) {
        showDialog(product);
    }

    private void showDialog(Product product) {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);

        LinearLayout linearLayout = new LinearLayout(this);
        final RatingBar rating = new RatingBar(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rating.setLayoutParams(lp);
        rating.setNumStars(5);
        rating.setStepSize(1);

        //add ratingBar to linearLayout
        linearLayout.addView(rating);

        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle("Add Rating");
        popDialog.setMessage(product.getTitle());
        //add linearLayout to dailog
        popDialog.setView(linearLayout);


        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                System.out.println("Rated val:" + v);
            }
        });


        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        textView.setText(String.valueOf(rating.getProgress()));
                        product.setRate(rating.getProgress());
                        Collections.sort(adapter.getProductList(), new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return (int)(o2.getRate() - o1.getRate());
                            }
                        });
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }

                })

                // Button Cancel
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        popDialog.create();
        popDialog.show();

    }

}
