package Niloy;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Gigabyte on 7/30/2017.
 */
public class InsertHelper
{
    private DBConnect dbConnect;

    public InsertHelper(DBConnect dbConnect)
    {
        this.dbConnect = dbConnect;
    }

    private boolean response;

    public boolean addCar()
    {
        try
        {
            response = false;
            Stage windowStage = new Stage();
            windowStage.getIcons().add(new Image("assets/icon.png"));
            windowStage.initModality(Modality.APPLICATION_MODAL);
            Parent parentFXML = FXMLLoader.load(getClass().getResource("AddCar.fxml"));
            Scene scene = new Scene(parentFXML, 413, 760);
            windowStage.getIcons().add(new Image("assets/icon.png"));
            windowStage.initModality(Modality.APPLICATION_MODAL);
            windowStage.setTitle("OTTO SPARK");
            windowStage.setScene(scene);
            TextField Chassis = (TextField) scene.lookup("#Chassis");
            TextField Engine_no = (TextField) scene.lookup("#Engine_no");
            TextField CC = (TextField) scene.lookup("#CC");
            TextField Color = (TextField) scene.lookup("#Color");
            TextField Transmission = (TextField) scene.lookup("#Transmission");
            TextField Brand = (TextField) scene.lookup("#Brand");
            TextField Price = (TextField) scene.lookup("#Price");
            TextField Car_type = (TextField) scene.lookup("#Car_type");
            TextField Designer = (TextField) scene.lookup("#Designer");
            TextField HP = (TextField) scene.lookup("#HP");
            TextField fourwheel_drive = (TextField) scene.lookup("#fourwheel_drive");
            TextField Fog_light = (TextField) scene.lookup("#Fog_light");
            TextField Ignition = (TextField) scene.lookup("#Ignition");
            TextField Door = (TextField) scene.lookup("#Door");
            TextField Seat = (TextField) scene.lookup("#Seat");
            TextField Salesman_id = (TextField) scene.lookup("#Salesman_id");
            TextField Customer_id = (TextField) scene.lookup("#Customer_id");
            TextField Quantity = (TextField) scene.lookup("#Quantity");

            Button bt_discard = (Button) scene.lookup("#id_cancel_bt");
            bt_discard.setOnAction(event ->
            {
                response = false;
                windowStage.close();
            });

            Button id_add_bt = (Button) scene.lookup("#id_add_bt");
            id_add_bt.setOnAction(event ->
            {
                try
                {
                    String _s = "INSERT INTO car VALUES (" + Chassis.getText() + "," + Engine_no.getText() + "," + CC.getText() + ",\'" + Color.getText() + "\',\'" + Transmission.getText() + "\',\'" + Brand.getText() + "\'," + Price.getText() + ",\'" + Car_type.getText() + "\',\'" + Designer.getText() + "\'," + HP.getText() + ",\'" + fourwheel_drive.getText() + "\',\'" + Fog_light.getText() + "\',\'" + Ignition.getText() + "\'," + Door.getText() + "," + Seat.getText() + "," + Salesman_id.getText() + "," + Customer_id.getText() + "," + Quantity.getText() + ")";
                    System.out.println(_s);
                    dbConnect.statement.executeUpdate(_s);
                    response = true;

                } catch (SQLException e)
                {
                    response = false;
                    e.printStackTrace();
                } finally
                {
                    windowStage.close();
                }

            });

            windowStage.showAndWait();
        } catch (Exception e)
        {
            response = false;
            AlertBox.alert_with_NO_Action("Error", "Error " + e);
            System.out.println(e);
        }
        return response;
    }


    public boolean addCustomer()
    {
        try
        {
            response = false;
            Stage windowStage = new Stage();
            windowStage.getIcons().add(new Image("assets/icon.png"));
            windowStage.initModality(Modality.APPLICATION_MODAL);
            Parent parentFXML = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
            Scene scene = new Scene(parentFXML, 413, 441);
            windowStage.getIcons().add(new Image("assets/icon.png"));
            windowStage.setTitle("OTTO SPARK");
            windowStage.setScene(scene);

            TextField n_id = (TextField) scene.lookup("#n_id");
            TextField Name = (TextField) scene.lookup("#Name");
            TextField Address = (TextField) scene.lookup("#Address");
            TextField Phone_no = (TextField) scene.lookup("#Phone_no");
            TextField Payment_type = (TextField) scene.lookup("#Payment_type");
            TextField Cash_amount = (TextField) scene.lookup("#Cash_amount");
            TextField No_of_installments = (TextField) scene.lookup("#No_of_installments");
            TextField Premium = (TextField) scene.lookup("#Premium");

            Button bt_discard = (Button) scene.lookup("#id_cancel_bt");
            bt_discard.setOnAction(event ->
            {
                response = false;
                windowStage.close();
            });

            Button id_add_bt = (Button) scene.lookup("#id_add_bt");
            id_add_bt.setOnAction(event ->
            {
                try
                {
                    String _s = "INSERT INTO customer VALUES " + "(\'" + n_id.getText() + "\',\'" + Name.getText() + "\',\'" + Address.getText() + "\'," + Phone_no.getText() + ",\'" + Payment_type.getText() + "\'," + Cash_amount.getText() + "," + No_of_installments.getText() + "," + Premium.getText() + ")";
                    System.out.println(_s);
                    dbConnect.statement.executeUpdate(_s);
                    response = true;
                } catch (SQLException e)
                {
                    response = false;
                    e.printStackTrace();
                } finally
                {
                    windowStage.close();
                }

            });

            windowStage.showAndWait();
        } catch (Exception e)
        {
            response = false;
            AlertBox.alert_with_NO_Action("Error", "Error " + e);
            System.out.println(e);
        }
        return response;
    }

    public boolean addEmployee()
    {
        try
        {
            response = false;
            Stage windowStage = new Stage();
            windowStage.getIcons().add(new Image("assets/icon.png"));
            windowStage.initModality(Modality.APPLICATION_MODAL);
            Parent parentFXML = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
            Scene scene = new Scene(parentFXML, 413, 441);
            windowStage.getIcons().add(new Image("assets/icon.png"));
            windowStage.setTitle("OTTO SPARK");
            windowStage.setScene(scene);


            Button bt_discard = (Button) scene.lookup("#id_cancel_bt");
            bt_discard.setOnAction(event ->
            {
                response = false;
                windowStage.close();
            });

            TabPane tabPane = (TabPane) scene.lookup("#id_tabpane");
            ObservableList<Tab> tabObservableLis = tabPane.getTabs();


//            Button id_add_bt = (Button) scene.lookup("#id_add_bt");

//                try
//                {
//                    String _s = "INSERT INTO car VALUES (" + Chassis.getText() + "," + Engine_no.getText() + "," + CC.getText() + ",\'" + Color.getText() + "\',\'" + Transmission.getText() + "\',\'" + Brand.getText() + "\'," + Price.getText() + ",\'" + Car_type.getText() + "\',\'" + Designer.getText() + "\'," + HP.getText() + ",\'" + fourwheel_drive.getText() + "\',\'" + Fog_light.getText() + "\',\'" + Ignition.getText() + "\'," + Door.getText() + "," + Seat.getText() + "," + Salesman_id.getText() + "," + Customer_id.getText() + "," + Quantity.getText() + ")";
//                    System.out.println(_s);
//                    dbConnect.statement.executeUpdate(_s);
//                    response = true;
//
//                } catch (SQLException e)
//                {
//                    response = false;
//                    e.printStackTrace();
//                } finally
//                {
//                    windowStage.close();
//                }


            TextField nid=null;
            TextField name=null;
            TextField address=null;
            TextField dob=null;
            TextField sex=null;
            TextField salary=null;
            TextField password=null;
            TextField commission=null;
            TextField s_ty=null;
            TextField g_loc=null;
            TextField c_ty=null;
            TextField mec_grd=null;
            Button id_add_bt;


            for (int i = 0; i < tabObservableLis.size(); i++)
            {
                Tab tab = tabObservableLis.get(i);
                if (tab.getId().equals("id_anc_manager"))
                {
                    id_add_bt  = (Button) scene.lookup("#id_add_bt");

                    id_add_bt.setOnAction(id_add_bt_e ->
                    {
                        System.out.println("id_anc_mang");
                    });

                } else if (tab.getId().equals("id_anc_salesman"))
                {
                    id_add_bt  = (Button) scene.lookup("#id_add_bt");

                    id_add_bt.setOnAction(id_add_bt_e ->
                    {
                        System.out.println("id_anc_salesman");
                    });

                } else if (tab.getId().equals("id_anc_staff"))
                {

                    id_add_bt  = (Button) scene.lookup("#id_add_bt");


                    id_add_bt.setOnAction(id_add_bt_e ->
                    {
                        System.out.println("id_anc_staff");
                    });

                }
            }
//            Tab managerTab = (Tab) scene.lookup("#id_anc_manager");
//            TabPane salesmanTab = (TabPane) scene.lookup("#id_anc_salesman");
//            TabPane staffTab = (TabPane) scene.lookup("#id_anc_staff");
//
//
//            if (managerTab.isDisabled())
//            {
//                System.out.println("manager");
//            } else if (salesmanTab.isFocused())
//            {
//                System.out.println("salesman");
//            } else if (staffTab.isFocused())
//            {
//                System.out.println("staff");
//            }

//            Tab tab = tabPane.getSelectionModel().getSelectedItem();


            //            TextField Chassis = (TextField) scene.lookup("#Chassis");
//            TextField Engine_no = (TextField) scene.lookup("#Engine_no");
//            TextField CC = (TextField) scene.lookup("#CC");
//            TextField Color = (TextField) scene.lookup("#Color");
//            TextField Transmission = (TextField) scene.lookup("#Transmission");
//            TextField Brand = (TextField) scene.lookup("#Brand");
//            TextField Price = (TextField) scene.lookup("#Price");
//            TextField Car_type = (TextField) scene.lookup("#Car_type");
//            TextField Designer = (TextField) scene.lookup("#Designer");
//            TextField HP = (TextField) scene.lookup("#HP");
//            TextField fourwheel_drive = (TextField) scene.lookup("#fourwheel_drive");
//            TextField Fog_light = (TextField) scene.lookup("#Fog_light");
//            TextField Ignition = (TextField) scene.lookup("#Ignition");
//            TextField Door = (TextField) scene.lookup("#Door");
//            TextField Seat = (TextField) scene.lookup("#Seat");
//            TextField Salesman_id = (TextField) scene.lookup("#Salesman_id");
//            TextField Customer_id = (TextField) scene.lookup("#Customer_id");
//            TextField Quantity = (TextField) scene.lookup("#Quantity");


            windowStage.showAndWait();
        } catch (Exception e)
        {
            response = false;
            AlertBox.alert_with_NO_Action("Error", "Error " + e);
            System.out.println(e);
            e.printStackTrace();
        }
        return response;

    }
}
