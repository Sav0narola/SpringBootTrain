package de.codecentric.train;


import de.codecentric.train.boards.Board;
import de.codecentric.train.boards.BoardRequestException;
import de.codecentric.train.boards.Randomizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BoardServiceTest {

    @Mock
    private Randomizer randomizer;

    @InjectMocks
    private BoardService boardService;

    @Test
    public void getBoardsReturnsListOfBoards() throws BoardRequestException {
        List<Board> boards = boardService.getBoards();
        assertThat(boards).isInstanceOf(List.class);
    }

    @Test
    public void getBoardsReturnsExactlyOneEntry() throws BoardRequestException {
        List<Board> boards = boardService.getBoards();
        assertThat(boards).hasSize(1);
    }

    @Test
    public void getBoardsReturnsEntryWithNameTestBoard() throws BoardRequestException {
        List<Board> boards = boardService.getBoards();
        assertThat(boards.get(0).getName()).isEqualTo("TestBoard");
    }

    @Test
    public void getBoardsUsesRandomizerToSimulateTimeoutExceptions() throws BoardRequestException, TimeoutException {
        boardService.getBoards();
        verify(randomizer).checkForExceptions();
    }

    @Test(expected = BoardRequestException.class)
    public void TimeoutExceptionsAreTransformedIntoBoardRequestExceptions() throws BoardRequestException, TimeoutException {
        doThrow(TimeoutException.class).when(randomizer).checkForExceptions();
        boardService.getBoards();
    }

}
