package com.wondersgroup.special;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Tips;
import com.wondersgroup.special.entity.BaseResult;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chan on 6/15/16.
 */
public abstract class ResponseCallBack<T> extends Callback<T> {
    protected Class<T> mType;
    private BaseResult mBaseResult;
    private Handler mDelivery;

    public ResponseCallBack() {
        Type genType = getClass().getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            try {
                mType = ((Class<T>) (((ParameterizedType) (genType)).getActualTypeArguments()[0]));
            } catch (ClassCastException e) {

            }
        }
        if (mType == null) {
            genType = getClass().getSuperclass().getGenericSuperclass();
            if (genType instanceof ParameterizedType) {
                try {
                    mType = ((Class<T>) (((ParameterizedType) (genType)).getActualTypeArguments()[0]));
                } catch (ClassCastException e) {

                }
            }
        }
        mDelivery = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onBefore(Request request) {
        super.onBefore(request);
    }

    @Override
    public void onAfter() {
        super.onAfter();
    }

    @Override
    public void inProgress(float progress) {
        super.inProgress(progress);
    }

    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        String result = response.body().string();
        Log.e("result", result);
        Gson gson = createGson();
        T t = null;
        if (isGoodJson(result)) {
            try {
                mBaseResult = gson.fromJson(result, BaseResult.class);
                if (Constant.BackCode.CODE_ZERO == mBaseResult.getCode()) {
                    if (mBaseResult.getObject().isJsonObject()) {
                        t = gson.fromJson(mBaseResult.getObject(), mType);
                    } else if (mBaseResult.getObject().isJsonArray()) {
                        // 生成List<T> 中的 List<T>
                        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{mType});
                        final ArrayList<T> ts = gson.fromJson(mBaseResult.getObject(), listType);
                        mDelivery.post(new Runnable() {
                            @Override
                            public void run() {
                                onResponse(ts);
                            }
                        });
                    }
                } else {
                    mDelivery.post(new Runnable() {
                        @Override
                        public void run() {
                            onError(null, new Exception(mBaseResult.getMessage()));
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                        onError(null, new Exception(Tips.INTERNAL_SERVER_ERROR));
                    }
                });
            }
        } else {
            mDelivery.post(new Runnable() {
                @Override
                public void run() {
                    onError(null, new Exception(Tips.INTERNAL_SERVER_ERROR));
                }
            });
        }
        return t;
    }

    @Override
    public void onError(Call call, Exception e) {
    }

    @Override
    public void onResponse(T t) {
    }

    public void onResponse(List<T> t) {

    }

    /**
     * 是否是json
     *
     * @param rs
     * @return
     */
    public static boolean isGoodJson(String rs) {
        if (TextUtils.isEmpty(rs)) {
            return false;
        }
        try {
            new JsonParser().parse(rs);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }

    /**
     * 需要对int进行处理，但有可能返回空字符串，空字符串返回0
     *
     * @return
     */
    public static Gson createGson() {
        Gson gson = new GsonBuilder().registerTypeAdapter(int.class, new JsonDeserializer<Integer>() {
            @Override
            public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                try {
                    return json.getAsInt();
                } catch (Exception e) {
                    return 0;
                }
            }
        }).create();
        return gson;
    }

    public static class ParameterizedTypeImpl implements ParameterizedType {
        private final Class raw;
        private final Type[] args;

        public ParameterizedTypeImpl(Class raw, Type[] args) {
            this.raw = raw;
            this.args = args != null ? args : new Type[0];
        }

        @Override
        public Type[] getActualTypeArguments() {
            return args;
        }

        @Override
        public Type getRawType() {
            return raw;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
