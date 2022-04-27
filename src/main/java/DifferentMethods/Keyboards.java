package DifferentMethods;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboards {
    public void setButtonsMain(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Где покушать?"));
        keyboardFirstRow.add(new KeyboardButton("Что значит это слово?"));
        keyboardFirstRow.add(new KeyboardButton("Разработчик"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardSecondRow.add(new KeyboardButton("Расписание"));
        keyboardSecondRow.add(new KeyboardButton("Администрация"));
        keyboardSecondRow.add(new KeyboardButton("Новости"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();

        keyboardThirdRow.add(new KeyboardButton("Полезные ссылки"));
        keyboardThirdRow.add(new KeyboardButton("Команды"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsLinks(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("КФУ"));
        keyboardFirstRow.add(new KeyboardButton("ИВМиИТ"));
        keyboardFirstRow.add(new KeyboardButton("FAQ абитуриента/первокурсника"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardSecondRow.add(new KeyboardButton("ПрИкЛаДниЧкИ"));
        keyboardSecondRow.add(new KeyboardButton("SAAS"));
        keyboardSecondRow.add(new KeyboardButton("КРИС ИВМиИТ"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();

        keyboardThirdRow.add(new KeyboardButton("Студ. клуб ИВМиИТ"));
        keyboardThirdRow.add(new KeyboardButton("Киберспорт ИВМиИТ"));
        keyboardThirdRow.add(new KeyboardButton("Спорт ИВМиИТ"));

        KeyboardRow keyboardFourthRow = new KeyboardRow();

        keyboardFourthRow.add(new KeyboardButton("Добро ИВМиИТ"));
        keyboardFourthRow.add(new KeyboardButton("Общежитие на Пушкина"));
        keyboardFourthRow.add(new KeyboardButton("Общежитие ДУ"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);
        keyboardRowList.add(keyboardFourthRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsDays(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Понедельник"));
        keyboardFirstRow.add(new KeyboardButton("Вторник"));
        keyboardFirstRow.add(new KeyboardButton("Среда"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardSecondRow.add(new KeyboardButton("Четверг"));
        keyboardSecondRow.add(new KeyboardButton("Пятница"));
        keyboardSecondRow.add(new KeyboardButton("Суббота"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsAddresses(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Деканат ИВМиИТ"));
        keyboardFirstRow.add(new KeyboardButton("Каф. ПМ и ИИ"));
        keyboardFirstRow.add(new KeyboardButton("Каф. теор. кибер."));

        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardSecondRow.add(new KeyboardButton("Каф. ан. дан. и теор. прог."));
        keyboardSecondRow.add(new KeyboardButton("Каф. ИС"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();

        keyboardThirdRow.add(new KeyboardButton("Каф. сис. ан. и ИТ"));
        keyboardThirdRow.add(new KeyboardButton("Бухгалтерия"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsChoose(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Нет"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardSecondRow.add(new KeyboardButton("Да"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void setButtonsEmpty(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(" "));
        keyboardRowList.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }
}
