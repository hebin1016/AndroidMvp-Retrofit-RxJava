package com.hank.library.util;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

/**
 * Description : 设备信息工具类
 * Author: roy.ren
 * Date: 2016/4/2 0002
 * Time: 20:58
 * Modify by:
 */
public class DeviceInfoUtil {

    /**
     * 设备ID(IMEI)
     *
     * @param context 上下文
     * @return IMEI
     */
    public static String getDeviceId(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Gets imsi.
     *
     * @param context the context
     * @return the imsi
     */
    public static String getImsi(Context context) {
        String result = "";
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        result = mTelephonyMgr.getSubscriberId();
        if (TextUtils.isEmpty(result)) result = "0";
        return result;
    }


    /**
     * Gets simCountryIso
     *
     * @param context the context
     * @return the simCountryIso
     */
    public static String getSimCountryIso(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getSimCountryIso();
    }

    /**
     * Gets PhoneType
     *
     * @param context the context
     * @return the PhoneType
     */
    public static int getPhoneType(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getPhoneType();
    }

    public static String getPhoneName(Context context) {
        return Build.MODEL;
    }

    /**
     * Gets cpu name.
     *
     * @return the cpu name
     */
    public static String getCpuName() {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("/proc/cpuinfo");
            br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
            }
            return array[1];
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null)
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return "";
    }

    /**
     * Gets SimOperatorName
     *
     * @param context the context
     * @return the SimOperatorName
     */
    public static String getSimOperatorName(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getSimOperatorName();
    }

    public static String getSimOperator(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getSimOperator();
    }

    /**
     * Gets NetworkType
     *
     * @param context the context
     * @return the NetworkType
     */
    public static int getNetworkType(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getNetworkType();
    }

    /**
     * Get Release version
     */
    public static String RELEASE_VERSION = Build.VERSION.RELEASE;


    /**
     * Get Phone number
     *
     * @param context the context
     * @return the Phonenumber
     */
    public static String getPhoneNumber(Context context) {
        String result = "";
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        result = mTelephonyMgr.getLine1Number();
        if (TextUtils.isEmpty(result)) result = "";
        return result;
    }

    public static boolean HasSim(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int absent = manager.getSimState();
        if (1 == absent) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return
     * @Description: 获取移动数据IP地址
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString() == null ? "" : inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return null;
    }


    private static String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }


    /**
     * @param ipaddr IP地址
     * @return public ip
     */

    public static String GetNetIp(String ipaddr) {
        URL infoUrl = null;
        InputStream inStream = null;
        try {
            infoUrl = new URL(ipaddr);
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                    strber.append(line);
                inStream.close();
                int index1 = strber.indexOf("[") + 1;
                int index2 = strber.indexOf("]");
                String ip = strber.substring(index1, index2);
                return ip;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}