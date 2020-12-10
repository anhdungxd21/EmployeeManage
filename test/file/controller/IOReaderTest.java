package file.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IOReaderTest {
    @Test
    void readFile(){
        IOReader ioReader = IOReader.getInstance();
        ioReader.setPath("data/employee.txt");
        ioReader.readFile(1);
    }
}