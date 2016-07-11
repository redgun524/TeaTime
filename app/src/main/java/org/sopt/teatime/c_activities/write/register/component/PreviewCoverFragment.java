package org.sopt.teatime.c_activities.write.register.component;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.view_object.CoverViewObject;
import org.sopt.teatime.c_activities.write.cover.model.CoverTemplateId;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JH on 2016-06-30.
 */
public class PreviewCoverFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.frameLayout_preview)
    FrameLayout frameLayout;

    static PreviewCoverFragment init(Cover cover) {               //adapter에서 전달한 값을 다시 onCreateView에 전달 예정
        PreviewCoverFragment frag = new PreviewCoverFragment();
        Bundle args = new Bundle();
        args.putParcelable("cover", cover);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {                           //생성함.
        super.onCreate(savedInstanceState);
        //   fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.item_preview, container, false);
        ButterKnife.bind(this, rootView);

        Cover cover = getArguments().getParcelable("cover");
        if (cover != null) {
            cover.title_color = FontController.FONT_BLACK_CODE;
            CoverTemplateId ids = new CoverTemplateId(getContext(), cover.template_code);
            View v =inflater.inflate(ids.layoutId, frameLayout);
            CoverViewObject coverView = new CoverViewObject(v, ids);
            coverView.setCoverDatas(getActivity(), cover, "0.6");

            return rootView;
        } else {
            return rootView;
        }

    }

    @Override
    public void onClick(View view) {
    }
}

