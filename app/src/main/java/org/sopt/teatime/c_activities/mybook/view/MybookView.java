package org.sopt.teatime.c_activities.mybook.view;

import org.sopt.teatime.b_model.domain.Cover;

import java.util.ArrayList;

/**
 * Created by 박은인 on 2016-07-02.
 */
public interface MybookView {
   void setDatas(ArrayList<Cover> user);
   void networkFailed();
}
