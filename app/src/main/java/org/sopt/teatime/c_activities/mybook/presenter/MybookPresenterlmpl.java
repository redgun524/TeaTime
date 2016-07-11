package org.sopt.teatime.c_activities.mybook.presenter;

import android.util.Log;

import org.sopt.teatime.a_others.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.network.UserContentsModel;
import org.sopt.teatime.c_activities.mybook.view.MybookView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 박은인 on 2016-07-02.
 */
public class MybookPresenterlmpl implements MybookPresenter, TeaTimePresenter {
    MybookView view;

    public MybookPresenterlmpl(MybookView view) {
        this.view = view;
    }

    @Override
    public void getUserList() {
        Log.i("MyTag", "2. 요청을 받은 프레젠터가 다시 모델에 데이터를 요청");
        UserContentsModel userContentsModel = new UserContentsModel(this);
        userContentsModel.getMyBookListFromServer();
    }

    /**
     * UserContentsModel 클래스에서 프레젠터에 데이터를 보내면
     * 인자로 받아온 데이터를 다시 view에 연결시켜 갱신합니다.
     */
    @Override
    public void getObjectFromModel(Object object) {

    }

    @Override
    public void getListFromModel(List<?> objectList) {
        Log.i("MyTag", "5. 모델에서 받은 데이터로 뷰를 갱신");
        ArrayList<Cover> userList = new ArrayList<>();
        if (objectList != null) {
            for(int i = 0; i < objectList.size(); i++) {
                Log.i("MyTag", ((Cover)objectList.get(i)).title);
                userList.add((Cover) objectList.get(i));
            }
            view.setDatas(userList);
        } else {
            view.networkFailed();
        }
    }

    @Override
    public void networkFailed() {
        view.networkFailed();
    }
}
