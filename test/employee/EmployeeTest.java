package employee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testToString() {
        Employee employee = new Employee("dung","GD",0);
        String[] expect = {"dung","GD","0"};
        String[] result = employee.toString().split(",");
        assertArrayEquals(expect,result);
    }
}