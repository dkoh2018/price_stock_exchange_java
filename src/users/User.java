package users;

import java.util.HashMap;
import tradable.TradableDTO;

public class User {
    private final String userId;
    private final HashMap<String, TradableDTO> tradables = new HashMap<>();
    
    public User(String id) {
        setUserId(id);
        this.userId = id;
    }
    
    private void setUserId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (id.length() != 3) {
            throw new IllegalArgumentException("User ID must be exactly 3 characters");
        }
        if (!id.matches("[a-zA-Z]{3}")) {
            throw new IllegalArgumentException("User ID must contain only letters");
        }
    }
    
    public void updateTradable(TradableDTO o) {
        if (o != null) {
            tradables.put(o.getId(), o);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User Id: ").append(this.userId).append("\n");
        for (TradableDTO tradable : tradables.values()) {
            sb.append(tradable.toString()).append("\n");
        }
        return sb.toString();
    }
}
