package org.sopt.teatime.c_activities.write.register.view;

import org.sopt.teatime.b_model.domain.Contents;

/**
 * Created by 품파파품파 on 2016-07-07.
 */
public interface RegisterView {
    void setContentTemplate(int page, Contents contents);
    void setCategory(String category);
    void cancelCategory(String category);
}
