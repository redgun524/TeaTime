package org.sopt.teatime.b_model.network;

import org.sopt.teatime.a_others.network.ApplicationController;
import org.sopt.teatime.a_others.network.NetworkService;
import org.sopt.teatime.a_others.function.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.domain.PhotoBook;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class PhotoBookModel {

    TeaTimePresenter presenter;
    NetworkService networkService;

    public PhotoBookModel(TeaTimePresenter presenter) {
        networkService = ApplicationController.getInstance().getNetworkService();
        this.presenter = presenter;
    }

    public void getPhotoBookListFromServer(){
        Call<List<Cover>> callCoverList = networkService.getCoverList();
        callCoverList.enqueue(new Callback<List<Cover>>() {
            @Override
            public void onResponse(Response<List<Cover>> response, Retrofit retrofit) {
                presenter.getListFromModel(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.networkFailed();
            }
        });
    }

    public void getPhotoBookFromServer() {
        Call<PhotoBook> callPhotoBook = networkService.getPhotoBook(17);
        callPhotoBook.enqueue(new Callback<PhotoBook>() {
            @Override
            public void onResponse(Response<PhotoBook> response, Retrofit retrofit) {
                presenter.getObjectFromModel(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.networkFailed();
            }
        });
    }

}
