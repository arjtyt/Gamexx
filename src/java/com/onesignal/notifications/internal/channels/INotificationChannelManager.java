package com.onesignal.notifications.internal.channels;

import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.internal.common.NotificationGenerationJob;
import kotlin.Metadata;
import org.json.JSONArray;

/* JADX INFO: compiled from: INotificationChannelManager.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\n"}, d2 = {"Lcom/onesignal/notifications/internal/channels/INotificationChannelManager;", "", "createNotificationChannel", "", "notificationJob", "Lcom/onesignal/notifications/internal/common/NotificationGenerationJob;", "processChannelList", "", "list", "Lorg/json/JSONArray;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface INotificationChannelManager {
    String createNotificationChannel(NotificationGenerationJob notificationGenerationJob);

    void processChannelList(JSONArray jSONArray);
}
