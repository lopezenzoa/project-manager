package org.project.view.impl;

import org.project.model.Admin;
import org.project.model.Leader;
import org.project.model.TeamMember;
import org.project.model.User;
import org.project.view.IUserView;

public class UserView implements IUserView<User> {
    @Override
    public void printUser(User user) {
        System.out.println(user);
    }

    @Override
    public void printDependants(User user) {
        System.out.println("Dependants");

        if (user instanceof Admin)
            for (Leader dependant : ((Admin) user).getDependants())
                System.out.println("  " + dependant.getName() + "\n");
        else
            for (TeamMember dependant : ((Leader) user).getDependants())
                System.out.println("  " + dependant.getName() + "\n");
    }

    @Override
    public void printOngoingProjects(User user) {
        System.out.println("On-Going Projects");

        if (user instanceof Leader)
            for (Integer projectID: ((Leader) user).getOngoingProjects())
                System.out.println("  " + projectID + "\n");
        else
            for (Integer projectID: ((TeamMember) user).getOngoingProjects())
                System.out.println("  " + projectID + "\n");
    }
}
