package DataBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

public class DbMethods {
    public static String getTerm(String term1) throws IOException {
        TermRes termRes = new TermRes();

        String term = term1.toLowerCase();
        term = term.replaceAll(" ", "");
        term = term.replaceAll("ё", "е");
        String urls = "http://localhost:19327/term?name="+ URLEncoder.encode(term, "utf-8");
        URL url = new URL(urls);

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext())
            result+=in.nextLine();

        JSONArray text = new JSONArray(result);
        if (text.length()>0) {
            JSONObject ttext = (JSONObject) text.get(0);
            termRes.setTerm(ttext.getString("text"));
            return term1 + " - " + termRes.getTerm();
        }else{
            return null;
        }
    }

    public static String getLink(String link) throws IOException {
        ArrayList<LinkRes> linkRes = new ArrayList<>();
        int counter = 0;
        Integer org_id = -1;
        String res = "";
        ArrayList<Integer> type_id = new ArrayList<>();
        String urls1 = "http://localhost:19327/organization?name="+ URLEncoder.encode(link, "utf-8");
        URL url1 = new URL(urls1);

        Scanner in = new Scanner((InputStream) url1.getContent());
        String result = "";
        while (in.hasNext())
            result+=in.nextLine();

        JSONArray orgs = new JSONArray(result);
        if (orgs.length()>0) {
            JSONObject orgss = (JSONObject) orgs.get(0);
            org_id = orgss.getInt("id");
        }else{
            return null;
        }

        String urls2 = "http://localhost:19327/links?org_name="+ org_id;
        URL url2 = new URL(urls2);

        Scanner in1 = new Scanner((InputStream) url2.getContent());
        String result1 = "";
        while (in1.hasNext())
            result1+=in1.nextLine();

        JSONArray links = new JSONArray(result1);
        if (links.length()>0) {
            for (int i=0; i<links.length(); i++) {
                JSONObject linkss = (JSONObject) links.get(i);
                LinkRes lr = new LinkRes();
                lr.setOrganization(link);
                lr.setLink(linkss.getString("text"));
                linkRes.add(lr);
                type_id.add(linkss.getInt("type_id"));
                counter++;
            }
        }else{
            return null;
        }

        for (int i=0; i< type_id.size(); i++) {
            String urls3 = "http://localhost:19327/type/" + type_id.get(i);
            URL url3 = new URL(urls3);

            Scanner in2 = new Scanner((InputStream) url3.getContent());
            String result2 = "";
            while (in2.hasNext())
                result2 += in2.nextLine();

            JSONObject types = new JSONObject(result2);
            if (types.length() > 0) {
                linkRes.get(i).setType(types.getString("name"));
            } else {
                res = null;
            }
        }

        res+=link+"\n";
        for (int i=0; i<counter; i++)
            res+=linkRes.get(i).getType()+": "+linkRes.get(i).getLink()+"\n";


        return res;
    }

    public static String getFood() throws IOException{
        ArrayList<FoodRes> foodRes = new ArrayList<>();

        String urls = "http://localhost:19327/food";
        URL url = new URL(urls);

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext())
            result+=in.nextLine();

        JSONArray text = new JSONArray(result);
        if (text.length()>0) {
            for (int i=0; i<text.length(); i++) {
                JSONObject ttext = (JSONObject) text.get(i);
                FoodRes fr = new FoodRes();
                fr.setName(ttext.getString("name"));
                fr.setAddress(ttext.getString("address"));
                Object o = ttext.get("site");
                if (!o.toString().equals("null"))
                    fr.setSite(ttext.getString("site"));
                else
                    fr.setSite("отсутствует");
                foodRes.add(fr);
            }
        }else{
            return null;
        }

        String res="";
        for (int i=0; i<foodRes.size(); i++)
            res+=foodRes.get(i).getName()+"\nАдрес: "+foodRes.get(i).getAddress()+"\nСайт: "+foodRes.get(i).getSite()+"\n\n";

        return res;
    }

    public static String getPlace(String place) throws IOException {
        PlaceRes placeRes = new PlaceRes();

        String urls = "http://localhost:19327/place?name="+ URLEncoder.encode(place, "utf-8");
        URL url = new URL(urls);

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext())
            result+=in.nextLine();

        JSONArray text = new JSONArray(result);
        if (text.length()>0) {
            JSONObject ttext = (JSONObject) text.get(0);
            placeRes.setName(ttext.getString("name"));
            placeRes.setAddress(ttext.getString("address"));
            placeRes.setPhone(ttext.getString("phone"));
        }else{
            return null;
        }

        return placeRes.getName()+"\nАдрес: "+placeRes.getAddress()+"\nТелефон: "+placeRes.getPhone();
    }
}
