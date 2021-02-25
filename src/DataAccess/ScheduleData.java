package DataAccess;

import Application.DataTypes.Schedule;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ScheduleData {

    private static Statement statement;
    private static ArrayList<Schedule> schedules;

    public static ArrayList<Schedule> getSchedules() {

        schedules = new ArrayList<>();

        try{

            statement = DataConnection.getConnection().createStatement();
            ResultSet rs =statement.executeQuery("SELECT * FROM schedule");

            if(rs != null){
                while (rs.next()){
                    Schedule schedule = new Schedule();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    schedule.setScheduleID(rs.getInt(1));
                    schedule.setDepartureDate(rs.getDate(2));
                    schedule.setDepartureTime(rs.getString(3));
                    schedule.setArrivalDate(rs.getDate(4));
                    schedule.setArrivalTime(rs.getString(5));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return schedules;
    }

    public static int insertSchedule(Schedule schedule){
        try {
            statement.executeUpdate("INSERT INTO schedule VALUE(default, '" +
                    schedule.getDepartureDate() + "', '" +
                    schedule.getDepartureTime() + "', '" +
                    schedule.getArrivalDate() + "', '" +
                    schedule.getArrivalTime() + "');"
                    , Statement.RETURN_GENERATED_KEYS); //pripremi autoincrement vrijednost za dohvat
            ResultSet generatedKeys = statement.getGeneratedKeys(); //dohvata
            if (generatedKeys.next()) {//prelazak na prvi iterator
                return generatedKeys.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
