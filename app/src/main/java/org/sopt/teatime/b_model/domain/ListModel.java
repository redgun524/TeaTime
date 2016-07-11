package org.sopt.teatime.b_model.domain;

/*
카테고리 리스트뷰 모델
 */
public class ListModel {
    private String mData;
    private int mImage;

    public ListModel(String obj, int img) {
        mData = obj;
        mImage = img;
    }

    public void setData(String s) {
        mData = s;
    }

    public int getImage() { return mImage; }

    public String getData() {
        return mData;
    }
}
