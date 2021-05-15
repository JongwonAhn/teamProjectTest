package com.osk.team.dao;

import java.util.List;
import com.osk.team.domain.Hotplace;

public interface HotplaceDao {

    int insert(Hotplace hotplace) throws Exception;

    List<Hotplace> findByKeyword(String keyword) throws Exception;

    int update(Hotplace hotplace) throws Exception;

    int delete(int hno) throws Exception;

}