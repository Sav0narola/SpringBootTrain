package de.codecentric.train.boards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {

    private String boardId;
    private String name;


    public Board() {}

    public Board(String id, String name){
        this.boardId = id;
        this.name = name;
    }

    public String getName() {
        return boardId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
}
