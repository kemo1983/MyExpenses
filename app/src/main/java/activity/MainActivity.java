package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ke.myexpenses.R;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }


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

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null ;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new AccountsFragment();
                title = getString(R.string.title_accounts);
                break;
            case 2:
                fragment = new IncomeFragment();
                title = getString(R.string.title_income);
                break;
            case 3:
                Intent i = new Intent(MainActivity.this,ExpensesFragment.class);
                startActivity(i);
                title = getString(R.string.title_expenses);
                getSupportActionBar().setTitle(title);

                break;
            case 4:
                fragment = new UpcomingBillsFragment();
                title = getString(R.string.title_upcoming_bills);
                break;
            case 5:
                fragment = new UnpaidBillsFragment();
                title = getString(R.string.title_unpaid_bills);
                break;
            case 6:
                fragment = new Calculator();
                title = getString(R.string.title_calculator);
                break;
            case 7:
                fragment = new ReportsFragment();
                title = getString(R.string.title_reports);
                break;
            case 8:
                Intent okk = new Intent(this,TransferFragment.class);
                startActivity(okk);
                title = getString(R.string.nav_item_transfer);
                getSupportActionBar().setTitle(title);
                break;
            case 9:
                fragment = new SummaryFragment();
                title = getString(R.string.title_summary);
                break;
            case 10:
                fragment = new SettingsFragment();
                title = getString(R.string.title_settings);
                break;
            case 11:
                Intent oo = new Intent(this,ExpenseDataList.class);
                startActivity(oo);
                title = getString(R.string.nav_item_viewexpense);
                getSupportActionBar().setTitle(title);
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}