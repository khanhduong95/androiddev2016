package feedr.usth.tanu.feedr;

import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
/**
 * Created by tanu on 3/11/16.
 */
public class SecondTab extends Activity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
/* Second Tab Content */
        TextView textView = new TextView(this);
        textView.setText("DanTri");
        setContentView(textView);

    }
}
