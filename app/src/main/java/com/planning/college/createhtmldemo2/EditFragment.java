package com.planning.college.createhtmldemo2;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by KUIKUI on 2018-08-03.
 */


@EFragment(R.layout.fragment_edit)
public class EditFragment extends Fragment implements View.OnClickListener {

    @ViewById(R.id.edit_text)
    EditText editText;

    @ViewById(R.id.bt_preview)
    Button bt_preview;

    @ViewById(R.id.bt_edit)
    Button bt_edit;

    @ViewById(R.id.bt_addImage)
    Button bt_addImage;



    Context context;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {

            Manifest.permission.READ_EXTERNAL_STORAGE,

            Manifest.permission.WRITE_EXTERNAL_STORAGE

    };

    @AfterViews
    public void afterViews() {

        context = getActivity();

        bt_addImage.setOnClickListener(this);
        bt_preview.setOnClickListener(this);




    }

    public  void verifyStoragePermissions() {

        int permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            // We don't have permission so prompt the user

            ActivityCompat.requestPermissions(
                    getActivity(),
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );

        }

    }


    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_addImage:

                verifyStoragePermissions();

                Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
                getImage.addCategory(Intent.CATEGORY_OPENABLE);
                getImage.setType("image/*");
                startActivityForResult(getImage, 1);
                break;
            case R.id.bt_preview:

                String filePath = context.getCacheDir()+"/test.html";

                String content = Global.htmlHead+ editText.getText().toString() +Global.htmltail;

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(filePath);

                    byte [] bytes = content.getBytes();

                    fos.write(bytes);
                    fos.flush();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }   finally {
                    if(fos != null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment,new PreviewFragment_());
                transaction.commit();

                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String content = editText.getText().toString();
        if (resultCode == getActivity().RESULT_OK && requestCode == 1) {
            Uri uri = data.getData();
            String test =  uri.toString();
           String path = Tool.getPath(context, uri);
            String path2 = test.substring(test.indexOf(":")+2);

            String fileName = path.substring(path.lastIndexOf("/")+1);

            Log.i("tag","文件名："+fileName+"");

            File file = new File(path);
          //  Toast.makeText(context,"文件是否存在："+file.exists(),Toast.LENGTH_SHORT).show();

            //将文件复制到cache目录下

/*
            float radio = 1;
            if(file.length()>100000){

                radio = 100000 / file.length();//将图片压缩到100k,
            }
            //压缩并复制图片到指定位置
             ImageCompress.reduceImg(path,context.getCacheDir()+"/test.html",1000,1000,radio);
*/

           ImageCompress.saveBitmapFile(path,context.getCacheDir()+"/"+fileName);


            content += "<br/><img src='" + fileName + "' width='100%' />";

            editText.setText(content);
        }
    }

}
