package com.osk.team.service;

import com.osk.team.domain.Qna;

import java.util.List;

public interface QnaService {

    int add(Qna qna) throws Exception;

    List<Qna> listAll() throws Exception;

    Qna get(int no) throws Exception;

    int update(Qna qna) throws Exception;

    int delete(int no) throws Exception;

}
