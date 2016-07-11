package org.sopt.teatime.c_activities.write.cover.model;

import android.content.Context;

import java.util.Locale;

/**
 * Created by 품파파품파 on 2016-07-06.
 */
public class CoverTemplateId {
    public int layoutId;
    public int imgPicId;
    public int txtTitleId;
    public int txtSubTitleId;
    public int txtKeywordId1;
    public int txtKeywordId2;
    public int txtKeywordId3;
    public int txtAuthorId;

    public CoverTemplateId(Context ctx, int code) {

        String templateLayout = String.format(Locale.KOREA, "template_cover_%d", code);
        String imgPic = String.format(Locale.KOREA, "imgPic_cover_%d", code);
        String txtTitle = String.format(Locale.KOREA, "txtTitle_cover_%d", code);
        final String txtSubTitle = String.format(Locale.KOREA, "txtSubTitle_cover_%d", code);
        final String txtKeyword1 = String.format(Locale.KOREA, "txtKeyword_cover_%d_1", code);
        final String txtKeyword2 = String.format(Locale.KOREA, "txtKeyword_cover_%d_2", code);
        final String txtKeyword3 = String.format(Locale.KOREA, "txtKeyword_cover_%d_3", code);
        final String txtAuthor = String.format(Locale.KOREA, "txtAuthor_cover_%d", code);
        this.layoutId = ctx.getResources().getIdentifier(templateLayout, "layout", ctx.getPackageName());
        this.imgPicId = ctx.getResources().getIdentifier(imgPic, "id", ctx.getPackageName());
        this.txtTitleId = ctx.getResources().getIdentifier(txtTitle, "id", ctx.getPackageName());
        this.txtSubTitleId = ctx.getResources().getIdentifier(txtSubTitle, "id", ctx.getPackageName());
        this.txtKeywordId1 = ctx.getResources().getIdentifier(txtKeyword1, "id", ctx.getPackageName());
        this.txtKeywordId2 = ctx.getResources().getIdentifier(txtKeyword2, "id", ctx.getPackageName());
        this.txtKeywordId3 = ctx.getResources().getIdentifier(txtKeyword3, "id", ctx.getPackageName());
        this.txtAuthorId = ctx.getResources().getIdentifier(txtAuthor, "id", ctx.getPackageName());
    }
}
