package org.sopt.teatime.c_activities.search.presenter;

import org.sopt.teatime.a_others.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.network.UserContentsModel;
import org.sopt.teatime.c_activities.search.view.SearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 아라 on 2016-07-03.
 */
public class ListPresenterImpl implements ListPresenter, TeaTimePresenter {

    SearchView view;

    public ListPresenterImpl(SearchView view) {
        this.view = view;
    }

    @Override
    public void getUserList(String key) {
        UserContentsModel model = new UserContentsModel(this);
        model.getSearchResultFromServer(key);
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
    public void getObjectFromModel(Object object) {

    }

    @Override
    public void networkFailed() {
        view.networkFailed();
    }
}