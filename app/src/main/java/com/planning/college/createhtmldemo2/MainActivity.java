package com.planning.college.createhtmldemo2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private FragmentManager manager;

    private FragmentTransaction transaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_main);



        manager = getFragmentManager();

        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment,new EditFragment_());
        transaction.commit();
    }


}
