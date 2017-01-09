package cn.bproject.neteasynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.DensityUtils;
import cn.bproject.neteasynews.Utils.ThreadManager;
import cn.bproject.neteasynews.Utils.UIUtils;
import cn.bproject.neteasynews.activity.VideoDetailActivity;
import cn.bproject.neteasynews.adapter.VideoListAdapter;
import cn.bproject.neteasynews.bean.VideoBean;
import cn.bproject.neteasynews.common.Api;
import cn.bproject.neteasynews.common.DefineView;
import cn.bproject.neteasynews.http.DataParse;
import cn.bproject.neteasynews.http.HttpCallbackListener;
import cn.bproject.neteasynews.http.HttpHelper;
import cn.bproject.neteasynews.widget.ClassicRefreshHeaderView;
import cn.bproject.neteasynews.widget.LoadMoreFooterView;
import cn.bproject.neteasynews.widget.NormalTitleBar;

import static cn.bproject.neteasynews.R.id.iRecyclerView;

/**
 * Created by Administrator on 2016/12/24.
 * 视频模块
 */

public class VideoFragment extends Fragment implements DefineView {
    private final String TAG = VideoFragment.class.getSimpleName();

    private View mView;
    //    private PullToRefreshListView mListView;
    private ArrayList<VideoBean> mVideoBeanList;
    //    private VideoListAdapter mVideoListAdapter;
    private IRecyclerView mIRecyclerView;
    private ThreadManager.ThreadPool mThreadPool;   // 线程池
    private int mStartIndex = 0;
    private boolean isPullRefresh;
    private List<VideoBean> newlist;   // 上拉刷新后获得的数据
    private final String VID = "VID";
    private LoadMoreFooterView mLoadMoreFooterView;
    private VideoListAdapter mVideoListAdapter;
    private NormalTitleBar mNormalTitleBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_video, null);
        initView();
        initValidata();
        initListener();
        return mView;
    }

    @Override
    public void initView() {
        mNormalTitleBar = (NormalTitleBar) mView.findViewById(R.id.ntb);
        mNormalTitleBar.setTvLeftVisiable(false);
        mNormalTitleBar.setTitleText("推荐视频");
        mIRecyclerView = (IRecyclerView) mView.findViewById(iRecyclerView);
        mIRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mIRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mLoadMoreFooterView = (LoadMoreFooterView) mIRecyclerView.getLoadMoreFooterView();
        ClassicRefreshHeaderView classicRefreshHeaderView = new ClassicRefreshHeaderView(getActivity());
        classicRefreshHeaderView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(getActivity(), 80)));
        // we can set view
        mIRecyclerView.setRefreshHeaderView(classicRefreshHeaderView);

//        mIRecyclerView.post(new Runnable() {
//            @Override
//            public void run() {
//                mIRecyclerView.setRefreshing(true);
//            }
//        });

    }


    @Override
    public void initValidata() {
        // 创建线程池
        mThreadPool = UIUtils.getThreadPool();
        requestData();
    }

    public void requestData() {
//        mUrl = Api.CommonUrl + Api.toutiaoId + "/" + mStartIndex + Api.endUrl;
//        Log.d(TAG, "mUrl地址为: " + mUrl);
//        http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
//        http://c.m.163.com/nc/article/list/T1348647909107/0-20.html

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + mStartIndex + Api.devId;
                HttpHelper.get(url, new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String result) {
                        mVideoBeanList = DataParse.VideoList(result);
                        UIUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mVideoBeanList != null) {
                                    bindData();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(String result, Exception e) {
                        e.printStackTrace();
                    }
                });

            }
        });

    }

    @Override
    public void initListener() {
//        mIRecyclerView.setLoadMoreEnabled(true);
//        mIRecyclerView.setRefreshEnabled(true);
        mIRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + 0 + Api.devId;
                        HttpHelper.get(url, new HttpCallbackListener() {
                            @Override
                            public void onSuccess(String result) {
                                Log.d(TAG, "下拉刷新onSuccess: " + result);
                                isPullRefresh = true;
                                newlist = DataParse.VideoList(result);
//                                UIUtils.runOnUIThread(new Runnable() {
//                                    @Override
//                                    public void run() {
////                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
////                                        isPullRefreshView();
//                                        // 是下拉刷新
//                                        Log.d(TAG, "isPullRefreshView: " +  mVideoBeanList.toString());
//                                        newlist.addAll(mVideoBeanList);
//                                        mVideoBeanList.removeAll(mVideoBeanList);
//                                        mVideoBeanList.addAll(newlist);
//                                        mVideoListAdapter.notifyDataSetChanged();
//                                        Toast.makeText(getActivity(), "数据已更新", Toast.LENGTH_SHORT).show();
//                                        // 收起刷新视图
//                                        mIRecyclerView.setRefreshing(false);
//
//                                    }
//                                });
                                DataChange();
                            }

                            @Override
                            public void onError(String result, Exception e) {
                                e.printStackTrace();
                                mIRecyclerView.setRefreshing(false);
                                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }
        });
        mIRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mLoadMoreFooterView.canLoadMore() && mVideoListAdapter.getItemCount() > 0) {
                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);

                    mStartIndex += 20;
                    mThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + mStartIndex + Api.devId;
                            HttpHelper.get(url, new HttpCallbackListener() {
                                @Override
                                public void onSuccess(String result) {

                                    isPullRefresh = false;
                                    newlist = DataParse.VideoList(result);
                                    DataChange();
                                }

                                @Override
                                public void onError(String result, Exception e) {
                                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.ERROR);
                                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            });

                        }
                    });
                }
            }
        });


//        mListView.setMode(PullToRefreshBase.Mode.BOTH);
//        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//                LogUtils.d(TAG, "onPullDownToRefresh: 下拉刷新了");
//
//                mThreadPool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + 0 + Api.devId;
//                        HttpHelper.get(url, new HttpCallbackListener() {
//                            @Override
//                            public void onSuccess(String result) {
//                                newlist = DataParse.VideoList(result);
//                                isPullRefresh = true;
//                                DataChange();
//                            }
//
//                            @Override
//                            public void onError(String result, Exception e) {
//
//                            }
//                        });
//                    }
//                });
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                LogUtils.d(TAG, "onPullUpToRefresh: 上拉刷新了");
//                mStartIndex += 20;
//
//                LogUtils.d(TAG, "mStartIndex: " + mStartIndex);
////                mUrl = Api.CommonUrl + Api.yaowenspecialId + "/" + mStartIndex + Api.endUrl;
//
//                mThreadPool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        String url = Api.host + Api.SpecialColumn2 + "T1457068979049" + Api.SpecialendUrl + mStartIndex + Api.devId;
//                        HttpHelper.get(url, new HttpCallbackListener() {
//                            @Override
//                            public void onSuccess(String result) {
//                                newlist = DataParse.VideoList(result);
//                                isPullRefresh = false;
//                                DataChange();
//                            }
//
//                            @Override
//                            public void onError(String result, Exception e) {
//
//                            }
//                        });
//
//                    }
//                });
//            }
//        });

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
//                intent.putExtra(VID, mVideoBeanList.get((int) l).getVid());
////                intent.putExtra("VIDEO", mVideoBeanList.get(i));
//                getActivity().startActivity(intent);
//            }
//        });


    }


    @Override
    public void bindData() {
        mVideoListAdapter = new VideoListAdapter(getActivity(), mVideoBeanList);
        mIRecyclerView.setIAdapter(mVideoListAdapter);
        mVideoListAdapter.setOnItemClickListener(new VideoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o, View v) {
                Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
                intent.putExtra(VID, mVideoBeanList.get(position).getVid());
//                intent.putExtra("VIDEO", mVideoBeanList.get(i));
                getActivity().startActivity(intent);
            }
        });

    }

    /**
     * 上拉或下拉刷新之后更新UI界面
     */
    private void DataChange() {
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
//                    mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
                isPullRefreshView();
                Toast.makeText(getActivity(), "数据已更新", Toast.LENGTH_SHORT).show();
                // 收起刷新视图
                mIRecyclerView.setRefreshing(false);

            }
        });
    }

    /**
     * 判断是上拉刷新还是下拉刷新，执行相应的方法
     */
    public void isPullRefreshView() {
        if (isPullRefresh) {
            // 是下拉刷新
            Log.d(TAG, "isPullRefreshView: " +  mVideoBeanList.toString());
            newlist.addAll(mVideoBeanList);
            mVideoBeanList.removeAll(mVideoBeanList);
            mVideoBeanList.clear();
            mVideoBeanList.addAll(newlist);
        } else {
            if (newlist == null) {
                mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
            } else {
                // 上拉刷新
                mVideoBeanList.addAll(newlist);
                mLoadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
            }
        }
        mVideoListAdapter.notifyDataSetChanged();
    }
}
