package org.sopt.teatime.c_activities.main.component;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.sopt.teatime.b_model.domain.Cover;

import java.util.ArrayList;


/**
 * Created by JH on 2016-06-30.
 */


public class CoverAdapter extends FragmentPagerAdapter {

    ArrayList<Cover> coverList = new ArrayList<>();

    public void setCoverList(ArrayList<Cover> coverList) {         // adapter에 list 받아와서 값 변경
        this.coverList = coverList;
        notifyDataSetChanged();
    }

    public CoverAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        //PhotoCard photoCard_tmp = photoCardList.get(position);
//        PhotoCard photoCard_tmp=null;
//        Fragment fragment = new CoverFragment();
//        Bundle args = new Bundle();
//        args.putString("title","abc");
//        args.putString("img","bbc");


//        // Our object is just an integer :-P
//        args.putInt(CoverFragment.ARG_OBJECT, position + 1);
//        fragment.setArguments(args);
//        ArrayList<PhotoCard> photoCardList = null;
//        PhotoCard photoCard_temp = photoCardList.get(position);

        //int i = photoCardList.size();

        Cover cover_tmp = coverList.get(position);      //해당하는 위치의 PhotoCard temp 생성

        //  args1.putString("adapterName", photoCard_tmp.name);

        return CoverFragment.init(cover_tmp);       //화면에 값을 설정하기 위해서 fragment 생성으로 값을 넘기려고 함.
        //TODO 1.fragment값 설정을 좀 더 간단히 할 수 있는 방법?
    }

    @Override
    public int getCount() {

        return coverList.size() <= 0 ? 0 : coverList.size();
        //return 6;
    }
}
