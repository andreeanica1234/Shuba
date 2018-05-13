package shuba.shuba.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andreea on 13/05/2018.
 *
 * This represents the link between the application and the database support. It implements
 * the following functionality:
 *   1. creating the SQLite tables
 *   2. handling database upgrades
 *
 * An SQLiteOpenHelper can also override functionality when opening a connection towards the
 * local database, as well as for handling downgrades.
 */
public class MySqlHelper extends SQLiteOpenHelper {
    /**
     * The name of the SQLite persistence support. If you're using an emulator (or have root
     * support on your device), you can actually pull the database file from the device and on
     * your workstation using the following command:
     *
     *   $ adb pull /data/data/<your-application-package>/database/github.db
     */
    private static final String DB_NAME = "github.db";

    /**
     * The current version of the database. Since we don't have upgrade support, this will remain
     * unchanged for now.
     */
    private static final int DB_VERSION = 1;

    public MySqlHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the profile table (this is where all of the users data will be persisted)
        db.execSQL("CREATE TABLE " + DbContract.User.TABLE + "("
                + DbContract.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + DbContract.User.ID + " INTEGER UNIQUE ON CONFLICT ABORT, "
                + DbContract.User.LOGIN + " TEXT, "
                + DbContract.User.NAME + " TEXT, "
                + DbContract.User.PASSWORD + " TEXT, "
                + DbContract.User.EMAIL + " TEXT, "
                + DbContract.User.CREATED_AT + " TEXT, "
                + DbContract.User.GROUP_ID + " INTEGER, "
          //      + "FOREIGN KEY (" + DbContract.User.GROUP_ID + ") REFERENCES ,"
                + DbContract.User.PICTURE + " TEXT "
                + ")");

        // Create the repository table (this is where all of the group data will be persisted)
        db.execSQL("CREATE TABLE " + DbContract.Group.TABLE + "("
                + DbContract.Group._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + DbContract.Group.ID + " INTEGER UNIQUE ON CONFLICT ABORT, "
                + DbContract.Group.NAME + " TEXT, "
                + DbContract.Group.DESCRIPTION + " TEXT, "
                + DbContract.Group.MEMBERS + " TEXT PRIMARY KEY AUTOINCREMENT,"
                + DbContract.Group.MANAGER + "INTEGER "
                + ")");

        db.execSQL("CREATE TABLE " + DbContract.Task.TABLE + "("
                + DbContract.Task._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + DbContract.Task.ID + " INTEGER UNIQUE ON CONFLICT ABORT, "
                + DbContract.Task.NAME + " TEXT, "
                + DbContract.Task.DESCRIPTION + " TEXT, "
                + DbContract.Task.STATE + " BOOLEAN, "
                + DbContract.Task.DOER + "INTEGER"
                + ")");

        // Notice how the GitHub IDs have a UNIQUE clause which leads to aborting on conflict. This
        // is used to prevent accidentally inserting the same entity twice and throwing an exception
        // in case this were to happen.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // We are currently not supporting database upgrades, so just clean it up and re-create it
        db.execSQL("DROP TABLE " + DbContract.User.TABLE);
        onCreate(db);
    }
}
