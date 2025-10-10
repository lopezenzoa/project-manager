package org.project.controller.managers;

import org.project.model.TeamMember;

import java.util.HashSet;

public interface ITeamManager {
    HashSet<Integer> getTeamIDs();
    HashSet<String> getTeamNames();
    boolean addTeamMember(TeamMember member);
    boolean checkMemberInTeam(TeamMember member);
    boolean checkMemberInTeam(Integer ID);
    TeamMember searchMemberByID(Integer ID);
    boolean removeMember(TeamMember member);
    boolean removeMember(Integer ID);
}
