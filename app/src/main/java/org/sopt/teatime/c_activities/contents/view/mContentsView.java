package org.sopt.teatime.c_activities.contents.view;

import org.sopt.teatime.b_model.domain.Contents;

import java.util.ArrayList;


/**
 * Created by JH on 2016-07-02.
 */
public interface mContentsView {

    void setDatas(ArrayList<Contents> contentsList);
    void networkFailed();
}
