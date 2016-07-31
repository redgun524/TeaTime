package org.sopt.teatime.b_model.network;

import org.sopt.teatime.a_others.network.ApplicationController;
import org.sopt.teatime.a_others.network.NetworkService;
import org.sopt.teatime.a_others.function.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.Contents;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by JH on 2016-07-02.
 */
public class ContentsModel {

    NetworkService networkService;
    TeaTimePresenter presenter;

    public ContentsModel(TeaTimePresenter presenter) {
        networkService = ApplicationController.getInstance().getNetworkService();
        this.presenter = presenter;
    }

    //콘텐츠들 받아오기
    public void getContentsListFromServer(){
        Call<List<Contents>> callContentsList = networkService.getContentsList();
        callContentsList.enqueue(new Callback<List<Contents>>() {
            @Override
            public void onResponse(Response<List<Contents>> response, Retrofit retrofit) {
                presenter.getListFromModel(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.networkFailed();
            }
        });
    }

}

