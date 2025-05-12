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

    public static void loadBannerAd(Activity activity, FrameLayout adContainerView) {
        AdView adView = new AdView(activity);
        adView.setAdUnitId("ca-app-pub-5272550552627150/8268700770");

//        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.setAdSize(AdSize.BANNER);
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
        });
    }

    public static void initializeAds(Activity activity) {
        MobileAds.initialize(activity, initializationStatus -> {});
    }
}
