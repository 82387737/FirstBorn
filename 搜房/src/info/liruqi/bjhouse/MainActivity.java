package info.liruqi.bjhouse;

import java.util.ArrayList;
import java.util.List;

import info.liruqi.bjhouse.domain.ViewPagerElement;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {
	List <ViewPagerElement> viewList ;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		initData();
		ViewPager vp = (ViewPager) findViewById(R.id.vp);
		vp.setAdapter(new MyAdapter());
	}
	private void initData() {
		viewList = new ArrayList<ViewPagerElement>();
		
		ViewPagerElement ve1 = new ViewPagerElement();
		ve1.setSrc_id(R.drawable.a);
		ve1.setTitle("这是第一张图片");
		viewList.add(ve1);
	
		
		ViewPagerElement ve2 = new ViewPagerElement();
		ve1.setSrc_id(R.drawable.b);
		ve1.setTitle("这是第二张图片");
		viewList.add(ve2);
		
		
		ViewPagerElement ve3 = new ViewPagerElement();
		ve1.setSrc_id(R.drawable.c);
		ve1.setTitle("这是第三张图片");
		viewList.add(ve3);
		
		
		ViewPagerElement ve4 = new ViewPagerElement();
		ve1.setSrc_id(R.drawable.d);
		ve1.setTitle("这是第四张图片");
		viewList.add(ve4);
		
	}
	class MyAdapter extends PagerAdapter {

	    @Override
	    public int getCount() {
	      return 4;
	    }

	    @Override
	    public CharSequence getPageTitle(int position) {
	      return (viewList.get(position)).getTitle();
	    }

	    @Override
	    public Object instantiateItem(ViewGroup container, int position) {
	    	ImageView iv = new ImageView(MainActivity.this);
	    	iv.setBackgroundResource(viewList.get(position).getSrc_id());
	    	container.addView(iv);
	    	return iv;
	    }

	    @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	      // super.destroyItem(container, position, object);
	    }

	    @Override
	    public boolean isViewFromObject(View arg0, Object arg1) {
	      return arg0 == arg1;// 官方提示这样写
	    }

	  }
}
