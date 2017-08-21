package de.codecentric.train;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelloControllerTest {

    private HelloController helloController = new HelloController();

    @Test
    public void testFoo(){
        helloController.getIndex();
    }
}