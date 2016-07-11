package org.sopt.teatime.c_activities.search.component;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.sopt.teatime.b_model.domain.ListModel;
import org.sopt.teatime.c_activities.search.view.SearchView;

import java.util.ArrayList;
import java.util.List;

/*
리스트뷰
 */

public class CategoryListAdapter extends BaseAdapter {
    private Context mContext;
    private List<ListModel> items = new ArrayList<ListModel>();
    private SearchView searchView;

    public CategoryListAdapter(Context context){
        super();
        mContext = context;
    }

    public void setItems(List<ListModel> items, SearchView view) {
        this.items = items;
        this.searchView = view;
        notifyDataSetChanged();
        //모델을 수정했다는 것을 알린다.
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CategoryListViewItem itemView;
        if(convertView == null){
            itemView = new CategoryListViewItem(mContext);
        }
        else{
            itemView = (CategoryListViewItem) convertView;
        }

        final ListModel item = items.get(position);
        itemView.setText(item.getData());
        itemView.setImage(item.getImage());
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                searchView.itemClickEvent(item.getData());
            }
        });
        return itemView;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
