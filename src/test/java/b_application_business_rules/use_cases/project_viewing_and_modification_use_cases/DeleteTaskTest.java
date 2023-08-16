package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteTaskTest {

    @Mock
    private Project mockProject;

    @Mock
    private Column mockColumn;

    @Mock
    private Task mockTask;

    @Mock
    private IDBRemove databaseRemover;

    @Mock
    private IDBInsert databaseInserter;

    private DeleteTask deleteTask;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteTask = new DeleteTask(mockProject);
    }

    @Test
    public void testDeleteTask() {
        UUID columnID = UUID.randomUUID();
        UUID taskID = UUID.randomUUID();
        TaskModel taskModel = new TaskModel("Test Task", taskID, "Task Description", false, LocalDateTime.now());

        List<Column> columns = new ArrayList<>();
        columns.add(mockColumn);

        when(mockProject.getColumns()).thenReturn(columns);
        when(Column.IDToColumn(columnID, columns)).thenReturn(mockColumn);
        when(mockColumn.getTasks()).thenReturn(new ArrayList<>(List.of(mockTask)));
        when(Task.IDToTask(taskID, new ArrayList<>(List.of(mockTask)))).thenReturn(mockTask);

        deleteTask.deleteTask(columnID, taskModel);

        verify(mockColumn, times(1)).removeTask(eq(mockTask));
        verify(databaseRemover, times(1)).DBRemoveTask(eq(taskModel.getID()));
        verify(databaseRemover, times(1)).DBRemoveColumn(eq(columnID));
        verify(databaseInserter, times(1)).DBInsert(any(ColumnModel.class));
    }
}
