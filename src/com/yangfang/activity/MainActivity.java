package com.yangfang.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yangfang.Fragment.AllNewsFragment;
import com.yangfang.Fragment.BoringImageFragment;
import com.yangfang.Fragment.DuanziFragment;
import com.yangfang.Fragment.MoviesFragment;
import com.yangfang.adapter.ContentAdapter;
import com.yangfang.app.R;
import com.yangfang.entiy.ContentModel;
import com.yangfang.utils.NetUtils;

public class MainActivity extends BaseActivity {

	private ListView leftlistview;
	private ContentAdapter contentadapter;
	private ArrayList<ContentModel> list;
	private ImageView leftmenu;
	private DrawerLayout drawerLayout;
	private FragmentManager fragment;
	private TextView tvtitle;
	private FragmentTransaction ft;
	private ImageView titlerightshuaxin;
	private ArrayList<String> listItem;
	private long exitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化控件
		setViews();
		// 添加数据
		setDate();
		// 设置适配器
		setAdapter();
		// 监听
		setListeners();

	}

	// 监听
	private void setListeners() {
		leftmenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				drawerLayout.openDrawer(Gravity.LEFT);

			}
		});

		setFragment(1);

		leftlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				setFragment((int) id);
			}
		});

	}

	private void setFragment(int tag) {
		ft = fragment.beginTransaction();
		switch (tag) {
		case 1:
			ft.replace(R.id.content, new AllNewsFragment());
			tvtitle.setText("新闻资讯");

			break;

		case 2:
			ft.replace(R.id.content, new BoringImageFragment());
			tvtitle.setText("无聊图");
			break;

		case 3:
			ft.replace(R.id.content, new DuanziFragment());
			tvtitle.setText("段子");
			break;
		case 4:
			ft.replace(R.id.content, new MoviesFragment());
			tvtitle.setText("小电影");
			break;

		default:
			break;
		}

		ft.commit();
		drawerLayout.closeDrawer(Gravity.LEFT);
	}

	// 添加数据
	private void setDate() {
		list = new ArrayList<ContentModel>();
		list.add(new ContentModel("新闻资讯", R.drawable.ic_explore_white_24dp, 1));
		list.add(new ContentModel("无聊图", R.drawable.ic_mood_white_24dp, 2));
		list.add(new ContentModel("段子", R.drawable.ic_chat_white_24dp, 3));
		list.add(new ContentModel("小电影", R.drawable.ic_movie_white_24dp, 4));

	}

	// 配置适配器
	private void setAdapter() {
		contentadapter = new ContentAdapter(MainActivity.this, list);
		leftlistview.setAdapter(contentadapter);

	}

	// 初始化控件
	private void setViews() {
		leftlistview = (ListView) findViewById(R.id.left_listview);
		leftmenu = (ImageView) findViewById(R.id.left_menu);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
		fragment = getSupportFragmentManager();
		tvtitle = (TextView) findViewById(R.id.tv_title);
		titlerightshuaxin = (ImageView) findViewById(R.id.right_shuaxin);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
//	@Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    public void exit() {
//        if ((System.currentTimeMillis() - exitTime) > 2000) {
//            Toast.makeText(getApplicationContext(), "再按一次退出程序",
//                    Toast.LENGTH_SHORT).show();
//            exitTime = System.currentTimeMillis();
//        } else {
//            finish();
//            System.exit(0);
//        }
//    }
}
