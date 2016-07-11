package org.sopt.teatime.c_activities.search.presenter;

import java.util.List;

/**
 * Created by 아라 on 2016-07-03.
 */
public interface ListPresenter {
    void getUserList(String key);
    void getListFromModel(List<?> objectList);
}
