package com.hank.library.util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * @author hank.he
 * @date 2016/8/15 17:46
 */
public class SDCardUtils {

    /**
     * Check the SD card
     *
     * @return
     */
    public static boolean checkSDCardAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * Check if the file is exists
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean isFileExistsInSDCard(String filePath, String fileName) {
        boolean flag = false;
        if (checkSDCardAvailable()) {
            File file = new File(filePath, fileName);
            if (file.exists()) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Write file to SD card
     *
     * @param filePath
     * @param filename
     * @param content
     * @return
     * @throws Exception
     */
    public static boolean saveFileToSDCard(String filePath, String filename, String content)
            throws Exception {
        boolean flag = false;
        if (checkSDCardAvailable()) {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(filePath, filename);
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
            outStream.close();
            flag = true;
        }
        return flag;
    }

    /**
     * Read file as stream from SD card
     *
     * @param fileName String PATH =
     *                 Environment.getExternalStorageDirectory().getAbsolutePath() +
     *                 "/dirName";
     * @return
     */
    public static byte[] readFileFromSDCard(String filePath, String fileName) {
        byte[] buffer = null;
        try {
            if (checkSDCardAvailable()) {
                String filePaht = filePath + "/" + fileName;
                FileInputStream fin = new FileInputStream(filePaht);
                int length = fin.available();
                buffer = new byte[length];
                fin.read(buffer);
                fin.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * Delete file
     *
     * @param filePath
     * @param fileName filePath =
     *                 android.os.Environment.getExternalStorageDirectory().getPath()
     * @return
     */
    public static boolean deleteSDFile(String filePath, String fileName) {
        File file = new File(filePath + "/" + fileName);
        if (file == null || !file.exists() || file.isDirectory())
            return false;
        return file.delete();
    }


    public static String getDownloadedPath() {
        String path=Environment.getExternalStorageDirectory().getAbsolutePath() + "/injest/";
        File floder= new File(path);
        if(!floder.exists()){
            floder.mkdir();
        }
        return path;
    }

    public static boolean writeResponseBodyToDisk(String path, ResponseBody body) {
        try {
            File futureStudioIconFile = new File(path);
            if (futureStudioIconFile.exists())
                return true;
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
