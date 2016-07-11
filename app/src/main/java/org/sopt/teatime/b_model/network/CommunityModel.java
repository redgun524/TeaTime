package org.sopt.teatime.b_model.network;

import android.util.Log;

import org.sopt.teatime.a_others.network.ApplicationController;
import org.sopt.teatime.a_others.network.NetworkService;
import org.sopt.teatime.a_others.function.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.Comment;
import org.sopt.teatime.b_model.domain.Like;
import org.sopt.teatime.b_model.domain.Scrap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by JH on 2016-07-07.
 */
public class CommunityModel {

    NetworkService networkService;
    TeaTimePresenter presenter;

    public CommunityModel(TeaTimePresenter presenter) {
        networkService = ApplicationController.getInstance().getNetworkService();
        this.presenter = presenter;
    }

    //스크랩? 공유? 네트워킹
    public int getShareModel(int id) {

        final int[] abc = new int[1];
        abc[0] = 2;
        Call<Scrap> callScrap = networkService.getScrap(id);
        callScrap.enqueue(new Callback<Scrap>() {
            @Override
            public void onResponse(Response<Scrap> response, Retrofit retrofit) {

                Log.i("MyLike", "스크랩 실패");
//                String result = (String)response.body().)
                abc[0] = 0;
            }

            @Override
            public void onFailure(Throwable t) {

                Log.i("MyLike", "스크랩 성공");
                abc[0] = 1;
            }
        });
        return abc[0];
    }

    //좋아요 네트워킹
    public int getLikeModel(int id) {

        final int[] abc = new int[1];
        abc[0] = 2;
        Log.i("MyLike", "좋아요 누르면 실행");
        Call<Like> callLike = networkService.getLike(id);
        callLike.enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Response<Like> response, Retrofit retrofit) {

                Log.i("MyLike", "좋아요 실패");
//                String result = (String)response.body().)
                abc[0] = 0;
            }

            @Override
            public void onFailure(Throwable t) {

                Log.i("MyLike", "좋아요 성공");
                abc[0] = 1;
            }
        });
        return abc[0];
    }

    //댓글 달기 네트워크
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
