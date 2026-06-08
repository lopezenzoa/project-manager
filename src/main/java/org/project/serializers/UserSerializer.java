package org.project.serializers;


import org.json.JSONException;
import org.json.JSONObject;
import org.project.model.User;
import org.project.serializers.interfaces.Serializable;

public abstract class UserSerializer implements Serializable<User> {
    /**
     * Creates a new user using as a base a JSONObject.
     * @param userJSON is the JSONObject used as starting point.
     * */
    @Override
    public User deserialize(JSONObject userJSON) {
        try {
            Integer ID = userJSON.getInt("userId");
            String name = userJSON.getString("name");
            String email = userJSON.getString("email");
            String password = userJSON.getString("password");
            Boolean isActive = userJSON.getBoolean("isActive");

            return new User(ID, name, email, password, isActive);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Serializes the class User.
     * @return a JSONObject representation of the class.
     * */
    @Override
    public JSONObject serialize(User user) {
        JSONObject userJSON = null;

        try {
            userJSON = new JSONObject();

            userJSON.put("userId", user.getUserId());
            userJSON.put("name", user.getName());
            userJSON.put("email", user.getEmail());
            userJSON.put("password", user.getPassword());
            userJSON.put("isActive", user.getIsActive());

        } catch (JSONException e){
            e.printStackTrace();
        }

        return userJSON;
    }
}
