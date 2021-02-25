package DataAccess;

import Application.DataTypes.Plane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlaneData {

    private static Statement statement;
    private static ObservableList<Plane> planes;

    public static ObservableList<Plane> getPlanes() {

        planes = FXCollections.observableArrayList();

        try{

            statement = DataConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM plane");

            if(rs != null){
                while(rs.next()){
                    Plane plane = new Plane();
                    plane.setPlaneID(rs.getInt(1));
                    plane.setFirstClass(rs.getInt(2));
                    plane.setCoach(rs.getInt(3));
                    plane.setEconomy(rs.getInt(4));
                    plane.setPlaneName(rs.getString(5));

                    planes.add(plane);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return planes;
    }

    public static void insertPlanes(Plane plane) {
        try{
            statement.executeUpdate("INSERT INTO plane VALUE (default, " +
                                        plane.getFirstClass() + ", " +
                                        plane.getCoach() + ", " +
                                        plane.getEconomy() + ", '" +
                                        plane.getPlaneName() + "');");
            planes.add(plane);
            plane.setPlaneID(planes.indexOf(plane) + 1);
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    //metoda za azuriranje detalja o avionima
    public static void updatePlane(Plane plane){
        try{
            statement.executeUpdate("UPDATE plane SET planeName = '" + plane.getPlaneName() +
                                        "', firstClass = " + plane.getFirstClass() +
                                        ", coach = " + plane.getCoach() +
                                        ", economy = " + plane.getEconomy() +
                                        " WHERE planeID = " + plane.getPlaneID() + ";");
            planes.set(plane.getPlaneID() - 1, plane);
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    //metoda izvoza popisa aviona
    public static void exportPlanes() {
        String filePath = "C:\\test\\test2.txt";
        try{
            FileOutputStream out = new FileOutputStream(filePath);
            for(Plane p : planes)
            {
                String planesData = p.toString();
                out.write(planesData.getBytes());
            }
            out.close();


        }catch (Exception e){}


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Popis aviona je izvezen!");
        alert.setHeaderText("Success!");
        alert.showAndWait();
    }

}
