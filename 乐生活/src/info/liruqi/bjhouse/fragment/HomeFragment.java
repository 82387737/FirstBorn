package info.liruqi.bjhouse.fragment;

import java.util.ArrayList;
import java.util.List;

import info.liruqi.bjhouse.EntryModel;
import info.liruqi.bjhouse.MainActivity;
import info.liruqi.bjhouse.R;
import info.liruqi.bjhouse.ToastUtil;
import info.liruqi.bjhouse.activity.ItemActivity;
import info.liruqi.bjhouse.activity.OtherItemsActivity;
import info.liruqi.bjhouse.customcomponent.MyDialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
	private int lastPosition = 0;
	private int textPosition = 3;
	private ViewPager vp;
	private GridView mGridView;
	MyGridAdapter gridAdapter;
	private boolean mEditing = false;
	private static int[] srcId = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, };
	private static String[] textOfRain = { "很久以前下过雨", "前些天下过一场雨", "今天下雨了",
			"明天要下雨", "未来几天皆是有雨的", "连绵之雨，不知道要下多久" };
	private View view;
	private LinearLayout ll_vp;
	private ImageView iv_circle;
	List<EntryModel> mEntrys;

	public HomeFragment() {
		super();
		final int[] gridId = { R.drawable.p1, R.drawable.p2, R.drawable.p3,
				R.drawable.p4 };
		final String[] iconTopic = { "WIFI", "周边", "管理", "停车" };

		mEntrys = new ArrayList<EntryModel>();
		for (int i = 0; i < iconTopic.length; i++) {
			EntryModel e = new EntryModel(iconTopic[i], gridId[i]);
			mEntrys.add(e);
		}
	}

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
		view = View.inflate(getActivity(), R.layout.layout_basefragment, null);
		initData();
		initUI();
		return view;
	}

	public void onHiddenChanged(boolean hidden) {
		if (hidden)
			return;
		gridAdapter.notifyDataSetChanged();
	}

	private void initData() {
		vp = (ViewPager) view.findViewById(R.id.vp);
		mGridView = (GridView) view.findViewById(R.id.gv);

		gridAdapter = new MyGridAdapter();
		mGridView.setAdapter(gridAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View args1,
					int position, long arg3) {
				if (mEditing) {
					mEntrys.remove(position);
					gridAdapter.notifyDataSetChanged();
					mEditing = false;
					return;
				}

				if (position == 0) {
					startActivity(new Intent(getActivity(), ItemActivity.class));
				} else {
					Intent intent = new Intent(getActivity(),
							OtherItemsActivity.class);
					intent.putExtra("text", ((TextView) args1
							.findViewById(R.id.tv_gv)).getText());
					startActivity(intent);
				}
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
		ImageView iv_right_titlebar = (ImageView) view
				.findViewById(R.id.iv_right_titlebar);
		iv_right_titlebar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(getActivity()).setView(
						View.inflate(getActivity(),
								R.layout.alert_repair_layout, null)).show();
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
		ImageView iv_middle_titlebar = (ImageView) view
				.findViewById(R.id.iv_middle_titlebar);
		iv_middle_titlebar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(getActivity())
						.setTitle("功能管理")
						.setItems(
								new String[] { "北京", "上海", "广州", "深圳", "杭州",
										"青岛", "大连", "武汉" },
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										switch (which) {
										case 0: {
											new AlertDialog.Builder(
													getActivity())
													.setTitle("选择楼盘")
													.setItems(
															new String[] {
																	"韩建雅苑",
																	"花雨汀",
																	"春辉时代	",
																	"万润北京运河湾",
																	"星光城",
																	"中国铁建顺鑫汇",
																	"华夏幸福城",
																	"鸿坤罗纳河谷" },
															null)

													.setPositiveButton("确定",
															null)
													.setNegativeButton("取消",
															null).show();
										}
											break;
										default: {
											new AlertDialog.Builder(
													getActivity())
													.setTitle("选择楼盘")
													.setItems(
															new String[] {
																	"宝华源墅",
																	"龙湖北城天街",
																	"上海中骏广场",
																	"阳光城MODO",
																	"国浩长风汇都",
																	"曹路家苑",
																	"中海万锦城",
																	"宝华栎庭" },
															null)

													.setPositiveButton("确定",
															null)
													.setNegativeButton("取消",
															null).show();
										}
										}
									}
								})

						.setPositiveButton("确定", null)
						.setNegativeButton("取消", null).show();
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

		mGridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			private ImageView iv_delete_icon;

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0,
					final View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				// new MainActivity().setButtonChecked(3);
				// ImageView iv_delete_icon = (ImageView)
				// gv.findViewById(R.id.iv_delete_icon);
				// iv_delete_icon.setVisibility(View.VISIBLE);
				// arg1.startAnimation(animation);
				mEditing = true;

				gridAdapter.notifyDataSetChanged();
				return true;
			}
		});
	}

	public void addEntry(EntryModel e) {
		if (mEntrys.size() < 6) {
			mEntrys.add(e);
			gridAdapter.notifyDataSetChanged();
		} else {
			ToastUtil.show("首页快捷入口已满，请删除后再添加");
		}
	}

	private void initUI() {
		// TODO Auto-generated method stub

		setViewPager();
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

		private ImageView iv_gv;
		private TextView tv_gv;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			// if (MainActivity.srcIdSelected != -1
			// && MainActivity.iconTopicSelected != null) {
			// return mEntrys.size() + 1;
			// } else {

			return mEntrys.size();
		}

		@Override
		public Object getItem(int position) {
			return mEntrys.get(position);
		}

		@Override
		public long getItemId(int position) {
			return mEntrys.get(position).resID;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getActivity(), R.layout.grid_item, null);
			iv_gv = (ImageView) view.findViewById(R.id.iv_gv);
			tv_gv = (TextView) view.findViewById(R.id.tv_gv);
			// if (position >= mEntrys.size() && MainActivity.srcIdSelected !=
			// -1
			// && MainActivity.iconTopicSelected != null) {
			// iv_gv.setBackgroundResource(MainActivity.srcIdSelected);
			// tv_gv.setText(MainActivity.iconTopicSelected);
			// } else
			{
				iv_gv.setBackgroundResource(mEntrys.get(position).resID);
				tv_gv.setText(mEntrys.get(position).name);
			}

			if (mEditing) {
				Animation animation = AnimationUtils.loadAnimation(
						getActivity(), R.anim.rotate);
				ImageView iv_delete_icon = (ImageView) view
						.findViewById(R.id.iv_delete_icon);
				iv_delete_icon.setVisibility(View.VISIBLE);
				view.startAnimation(animation);
			}
			return view;
		}

	}

}
