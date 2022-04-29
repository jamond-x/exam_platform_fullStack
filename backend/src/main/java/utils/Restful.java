package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装好响应的数据
 */
public class Restful {
    //需要返回的消息
    public final static String CODE_ZERO="0";
    public final static String CODE_ONE="1";
    public final static String CODE_TWO="2";
    public final static String CODE_THREE="3";
    public final static String CODE_FOUR="4";
    public final static String CODE="code";//
    public final static String DATA="data";//
    public final static String FROM_NAME="fromName";//发送者用户名
    public final static String TO_NAME="toName";//接收者用户名
    public final static String MSG="msg";
    public final static String USERNAME="username";


    public static String RestfulJson(String code,String message, Object data){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", message);
        json.put("data", data);
        return json.toJSONString();
    }

    //发送聊天内容
    public static String getContent(Object code,String fromName,String toName,String content){
        Map<String,Object> userMap=new HashMap<String,Object>();
        userMap.put(Restful.CODE,code);
        userMap.put(Restful.DATA,content);
        userMap.put(Restful.FROM_NAME,fromName);
        userMap.put(Restful.TO_NAME,toName);
        String json= JSON.toJSONString(userMap);
        return json;
    }

    //发送用户名列表
    public static String getNames(Object code, List<String> names){
        Map<String,Object> userMap=new HashMap<String,Object>();
        userMap.put(Restful.CODE,code);
        userMap.put(Restful.DATA,names);
        String json= JSON.toJSONString(userMap);
        return json;
    }
    //返回给前端对应的mag 和 code值
    public static String StandardRestful(Object code, String msg){
        Map<String,Object> userMap=new HashMap<String,Object>();
        userMap.put(Restful.CODE,code);
        userMap.put(Restful.MSG,msg);
        String json= JSON.toJSONString(userMap);
        return json;
    }

    //返回给前端对应的mag,code,username值
    public static String ReturnData_MCU(String msg,Object code,String username){
        Map<String,Object> userMap=new HashMap<String,Object>();
        userMap.put(Restful.MSG,msg);
        userMap.put(Restful.CODE,code);
        userMap.put(Restful.USERNAME,username);
        String json= JSON.toJSONString(userMap);
        return json;
    }
}