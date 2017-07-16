package com.pl.incrementalupdata;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by PengLei_PC on 2017/7/14.
 */

public class Utils {
    /**
     * 下载
     */
    public static File down(String url) {
        File file = null;
        InputStream is = null;
        FileOutputStream os = null;
        file = new File(Environment.getExternalStorageDirectory(), MyConstants.PATCH_FILE);
        if (file.exists()) {
            file.delete();
        }
        try {
            HttpURLConnection coon = (HttpURLConnection) new URL(url).openConnection();
            coon.setDoInput(true);
            is = coon.getInputStream();
            os = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                Log.d("TAG", "downLoad Byte:" + String.valueOf(len));
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();

                else if (os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 拿到已安装的apk源文件
     * @param context
     * @param packageName
     * @return
     */
    public static String getSourceAPKPath(Context context,String packageName){
        if (isEmpty(packageName))
            return null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName,0);
            return appInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 拿到版本号
     * @param context
     * @return
     */
    public static int getVersionCode(Context context){
        PackageManager packageManager = context.getPackageManager();
        try {
           PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 安装
     * @param context
     * @param apkPath
     */
    public static void installApk(Context context,String apkPath){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://"+apkPath),"application/vnd.android.package-archive");
        context.startActivity(intent);
    }
    public static boolean isEmpty(String s){
        if (s==null||s.length()<1)
            return true;
        else
            return false;
    }

}
