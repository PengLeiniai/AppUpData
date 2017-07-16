package com.pl.incrementalupdata;

import android.os.Environment;

import java.io.File;

/**
 * Created by PengLei_PC on 2017/7/14.
 */

public class MyConstants {
    public static final String PATCH_FILE = "apk.patch";
    public static final String RUL_PATCH_DOWN = "http://192.168.0.103/"+PATCH_FILE;
    public static final String PACKAGE_NAME = "com.pl.incrementalupdata";
    public static final String SD = Environment.getExternalStorageDirectory()+ File.separator;
    public static final String NEW_APK_PATH = SD + "new_apk.apk";
    public static final String PATCH_FILE_PATH = SD +PATCH_FILE;
}
