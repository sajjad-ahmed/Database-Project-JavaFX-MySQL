package Niloy;

/**
 * Created by Sajjad  on 7/28/2017.
 */
public class Employee
{

    public String n_id;
    public String date_of_Birth;
    public String name;
    public String address;
    public String salary;
    public String sex;
    public String password;

    //----salesman----//
    public String commission;

    //---stuff-----//
    public String staff_type;
    public String g_location;
    public String c_type;
    public String mec_grade;

    public Employee(String n_id, String date_of_Birth, String name, String address, String salary, String sex, String password, String commission, String staff_type, String g_location, String c_type, String mec_grade)
    {
        this.n_id = n_id;
        this.date_of_Birth = date_of_Birth;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.sex = sex;
        this.password = password;
        this.commission = commission;
        this.staff_type = staff_type;
        this.g_location = g_location;
        this.c_type = c_type;
        this.mec_grade = mec_grade;
    }

    public Employee()
    {
        this.n_id = "";
        this.date_of_Birth = "";
        this.name = "";
        this.address = "";
        this.salary = "";
        this.sex = "";
        this.password = "";
        this.commission = "";
        this.staff_type = "";
        this.g_location = "";
        this.c_type = "";
        this.mec_grade = "";
    }

    public String getN_id()
    {
        return n_id;
    }

    public void setN_id(String n_id)
    {
        this.n_id = n_id;
    }

    public String getDate_of_Birth()
    {
        return date_of_Birth;
    }

    public void setDate_of_Birth(String date_of_Birth)
    {
        this.date_of_Birth = date_of_Birth;
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

    public String getSalary()
    {
        return salary;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    public String getCommission()
    {
        return commission;
    }

    public void setCommission(String commission)
    {
        this.commission = commission;
    }

    public String getStaff_type()
    {
        return staff_type;
    }

    public void setStaff_type(String staff_type)
    {
        this.staff_type = staff_type;
    }

    public String getG_location()
    {
        return g_location;
    }

    public void setG_location(String g_location)
    {
        this.g_location = g_location;
    }

    public String getC_type()
    {
        return c_type;
    }

    public void setC_type(String c_type)
    {
        this.c_type = c_type;
    }

    public String getMec_grade()
    {
        return mec_grade;
    }

    public void setMec_grade(String mec_grade)
    {
        this.mec_grade = mec_grade;
    }
}
