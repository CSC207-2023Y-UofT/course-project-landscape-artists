package DBControllers;

import Entities.Column;
import Entities.Project;
import Entities.Task;

import java.util.UUID;

public interface IDBRemove {
    public void DBRemove(Project project, UUID uuid);
    public void DBRemove(Task task, UUID uuid);
    public void DBRemove(Column column, UUID uuid);
}
