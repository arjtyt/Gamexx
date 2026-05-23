package com.onesignal.notifications.internal.common;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.onesignal.common.AndroidUtils;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.internal.NotificationClickEvent;
import com.onesignal.notifications.internal.NotificationClickResult;
import com.onesignal.session.internal.influence.impl.InfluenceConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NotificationHelper.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006J*\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011H\u0007J\u001d\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J \u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u00112\u0006\u0010\t\u001a\u00020\nH\u0007J\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u001b2\u0006\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0007J\u0012\u0010$\u001a\u0004\u0018\u00010\u00062\b\u0010%\u001a\u0004\u0018\u00010!J\u000e\u0010&\u001a\u00020'2\u0006\u0010\t\u001a\u00020\nJ\u001a\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010\t\u001a\u00020\n2\b\u0010*\u001a\u0004\u0018\u00010\u0006J\u000e\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u0010J\u0010\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u00020!R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/onesignal/notifications/internal/common/NotificationHelper;", "", "()V", "GROUPLESS_SUMMARY_ID", "", "GROUPLESS_SUMMARY_KEY", "", "areNotificationsEnabled", "", "context", "Landroid/content/Context;", "channelId", "assignGrouplessNotifications", "", "grouplessNotifs", "Ljava/util/ArrayList;", "Landroid/service/notification/StatusBarNotification;", "Lkotlin/collections/ArrayList;", "generateNotificationOpenedResult", "Lcom/onesignal/notifications/internal/NotificationClickEvent;", "jsonArray", "Lorg/json/JSONArray;", InfluenceConstants.TIME, "Lcom/onesignal/core/internal/time/ITime;", "generateNotificationOpenedResult$com_onesignal_notifications", "getActiveGrouplessNotifications", "getActiveNotifications", "", "(Landroid/content/Context;)[Landroid/service/notification/StatusBarNotification;", "getCampaignNameFromNotification", OneSignalDbContract.NotificationTable.TABLE_NAME, "Lcom/onesignal/notifications/INotification;", "getCustomJSONObject", "Lorg/json/JSONObject;", "jsonObject", "getGrouplessNotifsCount", "getNotificationIdFromFCMJson", "fcmJson", "getNotificationManager", "Landroid/app/NotificationManager;", "getSoundUri", "Landroid/net/Uri;", "sound", "isGroupSummary", "notif", "parseVibrationPattern", "", "fcmBundle", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationHelper {
    public static final int GROUPLESS_SUMMARY_ID = -718463522;
    public static final String GROUPLESS_SUMMARY_KEY = "os_group_undefined";
    public static final NotificationHelper INSTANCE = new NotificationHelper();

    private NotificationHelper() {
    }

    public final StatusBarNotification[] getActiveNotifications(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        StatusBarNotification[] statusBarNotifications = new StatusBarNotification[0];
        try {
            StatusBarNotification[] statusBarNotifications2 = getNotificationManager(context).getActiveNotifications();
            Intrinsics.checkNotNullExpressionValue(statusBarNotifications2, "getNotificationManager(c…text).activeNotifications");
            return statusBarNotifications2;
        } catch (Throwable th) {
            return statusBarNotifications;
        }
    }

    public final int getGrouplessNotifsCount(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        StatusBarNotification[] statusBarNotifications = getActiveNotifications(context);
        int groupCount = 0;
        for (StatusBarNotification statusBarNotification : statusBarNotifications) {
            if (!NotificationCompat.isGroupSummary(statusBarNotification.getNotification()) && Intrinsics.areEqual(GROUPLESS_SUMMARY_KEY, statusBarNotification.getNotification().getGroup())) {
                groupCount++;
            }
        }
        return groupCount;
    }

    public final ArrayList<StatusBarNotification> getActiveGrouplessNotifications(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList<StatusBarNotification> arrayList = new ArrayList<>();
        StatusBarNotification[] statusBarNotifications = getActiveNotifications(context);
        for (StatusBarNotification statusBarNotification : statusBarNotifications) {
            Notification notification = statusBarNotification.getNotification();
            boolean isGroupSummary = isGroupSummary(statusBarNotification);
            boolean isGroupless = notification.getGroup() == null || Intrinsics.areEqual(notification.getGroup(), GROUPLESS_SUMMARY_KEY);
            if (!isGroupSummary && isGroupless) {
                arrayList.add(statusBarNotification);
            }
        }
        return arrayList;
    }

    public final boolean isGroupSummary(StatusBarNotification notif) {
        Intrinsics.checkNotNullParameter(notif, "notif");
        return (notif.getNotification().flags & 512) != 0;
    }

    public final void assignGrouplessNotifications(Context context, ArrayList<StatusBarNotification> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "grouplessNotifs");
        for (StatusBarNotification grouplessNotif : arrayList) {
            Notification.Builder grouplessNotifBuilder = Notification.Builder.recoverBuilder(context, grouplessNotif.getNotification());
            Intrinsics.checkNotNullExpressionValue(grouplessNotifBuilder, "recoverBuilder(context, …uplessNotif.notification)");
            Notification notif = grouplessNotifBuilder.setGroup(GROUPLESS_SUMMARY_KEY).setOnlyAlertOnce(true).build();
            Intrinsics.checkNotNullExpressionValue(notif, "grouplessNotifBuilder\n  …                 .build()");
            Intrinsics.checkNotNull(context);
            NotificationManagerCompat.from(context).notify(grouplessNotif.getId(), notif);
        }
    }

    public static /* synthetic */ boolean areNotificationsEnabled$default(NotificationHelper notificationHelper, Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return notificationHelper.areNotificationsEnabled(context, str);
    }

    public final boolean areNotificationsEnabled(Context context, String channelId) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            boolean notificationsEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled();
            if (!notificationsEnabled) {
                return false;
            }
            if (channelId != null && Build.VERSION.SDK_INT >= 26) {
                NotificationManager notificationManager = getNotificationManager(context);
                NotificationChannel channel = notificationManager != null ? notificationManager.getNotificationChannel(channelId) : null;
                if (channel == null) {
                    return true;
                }
                if (channel.getImportance() != 0) {
                    return true;
                }
                return false;
            }
        } catch (Throwable th) {
        }
        return true;
    }

    public final NotificationManager getNotificationManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        return (NotificationManager) systemService;
    }

    public final JSONObject getCustomJSONObject(JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        return new JSONObject(jsonObject.optString(NotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM));
    }

    public final String getNotificationIdFromFCMJson(JSONObject fcmJson) {
        JSONObject customJSON;
        if (fcmJson == null) {
            return null;
        }
        try {
            customJSON = new JSONObject(fcmJson.getString(NotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM));
        } catch (JSONException e) {
            Logging.debug$default("Not a OneSignal formatted FCM message. No 'custom' field in the JSONObject.", null, 2, null);
        }
        if (!customJSON.has("i")) {
            Logging.debug$default("Not a OneSignal formatted FCM message. No 'i' field in custom.", null, 2, null);
            return null;
        }
        return customJSON.optString("i", null);
    }

    public final long[] parseVibrationPattern(JSONObject fcmBundle) {
        JSONArray jsonVibArray;
        Intrinsics.checkNotNullParameter(fcmBundle, "fcmBundle");
        try {
            Object patternObj = fcmBundle.opt("vib_pt");
            if (patternObj instanceof String) {
                jsonVibArray = new JSONArray((String) patternObj);
            } else {
                Intrinsics.checkNotNull(patternObj, "null cannot be cast to non-null type org.json.JSONArray");
                jsonVibArray = (JSONArray) patternObj;
            }
            long[] longArray = new long[jsonVibArray.length()];
            int length = jsonVibArray.length();
            for (int i = 0; i < length; i++) {
                longArray[i] = jsonVibArray.optLong(i);
            }
            return longArray;
        } catch (JSONException e) {
            return null;
        }
    }

    public final Uri getSoundUri(Context context, String sound) {
        int soundId;
        Intrinsics.checkNotNullParameter(context, "context");
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        if (AndroidUtils.INSTANCE.isValidResourceName(sound) && (soundId = resources.getIdentifier(sound, "raw", packageName)) != 0) {
            return Uri.parse("android.resource://" + packageName + '/' + soundId);
        }
        int soundId2 = resources.getIdentifier("onesignal_default_sound", "raw", packageName);
        if (soundId2 != 0) {
            return Uri.parse("android.resource://" + packageName + '/' + soundId2);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getCampaignNameFromNotification(com.onesignal.notifications.INotification r5) {
        /*
            r4 = this;
            java.lang.String r0 = "notification"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = r5.getTemplateName()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L1c
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L17
            r0 = r1
            goto L18
        L17:
            r0 = r2
        L18:
            if (r0 != r1) goto L1c
            r0 = r1
            goto L1d
        L1c:
            r0 = r2
        L1d:
            if (r0 != 0) goto L56
            java.lang.String r0 = r5.getTemplateId()
            if (r0 == 0) goto L33
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L2f
            r0 = r1
            goto L30
        L2f:
            r0 = r2
        L30:
            if (r0 != r1) goto L33
            goto L34
        L33:
            r1 = r2
        L34:
            if (r1 != 0) goto L56
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r5.getTemplateName()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = " - "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r5.getTemplateId()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        L56:
            java.lang.String r0 = r5.getTitle()
            if (r0 == 0) goto L7f
            java.lang.String r0 = r5.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.lang.String r1 = r5.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r1 = r1.length()
            r3 = 10
            int r1 = java.lang.Math.min(r3, r1)
            java.lang.String r0 = r0.substring(r2, r1)
            java.lang.String r1 = "this as java.lang.String…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        L7f:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.common.NotificationHelper.getCampaignNameFromNotification(com.onesignal.notifications.INotification):java.lang.String");
    }

    public final NotificationClickEvent generateNotificationOpenedResult$com_onesignal_notifications(JSONArray jsonArray, ITime time) {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        Intrinsics.checkNotNullParameter(time, InfluenceConstants.TIME);
        int jsonArraySize = jsonArray.length();
        boolean firstMessage = true;
        int androidNotificationId = jsonArray.optJSONObject(0).optInt(NotificationConstants.BUNDLE_KEY_ANDROID_NOTIFICATION_ID);
        List groupedNotifications = new ArrayList();
        String actionSelected = null;
        JSONObject payload = null;
        for (int i = 0; i < jsonArraySize; i++) {
            try {
                payload = jsonArray.getJSONObject(i);
                if (actionSelected == null && payload.has(NotificationConstants.GENERATE_NOTIFICATION_BUNDLE_KEY_ACTION_ID)) {
                    actionSelected = payload.optString(NotificationConstants.GENERATE_NOTIFICATION_BUNDLE_KEY_ACTION_ID, null);
                }
                if (firstMessage) {
                    firstMessage = false;
                } else {
                    groupedNotifications.add(new com.onesignal.notifications.internal.Notification(payload, time));
                }
            } catch (Throwable t) {
                Logging.error("Error parsing JSON item " + i + '/' + jsonArraySize + " for callback.", t);
            }
        }
        Intrinsics.checkNotNull(payload);
        com.onesignal.notifications.internal.Notification notification = new com.onesignal.notifications.internal.Notification(groupedNotifications, payload, androidNotificationId, time);
        NotificationClickResult notificationResult = new NotificationClickResult(actionSelected, notification.getLaunchURL());
        return new NotificationClickEvent(notification, notificationResult);
    }
}
