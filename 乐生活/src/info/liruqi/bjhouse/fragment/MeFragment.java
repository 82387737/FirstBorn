package info.liruqi.bjhouse.fragment;

import java.util.ArrayList;
import java.util.List;

import info.liruqi.bjhouse.MainActivity;
import info.liruqi.bjhouse.R;
import info.liruqi.bjhouse.activity.OtherItemsActivity;
import info.liruqi.bjhouse.customcomponent.MyDialog;
import info.liruqi.bjhouse.fragment.FamilyFragment.MyGridAdapter;
import info.liruqi.bjhouse.fragment.FamilyFragment.MyViewAdapter;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout.LayoutParams;

public class MeFragment extends Fragment {
	private int lastPosition = 0;
	private int textPosition = 3;
	private ViewPager vp;
	public static boolean deleteOpen = false;
	private GridView gv;
	private static int[] srcId = { R.drawable.q };
	private static int[] gridId = { R.drawable.p5, R.drawable.p6,
			R.drawable.p7, R.drawable.p8, R.drawable.addfunc };
	private static String[] iconTopic = { "个人", "地址", "名片", "钱包", "添加" };
	private static String[] textOfRain = { "附近有停车场", "附近有景点", "附近有车站", "附近有餐厅",
			"附近有好友", "附近有购物中心，附近有学校" };
	private View view;
	public static List<View> gridList = new ArrayList<View>();
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
	private List<String> iconTopicList;
	private List<Integer> gridIdList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(getActivity(), R.layout.other_fragment, null);
		initData();
		initUI();
		return view;
	}

	private void initData() {
		gridIdList = new ArrayList<Integer>();
		for (int id : gridId) {
			gridIdList.add(id);
		}
		iconTopicList = new ArrayList<String>();
		for (String topic : iconTopic) {
			iconTopicList.add(topic);
		}

		TextView tv_middle_titlebar = (TextView) view
				.findViewById(R.id.tv_middle_titlebar);
		tv_middle_titlebar.setText("我");
		vp = (ViewPager) view.findViewById(R.id.vp);
		gv = (GridView) view.findViewById(R.id.gv);
		// gv.removeViewAt(index)
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View args1,
					int position, long arg3) {
				if (deleteOpen) {
					gridIdList.remove(position);
					iconTopicList.remove(position);
					setGridView();
					MainActivity.animation.setRepeatCount(0);
					MainActivity.animation = null;

				} else {
					Intent intent = new Intent(getActivity(),
							OtherItemsActivity.class);
					intent.putExtra("text", ((TextView) args1
							.findViewById(R.id.tv_gv)).getText());
					startActivity(intent);
				}
			}
		});
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {

			private ImageView iv_delete_icon;

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0,
					final View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				// new MainActivity().setButtonChecked(3);
				// ImageView iv_delete_icon = (ImageView)
				// gv.findViewById(R.id.iv_delete_icon);
				// iv_delete_icon.setVisibility(View.VISIBLE);
				MainActivity.animation = AnimationUtils.loadAnimation(
						getActivity(), R.anim.rotate);
				deleteOpen = true;
				// arg1.startAnimation(animation);
				for (View view : gridList) {
					iv_delete_icon = (ImageView) view
							.findViewById(R.id.iv_delete_icon);
					iv_delete_icon.setVisibility(View.VISIBLE);
					view.startAnimation(MainActivity.animation);
				}
				return true;
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
							new String[] { "个人信息", "地址管理", "名片管理", "电子钱包",
									"服务记录" }, null, null)
					.setPositiveButton("确定", null)
					.setNegativeButton("取消", null).show();
		}

		});
		intGridIcon();
		setViewPager();
		setGridView();
	}

	public static void intGridIcon() {
		// TODO Auto-generated method stub
		for (View view : gridList) {
			ImageView iv_delete_icon = (ImageView) view
					.findViewById(R.id.iv_delete_icon);
			iv_delete_icon.setVisibility(View.INVISIBLE);
		}
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
			return gridIdList.size();
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
			View view = View.inflate(getActivity(), R.layout.grid_item, null);
			ImageView iv_gv = (ImageView) view.findViewById(R.id.iv_gv);
			TextView tv_gv = (TextView) view.findViewById(R.id.tv_gv);
			ImageView iv_delete_icon = (ImageView) view
					.findViewById(R.id.iv_delete_icon);
			iv_gv.setBackgroundResource(gridIdList.get(position));
			tv_gv.setText(iconTopicList.get(position));
			gridList.add(view);
			return view;
		}

	}

}
