package employee;

public class Employee {
    private  final String COMMA = ",";
    private String name;
    private String position;
    private int dayInWork;
    public Employee(String name, String position,int dayInWork){
        this.name = name;
        this.position = position;
        this.dayInWork = dayInWork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDayInWork() {
        return dayInWork;
    }

    public void setDayInWork(int dayInWork) {
        this.dayInWork = dayInWork;
    }

    @Override
    public String toString() {
        return this.name + COMMA + this.position + COMMA + this.dayInWork;
    }
}
