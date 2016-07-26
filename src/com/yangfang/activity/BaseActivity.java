package com.yangfang.activity;

import com.yangfang.utils.NetUtils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;
/**
 */
public class BaseActivity extends FragmentActivity {

	private long exitTime;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		if (!NetUtils.haveNet(this)){
			Toast.makeText(this, "数据加载失败，请检查网络", Toast.LENGTH_SHORT).show();
			return;
		}
	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
