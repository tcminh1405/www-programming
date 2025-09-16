package iuh.fit.se.trancongminh_week02;

import java.io.*;
import java.nio.file.Path;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet("/uploadMultiFiles")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB)
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MultiFileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public MultiFileUploadServlet(){}
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

        // Tạo thư mục nếu chưa có
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        for (Part part : req.getParts()) {
            String fileName = getFileName(part);
            if (fileName != null && !fileName.isEmpty()) {
                part.write(uploadPath + File.separator + fileName);
            }
        }
        resp.setContentType("text/html");
        resp.getWriter().println("<h3>Upload thành công!</h3>");

    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return null;
    }

}