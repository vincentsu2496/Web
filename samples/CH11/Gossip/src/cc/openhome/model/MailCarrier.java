package cc.openhome.model;

public interface MailCarrier {
    void sendTo(Account account, String subject, String content);
}
