package org.sopt.teatime.c_activities.write.content.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.function.ContentTemplateController;
import org.sopt.teatime.c_activities.write.content.model.ContentTemplateIds;
import org.sopt.teatime.c_activities.write.content.view.DrawContentView;
import org.sopt.teatime.c_activities.write.cover.model.LayerRecyclerItem;

import java.util.ArrayList;


/**
 * Created by Hyeonu on 2016-03-22.
 */
public class ContentLayerRecyclerViewAdapter extends RecyclerView.Adapter<ContentLayerRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LayerRecyclerItem> items;
    private DrawContentView drawView;
    private Float viewHeight;

    public ContentLayerRecyclerViewAdapter(Context applicationContext, ArrayList<LayerRecyclerItem> items, DrawContentView drawView) {
        this.context = applicationContext;
        this.items = items;
        this.drawView = drawView;
    }

    public void setViewHeight(Float viewHeight) {
        this.viewHeight = viewHeight;
    }

    // inflate view and set to ViewHolder
    @Override
    public ContentLayerRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layer, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(parent.getWidth() / 4, ViewGroup.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(layoutParams);
        return new ViewHolder(v);
    }

    //this method is called when user scroll and set items of RecyclerView
    @Override
    public void onBindViewHolder(ContentLayerRecyclerViewAdapter.ViewHolder holder, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final ContentTemplateIds ids = new ContentTemplateIds(context, items.get(position).getTemplateCode());
        View v = inflater.inflate(ids.layoutId, holder.view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setPreview(ids);
            }
        });

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        Float ratio = Float.parseFloat(""+0.2);
        Log.i("MyTag", "Id가 뭔데 이자식아 : "+ ContentTemplateController.getCodeById(ids.txtDescId));
        Float titleFontSize = ((TextView)v.findViewById(ids.txtDescId)).getTextSize() * ratio;

        ((TextView)v.findViewById(ids.txtDescId)).setTextSize(TypedValue.COMPLEX_UNIT_PX, titleFontSize);
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
