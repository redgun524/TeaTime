package org.sopt.teatime.c_activities.main.component;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.sopt.teatime.R;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.view_object.CoverViewObject;
import org.sopt.teatime.c_activities.contents.view.PhotoBookActivity;
import org.sopt.teatime.c_activities.write.cover.model.CoverTemplateId;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JH on 2016-06-30.
 */
public class CoverFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.frameLayout_preview)
    FrameLayout frameLayout;

    static CoverFragment init(Cover cover) {               //adapter에서 전달한 값을 다시 onCreateView에 전달 예정
        CoverFragment frag = new CoverFragment();
        Bundle args = new Bundle();
        args.putParcelable("cover",cover);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {                           //생성함.
        super.onCreate(savedInstanceState);
        //   fragVal = getArguments() != null ? getArguments().getInt("val") : 1;       //값 설정방법 예시
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Cover cover = getArguments().getParcelable("cover");

        View rootView = inflater.inflate(R.layout.item_preview, container, false);
        ButterKnife.bind(this, rootView);

        CoverTemplateId ids = new CoverTemplateId(getContext(), cover.template_code);
        View v =inflater.inflate(ids.layoutId, frameLayout);
        CoverViewObject coverView = new CoverViewObject(v, ids);
        coverView.setCoverDatas(getActivity(), cover, "1.0");

        rootView.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {                                //viewPager의 클릭 리스너 설정은 fragment에서 해주면 된다.
                                                                        //TODO 1.화면 클릭시 실행되는 이벤트 구현(메인에서), 값을 넘겨준다

        Intent intent =  new Intent(getActivity(), PhotoBookActivity.class);

        //PhotoCard photoCard=(PhotoCard)().getSerializable("PhotoCard");

//        Log.i("Second","photoCard 확인 "+photoCard.title);


        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
