package com.hank.library.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * @author hank.he
 * @date 2016/9/14 17:12
 */
public class BitmapUtil {

    public static Bitmap file2Bitmap(String path){
        File picFile=new File(path);
        if(!picFile.exists())
            return null;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);
        options.inSampleSize = calculateInSampleSize(options, options.outWidth, options.outHeight);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig= Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(path,options);
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
