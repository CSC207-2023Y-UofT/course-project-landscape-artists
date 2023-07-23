package DBControllers;

import Entities.*;

import java.util.UUID;

public interface IDBInsert {
    public void DBInsert(Project project);

    public void DBInsert(Column column);

    public void DBInsert(Task task);

    public void DBInsert(UUID uuid);

}
