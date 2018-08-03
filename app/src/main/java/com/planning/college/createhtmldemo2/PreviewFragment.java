package com.planning.college.createhtmldemo2;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by KUIKUI on 2018-08-03.
 */


@EFragment(R.layout.fragment_previews)
public class PreviewFragment extends Fragment implements View.OnClickListener {

    @ViewById(R.id.web_view)
    WebView webView;



    Context context;

    @AfterViews
    public void afterViews() {

        context = getActivity();
        update();


    }

    @Background
    public void update(){


        updateWebView();

    }


    @UiThread
    public void updateWebView(){


        webView.loadUrl("file://"+context.getCacheDir()+"/test.html");
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webView.getSettings().setLoadWithOverviewMode(true);
    }




    public void onClick(View v) {
        switch (v.getId()) {


        }
    }



}
