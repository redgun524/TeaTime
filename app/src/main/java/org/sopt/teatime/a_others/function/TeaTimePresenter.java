package org.sopt.teatime.a_others.function;

import java.util.List;

public interface TeaTimePresenter {
    void getObjectFromModel(Object object);
    void getListFromModel(List<?> objectList);
    void networkFailed();
}
