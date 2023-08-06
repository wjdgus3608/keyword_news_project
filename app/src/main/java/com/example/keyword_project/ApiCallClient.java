package com.example.keyword_project;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.keyword_project.domain.KeywordUser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;

public class ApiCallClient {

    public static void callGetUserAgain(Context context, String userToken){
        String serverUrl = "http://49.247.40.141:80/logIn";

        String jsonData = "{\"userToken\":\""+userToken+"\"}";


        PostApiTask task = new PostApiTask((responseCode,result) -> {
            if(responseCode==200){
                GlobalData.loginUser=parseUserData(result);
            }
            else{
                Toast.makeText(context,"토큰값이 유효하지 않습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        task.execute(serverUrl,jsonData);

    }

    public static void callFindAllContainKeyword(Context context, String keyword,String userToken){
        String serverUrl = "http://49.247.40.141:80/keyword/findAllContainKeyword";
        String jsonData = "{\"keyword\":\""+keyword+ "\"," +
                "\"ownerId\":\""+userToken +
                "\"}";
        PostApiTask task = new PostApiTask((responseCode,result) -> {
            if(responseCode==200){
                Toast.makeText(context,"포함 키워드 로드 ",Toast.LENGTH_SHORT).show();
            }
        });
        task.execute(serverUrl,jsonData);
    }
    public static void callUpdateSetting(Context context, int type, Map<String,Object> data) {
        String serverUrl = "http://49.247.40.141:80/update";
        String jsonData = "";
        PostApiTask task;
        switch (type) {
            //VIP 여부 업데이트(추후 수정)
           /* case -2:
                jsonData = "{\"userToken\":\""+loginUser.getUserToken()+ "\"," +
                        "\"updateType\":\"IS_VIP\"," +
                        "\"updateValue\":\""+!isSettingAlaramBtnClicked+
                        "\"}";

                task = new PostApiTask((responseCode,result) -> {
                    if(responseCode==200){
                        Toast.makeText(this,"알림 "+(!isSettingAlaramBtnClicked?"On":"Off"),Toast.LENGTH_SHORT).show();
                    }
                });
                task.execute(serverUrl,jsonData);
                break;*/
            //FCM 토큰 업데이트
             case -1:

                jsonData = "{\"userToken\":\""+GlobalData.loginUser.getUserToken()+ "\"," +
                        "\"updateType\":\"FCM_TOKEN\"," +
                        "\"updateValue\":\""+(String)data.get("fcmToken")+
                        "\"}";

                task = new PostApiTask((responseCode,result) -> {
                    if(responseCode==200){
                        Toast.makeText(context,"FCM 토큰 업데이트 ",Toast.LENGTH_SHORT).show();
                    }
                });
                task.execute(serverUrl,jsonData);
                break;
            //알림 세팅 업데이트
            case 0:
                boolean isSettingAlaramBtnClicked = (boolean)data.get("isSettingAlaramBtnClicked");
                jsonData = "{\"userToken\":\"" + GlobalData.loginUser.getUserToken() + "\"," +
                        "\"updateType\":\"ALARM_ALLOWED\"," +
                        "\"updateValue\":\"" + !isSettingAlaramBtnClicked +
                        "\"}";

                task = new PostApiTask((responseCode, result) -> {
                    if (responseCode == 200) {
                        Toast.makeText(context, "알림 " + (!isSettingAlaramBtnClicked ? "On" : "Off"), Toast.LENGTH_SHORT).show();
                    }
                });
                task.execute(serverUrl, jsonData);
                break;
            //시간 세팅 업데이트
            case 1:
                jsonData = "{\"userToken\":\""+GlobalData.loginUser.getUserToken()+ "\"," +
                        "\"updateType\":\"FETCH_TIME\"," +
                        "\"updateValue\":\""+(String)data.get("fetchTime")+
                        "\"}";

                task = new PostApiTask((responseCode,result) -> {
                    if(responseCode==200){
                        Toast.makeText(context,"시간이 재설정되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                task.execute(serverUrl,jsonData);
                break;
            //주기 세팅 업데이트
            case 2:
                jsonData = "{\"userToken\":\""+GlobalData.loginUser.getUserToken()+ "\"," +
                        "\"updateType\":\"FETCH_INTERVAL\"," +
                        "\"updateValue\":\""+(String)data.get("fetchInterval")+
                        "\"}";

                task = new PostApiTask((responseCode,result) -> {
                    if(responseCode==200){
                        Toast.makeText(context,"주기가 업데이트되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                task.execute(serverUrl,jsonData);
                break;
            //포함키워드 추가
            case 3:
                jsonData = "{\"keyword\":\"" + data.get("keyword") + "\"," +
                        "\"ownerId\":\"" + GlobalData.loginUser.getUserToken() + "\"," +
                        "\"addContainKeyword\":\"" + data.get("addContainKeyword") +
                        "\"}";

                task = new PostApiTask((responseCode, result) -> {
                    if (responseCode == 200) {
                        Toast.makeText(context,"포함키워드 추가",Toast.LENGTH_SHORT).show();
                    }
                });
                task.execute("http://49.247.40.141:80/keyword/addContainKeyword", jsonData);
                break;
            //포함키워드 삭제
            case 4:
                jsonData = "{\"keyword\":\"" + data.get("keyword") + "\"," +
                        "\"ownerId\":\"" + GlobalData.loginUser.getUserToken() + "\"," +
                        "\"removeContainKeyword\":\"" + data.get("removeContainKeyword") +
                        "\"}";

                task = new PostApiTask((responseCode, result) -> {
                    if (responseCode == 200) {
//                        Toast.makeText(this,"주기 "+(!isSettingAlaramBtnClicked?"On":"Off"),Toast.LENGTH_SHORT).show();
                    }
                });
                task.execute("http://49.247.40.141:80/keyword/removeContainKeyword", jsonData);
                break;
            //제외키워드 추가
            case 5:
                jsonData = "{\"keyword\":\"" + data.get("keyword") + "\"," +
                        "\"ownerId\":\"" + GlobalData.loginUser.getUserToken() + "\"," +
                        "\"addExcludeKeyword\":\"" + data.get("addExcludeKeyword") +
                        "\"}";

                task = new PostApiTask((responseCode, result) -> {
                    if (responseCode == 200) {
//                        Toast.makeText(this,"주기 "+(!isSettingAlaramBtnClicked?"On":"Off"),Toast.LENGTH_SHORT).show();
                    }
                });
                task.execute("http://49.247.40.141:80/keyword/addExcludeKeyword", jsonData);
                break;
            //제외키워드 삭제
            case 6:
                jsonData = "{\"keyword\":\"" + data.get("keyword") + "\"," +
                        "\"ownerId\":\"" + GlobalData.loginUser.getUserToken() + "\"," +
                        "\"removeExcludeKeyword\":\"" + data.get("removeExcludeKeyword") +
                        "\"}";

                task = new PostApiTask((responseCode, result) -> {
                    if (responseCode == 200) {
//                        Toast.makeText(this,"주기 "+(!isSettingAlaramBtnClicked?"On":"Off"),Toast.LENGTH_SHORT).show();
                    }
                });
                task.execute("http://49.247.40.141:80/keyword/removeExcludeKeyword", jsonData);
                break;

        }
    }

    public static KeywordUser parseUserData(String response){
        // Gson을 사용하여 JSON 파싱
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);


        // 파싱한 데이터 사용 예시
        KeywordUser user = new KeywordUser();

        JsonElement e1 = jsonObject.get("userToken");
        JsonElement e2 = jsonObject.get("active");
        JsonElement e3 = jsonObject.get("alarmAllowed");
        JsonElement e4 = jsonObject.get("fcmToken");
        JsonElement e5 = jsonObject.get("vip");
        JsonElement e6 = jsonObject.get("fetchTime");
        JsonElement e7 = jsonObject.get("fetchInterval");

        String userToken = e1.isJsonNull() ? null : e1.getAsString();
        boolean isActive = e2.isJsonNull() ? null : e2.getAsBoolean();
        boolean isAlarmAllowed = e3.isJsonNull() ? null : e3.getAsBoolean();
        String fcmToken =e4.isJsonNull() ? null : e4.getAsString();
        boolean isVip =e5.isJsonNull() ? null : e5.getAsBoolean();
        String fetchTime = e6.isJsonNull() ? null : e6.getAsString();
        String fetchInterval = e7.isJsonNull() ? null : e7.getAsString();

        user.setUserToken(userToken);
        user.setActive(isActive);
        user.setAlarmAllowed(isAlarmAllowed);
        user.setFcmToken(fcmToken);
        user.setVip(isVip);
        user.setFetchTime(fetchTime);
        user.setFetchInterval(fetchInterval);

        return user;
    }
}
