package com.onesignal.core.internal.database.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.database.ICursor;
import com.onesignal.core.internal.database.IDatabase;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.session.internal.outcomes.impl.OutcomeTableProvider;
import com.onesignal.session.internal.outcomes.impl.OutcomesDbContract;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: OSDatabase.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0010\u0018\u0000 72\u00020\u00012\u00020\u0002:\u00017B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ/\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J$\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J$\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J \u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002J\u0010\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0013H\u0016J \u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0016J \u0010 \u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0016J{\u0010!\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00102\b\u0010#\u001a\u0004\u0018\u00010\r2\b\u0010$\u001a\u0004\u0018\u00010\r2\b\u0010%\u001a\u0004\u0018\u00010\r2\b\u0010&\u001a\u0004\u0018\u00010\r2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u000b0(H\u0016¢\u0006\u0002\u0010*J\u0018\u0010+\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\rH\u0002J7\u0010-\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J\u0010\u00100\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J\u0010\u00101\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J\u0010\u00102\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J\u0010\u00103\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J\u0010\u00104\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J\u0010\u00105\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J\u0010\u00106\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/onesignal/core/internal/database/impl/OSDatabase;", "Landroid/database/sqlite/SQLiteOpenHelper;", "Lcom/onesignal/core/internal/database/IDatabase;", "_outcomeTableProvider", "Lcom/onesignal/session/internal/outcomes/impl/OutcomeTableProvider;", "context", "Landroid/content/Context;", "version", "", "(Lcom/onesignal/session/internal/outcomes/impl/OutcomeTableProvider;Landroid/content/Context;I)V", "delete", "", "table", "", "whereClause", "whereArgs", "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "getSQLiteDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "getSQLiteDatabaseWithRetries", "insert", "nullColumnHack", "values", "Landroid/content/ContentValues;", "insertOrThrow", "internalOnUpgrade", "db", "oldVersion", "newVersion", "onCreate", "onDowngrade", "onUpgrade", "query", "columns", "groupBy", "having", "orderBy", "limit", "action", "Lkotlin/Function1;", "Lcom/onesignal/core/internal/database/ICursor;", "(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "safeExecSQL", "sql", "update", "(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "upgradeFromV5ToV6", "upgradeToV2", "upgradeToV3", "upgradeToV4", "upgradeToV5", "upgradeToV7", "upgradeToV8", "upgradeToV9", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public class OSDatabase extends SQLiteOpenHelper implements IDatabase {
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_NAME = "OneSignal.db";
    private static final int DB_OPEN_RETRY_BACKOFF = 400;
    private static final int DB_OPEN_RETRY_MAX = 5;
    private static final int DB_VERSION = 9;
    public static final int DEFAULT_TTL_IF_NOT_IN_PAYLOAD = 259200;
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String INTEGER_PRIMARY_KEY_TYPE = " INTEGER PRIMARY KEY";
    private static final String INT_TYPE = " INTEGER";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE notification (_id INTEGER PRIMARY KEY,notification_id TEXT,android_notification_id INTEGER,group_id TEXT,collapse_id TEXT,is_summary INTEGER DEFAULT 0,opened INTEGER DEFAULT 0,dismissed INTEGER DEFAULT 0,title TEXT,message TEXT,full_data TEXT,created_time TIMESTAMP DEFAULT (strftime('%s', 'now')),expire_time TIMESTAMP);";
    private static final String SQL_CREATE_IN_APP_MESSAGE_ENTRIES = "CREATE TABLE in_app_message (_id INTEGER PRIMARY KEY,display_quantity INTEGER,last_display INTEGER,message_id TEXT,displayed_in_session INTEGER,click_ids TEXT);";
    private static final String TEXT_TYPE = " TEXT";
    private static final String TIMESTAMP_TYPE = " TIMESTAMP";
    private final OutcomeTableProvider _outcomeTableProvider;
    public static final Companion Companion = new Companion(null);
    private static final Object LOCK = new Object();
    private static final String[] SQL_INDEX_ENTRIES = {OneSignalDbContract.NotificationTable.INDEX_CREATE_NOTIFICATION_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_ANDROID_NOTIFICATION_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_GROUP_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_COLLAPSE_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_CREATED_TIME, OneSignalDbContract.NotificationTable.INDEX_CREATE_EXPIRE_TIME};

    public /* synthetic */ OSDatabase(OutcomeTableProvider outcomeTableProvider, Context context, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(outcomeTableProvider, context, (i2 & 4) != 0 ? 9 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OSDatabase(OutcomeTableProvider _outcomeTableProvider, Context context, int version) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, version);
        Intrinsics.checkNotNullParameter(_outcomeTableProvider, "_outcomeTableProvider");
        this._outcomeTableProvider = _outcomeTableProvider;
    }

    private final SQLiteDatabase getSQLiteDatabase() {
        SQLiteDatabase writableDatabase;
        synchronized (LOCK) {
            try {
                writableDatabase = getWritableDatabase();
                Intrinsics.checkNotNullExpressionValue(writableDatabase, "{\n                writableDatabase\n            }");
            } catch (SQLiteCantOpenDatabaseException e) {
                throw e;
            } catch (SQLiteDatabaseLockedException e2) {
                throw e2;
            }
        }
        return writableDatabase;
    }

    private final SQLiteDatabase getSQLiteDatabaseWithRetries() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (LOCK) {
            int count = 0;
            while (true) {
                try {
                    sQLiteDatabase = getSQLiteDatabase();
                } catch (SQLiteCantOpenDatabaseException e) {
                    count++;
                    if (count >= 5) {
                        throw e;
                    }
                    SystemClock.sleep(count * DB_OPEN_RETRY_BACKOFF);
                } catch (SQLiteDatabaseLockedException e2) {
                    count++;
                    if (count >= 5) {
                        throw e2;
                    }
                    SystemClock.sleep(count * DB_OPEN_RETRY_BACKOFF);
                }
            }
        }
        return sQLiteDatabase;
    }

    @Override // com.onesignal.core.internal.database.IDatabase
    public void query(String table, String[] columns, String whereClause, String[] whereArgs, String groupBy, String having, String orderBy, String limit, Function1<? super ICursor, Unit> function1) {
        Object cursor;
        Intrinsics.checkNotNullParameter(table, "table");
        Intrinsics.checkNotNullParameter(function1, "action");
        synchronized (LOCK) {
            if (limit == null) {
                Object objQuery = getSQLiteDatabaseWithRetries().query(table, columns, whereClause, whereArgs, groupBy, having, orderBy);
                Intrinsics.checkNotNullExpressionValue(objQuery, "getSQLiteDatabaseWithRet…By,\n                    )");
                cursor = objQuery;
            } else {
                Object cursor2 = getSQLiteDatabaseWithRetries().query(table, columns, whereClause, whereArgs, groupBy, having, orderBy, limit);
                Intrinsics.checkNotNullExpressionValue(cursor2, "getSQLiteDatabaseWithRet…it,\n                    )");
                cursor = cursor2;
            }
            Unit unit = Unit.INSTANCE;
        }
        Cursor cursor3 = (Closeable) cursor;
        try {
            Cursor it = cursor3;
            DatabaseCursor dbCursor = new DatabaseCursor(it);
            function1.invoke(dbCursor);
            Unit unit2 = Unit.INSTANCE;
            CloseableKt.closeFinally(cursor3, (Throwable) null);
        } finally {
        }
    }

    @Override // com.onesignal.core.internal.database.IDatabase
    public void insert(String table, String nullColumnHack, ContentValues values) {
        Intrinsics.checkNotNullParameter(table, "table");
        synchronized (LOCK) {
            SQLiteDatabase writableDb = getSQLiteDatabaseWithRetries();
            try {
                try {
                    try {
                        writableDb.beginTransaction();
                        writableDb.insert(table, nullColumnHack, values);
                        writableDb.setTransactionSuccessful();
                    } catch (IllegalStateException e) {
                        Logging.error("Error under inserting transaction under table: " + table + " with nullColumnHack: " + nullColumnHack + " and values: " + values, e);
                        try {
                            writableDb.endTransaction();
                        } catch (SQLiteException e2) {
                            Logging.error("Error closing transaction! ", e2);
                        } catch (IllegalStateException e3) {
                            Logging.error("Error closing transaction! ", e3);
                        }
                    }
                } finally {
                    try {
                        try {
                            writableDb.endTransaction();
                        } catch (IllegalStateException e4) {
                            Logging.error("Error closing transaction! ", e4);
                        }
                    } catch (SQLiteException e5) {
                        Logging.error("Error closing transaction! ", e5);
                    }
                }
            } catch (SQLiteException e6) {
                Logging.error("Error inserting on table: " + table + " with nullColumnHack: " + nullColumnHack + " and values: " + values, e6);
                try {
                    try {
                        writableDb.endTransaction();
                    } catch (SQLiteException e7) {
                        Logging.error("Error closing transaction! ", e7);
                    }
                } catch (IllegalStateException e8) {
                    Logging.error("Error closing transaction! ", e8);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.onesignal.core.internal.database.IDatabase
    public void insertOrThrow(String table, String nullColumnHack, ContentValues values) throws SQLException {
        Intrinsics.checkNotNullParameter(table, "table");
        synchronized (LOCK) {
            SQLiteDatabase writableDb = getSQLiteDatabaseWithRetries();
            try {
                try {
                    try {
                        writableDb.beginTransaction();
                        writableDb.insertOrThrow(table, nullColumnHack, values);
                        writableDb.setTransactionSuccessful();
                    } catch (IllegalStateException e) {
                        Logging.error("Error under inserting or throw transaction under table: " + table + " with nullColumnHack: " + nullColumnHack + " and values: " + values, e);
                        try {
                            writableDb.endTransaction();
                        } catch (SQLiteException e2) {
                            Logging.error("Error closing transaction! ", e2);
                        } catch (IllegalStateException e3) {
                            Logging.error("Error closing transaction! ", e3);
                        }
                    }
                } finally {
                    try {
                        try {
                            writableDb.endTransaction();
                        } catch (IllegalStateException e4) {
                            Logging.error("Error closing transaction! ", e4);
                        }
                    } catch (SQLiteException e5) {
                        Logging.error("Error closing transaction! ", e5);
                    }
                }
            } catch (SQLiteException e6) {
                Logging.error("Error inserting or throw on table: " + table + " with nullColumnHack: " + nullColumnHack + " and values: " + values, e6);
                try {
                    try {
                        writableDb.endTransaction();
                    } catch (SQLiteException e7) {
                        Logging.error("Error closing transaction! ", e7);
                    }
                } catch (IllegalStateException e8) {
                    Logging.error("Error closing transaction! ", e8);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.onesignal.core.internal.database.IDatabase
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        Intrinsics.checkNotNullParameter(table, "table");
        Intrinsics.checkNotNullParameter(values, "values");
        int result = 0;
        String string = values.toString();
        Intrinsics.checkNotNullExpressionValue(string, "values.toString()");
        if (string.length() == 0) {
            return 0;
        }
        synchronized (LOCK) {
            SQLiteDatabase writableDb = getSQLiteDatabaseWithRetries();
            try {
                try {
                    try {
                        writableDb.beginTransaction();
                        result = writableDb.update(table, values, whereClause, whereArgs);
                        writableDb.setTransactionSuccessful();
                        try {
                            try {
                                writableDb.endTransaction();
                            } catch (IllegalStateException e) {
                                Logging.error("Error closing transaction! ", e);
                            }
                        } catch (SQLiteException e2) {
                            Logging.error("Error closing transaction! ", e2);
                        }
                    } catch (IllegalStateException e3) {
                        Logging.error("Error under update transaction under table: " + table + " with whereClause: " + whereClause + " and whereArgs: " + whereArgs, e3);
                        try {
                            writableDb.endTransaction();
                        } catch (SQLiteException e4) {
                            Logging.error("Error closing transaction! ", e4);
                        } catch (IllegalStateException e5) {
                            Logging.error("Error closing transaction! ", e5);
                        }
                    }
                } catch (Throwable th) {
                    try {
                        try {
                            writableDb.endTransaction();
                        } catch (SQLiteException e6) {
                            Logging.error("Error closing transaction! ", e6);
                        }
                    } catch (IllegalStateException e7) {
                        Logging.error("Error closing transaction! ", e7);
                    }
                    throw th;
                }
            } catch (SQLiteException e8) {
                Logging.error("Error updating on table: " + table + " with whereClause: " + whereClause + " and whereArgs: " + whereArgs, e8);
                try {
                    try {
                        writableDb.endTransaction();
                    } catch (SQLiteException e9) {
                        Logging.error("Error closing transaction! ", e9);
                    }
                } catch (IllegalStateException e10) {
                    Logging.error("Error closing transaction! ", e10);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        return result;
    }

    @Override // com.onesignal.core.internal.database.IDatabase
    public void delete(String table, String whereClause, String[] whereArgs) {
        Intrinsics.checkNotNullParameter(table, "table");
        synchronized (LOCK) {
            SQLiteDatabase writableDb = getSQLiteDatabaseWithRetries();
            try {
                try {
                    try {
                        writableDb.beginTransaction();
                        writableDb.delete(table, whereClause, whereArgs);
                        writableDb.setTransactionSuccessful();
                    } catch (IllegalStateException e) {
                        Logging.error("Error under delete transaction under table: " + table + " with whereClause: " + whereClause + " and whereArgs: " + whereArgs, e);
                        try {
                            writableDb.endTransaction();
                        } catch (SQLiteException e2) {
                            Logging.error("Error closing transaction! ", e2);
                        } catch (IllegalStateException e3) {
                            Logging.error("Error closing transaction! ", e3);
                        }
                    }
                } finally {
                    try {
                        try {
                            writableDb.endTransaction();
                        } catch (IllegalStateException e4) {
                            Logging.error("Error closing transaction! ", e4);
                        }
                    } catch (SQLiteException e5) {
                        Logging.error("Error closing transaction! ", e5);
                    }
                }
            } catch (SQLiteException e6) {
                Logging.error("Error deleting on table: " + table + " with whereClause: " + whereClause + " and whereArgs: " + whereArgs, e6);
                try {
                    try {
                        writableDb.endTransaction();
                    } catch (SQLiteException e7) {
                        Logging.error("Error closing transaction! ", e7);
                    }
                } catch (IllegalStateException e8) {
                    Logging.error("Error closing transaction! ", e8);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(OutcomesDbContract.SQL_CREATE_OUTCOME_ENTRIES_V4);
        db.execSQL(OutcomesDbContract.SQL_CREATE_UNIQUE_OUTCOME_ENTRIES_V2);
        db.execSQL(SQL_CREATE_IN_APP_MESSAGE_ENTRIES);
        for (String ind : SQL_INDEX_ENTRIES) {
            db.execSQL(ind);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Intrinsics.checkNotNullParameter(db, "db");
        Logging.debug$default("OneSignal Database onUpgrade from: " + oldVersion + " to: " + newVersion, null, 2, null);
        try {
            internalOnUpgrade(db, oldVersion, newVersion);
        } catch (SQLiteException e) {
            Logging.error("Error in upgrade, migration may have already run! Skipping!", e);
        }
    }

    private final synchronized void internalOnUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2 && newVersion >= 2) {
            try {
                upgradeToV2(db);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (oldVersion < 3 && newVersion >= 3) {
            upgradeToV3(db);
        }
        if (oldVersion < 4 && newVersion >= 4) {
            upgradeToV4(db);
        }
        if (oldVersion < 5 && newVersion >= 5) {
            upgradeToV5(db);
        }
        if (oldVersion == 5 && newVersion >= 6) {
            upgradeFromV5ToV6(db);
        }
        if (oldVersion < 7 && newVersion >= 7) {
            upgradeToV7(db);
        }
        if (oldVersion < 8 && newVersion >= 8) {
            upgradeToV8(db);
        }
        if (oldVersion < 9 && newVersion >= 9) {
            upgradeToV9(db);
        }
    }

    private final void upgradeToV2(SQLiteDatabase db) {
        safeExecSQL(db, "ALTER TABLE notification ADD COLUMN collapse_id TEXT;");
        safeExecSQL(db, OneSignalDbContract.NotificationTable.INDEX_CREATE_GROUP_ID);
    }

    private final void upgradeToV3(SQLiteDatabase db) {
        safeExecSQL(db, "ALTER TABLE notification ADD COLUMN expire_time TIMESTAMP;");
        safeExecSQL(db, "UPDATE notification SET expire_time = created_time + 259200;");
        safeExecSQL(db, OneSignalDbContract.NotificationTable.INDEX_CREATE_EXPIRE_TIME);
    }

    private final void upgradeToV4(SQLiteDatabase db) {
        safeExecSQL(db, OutcomesDbContract.SQL_CREATE_OUTCOME_ENTRIES_V1);
    }

    private final void upgradeToV5(SQLiteDatabase db) {
        safeExecSQL(db, OutcomesDbContract.SQL_CREATE_UNIQUE_OUTCOME_ENTRIES_V1);
        upgradeFromV5ToV6(db);
    }

    private final void upgradeFromV5ToV6(SQLiteDatabase db) {
        this._outcomeTableProvider.upgradeOutcomeTableRevision1To2(db);
    }

    private final void upgradeToV7(SQLiteDatabase db) {
        safeExecSQL(db, SQL_CREATE_IN_APP_MESSAGE_ENTRIES);
    }

    private final void safeExecSQL(SQLiteDatabase db, String sql) {
        try {
            db.execSQL(sql);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    private final synchronized void upgradeToV8(SQLiteDatabase db) {
        this._outcomeTableProvider.upgradeOutcomeTableRevision2To3(db);
        this._outcomeTableProvider.upgradeCacheOutcomeTableRevision1To2(db);
    }

    private final void upgradeToV9(SQLiteDatabase db) {
        this._outcomeTableProvider.upgradeOutcomeTableRevision3To4(db);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Intrinsics.checkNotNullParameter(db, "db");
        Logging.warn$default("SDK version rolled back! Clearing OneSignal.db as it could be in an unexpected state.", null, 2, null);
        Cursor cursorRawQuery = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        try {
            Cursor it = cursorRawQuery;
            List<String> tables = new ArrayList(it.getCount());
            while (it.moveToNext()) {
                String string = it.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "it.getString(0)");
                tables.add(string);
            }
            for (String table : tables) {
                if (!StringsKt.startsWith$default(table, "sqlite_", false, 2, (Object) null)) {
                    db.execSQL("DROP TABLE IF EXISTS " + table);
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, (Throwable) null);
            onCreate(db);
        } finally {
        }
    }

    /* JADX INFO: compiled from: OSDatabase.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/onesignal/core/internal/database/impl/OSDatabase$Companion;", "", "()V", "COMMA_SEP", "", "DATABASE_NAME", "DB_OPEN_RETRY_BACKOFF", "", "DB_OPEN_RETRY_MAX", "DB_VERSION", "DEFAULT_TTL_IF_NOT_IN_PAYLOAD", "FLOAT_TYPE", "INTEGER_PRIMARY_KEY_TYPE", "INT_TYPE", "LOCK", "SQL_CREATE_ENTRIES", "SQL_CREATE_IN_APP_MESSAGE_ENTRIES", "SQL_INDEX_ENTRIES", "", "[Ljava/lang/String;", "TEXT_TYPE", "TIMESTAMP_TYPE", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
