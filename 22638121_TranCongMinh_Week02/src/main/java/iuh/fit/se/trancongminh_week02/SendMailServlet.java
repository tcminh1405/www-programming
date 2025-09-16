package iuh.fit.se.trancongminh_week02;

import jakarta.mail.util.ByteArrayDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;
import jakarta.servlet.http.Part;

import java.io.*;
import java.util.Properties;

@WebServlet("/sendMail")
@MultipartConfig
public class SendMailServlet extends HttpServlet {

    private static final String USERNAME = "tcminhangiang@gmail.com";
    private static final String PASSWORD = "iwls ibmu mesy ssxq";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        Part filePart = request.getPart("file");

        // Cấu hình SMTP Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // File đính kèm nếu có
            if (filePart != null && filePart.getSize() > 0) {
                MimeBodyPart attachPart = new MimeBodyPart();
                attachPart.setFileName(filePart.getSubmittedFileName());
                attachPart.setDataHandler(new DataHandler(
                        new ByteArrayDataSource(filePart.getInputStream(), filePart.getContentType())));
                multipart.addBodyPart(attachPart);
            }

            message.setContent(multipart);

            // Gửi mail
            Transport.send(message);

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h3>Gửi mail thành công!</h3>");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
