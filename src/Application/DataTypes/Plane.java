package Application.DataTypes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plane {

    private IntegerProperty planeID, firstClass, coach, economy;
    private StringProperty planeName;

    public Plane() {
        this.planeID = new SimpleIntegerProperty(0);
        this.firstClass = new SimpleIntegerProperty(0);
        this.coach = new SimpleIntegerProperty(0);
        this.economy = new SimpleIntegerProperty(0);
        this.planeName = new SimpleStringProperty("");

    }

    public Plane(int planeID, int firstClass, int coach, int economy, String planeName) {
        this.planeID = new SimpleIntegerProperty(planeID);
        this.firstClass = new SimpleIntegerProperty(firstClass);
        this.coach = new SimpleIntegerProperty(coach);
        this.economy = new SimpleIntegerProperty(economy);
        this.planeName = new SimpleStringProperty(planeName);
    }

    public int getPlaneID() {
        return planeID.get();
    }

    public IntegerProperty planeIDProperty() {
        return planeID;
    }

    public void setPlaneID(int planeID) {
        this.planeID.set(planeID);
    }

    public int getFirstClass() {
        return firstClass.get();
    }

    public IntegerProperty firstClassProperty() {
        return firstClass;
    }

    public void setFirstClass(int firstClass) {
        this.firstClass.set(firstClass);
    }

    public int getCoach() {
        return coach.get();
    }

    public IntegerProperty coachProperty() {
        return coach;
    }

    public void setCoach(int coach) {
        this.coach.set(coach);
    }

    public int getEconomy() {
        return economy.get();
    }

    public IntegerProperty economyProperty() {
        return economy;
    }

    public void setEconomy(int economy) {
        this.economy.set(economy);
    }

    public String getPlaneName() {
        return planeName.get();
    }

    public StringProperty planeNameProperty() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName.set(planeName);
    }

    @Override
    public String toString() {
        return getPlaneID()+","+getPlaneName()+","+getCoach()+","+getEconomy()+","+getFirstClass()+"\n";
    }

}
