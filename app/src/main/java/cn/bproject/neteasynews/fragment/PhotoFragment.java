package cn.bproject.neteasynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.channelmanager.ProjectChannelBean;

import java.util.ArrayList;
import java.util.List;

import cn.bproject.neteasynews.R;
import cn.bproject.neteasynews.Utils.CategoryDataUtils;
import cn.bproject.neteasynews.Utils.LogUtils;
import cn.bproject.neteasynews.adapter.FixedPagerAdapter;
import cn.bproject.neteasynews.fragment.photo.PicListFragment;

import static cn.bproject.neteasynews.R.id.tab_layout;

/**
 * Created by Administrator on 2016/12/24.
 * 图片模块
 */

public class PhotoFragment extends Fragment{

    private final String TAG = PhotoFragment.class.getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mNewsViewpager;
    private View mView;
    private FixedPagerAdapter fixedPagerAdapter;
    private List<BaseFragment> fragments;
    private static List<ProjectChannelBean> channelBeanList;
    private BaseFragment mFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tablayout_pager, container, false);
        Toolbar myToolbar = (Toolbar) mView.findViewById(R.id.my_toolbar);
        myToolbar.setTitle("图片中心");
        mTabLayout = (TabLayout) mView.findViewById(tab_layout);
        mNewsViewpager = (ViewPager) mView.findViewById(R.id.news_viewpager);
        channelBeanList = CategoryDataUtils.getPicCategoryBeans();

        initValidata();
        return mView;
    }

    public void initValidata() {
        fixedPagerAdapter = new FixedPagerAdapter(getChildFragmentManager());
        fixedPagerAdapter.setChannelBean(channelBeanList);
        fragments = new ArrayList<BaseFragment>();
        for (int i = 0; i < channelBeanList.size(); i++) {
            // "推荐","","0031"
            // "明星","","0003"使用瀑布流
            ProjectChannelBean channelBean = channelBeanList.get(i);
            BaseFragment  fragment = PicListFragment.newInstance(channelBean.getTid(),channelBean.getColumn());
            fragments.add(fragment);
        }

        fixedPagerAdapter.setFragments(fragments);

        mNewsViewpager.setAdapter(fixedPagerAdapter);
        mTabLayout.setupWithViewPager(mNewsViewpager);
        // mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE); //适合很多tab
        mTabLayout.setTabMode(TabLayout.MODE_FIXED); // tablayout均分，适合少Tablayout
        mNewsViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.d(TAG, "onPageSelected: mNewsViewpager页面监听执行了" + position);
                BaseFragment fragment = fragments.get(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

}
