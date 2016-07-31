package org.sopt.teatime.b_model.domain;

import java.util.ArrayList;
import java.util.List;

public class PhotoBook {

    static public final String INTENT_COVER_KEY = "Cover";
    static public final String INTENT_CONTENTS_KEY = "Contents";
    static public final String INTENT_CANCEL_KEY = "Cancel";

    public Cover cover;
    public List<Contents> contents_list;
    public List<Comment> comment_list;

    public PhotoBook() {
        this.cover = new Cover();
        this.contents_list = new ArrayList<>();
        this.comment_list = new ArrayList<>();
    }
}
