package com.wondersgroup.special.activity;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by root on 12/27/16.
 */

public class DownloadCompleteReceiver extends BroadcastReceiver {
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onReceive(Context context, Intent intent) {
        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            String fileName = null;
            // 从下载服务获取下载管理器
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);// 设置过滤状态：成功
            Cursor c = manager.query(query);
            // 查询以前下载过的‘成功文件’
            if (c.moveToFirst()) {
                // 移动到最新下载的文件
                fileName = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
            }
            if (!TextUtils.isEmpty(fileName)) {
                File file = new File(fileName.replace("file://", ""));// 过滤路径
                if (file.exists()) {
                    installApk(context, Uri.parse("file://" + file.toString()));
                }
            }
        }
    }
    /**
     * 安装新应用
     */
    private void installApk(Context context, Uri apk) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(apk, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}