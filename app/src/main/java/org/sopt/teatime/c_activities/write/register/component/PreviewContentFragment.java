package org.sopt.teatime.c_activities.write.register.component;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.sopt.teatime.R;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.b_model.view_object.ContentsViewObject;
import org.sopt.teatime.c_activities.write.content.model.ContentTemplateIds;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JH on 2016-06-30.
 */
public class PreviewContentFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.frameLayout_preview)
    FrameLayout frameLayout;

    static PreviewContentFragment init(Contents contents,  PreviewTypefaceImpl previewTypeface) {               //adapter에서 전달한 값을 다시 onCreateView에 전달 예정
        PreviewContentFragment frag = new PreviewContentFragment();
        Bundle args = new Bundle();
        args.putParcelable("contents", contents);
        args.putSerializable("typeface", previewTypeface);
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

        Contents contents = getArguments().getParcelable("contents");
        ContentTemplateIds ids = new ContentTemplateIds(getContext(), contents.template_code);
        View v =inflater.inflate(ids.layoutId, frameLayout);

        ContentsViewObject contentsView = new ContentsViewObject(v, ids);
        contentsView.setContentsDatas(getActivity(), contents, "0.7");

        return rootView;
    }

    @Override
    public void onClick(View view) {
    }
}

