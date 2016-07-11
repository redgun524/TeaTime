package org.sopt.teatime.b_model.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JH on 2016-07-02.
 */
public class Contents implements Parcelable{

    public String id;
    public String description;
    public String url;
    public int template_code;
    public int font_code;
    public int font_size;
    public int font_color;
    public int page;

    public Contents() {
    }

    protected Contents(Parcel in) {
        id = in.readString();
        description = in.readString();
        url = in.readString();
        template_code = in.readInt();
        font_code = in.readInt();
        font_size = in.readInt();
        font_color = in.readInt();
        page = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeInt(template_code);
        dest.writeInt(font_code);
        dest.writeInt(font_size);
        dest.writeInt(font_color);
        dest.writeInt(page);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contents> CREATOR = new Creator<Contents>() {
        @Override
        public Contents createFromParcel(Parcel in) {
            return new Contents(in);
        }

        @Override
        public Contents[] newArray(int size) {
            return new Contents[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public int getTemplate_code() {
        return template_code;
    }

    public int getFont_code() {
        return font_code;
    }

    public int getFont_size() {
        return font_size;
    }

    public int getFont_color() {
        return font_color;
    }

    public int getPage() {
        return page;
    }

    public Contents(String id, String description, String url, int template_code, int font_code, int font_size, int font_color, int page) {

        this.id = id;
        this.description = description;
        this.url = url;
        this.template_code = template_code;
        this.font_code = font_code;
        this.font_size = font_size;
        this.font_color = font_color;
        this.page = page;
    }
}
