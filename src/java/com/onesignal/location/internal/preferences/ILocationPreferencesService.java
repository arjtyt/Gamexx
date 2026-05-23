package com.onesignal.location.internal.preferences;

import com.onesignal.location.BuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: ILocationPreferencesService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b`\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/onesignal/location/internal/preferences/ILocationPreferencesService;", "", "lastLocationTime", "", "getLastLocationTime", "()J", "setLastLocationTime", "(J)V", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ILocationPreferencesService {
    long getLastLocationTime();

    void setLastLocationTime(long j);
}
