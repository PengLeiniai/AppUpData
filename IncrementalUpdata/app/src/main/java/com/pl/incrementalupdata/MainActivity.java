package com.pl.incrementalupdata;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Utils.getVersionCode(MainActivity.this)<2){
            new ApkUpdata().execute();
        }
    // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
//    tv.setText(stringFromJNI());
    }
    class ApkUpdata extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                File patchFile = Utils.down(MyConstants.RUL_PATCH_DOWN);
                Log.d("TAG","开始下载...");
                String oldPath = Utils.getSourceAPKPath(MainActivity.this,getPackageName());
                String newPath = MyConstants.NEW_APK_PATH;
                String patchPath = patchFile.getAbsolutePath();
                int ret = Bspatch.bsPatch(oldPath,newPath,patchPath);
                if (ret<0){
                    return false;
                }else{
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            if (b){
                Utils.installApk(MainActivity.this, MyConstants.NEW_APK_PATH);
            }
        }
    }
}
