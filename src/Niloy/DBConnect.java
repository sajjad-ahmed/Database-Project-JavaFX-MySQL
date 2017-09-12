package Niloy;


import java.sql.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Sajjad Ahmed Niloy on 7/26/2017.
 */


/*
 * DBname: nilouklq_dbp
 *  un: nilouklq_sah
 * pw: 87864165154577841
 *
 *
 * jdbc:sqlserver://carshowroomserver.database.windows.net:1433;database=Carshowroom;user=carshowroom@carshowroomserver;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
 */

/*
 * DBname: carshowroomserver
 *  admin: carshowroom
 *  ad pw: 6545_GcaawE_34
 *  un: nilouklq_sah
 * pw: 87864165154577841
 */





public class DBConnect
{
    private Connection connection;
    public Statement statement;
    private ResultSet resultSet;

    public DBConnect()
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carshowroom", "root", "");
//            connection = DriverManager.getConnection("jdbc:mysql://193.111.63.252:2083/nilouklq_dbp?user=","nilouklq_sah","87864165154577841");
//            connection = DriverManager.getConnection("jdbc:mysql://niloy.net:2083/nilouklq_dbp", "nilouklq_sah", "s#$SDAS$%$FDF%");
//            connection = DriverManager.getConnection("jdbc:sqlserver://carshowroomserver.database.windows.net:1433;database=Carshowroom;user=carshowroom@carshowroomserver;password={6545_GcaawE_34};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30");

            statement = connection.createStatement();
        } catch (Exception e)
        {
            AlertBox.alert_with_NO_Action("Connection Error!", "Could not connect to the database..... :(\nCheck Internet Connection ");
            System.out.println(e);
        }
    }

    public String getPassword(String username)
    {
        if (username.isEmpty())
        {
            return "not found";
        } else
        {
            try
            {
                ResultSet resultSet = statement.executeQuery("select * from employee where n_id=" + username);
                String s = "";
                while (resultSet.next())
                    s = resultSet.getString("password");
                return s;
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return "not found";
    }

    public ArrayList<Car> get_Car_ArrayList()
    {
        ArrayList<Car> carArrayList = new ArrayList<>();
        try
        {
            ResultSet resultSet = statement.executeQuery("select * from car");
            addAllCar_from_ResultSet_to_ArrayList(carArrayList, resultSet);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return carArrayList;
    }

    private void addAllCar_from_ResultSet_to_ArrayList(ArrayList<Car> carArrayList, ResultSet resultSet) throws SQLException
    {
        while (resultSet.next())
        {
            String chassis = resultSet.getString("Chassis");
            String engine_no = resultSet.getString("Engine_no");
            String cc = resultSet.getString("CC");
            String color = resultSet.getString("Color");
            String transmission = resultSet.getString("Transmission");
            String brand = resultSet.getString("Brand");
            String price = resultSet.getString("Price");
            String car_Type = resultSet.getString("Car_type");
            String designer = resultSet.getString("Designer");
            String hp = resultSet.getString("HP");
            String four_wheelDrive = resultSet.getString("4Wheel_drive");
            String fog_Light = resultSet.getString("Fog_light");
            String ignition = resultSet.getString("Ignition");
            String door = resultSet.getString("Door");
            String seat = resultSet.getString("Seat");
            String salesman_id = resultSet.getString("Salesman_id");
            String customer_id = resultSet.getString("Customer_id");
            String quantity = resultSet.getString("Quantity");
            Car car = new Car(chassis, engine_no, cc, color, transmission, brand, price, car_Type, designer, hp, four_wheelDrive, fog_Light, ignition, door, seat, salesman_id, customer_id, quantity);
            carArrayList.add(car);
        }
    }


    public ObservableList<Car> get_Filtered_DB_Car_Query(String tableName, String conditionSql)
    {
        ObservableList<Car> carArrayList = FXCollections.observableArrayList();
        try
        {
            ResultSet resultSet = statement.executeQuery("select * from " + tableName + " where " + conditionSql); //"+tableName+" where "+conditionSql);
            ArrayList<Car> tempArrayList = new ArrayList<>();
            addAllCar_from_ResultSet_to_ArrayList(tempArrayList, resultSet);
            carArrayList.addAll(tempArrayList);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return carArrayList;
    }

    //        SELECT * from employee WHERE n_id LIKE '1701%';
//        SELECT * from employee E1, staff S1 WHERE (E1.n_id=S1.n_id);
//        SELECT * from employee E1, salesman S1 WHERE (E1.n_id=S1.n_id);
    public ObservableList<Employee> get_ALL_Employees_From_DB()
    {
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        try
        {
//-----------------------------manager
            ResultSet resultSet = statement.executeQuery("SELECT * from employee WHERE n_id LIKE \'1701%\'"); //"+tableName+" where "+conditionSql);
            while (resultSet.next())
            {
                String n_id = resultSet.getString("n_id");
                String date_of_Birth = resultSet.getString("Date_of_Birth");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String salary = resultSet.getString("Salary");
                String sex = resultSet.getString("Sex");
                String password = resultSet.getString("password");

                //----salesman----//
                String commission = "NULL";

                //---stuff-----//
                String stuff_type = "NULL";
                String g_location = "NULL";
                String c_type = "NULL";
                String mec_grade = "NULL";

                Employee employee = new Employee(n_id, date_of_Birth, name, address, salary, sex, password, commission, stuff_type, g_location, c_type, mec_grade);
                employeeObservableList.add(employee);
            }
//-----------------------------salesman
            resultSet = statement.executeQuery("SELECT * from employee E1, salesman S1 WHERE (E1.n_id=S1.n_id)"); //"+tableName+" where "+conditionSql);
            while (resultSet.next())
            {
                String n_id = resultSet.getString("n_id");
                String date_of_Birth = resultSet.getString("Date_of_Birth");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String salary = resultSet.getString("Salary");
                String sex = resultSet.getString("Sex");
                String password = resultSet.getString("password");

                //----salesman----//
                String commission = resultSet.getString("Commission");

                //---stuff-----//
                String stuff_type = "NULL";
                String g_location = "NULL";
                String c_type = "NULL";
                String mec_grade = "NULL";

                Employee employee = new Employee(n_id, date_of_Birth, name, address, salary, sex, password, commission, stuff_type, g_location, c_type, mec_grade);
                employeeObservableList.add(employee);
            }
//-----------------------------stuff
            resultSet = statement.executeQuery("SELECT * from employee E1, staff S1 WHERE (E1.n_id=S1.n_id)"); //"+tableName+" where "+conditionSql);
            while (resultSet.next())
            {
                String n_id = resultSet.getString("n_id");
                String date_of_Birth = resultSet.getString("Date_of_Birth");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String salary = resultSet.getString("Salary");
                String sex = resultSet.getString("Sex");
                String password = resultSet.getString("password");

                //----salesman----//
                String commission = "NULL";

                //---stuff-----//
                String stuff_type = resultSet.getString("Staff_type");
                String g_location = resultSet.getString("G_location");
                String c_type = resultSet.getString("C_type");
                String mec_grade = resultSet.getString("Mec_grade");

                Employee employee = new Employee(n_id, date_of_Birth, name, address, salary, sex, password, commission, stuff_type, g_location, c_type, mec_grade);
                employeeObservableList.add(employee);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return employeeObservableList;
    }

    public ObservableList<Customer> get_ALL_Customer_From_DB()
    {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        try
        {
            ResultSet resultSet = statement.executeQuery("select * from customer"); //"+tableName+" where "+conditionSql);
            addAllCustomer_from_ResultSet_to_ArrayList(customerObservableList, resultSet);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return customerObservableList;
    }

    public ObservableList<Customer> get_ALL_Customer_From_DB_with_SQL_Query(String sqlCondition)
    {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        try
        {
            ResultSet resultSet = statement.executeQuery("select * from customer WHERE " + sqlCondition); //"+tableName+" where "+conditionSql);
            addAllCustomer_from_ResultSet_to_ArrayList(customerObservableList, resultSet);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return customerObservableList;
    }

    private void addAllCustomer_from_ResultSet_to_ArrayList(ObservableList<Customer> customerObservableList, ResultSet resultSet) throws SQLException
    {
        while (resultSet.next())
        {
            String n_id = resultSet.getString("n_id");
            String name = resultSet.getString("Name");
            String address = resultSet.getString("Address");
            String phone_no = resultSet.getString("Phone_no");
            String payment_type = resultSet.getString("Payment_type");
            String cash_amount = resultSet.getString("Cash_amount");
            String no_of_installment = resultSet.getString("No_of_installments");
            String premium = resultSet.getString("Premium");

            Customer customer = new Customer(n_id, name, address, phone_no, payment_type, cash_amount, no_of_installment, premium);
            customerObservableList.add(customer);
        }
    }


    public ObservableList<Employee> get_ALL_Employees_From_DB_with_NID(String s)
    {

        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        try
        {
//-----------------------------manager
            ResultSet resultSet = statement.executeQuery("SELECT * from employee WHERE n_id LIKE \'1701%\' AND n_id=" + s); //"+tableName+" where "+conditionSql);
            while (resultSet.next())
            {
                String n_id = resultSet.getString("n_id");
                String date_of_Birth = resultSet.getString("Date_of_Birth");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String salary = resultSet.getString("Salary");
                String sex = resultSet.getString("Sex");
                String password = resultSet.getString("password");

                //----salesman----//
                String commission = "NULL";

                //---stuff-----//
                String stuff_type = "NULL";
                String g_location = "NULL";
                String c_type = "NULL";
                String mec_grade = "NULL";

                Employee employee = new Employee(n_id, date_of_Birth, name, address, salary, sex, password, commission, stuff_type, g_location, c_type, mec_grade);
                employeeObservableList.add(employee);
            }
//-----------------------------salesman
            System.out.println("SELECT * from employee E1, salesman S1 WHERE (E1.n_id=S1.n_id  AND E1.n_id=" + s + ")");
            resultSet = statement.executeQuery("SELECT * from employee E1, salesman S1 WHERE (E1.n_id=S1.n_id  AND E1.n_id=" + s + ")"); //"+tableName+" where "+conditionSql);
            while (resultSet.next())
            {
                String n_id = resultSet.getString("n_id");
                String date_of_Birth = resultSet.getString("Date_of_Birth");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String salary = resultSet.getString("Salary");
                String sex = resultSet.getString("Sex");
                String password = resultSet.getString("password");

                //----salesman----//
                String commission = resultSet.getString("Commission");

                //---stuff-----//
                String stuff_type = "NULL";
                String g_location = "NULL";
                String c_type = "NULL";
                String mec_grade = "NULL";

                Employee employee = new Employee(n_id, date_of_Birth, name, address, salary, sex, password, commission, stuff_type, g_location, c_type, mec_grade);
                employeeObservableList.add(employee);
            }
//-----------------------------stuff
            resultSet = statement.executeQuery("SELECT * from employee E1, staff S1 WHERE (E1.n_id=S1.n_id AND E1.n_id=" + s + ")"); //"+tableName+" where "+conditionSql);
            while (resultSet.next())
            {
                String n_id = resultSet.getString("n_id");
                String date_of_Birth = resultSet.getString("Date_of_Birth");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String salary = resultSet.getString("Salary");
                String sex = resultSet.getString("Sex");
                String password = resultSet.getString("password");

                //----salesman----//
                String commission = "NULL";

                //---stuff-----//
                String stuff_type = resultSet.getString("Staff_type");
                String g_location = resultSet.getString("G_location");
                String c_type = resultSet.getString("C_type");
                String mec_grade = resultSet.getString("Mec_grade");

                Employee employee = new Employee(n_id, date_of_Birth, name, address, salary, sex, password, commission, stuff_type, g_location, c_type, mec_grade);
                employeeObservableList.add(employee);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return employeeObservableList;

    }
}
