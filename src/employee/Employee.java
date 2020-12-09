package employee;

public class Employee {
    private  final String COMMA = ",";
    private int id;
    private String name;
    private String position;
    private int dayInWork;
    public Employee(int id, String name, String position,int dayInWork){
        this.id = id;
        this.name = name;
        this.position = position;
        this.dayInWork = dayInWork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return this.id + COMMA + this.name + COMMA + this.position + COMMA + this.dayInWork;
    }
}
