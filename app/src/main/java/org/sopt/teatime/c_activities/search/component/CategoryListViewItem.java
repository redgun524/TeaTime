package org.sopt.teatime.c_activities.search.component;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sopt.teatime.R;

/*
 카테고리 리스트뷰의 뷰
 */
public class CategoryListViewItem extends LinearLayout {

    TextView mText01;
    ImageView mIcon;

    public CategoryListViewItem(Context context){
        super(context);

        //Category_item.xml을 하나의 리스트로 쓸 수 있다.
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.category_item, this, true);

        mIcon = (ImageView)findViewById(R.id.categoryImage);
        mText01 = (TextView)findViewById(R.id.txtCategory_Category);

        //글씨체 변경
        mText01.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/BMJUA_ttf.ttf"));
    }

    public void setImage(int data) { mIcon.setImageResource(data); }

    public void setText(String data){
        mText01.setText(data);
    }

}
