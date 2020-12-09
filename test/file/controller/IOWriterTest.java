package file.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IOWriterTest {
    @Test
    void writeFile(){
        IOWriter ioWriter = IOWriter.getInstance("test/demo.txt");
        String[] string = {"tu","hoang","dung"};
        ioWriter.writeFile(string);
    }
}