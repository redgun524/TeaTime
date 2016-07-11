package org.sopt.teatime.c_activities.contents.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.b_model.domain.Comment;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.b_model.domain.PhotoBook;
import org.sopt.teatime.c_activities.contents.component.PhotoBookAdapter;
import org.sopt.teatime.c_activities.contents.component.PhotoBookCommentAdapter;
import org.sopt.teatime.c_activities.contents.presenter.PhotoBookPresenter;
import org.sopt.teatime.c_activities.contents.presenter.PhotoBookPresenterImpl;
import org.sopt.teatime.c_activities.main.view.mPhotoBookView;
import org.sopt.teatime.c_activities.write.register.component.PreviewTypefaceImpl;

import java.util.ArrayList;


public class PhotoBookActivity extends AppCompatActivity implements mPhotoBookView, View.OnClickListener{

    PhotoBookPresenter photoBookPresenter;
    PhotoBookAdapter photoBookAdapter;
    PhotoBookCommentAdapter photoBookCommentAdapter;
    ViewPager viewPager;
    RelativeLayout photoBookRelative;
    FrameLayout frameLayout;
    ImageView imgContent_Cover;
    RelativeLayout rootRelative_PhotoBook;
    ArrayList<Typeface> typefaces;
    Typeface typeface_ng, typeface_ngb, typeface_nbg, typeface_nb, typeface_nm, typeface_np, typeface_jua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photobook);

        photoBookPresenter = new PhotoBookPresenterImpl(this);
        PreviewTypefaceImpl previewTypeface = new PreviewTypefaceImpl(typefaces);
        photoBookAdapter = new PhotoBookAdapter(getSupportFragmentManager(), previewTypeface);
        photoBookCommentAdapter = new PhotoBookCommentAdapter(this);

        //photoBookPresenter.getPhotoBookData();

        initTypeFaces();
        viewPager = (ViewPager) findViewById(R.id.viewPager_PhotoBook);
        photoBookRelative = (RelativeLayout) findViewById(R.id.RelativeLayout_ContentsCover);
        rootRelative_PhotoBook = (RelativeLayout) findViewById(R.id.rootRelative_PhotoBook);

        rootRelative_PhotoBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("whatthe", "viewpager.떠라0");
                photoBookRelative.setVisibility(View.VISIBLE);

            }
        });


        frameLayout = (FrameLayout) findViewById(R.id.frame_PhotoBook);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("whatthe", "viewpager.떠라1");
                photoBookRelative.setVisibility(View.INVISIBLE);

            }
        });


        photoBookRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (photoBookRelative.getVisibility() == View.INVISIBLE) {
                    Log.i("whatthe", "relative.떠라3");
                    photoBookRelative.setVisibility(View.VISIBLE);
                } else {
                    Log.i("whatthe", "relative.떠라4");
                    photoBookRelative.setVisibility(View.INVISIBLE);
                }

            }
        });


        viewPager.setAdapter(photoBookAdapter);
    }

    private void initTypeFaces() {
        typeface_ng = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_GOTHIC);
        typeface_ngb = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_GOTHIC_BOLD);
        typeface_nbg = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_BARUN_GOTHIC);
        typeface_nb = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_BRUSH);
        typeface_nm = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_MYEONGJO);
        typeface_np = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_PEN);
        typeface_jua = Typeface.createFromAsset(getAssets(), FontController.PATH_JUA);
        typefaces = new ArrayList<>();
        typefaces.add(typeface_ng);
        typefaces.add(typeface_ngb);
        typefaces.add(typeface_nbg);
        typefaces.add(typeface_nb);
        typefaces.add(typeface_nm);
        typefaces.add(typeface_np);
        typefaces.add(typeface_jua);
    }

    @Override
    public void setDatas(PhotoBook photoBook) {

        photoBookAdapter.setPhotoBook((ArrayList<Contents>)photoBook.contents_list, (ArrayList<Comment>)photoBook.comment_list, photoBook);
    }

    @Override
    public void onClick(View view) {

        photoBookRelative.setVisibility(View.VISIBLE);
        Log.i("whatthe","viewpager.떠라4");
    }
}
