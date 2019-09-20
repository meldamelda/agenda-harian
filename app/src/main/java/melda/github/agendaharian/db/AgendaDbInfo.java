package melda.github.agendaharian.db;

import android.provider.BaseColumns;

public class AgendaDbInfo {
    public static final String DB_NAME = "melda.github.agendaharian.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns{
        public static final String TABLE_NAME = "agenda";
        public static final String COL_TODO_TITLE = "todo";
    }
}
