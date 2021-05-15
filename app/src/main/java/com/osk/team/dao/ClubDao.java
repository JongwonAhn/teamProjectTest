package com.osk.team.dao;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;

import java.util.List;
import java.util.Map;

public interface ClubDao {

    int insert(Club club) throws Exception;

    Club findByNo(int cno) throws Exception;

    int update(Club club) throws Exception;

    int delete(int cno) throws Exception;

    List<Club> findByKeyword(String keyword) throws Exception;//새로만듬



    int insertMember(Map<String,Object> params) throws Exception;

    List<Member> findAllMembers(int clubNo) throws Exception;

    int deleteMember(int memberNo) throws Exception;

    int insertMembers(Map<String,Object> params) throws Exception;//맴버 업데이트용



    int insertPhoto(Map<String,Object> params) throws Exception;

    List<String> findAllPhotos(int photoNo) throws Exception;

    int deletePhoto(int photoNo) throws Exception;

    int insertPhotos(Map<String,Object> params) throws Exception;//사진 업데이트용


    // 로그인 된 유저가 클럽 참여 -> 멤버 추가
}