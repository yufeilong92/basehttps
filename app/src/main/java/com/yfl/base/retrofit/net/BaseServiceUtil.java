package com.yfl.base.retrofit.net;

import android.text.TextUtils;
import com.yfl.base.retrofit.GsonFactory.GsonDConverterFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by cuiyan on 16/6/3 11:07
 * Email: cuiyan@rqb.com
 */
public class BaseServiceUtil {
    private static final int DEFAULT_TIMEOUT = 10;
//    private static final CommonInterceptor interceptor = new CommonInterceptor();
//    private static final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
//            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//    // Retrofit要求baseUrl以 '/' 为结尾
//    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    public static synchronized <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }
    public static Gson buildGson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
        return  gson;
    }
    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        CommonInterceptor interceptor = new CommonInterceptor();
        HashMap<String, String> map = new HashMap<>();
        map.put("client","android");
        CommonInterceptor.setCommonParam(map);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        // Retrofit要求baseUrl以 '/' 为结尾
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(new GsonDConverterFactory(buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        if (!TextUtils.isEmpty(baseUrl)) {
            retrofitBuilder.baseUrl(baseUrl);
       } // else {
//            retrofitBuilder.baseUrl(BuildConfig.BASE_URL);
//        }
        clientBuilder.interceptors().clear();
        clientBuilder.interceptors().add(interceptor);

        // 设置证书
//        try {
//            clientBuilder.sslSocketFactory(RqbTrustManager.getInstance().getSSLSocketFactory("BKS", R.raw.rqb_ssl));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        OkHttpClient client = clientBuilder.build();
        Retrofit retrofit = retrofitBuilder.client(client).build();
        return retrofit.create(serviceClass);
    }

}
