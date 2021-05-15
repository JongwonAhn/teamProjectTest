package com.osk.team.service.impl;



import java.util.List;

import com.osk.team.dao.BoardDao;
import com.osk.team.domain.Board;
import com.osk.team.service.BoardService;

public class DefaultBoardService implements BoardService {

  BoardDao boardDao;

  public DefaultBoardService(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public int add(Board board) throws Exception {
    return boardDao.insert(board);
  }

  @Override
  public List<Board> list() throws Exception {
    return boardDao.findByKeyword(null);
  }

  @Override
  public Board get(int no) throws Exception {
    Board board = boardDao.findByNo(no);
    if (board != null) {
      boardDao.updateViewCount(no);
    }
    return board;
  }

  @Override
  public int update(Board board) throws Exception {
    return boardDao.update(board);
  }

  @Override
  public int delete(int no) throws Exception {
    return boardDao.delete(no);
  }

  @Override
  public List<Board> search(String keyword) throws Exception {
    return boardDao.findByKeyword(keyword);
  }
}