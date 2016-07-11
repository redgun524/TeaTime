package org.sopt.teatime.b_model.view_object;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.a_others.network.ApplicationController;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.c_activities.write.content.model.ContentTemplateIds;

/**
 * Created by 품파파품파 on 2016-07-10.
 */
public class ContentsViewObject {
    ImageView imgPic;
    TextView txtDesc;

    private Contents contents;

    public ContentsViewObject(View v, ContentTemplateIds ids) {
        imgPic = (ImageView)v.findViewById(ids.imgPicId);
        txtDesc = (TextView)v.findViewById(ids.txtDescId);
    }

    public void setContentsDatas(Context ctx, Contents contents, String ratio) {

        this.contents = contents;

        setDatas(ctx);
        setTextSizes(ratio);
        //setTextColors();
        //setTextFonts();
    }

    private void setDatas(Context ctx) {
        txtDesc.setText(contents.description);

        Glide.with(ctx)
                .load(contents.url)
                .into(imgPic);
    }

    private void setTextSizes(String strRatio) {
        Float ratio = Float.valueOf(strRatio);
        Float titleFontSize = txtDesc.getTextSize() * ratio;

        txtDesc.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleFontSize);
    }

    private void setTextColors() {
        int resId = contents.getFont_color();
        txtDesc.setTextColor(resId);
    }

    private void setTextFonts() {
        String code = String.valueOf(contents.getFont_code());
        txtDesc.setTypeface(ApplicationController.getInstance().getTypeface().get(code));
    }
}
