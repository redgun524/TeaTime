package org.sopt.teatime.a_others.function;

import android.content.Context;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.ui.TeaTimeDialogListener;
import org.sopt.teatime.a_others.ui.TeaTimeDialogOne;

public class ErrorController {

    //네트워크 에러 발생 시 다이얼로그 띄움
    public static void notifyNetworkError(Context ctx) {

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

}
