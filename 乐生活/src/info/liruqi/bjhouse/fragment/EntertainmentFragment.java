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

public class EntertainmentFragment extends Fragment {
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(getActivity(), R.layout.other_fragment, null);
/*		TextView tv = (TextView) view.findViewById(R.id.tv);
		tv.setText("Entertainment");*/
		TextView tv_middle_titlebar = (TextView)view.findViewById(R.id.tv_middle_titlebar);
		tv_middle_titlebar.setText("Entertainment");
		return view;	
		
	}
}
