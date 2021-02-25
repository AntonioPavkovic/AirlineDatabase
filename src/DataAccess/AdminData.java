package DataAccess;

import Application.DataTypes.Admin;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminData {

    private static ArrayList<Admin> admins;
    private static Statement statement;

    public static ArrayList<Admin> getAdmins(){
        admins = new ArrayList<>();

        try{
            statement = DataConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM admin");

            if(rs != null)
                while (rs.next()) {
                    Admin admin = new Admin();
                    admin.setAdminID(rs.getInt(1));
                    admin.setFirstName(rs.getString(2));
                    admin.setLastName(rs.getString(3));
                    admin.setPassword(rs.getString(4));

                    admins.add(admin);
                }
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return admins;
    }

}
