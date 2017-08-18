package de.codecentric.train;

import de.codecentric.train.boards.Board;
import de.codecentric.train.boards.BoardRequestException;
import de.codecentric.train.boards.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private Randomizer randomizer;

    public List<Board> getBoards() throws BoardRequestException {
        return null;
    }

}
