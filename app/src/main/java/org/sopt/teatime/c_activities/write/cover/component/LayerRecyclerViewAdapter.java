package org.sopt.teatime.c_activities.write.cover.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sopt.teatime.R;
import org.sopt.teatime.c_activities.write.cover.model.CoverTemplateId;
import org.sopt.teatime.c_activities.write.cover.model.LayerRecyclerItem;
import org.sopt.teatime.c_activities.write.cover.view.DrawView;

import java.util.ArrayList;


/**
 * Created by Hyeonu on 2016-03-22.
 */
public class LayerRecyclerViewAdapter extends RecyclerView.Adapter<LayerRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LayerRecyclerItem> items;
    private DrawView drawView;
    private Float viewHeight;

    public LayerRecyclerViewAdapter(Context applicationContext, ArrayList<LayerRecyclerItem> items, DrawView drawView) {
        this.context = applicationContext;
        this.items = items;
        this.drawView = drawView;
    }

    public void setViewHeight(Float viewHeight) {
        this.viewHeight = viewHeight;
    }

    // inflate view and set to ViewHolder
    @Override
    public LayerRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layer, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(parent.getWidth() / 4, ViewGroup.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(layoutParams);
        return new ViewHolder(v);
    }

    //this method is called when user scroll and set items of RecyclerView
    @Override
    public void onBindViewHolder(LayerRecyclerViewAdapter.ViewHolder holder, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final CoverTemplateId ids = new CoverTemplateId(context, items.get(position).getTemplateCode());
        Log.i("MyTag", ""+ids.layoutId);
        View v = inflater.inflate(ids.layoutId, holder.view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setPreview(ids);
            }
        });

        Float ratio = Float.parseFloat(""+0.2);

        Float titleFontSize = ((TextView)v.findViewById(ids.txtTitleId)).getTextSize() * ratio;
        Float subTitleFontSize = ((TextView)v.findViewById(ids.txtSubTitleId)).getTextSize() * ratio;
        Float keyword1FontSize = ((TextView)v.findViewById(ids.txtKeywordId1)).getTextSize() * ratio;
        Float keyword2FontSize = ((TextView)v.findViewById(ids.txtKeywordId2)).getTextSize() * ratio;
        Float keyword3FontSize = ((TextView)v.findViewById(ids.txtKeywordId3)).getTextSize() * ratio;
        Float authorFontSize = ((TextView)v.findViewById(ids.txtAuthorId)).getTextSize() * ratio;

        ((TextView)v.findViewById(ids.txtTitleId)).setTextSize(TypedValue.COMPLEX_UNIT_PX, titleFontSize);
        ((TextView)v.findViewById(ids.txtSubTitleId)).setTextSize(TypedValue.COMPLEX_UNIT_PX, subTitleFontSize);
        ((TextView)v.findViewById(ids.txtKeywordId1)).setTextSize(TypedValue.COMPLEX_UNIT_PX, keyword1FontSize);
        ((TextView)v.findViewById(ids.txtKeywordId2)).setTextSize(TypedValue.COMPLEX_UNIT_PX, keyword2FontSize);
        ((TextView)v.findViewById(ids.txtKeywordId3)).setTextSize(TypedValue.COMPLEX_UNIT_PX, keyword3FontSize);
        ((TextView)v.findViewById(ids.txtAuthorId)).setTextSize(TypedValue.COMPLEX_UNIT_PX, authorFontSize);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public FrameLayout view;
        public ViewGroup parent;

        public ViewHolder(View view) {
            super(view);
            this.view = (FrameLayout)view.findViewById(R.id.templateView_List);
            parent = (ViewGroup) view;
        }
    }
}
