package activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ke.myexpenses.R;

import java.util.ArrayList;
import java.util.List;


public class ExpenseListAdapter extends ArrayAdapter {

    List list = new ArrayList();


    public ExpenseListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ExpenseListAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    static class LayoutHandler {
        TextView E_Category,E_Account,E_Note,E_Amount,E_Date;


    }
    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutHandler layouthandler;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            layouthandler = new LayoutHandler();
            layouthandler.E_Category = (TextView) row.findViewById(R.id.category_view);
            layouthandler.E_Account = (TextView) row.findViewById(R.id.account_view);
            layouthandler.E_Amount = (TextView) row.findViewById(R.id.amount_view);
            layouthandler.E_Date = (TextView) row.findViewById(R.id.date_view);
            layouthandler.E_Note = (TextView) row.findViewById(R.id.note_view);
            row.setTag(layouthandler);

        } else {
            layouthandler = (LayoutHandler) row.getTag();
        }


        ExDataModel dataProvider = (ExDataModel) this.getItem(position);
        layouthandler.E_Category.setText(dataProvider.getE_category());
        layouthandler.E_Account.setText(dataProvider.getE_method());
        layouthandler.E_Amount.setText(dataProvider.getE_amt());
        layouthandler.E_Date.setText(dataProvider.getE_date());
        layouthandler.E_Note.setText(dataProvider.getDesc());

        return row;
    }

}
