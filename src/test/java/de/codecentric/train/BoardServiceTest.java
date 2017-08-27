package de.codecentric.train;


import de.codecentric.train.boards.Board;
import de.codecentric.train.boards.BoardRequestException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BoardServiceTest {
    private static final String ANY_BOARD_NAME = "My board";
    private static final String ANY_ID = "anyId";
    private static final String FIREBASE_URL = "https://goodurl.firebaseio.com/boards.json";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FirebaseBoardService boardService;

    @Before
    public void setup() {
        Field boardsUrlField = ReflectionUtils.findField(FirebaseBoardService.class, "boardsUrl");
        ReflectionUtils.makeAccessible(boardsUrlField);
        ReflectionUtils.setField(boardsUrlField, boardService, FIREBASE_URL);
    }

    @Test
    public void getBoardsReturnsListOfBoards() throws BoardRequestException {
        List<Board> boards = boardService.getBoards();
        assertThat(boards).isInstanceOf(List.class);
    }

    @Test
    public void getBoardUsesRestTemplateToRequestForBoards() throws BoardRequestException {
        boardService.getBoards();

        verify(restTemplate).exchange(FIREBASE_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<Map<String, Board>>() {
                });
    }

    @Test
    public void responseIsTranslatedIntoList() throws BoardRequestException {
        Board testBoard = new Board(ANY_ID, ANY_BOARD_NAME);

        when(restTemplate.exchange(
                anyString(), any(), any(), eq(new ParameterizedTypeReference<Map<String, Board>>() {
                })))
                .thenReturn(createResponseEntityWithGivenBoard(testBoard));

        List<Board> actualBoards = boardService.getBoards();

        assertThat(actualBoards).contains(testBoard);
    }

    private ResponseEntity<Map<String, Board>> createResponseEntityWithGivenBoard(Board testBoard) {
        Map<String, Board> boards = new HashMap<>();
        boards.put("anyKey", testBoard);

        return new ResponseEntity<>(boards, HttpStatus.OK);
    }


}