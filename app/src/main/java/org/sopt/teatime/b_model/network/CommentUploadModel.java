package org.sopt.teatime.b_model.network;

import android.util.Log;

import org.sopt.teatime.a_others.network.ApplicationController;
import org.sopt.teatime.a_others.network.NetworkService;
import org.sopt.teatime.b_model.domain.Comment;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by JH on 2016-07-07.
 */
public class CommentUploadModel {

    //    TeaTimePresenter presenter;
    NetworkService networkService;

    public CommentUploadModel() {
        networkService = ApplicationController.getInstance().getNetworkService();

    }

    public void postCommentModel(Comment comment){

        Call<Comment> commentUpload = networkService.postComment(comment);
        commentUpload.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Response<Comment> response, Retrofit retrofit) {

                int statusCode = response.code();
                if(response.isSuccess()){
                    Log.i("MyCommentup","성공적임");

                }
                else {
                    statusCode = response.code();
                    Log.i("MyCommentup","망했어요");
                }


            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }


}
