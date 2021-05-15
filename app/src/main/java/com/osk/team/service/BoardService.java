package com.osk.team.service;

import com.osk.team.domain.Board;
import com.osk.team.domain.Club;

import java.util.List;

public interface BoardService {

    int add(Board board) throws Exception;

    List<Board> list() throws Exception;

    Board get(int no) throws Exception;

    int update(Board board) throws Exception;

    int delete(int no) throws Exception;

    List<Board> search(String keyword) throws Exception;
}