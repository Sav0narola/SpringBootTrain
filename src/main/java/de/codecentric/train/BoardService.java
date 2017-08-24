package de.codecentric.train;

import de.codecentric.train.boards.Board;
import de.codecentric.train.boards.BoardRequestException;
import de.codecentric.train.boards.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;


@Service
public class BoardService {

    @Autowired
    private Randomizer randomizer;

    public List<Board> getBoards() throws BoardRequestException {
        try {
            List<Board> boards = new ArrayList<>();
            Board testBoard = new Board("1", "TestBoard");
            boards.add(testBoard);
            validateException();
            return boards;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void validateException() throws BoardRequestException {
        try {
            randomizer.checkForExceptions();
        } catch (TimeoutException e) {
            throw new BoardRequestException("This is the Test-Exception");
        }
    }
}
