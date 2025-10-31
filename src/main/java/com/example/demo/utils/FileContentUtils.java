package com.example.demo.utils;

import java.util.Calendar;
import java.util.Date;

public class FileContentUtils {

    public static String getFilePath(Date date, String rootFolder, String originalName) {
        StringBuilder path = new StringBuilder();
        if (rootFolder != null) {
            path.append(rootFolder);
            if (!rootFolder.endsWith("/")) {
                path.append("/");
            }
        }

        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        path.append(calendar.get(Calendar.YEAR))
                .append("/")
                .append(calendar.get(Calendar.MONTH) + 1)
                .append("/")
                .append(calendar.get(Calendar.DAY_OF_MONTH))
                .append("/")
                .append(getFilenameWithoutExtension(originalName))
                .append("-").append(calendar.get(Calendar.HOUR_OF_DAY))
                .append("-").append(calendar.get(Calendar.MINUTE))
                .append("-").append(calendar.get(Calendar.SECOND));

        String ext = getFileExtension(originalName);
        if (ext != null && !ext.isEmpty()) {
            path.append(".").append(ext);
        }

        return path.toString();
    }

    public static String getFileExtension(String file){
        String ext = null;
        if(file != null && !file.isEmpty()){
            if (file.contains(".")) {
                int mid = file.lastIndexOf(".");
                ext = file.substring(mid + 1);
            }
        }
        return ext;
    }

    public static String getFilenameWithoutExtension(String file){
        String filename = file;
        if(file != null && !file.isEmpty()){
            if (file.contains(".")) {
                int mid = file.lastIndexOf(".");
                filename = file.substring(0, mid);
            }
        }
        return filename;
    }

}
