package com.onesignal.core.internal.device.impl;

import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.device.IInstallIdService;
import com.onesignal.core.internal.preferences.IPreferencesService;
import com.onesignal.core.internal.preferences.PreferenceOneSignalKeys;
import com.onesignal.core.internal.preferences.PreferenceStores;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InstallIdService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u000b\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lcom/onesignal/core/internal/device/impl/InstallIdService;", "Lcom/onesignal/core/internal/device/IInstallIdService;", "_prefs", "Lcom/onesignal/core/internal/preferences/IPreferencesService;", "(Lcom/onesignal/core/internal/preferences/IPreferencesService;)V", "currentId", "Ljava/util/UUID;", "getCurrentId", "()Ljava/util/UUID;", "currentId$delegate", "Lkotlin/Lazy;", "getId", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InstallIdService implements IInstallIdService {
    private final IPreferencesService _prefs;
    private final Lazy currentId$delegate;

    public InstallIdService(IPreferencesService _prefs) {
        Intrinsics.checkNotNullParameter(_prefs, "_prefs");
        this._prefs = _prefs;
        this.currentId$delegate = LazyKt.lazy(new Function0<UUID>() { // from class: com.onesignal.core.internal.device.impl.InstallIdService$currentId$2
            {
                super(0);
            }

            public final UUID invoke() {
                String idFromPrefs = IPreferencesService.DefaultImpls.getString$default(this.this$0._prefs, PreferenceStores.ONESIGNAL, PreferenceOneSignalKeys.PREFS_OS_INSTALL_ID, null, 4, null);
                if (idFromPrefs != null) {
                    return UUID.fromString(idFromPrefs);
                }
                UUID newId = UUID.randomUUID();
                this.this$0._prefs.saveString(PreferenceStores.ONESIGNAL, PreferenceOneSignalKeys.PREFS_OS_INSTALL_ID, newId.toString());
                return newId;
            }
        });
    }

    private final UUID getCurrentId() {
        Object value = this.currentId$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-currentId>(...)");
        return (UUID) value;
    }

    @Override // com.onesignal.core.internal.device.IInstallIdService
    public Object getId(Continuation<? super UUID> continuation) {
        return getCurrentId();
    }
}
