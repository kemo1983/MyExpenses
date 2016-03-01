package activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Myexpenses";
    public static final int DATABASE_VERSION = 1;

    /* public static final String CREATE_QUERY =
            "CREATE TABLE " + UserContract.NewUserInfo.TABLE_NAME + "(" + UserContract.NewUserInfo.USER_NAME + " TEXT," + UserContract.NewUserInfo.USER_MOB + " TEXT," + UserContract.NewUserInfo.USER_EMAIL + " TEXT);";
*/
    public static final String CREATE_EQUERY = "CREATE TABLE " + ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_TABLE_NAME
            + "(" + ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_CATEGORY + " TEXT NOT NULL,"
            + ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_AMOUNT + " TEXT NOT NULL,"
            + ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_DATE + " TEXT NOT NULL,"
            + ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_METHOD + " TEXT NOT NULL,"
            + ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_DESC + " TEXT NOT NULL);";
    public static final String CREATE_IQUERY = "CREATE TABLE " + ExpenseIncomeTables.INCOMETABLECOLUMNS.INCOME_TABLE_NAME
            + "(" + ExpenseIncomeTables.INCOMETABLECOLUMNS.INCOME_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ExpenseIncomeTables.INCOMETABLECOLUMNS.INCOME_COLUMN_CATEGORY + " TEXT NOT NULL,"
            + ExpenseIncomeTables.INCOMETABLECOLUMNS.INCOME_COLUMN_AMOUNT + " TEXT NOT NULL,"
            + ExpenseIncomeTables.INCOMETABLECOLUMNS.INCOME_COLUMN_DATE + " TEXT NOT NULL,"
            + ExpenseIncomeTables.INCOMETABLECOLUMNS.INCOME_COLUMN_METHOD + " TEXT NOT NULL,"
            + ExpenseIncomeTables.INCOMETABLECOLUMNS.INCOME_COLUMN_DESC + " TEXT NOT NULL);";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("data base operations", "database created / opened.....");

    }




    /*String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
    TABLE_PRODUCTS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME
    + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")";
    db.execSQL(CREATE_PRODUCTS_TABLE);*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub


        db.execSQL(CREATE_EQUERY);
        db.execSQL(CREATE_IQUERY);
        Log.e("data base operations", "TABLE CREATED.....");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS expenses");
        db.execSQL("DROP TABLE IF EXISTS income");
        onCreate(db);

    }

    public long insertexps(String e_category, String e_date, String e_amt, String e_method, String e_desc, SQLiteDatabase db) {

        long id;
        if (e_category.equals("") || e_date.equals("") || e_amt.equals("") || e_method.equals("") || e_desc.equals("")) {
            id = -1;
        } else {
            ContentValues values = new ContentValues();
            values.put(ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_CATEGORY, e_category);
            values.put(ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_DATE, e_date);
            values.put(ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_AMOUNT, e_amt);
            values.put(ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_METHOD, e_method);
            values.put(ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_DESC, e_desc);
            id = db.insert(ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_TABLE_NAME, null, values);
        }

        return id;
    }

    public Cursor getExpenseData(SQLiteDatabase db) {
        String[] projections = {ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_ID, ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_CATEGORY,
                ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_DATE, ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_AMOUNT,
                ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_METHOD, ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_DESC};
        Cursor cursor = db.query(ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_TABLE_NAME, projections, null, null, null, null, null);

        return cursor;

    }


    public Cursor categoryfilter() {

        String[] projections = {ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_ID, ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_CATEGORY,
                ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_DATE, ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_AMOUNT,
                ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_METHOD, ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_DESC};
     SQLiteDatabase   db = this.getReadableDatabase();
        Cursor cursor = db.query(ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_TABLE_NAME,projections,null,null,null,null,ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_CATEGORY+" DESC");

        return cursor;

    }

    public Cursor ssss(SQLiteDatabase db ){


        String query = "select * from "+ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_TABLE_NAME + " group by "+ ExpenseIncomeTables.EXPENSETABLECOLUMNS.EXPENSES_COLUMN_DATE  ;

Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

}