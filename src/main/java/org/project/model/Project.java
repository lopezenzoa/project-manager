package org.project.model;

import org.project.model.enums.Status;
import org.project.model.enums.Active;

import java.util.*;

public class Project {
    private final Integer ID;
    private Admin admin;
    private Leader leader;
    private HashMap<Integer, TeamMember> team;
    private LinkedList<Task> tasks;
    private String name;
    private String creationDate;
    private String deadline;
    private Status status;
    private Active active;

    public Project(Integer ID, Admin admin, Leader leader, HashMap<Integer, TeamMember> team, LinkedList<Task> tasks, String name, String creationDate, String deadline, Status status, Active active) {
        this.ID = ID;
        this.admin = admin;
        this.leader = leader;
        this.team = team;
        this.tasks = tasks;
        this.name = name;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.status = status;
        this.active = active;
    }

    public Integer getID() {
        return ID;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public HashMap<Integer, TeamMember> getTeam() {
        return team;
    }

    public void setTeam(HashMap<Integer, TeamMember> team) {
        this.team = team;
    }

    public LinkedList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }
}
