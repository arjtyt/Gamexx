package com.onesignal.session.internal.outcomes.impl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.onesignal.core.BuildConfig;
import com.onesignal.session.internal.influence.InfluenceChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OutcomeTableProvider.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lcom/onesignal/session/internal/outcomes/impl/OutcomeTableProvider;", "", "()V", "upgradeCacheOutcomeTableRevision1To2", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "upgradeOutcomeTableRevision1To2", "upgradeOutcomeTableRevision2To3", "upgradeOutcomeTableRevision3To4", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OutcomeTableProvider {
    public final void upgradeOutcomeTableRevision1To2(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        try {
            try {
                db.execSQL("BEGIN TRANSACTION;");
                db.execSQL("CREATE TEMPORARY TABLE outcome_backup(_id,session,notification_ids,name,timestamp);");
                db.execSQL("INSERT INTO outcome_backup SELECT _id,session,notification_ids,name,timestamp FROM outcome;");
                db.execSQL("DROP TABLE outcome;");
                db.execSQL(OutcomesDbContract.SQL_CREATE_OUTCOME_ENTRIES_V2);
                db.execSQL("INSERT INTO outcome (_id,session,notification_ids,name,timestamp, weight) SELECT _id,session,notification_ids,name,timestamp, 0 FROM outcome_backup;");
                db.execSQL("DROP TABLE outcome_backup;");
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } finally {
            db.execSQL("COMMIT;");
        }
    }

    public final void upgradeOutcomeTableRevision2To3(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        String commonColumnsWithSessionColumn = "_id,name,timestamp,notification_ids,weight,session";
        String commonColumnsWithNewSessionColumn = "_id,name,timestamp,notification_ids,weight,notification_influence_type";
        try {
            try {
                db.execSQL("BEGIN TRANSACTION;");
                db.execSQL("ALTER TABLE outcome RENAME TO outcome_aux;");
                db.execSQL(OutcomesDbContract.SQL_CREATE_OUTCOME_ENTRIES_V3);
                db.execSQL("INSERT INTO outcome(" + commonColumnsWithNewSessionColumn + ") SELECT " + commonColumnsWithSessionColumn + " FROM outcome_aux;");
                db.execSQL("DROP TABLE outcome_aux;");
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } finally {
            db.execSQL("COMMIT;");
        }
    }

    public final void upgradeOutcomeTableRevision3To4(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        try {
            try {
                db.execSQL("BEGIN TRANSACTION;");
                db.execSQL("ALTER TABLE outcome ADD COLUMN session_time INTEGER DEFAULT 1;");
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } finally {
            db.execSQL("COMMIT;");
        }
    }

    public final void upgradeCacheOutcomeTableRevision1To2(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        String commonColumnsWithNotificationIdColumn = "_id,name,notification_id";
        String commonColumnsWithNewInfluenceIdColumn = "_id,name,channel_influence_id";
        try {
            try {
                db.execSQL("BEGIN TRANSACTION;");
                db.execSQL(OutcomesDbContract.SQL_CREATE_UNIQUE_OUTCOME_ENTRIES_V2);
                db.execSQL("INSERT INTO cached_unique_outcome(" + commonColumnsWithNewInfluenceIdColumn + ") SELECT " + commonColumnsWithNotificationIdColumn + " FROM " + CachedUniqueOutcomeTable.TABLE_NAME_V1 + ';');
                db.execSQL("UPDATE cached_unique_outcome SET channel_type = '" + InfluenceChannel.NOTIFICATION + "';");
                db.execSQL("DROP TABLE " + CachedUniqueOutcomeTable.TABLE_NAME_V1 + ';');
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } finally {
            db.execSQL("COMMIT;");
        }
    }
}
