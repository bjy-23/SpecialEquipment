package com.wondersgroup.special.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import com.wondersgroup.special.AppApplication;
import com.wondersgroup.special.entity.User;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;

public class Utils {
    public static final SimpleDateFormat gDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static final Context context = AppApplication.getInstance();
    private static final SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
    private static final String[] gSex = {"M", "F"};

    public Utils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void saveUser(User user) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ObjectOutputStream object = new ObjectOutputStream(output);
            object.writeObject(user);

            String personBase64 = new String(Base64.encode(output.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("user", personBase64);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUser() {
        try {
            String personBase64 = preferences.getString("user", "");
            byte[] base64Bytes = Base64.decode(personBase64.getBytes(), Base64.DEFAULT);
            ByteArrayInputStream input = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream object = new ObjectInputStream(input);
            User user = (User) object.readObject();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User();
    }

    /**
     * 从1900-12-12 00:00:00 取到1900-12-12
     *
     * @return
     */
    public static String getDate(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        } else {
            return date.split(" ")[0];
        }
    }

    /**
     * 格式化时间格式
     *
     * @param dateSrc
     * @param formatSrc
     * @param formatDes
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatDate(String dateSrc, String formatSrc, String formatDes) {
        SimpleDateFormat format = new SimpleDateFormat(formatSrc);
        Date date = null;
        try {
            date = format.parse(dateSrc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(formatDes);
        return s.format(date);
    }

    /**
     * 把数据转正两位数字格式，如9转成09， 11转成11（大于10则不用转）
     *
     * @param num
     * @return
     */
    public static String formatTwoDigit(int num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return Integer.toString(num);
        }
    }

    /**
     * 获取两位小数点
     *
     * @param x
     * @return
     */
    public static String formatTwoDecimal(double x) {
        DecimalFormat df = new DecimalFormat("#.00");
        BigDecimal b = new BigDecimal(x);
        double d = b.setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        return df.format(d);
    }

    /**
     * @param x
     * @return
     */
    public static double getTwoDecimal(double x) {
        BigDecimal b = new BigDecimal(x);
        double d = b.setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        return d;
    }

    /**
     * 检测网络是否连接
     *
     * @return true:已连接；false:未连接
     */
    public static boolean isNetConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo == null || !activeInfo.isConnected()) {
            return false;
        }
        return true;
    }

    /**
     * 获取应用版本号
     *
     * @return 应用版本号
     */
    public static String getVersionName() {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        String version = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取设备号ID
     *
     * @return
     */
    public static String getDevicesSN() {
        String deviceSN = null;
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (null == deviceId) {// 如果获取的 设备ID 为空，则取mac地址
            String macAdress = null;
            Enumeration<NetworkInterface> enumerations;
            try {
                enumerations = NetworkInterface.getNetworkInterfaces();
                while (enumerations.hasMoreElements()) {
                    NetworkInterface networkInterface = (NetworkInterface) enumerations.nextElement();
                    if (null != networkInterface.getHardwareAddress()) {
                        macAdress = Base64.encodeToString(networkInterface.getHardwareAddress(), Base64.DEFAULT);
                        break;
                    }
                }
            } catch (SocketException e) {
                WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifi.getConnectionInfo();
                macAdress = info.getMacAddress();
            }
            deviceSN = macAdress;
        } else {
            deviceSN = deviceId;
        }
        deviceSN = deviceSN == null ? "example" : deviceSN;// 保证其不为空
        return deviceSN;
    }


    public static boolean saveImage(Bitmap photo, String spath) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(spath, false));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 按split隔开
     *
     * @param str
     * @param split
     * @return
     */
    public static String[] getSplitArray(String str, String split) {
        String[] array = null;
        if (!TextUtils.isEmpty(str) && null != split) {
            array = str.split(split);
        }
        return array;
    }
}
