package org.sopt.teatime.a_others.function;

import android.graphics.Typeface;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.ApplicationController;

public class FontController {
    //나눔 : 100
    //배민 : 200

    public final static int FONT_NANUM_GOTHIC = 100;
    public final static int FONT_NANUM_GOTHIC_BOLD = 101;
    public final static int FONT_NANUM_BARUN_GOTHIC = 110;
    public final static int FONT_NANUM_MYEONGJO = 120;
    public final static int FONT_NANUM_BRUSH = 130;
    public final static int FONT_NANUM_PEN = 140;
    public final static int FONT_JUA = 200;

    public final static String PATH_NANUM_GOTHIC = "fonts/NanumGothic.ttf";
    public final static String PATH_NANUM_GOTHIC_BOLD = "fonts/NanumGothicBold.ttf";
    public final static String PATH_NANUM_BARUN_GOTHIC = "fonts/NanumBarunGothic.ttf";
    public final static String PATH_NANUM_MYEONGJO = "fonts/NanumMyeongjo.ttf";
    public final static String PATH_NANUM_BRUSH = "fonts/NanumBrush.ttf";
    public final static String PATH_NANUM_PEN = "fonts/NanumPen.ttf";
    public final static String PATH_JUA = "fonts/BMJUA_ttf.ttf";

    public final static float TITLE_SMALL = 24;
    public final static float TITLE_NORMAL = 30;
    public final static float TITLE_LARGE = 36;
    public final static float SUBTITLE_SMALL = 16;
    public final static float SUBTITLE_NORMAL = 20;
    public final static float SUBTITLE_LARGE = 24;
    public final static float DESC_SMALL = 14;
    public final static float DESC_NORMAL = 18;
    public final static float DESC_LARGE = 22;

    public final static int FONT_SMALL_CODE = 0;
    public final static int FONT_NORMAL_CODE = 1;
    public final static int FONT_LARGE_CODE = 2;

    public final static int FONT_WHITE_CODE = 0;
    public final static int FONT_GRAY9_CODE = 1;
    public final static int FONT_BLACK_CODE = 2;

    public final static int COLOR_WHITE_ID = R.color.white;
    public final static int COLOR_GRAY9_ID = R.color.gray9;
    public final static int COLOR_BLACK_ID = R.color.black;

    public static String getPathByCode(int code) {
        switch (code) {
            case FONT_NANUM_GOTHIC :
                return PATH_NANUM_GOTHIC;
            case FONT_NANUM_GOTHIC_BOLD :
                return PATH_NANUM_GOTHIC_BOLD;
            case FONT_NANUM_BARUN_GOTHIC :
                return PATH_NANUM_BARUN_GOTHIC;
            case FONT_NANUM_MYEONGJO :
                return PATH_NANUM_MYEONGJO;
            case FONT_NANUM_BRUSH :
                return PATH_NANUM_BRUSH;
            case FONT_NANUM_PEN :
                return PATH_NANUM_PEN;
            case FONT_JUA :
                return PATH_JUA;
            default :
                return PATH_NANUM_GOTHIC;
        }
    }

    public static Typeface getFontByCode(int code) {
        return ApplicationController.getInstance().getTypeface().get(String.valueOf(code));
    }

    public static int getCodeByFont(String path) {
        switch (path) {
            case PATH_NANUM_GOTHIC :
                return FONT_NANUM_GOTHIC;
            case PATH_NANUM_GOTHIC_BOLD :
                return FONT_NANUM_GOTHIC_BOLD;
            case PATH_NANUM_BARUN_GOTHIC :
                return FONT_NANUM_BARUN_GOTHIC;
            case PATH_NANUM_MYEONGJO :
                return FONT_NANUM_MYEONGJO;
            case PATH_NANUM_BRUSH :
                return FONT_NANUM_BRUSH;
            case PATH_NANUM_PEN :
                return FONT_NANUM_PEN;
            case PATH_JUA :
                return FONT_JUA;
            default :
                return FONT_NANUM_GOTHIC;
        }
    }

    public static int getResIdByCode(int code) {
        switch (code) {
            case FONT_WHITE_CODE :
                return COLOR_WHITE_ID;
            case FONT_GRAY9_CODE:
                return COLOR_GRAY9_ID;
            case FONT_BLACK_CODE :
                return COLOR_BLACK_ID;
            default:
                return COLOR_BLACK_ID;
        }
    }
}
