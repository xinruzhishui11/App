package com.yangfang.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangfang.app.R;
import com.yangfang.ui.PagerTab;
import com.yangfang.utils.UIUtils;

public class AllNewsFragment extends Fragment {
	private PagerTab mPagerTab;
	private ViewPager mViewPager;
	private MyAdapter mAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news_list, null);
		
		mPagerTab = (PagerTab) view.findViewById(R.id.pager_tab);
		mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
		mAdapter = new MyAdapter(getFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mPagerTab.setViewPager(mViewPager);
		mPagerTab.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				BaseFragment fragment = FragmentFactory
						.createFragment(position);
				// ��ʼ��������
				fragment.loadData();
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
		return view;
	}
	
	/**
	 * FragmentPagerAdapter��PagerAdapter������, ���viewpager��ҳ����fragment�Ļ�,�ͼ̳д���
	 */
	class MyAdapter extends FragmentPagerAdapter {

		private String[] mTabNames;

		public MyAdapter(FragmentManager fm) {
			super(fm);
			mTabNames = UIUtils.getStringArray(R.array.tab_names);// ����ҳ���������
		}

		// ����ҳǩ����
		@Override
		public CharSequence getPageTitle(int position) {
			return mTabNames[position];
		}

		// ���ص�ǰҳ��λ�õ�fragment����
		@Override
		public Fragment getItem(int position) {
			BaseFragment fragment = FragmentFactory.createFragment(position);
			return fragment;
		}

		// fragment����
		@Override
		public int getCount() {
			return mTabNames.length;
		}
	}
}
