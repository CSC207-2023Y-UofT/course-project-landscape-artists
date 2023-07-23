package DBControllers;

import Entities.Column;
import Entities.Project;
import Entities.Task;

public interface IEntityIDsToList {
    public String EntityIDsToList(Project project);
    public String EntityIDsToList(Column columns);
}
