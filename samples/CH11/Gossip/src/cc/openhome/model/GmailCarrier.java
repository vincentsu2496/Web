package cc.openhome.model;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class GmailCarrier implements MailCarrier {
    private Properties props;

    public GmailCarrier(Properties props) {
        this.props = props;
    }

    @Override
    public void sendTo(Account account, String subject, String content) {

        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(props
                                    .getProperty("cc.openhome.username"), props
                                    .getProperty("cc.openhome.password"));
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty("cc.openhome.address")));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(
                    account.getEmail()));
            message.setSubject(subject);
            message.setSentDate(new Date());

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(content, "text/html;charset=UTF-8");

            Multipart multiPart = new MimeMultipart();

            multiPart.addBodyPart(htmlPart);

            message.setContent(multiPart);
            Transport.send(message);
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
