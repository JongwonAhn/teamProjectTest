package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@SuppressWarnings("serial")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/member/add")
public class MemberAddHandler extends HttpServlet {

    private String uploadDir;

    @Override
    public void init() throws ServletException {
        this.uploadDir = this.getServletContext().getRealPath("/upload");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>회원 등록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>회원 등록</h1>");


    /*private int no;//멤버번호
    private String name;//이름
    private String password;//암호
    private String email;//이메일
    private String photo;//사진
    private Date birth;//생년월일

    private int tel;//전화번호w
    private int gender;//성별
    private int status;//탈퇴여부
    private int power;//관리자권한
    private int count;//제제횟수 */

        try {
            Member m = new Member();
            m.setName(request.getParameter("name"));
            m.setEmail(request.getParameter("email"));
            m.setPassword(request.getParameter("password"));
            m.setTel(Integer.parseInt(request.getParameter("tel")));
            m.setGender(Integer.parseInt(request.getParameter("gender")));
            m.setBirth(Date.valueOf(request.getParameter("birth")));

            //m.setTel(request.getParameter("tel"));

            Part photoPart = request.getPart("photo");
            if (photoPart.getSize() > 0) {
                // 파일을 선택해서 업로드 했다면,
                String filename = UUID.randomUUID().toString();
                photoPart.write(this.uploadDir + "/" + filename);
                m.setPhoto(filename);

                // 썸네일 이미지 생성하는 부분
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

                Thumbnails.of(this.uploadDir + "/" + filename)
                        .size(80, 80)
                        .outputFormat("jpg")
                        .crop(Positions.CENTER)
                        .toFiles(new Rename() {
                            @Override
                            public String apply(String name, ThumbnailParameter param) {
                                return name + "_80x80";
                            }
                        });
            }
            memberService.add(m);

            out.println("<p>회원을 등록했습니다.</p>");

            // 응답헤더에 리프래시 정보를 설정
            response.setHeader("Refresh", "0;url=list");


        } catch (Exception e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher("/error").forward(request, response);
            return;
        }

        out.println("</body>");
        out.println("</html>");
    }
}