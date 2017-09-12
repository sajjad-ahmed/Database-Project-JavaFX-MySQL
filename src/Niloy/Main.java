package Niloy;
/**
 * Created by Sajjad Ahmed Niloy on 7/28/2017.
 */

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application
{
    public Parent start_fxml;
    public Stage primaryStage;
    public DBConnect dbConnect;
    private ObservableList<Car> carObservableList;
    private Label lb_console;
    public InsertHelper insertHelper;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        this.primaryStage.getIcons().add(new Image("assets/icon.png"));

        dbConnect = new DBConnect(); //database
        insertHelper = new InsertHelper(dbConnect);

        start_fxml = FXMLLoader.load(getClass().getResource("Start.fxml"));

        primaryStage.setTitle("OTTO SPARK - developed by Niloy");
        Scene start_scene = new Scene(start_fxml, 720, 480);
        Button st_login_Btn = (Button) start_scene.lookup("#id_login_bt");
        Button st_browse_Btn = (Button) start_scene.lookup("#id_browse_bt");
        carObservableList = get_carActivity_DB();

        st_browse_Btn.setOnAction(ebr ->
        {
            carBrowserActivity(start_scene);
        });

        st_login_Btn.setOnAction(e ->
        {
            login_Activity(start_scene);
        });
        primaryStage.setScene(start_scene);
        primaryStage.show();
    }


    //-------------login------------------------------
    private void login_Activity(Scene backScene)   //todo ---------------- login_Activity()
    {
        Parent loginpanel_fxml = null;
        try
        {
            loginpanel_fxml = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene login_scene = new Scene(loginpanel_fxml, 720, 480);
            Button login_bt = (Button) login_scene.lookup("#id_login_button");
            TextField lf_username = (TextField) login_scene.lookup("#id_tf_un");
            PasswordField lf_password = (PasswordField) login_scene.lookup("#id_tf_pw");
            Label lb_error = (Label) login_scene.lookup("#id_lb_error");
            lb_error.setVisible(false);

            login_bt.setOnAction(e1 ->
            {
                String f_un = lf_username.getText();
                String f_pw = lf_password.getText();
                String _r = dbConnect.getPassword(f_un);

                if (f_pw.trim().equals(_r))
                {
                    if (lb_error.isVisible())
                        lb_error.setVisible(false);
                    System.out.println("Log-in:  successful");

                    char user_type = f_un.charAt(3);
                    if (user_type == '1')
                    {
                        lf_password.clear();
                        lf_password.clear();
                        managerActivity(login_scene, f_un);
                        System.out.println("Log-in:  successful----" + "Manager");
                    } else if (user_type == '5')
                    {//Todo debug code ......Salesman

                        lf_password.clear();
                        lf_password.clear();
                        salesmanActivity(login_scene, f_un);

                        System.out.println("Log-in:  successful----" + "Salesman");
                    }
                    if (user_type == '9')
                    {
                        if (!lb_error.isVisible())
                        {
                            lb_error.setText("Sorry.... :( \nYou dont have the access to the database.");
                            lb_error.setVisible(true);
                        }
                        System.out.println("Log-in:  successful----" + "Stuff");
                    }
                } else
                {
                    lb_error.setVisible(true);
                    System.out.println("Log-in:  failed--------" + f_un + "   " + f_pw);
                }

            });
            Button back_bt = (Button) login_scene.lookup("#id_back_button");
            back_bt.setOnAction(e2 ->
            {
                primaryStage.setScene(backScene);
                primaryStage.show();
            });
            primaryStage.setScene(login_scene);
            primaryStage.show();

        } catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }


    private void salesmanActivity(Scene backScene, String userid) //Todo---------------- salesmanActivity()
    {
        Parent salesmanPanel_fxml = null;
        try
        {
            salesmanPanel_fxml = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
            Scene managerPanel_scene = new Scene(salesmanPanel_fxml, 1280, 720);

            Pane topPane = (Pane) managerPanel_scene.lookup("#id_top");
            topPane.setStyle("-fx-background-color: #3A3F44");
            Pane bottomPane = (Pane) managerPanel_scene.lookup("#id_bottom");
            bottomPane.setStyle("-fx-background-color: #929EAA");
            Label welcome_lb = (Label) managerPanel_scene.lookup("#id_label_welcome");
            welcome_lb.setText("Welcome to Salesman Panel");
            Label console_lb = (Label) managerPanel_scene.lookup("#id_console_label");
            console_lb.setStyle("-fx-text-fill: white");

            Button addEmployee_bt = (Button) managerPanel_scene.lookup("#id_addEmployee");
            addEmployee_bt.setVisible(false);

            Button showAllEmployees_bt = (Button) managerPanel_scene.lookup("#id_bt_allEmployee");
            showAllEmployees_bt.setVisible(false);
            Button showAllCustomer_bt = (Button) managerPanel_scene.lookup("#id_bt_allCustomer");
            Button showAllCars_bt = (Button) managerPanel_scene.lookup("#id_bt_allCars");
            showAllCars_bt.setVisible(false);
            Button search_bt = (Button) managerPanel_scene.lookup("#id_search_bt");

            Button cellDelete_bt = (Button) managerPanel_scene.lookup("#id_cellDelete_bt");
            Button addCar_bt = (Button) managerPanel_scene.lookup("#id_addCar");
            addCar_bt.setVisible(false);


            Button addCustomer_bt = (Button) managerPanel_scene.lookup("#id_addCustomer");
            addCustomer_bt.setOnAction(addCustomer_bt_e ->
            {
                if (insertHelper.addCustomer())
                {
                    lb_console.setText("> New Customer Added Successfully!");
                } else
                {
                    lb_console.setText("> Sorry! Something went wrong.");
                }

            });
            Button back_bt = (Button) managerPanel_scene.lookup("#id_bt_back");
            back_bt.setOnAction(back_bt_e ->
            {
                primaryStage.setScene(backScene);
                primaryStage.show();
            });

            Label user_lb = (Label) managerPanel_scene.lookup("#id_label_user");

            lb_console = (Label) managerPanel_scene.lookup("#id_console_label");
            TextField search_fld = (TextField) managerPanel_scene.lookup("#id_searchkey_fld");
            user_lb.setText("Hello, " + userid);
            showAllCustomer_bt.setOnAction(showAllCars_bt_e ->           //todo--------------- showAllCustomer_bt.setOnAction()
            {

                lb_console.setText("> Customer List Loaded Successfully!");
                search_fld.setPromptText("Customer by NID");
                search_bt.setOnAction(search_bt_e ->
                {
                    if (!search_fld.getText().isEmpty())
                    {
                        ObservableList<Customer> tempObserveableList = dbConnect.get_ALL_Customer_From_DB_with_SQL_Query("n_id=" + search_fld.getText() + "");
                        TableView<Customer> managerPanleTableView = (TableView<Customer>) managerPanel_scene.lookup("#id_table_managerPanel");
                        cellDelete_bt.setOnAction(cellDelete_bt_e ->
                        {
                            customerCellDeleteAction(managerPanleTableView);
                        });
                        if (tempObserveableList.size() == 0)
                            lb_console.setText("> Sorry! No Match Found.");
                        else
                            lb_console.setText("> Match Found! Check Results.");
                        set_up_Customer_Table_Editable(managerPanleTableView, tempObserveableList);

                    } else
                        lb_console.setText("> Can not Search! Search Field is Empty.");
                });

                TableView<Customer> managerPanleTableView = (TableView<Customer>) managerPanel_scene.lookup("#id_table_managerPanel");
                cellDelete_bt.setOnAction(cellDelete_bt_e ->
                {
                    customerCellDeleteAction(managerPanleTableView);
                });
//                search_bt
                set_up_Customer_Table_Editable(managerPanleTableView, dbConnect.get_ALL_Customer_From_DB());
            });

            primaryStage.setScene(managerPanel_scene);
            primaryStage.show();
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }


    private void managerActivity(Scene backScene, String userid) //Todo---------------- managerActivity()
    {
        Parent managerPanel_fxml = null;
        try
        {
            managerPanel_fxml = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
            Scene managerPanel_scene = new Scene(managerPanel_fxml, 1280, 720);


            Button showAllEmployees_bt = (Button) managerPanel_scene.lookup("#id_bt_allEmployee");
            Button showAllCustomer_bt = (Button) managerPanel_scene.lookup("#id_bt_allCustomer");
            Button showAllCars_bt = (Button) managerPanel_scene.lookup("#id_bt_allCars");
            Button search_bt = (Button) managerPanel_scene.lookup("#id_search_bt");

            Button cellDelete_bt = (Button) managerPanel_scene.lookup("#id_cellDelete_bt");
            Button addCar_bt = (Button) managerPanel_scene.lookup("#id_addCar");
            addCar_bt.setOnAction(addCar_bt_e ->
            {
                if (insertHelper.addCar())
                {
                    lb_console.setText("> New Car Added Successfully!");
                } else
                {
                    lb_console.setText("> Sorry! Something went wrong.");
                }
            });
            Button addEmployee_bt = (Button) managerPanel_scene.lookup("#id_addEmployee");
            addEmployee_bt.setOnAction(addCar_bt_e ->
            {
                if (insertHelper.addEmployee())
                {
                    lb_console.setText("> New Employee Added Successfully!");
                } else
                {
                    lb_console.setText("> Sorry! Something went wrong.");
                }
            });
            Button addCustomer_bt = (Button) managerPanel_scene.lookup("#id_addCustomer");
            addCustomer_bt.setOnAction(addCustomer_bt_e ->
            {
                if (insertHelper.addCustomer())
                {
                    lb_console.setText("> New Customer Added Successfully!");
                } else
                {
                    lb_console.setText("> Sorry! Something went wrong.");
                }

            });
            Button back_bt = (Button) managerPanel_scene.lookup("#id_bt_back");
            back_bt.setOnAction(back_bt_e ->
            {
                primaryStage.setScene(backScene);
                primaryStage.show();
            });

            Label user_lb = (Label) managerPanel_scene.lookup("#id_label_user");
            lb_console = (Label) managerPanel_scene.lookup("#id_console_label");
            TextField search_fld = (TextField) managerPanel_scene.lookup("#id_searchkey_fld");

            user_lb.setText("Hello, " + userid);


            get_carActivity_DB();

            showAllEmployees_bt.setOnAction(showAllCars_bt_e ->
            {
                lb_console.setText("> Employee List Loaded Successfully!");

                //-----------------search Employee by Nid--------------//
                search_fld.setPromptText("Employee by NID");
                search_bt.setOnAction(search_bt_e ->
                {
                    if (!search_fld.getText().isEmpty())
                    {
                        ObservableList<Employee> tempObserveableList = dbConnect.get_ALL_Employees_From_DB_with_NID(search_fld.getText());
                        TableView<Employee> managerPanleTableView = (TableView<Employee>) managerPanel_scene.lookup("#id_table_managerPanel");
                        cellDelete_bt.setOnAction(cellDelete_bt_e ->
                        {
                            employeeCellDeleteAction(managerPanleTableView);
                        });
                        if (tempObserveableList.size() == 0)
                            lb_console.setText("> Sorry! No Match Found.");
                        else
                            lb_console.setText("> Match Found! Check Results.");
                        set_up_Employee_Table_Editable(managerPanleTableView, tempObserveableList);

                    } else
                        lb_console.setText("> Can not Search! Search Field is Empty.");
                });

                //-----------------search Car by Engine no--------------//


                TableView<Employee> managerPanleTableView = (TableView<Employee>) managerPanel_scene.lookup("#id_table_managerPanel");
                cellDelete_bt.setOnAction(cellDelete_bt_e ->
                {
                    employeeCellDeleteAction(managerPanleTableView);
                });
//                search_bt
                set_up_Employee_Table_Editable(managerPanleTableView, dbConnect.get_ALL_Employees_From_DB());
            });

            showAllCustomer_bt.setOnAction(showAllCars_bt_e ->           //todo--------------- showAllCustomer_bt.setOnAction()
            {
                lb_console.setText("> Customer List Loaded Successfully!");
                //-----------------search Car by Engine no--------------//
                search_fld.setPromptText("Customer by NID");
                search_bt.setOnAction(search_bt_e ->
                {
                    if (!search_fld.getText().isEmpty())
                    {
                        ObservableList<Customer> tempObserveableList = dbConnect.get_ALL_Customer_From_DB_with_SQL_Query("n_id=" + search_fld.getText() + "");
                        TableView<Customer> managerPanleTableView = (TableView<Customer>) managerPanel_scene.lookup("#id_table_managerPanel");
                        cellDelete_bt.setOnAction(cellDelete_bt_e ->
                        {
                            customerCellDeleteAction(managerPanleTableView);
                        });
                        if (tempObserveableList.size() == 0)
                            lb_console.setText("> Sorry! No Match Found.");
                        else
                            lb_console.setText("> Match Found! Check Results.");
                        set_up_Customer_Table_Editable(managerPanleTableView, tempObserveableList);

                    } else
                        lb_console.setText("> Can not Search! Search Field is Empty.");
                });

                //-----------------search Car by Engine no--------------//


                TableView<Customer> managerPanleTableView = (TableView<Customer>) managerPanel_scene.lookup("#id_table_managerPanel");
                cellDelete_bt.setOnAction(cellDelete_bt_e ->
                {
                    customerCellDeleteAction(managerPanleTableView);
                });
//                search_bt
                set_up_Customer_Table_Editable(managerPanleTableView, dbConnect.get_ALL_Customer_From_DB());


            });
            showAllCars_bt.setOnAction(showAllCars_bt_e ->         //todo--------------- showAllCars_bt.setOnAction()
            {
                lb_console.setText("> Car List Loaded Successfully!");

                //-----------------search Car by Engine no--------------//
                search_fld.setPromptText("Search By Engine No");
                search_bt.setOnAction(search_bt_e ->
                {
                    if (!search_fld.getText().isEmpty())
                    {
                        ObservableList<Car> tempObserveableList = dbConnect.get_Filtered_DB_Car_Query("car", "Engine_no=" + search_fld.getText() + "");
                        TableView<Car> managerPanleTableView = (TableView<Car>) managerPanel_scene.lookup("#id_table_managerPanel");
                        cellDelete_bt.setOnAction(cellDelete_bt_e ->
                        {
                            carCellDeleteAction(managerPanleTableView);
                        });
                        if (tempObserveableList.size() == 0)
                            lb_console.setText("> Sorry! No Match Found.");
                        else
                            lb_console.setText("> Match Found! Check Results.");
                        set_up_Car_Table_Editable(managerPanleTableView, tempObserveableList);

                    } else
                        lb_console.setText("> Can not Search! Search Field is Empty.");
                });

                //-----------------search Car by Engine no--------------//


                TableView<Car> managerPanleTableView = (TableView<Car>) managerPanel_scene.lookup("#id_table_managerPanel");
                cellDelete_bt.setOnAction(cellDelete_bt_e ->
                {
                    carCellDeleteAction(managerPanleTableView);
                });
                set_up_Car_Table_Editable(managerPanleTableView, get_carActivity_DB());
            });


            primaryStage.setScene(managerPanel_scene);
            primaryStage.show();
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }


    private void employeeCellDeleteAction(TableView<Employee> managerPanleTableView)
    {
        if (managerPanleTableView.getSelectionModel().getSelectedIndex() >= 0)
        {
            Employee _employee = managerPanleTableView.getSelectionModel().getSelectedItem();
            AlertBox.delete_Alert("Alert!", "Are you sure that you want to delete? " + _employee.name + "\nNB: Once you delete your action can be Undone!");
            if (AlertBox.approval)
            {
                try
                {
                    dbConnect.statement.executeUpdate("DELETE FROM employee WHERE n_id=" + _employee.n_id);
                    managerPanleTableView.getItems().remove(managerPanleTableView.getSelectionModel().getSelectedIndex());
                    lb_console.setText("> Deleted Successfully!");

                } catch (SQLException e)
                {
                    lb_console.setText("> Database Error Occurred! " + e);
                    e.printStackTrace();
                }
            } else
            {
                lb_console.setText("> Delete Operation Cancelled!");
            }

        } else
        {
            AlertBox.alert_with_NO_Action("Can not be deleted!", "No item selected.\nplease select item to delete.");
        }
    }

    private void carCellDeleteAction(TableView<Car> managerPanleTableView)
    {
        if (managerPanleTableView.getSelectionModel().getSelectedIndex() >= 0)
        {
            Car _car = managerPanleTableView.getSelectionModel().getSelectedItem();
            AlertBox.delete_Alert("Alert!", "Are you sure that you want to delete?" + _car.brand + "(Engine no:" + _car.engine_no + ")" + "\nNB: Once you delete your action can be Undone!");
            if (AlertBox.approval)
            {
                try
                {
                    dbConnect.statement.executeUpdate("DELETE FROM car WHERE Engine_no=" + _car.engine_no);
                    managerPanleTableView.getItems().remove(managerPanleTableView.getSelectionModel().getSelectedIndex());
                    lb_console.setText("> Deleted Successfully!");

                } catch (SQLException e)
                {
                    lb_console.setText("> Database Error Occurred! " + e);
                    e.printStackTrace();
                }
            } else
            {
                lb_console.setText("> Delete Operation Cancelled!");
            }

        } else
        {
            AlertBox.alert_with_NO_Action("Can not be deleted!", "No item selected.\nplease select item to delete.");
        }
    }

    private void customerCellDeleteAction(TableView<Customer> managerPanleTableView)
    {
        if (managerPanleTableView.getSelectionModel().getSelectedIndex() >= 0)
        {
            Customer _customer = managerPanleTableView.getSelectionModel().getSelectedItem();
            AlertBox.delete_Alert("Alert!", "Are you sure that you want to delete? " + _customer.name + "\nNB: Once you delete your action can be Undone!");
            if (AlertBox.approval)
            {
                try
                {
                    dbConnect.statement.executeUpdate("DELETE FROM customer WHERE n_id=" + _customer.n_id);
                    managerPanleTableView.getItems().remove(managerPanleTableView.getSelectionModel().getSelectedIndex());
                    lb_console.setText("> Deleted Successfully!");

                } catch (SQLException e)
                {
                    lb_console.setText("> Database Error Occurred! " + e);
                    e.printStackTrace();
                }
            } else
            {
                lb_console.setText("> Delete Operation Cancelled!");
            }

        } else
        {
            AlertBox.alert_with_NO_Action("Can not be deleted!", "No item selected.\nplease select item to delete.");
        }
    }


    private void set_up_Employee_Table_Editable(TableView<Employee> table, ObservableList<Employee> observableList)   //Todo---------------- set_up_Customer_Table_Editable()
    {
        table.setEditable(true);
        TableColumn<Employee, String> n_id_column = new TableColumn<>("n_id");
        n_id_column.setCellValueFactory(new PropertyValueFactory<>("n_id"));


        TableColumn<Employee, String> name_column = new TableColumn<>("Name");
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        name_column.setCellFactory(TextFieldTableCell.forTableColumn());
        name_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.name = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update employee set Name = \'" + t.getNewValue() + "\' where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );

        TableColumn<Employee, String> date_of_birth_column = new TableColumn<>("Date of Birth");
        date_of_birth_column.setCellValueFactory(new PropertyValueFactory<>("date_of_Birth"));
        date_of_birth_column.setCellFactory(TextFieldTableCell.forTableColumn());
        date_of_birth_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.name = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update employee set Date_of_Birth = \'" + t.getNewValue() + "\' where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );


        TableColumn<Employee, String> address_column = new TableColumn<>("Address");
        address_column.setCellValueFactory(new PropertyValueFactory<>("address"));
        address_column.setCellFactory(TextFieldTableCell.forTableColumn());
        address_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.address = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update employee set Address = \'" + t.getNewValue() + "\' where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );

        TableColumn<Employee, String> salary_column = new TableColumn<>("Salary");
        salary_column.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salary_column.setCellFactory(TextFieldTableCell.forTableColumn());
        salary_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.address = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update employee set Salary = " + t.getNewValue() + " where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );

        TableColumn<Employee, String> sex_column = new TableColumn<>("Sex");
        sex_column.setCellValueFactory(new PropertyValueFactory<>("sex"));
        sex_column.setCellFactory(TextFieldTableCell.forTableColumn());
        sex_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.address = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update employee set Sex = \'" + t.getNewValue() + "\' where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );

        TableColumn<Employee, String> password_column = new TableColumn<>("Password");
        password_column.setCellValueFactory(new PropertyValueFactory<>("password"));
        password_column.setCellFactory(TextFieldTableCell.forTableColumn());
        password_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.address = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update employee set password = \'" + t.getNewValue() + "\' where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );


        TableColumn<Employee, String> Commission_column = new TableColumn<>("Commission");
        Commission_column.setCellValueFactory(new PropertyValueFactory<>("commission"));
        Commission_column.setCellFactory(TextFieldTableCell.forTableColumn());
        Commission_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.address = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update salesman set Commission = " + t.getNewValue() + " where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );

        TableColumn<Employee, String> staffType_column = new TableColumn<>("Staff Type");
        staffType_column.setCellValueFactory(new PropertyValueFactory<>("staff_type"));
        staffType_column.setCellFactory(TextFieldTableCell.forTableColumn());
        staffType_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.address = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update staff set Staff_type = \'" + t.getNewValue() + "\' where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );

        TableColumn<Employee, String> GlocationType_column = new TableColumn<>("Guard location");
        GlocationType_column.setCellValueFactory(new PropertyValueFactory<>("g_location"));
        GlocationType_column.setCellFactory(TextFieldTableCell.forTableColumn());
        GlocationType_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.address = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update staff set G_location = \'" + t.getNewValue() + "\' where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );

        TableColumn<Employee, String> cleanerType_column = new TableColumn<>("Cleaner Type");
        cleanerType_column.setCellValueFactory(new PropertyValueFactory<>("c_type"));
        cleanerType_column.setCellFactory(TextFieldTableCell.forTableColumn());
        cleanerType_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.address = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update staff set C_type = \'" + t.getNewValue() + "\' where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );

        TableColumn<Employee, String> MecGradeType_column = new TableColumn<>("Mec Grade");
        MecGradeType_column.setCellValueFactory(new PropertyValueFactory<>("mec_grade"));
        MecGradeType_column.setCellFactory(TextFieldTableCell.forTableColumn());
        MecGradeType_column.setOnEditCommit(
                t ->
                {
                    Employee _e = t.getRowValue();
                    _e.address = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update staff set Mec_grade = \'" + t.getNewValue() + "\' where n_id=" + _e.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );

        table.getColumns().clear();
        table.getItems().clear();
        table.setItems(observableList);
        table.getColumns().addAll(n_id_column, name_column, address_column, sex_column, date_of_birth_column, salary_column, Commission_column, staffType_column, GlocationType_column, cleanerType_column, MecGradeType_column, password_column);
    }


    private void set_up_Customer_Table_Editable(TableView<Customer> table, ObservableList<Customer> observableList)   //Todo---------------- set_up_Customer_Table_Editable()
    {
        table.setEditable(true);
        TableColumn<Customer, String> n_id_column = new TableColumn<>("n_id");
        n_id_column.setCellValueFactory(new PropertyValueFactory<>("n_id"));


        TableColumn<Customer, String> name_column = new TableColumn<>("Name");
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        name_column.setCellFactory(TextFieldTableCell.forTableColumn());
        name_column.setOnEditCommit(
                t ->
                {
                    Customer _c = t.getRowValue();
                    _c.name = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update customer set Name = \'" + t.getNewValue() + "\' where n_id=" + _c.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );

        TableColumn<Customer, String> address_column = new TableColumn<>("Address");
        address_column.setCellValueFactory(new PropertyValueFactory<>("address"));
        address_column.setCellFactory(TextFieldTableCell.forTableColumn());
        address_column.setOnEditCommit(
                t ->
                {
                    Customer _c = t.getRowValue();
                    _c.address = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update customer set Address = \'" + t.getNewValue() + "\' where n_id=" + _c.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );


        TableColumn<Customer, String> phone_no_column = new TableColumn<>("Phone No");
        phone_no_column.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        phone_no_column.setCellFactory(TextFieldTableCell.forTableColumn());
        phone_no_column.setOnEditCommit(
                t ->
                {
                    Customer _c = t.getRowValue();
                    _c.phone_no = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update customer set Phone_no = " + t.getNewValue() + " where n_id=" + _c.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );
        TableColumn<Customer, String> payment_type_column = new TableColumn<>("Payment type");
        payment_type_column.setCellValueFactory(new PropertyValueFactory<>("payment_type"));
        payment_type_column.setCellFactory(TextFieldTableCell.forTableColumn());
        payment_type_column.setOnEditCommit(
                t ->
                {
                    Customer _c = t.getRowValue();
                    _c.payment_type = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update customer set Payment_type = \'" + t.getNewValue() + "\' where n_id=" + _c.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Customer, String> cash_amount_column = new TableColumn<>("cash_amount");
        cash_amount_column.setCellValueFactory(new PropertyValueFactory<>("cash_amount"));
        cash_amount_column.setCellFactory(TextFieldTableCell.forTableColumn());
        cash_amount_column.setOnEditCommit(
                t ->
                {
                    Customer _c = t.getRowValue();
                    _c.cash_amount = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update customer set Cash_amount = " + t.getNewValue() + " where n_id=" + _c.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Customer, String> no_of_installments_column = new TableColumn<>("No_of_installments");
        no_of_installments_column.setCellValueFactory(new PropertyValueFactory<>("no_of_installment"));
        no_of_installments_column.setCellFactory(TextFieldTableCell.forTableColumn());
        no_of_installments_column.setOnEditCommit(
                t ->
                {
                    Customer _c = t.getRowValue();
                    _c.no_of_installment = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update customer set No_of_installments = " + t.getNewValue() + " where n_id=" + _c.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Customer, String> premium_column = new TableColumn<>("Premium");
        premium_column.setCellValueFactory(new PropertyValueFactory<>("premium"));
        premium_column.setCellFactory(TextFieldTableCell.forTableColumn());
        premium_column.setOnEditCommit(
                t ->
                {
                    Customer _c = t.getRowValue();
                    _c.premium = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update customer set Premium = " + t.getNewValue() + " where n_id=" + _c.n_id);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );
        table.getColumns().clear();
        table.getItems().clear();
        table.setItems(observableList);
        table.getColumns().addAll(n_id_column, name_column, address_column, phone_no_column, payment_type_column, cash_amount_column, no_of_installments_column, premium_column);
    }


    //    -----------------------------*******-----------------------------------------------------------
    private void set_up_Car_Table_Editable(TableView<Car> table, ObservableList<Car> observableList)  //Todo---------------- set_up_Car_Table_Editable()
    {
        table.setEditable(true);          //define its editable

        TableColumn<Car, String> engine_no_column = new TableColumn<>("engine_no");
        engine_no_column.setCellValueFactory(new PropertyValueFactory<Car, String>("engine_no"));

        TableColumn<Car, String> chassis_column = new TableColumn<>("chassis");
        chassis_column.setCellValueFactory(new PropertyValueFactory<>("chassis"));
        chassis_column.setCellFactory(TextFieldTableCell.forTableColumn());
        chassis_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.chassis = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set chassis = " + t.getNewValue() + " where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }

                }
        );


        TableColumn<Car, String> cc_column = new TableColumn<>("cc");
        cc_column.setCellValueFactory(new PropertyValueFactory<>("cc"));
        cc_column.setCellFactory(TextFieldTableCell.forTableColumn());
        cc_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.cc = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set CC = " + t.getNewValue() + " where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> color_column = new TableColumn<>("color");
        color_column.setCellValueFactory(new PropertyValueFactory<>("color"));
        color_column.setCellFactory(TextFieldTableCell.forTableColumn());
        color_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.color = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Color = \'" + t.getNewValue() + "\' where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> transmission_column = new TableColumn<>("transmission");
        transmission_column.setCellValueFactory(new PropertyValueFactory<>("transmission"));
        transmission_column.setCellFactory(TextFieldTableCell.forTableColumn());
        transmission_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.transmission = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Transmission = \'" + t.getNewValue() + "\' where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> brand_column = new TableColumn<>("brand");
        brand_column.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brand_column.setCellFactory(TextFieldTableCell.forTableColumn());
        brand_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.brand = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Brand = \'" + t.getNewValue() + "\' where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );


        TableColumn<Car, String> price_column = new TableColumn<>("price");
        price_column.setCellValueFactory(new PropertyValueFactory<>("price"));
        price_column.setCellFactory(TextFieldTableCell.forTableColumn());
        price_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.price = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Price = " + t.getNewValue() + " where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> car_Type_column = new TableColumn<>("car_Type");
        car_Type_column.setCellValueFactory(new PropertyValueFactory<>("car_Type"));
        car_Type_column.setCellFactory(TextFieldTableCell.forTableColumn());
        car_Type_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.car_Type = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Car_type = \'" + t.getNewValue() + "\' where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> designer_column = new TableColumn<>("designer");
        designer_column.setCellValueFactory(new PropertyValueFactory<>("designer"));
        designer_column.setCellFactory(TextFieldTableCell.forTableColumn());
        designer_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.designer = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Designer = \'" + t.getNewValue() + "\' where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> hp_column = new TableColumn<>("hp");
        hp_column.setCellValueFactory(new PropertyValueFactory<>("hp"));
        hp_column.setCellFactory(TextFieldTableCell.forTableColumn());
        hp_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.hp = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set HP = " + t.getNewValue() + " where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> four_wheelDrive_column = new TableColumn<>("four_wheelDrive");
        four_wheelDrive_column.setCellValueFactory(new PropertyValueFactory<>("four_wheelDrive"));
        four_wheelDrive_column.setCellFactory(TextFieldTableCell.forTableColumn());
        four_wheelDrive_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.four_wheelDrive = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set 4wheel_drive = \'" + t.getNewValue() + "\' where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> fog_Light_column = new TableColumn<>("fog_Light");
        fog_Light_column.setCellValueFactory(new PropertyValueFactory<>("fog_Light"));
        fog_Light_column.setCellFactory(TextFieldTableCell.forTableColumn());
        fog_Light_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.fog_Light = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Fog_light = \'" + t.getNewValue() + "\' where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> ignition_column = new TableColumn<>("ignition");
        ignition_column.setCellValueFactory(new PropertyValueFactory<>("ignition"));
        ignition_column.setCellFactory(TextFieldTableCell.forTableColumn());
        ignition_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.ignition = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Ignition = \'" + t.getNewValue() + "\' where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> door_column = new TableColumn<>("door");
        door_column.setCellValueFactory(new PropertyValueFactory<>("door"));
        door_column.setCellFactory(TextFieldTableCell.forTableColumn());
        door_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.door = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Door = " + t.getNewValue() + " where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> seat_column = new TableColumn<>("seat");
        seat_column.setCellValueFactory(new PropertyValueFactory<>("seat"));
        seat_column.setCellFactory(TextFieldTableCell.forTableColumn());
        seat_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.seat = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Seat = " + t.getNewValue() + " where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> salesman_id_column = new TableColumn<>("salesman_id");
        salesman_id_column.setCellValueFactory(new PropertyValueFactory<>("salesman_id"));
        salesman_id_column.setCellFactory(TextFieldTableCell.forTableColumn());
        salesman_id_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.salesman_id = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Salesman_id = " + t.getNewValue() + " where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );


        TableColumn<Car, String> customer_id_column = new TableColumn<>("customer_id");
        customer_id_column.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        customer_id_column.setCellFactory(TextFieldTableCell.forTableColumn());
        customer_id_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.customer_id = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Customer_id = " + t.getNewValue() + " where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        TableColumn<Car, String> quantity_column = new TableColumn<>("quantity");
        quantity_column.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantity_column.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity_column.setOnEditCommit(
                t ->
                {
                    Car _c = t.getRowValue();
                    _c.quantity = t.getNewValue();
                    try
                    {
                        dbConnect.statement.execute("update car set Quantity = " + t.getNewValue() + " where engine_no=" + _c.engine_no);
                        lb_console.setText("> Value Updated Successfully! \n> New value is = " + t.getNewValue());
                    } catch (SQLException e)
                    {
                        lb_console.setText("> Error Occurred! " + e);
                        e.printStackTrace();
                    }
                }
        );

        table.getColumns().clear();
        table.getItems().clear();
        table.setItems(observableList);
        table.getColumns().addAll(engine_no_column, chassis_column, cc_column, color_column, transmission_column, brand_column, price_column, car_Type_column, designer_column, hp_column, four_wheelDrive_column, fog_Light_column, ignition_column, door_column, seat_column, salesman_id_column, customer_id_column, quantity_column);

    }


    private void set_up_Car_Table_NON_Editable(TableView<Car> table, ObservableList observableList) //Todo--------- set_up_Car_Table_NON_Editable()
    {
        TableColumn<Car, String> engine_no_column = new TableColumn<>("engine_no");
        engine_no_column.setCellValueFactory(new PropertyValueFactory<Car, String>("engine_no"));

        TableColumn<Car, String> chassis_column = new TableColumn<>("chassis");
        chassis_column.setCellValueFactory(new PropertyValueFactory<>("chassis"));

        TableColumn<Car, String> cc_column = new TableColumn<>("cc");
        cc_column.setCellValueFactory(new PropertyValueFactory<>("cc"));

        TableColumn<Car, String> color_column = new TableColumn<>("color");
        color_column.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Car, String> transmission_column = new TableColumn<>("transmission");
        transmission_column.setCellValueFactory(new PropertyValueFactory<>("transmission"));

        TableColumn<Car, String> brand_column = new TableColumn<>("brand");
        brand_column.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<Car, String> price_column = new TableColumn<>("price");
        price_column.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Car, String> car_Type_column = new TableColumn<>("car_Type");
        car_Type_column.setCellValueFactory(new PropertyValueFactory<>("car_Type"));

        TableColumn<Car, String> designer_column = new TableColumn<>("designer");
        designer_column.setCellValueFactory(new PropertyValueFactory<>("designer"));

        TableColumn<Car, String> hp_column = new TableColumn<>("hp");
        hp_column.setCellValueFactory(new PropertyValueFactory<>("hp"));

        TableColumn<Car, String> four_wheelDrive_column = new TableColumn<>("four_wheelDrive");
        four_wheelDrive_column.setCellValueFactory(new PropertyValueFactory<>("four_wheelDrive"));

        TableColumn<Car, String> fog_Light_column = new TableColumn<>("fog_Light");
        fog_Light_column.setCellValueFactory(new PropertyValueFactory<>("fog_Light"));

        TableColumn<Car, String> ignition_column = new TableColumn<>("ignition");
        ignition_column.setCellValueFactory(new PropertyValueFactory<>("ignition"));

        TableColumn<Car, String> door_column = new TableColumn<>("door");
        door_column.setCellValueFactory(new PropertyValueFactory<>("door"));

        TableColumn<Car, String> seat_column = new TableColumn<>("seat");
        seat_column.setCellValueFactory(new PropertyValueFactory<>("seat"));

        TableColumn<Car, String> salesman_id_column = new TableColumn<>("salesman_id");
        salesman_id_column.setCellValueFactory(new PropertyValueFactory<>("salesman_id"));

        TableColumn<Car, String> customer_id_column = new TableColumn<>("customer_id");
        customer_id_column.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

        TableColumn<Car, String> quantity_column = new TableColumn<>("quantity");
        quantity_column.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table.setItems(observableList);
        table.getColumns().addAll(chassis_column, engine_no_column, cc_column, color_column, transmission_column, brand_column, price_column, car_Type_column, designer_column, hp_column, four_wheelDrive_column, fog_Light_column, ignition_column, door_column, seat_column, salesman_id_column, customer_id_column, quantity_column);
    }
    public ObservableList<Car> get_carActivity_DB()
    {
        ObservableList<Car> carObservableList = FXCollections.observableArrayList();
        ArrayList<Car> carArrayList = dbConnect.get_Car_ArrayList();
        carObservableList.addAll(carArrayList);
        return carObservableList;
    }

    //    public BorderPane borderPane;
    private void carBrowserActivity(Scene backScene)       //Todo---------------- carBrowserActivity()
    {
        Parent carBrowserpanel_fxml = null;
        try
        {
            carBrowserpanel_fxml = FXMLLoader.load(getClass().getResource("CarBrowser.fxml"));
            Scene carBrowser_scene = new Scene(carBrowserpanel_fxml, 1280, 720);

            Button searchBy_Brand_bt = (Button) carBrowser_scene.lookup("#id_bt_searchByBrand");
            Button searchBy_Color_bt = (Button) carBrowser_scene.lookup("#id_bt_searchByColor");
            Button searchBy_Price_bt = (Button) carBrowser_scene.lookup("#id_bt_searchByPrice");
            Button back_bt = (Button) carBrowser_scene.lookup("#id_bt_back");
            back_bt.setOnAction(back_bt_e ->
            {
                primaryStage.setScene(backScene);
                primaryStage.show();
            });

            TextField color_filed = (TextField) carBrowser_scene.lookup("#id_fld_color");
            TextField maxPrice_filed = (TextField) carBrowser_scene.lookup("#id_fld_max_price");
            TextField minPrice_filed = (TextField) carBrowser_scene.lookup("#id_fld_min_price");
            TextField brand_filed = (TextField) carBrowser_scene.lookup("#id_fld_brand");
//            borderPane = (BorderPane) carBrowser_scene.lookup("#id_borderpane");

            get_carActivity_DB();

            searchBy_Brand_bt.setOnAction(searchBy_Brand_bt_e ->
            {
                if (!brand_filed.getText().isEmpty())
                {
                    carObservableList = dbConnect.get_Filtered_DB_Car_Query("car", "brand=\'" + brand_filed.getText() + "\'");
                    if (carObservableList.size() == 0)
                    {
                        AlertBox.alert_with_NO_Action("Sorry!", "No Match Found... :( \nPlease Check Spelling");
                        carObservableList = get_carActivity_DB();
                    }
                    carBrowserActivity(backScene);
                }

            });
            searchBy_Price_bt.setOnAction(searchBy_Color_bt_e ->
            {
                if (!maxPrice_filed.getText().isEmpty() && !minPrice_filed.getText().isEmpty())
                {
                    carObservableList = dbConnect.get_Filtered_DB_Car_Query("car", "price between " + minPrice_filed.getText() + " and " + maxPrice_filed.getText());
                    if (carObservableList.size() == 0)
                    {
                        AlertBox.alert_with_NO_Action("Sorry!", "No Match Found... :( \nPlease Check Spelling");
                        carObservableList = get_carActivity_DB();
                    }
                    carBrowserActivity(backScene);
                }
                if (!maxPrice_filed.getText().isEmpty())
                {
                    carObservableList = dbConnect.get_Filtered_DB_Car_Query("car", "price between 0 and " + maxPrice_filed.getText());
                    if (carObservableList.size() == 0)
                    {
                        AlertBox.alert_with_NO_Action("Sorry!", "No Match Found... :( \nPlease Check Spelling");
                        carObservableList = get_carActivity_DB();
                    }
                    carBrowserActivity(backScene);
                }
                if (!minPrice_filed.getText().isEmpty())
                {
                    carObservableList = dbConnect.get_Filtered_DB_Car_Query("car", "price between " + minPrice_filed.getText() + " and 12000000000");
                    if (carObservableList.size() == 0)
                    {
                        AlertBox.alert_with_NO_Action("Sorry!", "No Match Found... :( \nPlease Check Spelling");
                        carObservableList = get_carActivity_DB();
                    }
                    carBrowserActivity(backScene);
                }
            });
            searchBy_Color_bt.setOnAction(searchBy_Price_bt_e ->
            {
                if (!color_filed.getText().isEmpty())
                {
                    carObservableList = dbConnect.get_Filtered_DB_Car_Query("car", "Color=\'" + color_filed.getText() + "\'");
                    if (carObservableList.size() == 0)
                    {
                        AlertBox.alert_with_NO_Action("Sorry!", "No Match Found... :( \nPlease Check Spelling");
                        carObservableList = get_carActivity_DB();
                    }
                    carBrowserActivity(backScene);
                }
            });
            TableView<Car> carTableView = (TableView<Car>) carBrowser_scene.lookup("#id_table_carBrowser");
            set_up_Car_Table_NON_Editable(carTableView, carObservableList);
            primaryStage.setScene(carBrowser_scene);
            primaryStage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}