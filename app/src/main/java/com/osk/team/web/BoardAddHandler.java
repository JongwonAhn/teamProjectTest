package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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

  @Override
  public void init() throws ServletException {
    this.uploadDir = this.getServletContext().getRealPath("/upload");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {



    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>새 게시글</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>새 게시글</h1>");
    out.println("<form action='add' method='post' enctype='multipart/form-data'> ");

    out.println("제목: <input type='text' name='title'><br>");
    out.println("내용: <textarea name='content' rows='10' cols='60'></textarea><br>");
    //    out.println("사진1: <input type='file' name='photo1'><br>");
    //    out.println("사진2: <input type='file' name='photo2'><br>");
    //    out.println("사진3: <input type='file' name='photo3'><br>");
    out.println("<input type='submit' value='등록'>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    BoardService boardService = (BoardService) request.getServletContext().getAttribute("boardService");

    Board b = new Board();



    request.setCharacterEncoding("UTF-8");
    b.setTitle(request.getParameter("title"));
    b.setContent(request.getParameter("content"));
    b.setViewCount(1);

    ///-----사진처리
    Part photoPart1 = request.getPart("photo1");
    Part photoPart2 = request.getPart("photo2");
    Part photoPart3 = request.getPart("photo3");


    if (photoPart1.getSize() > 0) {
      // 파일을 선택해서 업로드 했다면,
      String filename = UUID.randomUUID().toString();
      photoPart1.write(this.uploadDir + "/" + filename);
      b.setbphoto(filename);

      // 썸네일 이미지 생성
      Thumbnails.of(this.uploadDir + "/" + filename)
      .size(30, 30)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_30x30";
        }
      });
      //사진처리----


      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"); // 날짜 포멧 형식 지정 및 생성
      //    System.out.println(sdf.format(d)); // 위에서 지정한 포멧 형식으로 날짜 출력
      b.setRegisteredDate(sdf.format(d));
      b.setBoardTypeNo(1);


      HttpServletRequest httpRequest = request;
      Member loginUser = (Member) httpRequest.getSession().getAttribute("loginUser");
      b.setWriter(loginUser);

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>게시글 등록</title>");

      try {
        boardService.add(b);

        out.println("<meta http-equiv='Refresh' content='1;url=list'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>게시글 등록</h1>");
        out.println("<p>게시글을 등록했습니다.</p>");

      } catch (Exception e) {
        StringWriter strWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(strWriter);
        e.printStackTrace(printWriter);

        out.println("</head>");
        out.println("<body>");
        out.println("<h1>게시글 등록오류</h1>");
        out.printf("<pre>%s</pre>\n",strWriter.toString());
        out.println("<p><a href='list'>목록</a></p>");

      }

      out.println("</body>");
      out.println("</html>");
    }
  }





