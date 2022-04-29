package entity;

public class Token {
    private String id;
    private String token;

    public Token(){}

    public Token(String token){
        this.token = token;
    }

    public Token(String token, String id){
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public String getId(){
        return this.id;
    }
}
