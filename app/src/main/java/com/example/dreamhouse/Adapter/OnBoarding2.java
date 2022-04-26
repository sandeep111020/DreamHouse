package com.example.dreamhouse.Adapter;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.example.dreamhouse.Models.OnBoarditem;
import com.example.dreamhouse.R;

import java.util.ArrayList;





public class OnBoarding2 extends PagerAdapter {

    private Context mContext;
    ArrayList<OnBoarditem> onBoardItems=new ArrayList<>();


    public OnBoarding2(Context mContext, ArrayList<OnBoarditem> items) {
        this.mContext = mContext;
        this.onBoardItems = items;
    }

    @Override
    public int getCount() {
        return onBoardItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.onboard2, container, false);

        OnBoarditem item=onBoardItems.get(position);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_onb);
        ImageView img1 = (ImageView) itemView.findViewById(R.id.img1);
        ImageView img2 = (ImageView) itemView.findViewById(R.id.img2);
        ImageView img3 = (ImageView) itemView.findViewById(R.id.img3);

        imageView.setImageResource(item.getImageID());
        final ImagePopup imagePopup = new ImagePopup(mContext);
        imagePopup.setWindowHeight(800); // Optional
        imagePopup.setWindowWidth(800); // Optional
        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
        imagePopup.setFullScreen(true); // Optional
        imagePopup.setHideCloseIcon(true);  // Optional
        imagePopup.setImageOnClickClose(true);
        TextView tv_title=(TextView)itemView.findViewById(R.id.tv_header);
        tv_title.setText(item.getTitle());
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePopup.initiatePopup(img1.getDrawable());
                imagePopup.viewPopup();
            }
        });

        TextView tv_content=(TextView)itemView.findViewById(R.id.tv_desc);
        tv_content.setText(item.getDescription());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}
