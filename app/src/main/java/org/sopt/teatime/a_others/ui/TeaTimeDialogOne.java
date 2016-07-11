package org.sopt.teatime.a_others.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.sopt.teatime.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeaTimeDialogOne extends Dialog {

    TeaTimeDialogOne dialog;
    @BindView(R.id.txtMsg_dialogOne)
    TextView txtMsg;
    @BindView(R.id.txtSubMsg_dialogOne)
    TextView txtSubMsg;
    @BindView(R.id.btnDone_dialogOne)
    Button btnYes;

    public TeaTimeDialogOne(Context context) {
        super(context);

        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_teatime_one);
        dialog = this;

        ButterKnife.bind(this);
    }

    public void setMessage(String msg) {
        txtMsg.setText(msg);
    }

    public void setSubMessage(String msg) {
        if (msg == null || msg.equals("")) {
            txtSubMsg.setVisibility(View.GONE);
        } else {
            txtSubMsg.setVisibility(View.VISIBLE);
            txtSubMsg.setText(msg);
        }
    }

    public void setOnTeaTimeDialogClickListener(final TeaTimeDialogListener listener) {
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickYes();
                dialog.dismiss();
            }
        });
    }
}
