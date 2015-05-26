package info.liruqi.bjhouse.fragment;

import info.liruqi.bjhouse.R;
import info.liruqi.bjhouse.customcomponent.MyDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class HomeFragment extends Fragment implements OnClickListener{
	private int lastPosition = 0;
	private ViewPager vp;
	private GridView gv;
	private final int[] srcId = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, };
	private final int[] gridId = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4};
	private final String[] iconTopic = {"报修","订餐","家政","装修"};
	private View view;
	private LinearLayout ll_vp;
	private ImageView iv_circle;
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			int index = vp.getCurrentItem();
			index++;
			index %= srcId.length;

			vp.setCurrentItem(index);
			
			sendEmptyMessageDelayed(0, 3000);
		}
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(getActivity(), R.layout.layout_basefragment, null);
		initData();
		initUI();
		return view;	
	}
	private void initData() {
		vp = (ViewPager) view.findViewById(R.id.vp);
		gv = (GridView) view.findViewById(R.id.gv);
		ImageView iv_left_titlebar = (ImageView) view
				.findViewById(R.id.iv_left_titlebar);
		iv_left_titlebar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("er", "到底点了没有~！");
				new MyDialog(getActivity()).showDialog(R.layout.erweima_dialog,-50,-100);
			}
		});
	}

	private void initUI() {
		// TODO Auto-generated method stub
		
		setViewPager();
		setGridView();
	}

	private void setViewPager() {
		// TODO Auto-generated method stub
		vp.setAdapter(new MyViewAdapter());
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				ll_vp.getChildAt(position).setEnabled(true);
				ll_vp.getChildAt(lastPosition).setEnabled(false);
				lastPosition = position;	
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		for (int i=0;i< srcId.length;i++){
			ll_vp = (LinearLayout)view.findViewById(R.id.ll_vp);
        	iv_circle = new ImageView(getActivity());
        	iv_circle.setImageResource(R.drawable.selector_circle_viewpager);
        	LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	lp.leftMargin = 15;
        	lp.bottomMargin = 5;
        	lp.rightMargin = 5;
        	//把lp设置给iv_circle
        	iv_circle.setLayoutParams(lp);
        	
        	//修改iv_circle的enable
        	if(i != 0){
        		iv_circle.setEnabled(false);
        	}
        	//ll是可见的，那么ll的子节点也会显示在界面上
        	ll_vp.addView(iv_circle);
        	handler.sendEmptyMessageDelayed(0, 2000);
		}
	}

	private void setGridView() {
		gv.setAdapter(new MyGridAdapter());
	}
	class MyViewAdapter extends PagerAdapter {

		private ImageView iv;

		@Override
		public int getCount() {
			return srcId.length;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			iv = new ImageView(getActivity());
			iv.setBackgroundResource(srcId[position]);
			container.addView(iv);
			return iv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
	public class MyGridAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return gridId.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view= View.inflate(getActivity(), R.layout.item_gridview, null);
			ImageView iv_gv = (ImageView) view.findViewById(R.id.iv_gv);
			TextView tv_gv =(TextView) view.findViewById(R.id.tv_gv);
			iv_gv.setBackgroundResource(gridId[position]);
			tv_gv.setText(iconTopic[position]);
			return view;
		}
		
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.iv_left_titlebar:
				Log.i("er", "到底点了没有~！");
				new MyDialog(getActivity()).showDialog(R.layout.erweima_dialog,-50,-100);
				break;
		
			case R.id.iv_right_titlebar:
			
				break;
			
			case R.id.iv_middle_titlebar:
			
				break;
			
		}
		
	}
}
