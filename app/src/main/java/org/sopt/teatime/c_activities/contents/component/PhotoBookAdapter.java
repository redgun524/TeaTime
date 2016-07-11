package org.sopt.teatime.c_activities.contents.component;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import org.sopt.teatime.b_model.domain.Comment;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.b_model.domain.PhotoBook;
import org.sopt.teatime.c_activities.contents.view.PhotoBookCommentFragment;
import org.sopt.teatime.c_activities.contents.view.PhotoBookContentsFragment;
import org.sopt.teatime.c_activities.contents.view.PhotoBookCoverFragment;
import org.sopt.teatime.c_activities.write.register.component.PreviewTypefaceImpl;

import java.util.ArrayList;

/**
 * Created by JH on 2016-07-07.
 */
public class PhotoBookAdapter extends FragmentPagerAdapter {

    PhotoBook photoBook;
    ArrayList<Contents> contentsList;
    ArrayList<Comment> commentList;
    PhotoBookCommentAdapter photoBookCommentAdapter;
    ViewPager viewPager;
    RelativeLayout photoBookRelative;
    PreviewTypefaceImpl previewTypeface;

//    PhotoBookCommentAdapter photoBookCommentAdapter;

    public PhotoBookAdapter(FragmentManager fm, PreviewTypefaceImpl previewTypeface) {
        super(fm);
        this.previewTypeface = previewTypeface;
    }

    public void setPhotoBook(ArrayList<Contents> contentsList,ArrayList<Comment> commentList, PhotoBook photoBook) {
        this.photoBook = photoBook;
        this.contentsList = contentsList;
        this.commentList = commentList;


        //        Comment comment_tmp1 = new Comment();
//        Comment comment_tmp2 = new Comment();
//        Comment comment_tmp3 = new Comment();
//        comment_tmp1.id = "abc";
//        comment_tmp1.content = "123";
//        comment_tmp2.id = "def";
//        comment_tmp2.content = "456";
//        comment_tmp3.id = "ghi";
//        comment_tmp3.content = "789";
//
//        //photoBook.comment_list.add(comment_tmp1);
//        //photoBook.comment_list.add(comment_tmp2);
//        this.photoBook.comment_list.add(0,comment_tmp1);
//        this.photoBook.comment_list.add(1,comment_tmp2);
//        this.photoBook.comment_list.add(2,comment_tmp3);

        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {



        if (position == 0) {                  //첫화면 = cover
            return PhotoBookCoverFragment.init(photoBook.cover, previewTypeface);            //커버 하나만 넘기고

        } else if (position == contentsList.size()+1) {

            return PhotoBookCommentFragment.init(photoBook.cover);                //댓글창을 넘기고

        } else {
            return PhotoBookContentsFragment.init(contentsList.get(position-1), previewTypeface);    //화면을 넘긴다.
        }
    }

    @Override
    public int getCount() {
        return (contentsList == null || contentsList.size() <= 0) ? 0 : contentsList.size() +2;
    }
}