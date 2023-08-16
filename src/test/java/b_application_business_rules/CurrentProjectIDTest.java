package b_application_business_rules;

import b_application_business_rules.use_cases.CurrentProjectID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CurrentProjectIDTest {

    private CurrentProjectID currentProjectID;

    @BeforeEach
    public void setup() {
        currentProjectID = new CurrentProjectID(null);
    }

    @Test
    public void testGetSelectedProjectID() {
        assertNull(currentProjectID.getSelectedProjectID());
    }

    @Test
    public void testSetSelectedProjectID() {
        UUID uuid = UUID.randomUUID();
        currentProjectID.setSelectedProjectID(uuid);
        assertEquals(uuid, currentProjectID.getSelectedProjectID());
    }

    @Test
    public void testRemoveCurrentProjectID() {
        UUID uuid = UUID.randomUUID();
        currentProjectID.setSelectedProjectID(uuid);
        currentProjectID.removeCurrentProjectID();
        assertNull(currentProjectID.getSelectedProjectID());
    }
}
