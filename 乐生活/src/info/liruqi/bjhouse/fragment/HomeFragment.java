package info.liruqi.bjhouse.fragment;

import info.liruqi.bjhouse.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	private ViewPager vp;
	private GridView gv;
	private final int[] srcId = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, };
	private final int[] gridId = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4};
	private final String[] iconTopic = {"报修","订餐","家政","装修"};
	private View view;
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
	}

	private void initUI() {
		// TODO Auto-generated method stub
		setViewPager();
		setGridView();
	}

	private void setViewPager() {
		// TODO Auto-generated method stub
		vp.setAdapter(new MyViewAdapter());
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
}
