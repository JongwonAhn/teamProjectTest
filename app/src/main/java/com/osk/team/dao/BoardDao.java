package com.osk.team.dao;

import java.util.List;

import com.osk.team.domain.Board;
import com.osk.team.domain.BoardPhoto;


public interface BoardDao {

  int insert(Board board) throws Exception;

  int insertphoto(BoardPhoto photo) throws Exception;

  Board boardbno() throws Exception; // 오토 인크리먼츠값을 꺼내기 위해 만듬.

  List<Board> findByKeyword(String keyword) throws Exception;

  Board findByNo(int no) throws Exception;

  int update(Board board) throws Exception;

  int delete(int bno) throws Exception;

  int updateViewCount(int bno) throws Exception;
}


