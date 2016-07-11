package org.sopt.teatime.b_model.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by 박은인 on 2016-07-05.
 */

public class Cover implements Parcelable {
    public int id; // 포토북 아이디

    public Cover() {
    }

    public Cover(int id, String author_nick, String author_id, int like_count, int comment_count, int scrap_count, Date timestamp, int template_code, String title, int title_font_code, int title_size, int title_color, String subtitle, int subtitle_font_code, int subtitle_size, int subtitle_color, String key1, String key2, String key3, String url, String profile_url, int isPublic) {
        this.id = id;
        this.author_nick = author_nick;
        this.author_id = author_id;
        this.like_count = like_count;
        this.comment_count = comment_count;
        this.scrap_count = scrap_count;
        this.timestamp = timestamp;
        this.template_code = template_code;
        this.title = title;
        this.title_font_code = title_font_code;
        this.title_size = title_size;
        this.title_color = title_color;
        this.subtitle = subtitle;
        this.subtitle_font_code = subtitle_font_code;
        this.subtitle_size = subtitle_size;
        this.subtitle_color = subtitle_color;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.url = url;
        this.profile_url = profile_url;
        this.isPublic = isPublic;
    }

    public int getId() {
        return id;
    }

    public String getAuthor_nick() {
        return author_nick;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public int getLike_count() {
        return like_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getScrap_count() {
        return scrap_count;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getTemplate_code() {
        return template_code;
    }

    public String getTitle() {
        return title;
    }

    public int getTitle_font_code() {
        return title_font_code;
    }

    public int getTitle_size() {
        return title_size;
    }

    public int getTitle_color() {
        return title_color;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getSubtitle_font_code() {
        return subtitle_font_code;
    }

    public int getSubtitle_size() {
        return subtitle_size;
    }

    public int getSubtitle_color() {
        return subtitle_color;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }

    public String getKey3() {
        return key3;
    }

    public String getUrl() {
        return url;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public String author_nick; // 작성자 닉네임
    public String author_id; // 작성자 아이디
    public int like_count; //좋아요 수
    public int comment_count; //댓글 수
    public int scrap_count; // 스크랩 수
    public Date timestamp; //작성시간
    public int template_code;
    public String title; //제목
    public int title_font_code;
    public int title_size;
    public int title_color;
    public String subtitle;
    public int subtitle_font_code;
    public int subtitle_size;
    public int subtitle_color;
    public String key1; //키워드
    public String key2;
    public String key3;
    public String url;
    public String profile_url;
    public int isPublic;

    protected Cover(Parcel in) {
        id = in.readInt();
        author_nick = in.readString();
        author_id = in.readString();
        like_count = in.readInt();
        comment_count = in.readInt();
        scrap_count = in.readInt();
        template_code = in.readInt();
        title = in.readString();
        title_font_code = in.readInt();
        title_size = in.readInt();
        title_color = in.readInt();
        subtitle = in.readString();
        subtitle_font_code = in.readInt();
        subtitle_size = in.readInt();
        subtitle_color = in.readInt();
        key1 = in.readString();
        key2 = in.readString();
        key3 = in.readString();
        url = in.readString();
        profile_url = in.readString();
        isPublic = in.readInt();
    }

    public static final Creator<Cover> CREATOR = new Creator<Cover>() {
        @Override
        public Cover createFromParcel(Parcel in) {
            return new Cover(in);
        }

        @Override
        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(author_nick);
        parcel.writeString(author_id);
        parcel.writeInt(like_count);
        parcel.writeInt(comment_count);
        parcel.writeInt(scrap_count);
        parcel.writeInt(template_code);
        parcel.writeString(title);
        parcel.writeInt(title_font_code);
        parcel.writeInt(title_size);
        parcel.writeInt(title_color);
        parcel.writeString(subtitle);
        parcel.writeInt(subtitle_font_code);
        parcel.writeInt(subtitle_size);
        parcel.writeInt(subtitle_color);
        parcel.writeString(key1);
        parcel.writeString(key2);
        parcel.writeString(key3);
        parcel.writeString(url);
        parcel.writeString(profile_url);
        parcel.writeInt(isPublic);
    }
}

//public class Cover implements Serializable {
//    public int id; // 포토북 아이디
//    public String author_nick; // 작성자 닉네임
//    public String author_id; // 작성자 아이디
//    public int like_count; //좋아요 수
//    public int comment_count; //댓글 수
//    public int scrap_count; // 스크랩 수
//    public Date timestamp; //작성시간
//    public int template_code;
//    public String title; //제목
//    public int title_font_code;
//    public int title_size;
//    public int title_color;
//    public String subtitle;
//    public int subtitle_font_code;
//    public int subtitle_size;
//    public int subtitle_color;
//    public String key1; //키워드
//    public String key2;
//    public String key3;
//    public String url;
//    public String profile_url;
//    public int isPublic;
//
//    //작성 관련 생성자
//
//
//    public Cover(int id, String author_nick, String author_id, int like_count, int comment_count, int scrap_count, Date timestamp, int template_code, String title, int title_font_code, int title_size, int title_color, String subtitle, int subtitle_font_code, int subtitle_size, int subtitle_color, String key1, String key2, String key3, String url, String profile_url) {
//        this.id = id;
//        this.author_nick = author_nick;
//        this.author_id = author_id;
//        this.like_count = like_count;
//        this.comment_count = comment_count;
//        this.scrap_count = scrap_count;
//        this.timestamp = timestamp;
//        this.template_code = template_code;
//        this.title = title;
//        this.title_font_code = title_font_code;
//        this.title_size = title_size;
//        this.title_color = title_color;
//        this.subtitle = subtitle;
//        this.subtitle_font_code = subtitle_font_code;
//        this.subtitle_size = subtitle_size;
//        this.subtitle_color = subtitle_color;
//        this.key1 = key1;
//        this.key2 = key2;
//        this.key3 = key3;
//        this.url = url;
//        this.profile_url = profile_url;
//    }
//
//    public Cover(int id, String author_nick, String author_id, int like_count, int comment_count, int scrap_count, Date timestamp, int template_code, String title, int title_font_code, int title_size, int title_color, String key1, String key2, String key3, String url, String profile_url) {
//        this.id = id;
//        this.author_nick = author_nick;
//        this.author_id = author_id;
//        this.like_count = like_count;
//        this.comment_count = comment_count;
//        this.scrap_count = scrap_count;
//        this.timestamp = timestamp;
//        this.template_code = template_code;
//        this.title = title;
//        this.title_font_code = title_font_code;
//        this.title_size = title_size;
//        this.title_color = title_color;
//        this.key1 = key1;
//        this.key2 = key2;
//        this.key3 = key3;
//        this.url = url;
//        this.profile_url = profile_url;
//    }
//
//
//
//    public int getId(){
//        return this.id;
//    }
//    public String getAuthor_nick() { return this.author_nick; }
//    public String getAuthor_id() { return this.author_id; }
//    public int getLike_count() { return this.like_count; }
//    public int getComment_count() { return this.comment_count; }
//    public int getScrap_count() { return this.scrap_count;}
//    public Date getTimestamp() { return this.timestamp;}
//    public int getTemplate_code() { return this.template_code;}
//    public String getTitle() { return this.title; }
//    public int getTitle_font_code() { return this.title_font_code;}
//    public int getTitle_size() { return this.title_size; }
//    public int getTitle_color() { return this.title_color; }
//    public String getKey1() { return this.key1; }
//    public String getKey2() { return this.key2; }
//    public String getKey3() { return this.key3; }
//    public String getUrl() { return this.url; }
//    public String getProfile_url() {
//        return profile_url;
//    }
//}
