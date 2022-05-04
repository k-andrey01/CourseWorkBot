

import DataBase.DbMethods;
import DifferentMethods.Email;
import DifferentMethods.GetVK;
import DifferentMethods.Keyboards;
import DifferentMethods.Methods;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {

    private int type = 1;

    public String dayWeek;
    public String myGroup;
    public String termin;
    Keyboards keyboards = new Keyboards();
    Methods methods = new Methods();

    public String info = "Список доступных команд и описание кнопок: \n"+
                         "/food (Где покушать?) - запрос мест общественного питания, популярных среди студентов,\n"+
                         "/term (Что значит это слово?) - получение определения некоторого специфичного слова, распространенного среди студентов,\n"+
                         "/developer (Разработчик) - получение контактных данных разработчика,\n"+
                         "/schedule (Расписание) - получение своего расписания на интересующий день недели,\n"+
                         "/address (Администрация) - получение адреса некоторой точки администрации ВУЗа или института (деканат, кафедра, бухгалтерия и т.д.,)\n"+
                         "/news (Новости) - получение последних новостей ВУЗа или института,\n"+
                         "/links (Полезные ссылки) - получение ссылок на полезные для студентов ресурсы\n"+
                         "/help (Команды) - получение списка команд и кнопок бота, а также их описания\n"+
                         "/start - запуск или перезапуск бота";

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new Bot());
    }

    public void sendMsg(Message message, String text, String keyboard){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{
            switch (keyboard){
                case "Main":
                    keyboards.setButtonsMain(sendMessage);
                    break;
                case "Days":
                    keyboards.setButtonsDays(sendMessage);
                    break;
                case "Group":
                    keyboards.setButtonsEmpty(sendMessage);
                    break;
                case "Term":
                    keyboards.setButtonsEmpty(sendMessage);
                    break;
                case "YesNo":
                    keyboards.setButtonsChoose(sendMessage);
                    break;
                case "Address":
                    keyboards.setButtonsAddresses(sendMessage);
                    break;
                case "Links":
                    keyboards.setButtonsLinks(sendMessage);
                    break;
            }
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        HashMap<String,String> commands = methods.getCommands();
        Message message = update.getMessage();
        if (message!=null && message.hasText()){
            switch (type){
                case 1:
                    String mes;
                    if (message.getText().startsWith("/")){
                        mes = message.getText();
                    }else {
                        mes = commands.get(message.getText());
                    }
                    switch (mes){
                        case "/help":
                            type = 1;
                            sendMsg(message, info, "Main");
                            break;

                        case "/food":
                            try {
                                sendMsg(message, DbMethods.getFood(), "Main");
                            } catch (IOException e) {
                                type=1;
                                sendMsg(message, "Не удалось подключиться к базе данных", "Main");
                            }
                            break;

                        case "/term":
                            sendMsg(message, "Введите термин, значение которого Вам интересно:","Term");
                            type=4;
                            break;

                        case "/developer":
                            sendMsg(message, "ВКонтакте разработчика: https://vk.com/k.a_01\nТелеграм разработчика: https://t.me/k_andrey01", "Main");
                            break;

                        case "/schedule":
                            sendMsg(message, "Выберите день недели:", "Days");
                            type = 2;
                            break;

                        case "/address":
                            sendMsg(message, "Выберите интересующий Вас пункт администрации", "Address");
                            type=6;
                            break;

                        case "/news":
                            try {
                                ArrayList<String> arr = GetVK.getVk();

                                for (int i=0; i< arr.size() && arr.get(i).length()>0; i+=2) {
                                    if (i + 1 < arr.size())
                                        sendMsg(message, "Вот последние новости ИВМиИТ:\n\n" + arr.get(i) + "\n" +"Фото: " +arr.get(i + 1) + "\n\nБольше новостей здесь: https://vk.com/ivmiit", "Main");
                                    else
                                        sendMsg(message, "Вот последние новости ИВМиИТ:\n\n" + arr.get(i) + "\n" + "\n\nБольше новостей здесь: https://vk.com/ivmiit", "Main");
                                }
                            } catch (ClientException e) {
                                e.printStackTrace();
                            } catch (ApiException e) {
                                e.printStackTrace();
                            }
                            break;

                        case "/links":
                            sendMsg(message, "Что Вам интересно?", "Links");
                            type=7;
                            break;

                        case "/start":
                            sendMsg(message, "Бот запущен", "Main");
                            break;

                        default:
                            sendMsg(message, "Неизвестная команда!", "Main");
                    }
                    break;
                case 2:
                    dayWeek=message.getText();
                    type=3;
                    sendMsg(message, "Введите вашу группу в формате: 09-951 (2)", "Group");
                    break;
                case 3:
                    myGroup = message.getText();
                    try {
                        sendMsg(message, Methods.getSchedule(dayWeek, myGroup), "Main");
                    } catch (IOException e) {
                        sendMsg(message, "Ошибка при получении данных", "Main");
                    }
                    type=1;
                    sendMsg(message, "Главное меню", "Main");
                    break;
                case 4:
                    termin = message.getText();
                    try {
                        String ms = DbMethods.getTerm(termin);
                        if (ms!=null) {
                            type = 1;
                            sendMsg(message, DbMethods.getTerm(termin), "Main");
                        } else {
                            type = 5;
                            sendMsg(message, "К сожалению. данного слова нет в нашем словаре. Хотите ли Вы отправить термин на рассмотрение к добавлению?", "YesNo");
                        }
                    } catch (IOException e) {
                        type = 1;
                        sendMsg(message, "Не удалось подключиться к базе данных", "Main");
                    }
                    break;
                case 5:
                    switch (message.getText()){
                        case "Да":
                            type=1;
                            Email email = new Email();
                            email.sendMail(termin);
                            sendMsg(message, "Термин отправлен на рассмотрение к добавлению. Главное меню", "Main");
                        case "Нет":
                            type=1;
                            sendMsg(message, "Главное меню", "Main");
                            break;
                        default:
                            type=1;
                            sendMsg(message, "Ошибка! Главное меню:", "Main");
                            break;
                    }
                    break;
                case 6:
                    try {
                        String ms = DbMethods.getPlace(message.getText());
                        if (ms!=null) {
                            type = 1;
                            sendMsg(message, ms, "Main");
                        } else {
                            type = 1;
                            sendMsg(message, "Произошла ошибка", "Main");
                        }
                    } catch (IOException e) {
                        type = 1;
                        sendMsg(message, "Не удалось подключиться к базе данных", "Main");
                    }
                    break;
                case 7:
                    try {
                        String ms = DbMethods.getLink(message.getText());
                        if (ms!=null) {
                            type = 1;
                            sendMsg(message, ms, "Main");
                        } else {
                            type = 1;
                            sendMsg(message, "Произошла ошибка", "Main");
                        }
                    } catch (IOException e) {
                        type = 1;
                        sendMsg(message, "Не удалось подключиться к базе данных", "Main");
                    }
                    break;
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "KpfuStudHelperBot";
    }

    @Override
    public String getBotToken() {
        return "5081862803:AAEd-FwKKBAdvtFKRU5cMdyZvI0MUMEDYdM";
    }
}
