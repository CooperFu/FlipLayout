package io.naotou.layout;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Jack_Cooper on 2014/11/17.
 * Have a nice day!
 */
public class demo extends Activity {
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }
}
