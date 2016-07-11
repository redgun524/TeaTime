package org.sopt.teatime.a_others;

import android.app.Application;
import android.graphics.Typeface;

import com.google.gson.GsonBuilder;

import org.sopt.teatime.a_others.function.FontController;

import java.util.HashMap;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ApplicationController extends Application {

    private static ApplicationController instance;
    private static String baseUrl = "http://52.78.69.215:3000/";
    private static String s3Url = "https://s3.ap-northeast-2.amazonaws.com/sqoo/";
    public static String getS3Url() {
        return s3Url;
    }

    private HashMap<String, Typeface> typefaces;
    private NetworkService networkService;

    public static ApplicationController getInstance() {
        return instance;
    }

    public NetworkService getNetworkService() {
        return networkService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
        this.buildService();
    }

    private void buildService() {

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setDateFormat("yyyy-mm-dd").create()))
                .build();

        networkService = retrofit.create(NetworkService.class);
    }

    public void buildTypefaces() {
        typefaces = new HashMap<>();

        Typeface typeface_ng = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_GOTHIC);
        Typeface typeface_ngb = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_GOTHIC_BOLD);
        Typeface typeface_nbg = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_BARUN_GOTHIC);
        Typeface typeface_nb = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_BRUSH);
        Typeface typeface_nm = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_MYEONGJO);
        Typeface typeface_np = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_PEN);
        Typeface typeface_jua = Typeface.createFromAsset(getAssets(), FontController.PATH_JUA);

        typefaces.put(String.valueOf(FontController.FONT_NANUM_GOTHIC), typeface_ng);
        typefaces.put(String.valueOf(FontController.FONT_NANUM_GOTHIC_BOLD), typeface_ngb);
        typefaces.put(String.valueOf(FontController.FONT_NANUM_BARUN_GOTHIC), typeface_nbg);
        typefaces.put(String.valueOf(FontController.FONT_NANUM_BRUSH), typeface_nb);
        typefaces.put(String.valueOf(FontController.FONT_NANUM_MYEONGJO), typeface_nm);
        typefaces.put(String.valueOf(FontController.FONT_NANUM_PEN), typeface_np);
        typefaces.put(String.valueOf(FontController.FONT_JUA), typeface_jua);

    }

    public HashMap<String, Typeface> getTypeface() {
        if (typefaces == null) {
            buildTypefaces();
            return typefaces;
        } else {
            return typefaces;
        }
    }
}
