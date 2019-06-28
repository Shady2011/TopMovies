package com.shady.favouriteproducts;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageBinderAdapter {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageDrawable(null);
        } else {
            DisplayImageOptions options= new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.ic_launcher) // resource or drawable
                    .showImageForEmptyUri(R.mipmap.ic_launcher) // resource or drawable
                    .showImageOnFail(R.mipmap.ic_launcher) // resource or drawable
                    .build();
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            ImageLoader.getInstance().displayImage(url, imageView, options);
        }
    }
}
