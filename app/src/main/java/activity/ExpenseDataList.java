package activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ke.myexpenses.R;

public class ExpenseDataList extends AppCompatActivity {

    ListView listview;
    SQLiteDatabase sqlitedatabase;
    DbHelper dbhelper;
    Cursor cursor;
    ExpenseListAdapter expenselistadapter;
    Button add_new_expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_data_list_activity_layout);

        add_new_expense = (Button) findViewById(R.id.add_new_expense);
        listview = (ListView) findViewById(R.id.expense_list_view);
        expenselistadapter = new ExpenseListAdapter(getApplicationContext(), R.layout.row_layout);
        listview.setAdapter(expenselistadapter);


        dbhelper = new DbHelper(getApplicationContext());
        sqlitedatabase = dbhelper.getReadableDatabase();
        cursor = dbhelper.getExpenseData(sqlitedatabase);
     //   cursor = dbhelper.ssss(sqlitedatabase);
        if (cursor.moveToFirst()) {

            do {

                String e_category, e_amt, e_account, e_date, e_note;
                e_category = cursor.getString(cursor.getColumnIndex("e_category"));
                e_amt = cursor.getString(cursor.getColumnIndex("e_amt"));
                e_account = cursor.getString(cursor.getColumnIndex("e_method"));
                e_date = cursor.getString(cursor.getColumnIndex("e_date"));
                e_note = cursor.getString(cursor.getColumnIndex("e_desc"));
                ExDataModel dataprovider = new ExDataModel(e_category, e_amt, e_account, e_date, e_note);
                expenselistadapter.add(dataprovider);


            } while (cursor.moveToNext());


        }


    }

    public void onclickaddnewexpense(View view) {

        Intent i = new Intent(ExpenseDataList.this, ExpensesFragment.class);
        startActivity(i);
    }





}
