import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {

    public int type = 1;

    public String dayWeek;
    public String myGroup;
    Keyboards keyboards = new Keyboards();
    Methods methods = new Methods();

    public String info = "Список доступных команд и описание кнопок: \n"+
                         "/food (Где покушать?) - запрос мест общественного питания, популярных среди студентов,\n"+
                         "/term (Что значит это слово?) - получение определения некоторого специфичного слова, распространенного среди студентов,\n"+
                         "/developer (Разработчик) - получение контактных данных разработчика,\n"+
                         "/schedule (Расписание) - получение своего расписания на интересующий день недели,\n"+
                         "/address (Адрес администрации) - получение адреса некоторой точки администрации ВУЗа или института (деканат, кафедра, бухгалтерия и т.д.,)\n"+
                         "/news (Новости) - получение последних новостей ВУЗа или института,\n"+
                         "/links (Полезные ссылки) - получение ссылок на полезные для студентов ресурсы\n"+
                         "/help (Команды) - получение списка команд и кнопок бота, а также их описания\n"+
                         "/start - запуск или перезапуск бота";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
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
            sendMessage(sendMessage);
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
                            sendMsg(message, info, "Main");
                            break;

                        case "/food":
                            sendMsg(message, "Вот места общепита, которые мы рекомендовали бы Вам посетить неподалёку от Главного здания университета:\n"+
                                    "1) Добрая столовая. Баумана х\n"+
                                    "2) Домашняя столовая. Кремлевская х\n", "Main");
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
                            sendMsg(message, "Вот последние новости ИВМиИТ:\nБольше новостей здесь: https://vk.com/ivmiit", "Main");
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
                    switch (message.getText()){
                        case "Пн":
                            dayWeek = "Понедельник";
                            type=3;
                            sendMsg(message, "Введите вашу группу в формате: 09-951 (2)", "Group");
                            break;
                        case "Вт":
                            dayWeek = "Вторник";
                            type=3;
                            sendMsg(message, "Введите вашу группу в формате: 09-951 (2)", "Group");
                            break;
                        case "Ср":
                            dayWeek = "Среда";
                            type=3;
                            sendMsg(message, "Введите вашу группу в формате: 09-951 (2)", "Group");
                            break;
                        case "Чт":
                            dayWeek = "Четверг";
                            type=3;
                            sendMsg(message, "Введите вашу группу в формате: 09-951 (2)", "Group");
                            break;
                        case "Пт":
                            dayWeek = "Пятница";
                            type=3;
                            sendMsg(message, "Введите вашу группу в формате: 09-951 (2)", "Group");
                            break;
                        case "Сб":
                            dayWeek = "Суббота";
                            type=3;
                            sendMsg(message, "Введите вашу группу в формате: 09-951 (2)", "Group");
                            break;
                        default:
                            type=1;
                            sendMsg(message, "Неизвестный день недели!", "Main");
                    }
                    break;
                case 3:
                    myGroup = message.getText();
                    if (methods.checkGroup(myGroup)){
                        try {
                            sendMsg(message, Methods.getSchedule(dayWeek, myGroup), "Main");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        type=1;
                        sendMsg(message, "Главное меню", "Main");
                        break;
                    }else{
                        type=1;
                        sendMsg(message, "Неверный номер группы", "Main");
                    }
                    break;
                case 4:
                    String termin = message.getText();

                        try {
                            type=1;
                            sendMsg(message, DbMethods.getTerm(termin), "Main");
                        } catch (IOException e) {
                            type=5;
                            sendMsg(message, "К сожалению. данного слова нет в нашем словаре. Хотите ли Вы отправить термин на рассмотрение к добавлению?", "YesNo");
                        }
                        break;
                case 5:
                    switch (message.getText()){
                        case "Да":
                            type=1;
                            Email email = new Email();
                            email.sendMail(message.getAuthorSignature()+"термин");
                            sendMsg(message, "Главное меню", "Main");
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
                    switch (message.getText()){
                        case "Деканат ИВМиИТ":
                            type=1;
                            sendMsg(message, "ул. Кремлевская, 35, каб.1004", "Main");
                            break;
                        case "Каф. ПМ и ИИ":
                            type=1;
                            sendMsg(message, "ул. Кремлевская, 35, каб. 1202, 1210, 1106", "Main");
                            break;
                        case "Каф. теор. кибер.":
                            type=1;
                            sendMsg(message, "ул. Кремлевская, д. 35, к.1203, 1209", "Main");
                            break;
                        case "Каф. ИС":
                            type=1;
                            sendMsg(message, "ул. Кремлевская, 35, к.902, 908", "Main");
                            break;
                        case "Каф. сис. ан. и ИТ":
                            type=1;
                            sendMsg(message, "ул. Кремлевская, д. 35, к.1102, 1103", "Main");
                            break;
                        case "Бухгалтерия":
                            type=1;
                            sendMsg(message, "ул. Кремлевская, 18, к.109, 120, 121, 131, 135, 138, 142, 155", "Main");
                            break;
                        default:
                            type=1;
                            sendMsg(message, "Ошибка! Главное меню:", "Main");
                    }
                    break;
                case 7:
                    switch (message.getText()){
                        case "КФУ":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/kazan_federal_university\n" +
                                    "Сайт ВУЗа: https://kpfu.ru/\n" +
                                    "Инстаграм: https://www.instagram.com/kazanfederaluniversity/\n", "Main");
                            break;
                        case "ИВМиИТ":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/ivmiit\n" +
                                    "Сайт института: https://kpfu.ru/computing-technology\n" +
                                    "Инстаграм: https://www.instagram.com/ivmiit/\n", "Main");
                            break;
                        case "FAQ абитуриента/первокурсника":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/club185758815", "Main");
                            break;
                        case "ПрИкЛаДниЧкИ":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/club170864337", "Main");
                            break;
                        case "SAAS":
                            type=1;
                            sendMsg(message, "Сайт: http://studactivity.kpfu.ru/", "Main");
                            break;
                        case "КРИС ИВМиИТ":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/club198486158", "Main");
                            break;
                        case "Студ. клуб ИВМиИТ":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/public159961287\n" +
                                    "Инстаграм: https://www.instagram.com/stud_ivmiit/", "Main");
                            break;
                        case "Киберспорт ИВМиИТ":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/vmkcybersport", "Main");
                            break;
                        case "Спорт ИВМиИТ":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/club8570401", "Main");
                            break;
                        case "Добро ИВМиИТ":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/dobroivmiit\n" +
                                    "Инстаграм: https://www.instagram.com/dobroivmiit/", "Main");
                            break;
                        case "Общежитие на Пушкина":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/dom9pushkina", "Main");
                            break;
                        case "Общежитие ДУ":
                            type=1;
                            sendMsg(message, "Группа ВК: https://vk.com/du_ivmit\n" +
                                    "Инстаграм: https://www.instagram.com/3.1house/\n" +
                                    "Сайт ДУ: https://universiadevillage.ru/", "Main");
                            break;
                    }
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
