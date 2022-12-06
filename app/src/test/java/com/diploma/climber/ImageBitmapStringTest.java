package com.diploma.climber;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.diploma.climber.database.converter.ImageBitmapString;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(JUnit4.class)
public class ImageBitmapStringTest {

    Bitmap bitmap;

    @Before
    public void initializeImage() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File("/Users/mmarzec/AndroidStudioProjects/Climber/app/src/test/java/com/diploma/climber/resources/testImageForConverting.png"));
        bitmap = BitmapFactory.decodeStream(fileInputStream);
    }

    @Ignore
    @Test
    public void test_bitmap_string_conversion() {
        String encoded = ImageBitmapString.BitmapToString(bitmap);
        Bitmap decoded = ImageBitmapString.StringToBitmap(encoded);

        Assert.assertEquals(decoded, bitmap);
    }
}
