package activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.ke.myexpenses.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransferFragment extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_transfer);
    }
}
