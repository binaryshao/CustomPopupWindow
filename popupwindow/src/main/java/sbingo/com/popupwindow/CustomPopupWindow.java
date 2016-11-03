package sbingo.com.popupwindow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

/**
 * Created by Sbingo on 2016/8/26.
 * There are 6 ways here to show the popupWidow, just choose the one you need.
 * Notice: The contentView to popup here will automatically has a parent view
 * with dark background, and the contentView's default gravity is {@link Gravity#CENTER}.
 * You could change the gravity by calling {@link this#setGravity(int)} before
 * any {@code show()} methods) be called every time.
 */
public class CustomPopupWindow {

    public static PopupWindow popupWindow;
    private DismissCallBack dismissCallBack;
    private FrameLayout frameLayout;
    private int gravity;
    private int screenWidth;
    private int screenHight;
    private boolean hasSetGravity;
    private Context context;

    public CustomPopupWindow(Context context) {
        popupWindow = new PopupWindow();
        frameLayout = new FrameLayout(context);
        frameLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.defaultBackground));
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHight = context.getResources().getDisplayMetrics().heightPixels;
        gravity = Gravity.CENTER;
        this.context = context;
    }

    public interface DismissCallBack {
        void dismissCallBack();
    }

    /**
     * set the listener to be called when the popupWindow is dismissed.
     *
     * @param listener
     */
    public void setDismissListener(DismissCallBack listener) {
        this.dismissCallBack = listener;
    }

    /**
     * set the gravity to apply with the View to which
     * these layout parameters are associated
     *
     * @param gravity
     */
    public void setGravity(int gravity) {
        this.gravity = gravity;
        hasSetGravity = true;
    }

    /**
     * show the popupWidow on the bottom of the parentView.
     *
     * @param parentView  the view on which to pin the popup window
     * @param contentView the popup's content
     * @param style       animation style to use when the popup appears
     *                    and disappears.  Set to -1 for the default animation, 0 for no
     *                    animation, or a resource identifier for an explicit animation.
     * @param width       the width, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param height      the height, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param xOff        A horizontal offset from the contentView's gravity in pixels
     * @param yOff        A vertical offset from the contentView's gravity in pixels
     */
    public void showOnBottom(View parentView, View contentView, int style, int width, int height, int xOff, int yOff) {
        frameLayout.removeAllViews();
        frameLayout.addView(contentView, new ViewGroup.LayoutParams(width, height));
        setContentViewLayoutParams(contentView, xOff, yOff);
        createPopupWindow(frameLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, style);
        popupWindow.showAsDropDown(parentView, 0, 0);
    }

    /**
     * show the popupWidow on the top of the parentView.
     *
     * @param parentView  the view on which to pin the popup window and
     *                    get the {@link View#getWindowToken()} token from
     * @param contentView the popup's content
     * @param style       animation style to use when the popup appears
     *                    and disappears.  Set to -1 for the default animation, 0 for no
     *                    animation, or a resource identifier for an explicit animation.
     * @param width       the width, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param height      the height, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param xOff        A horizontal offset from the contentView's gravity in pixels
     * @param yOff        A vertical offset from the contentView's gravity in pixels
     */
    public void showOnTop(View parentView, View contentView, int style, int width, int height, int xOff, int yOff) {
        int[] location = new int[2];
        parentView.getLocationOnScreen(location);
        frameLayout.removeAllViews();
        frameLayout.addView(contentView, new ViewGroup.LayoutParams(width, height));
        setContentViewLayoutParams(contentView, xOff, yOff);
        createPopupWindow(frameLayout, ViewGroup.LayoutParams.MATCH_PARENT, location[1], style);
        popupWindow.showAtLocation(parentView, Gravity.NO_GRAVITY, location[0], location[1] - popupWindow.getHeight());
    }

    /**
     * show the popupWidow on the left of the parentView.
     *
     * @param parentView  the view on which to pin the popup window and
     *                    get the {@link View#getWindowToken()} token from
     * @param contentView the popup's content
     * @param style       animation style to use when the popup appears
     *                    and disappears.  Set to -1 for the default animation, 0 for no
     *                    animation, or a resource identifier for an explicit animation.
     * @param width       the width, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param height      the height, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param xOff        A horizontal offset from the contentView's gravity in pixels
     * @param yOff        A vertical offset from the contentView's gravity in pixels
     */
    public void showOnLeft(View parentView, View contentView, int style, int width, int height, int xOff, int yOff) {
        int[] location = new int[2];
        parentView.getLocationOnScreen(location);
        frameLayout.removeAllViews();
        frameLayout.addView(contentView, new ViewGroup.LayoutParams(width, height));
        setContentViewLayoutParams(contentView, xOff, yOff);
        createPopupWindow(frameLayout, location[0], ViewGroup.LayoutParams.MATCH_PARENT, style);
        popupWindow.showAtLocation(parentView, Gravity.NO_GRAVITY, location[0] - popupWindow.getWidth(), 0);
    }

    /**
     * show the popupWidow on the right of the parentView.
     *
     * @param parentView  the view on which to pin the popup window and
     *                    get the {@link View#getWindowToken()} token from
     * @param contentView the popup's content
     * @param style       animation style to use when the popup appears
     *                    and disappears.  Set to -1 for the default animation, 0 for no
     *                    animation, or a resource identifier for an explicit animation.
     * @param width       the width, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param height      the height, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param xOff        A horizontal offset from the contentView's gravity in pixels
     * @param yOff        A vertical offset from the contentView's gravity in pixels
     */
    public void showOnRight(View parentView, View contentView, int style, int width, int height, int xOff, int yOff) {
        int[] location = new int[2];
        parentView.getLocationOnScreen(location);
        frameLayout.removeAllViews();
        frameLayout.addView(contentView, new ViewGroup.LayoutParams(width, height));
        setContentViewLayoutParams(contentView, xOff, yOff);
        createPopupWindow(frameLayout, screenWidth - location[0] - parentView.getWidth(), ViewGroup.LayoutParams.MATCH_PARENT, style);
        popupWindow.showAtLocation(parentView, Gravity.NO_GRAVITY, location[0] + parentView.getMeasuredWidth(), 0);
    }

    /**
     * show the popupWidow on the bottom of the screen.
     *
     * @param parentView  the view to get the {@link View#getWindowToken()} token from
     * @param contentView the popup's content
     * @param style       animation style to use when the popup appears
     *                    and disappears.  Set to -1 for the default animation, 0 for no
     *                    animation, or a resource identifier for an explicit animation.
     * @param width       the width, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param height      the height, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     */
    public void showOnScreenBottom(View parentView, View contentView, int style, int width, int height,int xOff, int yOff) {
        frameLayout.removeAllViews();
        frameLayout.addView(contentView, new ViewGroup.LayoutParams(width, height));
        setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
        setContentViewLayoutParams(contentView, xOff, yOff);
        createPopupWindow(frameLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, style);
        popupWindow.showAtLocation(parentView, Gravity.NO_GRAVITY, 0, 0);
    }

    /**
     * show the popupWidow full of the screen.
     *
     * @param parentView  the view to get the {@link View#getWindowToken()} token from
     * @param contentView the popup's content
     * @param style       animation style to use when the popup appears
     *                    and disappears.  Set to -1 for the default animation, 0 for no
     *                    animation, or a resource identifier for an explicit animation.
     * @param width       the width, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param height      the height, either {@link ViewGroup.LayoutParams#WRAP_CONTENT},
     *                    {@link ViewGroup.LayoutParams#MATCH_PARENT}, or a fixed size in pixels
     * @param xOff        A horizontal offset from the contentView's gravity in pixels
     * @param yOff        A vertical offset from the contentView's gravity in pixels
     */
    public void showFullScreen(View parentView, View contentView, int style, int width, int height, int xOff, int yOff) {
        frameLayout.removeAllViews();
        frameLayout.addView(contentView, new ViewGroup.LayoutParams(width, height));
        setContentViewLayoutParams(contentView, xOff, yOff);
        createPopupWindow(frameLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, style);
        popupWindow.showAtLocation(parentView, Gravity.NO_GRAVITY, 0, 0);
    }


    private void tryClearGravity() {
        if (!hasSetGravity) {
            gravity = Gravity.CENTER;
        }
    }

    private void setContentViewLayoutParams(View contentView, int xOff, int yOff) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) contentView.getLayoutParams();
        tryClearGravity();
        hasSetGravity = false;
        lp.gravity = gravity;
        if ((gravity & Gravity.RIGHT) == Gravity.RIGHT) {
            lp.rightMargin = -xOff;
        } else {
            lp.leftMargin = xOff;
        }

        if ((gravity & Gravity.BOTTOM) == Gravity.BOTTOM) {
            lp.bottomMargin = -yOff;
        } else {
            lp.topMargin = yOff;
        }
        contentView.setLayoutParams(lp);
        contentView.setOnClickListener(null);
    }

    private void createPopupWindow(View popupView, int width, int height, int animationStyle) {
        popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (dismissCallBack != null)
                    dismissCallBack.dismissCallBack();
            }
        });
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.setAnimationStyle(animationStyle);
    }


}
