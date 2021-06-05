package com.example.trending;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.PressHolder>{
    private List<ListBean> mData;
    private OnItemClickListener mOnItemClickListener;
    private static int isOpen=-1;

    public ViewAdapter(List<ListBean> list){
        this.mData=list;
    }

    @NonNull
    @Override
    public PressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.trending_item,parent,false);
        return new PressHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PressHolder holder, int position) {
        if(mData!=null) holder.setData(mData.get(position));

        if(isOpen==position){
            holder.mLinearLayout.setVisibility(View.VISIBLE);
            holder.Content.setMaxLines(5);
        }else {
            holder.mLinearLayout.setVisibility(View.GONE);
            holder.Content.setMaxLines(1);
        }


        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                    if(isOpen==position){
                        isOpen=-1;
                        notifyItemChanged(position);
                    }else{
                        int oldisOpen=isOpen;
                        isOpen=position;
                        notifyItemChanged(oldisOpen);
                        notifyItemChanged(isOpen);
                    }

                }
            });
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        //设置一个监听器，其实就是设置一个回调的接口
        this.mOnItemClickListener=listener;

    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        if(mData!=null){
            return mData.size();
        }else
        return 0;
    }
    public class PressHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView icon;
        private TextView lines;
        private TextView stars;
        private TextView language;
        private ImageView mLanguageColor;
        private String color;
        private TextView url;
        public LinearLayout mLinearLayout;
        private TextView Name;
        private TextView Content;
        private Uri uri;

        public PressHolder(@NonNull View itemView) {
            super(itemView);
            mLinearLayout=itemView.findViewById(R.id.son_layout);
            Name=itemView.findViewById(R.id.user_name);
            Content=itemView.findViewById(R.id.item_content);
            url=itemView.findViewById(R.id.url);
            language=itemView.findViewById(R.id.programLanguage);
            stars=itemView.findViewById(R.id.starNum);
            lines=itemView.findViewById(R.id.forkNum);
            mLanguageColor=itemView.findViewById(R.id.LanguageColor);
            icon=itemView.findViewById(R.id.icon);
        }

        public void setData(ListBean data){
            Name.setText(data.getName());
            Content.setText(data.getIntroduction());
            url.setText(data.getUrl());
            if(data.getLanguage()!=null)
                language.setText(data.getLanguage());
            else language.setText("--");
            stars.setText((String.valueOf(data.getStars())));
            lines.setText(String.valueOf(data.getLines()));
            color=data.getLanguageColor();
            if(color!=null){
                Drawable drawable=new ColorDrawable(Color.parseColor(color));
                mLanguageColor.setImageDrawable(drawable);
            }
            if (color==null)
                mLanguageColor.setImageDrawable(new ColorDrawable(Color.parseColor("#525252")));
            Log.d("1","3"+color);
            uri= Uri.parse(data.getUrl());
            icon.setImageURI(uri);
        }
    }
}
