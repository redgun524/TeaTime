package org.sopt.teatime.c_activities.write.cover.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.R;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.c_activities.write.content.model.ContentTemplateIds;
import org.sopt.teatime.c_activities.write.register.view.RegisterView;

import java.util.List;

/**
 * Created by Hyeonu on 2016-03-22.
 */
public class ContentsRecyclerViewAdapter extends RecyclerView.Adapter<ContentsRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Contents> items;
    private RegisterView registerView;

    public ContentsRecyclerViewAdapter(Context applicationContext, List<Contents> items, RegisterView view) {
        this.context = applicationContext;
        this.items = items;
        registerView = view;
    }

    public void setSource(List<Contents> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    public void appendContent(Contents contents) {
        items.add(contents.page - 1, contents);
        notifyDataSetChanged();
    }

    public void replaceContent(Contents contents, int position) {
        items.set(position, contents);
        notifyDataSetChanged();
    }

    // inflate view and set to ViewHolder
    @Override
    public ContentsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template, null);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(270, ViewGroup.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(layoutParams);
        return new ViewHolder(v);
    }

    //this method is called when user scroll and set items of RecyclerView
    @Override
    public void onBindViewHolder(final ContentsRecyclerViewAdapter.ViewHolder holder, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final int pageNum = position + 1; // 콘텐츠 페이지
        int lastPage = items.size(); //아직 추가될 마지막 페이지(이미 1개의 콘텐츠가 있으면 lastPage = 2)
        Log.i("MyTag", "lastPage : " + lastPage);
        if (pageNum == lastPage) {
            View v = inflater.inflate(R.layout.item_image_only, holder.view);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registerView.setContentTemplate(pageNum, items.get(position));
                }
            });
        } else {
            final Contents content = items.get(position);
            Log.i("MyTag", "템플릿 코드 : " + content.template_code);
            final ContentTemplateIds ids = new ContentTemplateIds(context, content.template_code);
            holder.view.removeAllViews();
            Log.i("MyTag", "" + ids.layoutId);
            View v = inflater.inflate(ids.layoutId, holder.view);
            ImageView imgPic = (ImageView)v.findViewById(ids.imgPicId);
            TextView txtDesc = (TextView)v.findViewById(ids.txtDescId);
            Glide.with(context)
                    .load(content.url)
                    .into(imgPic);
            txtDesc.setText(content.description);
            Float ratio = Float.parseFloat("0.2");
            Float descFontSize = txtDesc.getTextSize() * ratio;
            txtDesc.setTextSize(TypedValue.COMPLEX_UNIT_PX, descFontSize);
            content.page = pageNum;
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registerView.setContentTemplate(pageNum, content);
                }
            });
        }

//        Glide.with(context).load(items.get(position).url).into(holder.picture);
//        holder.picture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(items.get(position).url.contains("plus2")) {
//                    Intent intent = new Intent(context, DrawActivity.class);
//                    intent.putExtra("isCover", "false");
//                    ((Activity) context).startActivityForResult(intent, 2);
//                }
//                else {
//                    photoBook.contents_list.add(items.get(0));
//                    listener.onItemClick(photoBook);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public FrameLayout view;

        public ViewHolder(View view) {
            super(view);
            this.view = (FrameLayout)view.findViewById(R.id.frameLayout_template);
        }
    }
}
