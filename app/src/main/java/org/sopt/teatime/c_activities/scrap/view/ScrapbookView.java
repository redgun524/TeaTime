package org.sopt.teatime.c_activities.scrap.view;

import org.sopt.teatime.b_model.domain.Cover;

import java.util.ArrayList;

/**
 * Created by 박은인 on 2016-07-02.
 */
public interface ScrapbookView {
      void setDatas(ArrayList<Cover> userList);
      void noResultEvent();
      void networkFailed();
}
