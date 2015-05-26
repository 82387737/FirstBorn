package info.liruqi.bjhouse.customcomponent;

import info.liruqi.bjhouse.R;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

public class MyDialog extends Dialog {

	private Context m_Context;
	private Window window;

	public MyDialog(Context context) {
		super(context);
		m_Context = context;
	}

	public void showDialog(int pa_LayoutId, int pa_X, int pa_Y) {

		setContentView(R.layout.erweima_dialog); // 原来对话框里也有这个方法，和Activity里一样的。
		setControler();
		windowDeploy(pa_X, pa_Y);
		// 设置触摸对话框意外的地方取消对话框
		setCanceledOnTouchOutside(true);
		show(); // Dialog.show();
	}

	/*
	 * 设置窗口显示
	 */
	public void windowDeploy(int x, int y) {

		window = getWindow(); // 得到对话框
		//window.setWindowAnimations(R.style.dialogWindowAnim); // 设置窗口弹出动
		// window.setBackgroundDrawableResource(R.color.vifrification);
		// //设置对话框背景为透明
		WindowManager.LayoutParams l_LP = window.getAttributes();
		// 根据x，y坐标设置窗口需要显示的位置
		l_LP.x = x; // x小于0左移，大于0右移
		l_LP.y = y; // y小于0上移，大于0下移
		// wl.alpha = 0.6f; //设置透明度
		// wl.gravity = Gravity.BOTTOM; //设置重力
		window.setAttributes(l_LP);
	}

	/*
	 * 
	 * 设置控件属性 ::感觉设置和Activity里一样
	 */
	private void setControler() {

/*		Button l_Btn = (Button) this.findViewById(R.id.ButtonTest);
		l_Btn.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(m_Context, "点击了按钮", Toast.LENGTH_SHORT).show();
			}

		});*/
	}

}
