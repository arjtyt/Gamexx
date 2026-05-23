package com.onesignal.notifications.internal.common;

import com.onesignal.notifications.BuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: NotificationConstants.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onesignal/notifications/internal/common/NotificationConstants;", "", "()V", "BUNDLE_KEY_ANDROID_NOTIFICATION_ID", "", "BUNDLE_KEY_ONESIGNAL_DATA", "DEFAULT_TTL_IF_NOT_IN_PAYLOAD", "", "EXTENSION_SERVICE_META_DATA_TAG_NAME", "GENERATE_NOTIFICATION_BUNDLE_KEY_ACTION_ID", "GOOGLE_SENT_TIME_KEY", "GOOGLE_TTL_KEY", "HMS_SENT_TIME_KEY", "HMS_TTL_KEY", "IAM_PREVIEW_KEY", "PUSH_ADDITIONAL_DATA_KEY", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationConstants {
    public static final String BUNDLE_KEY_ANDROID_NOTIFICATION_ID = "androidNotificationId";
    public static final String BUNDLE_KEY_ONESIGNAL_DATA = "onesignalData";
    public static final int DEFAULT_TTL_IF_NOT_IN_PAYLOAD = 259200;
    public static final String EXTENSION_SERVICE_META_DATA_TAG_NAME = "com.onesignal.NotificationServiceExtension";
    public static final String GENERATE_NOTIFICATION_BUNDLE_KEY_ACTION_ID = "actionId";
    public static final String GOOGLE_SENT_TIME_KEY = "google.sent_time";
    public static final String GOOGLE_TTL_KEY = "google.ttl";
    public static final String HMS_SENT_TIME_KEY = "hms.sent_time";
    public static final String HMS_TTL_KEY = "hms.ttl";
    public static final String IAM_PREVIEW_KEY = "os_in_app_message_preview_id";
    public static final NotificationConstants INSTANCE = new NotificationConstants();
    public static final String PUSH_ADDITIONAL_DATA_KEY = "a";

    private NotificationConstants() {
    }
}
