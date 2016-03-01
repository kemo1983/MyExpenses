package activity;

/**
 * Created by K@E on 2/23/2016.
 */
public class ExDataModel {

    private String e_category, e_date, e_amt, e_method, e_desc;
    private int _id;


    public ExDataModel() {

    }

    public ExDataModel(int id, String e_category, String e_date, String e_amt, String e_method, String e_desc) {

        this._id = id;
        this.e_category = e_category;
        this.e_date = e_date;
        this.e_amt = e_amt;
        this.e_method = e_method;
        this.e_desc = e_desc;

    }


    public ExDataModel(String e_category, String e_date, String e_amt, String e_method, String e_desc) {
        this.e_category = e_category;
        this.e_date = e_date;
        this.e_amt = e_amt;
        this.e_method = e_method;
        this.e_desc = e_desc;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getE_method() {
        return e_method;
    }

    public void setE_method(String e_method) {
        this.e_method = e_method;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }

    public String getE_category() {
        return e_category;
    }

    public void setE_category(String e_category) {
        this.e_category = e_category;
    }

    public String getE_amt() {
        return e_amt;
    }

    public void setE_amt(String e_amt) {
        this.e_amt = e_amt;
    }

    public String getDesc() {
        return e_desc;
    }

    public void setDesc(String desc) {
        this.e_desc = desc;
    }
}
