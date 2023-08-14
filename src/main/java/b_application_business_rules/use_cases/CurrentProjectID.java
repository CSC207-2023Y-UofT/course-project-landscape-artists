package b_application_business_rules.use_cases;

import java.util.UUID;

public class CurrentProjectID {

    private static final CurrentProjectID currentProjectID = new CurrentProjectID(null);

    private final UUID[] selectedProjectID = new UUID[1];
    public CurrentProjectID(UUID uuid){
        this.selectedProjectID[0] = uuid;
    }
    public static CurrentProjectID getCurrentProjectID(){
        return currentProjectID;
    }

    public UUID getSelectedProjectID() {
        return selectedProjectID[0];
    }

    public void setSelectedProjectID(UUID selectedProjectID) {
        this.selectedProjectID[0] = selectedProjectID;
    }

    public void removeCurrentProjectID(){
        this.setSelectedProjectID(null);
    }
}
