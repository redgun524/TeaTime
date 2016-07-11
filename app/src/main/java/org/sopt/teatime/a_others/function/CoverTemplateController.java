package org.sopt.teatime.a_others.function;

import org.sopt.teatime.R;

/**
 * Created by 품파파품파 on 2016-07-06.
 */
public class CoverTemplateController {

    public final static int COVER_TEMPLETE_WHITE_1 = 101;
    public final static int COVER_TEMPLETE_WHITE_2 = 102;
    public final static int COVER_TEMPLETE_200_1 = 201;
    public final static int COVER_TEMPLETE_300_1 = 301;
    public final static int COVER_TEMPLETE_400_1 = 401;
    public final static int COVER_TEMPLETE_500_2 = 502;
    public final static int COVER_TEMPLETE_500_3 = 503;


    public final static int ID_TEMPLATE_COVER_101 = R.layout.template_cover_101;
    public final static int ID_TEMPLATE_COVER_102 = R.layout.template_cover_102;
    public final static int ID_TEMPLATE_COVER_201 = R.layout.template_cover_201;
    public final static int ID_TEMPLATE_COVER_301 = R.layout.template_cover_301;
    public final static int ID_TEMPLATE_COVER_401 = R.layout.template_cover_401;
    public final static int ID_TEMPLATE_COVER_502 = R.layout.template_cover_502;
    public final static int ID_TEMPLATE_COVER_503 = R.layout.template_cover_503;

    public static int getCodeById(int id) {
        switch (id) {
            case ID_TEMPLATE_COVER_101 :
                return COVER_TEMPLETE_WHITE_1;
            case ID_TEMPLATE_COVER_102 :
                return COVER_TEMPLETE_WHITE_2;
            case ID_TEMPLATE_COVER_201 :
                return COVER_TEMPLETE_200_1;
            case ID_TEMPLATE_COVER_301 :
                return COVER_TEMPLETE_300_1;
            case ID_TEMPLATE_COVER_401 :
                return COVER_TEMPLETE_400_1;
            case ID_TEMPLATE_COVER_502 :
                return COVER_TEMPLETE_500_2;
            case ID_TEMPLATE_COVER_503 :
                return COVER_TEMPLETE_500_3;
            default :
                return COVER_TEMPLETE_WHITE_1;
        }
    }

    public static int getIdByCode(int code) {
        switch (code) {
            case COVER_TEMPLETE_WHITE_1:
                return ID_TEMPLATE_COVER_101;
            case COVER_TEMPLETE_WHITE_2:
                return ID_TEMPLATE_COVER_102;
            case COVER_TEMPLETE_200_1:
                return ID_TEMPLATE_COVER_201;
            case COVER_TEMPLETE_300_1:
                return ID_TEMPLATE_COVER_301;
            case COVER_TEMPLETE_400_1:
                return ID_TEMPLATE_COVER_401;
            case COVER_TEMPLETE_500_2:
                return ID_TEMPLATE_COVER_502;
            case COVER_TEMPLETE_500_3:
                return ID_TEMPLATE_COVER_503;
            default :
                return ID_TEMPLATE_COVER_101;
        }
    }
}
