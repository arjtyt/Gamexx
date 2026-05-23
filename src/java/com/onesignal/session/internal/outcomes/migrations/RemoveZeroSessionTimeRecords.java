package com.onesignal.session.internal.outcomes.migrations;

import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.database.IDatabaseProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RemoveZeroSessionTimeRecords.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/onesignal/session/internal/outcomes/migrations/RemoveZeroSessionTimeRecords;", "", "()V", "run", "", "databaseProvider", "Lcom/onesignal/core/internal/database/IDatabaseProvider;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class RemoveZeroSessionTimeRecords {
    public static final RemoveZeroSessionTimeRecords INSTANCE = new RemoveZeroSessionTimeRecords();

    private RemoveZeroSessionTimeRecords() {
    }

    public final void run(IDatabaseProvider databaseProvider) {
        Intrinsics.checkNotNullParameter(databaseProvider, "databaseProvider");
        databaseProvider.getOs().delete("outcome", "name = \"os__session_duration\" AND session_time = 0", null);
    }
}
