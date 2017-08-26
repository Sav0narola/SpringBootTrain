package de.codecentric.train;

import de.codecentric.train.boards.Board;
import de.codecentric.train.boards.BoardRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class FirebaseBoardService implements BoardService {

    @Value("${firebase.boardsUrl}")
    private String BOARDS_URL;

    private final ParameterizedTypeReference<Map<String, Board>> TYPE_REFERENCE =
    new ParameterizedTypeReference<Map<String, Board>>() {};

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Board> getBoards() throws BoardRequestException {
        ResponseEntity<Map<String, Board>> boards =
                restTemplate.exchange(BOARDS_URL, HttpMethod.POST,
                        null, TYPE_REFERENCE);
        if (boards != null) {
            return new ArrayList<>(boards.getBody().values());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

}
