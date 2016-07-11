package org.sopt.teatime.c_activities.contents.presenter;

import android.util.Log;

import org.sopt.teatime.a_others.function.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.b_model.network.ContentsModel;
import org.sopt.teatime.c_activities.contents.view.mContentsView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JH on 2016-07-02.
 */
public class ContentsPresenterImpl implements ContentsPresenter, TeaTimePresenter {

    mContentsView view;

    public ContentsPresenterImpl(mContentsView view) {this.view = view;}

    @Override
    public void getContentsList() {

        ContentsModel contentsModel = new ContentsModel(this);
        contentsModel.getContentsListFromServer();
    }

    @Override
    public void getObjectFromModel(Object object) {

    }

    @Override
    public void getListFromModel(List<?> objectList) {

        int i;
        Log.i("MyTag", "5. 모델에서 받은 데이터로 contents의 뷰를 갱신");
        ArrayList<Contents> contentsList  = new ArrayList<>();
        if (objectList == null) {
            view.networkFailed();
        } else {
            for ( i = 0; i < objectList.size(); i++) {
                contentsList.add((Contents) objectList.get(i));
            }
            view.setDatas(contentsList);
        }
    }

    @Override
    public void networkFailed() {
        view.networkFailed();
    }
}
