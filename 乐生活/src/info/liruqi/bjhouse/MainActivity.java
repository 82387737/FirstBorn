package info.liruqi.bjhouse;

import info.liruqi.bjhouse.customcomponent.MyDialog;
import info.liruqi.bjhouse.fragment.EntertainmentFragment;
import info.liruqi.bjhouse.fragment.FamilyFragment;
import info.liruqi.bjhouse.fragment.HomeFragment;
import info.liruqi.bjhouse.fragment.MeFragment;
import info.liruqi.bjhouse.fragment.WorkFragment;
import android.app.Activity;
import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private static MyDialog dialog = null;
	private SensorManager sensorManager;
	private static final String TAG = "TestSensorActivity";
	private static final int SENSOR_SHAKE = 10;
	public static Animation animation = null;
	public static int srcIdSelected = -1;
	public static String iconTopicSelected = null;
	public static int[] buttons = { 0, 0, 0, 0, 0 };
	public static int currentPosition =-1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		startFragment(new HomeFragment());
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Log.i("ola", "alala,nene,sabixi");
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (animation != null) {
			// animation.getFillBefore();
			// animation.reset();
			animation.setRepeatCount(0);
			// animation.cancel();
			animation = null;
			if(currentPosition == 0){FamilyFragment.intGridIcon();}
			else if(currentPosition == 1){WorkFragment.intGridIcon();}
			else if(currentPosition == 2){HomeFragment.intGridIcon();}
			else if(currentPosition == 3){EntertainmentFragment.intGridIcon();EntertainmentFragment.deleteOpen = false;}
			else if(currentPosition == 4){MeFragment.intGridIcon();}
			
		} else {
			super.onBackPressed();
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		if (sensorManager != null) {// 注册监听器
			sensorManager.registerListener(sensorEventListener,
					sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManager.SENSOR_DELAY_NORMAL);
			// 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
		}
		dialog = null;
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	Log.i("ano", "dialog可以让我失去焦点吗？");
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (sensorManager != null) {// 取消监听器
			sensorManager.unregisterListener(sensorEventListener);
		}
	}

	/**
	 * 重力感应监听
	 */
	private SensorEventListener sensorEventListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			// 传感器信息改变时执行该方法
			float[] values = event.values;
			float x = values[0]; // x轴方向的重力加速度，向右为正
			float y = values[1]; // y轴方向的重力加速度，向前为正
			float z = values[2]; // z轴方向的重力加速度，向上为正
			Log.i(TAG, "x轴方向的重力加速度" + x + "；y轴方向的重力加速度" + y + "；z轴方向的重力加速度" + z);
			// 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
			int medumValue = 17;// 如果不敏感请自行调低该数值,低于10的话就不行了,因为z轴上的加速度本身就已经达到10了
			if (Math.abs(x) > medumValue || Math.abs(y) > medumValue
					|| Math.abs(z) > medumValue) {
				Message msg = new Message();
				msg.what = SENSOR_SHAKE;
				handler.sendMessage(msg);
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	};

	/**
	 * 动作执行
	 */

	Handler handler = new Handler() {


		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SENSOR_SHAKE:
				if (dialog == null) {
					dialog = new MyDialog(MainActivity.this);
					dialog.showDialog(R.layout.erweima_dialog, -50, -100);
				}
				break;
			}
		}

	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll_menu1:
			setButtonChecked(0);
			currentPosition =0;
			startFragment(new FamilyFragment());
			break;
		case R.id.ll_menu2:
			setButtonChecked(1);
			currentPosition =1;
			startFragment(new WorkFragment());
			break;
		case R.id.ll_menu3:
			setButtonChecked(2);
			currentPosition =2;
			startFragment(new HomeFragment());

			break;
		case R.id.ll_menu4:
			setButtonChecked(3);
			currentPosition =3;
			startFragment(new EntertainmentFragment());
			break;
		case R.id.ll_menu5:
			setButtonChecked(4);
			currentPosition =4;
			startFragment(new MeFragment());
			break;

		}

	}

	public void startFragment(android.support.v4.app.Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fl_main, fragment).commit();
	}

	public void setButtonChecked(int Position) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = 0;
		}
		buttons[Position] = 1;
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[0] != 0) {
				((ImageView) findViewById(R.id.iv_family))
						.setBackgroundResource(R.drawable.family_pressed);
			} else {
				((ImageView) findViewById(R.id.iv_family))
						.setBackgroundResource(R.drawable.family_nomal);
			}
			if (buttons[1] != 0) {
				((ImageView) findViewById(R.id.iv_work))
						.setBackgroundResource(R.drawable.work_pressed);
			} else {
				((ImageView) findViewById(R.id.iv_work))
						.setBackgroundResource(R.drawable.work_nomal);
			}
			if (buttons[2] != 0) {
				((ImageView) findViewById(R.id.iv_home))
						.setBackgroundResource(R.drawable.home_pressed);
			} else {
				((ImageView) findViewById(R.id.iv_home))
						.setBackgroundResource(R.drawable.home_nomal);
			}
			if (buttons[3] != 0) {
				((ImageView) findViewById(R.id.iv_entertainment))
						.setBackgroundResource(R.drawable.entertain_pressed);
			} else {
				((ImageView) findViewById(R.id.iv_entertainment))
						.setBackgroundResource(R.drawable.entertain_nomal);
			}
			if (buttons[4] != 0) {
				((ImageView) findViewById(R.id.iv_me))
						.setBackgroundResource(R.drawable.me_pressed);
			} else {
				((ImageView) findViewById(R.id.iv_me))
						.setBackgroundResource(R.drawable.me_nomal);
			}
		}
	}
}
