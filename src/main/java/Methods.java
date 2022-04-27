//import org.telegram.telegrambots.api.objects.Message;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
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

    public static String getSchedule(String day, String group) throws IOException {
        downloadUsingStream("https://kpfu.ru/portal/docs/F_1642005522/Raspisanie._2.sem._2021_22.uch.g._18.04.2022.xlsx", "Schedule.xlsx");

        String text = day+". Расписание для группы "+group+"\n\n"+ExcelParser.parse("Schedule.xlsx", group, day);
        return text;
    }

    private static void downloadUsingStream(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}

