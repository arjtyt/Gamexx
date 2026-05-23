package com.onesignal.notifications.internal.common;

import android.content.Intent;
import android.os.Bundle;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.notifications.BuildConfig;
import kotlin.Metadata;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NotificationFormatHelper.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0014\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onesignal/notifications/internal/common/NotificationFormatHelper;", "", "()V", "PAYLOAD_OS_NOTIFICATION_ID", "", "PAYLOAD_OS_ROOT_CUSTOM", "getOSNotificationIdFromBundle", "bundle", "Landroid/os/Bundle;", "getOSNotificationIdFromJson", "jsonObject", "Lorg/json/JSONObject;", "getOSNotificationIdFromJsonString", "jsonStr", "isOneSignalBundle", "", "isOneSignalIntent", "intent", "Landroid/content/Intent;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationFormatHelper {
    public static final NotificationFormatHelper INSTANCE = new NotificationFormatHelper();
    public static final String PAYLOAD_OS_NOTIFICATION_ID = "i";
    public static final String PAYLOAD_OS_ROOT_CUSTOM = "custom";

    private NotificationFormatHelper() {
    }

    public final boolean isOneSignalIntent(Intent intent) {
        if (intent == null) {
            return false;
        }
        Bundle bundle = intent.getExtras();
        return isOneSignalBundle(bundle);
    }

    public final boolean isOneSignalBundle(Bundle bundle) {
        return getOSNotificationIdFromBundle(bundle) != null;
    }

    private final String getOSNotificationIdFromBundle(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return null;
        }
        String custom = bundle.getString(PAYLOAD_OS_ROOT_CUSTOM, null);
        if (custom == null) {
            Logging.debug$default("Not a OneSignal formatted Bundle. No 'custom' field in the bundle.", null, 2, null);
            return null;
        }
        return getOSNotificationIdFromJsonString(custom);
    }

    public final String getOSNotificationIdFromJson(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        String custom = jsonObject.optString(PAYLOAD_OS_ROOT_CUSTOM, null);
        return getOSNotificationIdFromJsonString(custom);
    }

    private final String getOSNotificationIdFromJsonString(String jsonStr) {
        JSONObject customJSON;
        try {
            customJSON = new JSONObject(jsonStr);
        } catch (JSONException e) {
            Logging.debug$default("Not a OneSignal formatted JSON String, error parsing string as JSON.", null, 2, null);
        }
        if (!customJSON.has("i")) {
            Logging.debug$default("Not a OneSignal formatted JSON string. No 'i' field in custom.", null, 2, null);
            return null;
        }
        return customJSON.optString("i", null);
    }
}
