package org.sopt.teatime.c_activities.write.content.model;

import android.content.Context;

import java.util.Locale;

/**
 * Created by 품파파품파 on 2016-07-06.
 */
public class ContentTemplateIds {
    public int layoutId;
    public int imgPicId;
    public int txtDescId;

    public ContentTemplateIds(Context ctx, int code) {

        String templateLayout = String.format(Locale.KOREA, "template_%d", code);
        String imgPic = String.format(Locale.KOREA, "imgPic_%d", code);
        String txtDesc = String.format(Locale.KOREA, "txtExplain_%d", code);
        this.layoutId = ctx.getResources().getIdentifier(templateLayout, "layout", ctx.getPackageName());
        this.imgPicId = ctx.getResources().getIdentifier(imgPic, "id", ctx.getPackageName());
        this.txtDescId = ctx.getResources().getIdentifier(txtDesc, "id", ctx.getPackageName());
    }
}
