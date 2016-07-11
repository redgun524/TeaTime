package org.sopt.teatime.c_activities.main.view;

import org.sopt.teatime.b_model.domain.Cover;

import java.util.ArrayList;


/**
 * Created by JH on 2016-06-30.
 */
public interface mPhotoCardView {
    void setDatas(ArrayList<Cover> coverList);
    void networkFailed();
}
