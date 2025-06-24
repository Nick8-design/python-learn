package com.nickdieda.pythonlearn.common;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdHelper {

    // Reuse a single AdView across activities
    private static AdView adView;

    public static void initializeAds(Activity activity) {
        MobileAds.initialize(activity, initializationStatus -> {});
    }

    public static void loadBannerAd(Activity activity, FrameLayout adContainerView) {
        // If adView is already created, remove it from old parent
        if (adView != null && adView.getParent() != null) {
            ((FrameLayout) adView.getParent()).removeView(adView);
        }


        if (adView == null) {
            adView = new AdView(activity.getApplicationContext());
//            production
//            adView.setAdUnitId("ca-app-pub-5272550552627150/8268700770");

            //test
            adView.setAdUnitId("ca-app-pub-3940256099942544/9214589741");


            adView.setAdSize(AdSize.BANNER);
        }

        // Add adView to container
        adContainerView.removeAllViews();
        adContainerView.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                adContainerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(com.google.android.gms.ads.LoadAdError adError) {
                Log.e("AdError", adError.getMessage());
                adContainerView.setVisibility(View.GONE);
            }

            @Override
            public void onAdOpened() {
                // Preload next ad immediately after this one is shown
                adView.loadAd(new AdRequest.Builder().build());
            }
        });
    }
}
