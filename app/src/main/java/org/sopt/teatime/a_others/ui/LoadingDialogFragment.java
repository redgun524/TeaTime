package org.sopt.teatime.a_others.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import org.sopt.teatime.R;

public class LoadingDialogFragment extends Dialog {
    public LoadingDialogFragment(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loading);
    }
}
