package org.sopt.teatime.c_activities.main.presenter;

import org.sopt.teatime.a_others.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.network.PhotoBookModel;
import org.sopt.teatime.c_activities.main.view.mPhotoCardView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JH on 2016-06-30.
 */
public class MainPresenterImpl implements MainListPresenter, TeaTimePresenter {

    mPhotoCardView view;

    public MainPresenterImpl(mPhotoCardView view) {
        this.view = view;
    }

    @Override
    public void getPhotoCardList() {
        PhotoBookModel photoBookModel = new PhotoBookModel(this);
        photoBookModel.getPhotoBookListFromServer();
    }

    @Override
    public void getObjectFromModel(Object object) {
    }

    @Override
    public void getListFromModel(List<?> objectList) {
        int i;
        ArrayList<Cover> coverList = new ArrayList<>();
        if (objectList == null || objectList.size() <= 0) {
            view.networkFailed();
        } else {
            for (i = 0; i < objectList.size(); i++) {
                coverList.add((Cover) objectList.get(i));
            }
            view.setDatas(coverList);
        }
    }

    @Override
    public void networkFailed() {
        view.networkFailed();
    }
}
