package org.sopt.teatime.c_activities.contents.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.ui.LoadingDialog;
import org.sopt.teatime.a_others.ui.TeaTimeDialogListener;
import org.sopt.teatime.a_others.ui.TeaTimeDialogOne;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.c_activities.contents.component.ContentsAdapter;
import org.sopt.teatime.c_activities.contents.presenter.ContentsPresenter;
import org.sopt.teatime.c_activities.contents.presenter.ContentsPresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentsActivity extends AppCompatActivity implements mContentsView{

    @BindView(R.id.viewPager_Contents) ViewPager viewPager;
    @BindView(R.id.toolbar_Contents) Toolbar toolbar;

    ContentsPresenter contentsPresenter;
    ContentsAdapter contentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contents);

        ButterKnife.bind(this);
        initToolbar();

        contentsPresenter = new ContentsPresenterImpl(this);
        contentsAdapter = new ContentsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(contentsAdapter);

        LoadingDialog.show(this);
        contentsPresenter.getContentsList();
    }

    private void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public void setDatas(ArrayList<Contents> contentsList) {
        LoadingDialog.dismiss();
        contentsAdapter.setContentsList(contentsList);
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
