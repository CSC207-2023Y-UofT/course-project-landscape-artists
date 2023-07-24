package application_business_rules.boundaries;//this boundary will be the input boundary when we are vieweing and modifying a single project.
//
//this boundary will be responsable for getting the information from the outer layers and doing the work (running the right use cases, etc.)

public interface ProjectViewingAndModificationInputBoundary {
    public void removeCurrentProject();
}