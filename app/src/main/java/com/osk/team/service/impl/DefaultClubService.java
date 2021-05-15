package com.osk.team.service.impl;

import com.osk.mybatis.TransactionCallback;
import com.osk.mybatis.TransactionManager;
import com.osk.mybatis.TransactionTemplate;
import com.osk.team.dao.ClubDao;
import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.service.ClubService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultClubService implements ClubService {

    TransactionTemplate transactionTemplate;
    ClubDao clubDao;

    public DefaultClubService(TransactionManager txManager, ClubDao clubDao) {
        this.transactionTemplate = new TransactionTemplate(txManager);
        this.clubDao = clubDao;
    }

    @Override
    public int add(Club club) throws Exception {
        return clubDao.insert(club);
    }

    @Override
    //현재클럽가입 인원
    public List<Club> listOfMember() throws Exception {
        return clubDao.findByKeyword(null);
    }

    @Override
    public Club get(int no) throws Exception {
        return clubDao.findByNo(no);
    }

    @Override
    public int update(Club club) throws Exception {
        return clubDao.update(club);
    }

    @Override
    public int delete(int no) throws Exception {
        return clubDao.delete(no);
    }

    @Override
    public List<Club> search(String keyword) throws Exception {//새로 만듬
        return clubDao.findByKeyword(keyword);
    }

    @Override
    public int deleteMember(int clubNo) throws Exception {
        return clubDao.deleteMember(clubNo);
    }

    @Override
    public int updateMember(int clubNo, List<Member> members) throws Exception {
        return (int) transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction() throws Exception {
                clubDao.deleteMember(clubNo);

                HashMap<String,Object> params = new HashMap<>();
                params.put("clubNo", clubNo);
                params.put("members", members);

                return clubDao.insertMember(params);
            }
        });
    }

    @Override
    public int deletePhoto(int clubNo) throws Exception {
        return clubDao.deletePhoto(clubNo);
    }

    @Override
    public int updatePhoto(int clubNo, List<String> photos) throws Exception {
        return (int) transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction() throws Exception {
                clubDao.deletePhoto(clubNo);

                HashMap<String,Object> params = new HashMap<>();
                params.put("clubNo", clubNo);
                params.put("photos", photos);

                return clubDao.insertMember(params);
            }
        });
    }
}
