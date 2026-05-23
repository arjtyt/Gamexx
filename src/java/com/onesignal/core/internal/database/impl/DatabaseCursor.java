package com.onesignal.core.internal.database.impl;

import android.database.Cursor;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.database.ICursor;
import com.onesignal.notifications.internal.badges.impl.shortcutbadger.impl.NewHtcHomeBadger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DatabaseCursor.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0017\u0010\u0010\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u0013J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/onesignal/core/internal/database/impl/DatabaseCursor;", "Lcom/onesignal/core/internal/database/ICursor;", "_cursor", "Landroid/database/Cursor;", "(Landroid/database/Cursor;)V", NewHtcHomeBadger.COUNT, "", "getCount", "()I", "getFloat", "", "column", "", "getInt", "getLong", "", "getOptFloat", "(Ljava/lang/String;)Ljava/lang/Float;", "getOptInt", "(Ljava/lang/String;)Ljava/lang/Integer;", "getOptLong", "(Ljava/lang/String;)Ljava/lang/Long;", "getOptString", "getString", "moveToFirst", "", "moveToNext", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DatabaseCursor implements ICursor {
    private final Cursor _cursor;

    public DatabaseCursor(Cursor _cursor) {
        Intrinsics.checkNotNullParameter(_cursor, "_cursor");
        this._cursor = _cursor;
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public int getCount() {
        return this._cursor.getCount();
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public boolean moveToFirst() {
        return this._cursor.moveToFirst();
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public boolean moveToNext() {
        return this._cursor.moveToNext();
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public String getString(String column) {
        Intrinsics.checkNotNullParameter(column, "column");
        String string = this._cursor.getString(this._cursor.getColumnIndex(column));
        Intrinsics.checkNotNullExpressionValue(string, "_cursor.getString(_cursor.getColumnIndex(column))");
        return string;
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public float getFloat(String column) {
        Intrinsics.checkNotNullParameter(column, "column");
        return this._cursor.getFloat(this._cursor.getColumnIndex(column));
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public long getLong(String column) {
        Intrinsics.checkNotNullParameter(column, "column");
        return this._cursor.getLong(this._cursor.getColumnIndex(column));
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public int getInt(String column) {
        Intrinsics.checkNotNullParameter(column, "column");
        return this._cursor.getInt(this._cursor.getColumnIndex(column));
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public String getOptString(String column) {
        Intrinsics.checkNotNullParameter(column, "column");
        int idx = this._cursor.getColumnIndex(column);
        if (this._cursor.isNull(idx)) {
            return null;
        }
        return this._cursor.getString(idx);
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public Float getOptFloat(String column) {
        Intrinsics.checkNotNullParameter(column, "column");
        int idx = this._cursor.getColumnIndex(column);
        if (this._cursor.isNull(idx)) {
            return null;
        }
        return Float.valueOf(this._cursor.getFloat(idx));
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public Long getOptLong(String column) {
        Intrinsics.checkNotNullParameter(column, "column");
        int idx = this._cursor.getColumnIndex(column);
        if (this._cursor.isNull(idx)) {
            return null;
        }
        return Long.valueOf(this._cursor.getLong(idx));
    }

    @Override // com.onesignal.core.internal.database.ICursor
    public Integer getOptInt(String column) {
        Intrinsics.checkNotNullParameter(column, "column");
        int idx = this._cursor.getColumnIndex(column);
        if (this._cursor.isNull(idx)) {
            return null;
        }
        return Integer.valueOf(this._cursor.getInt(idx));
    }
}
