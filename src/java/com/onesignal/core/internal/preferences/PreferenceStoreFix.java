package com.onesignal.core.internal.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.onesignal.core.BuildConfig;
import com.onesignal.debug.LogLevel;
import com.onesignal.debug.internal.logging.Logging;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PreferenceStoreFix.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/onesignal/core/internal/preferences/PreferenceStoreFix;", "", "()V", "ensureNoObfuscatedPrefStore", "", "context", "Landroid/content/Context;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PreferenceStoreFix {
    public static final PreferenceStoreFix INSTANCE = new PreferenceStoreFix();

    private PreferenceStoreFix() {
    }

    public final void ensureNoObfuscatedPrefStore(Context context) {
        File[] prefsFileList;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            File sharedPrefsDir = new File(context.getDataDir(), "shared_prefs");
            File osPrefsFile = new File(sharedPrefsDir, "OneSignal.xml");
            if (!sharedPrefsDir.exists() || !sharedPrefsDir.isDirectory() || osPrefsFile.exists() || (prefsFileList = sharedPrefsDir.listFiles()) == null) {
                return;
            }
            for (File prefsFile : prefsFileList) {
                Intrinsics.checkNotNullExpressionValue(prefsFile, "prefsFile");
                SharedPreferences prefsStore = context.getSharedPreferences(FilesKt.getNameWithoutExtension(prefsFile), 0);
                if (prefsStore.contains(PreferenceOneSignalKeys.PREFS_LEGACY_PLAYER_ID)) {
                    prefsFile.renameTo(osPrefsFile);
                    return;
                }
            }
        } catch (Throwable e) {
            Logging.log(LogLevel.ERROR, "error attempting to fix obfuscated preference store", e);
        }
    }
}
