package org.project.view;

import org.project.model.Admin;
import org.project.model.Leader;
import org.project.model.TeamMember;
import org.project.model.User;

public class UserView<T> {
    
    public void printUser(T t) {
        System.out.println(t);
    }

    public void printDependants(T t) {
        System.out.println("Dependants");

        if (t instanceof Admin)
            for (Leader dependant : ((Admin) t).getDependants())
                System.out.println("  " + dependant.getName() + "\n");
        else
            for (TeamMember dependant : ((Leader) t).getDependants())
                System.out.println("  " + dependant.getName() + "\n");
    }

    public void printOngoingProjects(T t) {
        System.out.println("On-Going Projects");

        if (t instanceof Leader)
            for (Integer projectId: ((Leader) t).getOngoingProjectsIds())
                System.out.println("  " + projectId + "\n");
        else
            for (Integer projectID: ((TeamMember) t).getOngoingProjectsIds())
                System.out.println("  " + projectID + "\n");
    }
}
