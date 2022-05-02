package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class ParseRequest {
  public ParseRequest(){
  }

  public static <T> T get(HttpServletRequest request, Class<T> type) throws IOException {
    BufferedReader reader = request.getReader();
    String line = null;
    String json = "";
    while ((line = reader.readLine()) != null){
      json += line;
    }
    ObjectMapper om = new ObjectMapper();
    T obj = om.readValue(json, type);
    return obj;
  }

}
