package principal;

import kong.unirest.Unirest;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {

    private final String TEXTO_PESQUISA = "";
    private final String URL = "";
    private final String EMAIL_HOST = "";
    private final String EMAIL_PORT = "";
    private final String EMAIL_USERNAME = "";
    private final String EMAIL_PASSWORD = "";
    private final String EMAIL_DESTINATARIO = "";
    private final String EMAIL_ASSUNTO = "";
    private final String EMAIL_MENSAGEM = "";


    private String response = "";

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        while (true) {
            main.getConteudoPagina().checkConteudoPagina();
            System.out.println("Dormindo 10 segundos ...");
            Thread.sleep(10000);
        }
    }

    Main getConteudoPagina() {
        try {
            response = Unirest.get(URL)
                    .header("Cookie", "_d2id=7dd94422-41e7-47c8-b3e1-ed593e67ab27-n; navigation_items=MLB1557240039%7C17062020171748")
                    .asString().getBody();
        } catch (Exception ex) {
            response = "";
            ex.printStackTrace();
        }
        return this;
    }

    void checkConteudoPagina() {
        if (!response.equals("") && !response.contains(TEXTO_PESQUISA)) {
            enviarEmail();
        }
    }

    void enviarEmail() {
        System.out.println("Enviando e-mail");
        Session session;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", EMAIL_HOST);
        prop.put("mail.smtp.port", EMAIL_PORT);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", EMAIL_PORT);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_DESTINATARIO)
            );
            message.setSubject(EMAIL_ASSUNTO);
            message.setText(EMAIL_MENSAGEM);

            Transport.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}