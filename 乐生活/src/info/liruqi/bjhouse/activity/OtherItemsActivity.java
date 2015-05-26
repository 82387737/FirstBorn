package info.liruqi.bjhouse.activity;

import info.liruqi.bjhouse.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OtherItemsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.items_activity);
		TextView tv = (TextView) findViewById(R.id.tv);
		Intent intent = getIntent();
		String text = intent.getStringExtra("text");
		tv.setText(text);
	}
}
