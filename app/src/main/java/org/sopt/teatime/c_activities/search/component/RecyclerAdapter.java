package org.sopt.teatime.c_activities.search.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.R;
import org.sopt.teatime.a_others.network.ApplicationController;
import org.sopt.teatime.a_others.function.FontController;
import org.sopt.teatime.b_model.domain.Cover;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<Cover> items;
    LayoutInflater inflater;

    Typeface typeface_ng,typeface_jua;

    public RecyclerAdapter(Context context, ArrayList<Cover> items) {
        this.context=context;
        this.items=items;

        typeface_ng = Typeface.createFromAsset(context.getAssets(), FontController.PATH_NANUM_GOTHIC);
        typeface_jua = Typeface.createFromAsset(context.getAssets(), FontController.PATH_JUA);
    }

    public RecyclerAdapter(Context  ctx) {
        inflater = LayoutInflater.from(ctx);

        typeface_ng = Typeface.createFromAsset(ctx.getAssets(), FontController.PATH_NANUM_GOTHIC);
        typeface_jua = Typeface.createFromAsset(ctx.getAssets(), FontController.PATH_JUA);
    }

    public void setCoverList(ArrayList<Cover> coverList) {
        this.items = coverList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_mybook,null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cover item=items.get(position);
        holder.txtNick.setText(item.getAuthor_nick());
        holder.txtTitle.setText(item.getTitle());
        holder.imgTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyTag", "쓰레기통 버튼을 눌렸다고!!!!!!");
            }
        });
        holder.txtKey.setText(item.getKey1() + item.getKey2() + item.getKey3());
        holder.txtLike_count.setText("" + item.getLike_count());
        holder.txtComment_count.setText("" + item.getComment_count());
        holder.txtScrap_count.setText("" + item.getScrap_count());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyTag", "상세 보여줘!!!!!!");
            }
        });

        Glide.with(context)
                .load(ApplicationController.getS3Url() + item.url)
                .into(holder.image);

        Glide.with(context)
                .load(ApplicationController.getS3Url()+item.profile_url)
                .placeholder(R.drawable.ic_profile_incard)
                .into(holder.imgProfile);

        holder.txtNick.setTypeface(typeface_ng);
        holder.txtTitle.setTypeface(typeface_ng);
        holder.txtKey.setTypeface(typeface_ng);
        holder.txtLike_count.setTypeface(typeface_ng);
        holder.txtComment_count.setTypeface(typeface_ng);
        holder.txtScrap_count.setTypeface(typeface_ng);
    }
    @Override
    public int getItemCount() {
        return items == null ? 0 : this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardview)
        CardView cardview;
        @BindView(R.id.imgProfile_Mybook)
        ImageView imgProfile;
        @BindView(R.id.txtId_Mybook)
        TextView txtNick;
        @BindView(R.id.imgTrash_Mybook)
        ImageView imgTrash;
        @BindView(R.id.imgthumbnail_Mybook)
        ImageView image;
        @BindView(R.id.title_Scrapbook)
        TextView txtTitle;
        @BindView(R.id.key_Mybook)
        TextView txtKey;
        @BindView(R.id.Like_count)
        TextView txtLike_count;
        @BindView(R.id.Comment_count)
        TextView txtComment_count;
        @BindView(R.id.Scrap_count)
        TextView txtScrap_count;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}