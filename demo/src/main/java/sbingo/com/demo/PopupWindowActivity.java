package sbingo.com.demo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import sbingo.com.popupwindow.CustomPopupWindow;


/**
 * Created by Sbingo on 2016/8/26.
 */
public class PopupWindowActivity extends BaseActivity {

    private ActionBar actionBar;
    private CustomPopupWindow customPopupWindow;
    private PopupTestView popupTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow_layout);

        actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.app_name));

        /**
         * step1
         * create an instance of {@link CustomPopupWindow}.
         */
        customPopupWindow = new CustomPopupWindow(PopupWindowActivity.this);
        /** This is optional */
        customPopupWindow.setDismissListener(new CustomPopupWindow.DismissCallBack() {
            @Override
            public void dismissCallBack() {
                /** do sth you like here */
            }
        });

        /**
         * step2
         * create a arbitrary view to popup yourself.
         */
        popupTestView = new PopupTestView(PopupWindowActivity.this);
        popupTestView.setPopListener(new PopupTestView.PopListener() {
            @Override
            public void close() {
                /** if you want to dismiss the popupWindow in your view,
                 * do it like this.
                 */
                if (CustomPopupWindow.popupWindow != null) {
                    CustomPopupWindow.popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * step3
     * choose one way to show the popupWindow as follows.
     */

    public void showRight(View v) {
        /** {@link CustomPopupWindow#setGravity(int)} is optional */
        customPopupWindow.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
        customPopupWindow.showOnRight(v, popupTestView, -1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, -50, -100);
    }

    public void showLeft(View v) {
        customPopupWindow.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        customPopupWindow.showOnLeft(v, popupTestView, -1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 50, -150);
    }

    public void showTop(View v) {
        customPopupWindow.setGravity(Gravity.BOTTOM);
        customPopupWindow.showOnTop(v, popupTestView, 0, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, -50);
    }

    public void showBottom(View v) {
        customPopupWindow.setGravity(Gravity.TOP);
        customPopupWindow.showOnBottom(v, popupTestView, 0, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 100);
    }

    public void showFullScreen(View v) {
        customPopupWindow.setGravity(Gravity.RIGHT | Gravity.TOP);
        customPopupWindow.showFullScreen(v, popupTestView, R.style.pop_fullscreen_style, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, -100, 300);
    }

    public void showScreenBottom(View v) {
        customPopupWindow.showOnScreenBottom(v, popupTestView, R.style.pop_bottom_style, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0);
    }

}
