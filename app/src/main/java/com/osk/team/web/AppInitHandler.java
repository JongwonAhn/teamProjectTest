package com.osk.team.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.osk.mybatis.MybatisDaoFactory;
import com.osk.mybatis.SqlSessionFactoryProxy;
import com.osk.mybatis.TransactionManager;
import com.osk.team.dao.BoardDao;
import com.osk.team.dao.ClubDao;
import com.osk.team.dao.HotplaceDao;
import com.osk.team.dao.MemberDao;
import com.osk.team.dao.QnaDao;
import com.osk.team.service.BoardService;
import com.osk.team.service.ClubService;
import com.osk.team.service.HotplaceService;
import com.osk.team.service.MemberService;
import com.osk.team.service.QnaService;
import com.osk.team.service.impl.DefaultBoardService;
import com.osk.team.service.impl.DefaultClubService;
import com.osk.team.service.impl.DefaultHotplaceService;
import com.osk.team.service.impl.DefaultMemberService;
import com.osk.team.service.impl.DefaultQnaService;

@WebServlet(value = "/init", loadOnStartup = 1)
public class AppInitHandler implements Servlet {

  @Override
  public void init(ServletConfig config) throws ServletException {

    try {
      // 1) Mybatis 관련 객체 준비
      InputStream mybatisConfigStream = Resources.getResourceAsStream(
          "com/osk/team/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfigStream);
      SqlSessionFactoryProxy sqlSessionFactoryProxy = new SqlSessionFactoryProxy(sqlSessionFactory);

      // 2) DAO 관련 객체 준비
      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactoryProxy);
      BoardDao boardDao = daoFactory.createDao(BoardDao.class);
      ClubDao clubDao = daoFactory.createDao(ClubDao.class);
      HotplaceDao hotplaceDao = daoFactory.createDao(HotplaceDao.class);
      MemberDao memberDao = daoFactory.createDao(MemberDao.class);
      QnaDao qnaDao = daoFactory.createDao(QnaDao.class);

      // 3) 서비스 관련 객체 준비
      TransactionManager txManager = new TransactionManager(sqlSessionFactoryProxy);

      BoardService boardService = new DefaultBoardService(boardDao);
      ClubService clubService = new DefaultClubService(txManager, clubDao);
      HotplaceService hotplaceService = new DefaultHotplaceService(hotplaceDao);
      MemberService memberService = new DefaultMemberService(memberDao);
      QnaService qnaService = new DefaultQnaService(qnaDao);

      // 4) 서비스 객체를 ServletContext 보관소에 저장한다.
      ServletContext servletContext = config.getServletContext();

      servletContext.setAttribute("boardService", boardService);
      servletContext.setAttribute("clubService", clubService);
      servletContext.setAttribute("hotplaceService", hotplaceService);
      servletContext.setAttribute("memberService", memberService);
      servletContext.setAttribute("qnaService", qnaService);

      System.out.println("의존 객체를 모두 준비하였습니다2.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void destroy() {
  }

  @Override
  public ServletConfig getServletConfig() {
    return null;
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
  }
}
