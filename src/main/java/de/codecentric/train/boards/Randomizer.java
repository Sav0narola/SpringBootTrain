package de.codecentric.train.boards;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;

@Component
public class Randomizer {

    public void checkForExceptions() throws TimeoutException {
        double random = Math.random();

        if (random >= 0.9d) {
            throw new TimeoutException("This is a simulated timeout");
        }
    }

}
