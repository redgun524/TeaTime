package org.sopt.teatime.c_activities.write.cover.model;

import java.io.Serializable;

/**
 * Created by Hyeonu on 2016-07-02.
 */
public class TemplateRecyclerItem implements Serializable{
    String Picture;
    String Layer;
    String title;
    String subtitle;
    String content;
    String font;

    public void setPicture(String picture) {
        Picture = picture;
    }

    public void setLayer(String layer) {
        Layer = layer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getPicture() {
        return Picture;
    }

    public String getLayer() {
        return Layer;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getContent() {
        return content;
    }

    public String getFont() {
        return font;
    }
}
