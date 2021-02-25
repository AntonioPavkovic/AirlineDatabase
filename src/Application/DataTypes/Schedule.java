package Application.DataTypes;

import java.util.Date;

public class Schedule {

    private int scheduleID;
    private Date departureDate;
    private String departureTime;
    private Date arrivalDate;
    private String arrivalTime;

    public Schedule(){
        departureDate = new Date();
        arrivalDate = new Date();
        departureTime = "7:00";
        arrivalTime = "8:00";
    }

    public Schedule(int scheduleID, Date departureDate, String departureTime, Date arrivalDate, String arrivalTime) {
        this.scheduleID = scheduleID;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleID=" + scheduleID +
                ", departureDate=" + departureDate +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }

}
