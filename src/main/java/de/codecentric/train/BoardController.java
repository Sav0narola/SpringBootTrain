package de.codecentric.train;

import de.codecentric.train.boards.Board;
import de.codecentric.train.boards.BoardRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class BoardController {
    private static final Logger LOG = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    @RequestMapping("/boards")
    public List<Board> getBoards() {
        try {
            return boardService.getBoards();
        } catch (BoardRequestException e) {
            LOG.error(e.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

}
