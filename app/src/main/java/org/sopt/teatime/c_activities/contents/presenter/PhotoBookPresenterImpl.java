package org.sopt.teatime.c_activities.contents.presenter;

import android.util.Log;

import org.sopt.teatime.a_others.function.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.PhotoBook;
import org.sopt.teatime.b_model.network.PhotoBookModel;
import org.sopt.teatime.c_activities.main.view.mPhotoBookView;

import java.util.List;

/**
 * Created by JH on 2016-07-07.
 */
public class PhotoBookPresenterImpl implements PhotoBookPresenter, TeaTimePresenter {

    mPhotoBookView photoBookView;

    public PhotoBookPresenterImpl(mPhotoBookView photoBookView) {
        this.photoBookView = photoBookView;
    }


    @Override
    public void getPhotoBookData() {

        Log.i("MyPhoto", " Photobook에서 2. 요청을 받은 프레젠터가 다시 모델에 데이터를 요청");
        PhotoBookModel photoBookModel = new PhotoBookModel(this);
        photoBookModel.getPhotoBookFromServer();
    }

    @Override
    public void getObjectFromModel(Object object) {

        Log.i("MyPhotoBook", "PhotoBook 에서 5. 모델에서 받은 데이터로 뷰를 갱신");
        Log.i("MyPhotoBook", "PhotoBook presenter"+((PhotoBook)object).cover.title);
        photoBookView.setDatas(((PhotoBook)object));
    }

    @Override
    public void getListFromModel(List<?> objectList) {

    }

    @Override
    public void networkFailed() {

    }
}
