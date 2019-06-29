package com.demo.utils;

import java.io.*;


/**
 * Created by Enzo Cotter on 2019/6/6.
 */
public class Utils {

    public static FileInputStream readImage(String path) throws IOException {
        return new FileInputStream(new File(path));
    }

    // 读取表中图片获取输出流,写入targetPath路径中
    public static void readImage(InputStream in, String targetPath) {
        File file = new File(targetPath);
        String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
        if (!file.exists()) {
            new File(path).mkdir();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 判断文件是否为图片文件
     *
     * @param fileName
     * @return
     */
    private boolean isImageFile(String fileName) {
        String[] img_type = new String[] { ".jpg", ".jpeg", ".png", ".gif", ".bmp" };
        if (fileName == null) {
            return false;
        }
        fileName = fileName.toLowerCase();
        for (String type : img_type) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }
        return false;
    }





    }
