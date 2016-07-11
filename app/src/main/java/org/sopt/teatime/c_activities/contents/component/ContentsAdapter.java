package org.sopt.teatime.c_activities.contents.component;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.sopt.teatime.b_model.domain.Contents;

import java.util.ArrayList;


/**
 * Created by JH on 2016-07-02.
 */
public class ContentsAdapter extends FragmentStatePagerAdapter {

    ArrayList<Contents> contentsList = new ArrayList<>();

    public ContentsAdapter(FragmentManager fm) {  super(fm); }

    public void setContentsList(ArrayList<Contents> contentsList) {         // adapter에 list 받아와서 값 변경
        this.contentsList = contentsList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {

        Contents contents_tmp = contentsList.get(position);      //해당하는 위치의 PhotoCard temp 생성

        //Bundle args1 = new Bundle();
        //args1.putString("adapterName", contents_tmp.name);

        return ContentsFragment.init("!!", "!!");       //화면에 값을 설정하기 위해서 fragment 생성으로 값을 넘기려고 함.

    }

    @Override
    public int getCount() {
        //return 0;
        return contentsList.size() <= 0 ? 0 : contentsList.size();
    }
}
