package org.sopt.teatime.c_activities.write.cover.model;


/**
 * Created by Hyeonu on 2016-03-22.
 */
public class CategoryRecyclerItem {
    String category;
    public boolean isClick;

    public CategoryRecyclerItem(String category) {
        this.category = category;
        this.isClick = false;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
