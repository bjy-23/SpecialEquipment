package com.wondersgroup.special.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wondersgroup.special.AppApplication;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Tips;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.dialog.PromptDialog;
import com.wondersgroup.special.entity.User;
import com.wondersgroup.special.entity.Version;
import com.wondersgroup.special.utils.PrefUtil;
import com.wondersgroup.special.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;

public class LoginActivity extends BaseActivity {
    private TextView mButtonLogin;
    private EditText mEditName;
    private EditText mEditPassword;
    private DownloadManager manager;
    private String version = "", serverVersion = "", downloadUrl = "";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        setToolBarVisible(false);
        mButtonLogin = (TextView) this.findViewById(R.id.button_login);
        mEditName = (EditText) this.findViewById(R.id.edit_name);
        mEditPassword = (EditText) this.findViewById(R.id.edit_password);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        String name = PrefUtil.getString(AppApplication.getInstance(), Constant.Extra.NAME);
        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        if (!TextUtils.isEmpty(name)) {
            mEditName.setText(name);
            mEditName.setSelection(name.length());
        }
        mButtonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (serverVersion.equals(version)) {
                    login();
                } else {
                    showMsg("请更新应用到最新版本!");
                }
            }
        });
        checkNewVersion();
    }

    /**
     * 验证用户名
     *
     * @return
     */
    private boolean validateName() {
        String name = mEditName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showMsg(Tips.USER_NAME_CAN_NOT_BE_EMPTY);
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     *
     * @return
     */
    private boolean validatePwd() {
        String password = mEditPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            showMsg(Tips.PASSWORD_CAN_NOT_BE_EMPTY);
            return false;
        }
        return true;
    }

    private void login() {
        String name = mEditName.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        if (validateName() && validatePwd()) {
            String url = UrlConstant.LOGIN + "?portal_login_loginName=" + name + "&portal_login_password=" + password;
            OkHttpUtils.post().url(url).addParams("phoneNo", Utils.getDevicesSN()).build().execute(new ResponseCallBack<User>() {
                @Override
                public void onBefore(Request request) {
                    super.onBefore(request);
                    showLoadingDialog();
                }

                @Override
                public void onAfter() {
                    super.onAfter();
                    dismissLoadingDialog();
                }

                @Override
                public void onError(Call call, Exception e) {
                    super.onError(call, e);
                    showMsg(e.getMessage());
                }

                @Override
                public void onResponse(User t) {
                    super.onResponse(t);
                    if (null != t) {
                        Utils.saveUser(t);
                        PrefUtil.putString(AppApplication.getInstance(), Constant.Extra.NAME, mEditName.getText().toString().trim());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }
            });
        }
    }

    private void checkNewVersion() {
        PackageManager manager = this.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            version = info.versionName;
            serverVersion = version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        OkHttpUtils.get().url(UrlConstant.VERSION).build().execute(new ResponseCallBack<Version>() {
            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                showLoadingDialog();
            }

            @Override
            public void onAfter() {
                super.onAfter();
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(Version t) {
                super.onResponse(t);
                if (null != t) {
                    if (t != null) {
                        serverVersion = t.getVersion();
                        if (!t.getVersion().equals(version)) {
                            try {
                                showUpdateDialog(t.getDownLoadAddress());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
//                showMsg(e.getMessage());
            }
        });
    }

    private void showUpdateDialog(String url) {
        downloadUrl = url;
        PromptDialog dialog = new PromptDialog().setTitle("发现新版本").setMessage("确定要更新吗?")
                .setPositiveButton("立即更新", new PromptDialog.OnClickListener() {

                    @Override
                    public void onClick(int which) {
                        validateWriteExternalStorage();
                    }
                }).setNegativeButton("以后再说", new PromptDialog.OnClickListener() {

                    @Override
                    public void onClick(int which) {
                    }
                });
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager());
    }

    /**
     * 保存文件
     */
    private void downloadUrl() {
        if (!TextUtils.isEmpty(downloadUrl))
            setDownloadManager(downloadUrl);
    }

    private void setDownloadManager(String url) {
        // 创建下载请求
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("特种设备");
        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        // 不显示下载界面
        request.setVisibleInDownloadsUi(false);
        long name = System.currentTimeMillis();
        // 设置下载后文件存放的位置
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name + ".apk");
        // System.out.println(Environment.DIRECTORY_DOWNLOADS);
        // 将下载请求放入队列
        manager.enqueue(request);
    }

    private void showMessageOKCancel(String message, PromptDialog.OnClickListener okListener) {
        PromptDialog dialog = new PromptDialog().setMessage(message).setPositiveButton("确定", okListener)
                .setNegativeButton("取消", null);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager());
    }

    protected void validateWriteExternalStorage() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showMessageOKCancel("该权限用于保存本地文件", new PromptDialog.OnClickListener() {

                    @Override
                    public void onClick(int which) {
                        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                });
                // Show an expanation to the user *asynchronously* -- don't
                // block
                // this thread waiting for the user's response! After the
                // user
                // sees the explanation, try again to request the
                // permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            downloadUrl();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadUrl();
                }
                break;

            default:
                break;
        }
    }
}
