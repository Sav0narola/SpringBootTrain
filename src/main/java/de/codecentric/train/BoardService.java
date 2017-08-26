package de.codecentric.train;

import de.codecentric.train.boards.Board;
import de.codecentric.train.boards.BoardRequestException;

import java.util.List;

public interface BoardService {

    List<Board> getBoards() throws BoardRequestException;
}
