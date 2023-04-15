package email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.IOException;
import java.util.Properties;

public class EmailService {

        private static final Properties properties = new Properties();
        private static String username ;
        private static String password;

        static{

            try {
                properties.load(email.EmailService.class.getResourceAsStream("/resources/application.properties"));
                username = properties.getProperty("email_username");
                password = properties.getProperty("email_password");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public static boolean sendMail(String from , String to , String subject,String body) throws MessagingException {

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);

            return true;
        }


}
