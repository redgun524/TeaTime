package org.sopt.teatime.a_others.function;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;

/**
 * 단위 변환 및 Resource를 활용하기 위해 만든 클래스입니다.
 */

public class ResourceController {

    //dp값을 넣어 pixel로 반환합니다.
    public static int getPixelFromDP(Context ctx, int dpValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) dpValue, ctx.getResources().getDisplayMetrics());
    }

    //resourceId를 통해 색을 가져옵니다.
    public static int getColorFromRes(Context ctx, int resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ctx.getResources().getColor(resId, ctx.getTheme());
        }
        else{
            return ctx.getResources().getColor(resId);
        }
    }

    //resourceId를 통해 문자열을 가져옵니다.
    public static String getStringFromRes(Context ctx, int resId){
        return ctx.getResources().getString(resId);
    }
}
