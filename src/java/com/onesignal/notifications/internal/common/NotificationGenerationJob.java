package com.onesignal.notifications.internal.common;

import android.net.Uri;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.internal.Notification;
import com.onesignal.session.internal.influence.impl.InfluenceConstants;
import java.security.SecureRandom;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: NotificationGenerationJob.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0015\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\u0006\u0010H\u001a\u00020\u001aJ\b\u0010I\u001a\u00020\u0012H\u0016J\f\u0010J\u001a\u00020\b*\u00020\bH\u0002R\u0011\u0010\n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001b\"\u0004\b\u001f\u0010\u001dR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\f\"\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001e\u0010&\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010+\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u00102\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0018\"\u0004\b4\u00105R\u001e\u00106\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010+\u001a\u0004\b7\u0010(\"\u0004\b8\u0010*R\u001c\u00109\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010/\"\u0004\b;\u00101R\u001c\u0010<\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0018\"\u0004\b>\u00105R\u001e\u0010?\u001a\u0004\u0018\u00010@X\u0086\u000e¢\u0006\u0010\n\u0002\u0010E\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u0013\u0010F\u001a\u0004\u0018\u00010\u00168F¢\u0006\u0006\u001a\u0004\bG\u0010\u0018¨\u0006K"}, d2 = {"Lcom/onesignal/notifications/internal/common/NotificationGenerationJob;", "", "jsonPayload", "Lorg/json/JSONObject;", InfluenceConstants.TIME, "Lcom/onesignal/core/internal/time/ITime;", "(Lorg/json/JSONObject;Lcom/onesignal/core/internal/time/ITime;)V", "inNotification", "Lcom/onesignal/notifications/internal/Notification;", "(Lcom/onesignal/notifications/internal/Notification;Lorg/json/JSONObject;)V", "additionalData", "getAdditionalData", "()Lorg/json/JSONObject;", "androidId", "", "getAndroidId", "()I", "apiNotificationId", "", "getApiNotificationId", "()Ljava/lang/String;", "body", "", "getBody", "()Ljava/lang/CharSequence;", "isNotificationToDisplay", "", "()Z", "setNotificationToDisplay", "(Z)V", "isRestoring", "setRestoring", "getJsonPayload", "setJsonPayload", "(Lorg/json/JSONObject;)V", OneSignalDbContract.NotificationTable.TABLE_NAME, "getNotification", "()Lcom/onesignal/notifications/internal/Notification;", "orgFlags", "getOrgFlags", "()Ljava/lang/Integer;", "setOrgFlags", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "orgSound", "Landroid/net/Uri;", "getOrgSound", "()Landroid/net/Uri;", "setOrgSound", "(Landroid/net/Uri;)V", "overriddenBodyFromExtender", "getOverriddenBodyFromExtender", "setOverriddenBodyFromExtender", "(Ljava/lang/CharSequence;)V", "overriddenFlags", "getOverriddenFlags", "setOverriddenFlags", "overriddenSound", "getOverriddenSound", "setOverriddenSound", "overriddenTitleFromExtender", "getOverriddenTitleFromExtender", "setOverriddenTitleFromExtender", "shownTimeStamp", "", "getShownTimeStamp", "()Ljava/lang/Long;", "setShownTimeStamp", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE, "getTitle", "hasExtender", "toString", "setAndroidNotificationId", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationGenerationJob {
    private boolean isNotificationToDisplay;
    private boolean isRestoring;
    private JSONObject jsonPayload;
    private final Notification notification;
    private Integer orgFlags;
    private Uri orgSound;
    private CharSequence overriddenBodyFromExtender;
    private Integer overriddenFlags;
    private Uri overriddenSound;
    private CharSequence overriddenTitleFromExtender;
    private Long shownTimeStamp;

    public NotificationGenerationJob(Notification inNotification, JSONObject jsonPayload) {
        Intrinsics.checkNotNullParameter(inNotification, "inNotification");
        Intrinsics.checkNotNullParameter(jsonPayload, "jsonPayload");
        this.jsonPayload = jsonPayload;
        this.notification = setAndroidNotificationId(inNotification);
    }

    public final JSONObject getJsonPayload() {
        return this.jsonPayload;
    }

    public final void setJsonPayload(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<set-?>");
        this.jsonPayload = jSONObject;
    }

    public final Notification getNotification() {
        return this.notification;
    }

    private final Notification setAndroidNotificationId(Notification $this$setAndroidNotificationId) {
        if ($this$setAndroidNotificationId != null && !$this$setAndroidNotificationId.hasNotificationId()) {
            $this$setAndroidNotificationId.setAndroidNotificationId(new SecureRandom().nextInt());
        }
        return $this$setAndroidNotificationId;
    }

    public final boolean isRestoring() {
        return this.isRestoring;
    }

    public final void setRestoring(boolean z) {
        this.isRestoring = z;
    }

    public final boolean isNotificationToDisplay() {
        return this.isNotificationToDisplay;
    }

    public final void setNotificationToDisplay(boolean z) {
        this.isNotificationToDisplay = z;
    }

    public final Long getShownTimeStamp() {
        return this.shownTimeStamp;
    }

    public final void setShownTimeStamp(Long l) {
        this.shownTimeStamp = l;
    }

    public final CharSequence getOverriddenBodyFromExtender() {
        return this.overriddenBodyFromExtender;
    }

    public final void setOverriddenBodyFromExtender(CharSequence charSequence) {
        this.overriddenBodyFromExtender = charSequence;
    }

    public final CharSequence getOverriddenTitleFromExtender() {
        return this.overriddenTitleFromExtender;
    }

    public final void setOverriddenTitleFromExtender(CharSequence charSequence) {
        this.overriddenTitleFromExtender = charSequence;
    }

    public final Uri getOverriddenSound() {
        return this.overriddenSound;
    }

    public final void setOverriddenSound(Uri uri) {
        this.overriddenSound = uri;
    }

    public final Integer getOverriddenFlags() {
        return this.overriddenFlags;
    }

    public final void setOverriddenFlags(Integer num) {
        this.overriddenFlags = num;
    }

    public final Integer getOrgFlags() {
        return this.orgFlags;
    }

    public final void setOrgFlags(Integer num) {
        this.orgFlags = num;
    }

    public final Uri getOrgSound() {
        return this.orgSound;
    }

    public final void setOrgSound(Uri uri) {
        this.orgSound = uri;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NotificationGenerationJob(JSONObject jsonPayload, ITime time) {
        this(new Notification(jsonPayload, time), jsonPayload);
        Intrinsics.checkNotNullParameter(jsonPayload, "jsonPayload");
        Intrinsics.checkNotNullParameter(time, InfluenceConstants.TIME);
    }

    public final CharSequence getTitle() {
        CharSequence charSequence = this.overriddenTitleFromExtender;
        if (charSequence != null) {
            return charSequence;
        }
        Notification notification = this.notification;
        Intrinsics.checkNotNull(notification);
        return notification.getTitle();
    }

    public final CharSequence getBody() {
        CharSequence charSequence = this.overriddenBodyFromExtender;
        if (charSequence != null) {
            return charSequence;
        }
        Notification notification = this.notification;
        Intrinsics.checkNotNull(notification);
        return notification.getBody();
    }

    public final JSONObject getAdditionalData() {
        Notification notification = this.notification;
        Intrinsics.checkNotNull(notification);
        JSONObject additionalData = notification.getAdditionalData();
        return additionalData == null ? new JSONObject() : additionalData;
    }

    public final boolean hasExtender() {
        Notification notification = this.notification;
        Intrinsics.checkNotNull(notification);
        return notification.getNotificationExtender() != null;
    }

    public final String getApiNotificationId() {
        String notificationIdFromFCMJson = NotificationHelper.INSTANCE.getNotificationIdFromFCMJson(this.jsonPayload);
        return notificationIdFromFCMJson == null ? "" : notificationIdFromFCMJson;
    }

    public final int getAndroidId() {
        Notification notification = this.notification;
        Intrinsics.checkNotNull(notification);
        return notification.getAndroidNotificationId();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NotificationGenerationJob{jsonPayload=").append(this.jsonPayload).append(", isRestoring=").append(this.isRestoring).append(", isNotificationToDisplay=").append(this.isNotificationToDisplay).append(", shownTimeStamp=").append(this.shownTimeStamp).append(", overriddenBodyFromExtender=").append((Object) this.overriddenBodyFromExtender).append(", overriddenTitleFromExtender=").append((Object) this.overriddenTitleFromExtender).append(", overriddenSound=").append(this.overriddenSound).append(", overriddenFlags=").append(this.overriddenFlags).append(", orgFlags=").append(this.orgFlags).append(", orgSound=").append(this.orgSound).append(", notification=").append(this.notification).append('}');
        return sb.toString();
    }
}
