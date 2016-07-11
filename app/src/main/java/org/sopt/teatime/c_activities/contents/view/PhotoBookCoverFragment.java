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
import org.sopt.teatime.a_others.ApplicationController;
import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.c_activities.write.cover.model.CoverTemplateId;
import org.sopt.teatime.c_activities.write.register.component.PreviewTypefaceImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JH on 2016-07-07.
 */
public class PhotoBookCoverFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.imgContentsCover_ContentsCover)
    ImageView imgContentsCover;
    @BindView(R.id.txtHeart_ContentsCover)
    TextView txtHeartCount;
    @BindView(R.id.txtCommentCount_ContentsCover)
    TextView txtCommentCount;
    @BindView(R.id.txtScrapCount_ContentsCover)
    TextView txtScrapCount;
    @BindView(R.id.framelayout_ContensCover)
    FrameLayout frameLayout;

    public static PhotoBookCoverFragment init(Cover cover, PreviewTypefaceImpl previewTypeface) {               //adapter에서 전달한 값을 다시 onCreateView에 전달 예정

        PhotoBookCoverFragment frag = new PhotoBookCoverFragment();
        Bundle args = new Bundle();
        args.putParcelable("photoBook",cover);
        args.putSerializable("typeface", previewTypeface);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {                           //생성함.
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.photobook_contents_cover, container, false);                        //fragment를 inflate
        ButterKnife.bind(this, rootView);
        Cover cover = getArguments().getParcelable("photoBook");
        PreviewTypefaceImpl previewTypeface = (PreviewTypefaceImpl) getArguments().getSerializable("typeface");
        CoverTemplateId ids = new CoverTemplateId(getContext(), cover.template_code);
        View v =inflater.inflate(ids.layoutId, frameLayout);

        ImageView imgPicture = (ImageView)v.findViewById(ids.imgPicId);
        TextView txtTitle = (TextView)v.findViewById(ids.txtTitleId);
        TextView txtSubTitle = (TextView)v.findViewById(ids.txtSubTitleId);
        TextView txtKeyword1 = (TextView)v.findViewById(ids.txtKeywordId1);
        TextView txtKeyword2 = (TextView)v.findViewById(ids.txtKeywordId2);
        TextView txtKeyword3 = (TextView)v.findViewById(ids.txtKeywordId3);
        TextView txtAuthor = (TextView)v.findViewById(ids.txtAuthorId);
        txtTitle.setText(cover.title);
        txtSubTitle.setText(cover.subtitle);
        txtKeyword1.setText(cover.key1);
        txtKeyword2.setText(cover.key2);
        txtKeyword3.setText(cover.key3);
        txtAuthor.setText(cover.author_nick);

        Glide.with(getContext())
                .load(ApplicationController.getS3Url() + cover.url)
                .into(imgPicture);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Float ratio = Float.intBitsToFloat(frameLayout.getHeight()) /  Float.intBitsToFloat(metrics.heightPixels);
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTitle.getTextSize());
        txtSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtSubTitle.getTextSize());
        txtKeyword1.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtKeyword1.getTextSize());
        txtKeyword2.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtKeyword1.getTextSize());
        txtKeyword3.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtKeyword1.getTextSize());
        txtAuthor.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtAuthor.getTextSize());
        txtTitle.setTypeface(previewTypeface.getTypeFace(FontController.getPathByCode(cover.title_font_code)));
        txtSubTitle.setTypeface(previewTypeface.getTypeFace(FontController.getPathByCode(cover.title_font_code)));
        txtKeyword1.setTypeface(previewTypeface.getTypeFace(FontController.getPathByCode(cover.title_font_code)));
        txtKeyword2.setTypeface(previewTypeface.getTypeFace(FontController.getPathByCode(cover.title_font_code)));
        txtKeyword3.setTypeface(previewTypeface.getTypeFace(FontController.getPathByCode(cover.title_font_code)));
        txtAuthor.setTypeface(previewTypeface.getTypeFace(FontController.getPathByCode(cover.title_font_code)));
        txtTitle.setTextColor(FontController.getResIdByCode(cover.getTitle_color()));
        txtSubTitle.setTextColor(FontController.getResIdByCode(cover.getTitle_color()));

        return rootView;
    }

    @Override
    public void onClick(View view) {                    //클릭시 이벤트 처리
        //Toast.makeText(getContext(),"눌림",Toast.LENGTH_SHORT).show();
        Log.i("whatup","눌렸어");
    }


}
