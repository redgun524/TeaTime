package org.sopt.teatime.c_activities.contents.component;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.R;
import org.sopt.teatime.b_model.domain.Comment;

import java.util.ArrayList;

/**
 * Created by JH on 2016-07-07.
 */
public class PhotoBookCommentAdapter extends BaseAdapter {

    ArrayList<Comment> commentList;
    LayoutInflater inflater;

    public void setPhotoBook(ArrayList<Comment> commentList) {

        this.commentList = commentList;
    }

    public PhotoBookCommentAdapter(Context ctx) { inflater = LayoutInflater.from(ctx);    }

    @Override
    public int getCount() {
//        Log.i("HeyYo",Integer.toString(commentList.size()));
  //      return commentList.size();
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return (commentList.size() <= 0 || (i < 0 || i >= commentList.size())) ? null : commentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        PhotoBookCommentViewHolder viewHolder = new PhotoBookCommentViewHolder();

        if (view == null) {
            view = inflater.inflate(R.layout.comment_list, viewGroup, false);
            viewHolder.txtAuthor = (TextView) view.findViewById(R.id.txtUsername_CommentList);
            viewHolder.txtContents = (TextView) view.findViewById(R.id.txtContent_CommentList);
            viewHolder.imgProfile = (ImageView)view.findViewById(R.id.imgProfile_CommentList);
            view.setTag(viewHolder);

            Log.i("MyPhotoBook","viewHolder 설정");

        } else {
            viewHolder = (PhotoBookCommentViewHolder) view.getTag();
        }

        Log.i("whwhwh",Integer.toString(commentList.size()));
        Comment comment = commentList.get(i);
        viewHolder.txtAuthor.setText(comment.nick);
        Log.i("abcdef",comment.nick);
        Log.i("abcdef",comment.content);
//        viewHolder.txtContents.setText(comment.content);
//        viewHolder.txtAuthor.setText("authorText");
        viewHolder.txtContents.setText("contentsTest");

        Glide.with(view.getContext()).load("http://s3.ap-northeast-2.amazonaws.com/sqoo/"+comment.url).into(viewHolder.imgProfile);       //사진 설정..

        Log.i("MyPhotoBook","ViewHolder를 통해 값 설정");
        Log.i("MyPhotoBook","제목이름은"+comment.nick);

        return view;


    }
}
