package info.liruqi.bjhouse;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {

	private static final String TAG = "ToastUtil";

	private static final String DURATION = "duration";

	private static final String TEXT = "text";

	private static final int MSG_TOAST = 0;

	private static final int MSG_TOAST_TOP = 1;

	private static Context mAppContext;

    private static Toast mNormalToast;

    private static Toast mTopToast;

	private static Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			final Context cx = mAppContext;
			String text = msg.getData().getString(TEXT);
            int duration = msg.getData().getInt(DURATION);
			switch (msg.what) {
			case MSG_TOAST:
                showNormalToast(cx, text, duration);
				break;
			case MSG_TOAST_TOP:
                showTopToast(cx, text, duration);
				break;
			default:
				break;
			}
		}
	};

    private static void showNormalToast(Context context, CharSequence text, int duration) {
        if (mNormalToast == null) {
            mNormalToast = Toast.makeText(context, text, duration);
        } else {
            mNormalToast.setText(text);
            mNormalToast.setDuration(duration);
        }
        mNormalToast.show();
    }

    private static void showTopToast(Context context, CharSequence text, int duration) {
        if (mTopToast == null) {
            mTopToast = Toast.makeText(context, text, duration);
            mTopToast.setGravity(Gravity.TOP, 0, 0);
        } else {
            mTopToast.setText(text);
            mTopToast.setDuration(duration);
        }
        mTopToast.show();
    }

	private static void sendToastMessage(CharSequence text, int duration) {
		sendToastMessage(text, duration, MSG_TOAST);
	}

	private static void sendTopToastMessage(CharSequence text, int duration) {
		sendToastMessage(text, duration, MSG_TOAST_TOP);
	}

	private static void sendToastMessage(CharSequence text, int duration,
			int toastType) {
        if (TextUtils.isEmpty(text)) {
            return;
        }

		Message msg = new Message();
		Bundle b = new Bundle();
		b.putInt(DURATION, duration);
		b.putString(TEXT, text.toString());
		msg.setData(b);
		msg.what = toastType;
		mHandler.sendMessage(msg);
	}

	public static void init(Context cx) {
		mAppContext = cx.getApplicationContext();
	}

	public static void show(CharSequence text) {
		sendToastMessage(text, Toast.LENGTH_SHORT);
	}

	public static void show(int resId) {
		String text = mAppContext.getString(resId);
		sendToastMessage(text, Toast.LENGTH_SHORT);
	}

	public static void show(int resId, Object... formatArgs) {
		String text = mAppContext.getString(resId, formatArgs);
		sendToastMessage(text, Toast.LENGTH_SHORT);
	}

	public static void longShow(CharSequence text) {
		sendToastMessage(text, Toast.LENGTH_LONG);
	}

	public static void longShow(int resId) {
		String text = mAppContext.getString(resId);
		sendToastMessage(text, Toast.LENGTH_LONG);
	}

	public static void longShowTop(CharSequence text) {
		sendTopToastMessage(text, Toast.LENGTH_LONG);
	}

}
