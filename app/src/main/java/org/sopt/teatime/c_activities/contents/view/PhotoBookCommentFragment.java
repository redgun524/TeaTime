package org.sopt.teatime.c_activities.contents.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.TeaTimePresenter;
import org.sopt.teatime.b_model.domain.Comment;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.network.CommunityModel;
import org.sopt.teatime.c_activities.contents.component.PhotoBookCommentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by JH on 2016-07-07.
 */
public class PhotoBookCommentFragment extends Fragment implements TeaTimePresenter {

    @BindView(R.id.imgProfile_comment) ImageView imgProfile_comment;
    @BindView(R.id.txtTitle_comment) TextView txtTitle_comment;
    @BindView(R.id.txtAuthor_comment) TextView txtAuthor_comment;
    @BindView(R.id.imgHeart_Comment) ImageView imgHeart_Comment;
    @BindView(R.id.txtLike_Comment) TextView txtLike_Comment;
    @BindView(R.id.imgComment_Comment) ImageView imgComment_Comment;
    @BindView(R.id.txtCommentCount_Comment) TextView txtCommentCount_Comment;
    @BindView(R.id.imgScrap_Comment) ImageView imgScrap_Comment;
    @BindView(R.id.txtScrapCount_Comment) TextView txtScrapCount_Comment;
    @BindView(R.id.listView_Comment) ListView listView_Comment;
    @BindView(R.id.heartlayout_Comment) LinearLayout heartlayout_Comment;
    @BindView(R.id.scraplayout_Comment) LinearLayout scraplayout_Comment;
    @BindView(R.id.sharelayout_Comment) LinearLayout sharelayout_Comment;
    @BindView(R.id.editText_Comment) EditText editText_Comment;
    @BindView(R.id.btnCommentWrite_Comment) Button btnCommentWrite_Comment;

  PhotoBookCommentAdapter photoBookCommentAdapter;

    public static PhotoBookCommentFragment init(Cover cover) {               //adapter에서 전달한 값을 다시 onCreateView에 전달 예정

        PhotoBookCommentFragment frag = new PhotoBookCommentFragment();
        Bundle argComment = new Bundle();
        argComment.putParcelable("cover", cover);
        frag.setArguments(argComment);
//
//        Bundle argCommentList = new Bundle();
//        Comment comment = comment_list.get(0);


        //Log.i("MyPhotoz",Integer.toString(photoBook.contents_list.size()));
        //argCommentList.putSerializable("photoBook", photoBook);

        //argCommentList.putSerializable("commentList",commentList);
        //args.putString("contents",contentsList[0].);

        //args.putSerializable("photobook",photobook);
//        args.putString("title", title);
//        args.putString("url",url);
        //args.putSerializable("photoCard",photoBookThumbnail);
        //Log.i("init",photobook.cover.title);
//        Log.i("MyTag", "전달 전 값은" + title);
//        frag.setArguments(argCommentList);
        return frag;
    }

    public void onCreate(Bundle savedInstanceState) {                           //생성함.

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.photo_comment_detail, container, false);                        //fragment를 inflate

        ButterKnife.bind(this, rootView);

        Cover cover = getArguments().getParcelable("cover");

        final CommunityModel communityModel = new CommunityModel(this);


        //photoBookCommentAdapter.setPhotoBook();
        listView_Comment = (ListView) rootView.findViewById(R.id.listView_Comment);
        listView_Comment.setAdapter(new PhotoBookCommentAdapter((getActivity())));

        //LinearLayout heartlayout_Comment = (LinearLayout) rootView.findViewById(R.id.heartlayout_Comment);

        heartlayout_Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int result = communityModel.getLikeModel(17);         //TODO photobook id값 받아와야합니다.)
                if (result == 1)

                    Toast.makeText(getActivity(), "좋아요를 눌렀습니다.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "이미 눌렀습니다.", Toast.LENGTH_SHORT).show();
            }
        });


//        LinearLayout scraplayout_Comment = (LinearLayout) rootView.findViewById(R.id.scraplayout_Comment);

        scraplayout_Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int result = communityModel.getShareModel(17);         //photobook id값 받아와야합니다.)
                if (result == 1)

                    Toast.makeText(getActivity(), "스크랩을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "이미 눌렀습니다.", Toast.LENGTH_SHORT).show();
            }
        });

//        LinearLayout sharelayout_Comment = (LinearLayout) rootView.findViewById(R.id.sharelayout_Comment);
        sharelayout_Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "추후구현예정", Toast.LENGTH_SHORT).show();
            }
        });

        btnCommentWrite_Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "댓글입력중", Toast.LENGTH_SHORT).show();
                Comment comment = new Comment();
                comment.id = 17;       // TODO 포토북 아이디 받아오기
                comment.content = editText_Comment.getText().toString();
                communityModel.postCommentModel(comment);
                Toast.makeText(getActivity(), "댓글입력완료", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void getObjectFromModel(Object object) {

    }

    @Override
    public void getListFromModel(List<?> objectList) {

    }

    @Override
    public void networkFailed() {

    }
}
