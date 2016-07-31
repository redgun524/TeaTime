package org.sopt.teatime.c_activities.write.register.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.network.ApplicationController;
import org.sopt.teatime.a_others.network.NetworkService;
import org.sopt.teatime.a_others.function.DialogController;
import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.a_others.ui.LoadingDialog;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.domain.PhotoBook;
import org.sopt.teatime.b_model.view_object.CoverViewObject;
import org.sopt.teatime.c_activities.main.view.MainActivity;
import org.sopt.teatime.c_activities.mybook.view.MybookActivity;
import org.sopt.teatime.c_activities.scrap.view.ScrapbookActivity;
import org.sopt.teatime.c_activities.write.content.view.DrawContentActivity;
import org.sopt.teatime.c_activities.write.cover.component.CategoryRecyclerViewAdapter;
import org.sopt.teatime.c_activities.write.cover.component.ContentsRecyclerViewAdapter;
import org.sopt.teatime.c_activities.write.cover.model.CategoryRecyclerItem;
import org.sopt.teatime.c_activities.write.cover.model.CoverTemplateId;
import org.sopt.teatime.c_activities.write.cover.view.DrawActivity;
import org.sopt.teatime.c_activities.write.register.component.PreviewAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @BindView(R.id.btnPage_Reg) ImageButton btnTemplate;
    @BindView(R.id.drawer_layout_Reg) DrawerLayout drawer;
    @BindView(R.id.nav_view_Reg) NavigationView navigationView;
    @BindView(R.id.btnCategory_Reg) ImageButton btnCategory;
    @BindView(R.id.btnLock_Reg) ImageButton btnPublic;
    @BindView(R.id.layoutBottom_register) LinearLayout layoutBottom;
    @BindView(R.id.toolbar_Reg) Toolbar toolbar;
    @BindView(R.id.viewPager_Reg) ViewPager viewPager;
    @BindView(R.id.txtTitle_Reg) TextView txtTitle;
    @BindView(R.id.imgProfile_navi) ImageView imgProfile;

    LinearLayoutManager  linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    LayoutInflater inflater;
    PhotoBook photoBook;
    List<Contents> contentsList;
    Cover cover;
    ArrayList<CategoryRecyclerItem> categoryRecyclerItems;
    ContentsRecyclerViewAdapter contentsAdapter;
    CategoryRecyclerViewAdapter categoryAdapter;
    PreviewAdapter pagerAdapter;
    RecyclerView recyclerViewContents;
    RecyclerView recyclerViewCategory;
    FrameLayout bottomCoverPreview;
    NetworkService networkService;
    int isPublic = 1;
    private FileMakingTask fileMakingTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initToolbar();
        initDrawer();

        categoryRecyclerItems = new ArrayList<>();
        networkService = ApplicationController.getInstance().getNetworkService();
        photoBook = new PhotoBook();
        contentsList = new ArrayList<>();

        initItems();
        initBottomLayout();
        clickEvent();
    }

    //툴바 초기화
    private void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //툴바 타이틀 글씨체 변경 - 주아체
        txtTitle.setTypeface(FontController.getFontByCode(FontController.FONT_JUA));
    }

    //드로어 초기화
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

    //각종 도구들 초기화
    public void initItems() {
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        Contents plusItem = new Contents();
        plusItem.url = "android.resource://" + getApplicationContext().getPackageName() + "/drawable/plus2";
        contentsList.add(plusItem);

        pagerAdapter = new PreviewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        ArrayList<CategoryRecyclerItem> categoryRecyclerItems = new ArrayList<>();

        String[] categoryList = getResources().getStringArray(R.array.category);
        for (int i = 0; i < categoryList.length; i++) {
            categoryRecyclerItems.add(new CategoryRecyclerItem(categoryList[i]));
        }

        contentsAdapter = new ContentsRecyclerViewAdapter(this, contentsList, this);
        categoryAdapter = new CategoryRecyclerViewAdapter(this, categoryRecyclerItems, this);
    }

    private void initBottomLayout() {
        layoutBottom.removeAllViews();

        ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_bottom_template, layoutBottom, true);
        recyclerViewContents = (RecyclerView) layoutBottom.findViewById(R.id.recyclerView_template);
        bottomCoverPreview = (FrameLayout)layoutBottom.findViewById(R.id.frameLayout_template);
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.plus2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        bottomCoverPreview.addView(imageView);
        bottomCoverPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cover == null) {
                    Intent intent = new Intent(getApplicationContext(), DrawActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    Intent intent = new Intent(getApplicationContext(), DrawActivity.class);
                    intent.putExtra(PhotoBook.INTENT_COVER_KEY, cover);
                    startActivityForResult(intent, 1);
                }
            }
        });
        initLinearLayoutManager();
        initRecyclerViewContents();
    }

    public void initLinearLayoutManager() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    }

    public void initGridLayoutManager() {
        gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
    }

    @Override
    public void setCategory(String category) {

    }

    @Override
    public void cancelCategory(String category) {

    }

    /*
    @Override
    public void setCategory(String category) {
        if (cover.key1 == null) {
            cover.key1 = category;
        } else if (cover.key2 == null) {
            cover.key2 = category;
        } else if (cover.key3 == null) {
            cover.key3 = category;
        }
    }

    @Override
    public void cancelCategory(String category) {
        if (cover.key3 != null) {
            cover.key3 = null;
        } else if (cover.key2 != null) {
            cover.key2 = null;
        } else if (cover.key1 != null) {
            cover.key1 = null;
        }
    }*/

    public void initRecyclerViewContents()
    {
        recyclerViewContents.setHasFixedSize(true);
        recyclerViewContents.setLayoutManager(linearLayoutManager);
        recyclerViewContents.setAdapter(contentsAdapter);
    }

    public void initRecyclerViewCategory()
    {
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setLayoutManager(gridLayoutManager);
        recyclerViewCategory.setAdapter(categoryAdapter);
    }

    public void clickEvent()
    {
        btnTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutBottom.removeAllViews();
                ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_bottom_template, layoutBottom, true);
                recyclerViewContents = (RecyclerView) layoutBottom.findViewById(R.id.recyclerView_template);
                initLinearLayoutManager();
                initRecyclerViewContents();
            }
        });
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutBottom.removeAllViews();
                ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_bottom_category, layoutBottom, true);
                recyclerViewCategory = (RecyclerView) layoutBottom.findViewById(R.id.recyclerView_category);
                initGridLayoutManager();
                initRecyclerViewCategory();
            }
        });
        btnPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPublic == 1) {
                    btnPublic.setImageResource(R.drawable.btn_lock_selector);
                    isPublic = 0;
                    Toast.makeText(getApplication(), "비공개", Toast.LENGTH_SHORT).show();
                } else if(isPublic == 0) {
                    btnPublic.setImageResource(R.drawable.btn_lock_open_selector);
                    isPublic = 1;
                    Toast.makeText(getApplication(), "공개", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.txtHome_navi)
    void setMenuHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
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

    @OnClick(R.id.btnLogout_Reg)
    void setBtnLogout() {
        DialogController.showLogoutDialog(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            DialogController.showCancelWritingDialog(this);
        }
    }

    @Override
    public void setContentTemplate(int page, Contents contents) {
        if (contentsList.size() == page) {
            Intent intent = new Intent(getApplicationContext(), DrawContentActivity.class);
            intent.putExtra("page", page);
            startActivityForResult(intent, 2);
        } else {
            Intent intent = new Intent(getApplicationContext(), DrawContentActivity.class);
            intent.putExtra(PhotoBook.INTENT_CONTENTS_KEY, contentsList.get(page - 1));
            startActivityForResult(intent, 2);
        }
    }

    private File createTemporaryFile(String name) throws IOException {

        File temp = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/TEATIME/");

        if (!temp.exists()) {
            temp.mkdir();
        }
        File file = new File(temp, name);
        return file;
    }

    private RequestBody makeImg(String url, String filename) {

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(url));

            if (bitmap != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 40, stream);
                byte[] bytes = stream.toByteArray();
                final File file = createTemporaryFile(filename);

                FileOutputStream writer = new FileOutputStream(file);
                writer.write(bytes);
                stream.close();
                writer.close();
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                return requestBody;
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == 1) {
            cover = data.getParcelableExtra(PhotoBook.INTENT_COVER_KEY);
            if (data.getStringExtra(PhotoBook.INTENT_CANCEL_KEY) == null) {
                pagerAdapter.setCover(cover);
                CoverTemplateId ids = new CoverTemplateId(getApplicationContext(), cover.template_code);
                bottomCoverPreview.removeAllViews();
                View v = LayoutInflater.from(getApplicationContext()).inflate(ids.layoutId, bottomCoverPreview);
                CoverViewObject coverView = new CoverViewObject(v, ids);
                coverView.setCoverDatas(this, cover, "0.25");
            }
        }
        else if(resultCode == 2) {
            Contents contents = data.getParcelableExtra(PhotoBook.INTENT_CONTENTS_KEY);
            Log.i("MyTag", "size : "+contentsList.size());
            Log.i("MyTag", "page : "+contents.page);
            int lastPage = contentsList.size();
            int index = contents.page - 1;
            if (lastPage == contents.page) {
                contentsList.add(index, contents);
                contentsAdapter.notifyDataSetChanged();
                pagerAdapter.appendContents(contents);
            } else {
                contentsList.set(index, contents);
                contentsAdapter.notifyDataSetChanged();
                pagerAdapter.replaceContents(contents, contents.page);
            }
        }
    }

    @OnClick(R.id.btnDone_Reg)
    void setBtnDone() {
        photoBook.cover = cover;
        photoBook.contents_list = contentsList;

        LoadingDialog.show(this);
        fileMakingTask = new FileMakingTask();
        fileMakingTask.execute((Void) null);
    }

    private class FileMakingTask extends AsyncTask<Void, Void, HashMap<String, List<RequestBody>>> {

        @Override
        protected HashMap<String, List<RequestBody>> doInBackground(Void... params) {

            List<RequestBody> requestBodyList = new ArrayList<>();
            HashMap<String, List<RequestBody>> map = new HashMap<>();
            requestBodyList.add(makeImg(cover.url, "cover"));
            for(int i = 0; i < contentsList.size() - 1; i++) {
                RequestBody requestBody = makeImg(contentsList.get(i).url, "content" + i);
                requestBodyList.add(requestBody);
            }
            map.put("photo",requestBodyList);
            return map;
        }

        @Override
        protected void onPostExecute(HashMap<String, List<RequestBody>> map) {

            sendPhotoBooktoServer(map);
            fileMakingTask = null;
        }

        @Override
        protected void onCancelled() {
            LoadingDialog.dismiss();
            fileMakingTask = null;
        }
    }

    public void sendPhotoBooktoServer(HashMap<String, List<RequestBody>> map) {
        /*Call<Object> callPhotoBook = networkService.sendPhotoBook(map, photoBook);
        callPhotoBook.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    Log.i("MyLog", "성공");
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "전송을 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    LoadingDialog.dismiss();
                    int statusCode = response.code();
                    Log.i("MyLog", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("MyTag", t.getMessage());
            }
        });*/

        Call<Object> objectCall = networkService.sendPhotoBook2(map);
        objectCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                sendPhotoBookDatasOnly();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "이미지 전송을 실패하였습니다.", Toast.LENGTH_SHORT).show();
                LoadingDialog.dismiss();
            }
        });
    }

    public void sendPhotoBookDatasOnly() {
        Call<Object> objectCall = networkService.sendPhotoBookOnly(photoBook);
        objectCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Response<Object> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    Log.i("MyLog", "성공");
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "전송을 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    LoadingDialog.dismiss();
                    int statusCode = response.code();
                    Log.i("MyLog", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "데이터 전송을 실패하였습니다.", Toast.LENGTH_SHORT).show();
                LoadingDialog.dismiss();
            }
        });

    }
}