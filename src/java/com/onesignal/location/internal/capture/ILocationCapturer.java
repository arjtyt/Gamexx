package com.onesignal.location.internal.capture;

import com.onesignal.location.BuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: ILocationCapturer.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/onesignal/location/internal/capture/ILocationCapturer;", "", "locationCoarse", "", "getLocationCoarse", "()Z", "setLocationCoarse", "(Z)V", "captureLastLocation", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ILocationCapturer {
    void captureLastLocation();

    boolean getLocationCoarse();

    void setLocationCoarse(boolean z);
}
