package org.sopt.teatime.c_activities.write.content.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.function.ContentTemplateController;
import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.a_others.ui.TeaTimeDialog;
import org.sopt.teatime.a_others.ui.TeaTimeDialogListener;
import org.sopt.teatime.a_others.ui.TeaTimeDialogOne;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.c_activities.write.content.component.ContentLayerRecyclerViewAdapter;
import org.sopt.teatime.c_activities.write.content.model.ContentTemplateIds;
import org.sopt.teatime.c_activities.write.content.model.ContentTemplateViewItem;
import org.sopt.teatime.c_activities.write.cover.component.FontRecyclerViewAdapter;
import org.sopt.teatime.c_activities.write.cover.model.FontRecyclerItem;
import org.sopt.teatime.c_activities.write.cover.model.LayerRecyclerItem;
import org.sopt.teatime.c_activities.write.cover.model.OnItemClickListener;
import org.sopt.teatime.c_activities.write.cover.model.TemplateRecyclerItem;
import org.sopt.teatime.c_activities.write.register.view.RegisterActivity;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawContentActivity extends AppCompatActivity implements View.OnClickListener, DrawContentView {

    @BindView(R.id.toolbar_Cover) Toolbar toolbar;
    @BindView(R.id.layoutBottom_draw) LinearLayout layoutBottom;
    @BindView(R.id.btnTitle_draw) ImageButton btnTitle;
    @BindView(R.id.btnContent_draw) ImageButton btnContent;
    @BindView(R.id.btnLayer_draw) ImageButton btnLayer;
    @BindView(R.id.btnPicture_draw) ImageButton btnPicture;
    @BindView(R.id.btnFont_draw) ImageButton btnFont;
    @BindView(R.id.btnFontSize_draw) ImageButton btnFontSize;
    @BindView(R.id.btnFontColor_draw) ImageButton btnFontColor;
    @BindView(R.id.templeteView_Cover) FrameLayout templateView;

    LinearLayoutManager linearLayoutManager;
    ArrayList<LayerRecyclerItem> layerRecyclerItems;
    ArrayList<FontRecyclerItem> fontRecyclerItems;
    ContentLayerRecyclerViewAdapter layerAdapter;
    FontRecyclerViewAdapter fontAdapter;
    RecyclerView recyclerViewLayer;
    RecyclerView recyclerViewFont;

    ContentTemplateViewItem viewItem;
    LayoutInflater inflater;
    TemplateRecyclerItem templateCover;
    ArrayList<TemplateRecyclerItem> templateList;
    Uri mImageCaptureUri;
    Bitmap photo;
    String isCover;
    String strDesc = "";
    Float ratio;
    String font;
    Typeface typeface_ng, typeface_ngb, typeface_nbg, typeface_nb, typeface_nm, typeface_np, typeface_jua;
    ArrayList<Typeface> typefaces;

    Contents contents = new Contents();

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        initTypeFaces();

        inflater = LayoutInflater.from(getApplicationContext());
        layerRecyclerItems = new ArrayList<>();
        fontRecyclerItems = new ArrayList<>();
        templateCover = new TemplateRecyclerItem();

        initTemplate();
        initItems();
        receiveIntent();
        initBottomLayout();
        clickEvent();
    }

    private void initBottomLayout() {
        layoutBottom.removeAllViews();
        ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_bottom_contents, layoutBottom, true);
        final EditText editDesc = (EditText)layoutBottom.findViewById(R.id.txtTitle_contents);
        editDesc.setVisibility(View.INVISIBLE);
        final EditText editSubTitle = (EditText)layoutBottom.findViewById(R.id.txtSubtitle_contents);
        editSubTitle.setVisibility(View.INVISIBLE);
        final EditText editContent = (EditText)layoutBottom.findViewById(R.id.txtContent_contents);
        editContent.setVisibility(View.VISIBLE);
        layoutBottom.findViewById(R.id.btnSend_contents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strDesc = editContent.getText().toString();
                setPreviewTexts();

                InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(editContent.getWindowToken(), 0);
            }
        });
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

    private void adjustFontSize() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        ratio = Float.intBitsToFloat(templateView.getHeight()) /  Float.intBitsToFloat(metrics.heightPixels);

        Float descFontSize = viewItem.txtDesc.getTextSize() * ratio;
        viewItem.txtDesc.setTextSize(TypedValue.COMPLEX_UNIT_PX, descFontSize);
    }

    private void initTemplate() {
        Intent intent = getIntent();
        if (intent.getParcelableExtra("Contents") == null) {
            View v = inflater.inflate(R.layout.template_101, templateView);
            viewItem = new ContentTemplateViewItem();
            ContentTemplateIds id = new ContentTemplateIds(getApplicationContext(), ContentTemplateController.TEMPLETE_BLACK_1);
            viewItem.imgPicture = (ImageView)v.findViewById(id.imgPicId);
            viewItem.txtDesc = (TextView)v.findViewById(id.txtDescId);
            contents.template_code = ContentTemplateController.TEMPLETE_BLACK_1;
            contents.page = intent.getIntExtra("position", 0);
        } else {
            contents = intent.getParcelableExtra("Contents");
            ContentTemplateIds ids = new ContentTemplateIds(getApplicationContext(), contents.template_code);
            View v = inflater.inflate(ids.layoutId, templateView);
            viewItem = new ContentTemplateViewItem();
            viewItem.imgPicture = (ImageView)v.findViewById(ids.imgPicId);
            viewItem.txtDesc = (TextView)v.findViewById(ids.txtDescId);
            Glide.with(this)
                    .load(contents.url)
                    .into(viewItem.imgPicture);
            viewItem.txtDesc.setText(contents.description);
        }

        templateView.post(new Runnable() {
            public void run() {
                adjustFontSize();
            }
        });

        Glide.with(this).load(mImageCaptureUri).into(viewItem.imgPicture);
    }

    public void initLayoutManager() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    }

    public void initItems() {
        LayerRecyclerItem[] layerRecyclerItem = new LayerRecyclerItem[5];
        layerRecyclerItem[0] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_BLACK_1);
        layerRecyclerItem[1] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_BLACK_2);
        layerRecyclerItem[2] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_BLACK_3);
        layerRecyclerItem[3] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_200_1);
        layerRecyclerItem[4] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_200_2);
        /*layerRecyclerItem[5] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_200_3);
        layerRecyclerItem[6] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_300_1);
        layerRecyclerItem[7] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_300_2);
        layerRecyclerItem[8] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_300_3);
        layerRecyclerItem[9] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_300_4);
        layerRecyclerItem[10] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_400_1);
        layerRecyclerItem[11] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_400_2);
        layerRecyclerItem[12] = new LayerRecyclerItem(ContentTemplateController.TEMPLETE_400_3);*/

        FontRecyclerItem[] fontRecyclerItem = new FontRecyclerItem[7];
        fontRecyclerItem[0] = new FontRecyclerItem(FontController.PATH_NANUM_GOTHIC, "나눔고딕");
        fontRecyclerItem[1] = new FontRecyclerItem(FontController.PATH_NANUM_GOTHIC_BOLD, "나눔고딕볼드");
        fontRecyclerItem[2] = new FontRecyclerItem(FontController.PATH_NANUM_BARUN_GOTHIC, "나눔바른고딕");
        fontRecyclerItem[3] = new FontRecyclerItem(FontController.PATH_NANUM_MYEONGJO, "나눔명조");
        fontRecyclerItem[4] = new FontRecyclerItem(FontController.PATH_NANUM_BRUSH, "나눔브러쉬");
        fontRecyclerItem[5] = new FontRecyclerItem(FontController.PATH_NANUM_PEN, "나눔펜");
        fontRecyclerItem[6] = new FontRecyclerItem(FontController.PATH_JUA, "배민주아체");

        for(int i  = 0; i < 5; i++)
            layerRecyclerItems.add(layerRecyclerItem[i]);

        for(int i = 0; i < 7; i++)
            fontRecyclerItems.add(fontRecyclerItem[i]);

        layerAdapter = new ContentLayerRecyclerViewAdapter(this, layerRecyclerItems, this);
        fontAdapter = new FontRecyclerViewAdapter(this, fontRecyclerItems, typefaces, new OnItemClickListener() {
            @Override
            public void onItemClick(FontRecyclerItem item) {
                templateCover.setFont(item.getFont());
                setTextsFont(item.getFont());
            }
        });
    }

    public void setTextsFont(final String font) {
        this.font = font;
        contents.font_code = FontController.getCodeByFont(font);
        typeFaceWorking();
    }

    private void typeFaceWorking() {
        viewItem.txtDesc.setTypeface(setTypeFace(font));
    }

    private Typeface setTypeFace(String path) {
        switch (path) {
            case FontController.PATH_NANUM_GOTHIC :
                return typeface_ng;
            case FontController.PATH_NANUM_GOTHIC_BOLD :
                return typeface_ngb;
            case FontController.PATH_NANUM_BARUN_GOTHIC :
                return typeface_ng;
            case FontController.PATH_NANUM_MYEONGJO :
                return typeface_nm;
            case FontController.PATH_NANUM_BRUSH :
                return typeface_nb;
            case FontController.PATH_NANUM_PEN :
                return typeface_np;
            case FontController.PATH_JUA :
                return typeface_jua;
            default:
                return typeface_ng;
        }
    }

    public void initFontRecyclerView()
    {
        recyclerViewFont.setHasFixedSize(true);
        recyclerViewFont.setLayoutManager(linearLayoutManager);
        recyclerViewFont.setAdapter(fontAdapter);
    }

    public void initLayerRecyclerView()
    {
        recyclerViewLayer.setHasFixedSize(true);
        recyclerViewLayer.setLayoutManager(linearLayoutManager);
        recyclerViewLayer.setAdapter(layerAdapter);
    }

    public void receiveIntent()
    {
        btnTitle.setVisibility(View.VISIBLE);
        btnContent.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.btnDone_Cover)
    void setBtnDone() {
        if (TextUtils.isEmpty(contents.description)) {
            final TeaTimeDialogOne dialog = new TeaTimeDialogOne(this);
            dialog.setMessage("내용을 입력해주세요.");
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
        } else if (contents.url == null) {
            final TeaTimeDialogOne dialog = new TeaTimeDialogOne(this);
            dialog.setMessage("사진을 넣어주세요.");
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
        } else {
            Intent intent = new Intent(getApplication(), RegisterActivity.class);
            intent.putExtra("Contents", contents);
            setResult(2, intent);
            finish();
        }
    }

    @Override
    public void setPreview(ContentTemplateIds ids) {
        templateView.removeAllViews();
        View v = inflater.inflate(ids.layoutId, templateView);
        contents.template_code = ContentTemplateController.getCodeById(ids.layoutId);
        viewItem = new ContentTemplateViewItem();
        viewItem.imgPicture = (ImageView)v.findViewById(ids.imgPicId);
        viewItem.txtDesc = (TextView)v.findViewById(ids.txtDescId);
        viewItem.txtDesc.setText(strDesc);
        Glide.with(getApplicationContext())
                .load(mImageCaptureUri)
                .into(viewItem.imgPicture);
        adjustFontSize();
    }

    @Override
    public void onBackPressed() {
        final TeaTimeDialog dialog = new TeaTimeDialog(this);
        dialog.setMessage("수정 사항은 저장되지 않습니다.\n뒤로 가시겠습니까?");
        dialog.setOnTeaTimeDialogClickListener(new TeaTimeDialogListener() {
            @Override
            public void clickYes() {
                dialog.dismiss();
                finish();
            }

            @Override
            public void clickNo() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 카메라에서 이미지 가져오기
     */
    private void doTakePhotoAction()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 임시로 사용할 파일의 경로를 생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    /**
     * 앨범에서 이미지 가져오기
     */
    private void doTakeAlbumAction()
    {
        /**
         * Intent의 createChooser 속성을 이용하여 갤러리 어플 수행 요청을 쉽게 할 수도 있다
         */
        // startActivityForResult(Intent.createChooser(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), "Choose an image"), PICK_FROM_ALBUM);

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    /**
     * startActivity의 result 값을 받을 때 호출되는 함수로서 외부에서 intent가 넘어올 때
     * 갤러리에서 온 요청인지 카메라에서 온 요청인지 구별하기 위하여 overring을 통해 구현하였다.
     **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
//        if(resultCode != RESULT_OK)
//            return;

        switch(requestCode)
        {
            case CROP_FROM_CAMERA:
            {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.
                final Bundle extras = intent.getExtras();

                if(extras != null){
                    photo = extras.getParcelable("data");
//                Glide.with(this).load(mImageCaptureUri).into(imgPicture);
                    viewItem.imgPicture.setImageBitmap(photo);
                    contents.url = mImageCaptureUri.toString();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ;
                    // RelativeLayout layout_template = (RelativeLayout) findViewById(R.id.layout_template);
                    // ImageView imgBackground = new ImageView(this);
                    // imgBackground.setImageBitmap(photo);
                    // layout_template.addView(imgBackground);
                    // RelativeLayout.LayoutParams img_param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    // imgBackground.setLayoutParams(img_param);
                }

                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                if(f.exists())
                    f.delete();
                break;
            }
            case PICK_FROM_ALBUM:
                mImageCaptureUri = intent.getData();
                Glide.with(this).load(mImageCaptureUri).into(viewItem.imgPicture);
                contents.url = mImageCaptureUri.toString();
                break;
            case PICK_FROM_CAMERA:
            {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.
//                Intent intent2 = new Intent("com.android.camera.action.CROP");
//                intent2.setDataAndType(mImageCaptureUri, "image/*");

                /**
                 * putExtra에서 outputX,Y 값을 지정하면 반드시 해당 크기로만 crop하게 된다.
                 * aspectX,Y는 가로 세로의 비율을 정하는 것이며 값을 넣지 않을 경우 원하는 비율대로 crop 할 수 있다.
                 * intent를 통하여 이미지를 보내는 방식에는 putExtra("output", Uri)와 putExtra("return-data", true)가 있는데
                 * crop 화면에서 저장을 누를 시 첫 번째 방식은 해당 Uri로 파일을 저장하겠다는 뜻이며, 반드시 outputX,Y 값을 입력해주어야 한다.
                 * 두 번째는 Bundle을 통해 bitmap으로 데이터를 받아오겠다는 뜻이다. Bundle을 통하여 bitmap을 받아올 시 기기마다 인식하는
                 * outputX,Y 값이 달라 crop 크기만큼 먹는 기기가 있는 반면 바로 뻗어버리는 기기도 있다. 따라서 crop한 파일을 bitmap 대신
                 * jpg 파일로 저장시켜 불러오는 방식이 편할 수 있다.
                 **/
//                intent2.putExtra("outputX",300);
//                intent2.putExtra("outputY", 400);
                // intent2.putExtra("aspectX", 1);
                // intent2.putExtra("aspectY", 1);
//                intent2.putExtra("scale", true);
//                intent2.putExtra("return-data", true);
//                intent2.putExtra("output", mImageCaptureUri);
//                startActivityForResult(intent2, CROP_FROM_CAMERA);
                Glide.with(this).load(mImageCaptureUri).into(viewItem.imgPicture);
                contents.url = mImageCaptureUri.toString();

                break;
            }
        }
    }

    public void clickEvent()
    {
        btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutBottom.removeAllViews();
                ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_bottom_contents, layoutBottom, true);
                final EditText editDesc = (EditText)layoutBottom.findViewById(R.id.txtTitle_contents);
                editDesc.setVisibility(View.INVISIBLE);
                final EditText editSubTitle = (EditText)layoutBottom.findViewById(R.id.txtSubtitle_contents);
                editSubTitle.setVisibility(View.INVISIBLE);
                final EditText editContent = (EditText)layoutBottom.findViewById(R.id.txtContent_contents);
                editContent.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(contents.description)) {
                    editContent.setText(contents.description);
                }
                layoutBottom.findViewById(R.id.btnSend_contents).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        strDesc = editContent.getText().toString();
                        setPreviewTexts();
                        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        mInputMethodManager.hideSoftInputFromWindow(editContent.getWindowToken(), 0);
                    }
                });
            }
        });
        btnContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutBottom.removeAllViews();
                ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_bottom_contents, layoutBottom, true);
                Float viewHeight = Float.intBitsToFloat(recyclerViewLayer.getHeight() - getPixelFromDP(getApplicationContext(), 10));
                layerAdapter.setViewHeight(viewHeight);
                /*layoutBottom.findViewById(R.id.txtTitle_contents).setVisibility(View.INVISIBLE);
                layoutBottom.findViewById(R.id.txtSubtitle_contents).setVisibility(View.INVISIBLE);
                layoutBottom.findViewById(R.id.txtContent_contents).setVisibility(View.VISIBLE);
                txtTitle.setVisibility(View.INVISIBLE);
                txtSubtitle.setVisibility(View.INVISIBLE);
                txtContent.setVisibility(View.VISIBLE);*/
            }
        });
        btnLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutBottom.removeAllViews();
                ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_bottom_layer, layoutBottom, true);
                recyclerViewLayer = (RecyclerView) layoutBottom.findViewById(R.id.recyclerView_layer);
                initLayoutManager();
                initLayerRecyclerView();
            }
        });
        btnPicture.setOnClickListener(this);
        btnFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutBottom.removeAllViews();
                ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_bottom_font, layoutBottom, true);
                recyclerViewFont = (RecyclerView) layoutBottom.findViewById(R.id.recyclerView_font);
                initLayoutManager();
                initFontRecyclerView();
            }
        });
        btnFontSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutBottom.removeAllViews();
                ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_bottom_font_size_content, layoutBottom, true);
                TextView txtSmall = (TextView) layoutBottom.findViewById(R.id.btnSmallText_content);
                TextView txtNormal = (TextView) layoutBottom.findViewById(R.id.btnNormalText_content);
                TextView txtLarge = (TextView) layoutBottom.findViewById(R.id.btnLargeText_content);
                txtSmall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setPreviewFontSize(FontController.DESC_SMALL);
                        contents.font_size = FontController.FONT_SMALL_CODE;
                    }
                });
                txtNormal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setPreviewFontSize(FontController.DESC_NORMAL);
                        contents.font_size = FontController.FONT_NORMAL_CODE;
                    }
                });
                txtLarge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setPreviewFontSize(FontController.DESC_LARGE);
                        contents.font_size = FontController.FONT_LARGE_CODE;
                    }
                });
            }
        });
        btnFontColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutBottom.removeAllViews();
                ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_bottom_font_color, layoutBottom, true);
                TextView txtWhite = (TextView) layoutBottom.findViewById(R.id.btnWhiteText);
                TextView txtGray = (TextView) layoutBottom.findViewById(R.id.btnGrayText);
                TextView txtBlack = (TextView) layoutBottom.findViewById(R.id.btnBlackText);

                txtWhite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setPreviewFontColor(FontController.FONT_WHITE_CODE);
                    }
                });
                txtGray.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setPreviewFontColor(FontController.FONT_GRAY9_CODE);
                    }
                });
                txtBlack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setPreviewFontColor(FontController.FONT_BLACK_CODE);
                    }
                });
            }
        });
    }

    private void setPreviewFontColor(int code) {
        int resId;
        switch (code) {
            case FontController.FONT_WHITE_CODE :
                resId = R.color.white;
                break;
            case FontController.FONT_GRAY9_CODE:
                resId = R.color.gray9;
                break;
            case FontController.FONT_BLACK_CODE :
                resId = R.color.black;
                break;
            default:
                resId = R.color.black;
                break;
        }
        viewItem.txtDesc.setTextColor(getColorFromRes(getApplicationContext(), resId));
        contents.font_color = code;
    }

    private void setPreviewFontSize(float titleSize) {
        viewItem.txtDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize * ratio);
    }

    private int getColorFromRes(Context ctx, int resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ctx.getResources().getColor(resId, ctx.getTheme());
        }
        else{
            return ctx.getResources().getColor(resId);
        }
    }
    public int getPixelFromDP(Context ctx, int dpValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) dpValue, ctx.getResources().getDisplayMetrics());
    }

    private void setPreviewTexts() {
        viewItem.txtDesc.setText(strDesc);
        contents.description = strDesc;
    }

    @Override
    public void onClick(View v)
    {
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakePhotoAction();
            }
        };

        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        new AlertDialog.Builder(this)
                .setTitle("업로드할 이미지 선택")
                .setPositiveButton("사진촬영", cameraListener)
                .setNeutralButton("앨범선택", albumListener)
                .setNegativeButton("취소", cancelListener)
                .show();
    }
}
