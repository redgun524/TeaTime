package org.sopt.teatime.b_model.domain;

import java.sql.Timestamp;

public class PhotoCover {
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
}
