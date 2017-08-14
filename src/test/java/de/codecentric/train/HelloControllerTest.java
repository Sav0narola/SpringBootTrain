package de.codecentric.train;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class HelloControllerTest {

    private HelloController helloController = new HelloController();

    @Test
    public void indexAnswersWithHello() {
        String result = helloController.getIndex();
        assertThat(result, is("hello"));
    }


}