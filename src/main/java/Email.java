import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {
    public void sendMail(String term){
        Properties properties = new Properties();
        //Хост или IP-адрес почтового сервера
        properties.put("mail.smtp.host", "smtp.mail.ru");
        //Требуется ли аутентификация для отправки сообщения
        properties.put("mail.smtp.auth", "true");
        //Порт для установки соединения
        properties.put("mail.smtp.socketFactory.port", "465");
        //Фабрика сокетов, так как при отправке сообщения Yandex требует SSL-соединения
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        //Создаем соединение для отправки почтового сообщения
        Session session = Session.getDefaultInstance(properties,
                //Аутентификатор - объект, который передает логин и пароль
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("russia_andreykazantsev@mail.ru", ConfProperties.getProperty("password"));
                    }
                });

        try {
            //Создаем новое почтовое сообщение
            MimeMessage messager = new MimeMessage(session);
            //От кого
            messager.setFrom(new InternetAddress("russia_andreykazantsev@mail.ru"));
            //Кому
            messager.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress("russia_andreykazantsev2001@mail.ru"));
            //Тема письма
            messager.setSubject("Термин для чат бота телеграм");
            //Текст письма
            messager.setText(term);
            //Поехали!!!
            Transport.send(messager);
        }catch(MessagingException mex){
            mex.printStackTrace();
        }
    }
}
