package Niloy;

/**
 * Created by Gigabyte on 7/28/2017.
 */
public class Customer
{
    public String n_id;
    public String name;
    public String address;
    public String phone_no;
    public String payment_type;
    public String cash_amount;
    public String no_of_installment;
    public String premium;

    public Customer(String n_id, String name, String address, String phone_no, String payment_type, String cash_amount, String no_of_installment, String premium)
    {
        this.n_id = n_id;
        this.name = name;
        this.address = address;
        this.phone_no = phone_no;
        this.payment_type = payment_type;
        this.cash_amount = cash_amount;
        this.no_of_installment = no_of_installment;
        this.premium = premium;
    }

    public Customer()
    {
        this.n_id = "";
        this.name = "";
        this.address = "";
        this.phone_no = "";
        this.payment_type = "";
        this.cash_amount = "";
        this.no_of_installment = "";
        this.premium = "";
    }

    public String getN_id()
    {
        return n_id;
    }

    public void setN_id(String n_id)
    {
        this.n_id = n_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone_no()
    {
        return phone_no;
    }

    public void setPhone_no(String phone_no)
    {
        this.phone_no = phone_no;
    }

    public String getPayment_type()
    {
        return payment_type;
    }

    public void setPayment_type(String payment_type)
    {
        this.payment_type = payment_type;
    }

    public String getCash_amount()
    {
        return cash_amount;
    }

    public void setCash_amount(String cash_amount)
    {
        this.cash_amount = cash_amount;
    }

    public String getNo_of_installment()
    {
        return no_of_installment;
    }

    public void setNo_of_installment(String no_of_installment)
    {
        this.no_of_installment = no_of_installment;
    }

    public String getPremium()
    {
        return premium;
    }

    public void setPremium(String premium)
    {
        this.premium = premium;
    }
}