package sbingo.com.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sbingo on 2016/8/28.
 */
public class PopupTestView extends LinearLayout {

    @BindView(R.id.close)
    Button close;

    private PopListener listener;

    public PopupTestView(Context context) {
        this(context, null);
    }

    public PopupTestView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public PopupTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.pop_up_layout, this);
        ButterKnife.bind(this);
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.close();
            }
        });
    }

    public interface PopListener {
        void close();
    }

    public void setPopListener(PopListener listener) {
        this.listener = listener;
    }
}
