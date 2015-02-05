package com.example.smooth;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private HorizontalScrollView mHorizontalScrollView ;
	private LinearLayout mLinearLayout;
	private ViewPager pager;
	private ImageView mImageView;
	private int mScreenWidth;
	private int item_width;
	
	private int endPosition;
	private int beginPosition;
	private int currentFragmentIndex;
	private boolean isEnd;
	
	private ArrayList<Fragment> fragments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv_view);
		mLinearLayout = (LinearLayout) findViewById(R.id.hsv_content);
		mImageView = (ImageView) findViewById(R.id.img1);
		item_width = (int) ((mScreenWidth / 4.0 + 0.5f));
		mImageView.getLayoutParams().width = item_width;
		
		pager = (ViewPager) findViewById(R.id.pager);
		initNav();
		initViewPager();
	}

	private void initViewPager() {
		fragments = new ArrayList<Fragment>();
    	for (int i = 0; i < 10; i++) {
    		Bundle data = new Bundle();
    		data.putString("text", (i+1)+"");
    		BaseFragment fragment = new BaseFragment();
    		fragment.setArguments(data);
    		fragments.add(fragment);
		}
    	MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
		pager.setAdapter(fragmentPagerAdapter);
		fragmentPagerAdapter.setFragments(fragments);
		pager.setOnPageChangeListener(new MyOnPageChangeListener());
		currentFragmentIndex = 0;
		isClick = true;
		selectPager(8);
		pager.setCurrentItem(8);
		
	}
	
	private void initNav() {
		for (int i = 0 ; i < 10 ; i++) {
			RelativeLayout layout = new RelativeLayout(this);
			TextView view = new TextView(this);
			view.setText("测试"+(i+1)+"标题");
			view.setTextColor(Color.BLACK);
			RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			layout.addView(view, params);
			mLinearLayout.addView(layout, (int)(mScreenWidth/4 + 0.5f), 50);
			layout.setOnClickListener(this);
			layout.setTag(i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> fragments; 
		private FragmentManager fm;

	    public MyFragmentPagerAdapter(FragmentManager fm) {
	        super(fm);
	    	this.fm = fm;
	    }

	    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
	        super(fm);
			this.fm = fm;
	        this.fragments = fragments;
	    }

	    @Override
	    public int getCount() {
	        return fragments.size();
	    }

	    @Override
	    public Fragment getItem(int position) {
	        return fragments.get(position);
	    }

	    @Override
	    public int getItemPosition(Object object) {
	        return POSITION_NONE;
	    }
	    
	    
		public void setFragments(ArrayList<Fragment> fragments) {
			if(this.fragments != null){
				FragmentTransaction ft = fm.beginTransaction();
				for(Fragment f:this.fragments){
					ft.remove(f);
				}
				ft.commit();
				ft=null;
				fm.executePendingTransactions();
			}
			this.fragments = fragments;
			notifyDataSetChanged();
		}
		
		
		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
		    Object obj = super.instantiateItem(container, position);
		    return obj;
		}
			
	}
	
	private void selectPager(int position){
		Animation animation = null;
		
		if(isClick)		
			animation = new TranslateAnimation(currentFragmentIndex * item_width, position* item_width, 0, 0);
		else
			animation = new TranslateAnimation(endPosition, position* item_width, 0, 0);
		
		beginPosition = position * item_width;
		
		isClick = false;
		
		if (animation != null) {
			animation.setFillAfter(true);
			animation.setDuration(100);
			mImageView.startAnimation(animation);
		
			if(currentFragmentIndex > position){
				mHorizontalScrollView.smoothScrollTo((position - 1) * item_width , 0);
			}else{
				mHorizontalScrollView.smoothScrollTo((position - 2) * item_width , 0);
			}
		}
		
		currentFragmentIndex = position;
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(final int position) {
			selectPager(position);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			if(!isEnd){
				if(currentFragmentIndex == position){
					endPosition = item_width * currentFragmentIndex + 
							(int)(item_width * positionOffset);
				}
				if(currentFragmentIndex == position+1){
					endPosition = item_width * currentFragmentIndex - 
							(int)(item_width * (1-positionOffset));
				}
				
				Animation mAnimation = new TranslateAnimation(beginPosition, endPosition, 0, 0);
				mAnimation.setFillAfter(true);
				mAnimation.setDuration(0);
				mImageView.startAnimation(mAnimation);
				mHorizontalScrollView.invalidate();
				beginPosition = endPosition;
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_DRAGGING) {
				isEnd = false;
			} else if (state == ViewPager.SCROLL_STATE_SETTLING) {
				isEnd = true;
				beginPosition = currentFragmentIndex * item_width;
				if (pager.getCurrentItem() == currentFragmentIndex) {
					mImageView.clearAnimation();
					Animation animation = null;

					animation = new TranslateAnimation(endPosition, currentFragmentIndex * item_width, 0, 0);
					animation.setFillAfter(true);
					animation.setDuration(1);
					mImageView.startAnimation(animation);
					mHorizontalScrollView.invalidate();
					endPosition = currentFragmentIndex * item_width;
				}
			}
		}

	}
	
	boolean isClick = false;
	@Override
	public void onClick(View v) {
		isClick = true;
		pager.setCurrentItem((Integer)v.getTag());
	}
}
