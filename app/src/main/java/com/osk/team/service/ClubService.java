package com.osk.team.service;

import com.osk.team.domain.Board;
import com.osk.team.domain.Club;
import com.osk.team.domain.Member;

import java.util.List;
import java.util.Map;

public interface ClubService {

    int add(Club club) throws Exception;

    List<Club> listOfMember() throws Exception;//현재 참여 멤버

    Club get(int no) throws Exception;

    List<Club> search(String keyword) throws Exception;

    int update(Club club) throws Exception;

    int delete(int no) throws Exception;

    int deleteMember(int clubNo) throws Exception;

    int updateMember(int projectNo, List<Member> members) throws Exception;

    int deletePhoto(int clubNo) throws Exception;

    int updatePhoto(int projectNo, List<String> members) throws Exception;

    // 로그인 된 유저가 클럽 참여 -> 멤버 추가
}
