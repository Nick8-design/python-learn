package com.nickdieda.pythonlearn.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;

import com.nickdieda.pythonlearn.R;

public class BrightnessUtil {

    // Static method to show the brightness dialog
    public static void showBrightnessDialog(Context context) {
        // Create a Dialog with the passed context
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_brightness);

        // Get the SeekBar from the dialog layout
        SeekBar brightnessSeekBar = dialog.findViewById(R.id.seekBar_brightness);

        // Set the initial position of the SeekBar based on the current brightness
        int currentBrightness = getCurrentWindowBrightness(context);
        brightnessSeekBar.setProgress(currentBrightness);

        // Listen for changes on the SeekBar
        brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Set the brightness
                setScreenBrightness(context, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        dialog.show();
    }

    // Static method to get the current window brightness
    private static int getCurrentWindowBrightness(Context context) {
        Window window = ((Activity) context).getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        float brightness = layoutParams.screenBrightness;
        if (brightness < 0) {
            // The screen brightness is set to the system default
            brightness = 0.5f;  // Assume a default value if system setting is being used
        }
        return (int) (brightness * 255);
    }

    // Static method to set the screen brightness
    private static void setScreenBrightness(Context context, int brightness) {
        Window window = ((Activity) context).getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.screenBrightness = brightness / 255.0f;  // Convert to a float value between 0 and 1
        window.setAttributes(layoutParams);
    }
}
