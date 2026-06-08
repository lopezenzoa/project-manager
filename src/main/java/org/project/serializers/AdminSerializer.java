package org.project.serializers;


import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.project.model.Admin;
import org.project.model.Leader;

import java.util.HashSet;

@AllArgsConstructor
public class AdminSerializer extends UserSerializer {
    private final UserSerializer leaderSerializer;

    /**
     * Creates a new admin using as a base a JSONObject.
     * @param adminJSON is the JSONObject used as starting point.
     * */
    public Admin deserialize(JSONObject adminJSON) {
        HashSet<Leader> dependants = new HashSet<>();

        try {
            Admin admin = (Admin) super.deserialize(adminJSON);

            JSONArray dependantsJSON = adminJSON.getJSONArray("dependants");
            for (int i = 0; i < dependantsJSON.length(); i++) {
                JSONObject dependantJSON = dependantsJSON.getJSONObject(i);

                dependants.add((Leader) leaderSerializer.deserialize(dependantJSON));
                admin.setDependants(dependants);
            }

            return admin;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Serializes the class Admin.
     * @return a JSONObject representation of the class.
     * */
    public JSONObject serialize(Admin admin) {
        JSONObject adminJSON = null;

        try {
            adminJSON = super.serialize(admin);
            JSONArray dependantsJSON = new JSONArray();

            for(Leader dependant : admin.getDependants()) {
                dependantsJSON.put(leaderSerializer.serialize(dependant));
            }

            adminJSON.put("dependants", dependantsJSON);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return adminJSON;
    }
}
