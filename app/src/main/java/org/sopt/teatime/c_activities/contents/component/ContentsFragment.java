package org.sopt.teatime.c_activities.contents.component;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sopt.teatime.R;


/**
 * Created by JH on 2016-07-02.
 */
public class ContentsFragment extends Fragment {

    TextView textView;
    ImageView imageView;

    static ContentsFragment init(String title, String url) {               //adapter에서 전달한 값을 다시 onCreateView에 전달 예정
        ContentsFragment frag = new ContentsFragment();
        Bundle argsContents = new Bundle();
        //args.putInt("val", val);
        argsContents.putString("title", title);
        Log.i("MyTag", "Contents 전달 전 값은" + title);
        argsContents.putString("url", url);
        frag.setArguments(argsContents);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //실제로 View를 생성하는 부분
        //ListView의 getView 느낌
        // The last two arguments ensure LayoutParams are inflated
        // properly.

        View rootView = inflater.inflate(R.layout.contents_fragment, container, false);                        //fragment를 inflate
//        Bundle arg2 = new Bundle();
//        String test = getArguments().getString("adapterName");
        //Log.i("MyTag","받은 adapterName 값은"+test);
        //Log.i("MyTag","받은 title 값은"+arg2.getString("title"));

        Log.i("MyTag", "Contents 받은 url 값은" + getArguments().getString("url"));
        Log.i("MyTag", "Contents 받은 title 값은" + getArguments().getString("title"));

        textView = (TextView) rootView.findViewById(R.id.txtDescription_Contents);                              //textView를 받아온 값으로 설정함.
        textView.setText(getArguments().getString("title"));

        imageView = (ImageView)rootView.findViewById(R.id.imgContent_Contents);
        Glide.with(this).load("http://s3.ap-northeast-2.amazonaws.com/noldam/sitter/profile/rr@rr.com.sitter_profile.jpg").into(imageView);


        Log.i("MyTag", "Contents 설정된 값은" + textView.getText().toString());
        return rootView;
    }
}
