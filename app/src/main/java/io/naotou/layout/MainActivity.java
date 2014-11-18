package io.naotou.layout;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private LinearLayout ll_image1;
    private LinearLayout ll_image2;
    private LinearLayout ll_image3;
    public static final String TAG = "MainActivity";
    private ImageView imageView;
    private List<ImageView> imageList1 = new ArrayList<ImageView>();
    private List<ImageView> imageList2 = new ArrayList<ImageView>();
    private List<ImageView> imageList3 = new ArrayList<ImageView>();
    private int flag = 0;
    private int rotateY = 20;
    private ObjectAnimator animator1;
    private ObjectAnimator animator2;
    private ObjectAnimator animator3;
    private RelativeLayout rl;
    private boolean endAnimation;
    private int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll_image1 = (LinearLayout) findViewById(R.id.ll_image1);
        ll_image2 = (LinearLayout) findViewById(R.id.ll_image2);
        ll_image3 = (LinearLayout) findViewById(R.id.ll_image3);
        rl = (RelativeLayout) findViewById(R.id.rl);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.b7);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.b8);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.b10);
        bitmap1.setDensity((int) (bitmap1.getDensity() * dm.density + 0.5f));
        bitmap2.setDensity((int) (bitmap2.getDensity() * dm.density + 0.5f));
        bitmap3.setDensity((int) (bitmap3.getDensity() * dm.density + 0.5f));
        int width1 = bitmap1.getWidth() / rotateY;
        int height1 = bitmap1.getHeight();
        int width2 = bitmap1.getWidth() / rotateY;
        int height2 = bitmap1.getHeight();
        int width3 = bitmap1.getWidth() / rotateY;
        int height3 = bitmap1.getHeight();

        List<Bitmap> list1 = new ArrayList<Bitmap>();
        for (int i = 0; i < rotateY; i++) {
            Bitmap b = Bitmap.createBitmap(bitmap1, width1 * i, 0, width1, height1);
            list1.add(b);
        }


        List<Bitmap> list2 = new ArrayList<Bitmap>();
        for (int i = 0; i < rotateY; i++) {
            Bitmap b = Bitmap.createBitmap(bitmap2, width2 * i, 0, width2, height2);
            list2.add(b);
        }


        List<Bitmap> list3 = new ArrayList<Bitmap>();
        for (int i = 0; i < rotateY; i++) {
            Bitmap b = Bitmap.createBitmap(bitmap3, width3 * i, 0, width3, height3);
            list3.add(b);
        }


        for (int i = 0; i < rotateY; i++) {
            imageView = new ImageView(getApplicationContext());
            imageView.setImageBitmap(list1.get(i));

            ll_image1.addView(imageView);
            imageList1.add(imageView);

        }

        for (int i = 0; i < rotateY; i++) {
            imageView = new ImageView(getApplicationContext());
            imageView.setImageBitmap(list2.get(i));

            ll_image2.addView(imageView);
            imageList2.add(imageView);

        }

        for (int i = 0; i < rotateY; i++) {
            imageView = new ImageView(getApplicationContext());
            imageView.setImageBitmap(list3.get(i));

            ll_image3.addView(imageView);
            imageList3.add(imageView);

        }

        initPosition();
    }

    public void initPosition() {

        int time = 0;

        for (int i = 0; i < imageList2.size(); i++) {
//            rotate0_120(imageList1.get(i), time);
//            animator1.start();
//            rotate120_240(imageList2.get(i), time);
//            animator2.start();
//            rotate240_360(imageList3.get(i), time);
//            animator3.start();

        }
    }

    private void rotate0_120(ImageView imageView, int time) {

        animator1 = ObjectAnimator.ofFloat(imageView, "rotationY", 0, 120);
        animator1.setDuration(time);

//        animator.start();
    }

    private void rotate120_240(ImageView imageView, int time) {

        animator2 = ObjectAnimator.ofFloat(imageView, "rotationY", 120, 240);
        animator2.setDuration(time);

//        animator.start();
    }

    private void rotate240_360(ImageView imageView, int time) {

        animator3 = ObjectAnimator.ofFloat(imageView, "rotationY", 240, 360);
        animator3.setDuration(time);

//        animator.start();
    }

    public void click(View view) {

        int time = 2000;

        for (int i = 0; i < imageList1.size(); i++) {
            if (flag % 3 == 2) {
                rotate0_120(imageList1.get(i), time);
                rotate120_240(imageList2.get(i), time);
                rotate240_360(imageList3.get(i), time);
                Log.i(TAG,"动画3到前面");
                key = 3;

            } else if (flag % 3 == 0) {
                rotate120_240(imageList1.get(i), time);
                rotate240_360(imageList2.get(i), time);
                rotate0_120(imageList3.get(i), time);
                Log.i(TAG,"动画2到前面");

                key = 2;

                ll_image2.setFocusable(true);
            } else if (flag % 3 == 1) {
                rotate240_360(imageList1.get(i), time);
                rotate0_120(imageList2.get(i), time);
                rotate120_240(imageList3.get(i), time);
                Log.i(TAG,"动画1到前面");

                key = 1;


            }
            animator1.setStartDelay(50 * i);
            animator1.start();
            animator2.setStartDelay(50 * i);
            animator2.start();
            animator3.setStartDelay(50 * i);
            animator3.start();
            startAnimation();
        }
        flag++;
    }

    private void startAnimation() {


        new Thread() {
            @Override
            public void run() {

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        switch (key) {
                            case 2:
                                Log.i(TAG, "当前是2");
                                ll_image2.bringToFront();
                                rl.invalidate();
                                break;
                            case 3:
                                Log.i(TAG, "当前是3");
                                ll_image3.bringToFront();
                                rl.invalidate();
                                break;
                            case 1:
                                Log.i(TAG, "当前是1");
                                ll_image1.bringToFront();
                                rl.invalidate();
                                break;
                        }
                    }
                });

            }
        }.start();

    }


}
