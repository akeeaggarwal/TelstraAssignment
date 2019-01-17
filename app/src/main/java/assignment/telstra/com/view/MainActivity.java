package assignment.telstra.com.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import assignment.telstra.com.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            AboutCountryFragment fragment = new AboutCountryFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, AboutCountryFragment.TAG).commit();
        }
    }

}
