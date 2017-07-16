package com.pl.incrementalupdata;

/**
 * Created by PengLei_PC on 2017/7/14.
 */

public class Bspatch {
    public static native int bsPatch(String oldPath,String newPath,String patchPath);
    static{
        System.loadLibrary("bspatch");
    }
}
