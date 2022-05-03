package DAO;

import entity.Token;
import entity.User;

public interface APIUser {
    public boolean login(String id, String pw) throws Exception;
    public boolean signup(String id, String pw, String email)throws Exception;
    public boolean updateToken(String userId, String token, boolean isUpdate) throws Exception;
    public User getUserInfo(String userId) throws Exception;
    public User updateUserInfo(User user) throws Exception;
    public boolean verifyToken(Token token) throws Exception;
}
