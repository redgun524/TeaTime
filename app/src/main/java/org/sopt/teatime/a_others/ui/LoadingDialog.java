package org.sopt.teatime.a_others.ui;

import android.content.Context;

public class LoadingDialog {

    private static LoadingDialogFragment dialog;

    public static void show(Context ctx) {
        dialog = new LoadingDialogFragment(ctx);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void dismiss() {
        dialog.dismiss();
    }

}
