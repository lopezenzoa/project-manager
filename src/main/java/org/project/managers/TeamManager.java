package org.project.managers;

import lombok.AllArgsConstructor;
import org.project.model.TeamMember;

import java.util.HashMap;
import java.util.HashSet;

@AllArgsConstructor
public class TeamManager {
    private final HashMap<Integer, TeamMember> team;

    /**
     * Returns a collection of team members IDs.
     * @return a HashSet made of team IDs.
     * */
    public HashSet<Integer> getTeamIDs() {
        return new HashSet<>(team.keySet());
    }

    /**
     * Returns a collection of team members names.
     * @return a HashSet made of team names.
     * */
    public HashSet<String> getTeamNames() {
        HashSet<String> names = new HashSet<>();

        for (TeamMember member : team.values())
            names.add(member.getName());

        return names;
    }

    /**
     * Adds a member to the project's team.
     * @param member is the object that want to add.
     * @return a boolean value depending on if the member could be added or not.
     * */
    public boolean addTeamMember(TeamMember member) {
        if (!team.containsKey(member.getUserId())) {
            team.put(member.getUserId(), member);
            return true;
        }
        else
            return false;
    }

    /**
     * Checks if a member exists in the project's team.
     * @param member is the object that want to check its existence.
     * @return a boolean value depending on if the member exists or not.
     * */
    public boolean checkMemberInTeam(TeamMember member) {
        return team.containsKey(member.getUserId());
    }

    /**
     * Checks if a task exists in the project's team given its ID.
     * @param ID is the ID of the member that want to check its existence.
     * @return a boolean value depending on if the member exists or not.
     * */
    public boolean checkMemberInTeam(Integer ID) {
        return team.containsKey(ID);
    }

    /**
     * Searches a member in the project's team.
     * @param ID is the ID of the member that want to search.
     * @return a Task object if the ID corresponds with a member or null otherwise.
     * */
    public TeamMember searchMemberByID(Integer ID) {
        for (Integer memberID : team.keySet())
            if (memberID.equals(ID))
                return team.get(memberID);
        return null;
    }

    /**
     * Removes a member from the project's member.
     * @param member is the object that want to remove.
     * @return a boolean value depending on if the task could be removed or not.
     * */
    public boolean removeMember(TeamMember member) {
        if (team.containsKey(member.getUserId())) {
            team.remove(member.getUserId());
            return true;
        }
        else
            return false;
    }

    /**
     * Removes a member from the project's team given its ID.
     * @param ID is the ID of the member that want to remove.
     * @return a boolean value depending on if the member could be removed or not.
     * */
    public boolean removeMember(Integer ID) {
        TeamMember toDelete = searchMemberByID(ID);

        if (toDelete != null) {
            team.remove(toDelete.getUserId());
            return true;
        }

        return false;
    }
}
