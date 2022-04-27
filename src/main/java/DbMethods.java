import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class DbMethods {
    public static String getTerm(String term) throws IOException {
        TermRes termRes = new TermRes();

        term = term.toLowerCase();
        String urls = "http://localhost:19327/term?name="+ URLEncoder.encode(term, "utf-8");
        URL url = new URL(urls);

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext())
            result+=in.nextLine();

        result.replaceAll("]", "");

        JSONArray text = new JSONArray(result);
        if (text.length()>0) {
            JSONObject ttext = (JSONObject) text.get(0);
            termRes.setTerm(ttext.getString("text"));
            return term + " - " + termRes.getTerm();
        }else{
            return "null";
        }
    }
}
