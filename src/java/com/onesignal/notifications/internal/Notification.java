package com.onesignal.notifications.internal;

import androidx.core.app.NotificationCompat;
import com.onesignal.common.JSONObjectExtensionsKt;
import com.onesignal.common.threading.WaiterWithValue;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.notifications.BackgroundImageLayout;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.IActionButton;
import com.onesignal.notifications.IDisplayableMutableNotification;
import com.onesignal.notifications.internal.common.NotificationConstants;
import com.onesignal.notifications.internal.common.NotificationHelper;
import com.onesignal.session.internal.influence.impl.InfluenceConstants;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Notification.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001\u007fB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B/\b\u0016\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\b\u0010t\u001a\u00020uH\u0016J\u0006\u0010v\u001a\u000200J\u0018\u0010w\u001a\u00020u2\u0006\u0010x\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\b\u0010y\u001a\u00020uH\u0002J\u0010\u0010z\u001a\u00020u2\u0006\u0010x\u001a\u00020\u0003H\u0002J\u0012\u0010{\u001a\u00020u2\b\u0010|\u001a\u0004\u0018\u00010KH\u0016J\u0006\u0010}\u001a\u00020\u0003J\b\u0010~\u001a\u00020#H\u0016R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\n\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001c\u0010(\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010%\"\u0004\b*\u0010'R\u001c\u0010+\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010%\"\u0004\b-\u0010'R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u0002000/¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u001c\u00103\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010%\"\u0004\b5\u0010'R\u001c\u00106\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010%\"\u0004\b8\u0010'R\u001c\u00109\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010%\"\u0004\b;\u0010'R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u0010\"\u0004\b=\u0010\u0012R\u001c\u0010>\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010%\"\u0004\b@\u0010'R\u001c\u0010A\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010%\"\u0004\bC\u0010'R\u001c\u0010D\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010%\"\u0004\bF\u0010'R\u001a\u0010G\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\u0019\"\u0004\bI\u0010\u001bR\u001c\u0010J\u001a\u0004\u0018\u00010KX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001c\u0010P\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010%\"\u0004\bR\u0010'R\u001a\u0010S\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010\u0019\"\u0004\bU\u0010\u001bR\u001a\u0010V\u001a\u00020#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010%\"\u0004\bX\u0010'R\u001a\u0010Y\u001a\u00020ZX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u001c\u0010_\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010%\"\u0004\ba\u0010'R\u001c\u0010b\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010%\"\u0004\bd\u0010'R\u001c\u0010e\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010%\"\u0004\bg\u0010'R\u001c\u0010h\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010%\"\u0004\bj\u0010'R\u001c\u0010k\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010%\"\u0004\bm\u0010'R\u001c\u0010n\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010%\"\u0004\bp\u0010'R\u001a\u0010q\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010\u0019\"\u0004\bs\u0010\u001b¨\u0006\u0080\u0001"}, d2 = {"Lcom/onesignal/notifications/internal/Notification;", "Lcom/onesignal/notifications/IDisplayableMutableNotification;", "payload", "Lorg/json/JSONObject;", InfluenceConstants.TIME, "Lcom/onesignal/core/internal/time/ITime;", "(Lorg/json/JSONObject;Lcom/onesignal/core/internal/time/ITime;)V", "groupedNotifications", "", "jsonPayload", NotificationConstants.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, "", "(Ljava/util/List;Lorg/json/JSONObject;ILcom/onesignal/core/internal/time/ITime;)V", "actionButtons", "Lcom/onesignal/notifications/IActionButton;", "getActionButtons", "()Ljava/util/List;", "setActionButtons", "(Ljava/util/List;)V", "additionalData", "getAdditionalData", "()Lorg/json/JSONObject;", "setAdditionalData", "(Lorg/json/JSONObject;)V", "getAndroidNotificationId", "()I", "setAndroidNotificationId", "(I)V", "backgroundImageLayout", "Lcom/onesignal/notifications/BackgroundImageLayout;", "getBackgroundImageLayout", "()Lcom/onesignal/notifications/BackgroundImageLayout;", "setBackgroundImageLayout", "(Lcom/onesignal/notifications/BackgroundImageLayout;)V", "bigPicture", "", "getBigPicture", "()Ljava/lang/String;", "setBigPicture", "(Ljava/lang/String;)V", "body", "getBody", "setBody", "collapseId", "getCollapseId", "setCollapseId", "displayWaiter", "Lcom/onesignal/common/threading/WaiterWithValue;", "", "getDisplayWaiter", "()Lcom/onesignal/common/threading/WaiterWithValue;", "fromProjectNumber", "getFromProjectNumber", "setFromProjectNumber", "groupKey", "getGroupKey", "setGroupKey", "groupMessage", "getGroupMessage", "setGroupMessage", "getGroupedNotifications", "setGroupedNotifications", "largeIcon", "getLargeIcon", "setLargeIcon", "launchURL", "getLaunchURL", "setLaunchURL", "ledColor", "getLedColor", "setLedColor", "lockScreenVisibility", "getLockScreenVisibility", "setLockScreenVisibility", "notificationExtender", "Landroidx/core/app/NotificationCompat$Extender;", "getNotificationExtender", "()Landroidx/core/app/NotificationCompat$Extender;", "setNotificationExtender", "(Landroidx/core/app/NotificationCompat$Extender;)V", "notificationId", "getNotificationId", "setNotificationId", "priority", "getPriority", "setPriority", "rawPayload", "getRawPayload", "setRawPayload", "sentTime", "", "getSentTime", "()J", "setSentTime", "(J)V", "smallIcon", "getSmallIcon", "setSmallIcon", "smallIconAccentColor", "getSmallIconAccentColor", "setSmallIconAccentColor", "sound", "getSound", "setSound", "templateId", "getTemplateId", "setTemplateId", "templateName", "getTemplateName", "setTemplateName", OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE, "getTitle", "setTitle", "ttl", "getTtl", "setTtl", "display", "", "hasNotificationId", "initPayloadData", "currentJsonPayload", "setActionButtonsFromData", "setBackgroundImageLayoutFromData", "setExtender", "extender", "toJSONObject", "toString", "ActionButton", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class Notification implements IDisplayableMutableNotification {
    private List<? extends IActionButton> actionButtons;
    private JSONObject additionalData;
    private int androidNotificationId;
    private BackgroundImageLayout backgroundImageLayout;
    private String bigPicture;
    private String body;
    private String collapseId;
    private final WaiterWithValue<Boolean> displayWaiter;
    private String fromProjectNumber;
    private String groupKey;
    private String groupMessage;
    private List<Notification> groupedNotifications;
    private String largeIcon;
    private String launchURL;
    private String ledColor;
    private int lockScreenVisibility;
    private NotificationCompat.Extender notificationExtender;
    private String notificationId;
    private int priority;
    private String rawPayload;
    private long sentTime;
    private String smallIcon;
    private String smallIconAccentColor;
    private String sound;
    private String templateId;
    private String templateName;
    private String title;
    private int ttl;

    public final NotificationCompat.Extender getNotificationExtender() {
        return this.notificationExtender;
    }

    public final void setNotificationExtender(NotificationCompat.Extender extender) {
        this.notificationExtender = extender;
    }

    public final WaiterWithValue<Boolean> getDisplayWaiter() {
        return this.displayWaiter;
    }

    @Override // com.onesignal.notifications.INotification
    public List<Notification> getGroupedNotifications() {
        return this.groupedNotifications;
    }

    public void setGroupedNotifications(List<Notification> list) {
        this.groupedNotifications = list;
    }

    @Override // com.onesignal.notifications.INotification
    public int getAndroidNotificationId() {
        return this.androidNotificationId;
    }

    public void setAndroidNotificationId(int i) {
        this.androidNotificationId = i;
    }

    @Override // com.onesignal.notifications.INotification
    public String getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(String str) {
        this.notificationId = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(String str) {
        this.templateId = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    @Override // com.onesignal.notifications.INotification
    public JSONObject getAdditionalData() {
        return this.additionalData;
    }

    public void setAdditionalData(JSONObject jSONObject) {
        this.additionalData = jSONObject;
    }

    @Override // com.onesignal.notifications.INotification
    public String getSmallIcon() {
        return this.smallIcon;
    }

    public void setSmallIcon(String str) {
        this.smallIcon = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getLargeIcon() {
        return this.largeIcon;
    }

    public void setLargeIcon(String str) {
        this.largeIcon = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getBigPicture() {
        return this.bigPicture;
    }

    public void setBigPicture(String str) {
        this.bigPicture = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getSmallIconAccentColor() {
        return this.smallIconAccentColor;
    }

    public void setSmallIconAccentColor(String str) {
        this.smallIconAccentColor = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getLaunchURL() {
        return this.launchURL;
    }

    public void setLaunchURL(String str) {
        this.launchURL = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getSound() {
        return this.sound;
    }

    public void setSound(String str) {
        this.sound = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getLedColor() {
        return this.ledColor;
    }

    public void setLedColor(String str) {
        this.ledColor = str;
    }

    @Override // com.onesignal.notifications.INotification
    public int getLockScreenVisibility() {
        return this.lockScreenVisibility;
    }

    public void setLockScreenVisibility(int i) {
        this.lockScreenVisibility = i;
    }

    @Override // com.onesignal.notifications.INotification
    public String getGroupKey() {
        return this.groupKey;
    }

    public void setGroupKey(String str) {
        this.groupKey = str;
    }

    @Override // com.onesignal.notifications.INotification
    public String getGroupMessage() {
        return this.groupMessage;
    }

    public void setGroupMessage(String str) {
        this.groupMessage = str;
    }

    @Override // com.onesignal.notifications.INotification
    public List<IActionButton> getActionButtons() {
        return this.actionButtons;
    }

    public void setActionButtons(List<? extends IActionButton> list) {
        this.actionButtons = list;
    }

    @Override // com.onesignal.notifications.INotification
    public String getFromProjectNumber() {
        return this.fromProjectNumber;
    }

    public void setFromProjectNumber(String str) {
        this.fromProjectNumber = str;
    }

    @Override // com.onesignal.notifications.INotification
    public BackgroundImageLayout getBackgroundImageLayout() {
        return this.backgroundImageLayout;
    }

    public void setBackgroundImageLayout(BackgroundImageLayout backgroundImageLayout) {
        this.backgroundImageLayout = backgroundImageLayout;
    }

    @Override // com.onesignal.notifications.INotification
    public String getCollapseId() {
        return this.collapseId;
    }

    public void setCollapseId(String str) {
        this.collapseId = str;
    }

    @Override // com.onesignal.notifications.INotification
    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    @Override // com.onesignal.notifications.INotification
    public long getSentTime() {
        return this.sentTime;
    }

    public void setSentTime(long j) {
        this.sentTime = j;
    }

    @Override // com.onesignal.notifications.INotification
    public int getTtl() {
        return this.ttl;
    }

    public void setTtl(int i) {
        this.ttl = i;
    }

    @Override // com.onesignal.notifications.INotification
    public String getRawPayload() {
        return this.rawPayload;
    }

    public void setRawPayload(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.rawPayload = str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Notification(JSONObject payload, ITime time) {
        this(null, payload, 0, time);
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(time, InfluenceConstants.TIME);
    }

    public Notification(List<Notification> list, JSONObject jsonPayload, int androidNotificationId, ITime time) {
        Intrinsics.checkNotNullParameter(jsonPayload, "jsonPayload");
        Intrinsics.checkNotNullParameter(time, InfluenceConstants.TIME);
        this.displayWaiter = new WaiterWithValue<>();
        this.lockScreenVisibility = 1;
        this.rawPayload = "";
        initPayloadData(jsonPayload, time);
        setGroupedNotifications(list);
        setAndroidNotificationId(androidNotificationId);
    }

    private final void initPayloadData(JSONObject currentJsonPayload, ITime time) {
        try {
            JSONObject customJson = NotificationHelper.INSTANCE.getCustomJSONObject(currentJsonPayload);
            long currentTime = time.getCurrentTimeMillis();
            if (currentJsonPayload.has(NotificationConstants.GOOGLE_TTL_KEY)) {
                setSentTime(currentJsonPayload.optLong(NotificationConstants.GOOGLE_SENT_TIME_KEY, currentTime) / ((long) 1000));
                setTtl(currentJsonPayload.optInt(NotificationConstants.GOOGLE_TTL_KEY, 259200));
            } else if (currentJsonPayload.has("hms.ttl")) {
                setSentTime(currentJsonPayload.optLong("hms.sent_time", currentTime) / ((long) 1000));
                setTtl(currentJsonPayload.optInt("hms.ttl", 259200));
            } else {
                setSentTime(currentTime / ((long) 1000));
                setTtl(259200);
            }
            setNotificationId(JSONObjectExtensionsKt.safeString(customJson, "i"));
            setTemplateId(JSONObjectExtensionsKt.safeString(customJson, "ti"));
            setTemplateName(JSONObjectExtensionsKt.safeString(customJson, "tn"));
            String string = currentJsonPayload.toString();
            Intrinsics.checkNotNullExpressionValue(string, "currentJsonPayload.toString()");
            setRawPayload(string);
            setAdditionalData(JSONObjectExtensionsKt.safeJSONObject(customJson, "a"));
            setLaunchURL(JSONObjectExtensionsKt.safeString(customJson, "u"));
            setBody(JSONObjectExtensionsKt.safeString(currentJsonPayload, "alert"));
            setTitle(JSONObjectExtensionsKt.safeString(currentJsonPayload, OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE));
            setSmallIcon(JSONObjectExtensionsKt.safeString(currentJsonPayload, "sicon"));
            setBigPicture(JSONObjectExtensionsKt.safeString(currentJsonPayload, "bicon"));
            setLargeIcon(JSONObjectExtensionsKt.safeString(currentJsonPayload, "licon"));
            setSound(JSONObjectExtensionsKt.safeString(currentJsonPayload, "sound"));
            setGroupKey(JSONObjectExtensionsKt.safeString(currentJsonPayload, "grp"));
            setGroupMessage(JSONObjectExtensionsKt.safeString(currentJsonPayload, "grp_msg"));
            setSmallIconAccentColor(JSONObjectExtensionsKt.safeString(currentJsonPayload, "bgac"));
            setLedColor(JSONObjectExtensionsKt.safeString(currentJsonPayload, "ledc"));
            String visibility = JSONObjectExtensionsKt.safeString(currentJsonPayload, "vis");
            if (visibility != null) {
                setLockScreenVisibility(Integer.parseInt(visibility));
            }
            setFromProjectNumber(JSONObjectExtensionsKt.safeString(currentJsonPayload, "from"));
            setPriority(currentJsonPayload.optInt("pri", 0));
            String collapseKey = JSONObjectExtensionsKt.safeString(currentJsonPayload, "collapse_key");
            if (!Intrinsics.areEqual("do_not_collapse", collapseKey)) {
                setCollapseId(collapseKey);
            }
            try {
                setActionButtonsFromData();
            } catch (Throwable t) {
                Logging.error("Error assigning OSNotificationReceivedEvent.actionButtons values!", t);
            }
            try {
                setBackgroundImageLayoutFromData(currentJsonPayload);
            } catch (Throwable t2) {
                Logging.error("Error assigning OSNotificationReceivedEvent.backgroundImageLayout values!", t2);
            }
        } catch (Throwable t3) {
            Logging.error("Error assigning OSNotificationReceivedEvent payload values!", t3);
        }
    }

    private final void setActionButtonsFromData() throws Throwable {
        if (getAdditionalData() != null) {
            JSONObject additionalData = getAdditionalData();
            Intrinsics.checkNotNull(additionalData);
            if (additionalData.has("actionButtons")) {
                JSONObject additionalData2 = getAdditionalData();
                Intrinsics.checkNotNull(additionalData2);
                JSONArray jsonActionButtons = additionalData2.getJSONArray("actionButtons");
                List actionBtns = new ArrayList();
                int length = jsonActionButtons.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonActionButton = jsonActionButtons.getJSONObject(i);
                    Intrinsics.checkNotNullExpressionValue(jsonActionButton, "jsonActionButton");
                    ActionButton actionButton = new ActionButton(JSONObjectExtensionsKt.safeString(jsonActionButton, OutcomeConstants.OUTCOME_ID), JSONObjectExtensionsKt.safeString(jsonActionButton, "text"), JSONObjectExtensionsKt.safeString(jsonActionButton, "icon"));
                    actionBtns.add(actionButton);
                }
                setActionButtons(actionBtns);
                JSONObject additionalData3 = getAdditionalData();
                Intrinsics.checkNotNull(additionalData3);
                additionalData3.remove(NotificationConstants.GENERATE_NOTIFICATION_BUNDLE_KEY_ACTION_ID);
                JSONObject additionalData4 = getAdditionalData();
                Intrinsics.checkNotNull(additionalData4);
                additionalData4.remove("actionButtons");
            }
        }
    }

    private final void setBackgroundImageLayoutFromData(JSONObject currentJsonPayload) throws Throwable {
        String jsonStrBgImage = JSONObjectExtensionsKt.safeString(currentJsonPayload, "bg_img");
        if (jsonStrBgImage != null) {
            JSONObject jsonBgImage = new JSONObject(jsonStrBgImage);
            setBackgroundImageLayout(new BackgroundImageLayout(JSONObjectExtensionsKt.safeString(jsonBgImage, "img"), JSONObjectExtensionsKt.safeString(jsonBgImage, "tc"), JSONObjectExtensionsKt.safeString(jsonBgImage, "bc")));
        }
    }

    @Override // com.onesignal.notifications.IMutableNotification
    public void setExtender(NotificationCompat.Extender extender) {
        this.notificationExtender = extender;
    }

    public final boolean hasNotificationId() {
        return getAndroidNotificationId() != 0;
    }

    public final JSONObject toJSONObject() {
        JSONObject mainObj = new JSONObject();
        try {
            mainObj.put(NotificationConstants.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, getAndroidNotificationId());
            JSONArray payloadJsonArray = new JSONArray();
            if (getGroupedNotifications() != null) {
                List<Notification> groupedNotifications = getGroupedNotifications();
                Intrinsics.checkNotNull(groupedNotifications);
                for (Notification notification : groupedNotifications) {
                    payloadJsonArray.put(notification.toJSONObject());
                }
            }
            mainObj.put("groupedNotifications", payloadJsonArray);
            mainObj.put("notificationId", getNotificationId());
            mainObj.put("templateName", getTemplateName());
            mainObj.put("templateId", getTemplateId());
            mainObj.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE, getTitle());
            mainObj.put("body", getBody());
            mainObj.put("smallIcon", getSmallIcon());
            mainObj.put("largeIcon", getLargeIcon());
            mainObj.put("bigPicture", getBigPicture());
            mainObj.put("smallIconAccentColor", getSmallIconAccentColor());
            mainObj.put("launchURL", getLaunchURL());
            mainObj.put("sound", getSound());
            mainObj.put("ledColor", getLedColor());
            mainObj.put("lockScreenVisibility", getLockScreenVisibility());
            mainObj.put("groupKey", getGroupKey());
            mainObj.put("groupMessage", getGroupMessage());
            mainObj.put("fromProjectNumber", getFromProjectNumber());
            mainObj.put("collapseId", getCollapseId());
            mainObj.put("priority", getPriority());
            if (getAdditionalData() != null) {
                mainObj.put("additionalData", getAdditionalData());
            }
            if (getActionButtons() != null) {
                JSONArray actionButtonJsonArray = new JSONArray();
                List<IActionButton> actionButtons = getActionButtons();
                Intrinsics.checkNotNull(actionButtons);
                for (IActionButton actionButton : actionButtons) {
                    Intrinsics.checkNotNull(actionButton, "null cannot be cast to non-null type com.onesignal.notifications.internal.Notification.ActionButton");
                    actionButtonJsonArray.put(((ActionButton) actionButton).toJSONObject());
                }
                mainObj.put("actionButtons", actionButtonJsonArray);
            }
            mainObj.put("rawPayload", getRawPayload());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mainObj;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OSNotification{notificationExtender=").append(this.notificationExtender).append(", groupedNotifications=").append(getGroupedNotifications()).append(", androidNotificationId=").append(getAndroidNotificationId()).append(", notificationId='").append(getNotificationId()).append("', templateName='").append(getTemplateName()).append("', templateId='").append(getTemplateId()).append("', title='").append(getTitle()).append("', body='").append(getBody()).append("', additionalData=").append(getAdditionalData()).append(", smallIcon='").append(getSmallIcon()).append("', largeIcon='").append(getLargeIcon()).append("', bigPicture='");
        sb.append(getBigPicture()).append("', smallIconAccentColor='").append(getSmallIconAccentColor()).append("', launchURL='").append(getLaunchURL()).append("', sound='").append(getSound()).append("', ledColor='").append(getLedColor()).append("', lockScreenVisibility=").append(getLockScreenVisibility()).append(", groupKey='").append(getGroupKey()).append("', groupMessage='").append(getGroupMessage()).append("', actionButtons=").append(getActionButtons()).append(", fromProjectNumber='").append(getFromProjectNumber()).append("', backgroundImageLayout=").append(getBackgroundImageLayout()).append(", collapseId='").append(getCollapseId());
        sb.append("', priority=").append(getPriority()).append(", rawPayload='").append(getRawPayload()).append("'}");
        return sb.toString();
    }

    @Override // com.onesignal.notifications.IDisplayableNotification
    public void display() throws Exception {
        this.displayWaiter.wake(true);
    }

    /* JADX INFO: compiled from: Notification.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\r"}, d2 = {"Lcom/onesignal/notifications/internal/Notification$ActionButton;", "Lcom/onesignal/notifications/IActionButton;", OutcomeConstants.OUTCOME_ID, "", "text", "icon", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getIcon", "()Ljava/lang/String;", "getId", "getText", "toJSONObject", "Lorg/json/JSONObject;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class ActionButton implements IActionButton {
        private final String icon;
        private final String id;
        private final String text;

        public ActionButton() {
            this(null, null, null, 7, null);
        }

        public ActionButton(String id, String text, String icon) {
            this.id = id;
            this.text = text;
            this.icon = icon;
        }

        public /* synthetic */ ActionButton(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
        }

        @Override // com.onesignal.notifications.IActionButton
        public String getId() {
            return this.id;
        }

        @Override // com.onesignal.notifications.IActionButton
        public String getText() {
            return this.text;
        }

        @Override // com.onesignal.notifications.IActionButton
        public String getIcon() {
            return this.icon;
        }

        public final JSONObject toJSONObject() {
            JSONObject json = new JSONObject();
            try {
                json.put(OutcomeConstants.OUTCOME_ID, getId());
                json.put("text", getText());
                json.put("icon", getIcon());
            } catch (Throwable t) {
                t.printStackTrace();
            }
            return json;
        }
    }
}
