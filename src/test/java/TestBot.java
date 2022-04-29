import DataBase.DbMethods;
import DifferentMethods.ExcelParser;
import DifferentMethods.GetVK;
import DifferentMethods.Methods;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestBot {
    @Test
    public void getSchedule() throws IOException {
        String day = "Вторник";
        String group = "09-951 (2)";

        Methods methods = new Methods();
        String result = methods.getSchedule(day, group);

        Assert.assertTrue(result.contains("экономика") && result.contains("системы") && result.contains("Разработка"));
    }

    @Test
    public void getCom(){
        Methods methods = new Methods();
        HashMap<String, String> testMap = methods.getCommands();

        Assert.assertTrue(testMap.get("/start").equals("/start") && testMap.get("Полезные ссылки").equals("/links") && testMap.get("Команды").equals("/help") && testMap.get("Новости").equals("/news") && testMap.get("Администрация").equals("/address") && testMap.get("Расписание").equals("/schedule") && testMap.get("Разработчик").equals("/developer") && testMap.get("Что значит это слово?").equals("/term") && testMap.get("Где покушать?").equals("/food"));
    }

    @Test
    public void getVK() throws ClientException, ApiException {
        GetVK getVK = new GetVK();
        ArrayList<String> result = getVK.getVk();

        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void getTerm() throws IOException {
        DbMethods dbMethods = new DbMethods();
        String term = "ДвОЙкА";
        String result = dbMethods.getTerm(term);

        Assert.assertEquals(term+" - высотное здание, в котором проходит большинство занятий студентов ИВМиИТ. Находится по адресу: ул. Кремлевская, 35.",result);
    }

    @Test
    public void getLink() throws IOException{
        DbMethods dbMethods = new DbMethods();
        String link = "КФУ";
        String result = dbMethods.getLink(link);

        Assert.assertTrue(result.contains("https://vk.com/kazan_federal_university") && result.contains("https://kpfu.ru/"));
    }

    @Test
    public void getFood() throws IOException{
        DbMethods dbMethods = new DbMethods();
        String result = dbMethods.getFood();

        Assert.assertTrue(result.contains("Добрая столовая") && result.contains("Токмач") && result.contains("Исфара"));
    }

    @Test
    public void getPlace() throws IOException{
        DbMethods dbMethods = new DbMethods();
        String place = "Каф. ан. дан. и техн. прог.";
        String result = dbMethods.getPlace(place);

        Assert.assertTrue(result.contains("Кремлевская, 35, к.1105") && result.contains("(843) 233-76-09"));
    }
}
