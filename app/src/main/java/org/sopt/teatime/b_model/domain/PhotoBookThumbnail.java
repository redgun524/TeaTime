package org.sopt.teatime.b_model.domain;

import java.sql.Timestamp;


/**
 * Created by 박은인 on 2016-07-05.
 */
public class PhotoBookThumbnail {
    public int id; // 포토북 아이디
    public String author; // 작성자 닉네임
    public String author_id; // 작성자 아이디
    public int like_count; //좋아요 수
    public int comment_count; //댓글 수
    public int scrap_count; // 스크랩 수
    public Timestamp timestamp; //작성시간
    public int template_code;
    public String title; //제목
    public int title_font_code;
    public int tile_size;
    public int title_color;
    public String sub_title;
    public int sub_title_font_size;
    public int sub_title_size;
    public int sub_title_color;
    public String key1; //키워드
    public String key2;
    public String key3;
    public String url;
    public String profile_url;

    //작성 관련 생성자


    public PhotoBookThumbnail(int id, String author, String author_id, int like_count, int comment_count, int scrap_count, Timestamp timestamp, int template_code, String title, int title_font_code, int tile_size, int title_color, String sub_title, int sub_title_font_size, int sub_title_size, int sub_title_color, String key1, String key2, String key3, String url, String profile_url) {
        this.id = id;
        this.author = author;
        this.author_id = author_id;
        this.like_count = like_count;
        this.comment_count = comment_count;
        this.scrap_count = scrap_count;
        this.timestamp = timestamp;
        this.template_code = template_code;
        this.title = title;
        this.title_font_code = title_font_code;
        this.tile_size = tile_size;
        this.title_color = title_color;
        this.sub_title = sub_title;
        this.sub_title_font_size = sub_title_font_size;
        this.sub_title_size = sub_title_size;
        this.sub_title_color = sub_title_color;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.url = url;
        this.profile_url = profile_url;
    }

    public PhotoBookThumbnail(int id, String author, String author_id, int like_count, int comment_count, int scrap_count, Timestamp timestamp, int template_code, String title, int title_font_code, int tile_size, int title_color, String key1, String key2, String key3, String url, String profile_url) {
        this.id = id;
        this.author = author;
        this.author_id = author_id;
        this.like_count = like_count;
        this.comment_count = comment_count;
        this.scrap_count = scrap_count;
        this.timestamp = timestamp;
        this.template_code = template_code;
        this.title = title;
        this.title_font_code = title_font_code;
        this.tile_size = tile_size;
        this.title_color = title_color;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.url = url;
        this.profile_url = profile_url;
    }

    public int getId(){
        return this.id;
    }
    public String getAuthor() { return this.author; }
    public String getAuthor_id() { return this.author_id; }
    public int getLike_count() { return this.like_count; }
    public int getComment_count() { return this.comment_count; }
    public int getScrap_count() { return this.scrap_count;}
    public Timestamp getTimestamp() { return this.timestamp;}
    public int getTemplate_code() { return this.template_code;}
    public String getTitle() { return this.title; }
    public int getTitle_font_code() { return this.title_font_code;}
    public int getTile_size() { return this.tile_size; }
    public int getTitle_color() { return this.title_color; }
    public String getKey1() { return this.key1; }
    public String getKey2() { return this.key2; }
    public String getKey3() { return this.key3; }
    public String getUrl() { return this.url; }
    public String getProfile_url() {
        return profile_url;
    }
}
