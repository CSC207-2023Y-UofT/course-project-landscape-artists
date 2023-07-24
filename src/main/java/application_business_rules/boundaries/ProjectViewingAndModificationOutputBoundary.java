package application_business_rules.boundaries;//this boundary will be the output boundary when we are vieweing and modifying a single project.
//
//this boundary will be responsable for telling the outer classes what to do and what to show

import enterprise_business_rules.entities.Project;

public interface ProjectViewingAndModificationOutputBoundary {
    Project getCurrentProject();
    void displayAllProjects();
}