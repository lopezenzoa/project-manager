package org.project.controller.serializers;


import org.json.JSONException;
import org.json.JSONObject;
import org.project.model.User;
import org.project.model.enums.Active;

public abstract class UserSerializer implements IUserSerializer {
    /**
     * Creates a new user using as a base a JSONObject.
     * @param userJSON is the JSONObject used as starting point.
     * */
    @Override
    public User deserialize(JSONObject userJSON) {
        User user = new User();

        try {
            user.setID(userJSON.getInt("ID"));
            user.setName(userJSON.getString("name"));
            user.setEmail(userJSON.getString("email"));
            user.setPassword(userJSON.getString("password"));
            user.setActive(Active.valueOf(userJSON.getString("visibility")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
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

            userJSON.put("ID", user.getID());
            userJSON.put("name", user.getName());
            userJSON.put("email", user.getEmail());
            userJSON.put("password", user.getPassword());
            userJSON.put("active", user.getActive());

        } catch (JSONException e){
            e.printStackTrace();
        }

        return userJSON;
    }
}
