package info.liruqi.bjhouse;

import info.liruqi.bjhouse.customcomponent.MyDialog;
import info.liruqi.bjhouse.fragment.EntertainmentFragment;
import info.liruqi.bjhouse.fragment.FamilyFragment;
import info.liruqi.bjhouse.fragment.HomeFragment;
import info.liruqi.bjhouse.fragment.MeFragment;
import info.liruqi.bjhouse.fragment.WorkFragment;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
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
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener,SensorEventListener {
	public static int srcIdSelected = -1;
	public static String iconTopicSelected = null;
	public static int[] buttons = { 0, 0, 0, 0, 0 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		context = this;
		start();
		startFragment(new HomeFragment());
	    MainActivity shakeListener = new MainActivity();//创建一个对象  
	    shakeListener.setOnShakeListener(new OnShakeListener(){//调用setOnShakeListener方法进行监听  
	      
	    public void onShake() {  
	        //对手机摇晃后的处理（如换歌曲，换图片，震动……）  
	        //onVibrator(); 
			new MyDialog(MainActivity.this).showDialog(R.layout.erweima_dialog,
					-50, -100);
	    }  
	      
	    });  
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll_menu1:
			setButtonChecked(0);
			startFragment(new FamilyFragment());
			break;
		case R.id.ll_menu2:
			setButtonChecked(1);
			startFragment(new WorkFragment());
			break;
		case R.id.ll_menu3:
			setButtonChecked(2);
			startFragment(new HomeFragment());

			break;
		case R.id.ll_menu4:
			setButtonChecked(3);
			startFragment(new EntertainmentFragment());
			break;
		case R.id.ll_menu5:
			setButtonChecked(4);
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
	 //速度阈值，当摇晃速度达到这值后产生作用  
	 private static final int SPEED_SHRESHOLD = 4000;  
	 //两次检测的时间间隔  
	 private static final int UPTATE_INTERVAL_TIME = 70;  
	   
	 //传感器管理器  
	 private SensorManager sensorManager;  
	 //传感器  
	 private Sensor sensor;  
	 //重力感应监听器  
	 private OnShakeListener onShakeListener;  
	 //上下文  
	 private static Context context;  
	 //手机上一个位置时重力感应坐标  
	 private float lastX;  
	 private float lastY;  
	 private float lastZ;  
	   
	 //上次检测时间  
	 private long lastUpdateTime;  
	  
	 //构造器  
	   
	 //开始  
	 public void start() {  
	  //获得传感器管理器  
	  sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);   
	  if(sensorManager != null) {  
	   //获得重力传感器  
	   sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);  
	  }  
	  //注册  
	  if(sensor != null) {  
	   sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);  
	  }  
	    
	 }  
	   
	 //停止检测  
	 public void stop() {  
	  sensorManager.unregisterListener(this);  
	 }  
	   
	 //摇晃监听接口  
	 public interface OnShakeListener {  
	  public void onShake();  
	 }  
	   
	 //设置重力感应监听器  
	 public void setOnShakeListener(OnShakeListener listener) {  
	  onShakeListener = listener;  
	 }  
	   
	   
	 //重力感应器感应获得变化数据  
	 @Override
	 public void onSensorChanged(SensorEvent event) {  
	  //现在检测时间  
	  long currentUpdateTime = System.currentTimeMillis();  
	  //两次检测的时间间隔  
	  long timeInterval = currentUpdateTime - lastUpdateTime;    
	  //判断是否达到了检测时间间隔  
	  if(timeInterval < UPTATE_INTERVAL_TIME)   
	   return;  
	  //现在的时间变成last时间  
	  lastUpdateTime = currentUpdateTime;  
	    
	  //获得x,y,z坐标  
	  float x = event.values[0];  
	  float y = event.values[1];  
	  float z = event.values[2];  
	    
	  //获得x,y,z的变化值  
	  float deltaX = x - lastX;  
	  float deltaY = y - lastY;  
	  float deltaZ = z - lastZ;  
	    
	  //将现在的坐标变成last坐标  
	  lastX = x;  
	  lastY = y;  
	  lastZ = z;  
	    
	  double speed = Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ)/timeInterval * 10000;  
	  //达到速度阀值，发出提示  
	  if(speed >= SPEED_SHRESHOLD)  
	   onShakeListener.onShake();  
	 }  
	@Override   
	 public void onAccuracyChanged(Sensor sensor, int accuracy) {  
	    
	 }  

}
