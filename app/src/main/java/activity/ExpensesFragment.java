package activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ke.myexpenses.R;

import java.util.Calendar;

public class ExpensesFragment extends Activity implements View.OnClickListener, DatePickerFragment.TheListener {

    static final String KEY_RESULT = "resultField";
    public ArrayAdapter<CharSequence> adapter1;
    public ArrayAdapter<CharSequence> adapter2;
    ImageButton calculatorimagebutton, datepicker;
    RelativeLayout relativelayout;
    EditText enter_amount;
    EditText enter_date, description;
    int year, month, day;
    Calendar calendar;
    Spinner category_spin, account_spin;
    Context context = this;
    DbHelper dbhelper;
    SQLiteDatabase sqlitedatabase;
    String e_category, e_account;
    Button savebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_expenses);
        initialize();
    }

    public void initialize() {
        enter_amount = (EditText) findViewById(R.id.editText);
        description = (EditText) findViewById(R.id.editText3);
        datepicker = (ImageButton) findViewById(R.id.imageButton2);
        calculatorimagebutton = (ImageButton) findViewById(R.id.imageButton);
        savebtn = (Button) findViewById(R.id.btn_insert);

        // relativelayout = (RelativeLayout) findViewById(R.id.fragment_expense_layout);
        calculatorimagebutton.setOnClickListener(this);
        datepicker.setOnClickListener(this);
        savebtn.setOnClickListener(this);

        ///date picker

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        enter_date = (EditText) findViewById(R.id.textView7);
        datepicker = (ImageButton) findViewById(R.id.imageButton2);

        // spinners
        category_spin = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter1 = ArrayAdapter.createFromResource(this,
                R.array.expenses_category, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        category_spin.setAdapter(adapter1);
        category_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                e_category = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ///////////////second adapter for account

        account_spin = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Account, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        account_spin.setAdapter(adapter2);
        account_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                e_account = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imageButton:

                Intent i = new Intent(this, PopUpCalculator.class);
                startActivityForResult(i, 5833);
                break;

            case R.id.imageButton2:
                DatePickerFragment picker = new DatePickerFragment();
                picker.show(getFragmentManager(), "date picker");
                break;
            case R.id.btn_insert:
                saveexpense();
                break;

        }
    }

    private void saveexpense() {
        String e_amt, e_date, e_desc;

        e_amt = enter_amount.getText().toString();
        e_date = enter_date.getText().toString();
        e_desc = description.getText().toString();
        if (e_amt.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Amount", Toast.LENGTH_LONG).show();
        } else if (e_date.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Date", Toast.LENGTH_LONG).show();
        } else if (e_desc.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Description", Toast.LENGTH_LONG).show();
        } else {
            e_insert(e_category,e_account);
        }


    }

    private void e_insert(String e_category,String e_account) {
        String  e_amt, e_date , e_desc;
this.e_category=e_category;
        this.e_account=e_account;
        e_amt = enter_amount.getText().toString();
        e_date = enter_date.getText().toString();
        e_desc = description.getText().toString();

        dbhelper = new DbHelper(context);
        sqlitedatabase = dbhelper.getWritableDatabase();
        dbhelper.insertexps(e_category, e_amt, e_date, e_account, e_desc, sqlitedatabase);
        dbhelper.close();
        Toast.makeText(this, " Data Inserted", Toast.LENGTH_LONG).show();


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5833) {
            String message = data.getStringExtra("MESSAGE");
            enter_amount.setText(message);

        }
    }

///  method implemented from the inner interface inside DatePickerFragment activity for date picker
    /// here we put the selected date into the edi text for the date

    @Override
    public void returnDate(String date) {
        // TODO Auto-generated method stub
        enter_date.setText(date);

    }


}

