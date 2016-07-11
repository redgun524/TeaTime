package org.sopt.teatime.c_activities.mybook.view;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.ui.LoadingDialog;
import org.sopt.teatime.a_others.ui.TeaTimeDialogListener;
import org.sopt.teatime.a_others.ui.TeaTimeDialogOne;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.c_activities.main.view.MainActivity;
import org.sopt.teatime.c_activities.mybook.presenter.MybookPresenter;
import org.sopt.teatime.c_activities.mybook.presenter.MybookPresenterlmpl;
import org.sopt.teatime.c_activities.scrap.view.ScrapbookActivity;
import org.sopt.teatime.c_activities.search.component.RecyclerAdapter;
import org.sopt.teatime.c_activities.write.register.view.RegisterActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MybookActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MybookView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar_mybook)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout_mybook)
    DrawerLayout drawer;
    @BindView(R.id.nav_view_mybook)
    NavigationView navigationView;
    @BindView(R.id.b_imgProfile_Mybook)
    ImageView b_imgProgile;
    @BindView(R.id.b_txtNick_Mybook)
    TextView b_txtNick;
    @BindView(R.id.txtAppName_Mybook)
    TextView txtAppName;

    MybookPresenter presenter;
    RecyclerAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybook);

        ButterKnife.bind(this);

        presenter = new MybookPresenterlmpl(this);

        Log.i("MyTag", "1. 프레젠터에 데이터 요청");
        presenter.getUserList();
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        adapter = new RecyclerAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        txtAppName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/BMJUA_ttf.ttf"));
        b_txtNick.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothic.ttf"));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        ImageView imgProfile = (ImageView)findViewById(R.id.imgProfile_navi);
        Glide.with(getApplicationContext())
                .load("")
                .placeholder(R.drawable.ic_profile)
                .into(imgProfile);

        navigationView.setNavigationItemSelectedListener(this);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            super.onBackPressed();
        }
    }

    @OnClick(R.id.txtWrite_navi)
    void setMenuWrite() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.txtHome_navi)
    void setMenuHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.txtScrap_navi)
    void setMenuScrap() {
        Intent intent = new Intent(getApplicationContext(), ScrapbookActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setDatas(ArrayList<Cover> coverList) {

        Glide.with(this)
                .load("")
                .placeholder(R.drawable.ic_profile)
                .into(this.b_imgProgile);

        if (coverList.size() > 0) {
            String strId = coverList.get(0).getAuthor_nick();
            b_txtNick.setText(strId);
        }
        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), coverList));
    }

    @Override
    public void networkFailed() {
        LoadingDialog.dismiss();
        final TeaTimeDialogOne dialog = new TeaTimeDialogOne(this);
        dialog.setMessage("인터넷 연결을 확인해주세요.");
        dialog.setOnTeaTimeDialogClickListener(new TeaTimeDialogListener() {
            @Override
            public void clickYes() {
                dialog.dismiss();
            }

            @Override
            public void clickNo() {

            }
        });
        dialog.show();
    }
}
