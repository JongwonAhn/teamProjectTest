package com.osk.team.dao;

import com.osk.team.domain.Board;

import java.util.List;


public interface BoardDao {

        int insert(Board board) throws Exception;

        List<Board> findByKeyword(String keyword) throws Exception;

        Board findByNo(int no) throws Exception;

        int update(Board board) throws Exception;

        int delete(int bno) throws Exception;

        int updateViewCount(int bno) throws Exception;
}


