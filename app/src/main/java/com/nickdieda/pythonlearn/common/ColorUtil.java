package com.nickdieda.pythonlearn.common;

import android.graphics.Color;
import java.util.Random;

public class ColorUtil {

    // Function to generate a random color
    public static int getRandomColor() {
        Random random = new Random();

        // Generate random values for red, green, and blue components
        int red = random.nextInt(256);   // Range: 0-255
        int green = random.nextInt(256); // Range: 0-255
        int blue = random.nextInt(256);  // Range: 0-255

        // Return the color by combining the RGB values
        return Color.rgb(red, green, blue);
    }
}
