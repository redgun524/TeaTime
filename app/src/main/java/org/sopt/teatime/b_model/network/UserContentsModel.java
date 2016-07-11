package org.sopt.teatime.b_model.network;

import org.sopt.teatime.a_others.ApplicationController;
import org.sopt.teatime.a_others.NetworkService;
import org.sopt.teatime.a_others.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.Cover;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class UserContentsModel {

    NetworkService networkService;
    TeaTimePresenter presenter;

   public UserContentsModel(TeaTimePresenter presenter) {
        networkService = ApplicationController.getInstance().getNetworkService();
        this.presenter = presenter;
    }

    public void getScrapListFromServer() {
        Call<List<Cover>> callUserList = networkService.getScrapList();
        callUserList.enqueue(new Callback<List<Cover>>() {
            @Override
            public void onResponse(Response<List<Cover>> response, Retrofit retrofit) {
                presenter.getListFromModel(response.body());
            }


            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void getMyBookListFromServer() {
        Call<List<Cover>> callUserList = networkService.getMyBookList();
        callUserList.enqueue(new Callback<List<Cover>>() {
            @Override
            public void onResponse(Response<List<Cover>> response, Retrofit retrofit) {
                presenter.getListFromModel(response.body());
            }
            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void getSearchResultFromServer(String key) {
        Call<List<Cover>> callUserList = networkService.getPhotoBookList(key);
        callUserList.enqueue(new Callback<List<Cover>>() {
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
}
