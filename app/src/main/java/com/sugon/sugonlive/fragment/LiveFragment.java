package com.sugon.sugonlive.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sugon.sugonlive.R;
import com.sugon.sugonlive.activity.BaseApplication;
import com.sugon.sugonlive.activity.LivePlayerActivity;
import com.sugon.sugonlive.adapter.recyclerview.GridAdapter;
import com.sugon.sugonlive.net.BaseRes;
import com.sugon.sugonlive.net.NetClient;
import com.sugon.sugonlive.net.model.LiveStreamBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pjc on 2017/8/2.
 */

public class LiveFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<LiveStreamBean> mDatas = new ArrayList<>();
    private GridAdapter mGridAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View liveLayout = inflater.inflate(R.layout.live_fragment_layout, container, false);
        mRecyclerView = (RecyclerView) liveLayout.findViewById(R.id.grid_recycler);

        initData();

        mSwipeRefreshLayout = (SwipeRefreshLayout) liveLayout.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新，设置当前为刷新状态
                mSwipeRefreshLayout.setRefreshing(true);

                Call<BaseRes<LiveStreamBean>> callBack = NetClient.getService(getContext()).listLiveRooms();
                callBack.enqueue(new Callback<BaseRes<LiveStreamBean>>() {
                    @Override
                    public void onResponse(Call<BaseRes<LiveStreamBean>> call, Response<BaseRes<LiveStreamBean>> response) {
                        if (response.isSuccessful() && response.body().getTotal() >= 0) {
                            mDatas.clear();
                            mDatas.addAll(response.body().getDatas());
                            mGridAdapter.notifyDataSetChanged();
//                            Toast.makeText(getContext(), "刷新了数据", Toast.LENGTH_SHORT).show();
                            // 加载完数据设置为不刷新状态，将下拉进度收起来
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseRes<LiveStreamBean>> call, Throwable t) {
                        Toast.makeText(getContext(), "刷新失败", Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
        return liveLayout;
    }

    private void initData() {
        Call<BaseRes<LiveStreamBean>> callBack = NetClient.getService(getContext()).listLiveRooms();
        callBack.enqueue(new Callback<BaseRes<LiveStreamBean>>() {
            @Override
            public void onResponse(Call<BaseRes<LiveStreamBean>> call, Response<BaseRes<LiveStreamBean>> response) {
                if (response.isSuccessful() && response.body().getTotal() >= 0) {
                    mDatas = response.body().getDatas();
                    initRecyclerView();
//                    Toast.makeText(getContext(), "数据加载完成", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseRes<LiveStreamBean>> call, Throwable t) {
                Toast.makeText(getContext(), "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));
        mGridAdapter = new GridAdapter(this.getActivity(), mDatas);
        mGridAdapter.setOnItemClickListener(new GridAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(), "onClick", Toast.LENGTH_SHORT).show();
                LiveStreamBean sb = mDatas.get(position);
                Intent intent = new Intent(getActivity(), LivePlayerActivity.class);
                intent.putExtra("path", sb.getvSrcUrl());
                startActivity(intent);
                Call<BaseRes<String>> callBack = NetClient.getService(getContext()).startWatching(22, sb.getId());
                callBack.enqueue(new Callback<BaseRes<String>>() {
                    @Override
                    public void onResponse(Call<BaseRes<String>> call, Response<BaseRes<String>> response) {
                        if (response.isSuccessful() && response.body().getTotal() >= 0) {
                            String msg = response.body().getMsg();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseRes<String>> call, Throwable t) {
                        Toast.makeText(getContext(), "数据加载失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        mRecyclerView.setAdapter(mGridAdapter);

        //分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),
//                DividerItemDecoration.VERTICAL_LIST));
    }
}
