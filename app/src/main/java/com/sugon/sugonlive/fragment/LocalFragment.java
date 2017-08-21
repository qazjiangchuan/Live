package com.sugon.sugonlive.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sugon.sugonlive.R;
import com.sugon.sugonlive.activity.LivePlayerActivity;
import com.sugon.sugonlive.activity.MainActivity;
import com.sugon.sugonlive.adapter.JieVideoListViewAdapter;
import com.sugon.sugonlive.adapter.recyclerview.FileRecyclerViewAdapter;
import com.sugon.sugonlive.adapter.recyclerview.GridAdapter;
import com.sugon.sugonlive.model.Video;
import com.sugon.sugonlive.util.GetList;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by pjc on 2017/8/2.
 */

public class LocalFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final int UPDATE = 1;

    private RecyclerView fileRecyclerView;
    private FileRecyclerViewAdapter mAdapter;
    private ArrayList<Video> showListVideos;
    private SwipeRefreshLayout swipeLayout;
    public static Handler mHandler;
    private GetList getList;
    private boolean isUpdate = false;
    private SharedPreferences settings;
    private File currentFile;

    private TextView localPath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View localFragment = inflater.inflate(R.layout.local_fragment_layout, container, false);

        swipeLayout = (SwipeRefreshLayout) localFragment.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);

        localPath = (TextView) localFragment.findViewById(R.id.local_path);

        fileRecyclerView = (RecyclerView) localFragment.findViewById(R.id.list_local_frag);
        fileRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case UPDATE:
                        if (isUpdate) {
                            updateList();
                            swipeLayout.setRefreshing(false);
                        } else {
                            swipeLayout.setRefreshing(false);
                            Toast.makeText(getActivity(), "更新失败,请等待加载完毕", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (msg.obj instanceof ArrayList) {
                            showListVideos.clear();
                            showListVideos.addAll((ArrayList<Video>) msg.obj);
                            updateList();
                        }
                        break;
                    case 3:
                        if (msg.obj instanceof ArrayList) {
                            isUpdate = true;
                            showListVideos.clear();
                            showListVideos.addAll((ArrayList<Video>) msg.obj);
                        }
                }
            }
        };

        showListVideos = new ArrayList<Video>();
        getList = new GetList();
        getList.getFileList(showListVideos, Environment.getExternalStorageDirectory());
        currentFile = Environment.getExternalStorageDirectory();

        localPath.setText(currentFile.getAbsolutePath());

        return localFragment;
    }

    public void updateList() {
        mAdapter = new FileRecyclerViewAdapter(getActivity(), showListVideos);
        fileRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new FileRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Video v = showListVideos.get(position);
                File file = new File(v.getPath());
                if (file.isDirectory()) {
                    showListVideos.clear();
                    getList.getFileList(showListVideos, file);
                    currentFile = file;
                    localPath.setText(currentFile.getAbsolutePath());
                    Message msg = new Message();
                    msg.what = UPDATE;
                    mHandler.sendMessageDelayed(msg, 100);
                } else {
                    Intent intent = new Intent(getActivity(), LivePlayerActivity.class);
                    intent.putExtra("path", v.getPath());
                    startActivity(intent);
                }
            }
        });
    }

    public void setSettings(SharedPreferences set) {
        settings = set;
    }

    @Override
    public void onRefresh() {
        Message msg = new Message();
        msg.what = UPDATE;
        mHandler.sendMessageDelayed(msg, 2000);
    }

    public void onBackPressed() {
        if (currentFile.getAbsolutePath().equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
            ((MainActivity) getActivity()).exit();
        } else {
            showListVideos.clear();
            getList.getFileList(showListVideos, currentFile.getParentFile());
            currentFile = currentFile.getParentFile();
            localPath.setText(currentFile.getAbsolutePath());
            Message msg = new Message();
            msg.what = UPDATE;
            mHandler.sendMessageDelayed(msg, 500);
        }
    }
}
