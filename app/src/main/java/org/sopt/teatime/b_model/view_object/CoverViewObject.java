package org.sopt.teatime.b_model.view_object;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.a_others.network.ApplicationController;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.c_activities.write.cover.model.CoverTemplateId;

/**
 * Created by 품파파품파 on 2016-07-10.
 */
public class CoverViewObject {

    public ImageView imgPic;
    public TextView txtTitle;
    public TextView txtSubTitle;
    public TextView txtKeyword1;
    public TextView txtKeyword2;
    public TextView txtKeyword3;
    public TextView txtAuthor;

    private Cover cover;

    public CoverViewObject(View v, CoverTemplateId ids) {
        imgPic = (ImageView)v.findViewById(ids.imgPicId);
        txtTitle = (TextView)v.findViewById(ids.txtTitleId);
        txtSubTitle = (TextView)v.findViewById(ids.txtSubTitleId);
        txtKeyword1 = (TextView)v.findViewById(ids.txtKeywordId1);
        txtKeyword2 = (TextView)v.findViewById(ids.txtKeywordId2);
        txtKeyword3 = (TextView)v.findViewById(ids.txtKeywordId3);
        txtAuthor = (TextView)v.findViewById(ids.txtAuthorId);

    }

    public void setCoverDatas(Context ctx, Cover cover, String ratio) {

        this.cover = cover;
        Log.i("MyTag", "ratio : " + ratio);
        setDatas(ctx);
        setTextSizes(ratio);
        setTextColors(ctx);
        setTextFonts();
    }

    public void setDatas(Context ctx) {
        txtTitle.setText(cover.title);
        txtSubTitle.setText(cover.subtitle);
        txtKeyword1.setText(cover.key1);
        txtKeyword2.setText(cover.key2);
        txtKeyword3.setText(cover.key3);
        txtAuthor.setText(cover.author_nick);

        Glide.with(ctx)
                .load(cover.url)
                .into(imgPic);
    }

    public void setTextSizes(String strRatio) {
        //DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        Float ratio = Float.valueOf(strRatio);
        Float titleFontSize = txtTitle.getTextSize() * ratio;
        Float subTitleFontSize = txtSubTitle.getTextSize() * ratio;
        Float keywordFontSize = txtKeyword1.getTextSize() * ratio;
        Float authorFontSize = txtAuthor.getTextSize() * ratio;

        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleFontSize);
        txtSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, subTitleFontSize);
        txtKeyword1.setTextSize(TypedValue.COMPLEX_UNIT_PX, keywordFontSize);
        txtKeyword2.setTextSize(TypedValue.COMPLEX_UNIT_PX, keywordFontSize);
        txtKeyword3.setTextSize(TypedValue.COMPLEX_UNIT_PX, keywordFontSize);
        txtAuthor.setTextSize(TypedValue.COMPLEX_UNIT_PX, authorFontSize);
    }

    public void setTextColors(Context ctx) {
        int resId = FontController.getResIdByCode(cover.getTitle_color());

        txtTitle.setTextColor(getColorFromRes(ctx, resId));
        txtSubTitle.setTextColor(getColorFromRes(ctx, resId));
        txtKeyword1.setTextColor(getColorFromRes(ctx, resId));
        txtKeyword2.setTextColor(getColorFromRes(ctx, resId));
        txtKeyword3.setTextColor(getColorFromRes(ctx, resId));
        txtAuthor.setTextColor(getColorFromRes(ctx, resId));
    }

    public void setTextFonts() {
        String code = String.valueOf(cover.title_font_code);
        txtTitle.setTypeface(ApplicationController.getInstance().getTypeface().get(code));
        txtTitle.setTypeface(ApplicationController.getInstance().getTypeface().get(code));
        txtSubTitle.setTypeface(ApplicationController.getInstance().getTypeface().get(code));
        txtKeyword1.setTypeface(ApplicationController.getInstance().getTypeface().get(code));
        txtKeyword2.setTypeface(ApplicationController.getInstance().getTypeface().get(code));
        txtKeyword3.setTypeface(ApplicationController.getInstance().getTypeface().get(code));
        txtAuthor.setTypeface(ApplicationController.getInstance().getTypeface().get(code));
    }

    private int getColorFromRes(Context ctx, int resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ctx.getResources().getColor(resId, ctx.getTheme());
        }
        else{
            return ctx.getResources().getColor(resId);
        }
    }
}
