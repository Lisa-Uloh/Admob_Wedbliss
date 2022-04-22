package com.example.admobwedbliss;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    //Creating object of AdView
    private AdView bannerAdView;

    //creating Object of Buttons
    private Button loadAdBtn;
    private Button showAdBtn;
    private Button hideAdBtn;

    //Simple boolean for checking if ad is loaded or not
    private boolean adLoaded=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the Google Admob SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

                //Showing a simple Toast Message to the user when The Google AdMob Sdk Initialization is Completed

                Toast.makeText (MainActivity.this, "AdMob Sdk Initialize "+ initializationStatus.toString(), Toast.LENGTH_LONG).show();

            }
        });

        bannerAdView = (AdView) findViewById(R.id.bannerAdView);

        // Initializing the Button  objects to their respective views from activity_main.xml file
        loadAdBtn = (Button) findViewById(R.id.loadBannerBtn);
        showAdBtn = (Button) findViewById(R.id.showBannerBtn);
        hideAdBtn = (Button) findViewById(R.id.hideBannerBtn);

        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the loadBannerAd method
                loadBannerAd();
            }
        });

        showAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the showBannerAd method
                showBannerAd();
            }
        });

        hideAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the hideBannerAd method
                hideBannerAd();
            }
        });


        // creating different AdListener for Banner Ad with some Override methods
        bannerAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // setting adLoaded to true
                adLoaded=true;
                // Showing a simple Toast message to user when an ad is loaded
                Toast.makeText (MainActivity.this, "Ad is Loaded", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // setting adLoaded to false
                adLoaded=false;

                // Showing a simple Toast message to user when and ad is failed to load
                Toast.makeText (MainActivity.this, "Ad Failed to Load ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onAdOpened() {

                // Showing a simple Toast message to user when an ad opens and overlay and covers the device screen
                Toast.makeText (MainActivity.this, "Ad Opened", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onAdClicked() {

                // Showing a simple Toast message to user when a user clicked the ad
                Toast.makeText (MainActivity.this, "Ad Clicked", Toast.LENGTH_LONG).show();

            }


            public void onAdLeftApplication() {

                // Showing a simple Toast message to user when the user left the application
                Toast.makeText (MainActivity.this, "Ad Left the Application", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onAdClosed() {

                // Showing a simple Toast message to user when the user interacted with ad and got the other app and then return to the app again
                Toast.makeText (MainActivity.this, "Ad is Closed", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void loadBannerAd()
    {
        // Creating  a Ad Request
        AdRequest adRequest = new AdRequest.Builder().build();

        // load Ad with the Request
        bannerAdView.loadAd(adRequest);

        // Showing a simple Toast message to user when an ad is Loading
        Toast.makeText (MainActivity.this, "Banner Ad is loading ", Toast.LENGTH_LONG).show();

    }

    private void showBannerAd()
    {
        if ( adLoaded )
        {
            //showing the ad Banner Ad if it is loaded
            bannerAdView.setVisibility(View.VISIBLE);

            // Showing a simple Toast message to user when an banner ad is shown to the user
            Toast.makeText (MainActivity.this, "Banner Ad Shown", Toast.LENGTH_LONG).show();

        }
        else
        {
            //Load the banner ad if it is not loaded
            loadBannerAd();

            // Showing a simple Toast message to user when an ad is not loaded
            Toast.makeText (MainActivity.this, "Banner Ad is Loaded ", Toast.LENGTH_LONG).show();

        }
    }

    private void hideBannerAd()
    {
        // Hiding the Banner
        bannerAdView.setVisibility(View.GONE);
    }
}