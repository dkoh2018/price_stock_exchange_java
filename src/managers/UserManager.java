package managers;

import exceptions.DataValidationException;
import java.util.TreeMap;
import tradable.TradableDTO;
import users.User;

public class UserManager {
    private static final  UserManager instance = new UserManager();
    private final TreeMap<String, User> users = new TreeMap<>();
    
    private UserManager() {}
    
    public static UserManager getInstance() {
        return instance;
    }
    
    public void init(String[] usersIn) throws DataValidationException {
        if (usersIn == null) {
            throw new DataValidationException("Input user array cannot be null.");
        }
        
        for (String userId : usersIn) {
            User newUser = new User(userId);
            users.put(userId, newUser);
        }
    }
    
    public void updateTradable(String userId, TradableDTO o) throws DataValidationException {
        if (userId == null) {
            throw new DataValidationException("User ID cannot be null");
        }
        if (o == null) {
            throw new DataValidationException("TradableDTO cannot be null");
        }
        if (!users.containsKey(userId)) {
            throw new DataValidationException("User not found");
        }
        
        User user = users.get(userId);
        user.updateTradable(o);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user : users.values()) {
            sb.append(user.toString());
        }
        return sb.toString();
    }
}
