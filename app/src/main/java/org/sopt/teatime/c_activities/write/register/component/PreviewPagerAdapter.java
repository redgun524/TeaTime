package org.sopt.teatime.c_activities.write.register.component;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.R;
import org.sopt.teatime.b_model.domain.Contents;
import org.sopt.teatime.b_model.domain.Cover;
import org.sopt.teatime.c_activities.write.content.model.ContentTemplateIds;
import org.sopt.teatime.c_activities.write.cover.model.CoverTemplateId;

import java.util.ArrayList;

public class PreviewPagerAdapter extends PagerAdapter {

    private Cover cover;
    private ArrayList<Contents> contentsList;
    private LayoutInflater inflater;
    private Context ctx;

    public PreviewPagerAdapter(Context ctx) {
        this.ctx = ctx;
        contentsList = new ArrayList<>();
        inflater = LayoutInflater.from(ctx);
    }

    public void setCover(Cover cover) {
        this.cover = cover;
        notifyDataSetChanged();
    }

    public void appendContents(Contents contents) {
        contentsList.add(contents);
        notifyDataSetChanged();
    }

    public void setContentsList(ArrayList<Contents> contentsList) {
        this.contentsList = contentsList;
        notifyDataSetChanged();
    }

    public void replaceContent(Contents contents, int page) {
        contentsList.set(page, contents);
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_template, container, false);
        Log.i("MyTag", ""+position);
        if (cover != null) {
            if (position == 0) {
                Log.i("MyTag", "여긴가");
                CoverTemplateId ids = new CoverTemplateId(ctx, cover.template_code);
                FrameLayout coverLayout = (FrameLayout)view.findViewById(R.id.frameLayout_template);
                View coverView = inflater.inflate(ids.layoutId, coverLayout);
                ImageView imgPic = (ImageView)coverView.findViewById(ids.imgPicId);
                Glide.with((ctx))
                        .load(cover.url)
                        .into(imgPic);
                return coverView;
            } else {
                Log.i("MyTag", "우아아아아"+position);
                Contents contents = contentsList.get(position - 1);
                ContentTemplateIds ids = new ContentTemplateIds(ctx, contents.template_code);
                FrameLayout coverLayout = (FrameLayout)view.findViewById(R.id.frameLayout_template);
                View containerView = inflater.inflate(ids.layoutId, coverLayout);
                return containerView;
            }
        } else {
            Log.i("MyTag", "저긴가"+position);
            Contents contents = contentsList.get(position);
            ContentTemplateIds ids = new ContentTemplateIds(ctx, contents.template_code);
            FrameLayout coverLayout = (FrameLayout)view.findViewById(R.id.frameLayout_template);
            View containerView = inflater.inflate(ids.layoutId, coverLayout);
            return containerView;
        }
    }

    @Override
    public int getCount() {
        if (cover == null) {
            return contentsList == null ? 0 : contentsList.size();
        } else {
            return contentsList == null ? 1 : contentsList.size() + 1;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

}