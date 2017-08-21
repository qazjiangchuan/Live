package com.sugon.sugonlive.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sugon.sugonlive.R;
import com.sugon.sugonlive.net.model.LiveStreamBean;

import java.util.List;

/**
 * Created by pjc on 2017/8/3.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<LiveStreamBean> datas;

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {

        void onItemClick(View view, int position);

    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public GridAdapter(Context context, List<LiveStreamBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mContext).load("http://cn.bing.com/az/hprichbg/rb/BodieLighthouse_ZH-CN9415388071_1366x768.jpg").into(holder.iv);
        holder.tv_description.setText(datas.get(position).getUsername());

        holder.tv_onlineNum.setText(datas.get(position).getConnCount()+"");
        holder.tv_nickname.setText(datas.get(position).getUsername());
        holder.itemView.setTag(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_description;
        private ImageView iv;
        private TextView tv_onlineNum;
        private TextView tv_nickname;

        MyViewHolder(View view) {
            super(view);
            tv_description = (TextView) view.findViewById(R.id.tx_grid);
            iv = (ImageView) view.findViewById(R.id.img_grid);
            tv_onlineNum = (TextView) view.findViewById(R.id.tv_online_num);
            tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }
}
