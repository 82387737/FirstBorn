package info.liruqi.bjhouse;

import info.liruqi.bjhouse.fragment.EntertainmentFragment;
import info.liruqi.bjhouse.fragment.FamilyFragment;
import info.liruqi.bjhouse.fragment.HomeFragment;
import info.liruqi.bjhouse.fragment.MeFragment;
import info.liruqi.bjhouse.fragment.WorkFragment;
import android.app.Activity;
import android.app.Fragment;
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

public class MainActivity extends FragmentActivity implements OnClickListener{
	public static int srcIdSelected = -1;
	public static String iconTopicSelected = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		startFragment(new HomeFragment());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.ll_menu1:
				startFragment(new FamilyFragment());
				break;
			case R.id.ll_menu2:
				startFragment(new WorkFragment());
				break;
			case R.id.ll_menu3:
				startFragment(new HomeFragment());
				
				break;
			case R.id.ll_menu4:
				startFragment(new EntertainmentFragment());
				break;
			case R.id.ll_menu5:
				startFragment(new MeFragment());
				break;
			
			
		}
		
	}
	public void startFragment(android.support.v4.app.Fragment fragment){
		getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).commit();
		
		
	}
}
