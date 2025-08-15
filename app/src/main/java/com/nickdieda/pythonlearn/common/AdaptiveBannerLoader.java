package com.nickdieda.pythonlearn.common;


import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowMetrics;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdaptiveBannerLoader {

    private static AdView adView;

    /**
     * Loads an adaptive banner ad into the given container.
     *
     * @param activity The activity context.
     * @param adContainer The FrameLayout where the ad will be loaded.

     */
    public static void loadAd(Activity activity, FrameLayout adContainer  ) {
        // Initialize Mobile Ads SDK
        MobileAds.initialize(activity, initializationStatus -> {});

        String adUnitId=  "ca-app-pub-5272550552627150/7666397056";

        adView = new AdView(activity);
        adView.setAdUnitId(adUnitId);

        // Set adaptive banner size
        AdSize adSize = getAdSize(activity);
        adView.setAdSize(adSize);

        // Add AdView to container
        adContainer.removeAllViews();
        adContainer.addView(adView);

        // Load the ad
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // Optional listener
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Ad loaded
            }

            @Override
            public void onAdFailedToLoad(com.google.android.gms.ads.LoadAdError adError) {
                // Ad failed to load
            }
        });
    }

    /** Calculates adaptive ad size based on screen width */
    private static AdSize getAdSize(Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        float density = displayMetrics.density;

        int adWidthPixels;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            adWidthPixels = windowMetrics.getBounds().width();
        } else {
            adWidthPixels = displayMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

    /** Lifecycle methods */
    public static void onResume() {
        if (adView != null) adView.resume();
    }

    public static void onPause() {
        if (adView != null) adView.pause();
    }

    public static void onDestroy() {
        if (adView != null) adView.destroy();
    }
}
