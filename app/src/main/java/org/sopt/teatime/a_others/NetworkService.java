package org.sopt.teatime.a_others;

import com.squareup.okhttp.RequestBody;

import org.sopt.teatime.b_model.domain.Comment;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.domain.Like;
import org.sopt.teatime.b_model.domain.PhotoBook;
import org.sopt.teatime.b_model.domain.Scrap;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.http.Query;

public interface NetworkService {

    /******************
     * 로그인
     ******************/

    /*@GET("/session/abbbb")
    Call<User> getObject();

    @GET("/session/abc")     //"/sessions/list"
    Call<List<User>> getPhotoBookList();*/

    @GET("/photo/main") //PhotoCard
    Call<List<Cover>> getCoverList();

    //하나의 포토북 데이터 전부를 가져옴
    @GET("/photo/get_photobook")
    Call<PhotoBook> getPhotoBook(@Query("id") int id);

    //포토북 업로드
    @Multipart
    @POST("/photo/upload")
    Call<Object> sendPhotoBook(@PartMap() Map<String, List<RequestBody>> partMap,
                               @Part("object") PhotoBook photoBook);

    @Multipart
    @POST("/photo/upload/test2")
    Call<Object> sendPhotoBook2(@PartMap() Map<String, List<RequestBody>> partMap);

    @POST("/photo/upload/test3")
    Call<Object> sendPhotoBookOnly(@Body PhotoBook photoBook);

    @GET("/photo/get_contents")  //Contents 받아오기
    Call<List<Contents>> getContentsList();
//    //Call<List<Contents>> getContentsList(@Query("id") int photobook_id);

    @GET("/comment/load")   //해당하는 Comment 불러오기
    Call<List<Comment>> getCommentList(@Query("id") int id);

    @POST("/comment/upload")       //Comment작성하기
    Call<Comment> postComment(@Body Comment comment);

    @GET("/photo/like_photo")   //좋아요 버튼
    Call<Like> getLike(@Query("id") int id);

    @GET("/photo/scrap_photo")      //스크랩 버튼
    Call<Scrap> getScrap(@Query("id") int id);
    @POST("/profile")
    Call<Object> sendSitterProfile();

    @POST("/session")
    Call<Comment> newComment(@Body Comment comment);

    @GET("/photo/search/")
    Call<List<Cover>> getPhotoBookList(@Query("key") String key);

    @GET("/photo/scrap_page")
    Call<List<Cover>> getScrapList();

    @GET("/photo/my_page")
    Call<List<Cover>> getMyBookList();
}
