package DifferentMethods;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.streaming.clients.VkStreamingApiClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetVK {
    public static ArrayList<String> getVk() throws ClientException, ApiException {
        ArrayList<String> res = new ArrayList<>();

        TransportClient transportClient = new HttpTransportClient();

        VkStreamingApiClient streamingClient = new VkStreamingApiClient(transportClient);
        VkApiClient vk = new VkApiClient(transportClient);

        //Create service actor
        Integer appId = Integer.valueOf(ConfProperties.getProperty("appId"));
        String accessToken = ConfProperties.getProperty("accessToken");
        ServiceActor actor = new ServiceActor(appId, accessToken);

        GetResponse getResponse = vk.wall().get(actor).domain("ivmiit").count(3).execute();

        String obj = getResponse.toString();

        for (int i = 0; i<3; i++) {
            JSONObject jsonObject = new JSONObject(obj);
            JSONArray jsonArray = (JSONArray) jsonObject.get("items");
            JSONObject neededObj = (JSONObject) jsonArray.get(i);
            res.add(neededObj.getString("text"));

            if (neededObj.has("attachments")) {
                JSONArray attachments = (JSONArray) neededObj.get("attachments");
                JSONObject forPhoto = (JSONObject) attachments.get(0);
                JSONObject photo = (JSONObject) forPhoto.get("photo");

                JSONArray forUrlPhoto = (JSONArray) photo.get("sizes");
                JSONObject urlPhoto = (JSONObject) forUrlPhoto.get(forUrlPhoto.length() - 1);
                res.add(urlPhoto.getString("url"));
            }else{
                res.add("");
            }
        }

        return res;
    }
}
