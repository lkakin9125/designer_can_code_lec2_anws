package hk.edu.cityu.appslab.designercancodelecture2;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

public class ParabolaImageView extends ImageView {
    //this view only can move in or parabola to the center of the screen

    public static final String TAG = ParabolaImageView.class.getSimpleName();
    //a gravity constant copied from calculator
    public static final double GRAVITY_CONSTANT = 9.80665;
    private int distanceX, distanceY;
    private double uV,uH;
    private float parabolaHeight;

    public ParabolaImageView(Context context) {
        super(context);
        init();
    }

    public ParabolaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParabolaImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ParabolaImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //a method to initialize the view
    public void init(){
        //since the view is only use parabola to center
        //as s = u*t+0.5*a*t*t
        //in vertical
        // 0 = uV*t + 0.5*g*t*t
        // 0 = t* (uV + 0.5*g*t)
        // as i want the image will go back to same vertical level when objectAnimator assign t = 1,
        // t = 0 and t = 1 should be the root
        // |uV| = 0.5*g
        uV = 0.5*GRAVITY_CONSTANT;
        //WindowManager can find out the screen height and width by wm.getDefaultDisplay().getWidth() or ..getHeight()
        //as go back to center so distanceX and distanceY should /2
        final WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        distanceX = wm.getDefaultDisplay().getWidth()/2;
        distanceY = wm.getDefaultDisplay().getHeight()/2;

        //as s = u*t+0.5*a*t*t
        //in horizontal,
        //as i want the image will go to center when objectAnimator assign t = 1,
        //so when s = distanceX, t = 1
        //as no horizontal acceleration
        //distanceX = uH*1 + 0.5*0*1*1
        uH = distanceX;
        //get the parabolaHeight that i want
        //since I want to use dp to maintain the height is the same in different device,
        //but it only can hard code a px here
        //I set a dp value at dimens.xml and get it back
        //so that no need to convert dp to px
        parabolaHeight = getResources().getDimension(R.dimen.parabola_height);
    }

    public int getDistanceX() {
        return distanceX;
    }

    public double getuV() {
        return uV;
    }

    public double getuH() {
        return uH;
    }

    public void setDistanceX(int distanceX) {
        this.distanceX = distanceX;

    }

    public void setXFraction(float xFraction){
        //use xFraction to be as the percentage go to center
        setTranslationX(xFraction* distanceX);
    }

    public void setYFraction(float yFraction){
        // same as above
        setTranslationY(yFraction*distanceY);
    }

    public void setParabolaX(final float t) {
        //s=u*t+0.5*a*t*t
        //ensure no calculate mistake
        if (distanceX >0) {
            if (t == 1) {
                // ensure it go back to same level when t =1
                setTranslationX(0);
            } else {
                //using formula to calculate the translationX
                double sH = uH * t;
                setTranslationX((float) -( distanceX - sH));
            }
        }
    }

    public void setParabolaY(final float t) {
        //s=u*t+0.5*a*t*t
        if (distanceX >0) {
            if (t == 1) {
                setTranslationY(0);
            } else {
                double sV = uV * t - 0.5 * GRAVITY_CONSTANT * t * t;
                //since the top px is 0 and the object want to go up
                //the sv should be negative
                setTranslationY((float) (-sV*parabolaHeight));
            }
        }
    }
}
