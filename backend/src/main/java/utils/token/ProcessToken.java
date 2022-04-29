package utils.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

// https://github.com/auth0/java-jwt     // jwt github readme
// https://www.jb51.net/article/230923.htm#_lab2_2_4
public class ProcessToken {
    private String secret = "ZQExam";  // secret 存放 .properties ?
    private static Algorithm algorithm = null;
    public static long minute = 60 * 1000;
    public static long hour = minute * 60;
    public static long day = hour * 24;

    public static void getAlgorithm() throws UnsupportedEncodingException {
        Properties ppt = new Properties();
        String secret = "";
        try{
            InputStream in = ProcessToken.class.getClassLoader().getResourceAsStream("configs.properties");
            ppt.load(in);
            secret = ppt.getProperty("secret");
        } catch (IOException e) {
            e.printStackTrace();
        }
        algorithm = Algorithm.HMAC256(secret);
    }

    public static String dispatchToken(String userId, long expires) throws UnsupportedEncodingException {
        if(algorithm == null){
            getAlgorithm();
        }
        Date expiresTime = new Date(System.currentTimeMillis() + expires);
        return JWT.create().withClaim("id",userId).withExpiresAt(expiresTime).sign(algorithm);
    }

    public static boolean verifyToken(String jwt) throws UnsupportedEncodingException {
        if(algorithm == null){
            getAlgorithm();
        }
        JWTVerifier verifier = JWT.require(algorithm).build();
        try{
            verifier.verify(jwt);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }

    public static String parseToken(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("id").asString();
    }

    public static void  main(String[]args) throws UnsupportedEncodingException {
        String jwt = ProcessToken.dispatchToken("1111", 1000 * 60);
//        String jwt = ProcessToken.dispatchToken("1002", -100000);
//        System.out.println(ProcessToken.parseToken(jwt));
//        System.out.println(verifyToken(jwt));
    }
}
