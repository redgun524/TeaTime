package org.sopt.teatime.c_activities.write.register.component;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.b_model.domain.Cover;

import java.util.ArrayList;

/**
 * Created by JH on 2016-06-30.
 */
public class PreviewAdapter extends FragmentPagerAdapter {

    ArrayList<Contents> contentList;
    Cover cover;

    public PreviewAdapter(FragmentManager fm) {
        super(fm);
        contentList = new ArrayList<>();
    }

    public void setCover(Cover cover) {
        this.cover = cover;
        notifyDataSetChanged();
    }

    public void appendContents(Contents contents) {
        contentList.add(contents);
        notifyDataSetChanged();
    }

    public void replaceContents(Contents contents, int page) {
        contentList.set(page, contents);
        notifyDataSetChanged();
    }

    public void setContentList(ArrayList<Contents> contentList) {         // adapter에 list 받아와서 값 변경
        this.contentList = contentList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if (cover !=null) {
            if (position == 0) {
                return PreviewCoverFragment.init(cover);
            } else {
                Contents contents = contentList.get(position-1);
                return  PreviewContentFragment.init(contents, null);
            }
        } else {
            Contents contents = contentList.get(position);
            return  PreviewContentFragment.init(contents, null);
        }
    }

    @Override
    public int getCount()  {
        if (cover != null) {
            return contentList.size() <= 0 ? 1 : contentList.size() + 1;
        } else {
            return contentList.size() <= 0 ? 0 : contentList.size();
        }
    }
}

