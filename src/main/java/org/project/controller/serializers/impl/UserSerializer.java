package org.project.controller.serializers.impl;


import org.json.JSONException;
import org.json.JSONObject;
import org.project.controller.serializers.IUserSerializer;
import org.project.model.User;
import org.project.model.enums.Active;

public abstract class UserSerializer implements IUserSerializer {
    /**
     * Creates a new user using as a base a JSONObject.
     * @param userJSON is the JSONObject used as starting point.
     * */
    @Override
    public User deserialize(JSONObject userJSON) {
        try {
            Integer ID = userJSON.getInt("ID");
            String name = userJSON.getString("name");
            String email = userJSON.getString("email");
            String password = userJSON.getString("password");
            Active active = Active.valueOf(userJSON.getString("active"));

            return new User(ID, name, email, password, active);
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
