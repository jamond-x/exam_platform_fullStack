package entity;

public class User {
    private String id;
    private String password;
    private String name;
    private String gender;
    private String description;
    private String avatar;
    private String email;
    private String admin;
    public User(String id, String password){
        this.id = id;
        this.password = password;
    }

    public User(String id, String password, String email){
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public User(String id,  String name, String gender, String description, String avatar, String email, String admin) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.avatar = avatar;
        this.email = email;
        this.admin = admin;
    }

    public User(String id,  String name, String gender, String description, String avatar, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.avatar = avatar;
        this.email = email;
    }

    public User(){

    }

    public User(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getPassword(){
        return this.password;
    }

    public String getGender() {
        if(this.gender == null){
            return "";
        }
        if(this.gender.equals("0")){
            return "男";
        }
        if(this.gender.equals("1") ){
            return "女";
        }
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
      return "User{" +
        "id='" + id + '\'' +
        ", password='" + password + '\'' +
        ", name='" + name + '\'' +
        ", gender='" + gender + '\'' +
        ", description='" + description + '\'' +
        ", avatar='" + avatar + '\'' +
        ", email='" + email + '\'' +
        ", admin='" + admin + '\'' +
        '}';
    }
}
