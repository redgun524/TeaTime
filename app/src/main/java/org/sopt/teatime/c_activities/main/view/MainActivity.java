package org.sopt.teatime.c_activities.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.function.DialogController;
import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.a_others.ui.LoadingDialog;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.c_activities.main.component.CoverAdapter;
import org.sopt.teatime.c_activities.main.presenter.MainListPresenter;
import org.sopt.teatime.c_activities.main.presenter.MainPresenterImpl;
import org.sopt.teatime.c_activities.mybook.view.MybookActivity;
import org.sopt.teatime.c_activities.scrap.view.ScrapbookActivity;
import org.sopt.teatime.c_activities.search.view.SearchActivity;
import org.sopt.teatime.c_activities.write.register.view.RegisterActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity
        implements mPhotoCardView {

    @BindView(R.id.toolbar_Main) Toolbar toolbar;
    @BindView(R.id.drawer_layout_Main) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.viewPager_Main) VerticalViewPager viewPager;
    @BindView(R.id.btnSearch_Main) ImageButton btnSearch;
    @BindView(R.id.imgProfile_navi) ImageView imgProfile;
    @BindView(R.id.txtTitle_Main) TextView txtTitle;

    MainListPresenter mainListPresenter;
    CoverAdapter coverAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();
        initDrawer();

        mainListPresenter = new MainPresenterImpl(this);
        coverAdapter = new CoverAdapter(getSupportFragmentManager());
        viewPager.setAdapter(coverAdapter);

        LoadingDialog.show(this);
        mainListPresenter.getPhotoCardList();
    }

    private void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        txtTitle.setTypeface(FontController.getFontByCode(FontController.FONT_JUA));
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //드로어의 프로필 사진 설정
        Glide.with(getApplicationContext())
                .load("")
                .placeholder(R.drawable.ic_profile)
                .into(imgProfile);
    }

    @OnClick(R.id.btnSearch_Main)
    void setBtnSearch() {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.txtMyBook_navi)
    void setByBook() {
        Intent intent = new Intent(getApplicationContext(), MybookActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.txtScrap_navi)
    void setMenuScrap() {
        Intent intent = new Intent(getApplicationContext(), ScrapbookActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.txtWrite_navi)
    void setMenuWrite() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btnWrite_Main)
    void setBtnWrite() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btnLogout_navi)
    void setBtnLogout() {
        DialogController.showLogoutDialog(this);
    }

    @Override
    public void setDatas(ArrayList<Cover> coverList) {
        LoadingDialog.dismiss();
        coverAdapter.setCoverList(coverList);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_Main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void networkFailed() {
        LoadingDialog.dismiss();
        DialogController.showNetworkErrorDialog(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
