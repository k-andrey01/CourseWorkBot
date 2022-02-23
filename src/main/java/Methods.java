import java.util.HashMap;

public class Methods {
    //TODO сделать проверку по БД
    public boolean checkGroup(String group){
        return true;
    }

    public HashMap<String, String> getCommands(){
        HashMap<String,String> commands = new HashMap<>();
        commands.put("/start", "/start");
        commands.put("Полезные ссылки", "/links");
        commands.put("Команды", "/help");
        commands.put("Новости", "/news");
        commands.put("Адрес администрации", "/address");
        commands.put("Расписание", "/schedule");
        commands.put("Разработчик", "/developer");
        commands.put("Что значит это слово?", "/term");
        commands.put("Где покушать?", "/food");

        return commands;
    }
}

