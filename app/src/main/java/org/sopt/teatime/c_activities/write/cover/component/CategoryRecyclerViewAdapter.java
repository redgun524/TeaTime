package org.sopt.teatime.c_activities.write.cover.component;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.sopt.teatime.R;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.b_model.domain.PhotoBook;
import org.sopt.teatime.c_activities.write.cover.model.CategoryRecyclerItem;
import org.sopt.teatime.c_activities.write.register.view.RegisterView;

import java.util.ArrayList;

/**
 * Created by Hyeonu on 2016-03-22.
 */
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CategoryRecyclerItem> items;
    private RegisterView registerView;
    private PhotoBook photoBook;
    private Cover cover;
    private int clickCount;

    public CategoryRecyclerViewAdapter(Context applicationContext, ArrayList<CategoryRecyclerItem> items, RegisterView registerView) {
        this.context = applicationContext;
        this.items = items;
        this.registerView = registerView;
        photoBook = new PhotoBook();
        cover = new Cover();
        photoBook.cover = cover;
        clickCount = 0;
    }

    // inflate registerView and set to ViewHolder
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight() / 3);
        v.setLayoutParams(layoutParams);

        return new ViewHolder(v);
    }

    //this method is called when user scroll and set items of RecyclerView
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.button.setText(items.get(position).getCategory());
        holder.button.setBackgroundColor(Color.rgb(Integer.parseInt("E4", 16), Integer.parseInt("E4", 16), Integer.parseInt("E3", 16)));
        holder.button.setTextColor(Color.rgb(Integer.parseInt("8C", 16), Integer.parseInt("8C", 16), Integer.parseInt("8C", 16)));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(items.get(position).isClick) {
                    if(clickCount > 0) {
                        holder.button.setBackgroundColor(Color.rgb(Integer.parseInt("E4", 16), Integer.parseInt("E4", 16), Integer.parseInt("E3", 16)));
                        holder.button.setTextColor(Color.rgb(Integer.parseInt("8C", 16), Integer.parseInt("8C", 16), Integer.parseInt("8C", 16)));
                        items.get(position).isClick = false;
                        clickCount--;
                        registerView.cancelCategory(items.get(position).getCategory());
                    }
                }
                else {
                    if(clickCount < 3) {
                        holder.button.setBackgroundColor(Color.rgb(Integer.parseInt("66", 16), Integer.parseInt("AE", 16), Integer.parseInt("CF", 16)));
                        holder.button.setTextColor(Color.rgb(Integer.parseInt("FD", 16), Integer.parseInt("FD", 16), Integer.parseInt("FE", 16)));
                        items.get(position).isClick = true;
                        clickCount++;
                        registerView.setCategory(items.get(position).getCategory());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public Button button;

        public ViewHolder(View view) {
            super(view);
            button = (Button) view.findViewById(R.id.btn_category);
        }
    }
}
