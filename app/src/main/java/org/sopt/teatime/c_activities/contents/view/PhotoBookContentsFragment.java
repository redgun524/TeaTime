package org.sopt.teatime.c_activities.contents.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.c_activities.write.content.model.ContentTemplateIds;
import org.sopt.teatime.c_activities.write.register.component.PreviewTypefaceImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JH on 2016-07-07.
 */
public class PhotoBookContentsFragment extends Fragment {
/*
    @BindView(R.id.imgContent_Contents)
    ImageView imgContent_Contents;
    @BindView(R.id.txtContents_Contents)
    TextView txtContents_Contents;*/
    @BindView(R.id.framelayout_Contents)
    FrameLayout frameLayout;

    public static PhotoBookContentsFragment init(Contents contents, PreviewTypefaceImpl previewTypeface) {               //adapter에서 전달한 값을 다시 onCreateView에 전달 예정
        PhotoBookContentsFragment frag = new PhotoBookContentsFragment();

        Bundle args = new Bundle();
        args.putParcelable("contents",contents);
        args.putSerializable("typeface", previewTypeface);
        frag.setArguments(args);

        return frag;
    }

    public void onCreate(Bundle savedInstanceState) {                           //생성함.
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.photobook_contents, container, false);                        //fragment를 inflate

        ButterKnife.bind(this,rootView);

        Contents contents = getArguments().getParcelable("contents");
        PreviewTypefaceImpl previewTypeface = (PreviewTypefaceImpl) getArguments().getSerializable("typeface");
        ContentTemplateIds ids = new ContentTemplateIds(getContext(), contents.template_code);
        View v =inflater.inflate(ids.layoutId, frameLayout);

        ImageView imgPicture = (ImageView)v.findViewById(ids.imgPicId);
        TextView txtTitle = (TextView)v.findViewById(ids.txtDescId);
        txtTitle.setText(contents.description);
        Glide.with(getContext())
                .load(contents.url)
                .into(imgPicture);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Float titleFontSize = txtTitle.getTextSize() * Float.valueOf("0.7");
  txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleFontSize);
        txtTitle.setTypeface(previewTypeface.getTypeFace(FontController.getPathByCode(contents.font_code)));
        txtTitle.setTextColor(FontController.getResIdByCode(contents.font_color));

        Log.i("MyContents","값은"+contents.description);

        return rootView;
    }

}
