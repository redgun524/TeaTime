package org.sopt.teatime.c_activities.write.cover.model;


/**
 * Created by Hyeonu on 2016-03-22.
 */
public class FontRecyclerItem {
    String font;
    String fontName;

    public FontRecyclerItem(String font, String fontName) {
        this.font = font;
        this.fontName = fontName;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFont() {
        return font;
    }

    public String getFontName() {
        return fontName;
    }
}
