package org.sopt.teatime.c_activities.search.view;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.a_others.ui.LoadingDialog;
import org.sopt.teatime.a_others.ui.TeaTimeDialogListener;
import org.sopt.teatime.a_others.ui.TeaTimeDialogOne;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.domain.ListModel;
import org.sopt.teatime.c_activities.search.component.CategoryListAdapter;
import org.sopt.teatime.c_activities.search.component.RecyclerAdapter;
import org.sopt.teatime.c_activities.search.presenter.ListPresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchView {

    @BindView(R.id.toolbar_Search)
    Toolbar toolbar;
    @BindView(R.id.editSearch_Search)
    EditText editSearch; //검색창
    @BindView(R.id.btnSearch_Search)
    ImageButton btnSearch;
    @BindView(R.id.listCategory_Search)
    ListView listCategory;
    @BindView(R.id.recyclerView_Search)
    RecyclerView recyclerView;
    //검색확인버튼
    @BindView(R.id.txtKeyword_Search)
    TextView keyWordBar; //검색창 밑에 키워드라고 적혀있는부분
    @BindView(R.id.txtTitle_Search)
    TextView txtTitle;
    @BindView(R.id.imgKeywordBar)
    ImageView imgKeyword;

    ListPresenterImpl presenter;
    CategoryListAdapter categoryListAdapter;
    RecyclerAdapter adapter;
    GridLayoutManager layoutManager;
    Typeface typeJua, typeGo;

    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        adapter = new RecyclerAdapter(getApplicationContext());
        categoryListAdapter = new CategoryListAdapter(this);
        presenter = new ListPresenterImpl(this);
        layoutManager = new GridLayoutManager(this, 2);

        initViews();
        initToolbar();
        setDummyModels();

    }

    private void initViews() {

        typeJua = Typeface.createFromAsset(getAssets(), FontController.PATH_JUA);
        typeGo = Typeface.createFromAsset(getAssets(), FontController.PATH_NANUM_BARUN_GOTHIC);

        listCategory.setAdapter(categoryListAdapter);
        listCategory.setDivider(null); //리스트뷰 아이템 사이에 분리선을 없애준다.
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //글씨체 변경
        txtTitle.setTypeface(typeJua);
        keyWordBar.setTypeface(typeJua);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 내용이없을 때 리스트뷰 그대로 냅두기.

                //버튼을 클릭하면 리스트뷰를 안보이게하고 검색결과인 그리드뷰를 보이게 한다.

                LoadingDialog.show(SearchActivity.this);
                presenter.getUserList(key);
            }
        });

        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        LoadingDialog.show(SearchActivity.this);
                        presenter.getUserList(key);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setDummyModels() {
        String category1 = "여행";
        String category2 = "디자인";
        String category3 = "영화";
        String category4 = "책";
        String category5 = "일상";
        String category6 = "게임";
        String category7 = "패션";
        String category8 = "연예";
        String category9 = "육아";
        String category10 = "다이어트";
        String category11 = "컬쳐";
        String category12 = "음악";

        ArrayList<ListModel> model01 = new ArrayList<>();
        model01.add(new ListModel(category1, R.drawable.test));
        model01.add(new ListModel(category2, R.drawable.test));
        model01.add(new ListModel(category3, R.drawable.test));
        model01.add(new ListModel(category4, R.drawable.test));
        model01.add(new ListModel(category5, R.drawable.test));
        model01.add(new ListModel(category6, R.drawable.test));
        model01.add(new ListModel(category7, R.drawable.test));
        model01.add(new ListModel(category8, R.drawable.test));
        model01.add(new ListModel(category9, R.drawable.test));
        model01.add(new ListModel(category10, R.drawable.test));
        model01.add(new ListModel(category11, R.drawable.test));
        model01.add(new ListModel(category12, R.drawable.test));
        categoryListAdapter.setItems(model01, this);
    }

    private void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @OnClick(R.id.btnSearch_Search)
    void setBtnSearch() {

        key = editSearch.getText().toString();
        //검색내용이 비어있을 때
        if (TextUtils.isEmpty(key)) {
            final TeaTimeDialogOne dialog = new TeaTimeDialogOne(this);
            dialog.setMessage("검색 내용을 제대로 입력해주세요.");
            dialog.setOnTeaTimeDialogClickListener(new TeaTimeDialogListener() {
                @Override
                public void clickYes() {
                    dialog.dismiss();
                }

                @Override
                public void clickNo() {

                }
            });
        } else {
            //버튼을 클릭하면 리스트뷰를 안보이게하고 검색결과인 그리드뷰를 보이게 한다.
            LoadingDialog.show(this);
            presenter.getUserList(key);
        }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setDatas(ArrayList<Cover> coverList) {
        LoadingDialog.dismiss();

        listCategory.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

        //검색 시 #키워드부분 글씨체와 글씨크기 변경 (배달민족 주아체 -> 나눔바른고딕)
        keyWordBar.setTypeface(typeGo);

        //서브타이틀의 Text를 변경한다. (변경 전 : Category -> 변경 후 : 검색내용)
        keyWordBar.setText("'" + editSearch.getText() + "' 검색 결과 입니다.");
        //검색을 한 후 검색창을 빈칸으로 만들어준다.
        editSearch.setText("");

        //키워드바에서 티백모양 이미지를 없애준다.
        imgKeyword.setVisibility(View.GONE);

        adapter.setCoverList(coverList);
    }

    @Override
    public void itemClickEvent(String key) {
        presenter.getUserList(key);
        listCategory.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void noResultEvent() {
        final TeaTimeDialogOne dialog = new TeaTimeDialogOne(this);
        dialog.setMessage("검색 결과가 없습니다.");
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
