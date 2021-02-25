package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
public class DataConnection {

    private static Connection connection;
    private static String JDBC_Driver = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "";
    private static String username = UserPass.getUsername();
    private static String password = UserPass.getPassword();

    public static void connect() {
        try{

            Class.forName(JDBC_Driver);
            connection = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Success");

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }

    public static Connection getConnection(){
        return connection;
    }

}
