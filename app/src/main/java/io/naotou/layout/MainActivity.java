package io.naotou.layout;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private LinearLayout ll_image;
    public static final String TAG = "MainActivity";
    private ImageView imageView;
    private List<ImageView> imageList = new ArrayList<ImageView>();
    private int flag=0;
    private int rotateY = 20;
    private ObjectAnimator animator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll_image = (LinearLayout) findViewById(R.id.ll_image1);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.b7);
        bitmap1.setDensity((int) (bitmap1.getDensity() * dm.density + 0.5f));
        int width = bitmap1.getWidth() / rotateY;
        int height = bitmap1.getHeight();

        List<Bitmap> list = new ArrayList<Bitmap>();

        for (int i = 0; i < rotateY; i++) {
            Bitmap b = Bitmap.createBitmap(bitmap1, width * i, 0, width, height);
            list.add(b);
        }

        for (int i = 0; i < rotateY; i++) {
            imageView = new ImageView(getApplicationContext());
            imageView.setImageBitmap(list.get(i));

            ll_image.addView(imageView);
            imageList.add(imageView);

        }
    }

    private void rotate0_120(ImageView imageView) {

        animator = ObjectAnimator.ofFloat(imageView, "rotationY", 0, 120);
        animator.setDuration(2000);

//        animator.start();
    }

    private void rotate120_240(ImageView imageView) {

        animator = ObjectAnimator.ofFloat(imageView, "rotationY", 120, 240);
        animator.setDuration(2000);

//        animator.start();
    }

    private void rotate240_360(ImageView imageView) {

        animator = ObjectAnimator.ofFloat(imageView, "rotationY", 240, 360);
        animator.setDuration(2000);

//        animator.start();
    }

    public void click(View view) {

        for (int i = 0; i < imageList.size(); i++) {


            if(flag%3==0){
                rotate0_120(imageList.get(i));
            } else if(flag%3==1){
                rotate120_240(imageList.get(i));
            } else if(flag%3==2){
                rotate240_360(imageList.get(i));
            }

            animator.setStartDelay(50*i);
            animator.start();

        }
        flag++;
    }
}
