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

public class TeaTimeDialog extends Dialog {

    TeaTimeDialog dialog;
    @BindView(R.id.txtMsg_dialog)
    TextView txtMsg;
    @BindView(R.id.txtSubMsg_dialog)
    TextView txtSubMsg;
    @BindView(R.id.btnYes_dialog)
    Button btnYes;
    @BindView(R.id.btnNo_dialog)
    Button btnNo;

    public TeaTimeDialog(Context context) {
        super(context);

        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_teatime);
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
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickNo();
                dialog.dismiss();
            }
        });
    }
}
