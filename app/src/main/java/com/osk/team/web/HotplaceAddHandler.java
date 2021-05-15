package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.osk.team.domain.Hotplace;
import com.osk.team.service.HotplaceService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@SuppressWarnings("serial")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/hotplace/add")
public class HotplaceAddHandler extends HttpServlet {

  private String uploadDir;

  @Override
  public void init() throws ServletException {
    this.uploadDir = this.getServletContext().getRealPath("/upload");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HotplaceService hotplaceService = (HotplaceService) request.getServletContext().getAttribute("hotplaceService");


    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>핫플레이스 등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>핫플레이스 등록</h1>");

    try {
      Hotplace h = new Hotplace();

      h.setTitle(request.getParameter("title"));
      h.setContent(request.getParameter("content"));
      h.setDate(Date.valueOf(request.getParameter("date")));
      h.setCount(Integer.parseInt(request.getParameter("count")));
      h.setAddress(request.getParameter("addr"));
      h.setPhoto(request.getParameter("photo"));

      Part photoPart = request.getPart("photo1");
      if (photoPart.getSize() > 0) {
        //파일을 선택해서 업로드 했다면
        String filename = UUID.randomUUID().toString();
        photoPart.write(this.uploadDir + "/" + filename);
        h.setPhoto(filename);

        Thumbnails.of(this.uploadDir + "/" + filename).
                size(254, 178)
                .outputFormat("jpg")
                .crop(Positions.CENTER)
                .toFiles(new Rename() {
                  @Override
                  public String apply(String name, ThumbnailParameter param) {
                    return name + "_254*178";
                  }
                });
      }

      hotplaceService.add(h);

      out.println("<p>핫플레이스 등록했습니다.</p>");

      response.setHeader("Refresh", "0;url=list");

    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);

      out.println("</head>");
      out.println("<body>");
      out.println("<h1>핫플레이스 등록 오류</h1>");
      out.printf("<pre>%s</pre>\n", strWriter.toString());
      out.println("<p><a href='list'>핫플레이스 목록</a></p>");
    }

    out.println("</body>");
    out.println("</html>");
  }

}