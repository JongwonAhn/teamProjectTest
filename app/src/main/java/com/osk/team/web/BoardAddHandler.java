
package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.osk.team.domain.Board;
import com.osk.team.domain.Member;
import com.osk.team.domain.BoardPhoto;
import com.osk.team.service.BoardService;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@SuppressWarnings("serial")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/board/add")
public class BoardAddHandler extends HttpServlet {

  private String uploadDir;
  private List<Part> partList;          // 사진 리스트
  private List<BoardPhoto> Photos;       // 사진 정보 리스트
  @Override
  public void init() throws ServletException {
    this.uploadDir = this.getServletContext().getRealPath("/upload");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/jsp/board/form.jsp").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    BoardService boardService = (BoardService) request.getServletContext().getAttribute("boardService");
    partList=new ArrayList<Part>(); // 초기화
    Photos=new ArrayList<BoardPhoto>();
    Board b = new Board();

    request.setCharacterEncoding("UTF-8");
    b.setTitle(request.getParameter("title"));
    b.setContent(request.getParameter("content"));
    b.setViewCount(1);

    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"); // 날짜 포멧 형식 지정 및 생성
    //    System.out.println(sdf.format(d)); // 위에서 지정한 포멧 형식으로 날짜 출력
    b.setRegisteredDate(sdf.format(d));
    b.setBoardTypeNo(1);

    ///-----사진처리
    if(request.getPart("photo1").getSize()>0){
      partList.add(request.getPart("photo1")); // 사진값을 리스트에 순서대로 담아둠.
      //      System.out.println("1111111111111"+request.getPart("photo1")); //출력테스트
    }
    if(request.getPart("photo2").getSize()>0){
      partList.add(request.getPart("photo2"));
    }
    if(request.getPart("photo3").getSize()>0){
      partList.add(request.getPart("photo3"));
    }

    for(int i=0; i<partList.size(); i++) { // 리스트 수만큼 반복문 돌림
      if (partList.get(i).getSize() > 0) { // 해당 번째에 사이즈 있느냐 없느냐로, 사진이 들어갔나 체크
        Photos.add(new BoardPhoto()); // 사진 정보를 생성해서 사진 정보 리스트에 순서대로 넣음
        // 파일을 선택해서 업로드 했다면,
        String filename = UUID.randomUUID().toString();
        partList.get(i).write(this.uploadDir + "/" + filename);
        Photos.get(i).setPhoto(filename); //만들어진 사진 정보를 get해서 그 값을 set으로 바로 넣어줌

        // 썸네일 이미지 생성
        Thumbnails.of(this.uploadDir + "/" + filename)
        .size(250, 250)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_250x250";
          }
        });
      }
      //사진처리----
    }

    HttpServletRequest httpRequest = request;
    Member loginUser = (Member) httpRequest.getSession().getAttribute("loginUser");
    b.setWriter(loginUser);

    try {
      boardService.add(b);// 게시판 생성
      //        System.out.println("bno확인" + boardService.getBoardBno()); //1. 게시글을 등록하고
      int p_bno=boardService.getBoardBno().getNo(); // 같은 p_bno 번호 셋팅
      for(int i=0; i<Photos.size(); i++) { // 반복문 돌려서 갯수만큼 db에 저장.
        Photos.get(i).setBno(p_bno); //2. 등록된 게시글의  bno값을 출력해서 넣어줌 사진bno값 넣어줌
        boardService.addWithPhoto(Photos.get(i));// 게시판 사진 등록 3. 사진bno값과 이름값 함께 insert 해줌
      }
      response.sendRedirect("list");

    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);

    }
  }
}





