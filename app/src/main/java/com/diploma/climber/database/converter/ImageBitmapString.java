package com.diploma.climber.database.converter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

/**
 * This class is responsible as utility to converting profile picture
 * from Bitmap format which is stored in POJO object to String which
 * can be stored inside of SQLite table.
 *
 * Each method in this class uses Base64 algorithm to encode or decode
 * from given object.
 *The encoder maps the input to a set of characters in the A-Za-z0-9+/ character set.
 *
 * @author mmarzec
 * based on <a href="https://medium.com/@uttam.cooch/save-images-in-room-persistence-library-c71b60865b7e">
 * Medium.com tutorial link</a>
 */
public class ImageBitmapString {

    /**
     * Method for converting image bitmap to String
     *
     * @param bitmap image in bitmap format
     * @return encoded String object
     */
    @TypeConverter
    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();

        String temp = Base64.encodeToString(b, Base64.DEFAULT);

        return temp;
    }


    /**
     * Method for converting String to image bitmap
     *
     * @param encodedString String object
     * @return Image back in Bitmap format
     */
    @TypeConverter
    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

            return bitmap;

        } catch (Exception e) {
            Log.d("Converting exception", e.getMessage());
            return null;
        }
    }
}