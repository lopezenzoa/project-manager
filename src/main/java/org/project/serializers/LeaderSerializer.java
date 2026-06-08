package org.project.serializers;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.project.model.Leader;
import org.project.model.TeamMember;

import java.util.HashSet;

@AllArgsConstructor
public class LeaderSerializer extends UserSerializer {
    private final UserSerializer teamMemberSerializer;

    /**
     * Creates a new leader using as a base a JSONObject.
     * @param leaderJSON is the JSONObject used as starting point.
     * */
    public Leader deserialize(JSONObject leaderJSON) {
        HashSet<TeamMember> dependants = new HashSet<>();
        HashSet<Integer> ongoingProjects = new HashSet<>();

        try {
            Leader leader = (Leader) super.deserialize(leaderJSON);

            for (Object projectIDJSON : leaderJSON.getJSONArray("ongoingProjects"))
                ongoingProjects.add((Integer) projectIDJSON);

            leader.setOngoingProjectsIds(ongoingProjects);

            JSONArray dependantsJSON = leaderJSON.getJSONArray("dependants");
            for (int i = 0; i < dependantsJSON.length(); i++) {
                JSONObject dependantJSON = dependantsJSON.getJSONObject(i);
                dependants.add((TeamMember) teamMemberSerializer.deserialize(dependantJSON));
            }

            leader.setDependants(dependants);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Serializes the class Leader.
     * @return a JSONObject representation of the class.
     * */
    public JSONObject serialize(Leader leader) {
        JSONObject leaderJSON = null;

        try {
            leaderJSON = super.serialize(leader);
            JSONArray ongoingProjectsJSON = new JSONArray();
            JSONArray dependantsJSON = new JSONArray();

            for (Integer projectID : leader.getOngoingProjectsIds()){
                ongoingProjectsJSON.put(projectID);
            }

            leaderJSON.put("ongoingProjects", ongoingProjectsJSON);

            for(TeamMember dependant : leader.getDependants()){
                dependantsJSON.put(teamMemberSerializer.serialize(dependant));
            }

            leaderJSON.put("dependants", dependantsJSON);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return leaderJSON;
    }
}
