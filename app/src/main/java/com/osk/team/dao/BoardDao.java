package com.osk.team.dao;

import java.util.List;

import com.osk.team.domain.Board;


public interface BoardDao {

  int insert(Board board) throws Exception;

  int insert(Photo photo) throws Exception;

  List<Board> findByKeyword(String keyword) throws Exception;

  Board findByNo(int no) throws Exception;

  int update(Board board) throws Exception;

  int delete(int bno) throws Exception;

  int updateViewCount(int bno) throws Exception;
}


