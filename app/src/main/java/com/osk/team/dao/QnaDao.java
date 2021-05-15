package com.osk.team.dao;

import java.util.List;
import com.osk.team.domain.Qna;

public interface QnaDao {

  int insert(Qna qna) throws Exception;

  List<Qna> listAll() throws Exception;

  Qna findByNo(int no) throws Exception;

  int update(Qna qna) throws Exception;

  int delete(int no) throws Exception;

  List<Qna> findByKeyword(String keyword) throws Exception;

  // 로그인 된 유저가 클럽 참여 -> 멤버 추가
}
