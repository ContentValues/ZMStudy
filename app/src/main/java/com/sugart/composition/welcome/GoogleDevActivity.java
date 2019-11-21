package com.sugart.composition.welcome;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.base.BaseActivity;
import xdroid.mwee.com.zmstudy.R;

/**
 * Author：created by SugarT
 * Time：2019/10/17 18
 */
public class GoogleDevActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_google_dev;
    }

    @Override
    public void initView() {

        TextView mTvPolice = findViewById(R.id.mTvPolice);
        mTvPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Police");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}
