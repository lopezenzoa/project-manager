package org.project.serializers;


import org.json.JSONException;
import org.json.JSONObject;
import org.project.builders.UserBuilder;
import org.project.model.User;
import org.project.model.enums.Role;
import org.project.serializers.interfaces.Serializable;

public class UserSerializer implements Serializable<User> {
    /**
     * Creates a new user using as a base a JSONObject.
     * @param userJSON is the JSONObject used as starting point.
     * */
    @Override
    public User deserialize(JSONObject userJSON) {
        try {
            Integer id = userJSON.getInt("id");
            String name = userJSON.getString("name");
            String email = userJSON.getString("email");
            String password = userJSON.getString("password");
            Boolean isActive = userJSON.getBoolean("isActive");
            Role role = Role.valueOf(userJSON.getString("role"));

            return new UserBuilder()
                    .setId(id)
                    .setName(name)
                    .setEmail(email)
                    .setPassword(password)
                    .setActive(isActive)
                    .setRole(role)
                    .build();
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

            userJSON.put("id", user.getId());
            userJSON.put("name", user.getName());
            userJSON.put("email", user.getEmail());
            userJSON.put("password", user.getPassword());
            userJSON.put("isActive", user.getIsActive());
            userJSON.put("role", user.getRole());

        } catch (JSONException e){
            e.printStackTrace();
        }

        return userJSON;
    }
}
