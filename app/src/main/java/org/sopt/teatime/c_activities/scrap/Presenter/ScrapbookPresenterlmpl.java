package org.sopt.teatime.c_activities.scrap.Presenter;

import android.util.Log;

import org.sopt.teatime.a_others.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.network.UserContentsModel;
import org.sopt.teatime.c_activities.scrap.view.ScrapbookView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 박은인 on 2016-07-02.
 */
public class ScrapbookPresenterlmpl implements ScrapbookPresenter, TeaTimePresenter {
    ScrapbookView view;

    public ScrapbookPresenterlmpl(ScrapbookView view) {
        this.view = view;
    }

    @Override
    public void getUserList() {
        UserContentsModel userContentsModel = new UserContentsModel(this);
        userContentsModel.getScrapListFromServer();
    }

    @Override
    public void getObjectFromModel(Object object) {

    }

    @Override
    public void getListFromModel(List<?> objectList) {
        ArrayList<Cover> coverList = new ArrayList<>();
        if (objectList != null) {
            if (objectList.size() == 0) {
                view.noResultEvent();
            } else {
                for(int i = 0; i < objectList.size(); i++) {
                    coverList.add((Cover)objectList.get(i));
                }
                view.setDatas(coverList);
            }
        } else {
            view.networkFailed();
        }
    }

    @Override
    public void networkFailed() {
        view.networkFailed();
    }
}
