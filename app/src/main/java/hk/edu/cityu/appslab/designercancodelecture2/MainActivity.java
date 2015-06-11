package hk.edu.cityu.appslab.designercancodelecture2;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button scaleUpBtn;
    private ImageView icon;
    MainActivity activity;
    private Button parabolaBtn;
    private Button moveInBtn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //choose xml to be this activity layout
        setContentView(R.layout.activity_main);
        //find toolbar and set it as Actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set up elevation so that toolbar will have shadow
        //actually it is set the Z value of the view
        //according to material design doc
        //when a view's Z > 0, it should have shadow since reality having light
        //Therefore, it will automatic generate shadow
        //and different elevation will have different shadow
        //the first item in () is the object to be set
        //the second one is the elevation or Z value
        //getResources().getDimension(R.dimen.elevation) can get back the value of elevation in dimens.xml
        //this will return in px unit but it is set in dp
        //so the method can avoid dp to px convert
        ViewCompat.setElevation(toolbar, getResources().getDimension(R.dimen.elevation));
        //reference itself to call in following code
        activity = this;

        //find out all button and the android icon view
        scaleUpBtn = (Button) findViewById(R.id.scale_up);
        parabolaBtn = (Button) findViewById(R.id.parabola);
        moveInBtn = (Button) findViewById(R.id.move_in);
        icon = (ImageView) findViewById(R.id.icon);
        //set the elevation to the android icon so that the parabola animation will not be cover by the toolbar
        //if do not understand my comment, I suggest you try to comment and uncomment the follow statement to see the different
        //p.s. this only having effect at Android 5.0 or above device, use simulator to test the effect is better
        ViewCompat.setElevation(icon,getResources().getDimension(R.dimen.parabola_height));

        //set OnClickListener to trigger the animation
        scaleUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load animation from xml
                Animator animator = AnimatorInflater.loadAnimator(activity, R.animator.scale_up);
                //set the target of animation
                animator.setTarget(icon);
                //start it
                animator.start();
            }
        });

        //same as above
        parabolaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator = AnimatorInflater.loadAnimator(activity, R.animator.parabola);
                animator.setTarget(icon);
                animator.start();
            }
        });
        //same as above
        moveInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator = AnimatorInflater.loadAnimator(activity, R.animator.move_in);
                animator.setTarget(icon);
                animator.start();
            }
        });
    }

    //following code is generated when creating the project
    //they are not useful in this lecture
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
