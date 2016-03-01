package activity;

/**
 * Created by K@E on 2/23/2016.
 */
public class ExpenseIncomeTables {

    public ExpenseIncomeTables(){

    }

    public static abstract class EXPENSETABLECOLUMNS{

        public static final String EXPENSES_TABLE_NAME = "expenses";
        public static final String EXPENSES_COLUMN_ID = "id";
        public static final String EXPENSES_COLUMN_CATEGORY = "e_category";
        public static final String EXPENSES_COLUMN_DATE = "e_date";
        public static final String EXPENSES_COLUMN_AMOUNT = "e_amt";
        public static final String EXPENSES_COLUMN_METHOD = "e_method";
        public static final String EXPENSES_COLUMN_DESC = "e_desc";

    }


    public static abstract class INCOMETABLECOLUMNS{

        public static final String INCOME_TABLE_NAME = "income";
        public static final String INCOME_COLUMN_ID = "id";
        public static final String INCOME_COLUMN_CATEGORY = "i_category";
        public static final String INCOME_COLUMN_DATE = "i_date";
        public static final String INCOME_COLUMN_AMOUNT = "i_amt";
        public static final String INCOME_COLUMN_METHOD = "i_method";
        public static final String INCOME_COLUMN_DESC = "i_desc";
    }
}
