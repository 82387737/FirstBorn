package info.liruqi.bjhouse.fragment;

import info.liruqi.bjhouse.MainActivity;
import info.liruqi.bjhouse.R;
import info.liruqi.bjhouse.activity.OtherItemsActivity;
import info.liruqi.bjhouse.customcomponent.MyDialog;
import info.liruqi.bjhouse.fragment.EntertainmentFragment.MyGridAdapter;
import info.liruqi.bjhouse.fragment.EntertainmentFragment.MyViewAdapter;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout.LayoutParams;

public class WorkFragment extends Fragment {
	private int lastPosition = 0;
	private int textPosition = 3;
	private ViewPager vp;
	private GridView gv;
	private final int[] srcId = { R.drawable.m, R.drawable.n, R.drawable.o,
			R.drawable.p, };
	private final int[] gridId = { R.drawable.p5, R.drawable.p6, R.drawable.p7,R.drawable.p8,R.drawable.addfunc };
	private final String[] iconTopic = { "商业资讯", "日程提醒", "打卡", "视频会议","添加" };
	private final String[] textOfRain = { "附近有停车场", "附近有景点", "附近有车站",
			"附近有餐厅", "附近有好友", "附近有购物中心，附近有学校" };
	private View view;
	private LinearLayout ll_vp;
	private ImageView iv_circle;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int index = vp.getCurrentItem();
			index++;
			index %= srcId.length;

			vp.setCurrentItem(index);

			sendEmptyMessageDelayed(0, 3000);
		}
	};
	private TextView tv_arrow_middle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(getActivity(), R.layout.other_fragment, null);
		initData();
		initUI();
		return view;
	}

	private void initData() {
		TextView tv_middle_titlebar = (TextView)view.findViewById(R.id.tv_middle_titlebar);
		tv_middle_titlebar.setText("工作");
		vp = (ViewPager) view.findViewById(R.id.vp);
		gv = (GridView) view.findViewById(R.id.gv);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View args1,
					int position, long arg3) {

					Intent intent = new Intent(getActivity(),
							OtherItemsActivity.class);
					intent.putExtra("text", ((TextView) args1
							.findViewById(R.id.tv_gv)).getText());
					startActivity(intent);
				}
		});
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), "长按", 0).show();
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, new HomeFragment()).commit();
				MainActivity.srcIdSelected = gridId[position];
				MainActivity.iconTopicSelected = iconTopic[position];
				return false;
			}
		});
		ImageView iv_left_titlebar = (ImageView) view
				.findViewById(R.id.iv_left_titlebar);
		iv_left_titlebar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new MyDialog(getActivity()).showDialog(R.layout.erweima_dialog,
						-50, -100);
			}
		});
		ImageView iv_arrow_left = (ImageView) view
				.findViewById(R.id.iv_arrow_left);
		iv_arrow_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textPosition--;
				if (textPosition < 0) {
					textPosition = 3;
				}
				// TODO Auto-generated method stub
				tv_arrow_middle = (TextView) view
						.findViewById(R.id.tv_arrow_middle);
				tv_arrow_middle.setText(textOfRain[textPosition]);

			}
		});
		ImageView iv_arrow_right = (ImageView) view
				.findViewById(R.id.iv_arrow_right);
		iv_arrow_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textPosition++;
				if (textPosition > 5) {
					textPosition = 3;
				}
				// TODO Auto-generated method stub
				tv_arrow_middle = (TextView) view
						.findViewById(R.id.tv_arrow_middle);
				tv_arrow_middle.setText(textOfRain[textPosition]);

			}
		});
	}

	private void initUI() {
		ImageView iv_middle_titlebar = (ImageView) view
				.findViewById(R.id.iv_middle_titlebar);
		iv_middle_titlebar.setVisibility(View.VISIBLE);
		iv_middle_titlebar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(getActivity())
						.setTitle("功能管理")
						.setMultiChoiceItems(
								new String[] { "商业资讯", "日程提醒", "打卡", "视频会议",
										"协同办公","场地预约","餐厅预订"}, null, null)
						.setPositiveButton("确定", null)
						.setNegativeButton("取消", null).show();
			}
		});
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
		for (int i = 0; i < srcId.length; i++) {
			ll_vp = (LinearLayout) view.findViewById(R.id.ll_vp);
			iv_circle = new ImageView(getActivity());
			iv_circle.setImageResource(R.drawable.selector_circle_viewpager);
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			lp.leftMargin = 15;
			lp.bottomMargin = 5;
			lp.rightMargin = 5;
			// 把lp设置给iv_circle
			iv_circle.setLayoutParams(lp);

			// 修改iv_circle的enable
			if (i != 0) {
				iv_circle.setEnabled(false);
			}
			// ll是可见的，那么ll的子节点也会显示在界面上
			ll_vp.addView(iv_circle);
		}
		handler.sendEmptyMessageDelayed(0, 2000);
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

	public class MyGridAdapter extends BaseAdapter {

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
			View view = View.inflate(getActivity(), R.layout.item_gridview,
					null);
			ImageView iv_gv = (ImageView) view.findViewById(R.id.iv_gv);
			TextView tv_gv = (TextView) view.findViewById(R.id.tv_gv);
			iv_gv.setBackgroundResource(gridId[position]);
			tv_gv.setText(iconTopic[position]);
			return view;
		}

	}

}
