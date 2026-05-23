package com.onesignal.location.internal.controller;

import android.location.Location;
import com.onesignal.inAppMessages.internal.prompt.InAppMessagePromptTypes;
import com.onesignal.location.BuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: ILocationController.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onesignal/location/internal/controller/ILocationUpdatedHandler;", "", "onLocationChanged", "", InAppMessagePromptTypes.LOCATION_PROMPT_KEY, "Landroid/location/Location;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ILocationUpdatedHandler {
    void onLocationChanged(Location location);
}
