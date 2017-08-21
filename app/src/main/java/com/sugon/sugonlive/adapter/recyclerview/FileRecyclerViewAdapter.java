package com.sugon.sugonlive.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sugon.sugonlive.R;
import com.sugon.sugonlive.model.Video;
import com.sugon.sugonlive.util.MyVideoThumbLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pjc on 2017/8/15.
 * local file adapter
 */
public class FileRecyclerViewAdapter extends RecyclerView.Adapter<FileRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<Video> listVideos = new ArrayList<Video>();
    private MyVideoThumbLoader mVideoThumbLoader;

    //自定义监听事件
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public FileRecyclerViewAdapter(Context context, List<Video> listVideos) {
        mContext = context;
        this.listVideos.addAll(listVideos);
        mVideoThumbLoader = new MyVideoThumbLoader();// 初始化缩略图载入方法
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_local, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String path = listVideos.get(position).getPath();

        File file = new File(path);
        if (file.isDirectory()) {
            holder.img.setImageResource(R.drawable.file);
        } else {
            holder.img.setTag(path);
            mVideoThumbLoader.showThumbByAsyncTask(path, holder.img);
        }
        holder.title.setText(listVideos.get(position).getTitle());
        holder.path.setText(listVideos.get(position).getPath());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listVideos.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView path;

        ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_list_local);
            title = (TextView) itemView.findViewById(R.id.tittle_list_local);
            path = (TextView) itemView.findViewById(R.id.content_list_local);
        }
    }


}
