package shuba.shuba.database;

import android.provider.BaseColumns;

public interface DbContract {

    interface User extends BaseColumns {
        /**
         * The SQLite table name for this entity
         */
        String TABLE                = "user";

        /**
         * The SQLite table columns along with _id exposted by BaseColumns
         */
        String ID                   = "id";
        String LOGIN                = "login";
        String NAME                 = "name";
        String PASSWORD             = "password";
        String EMAIL                = "email";
        String CREATED_AT           = "created_at";
        String GROUP_ID             = "group_id";
        String PICTURE              = "picture";
    }

    interface Group extends BaseColumns {
        /**
         * The SQLite table name for this entity
         */
        String TABLE            = "repositories";

        /**
         * The SQLite table columns along with _id exposted by BaseColumns
         */
        String ID               = "id";
        String NAME             = "name";
        String DESCRIPTION      = "description";
        String MEMBERS          = "members";
        String MANAGER          = "manager_id";
    }

    interface Task extends BaseColumns {
        /**
         * The SQLite table name for this entity
         */
        String TABLE            = "tasks";

        /**
         * The SQLite table columns along with _id exposted by BaseColumns
         */
        String ID               = "id";
        String NAME             = "name";
        String DESCRIPTION      = "description";
        String STATE            = "state";
        String DOER             = "user_id";
    }
}
