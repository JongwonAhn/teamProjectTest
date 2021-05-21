package com.osk.team.service;

import java.util.List;

import com.osk.team.domain.Board;
import com.osk.team.domain.BoardPhoto;

public interface BoardService {

  int add(Board board) throws Exception;

  int addWithPhoto(BoardPhoto photo) throws Exception;

  Board getBoardBno() throws Exception; // 오토 인크리먼츠값을 꺼내기 위해 만듬.

  List<Board> list() throws Exception;

  Board get(int no) throws Exception;

  int update(Board board) throws Exception;

  int delete(int no) throws Exception;

  List<Board> search(String keyword) throws Exception;
}