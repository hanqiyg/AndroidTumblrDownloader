package com.onlysofts.tumblrdownloader;

import android.content.Context;

import java.io.File;

/**
 * Created by i.c.e on 2017/6/1.
 */

public class StorageUtils {
    public static File getExtPath(Context context,boolean useExtSDCard){
        File[] files = context.getExternalFilesDirs("");
        if(useExtSDCard){
            if(files.length > 1){
                return files[1];
            }
            return null;
        }else{
            if(files.length > 0){
                return files[0];
            }
            return null;
        }
    }
    public static boolean isFileExist(Context context,String filename,boolean useExtSDCard){
        boolean exist = false;
        File path = getExtPath(context,useExtSDCard);
        if(path != null){
            File f = new File(path,filename);
            exist = (f.exists() && f.isFile());
        }
        System.out.println("file [" + filename + "] " + (exist?" Exist.":"not Exist." ));
        return exist;
    }

    public static boolean remove(Context context, String filename, boolean useExtSDCard) {
        boolean success = false;
        File path = getExtPath(context,useExtSDCard);
        if(path != null){
            File f = new File(path,filename);
            success = f.delete();
        }
        System.out.println("remove file [" + filename + "] " + (success?" Success.":"Fails." ));
        return success;
    }
    public static boolean createDirs(File file){
        boolean create = false;
        if(file != null && !file.exists()){
            if(!file.getParentFile().exists()){
                create =  file.getParentFile().mkdir();
            }
        }
        System.out.println("createDirs [" + file.getAbsolutePath() + "] " + (create?"Success":"Fails."));
        return create;
    }
}
