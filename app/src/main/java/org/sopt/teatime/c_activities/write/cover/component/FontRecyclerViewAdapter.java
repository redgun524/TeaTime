package org.sopt.teatime.c_activities.write.cover.component;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.c_activities.write.cover.model.FontRecyclerItem;
import org.sopt.teatime.c_activities.write.cover.model.OnItemClickListener;
import org.sopt.teatime.c_activities.write.cover.model.TemplateRecyclerItem;

import java.util.ArrayList;

/**
 * Created by Hyeonu on 2016-03-22.
 */
public class FontRecyclerViewAdapter extends RecyclerView.Adapter<FontRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<FontRecyclerItem> items;
    private TemplateRecyclerItem templateItem;
    private OnItemClickListener listener;
    private ArrayList<Typeface> typefaces;

    public FontRecyclerViewAdapter(Context applicationContext, ArrayList<FontRecyclerItem> items, ArrayList<Typeface> typefaces, OnItemClickListener listener) {
        this.context = applicationContext;
        this.items = items;
        this.listener = listener;
        this.templateItem = new TemplateRecyclerItem();
        this.typefaces = typefaces;
    }

    // inflate view and set to ViewHolder
    @Override
    public FontRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_font, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(parent.getWidth() / 3, ViewGroup.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(layoutParams);
        return new ViewHolder(v);
    }

    //this method is called when user scroll and set items of RecyclerView
    @Override
    public void onBindViewHolder(FontRecyclerViewAdapter.ViewHolder holder, final int position) {
        templateItem.setFont(items.get(position).getFontName());
        final FontRecyclerItem item = items.get(position);
        holder.textView.setText(items.get(position).getFontName());
        holder.textView.setTypeface(getTypeFace(items.get(position).getFont()));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onItemClick(item);
                    }
                });
            }
        });
    }

    private Typeface getTypeFace(String path) {
        switch (path) {
            case FontController.PATH_NANUM_GOTHIC :
                return typefaces.get(0);
            case FontController.PATH_NANUM_GOTHIC_BOLD :
                return typefaces.get(1);
            case FontController.PATH_NANUM_BARUN_GOTHIC :
                return typefaces.get(2);
            case FontController.PATH_NANUM_MYEONGJO :
                return typefaces.get(3);
            case FontController.PATH_NANUM_BRUSH :
                return typefaces.get(4);
            case FontController.PATH_NANUM_PEN :
                return typefaces.get(5);
            case FontController.PATH_JUA :
                return typefaces.get(6);
            default:
                return typefaces.get(0);
        }
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.txt_font);
        }
    }
}
