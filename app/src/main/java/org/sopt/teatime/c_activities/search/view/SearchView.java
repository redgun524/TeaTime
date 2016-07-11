package org.sopt.teatime.c_activities.search.view;


import org.sopt.teatime.b_model.domain.Cover;

import java.util.ArrayList;

/**
 * Created by 아라 on 2016-07-02.
 */
public interface SearchView {
    void setDatas(ArrayList<Cover> userList);
    void itemClickEvent(String key);
    void noResultEvent();
    void networkFailed();
}
