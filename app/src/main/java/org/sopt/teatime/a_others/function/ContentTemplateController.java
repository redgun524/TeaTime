package org.sopt.teatime.a_others.function;

import org.sopt.teatime.R;

/**
 * Created by 품파파품파 on 2016-07-06.
 */
public class ContentTemplateController {

    public final static int TEMPLETE_BLACK_1 = 101;
    public final static int TEMPLETE_BLACK_2 = 102;
    public final static int TEMPLETE_BLACK_3 = 103;
    public final static int TEMPLETE_200_1 = 201;
    public final static int TEMPLETE_200_2 = 202;
    public final static int TEMPLETE_200_3 = 203;
    public final static int TEMPLETE_300_1 = 301;
    public final static int TEMPLETE_300_2 = 302;
    public final static int TEMPLETE_300_3 = 303;
    public final static int TEMPLETE_300_4 = 304;
    public final static int TEMPLETE_400_1 = 401;
    public final static int TEMPLETE_400_2 = 402;
    public final static int TEMPLETE_400_3 = 403;


    public final static int ID_TEMPLATE_101 = R.layout.template_101;
    public final static int ID_TEMPLATE_102 = R.layout.template_102;
    public final static int ID_TEMPLATE_103 = R.layout.template_103;
    public final static int ID_TEMPLATE_201 = R.layout.template_201;
    public final static int ID_TEMPLATE_202 = R.layout.template_202;
    public final static int ID_TEMPLATE_203 = R.layout.template_203;
    public final static int ID_TEMPLATE_301 = R.layout.template_301;
    public final static int ID_TEMPLATE_302 = R.layout.template_302;
    public final static int ID_TEMPLATE_303 = R.layout.template_303;
    public final static int ID_TEMPLATE_304 = R.layout.template_304;
    public final static int ID_TEMPLATE_401 = R.layout.template_401;
    public final static int ID_TEMPLATE_402 = R.layout.template_402;
    public final static int ID_TEMPLATE_403 = R.layout.template_403;

    public static int getCodeById(int id) {
        switch (id) {
            case ID_TEMPLATE_101 :
                return TEMPLETE_BLACK_1;
            case ID_TEMPLATE_102 :
                return TEMPLETE_BLACK_2;
            case ID_TEMPLATE_103 :
                return TEMPLETE_BLACK_3;
            case ID_TEMPLATE_201 :
                return TEMPLETE_200_1;
            case ID_TEMPLATE_202 :
                return TEMPLETE_200_2;
            case ID_TEMPLATE_203 :
                return TEMPLETE_200_3;
            case ID_TEMPLATE_301 :
                return TEMPLETE_300_1;
            case ID_TEMPLATE_302 :
                return TEMPLETE_300_2;
            case ID_TEMPLATE_303 :
                return TEMPLETE_300_3;
            case ID_TEMPLATE_304 :
                return TEMPLETE_300_4;
            case ID_TEMPLATE_401 :
                return TEMPLETE_400_1;
            case ID_TEMPLATE_402 :
                return TEMPLETE_400_2;
            case ID_TEMPLATE_403 :
                return TEMPLETE_400_3;
            default :
                return TEMPLETE_BLACK_1;
        }
    }

    public static int getIdByCode(int code) {
        switch (code) {
            case TEMPLETE_BLACK_1:
                return ID_TEMPLATE_101;
            case TEMPLETE_BLACK_2:
                return ID_TEMPLATE_102;
            case TEMPLETE_BLACK_3:
                return ID_TEMPLATE_103;
            case TEMPLETE_200_1:
                return ID_TEMPLATE_201;
            case TEMPLETE_200_2:
                return ID_TEMPLATE_202;
            case TEMPLETE_200_3:
                return ID_TEMPLATE_203;
            case TEMPLETE_300_1:
                return ID_TEMPLATE_301;
            case TEMPLETE_300_2:
                return ID_TEMPLATE_302;
            case TEMPLETE_300_3:
                return ID_TEMPLATE_303;
            case TEMPLETE_300_4:
                return ID_TEMPLATE_304;
            case TEMPLETE_400_1:
                return ID_TEMPLATE_401;
            case TEMPLETE_400_2:
                return ID_TEMPLATE_402;
            case TEMPLETE_400_3:
                return ID_TEMPLATE_403;
            default :
                return ID_TEMPLATE_101;
        }
    }
}
