package b_application_business_rules.use_cases;

import java.util.UUID;

/**
 * The CurrentProjectID class holds the currently selected project's UUID. It provides a single point
 * of access to the selected project's ID throughout the application.
 */
public class CurrentProjectID {

    private static final CurrentProjectID currentProjectID = new CurrentProjectID(null);

    private UUID[] selectedProjectID = new UUID[1];

    /**
     * Initializes the CurrentProjectID instance with the provided UUID.
     *
     * @param uuid The UUID of the initially selected project.
     */
    public CurrentProjectID(UUID uuid){
        this.selectedProjectID[0] = uuid;
    }

    /**
     * Returns the singleton instance of CurrentProjectID.
     *
     * @return The CurrentProjectID singleton instance.
     */
    public static CurrentProjectID getCurrentProjectID(){
        return currentProjectID;
    }

    /**
     * Returns the UUID of the currently selected project.
     *
     * @return The UUID of the currently selected project.
     */
    public UUID getSelectedProjectID() {
        return selectedProjectID[0];
    }

    /**
     * Sets the UUID of the currently selected project.
     *
     * @param selectedProjectID The UUID of the project to be set as the currently selected project.
     */
    public void setSelectedProjectID(UUID selectedProjectID) {
        this.selectedProjectID[0] = selectedProjectID;
    }

    /**
     * Removes the UUID of the currently selected project, effectively deselecting any project.
     */
    public void removeCurrentProjectID(){
        this.setSelectedProjectID(null);
    }
}

