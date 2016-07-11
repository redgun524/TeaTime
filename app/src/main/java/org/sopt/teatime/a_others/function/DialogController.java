package org.sopt.teatime.a_others.function;

import android.content.Context;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.ui.TeaTimeDialog;
import org.sopt.teatime.a_others.ui.TeaTimeDialogListener;
import org.sopt.teatime.a_others.ui.TeaTimeDialogOne;

/**
 * Created by 품파파품파 on 2016-07-12.
 */
public class DialogController {

    //네트워크 에러 발생 시 다이얼로그
    public static void showNetworkErrorDialog(Context ctx) {

        final TeaTimeDialogOne dialog = new TeaTimeDialogOne(ctx);
        dialog.setMessage(ResourceController.getStringFromRes(ctx, R.string.dialog_network_err));
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

    //로그아웃 시 다이얼로그
    public static void showLogoutDialog(Context ctx) {

        final TeaTimeDialog dialog = new TeaTimeDialog(ctx);
        dialog.setMessage(ctx.getResources().getString(R.string.dialog_logout_msg));
        dialog.setOnTeaTimeDialogClickListener(new TeaTimeDialogListener() {
            @Override
            public void clickYes() {
                dialog.dismiss();
            }

            @Override
            public void clickNo() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //작성 중 BackButton 클릭 시 다이얼로그
    public static void showCancelWritingDialog(Context ctx) {

        final TeaTimeDialog dialog = new TeaTimeDialog(ctx);
        dialog.setMessage(ctx.getResources().getString(R.string.dialog_cancel_writing));
        dialog.setOnTeaTimeDialogClickListener(new TeaTimeDialogListener() {
            @Override
            public void clickYes() {
                dialog.dismiss();
            }

            @Override
            public void clickNo() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //포토북 작성 중 텍스트 입력 안하고 완료 버튼 클릭 시 다이얼로그
    public static void showTextNullDialog(Context ctx) {

        final TeaTimeDialogOne dialog = new TeaTimeDialogOne(ctx);
        dialog.setMessage(ctx.getResources().getString(R.string.dialog_text_null));
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

    //포토북 작성 중 사진 입력 안하고 완료 버튼 클릭 시 다이얼로그
    public static void showPhotoNullDialog(Context ctx) {

        final TeaTimeDialogOne dialog = new TeaTimeDialogOne(ctx);
        dialog.setMessage(ctx.getResources().getString(R.string.dialog_photo_null));
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
