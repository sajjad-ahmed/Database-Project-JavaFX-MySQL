package Niloy;

/**
 * Created by Gigabyte on 7/28/2017.
 */
public class Car

{
    public String chassis;
    public String engine_no;
    public String cc;
    public String color;
    public String transmission;
    public String brand;
    public String price;
    public String car_Type;
    public String designer;
    public String hp;
    public String four_wheelDrive;
    public String fog_Light;
    public String ignition;
    public String door;
    public String seat;
    public String salesman_id;
    public String customer_id;
    public String quantity;

    public Car(String chassis, String engine_no, String cc, String color, String transmission, String brand, String price, String car_Type, String designer, String hp, String four_wheelDrive, String fog_Light, String ignition, String door, String seat, String salesman_id, String customer_id, String quantity)
    {
        this.chassis = chassis;
        this.engine_no = engine_no;
        this.cc = cc;
        this.color = color;
        this.transmission = transmission;
        this.brand = brand;
        this.price = price;
        this.car_Type = car_Type;
        this.designer = designer;
        this.hp = hp;
        this.four_wheelDrive = four_wheelDrive;
        this.fog_Light = fog_Light;
        this.ignition = ignition;
        this.door = door;
        this.seat = seat;
        this.salesman_id = salesman_id;
        this.customer_id = customer_id;
        this.quantity = quantity;
    }

    public Car()
    {
        this.chassis = "";
        this.engine_no = "";
        this.cc = "";
        this.color = "";
        this.transmission = "";
        this.brand = "";
        this.price = "";
        this.car_Type = "";
        this.designer = "";
        this.hp = "";
        this.four_wheelDrive = "";
        this.fog_Light = "";
        this.ignition = "";
        this.door = "";
        this.seat = "";
        this.salesman_id = "";
        this.customer_id = "";
        this.quantity = "";
    }


    public String getChassis()
    {
        return chassis;
    }

    public void setChassis(String chassis)
    {
        this.chassis = chassis;
    }

    public String getEngine_no()
    {
        return engine_no;
    }

    public void setEngine_no(String engine_no)
    {
        this.engine_no = engine_no;
    }

    public String getCc()
    {
        return cc;
    }

    public void setCc(String cc)
    {
        this.cc = cc;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getTransmission()
    {
        return transmission;
    }

    public void setTransmission(String transmission)
    {
        this.transmission = transmission;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getCar_Type()
    {
        return car_Type;
    }

    public void setCar_Type(String car_Type)
    {
        this.car_Type = car_Type;
    }

    public String getDesigner()
    {
        return designer;
    }

    public void setDesigner(String designer)
    {
        this.designer = designer;
    }

    public String getHp()
    {
        return hp;
    }

    public void setHp(String hp)
    {
        this.hp = hp;
    }

    public String getFour_wheelDrive()
    {
        return four_wheelDrive;
    }

    public void setFour_wheelDrive(String four_wheelDrive)
    {
        this.four_wheelDrive = four_wheelDrive;
    }

    public String getFog_Light()
    {
        return fog_Light;
    }

    public void setFog_Light(String fog_Light)
    {
        this.fog_Light = fog_Light;
    }

    public String getIgnition()
    {
        return ignition;
    }

    public void setIgnition(String ignition)
    {
        this.ignition = ignition;
    }

    public String getDoor()
    {
        return door;
    }

    public void setDoor(String door)
    {
        this.door = door;
    }

    public String getSeat()
    {
        return seat;
    }

    public void setSeat(String seat)
    {
        this.seat = seat;
    }

    public String getSalesman_id()
    {
        return salesman_id;
    }

    public void setSalesman_id(String salesman_id)
    {
        this.salesman_id = salesman_id;
    }

    public String getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(String customer_id)
    {
        this.customer_id = customer_id;
    }

    public String getQuantity()
    {
        return quantity;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }
}
