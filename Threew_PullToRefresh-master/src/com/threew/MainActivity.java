package com.threew;

import java.util.Arrays;
import java.util.LinkedList;

import com.threew.widget.PullToRefreshBase.OnRefreshListener;
import com.threew.widget.PullToRefreshListView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
/**
 * 
 * @ClassName MainActivity.java
 * @Author wanghb
 * @Date 2013-10-30 pm 02:56:09
 * @Desc 此例主要是展示上拉、下拉和上下拉刷新，修改的代码在 PullToRefreshBase的568-591行，
 *       和在PullToRefreshListView中增加一个getRefreshType方法(用来判断上拉、下拉)。、
 *       这种方式在应用开发中用到的是最多的，所以...大家懂的..
 */
public class MainActivity extends Activity {
	
	private LinkedList<String> mListItems;
	private PullToRefreshListView mPullRefreshListView;
	private ArrayAdapter<String> mAdapter;
	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initPage();
		setClickListener();
		businessMethod();
	}
	
	private void initPage() {
		setContentView(R.layout.activity_main);
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pullrefresh);
	}
	
	private void setClickListener() {
		mPullRefreshListView.setOnRefreshListener(mOnrefreshListener);
	}
	
	private void businessMethod() {
		mListView = mPullRefreshListView.getRefreshableView();
		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
		// You can also just use setListAdapter(mAdapter)
		mListView.setAdapter(mAdapter);
	}
	
	OnRefreshListener mOnrefreshListener = new OnRefreshListener() {
		public void onRefresh() {
			new GetDataTask(mPullRefreshListView.getRefreshType()).execute();
		}
	};
	
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {
		int pullState;
		
		public GetDataTask(int pullType) {
			this.pullState = pullType;
		}
		
		@Override
		protected String[] doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			if(pullState == 1) {//name="pullDownFromTop" value="0x1" 下拉
				mListItems.addFirst("Added after refresh by first...");
			}
			if(pullState == 2) {//上拉
				mListItems.addLast("Added after refresh by last...");
			}
			
			

			super.onPostExecute(result);
		}
	}

	private String[] mStrings = { "Abbaye de Belloc" };
}
