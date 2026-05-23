package com.onesignal.notifications.internal.display.impl;

import android.R;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.onesignal.common.AndroidUtils;
import com.onesignal.core.R;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.inAppMessages.internal.prompt.InAppMessagePromptTypes;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.internal.bundle.impl.NotificationBundleProcessor;
import com.onesignal.notifications.internal.channels.INotificationChannelManager;
import com.onesignal.notifications.internal.common.NotificationConstants;
import com.onesignal.notifications.internal.common.NotificationFormatHelper;
import com.onesignal.notifications.internal.common.NotificationGenerationJob;
import com.onesignal.notifications.internal.common.NotificationHelper;
import com.onesignal.notifications.internal.display.INotificationDisplayBuilder;
import com.onesignal.notifications.receivers.NotificationDismissReceiver;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NotificationDisplayBuilder.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001:\u0001UB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J4\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001a0#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0#H\u0002J,\u0010%\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001a0#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0#H\u0002J4\u0010&\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\u00142\b\u0010,\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010-\u001a\u00020\u001e2\b\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020\u00142\u0006\u00103\u001a\u00020\u0014H\u0002J\u0012\u00104\u001a\u0004\u0018\u0001052\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u00106\u001a\u00020/2\u0006\u00107\u001a\u000208H\u0016J\u0014\u00109\u001a\u0004\u0018\u00010\u00102\b\u0010:\u001a\u0004\u0018\u00010\u001aH\u0002J\u0012\u0010;\u001a\u0004\u0018\u00010\u00102\u0006\u0010<\u001a\u00020\u001aH\u0002J\u0012\u0010=\u001a\u0004\u0018\u00010\u00102\u0006\u0010>\u001a\u00020\u001aH\u0002J\u0010\u0010?\u001a\u00020\u00142\u0006\u0010:\u001a\u00020\u001aH\u0002J\b\u0010@\u001a\u00020\u0014H\u0016J\u0012\u0010A\u001a\u0004\u0018\u00010\u00102\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010B\u001a\u00020C2\u0006\u0010+\u001a\u00020\u0014H\u0016J\u0018\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\u00142\u0006\u0010G\u001a\u00020CH\u0016J\u0012\u0010H\u001a\u00020\u00142\b\u0010I\u001a\u0004\u0018\u00010\u001aH\u0002J\u0010\u0010J\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010K\u001a\u00020L2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010M\u001a\u00020N2\u0006\u0010 \u001a\u00020!H\u0002J\u0012\u0010O\u001a\u00020\u001e2\b\u0010P\u001a\u0004\u0018\u00010*H\u0016J\u0014\u0010Q\u001a\u0004\u0018\u00010\u00102\b\u0010R\u001a\u0004\u0018\u00010\u0010H\u0002J\u0018\u0010S\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010T\u001a\u00020*H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006V"}, d2 = {"Lcom/onesignal/notifications/internal/display/impl/NotificationDisplayBuilder;", "Lcom/onesignal/notifications/internal/display/INotificationDisplayBuilder;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_notificationChannelManager", "Lcom/onesignal/notifications/internal/channels/INotificationChannelManager;", "(Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/notifications/internal/channels/INotificationChannelManager;)V", "contextResources", "Landroid/content/res/Resources;", "getContextResources", "()Landroid/content/res/Resources;", "currentContext", "Landroid/content/Context;", "getCurrentContext", "()Landroid/content/Context;", "defaultLargeIcon", "Landroid/graphics/Bitmap;", "getDefaultLargeIcon", "()Landroid/graphics/Bitmap;", "defaultSmallIconId", "", "getDefaultSmallIconId", "()I", "notificationDismissedClass", "Ljava/lang/Class;", "packageName", "", "getPackageName", "()Ljava/lang/String;", "addAlertButtons", "", "context", "fcmJson", "Lorg/json/JSONObject;", "buttonsLabels", "", "buttonsIds", "addCustomAlertButtons", "addNotificationActionButtons", "intentGenerator", "Lcom/onesignal/notifications/internal/display/impl/IntentGeneratorForAttachingToNotifications;", "mBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "notificationId", "groupSummary", "addXiaomiSettings", "oneSignalNotificationBuilder", "Lcom/onesignal/notifications/internal/display/impl/NotificationDisplayBuilder$OneSignalNotificationBuilder;", OneSignalDbContract.NotificationTable.TABLE_NAME, "Landroid/app/Notification;", "convertOSToAndroidPriority", "priority", "getAccentColor", "Ljava/math/BigInteger;", "getBaseOneSignalNotificationBuilder", "notificationJob", "Lcom/onesignal/notifications/internal/common/NotificationGenerationJob;", "getBitmap", "name", "getBitmapFromAssetsOrResourceName", "bitmapStr", "getBitmapFromURL", InAppMessagePromptTypes.LOCATION_PROMPT_KEY, "getDrawableId", "getGroupAlertBehavior", "getLargeIcon", "getNewBaseDismissIntent", "Landroid/content/Intent;", "getNewDismissActionPendingIntent", "Landroid/app/PendingIntent;", "requestCode", "intent", "getResourceIcon", "iconName", "getSmallIconId", "getTitle", "", "isSoundEnabled", "", "removeNotifyOptions", "builder", "resizeBitmapForLargeIconArea", "bitmap", "setAlertnessOptions", "notifBuilder", "OneSignalNotificationBuilder", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationDisplayBuilder implements INotificationDisplayBuilder {
    private final IApplicationService _applicationService;
    private final INotificationChannelManager _notificationChannelManager;
    private final Class<?> notificationDismissedClass;

    public NotificationDisplayBuilder(IApplicationService _applicationService, INotificationChannelManager _notificationChannelManager) {
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_notificationChannelManager, "_notificationChannelManager");
        this._applicationService = _applicationService;
        this._notificationChannelManager = _notificationChannelManager;
        this.notificationDismissedClass = NotificationDismissReceiver.class;
    }

    private final Resources getContextResources() {
        return this._applicationService.getAppContext().getResources();
    }

    private final Context getCurrentContext() {
        return this._applicationService.getAppContext();
    }

    private final String getPackageName() {
        return this._applicationService.getAppContext().getPackageName();
    }

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayBuilder
    public int getGroupAlertBehavior() {
        return 2;
    }

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayBuilder
    public CharSequence getTitle(JSONObject fcmJson) {
        Intrinsics.checkNotNullParameter(fcmJson, "fcmJson");
        CharSequence title = fcmJson.optString(OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE, null);
        if (title != null) {
            return title;
        }
        Context currentContext = getCurrentContext();
        Intrinsics.checkNotNull(currentContext);
        PackageManager packageManager = currentContext.getPackageManager();
        Context currentContext2 = getCurrentContext();
        Intrinsics.checkNotNull(currentContext2);
        CharSequence applicationLabel = packageManager.getApplicationLabel(currentContext2.getApplicationInfo());
        Intrinsics.checkNotNullExpressionValue(applicationLabel, "currentContext!!.package…cationInfo,\n            )");
        return applicationLabel;
    }

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayBuilder
    public PendingIntent getNewDismissActionPendingIntent(int requestCode, Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        PendingIntent broadcast = PendingIntent.getBroadcast(getCurrentContext(), requestCode, intent, 201326592);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(\n          …FLAG_IMMUTABLE,\n        )");
        return broadcast;
    }

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayBuilder
    public Intent getNewBaseDismissIntent(int notificationId) {
        Intent intentPutExtra = new Intent(getCurrentContext(), this.notificationDismissedClass).putExtra(NotificationConstants.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, notificationId).putExtra(OneSignalDbContract.NotificationTable.COLUMN_NAME_DISMISSED, true);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "Intent(currentContext, n…tExtra(\"dismissed\", true)");
        return intentPutExtra;
    }

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayBuilder
    public OneSignalNotificationBuilder getBaseOneSignalNotificationBuilder(NotificationGenerationJob notificationJob) {
        Intrinsics.checkNotNullParameter(notificationJob, "notificationJob");
        JSONObject fcmJson = notificationJob.getJsonPayload();
        Intrinsics.checkNotNull(fcmJson);
        OneSignalNotificationBuilder oneSignalNotificationBuilder = new OneSignalNotificationBuilder();
        String channelId = this._notificationChannelManager.createNotificationChannel(notificationJob);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getCurrentContext(), channelId);
        String message = fcmJson.optString("alert", null);
        notificationBuilder.setAutoCancel(true).setSmallIcon(getSmallIconId(fcmJson)).setStyle(new NotificationCompat.BigTextStyle().bigText(message)).setContentText(message).setTicker(message);
        if (!Intrinsics.areEqual(fcmJson.optString(OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE), "")) {
            notificationBuilder.setContentTitle(getTitle(fcmJson));
        }
        try {
            BigInteger accentColor = getAccentColor(fcmJson);
            if (accentColor != null) {
                notificationBuilder.setColor(accentColor.intValue());
            }
        } catch (Throwable th) {
        }
        int lockScreenVisibility = 1;
        try {
            if (fcmJson.has("vis")) {
                String strOptString = fcmJson.optString("vis");
                Intrinsics.checkNotNullExpressionValue(strOptString, "fcmJson.optString(\"vis\")");
                lockScreenVisibility = Integer.parseInt(strOptString);
            }
            notificationBuilder.setVisibility(lockScreenVisibility);
        } catch (Throwable th2) {
        }
        Bitmap largeIcon = getLargeIcon(fcmJson);
        if (largeIcon != null) {
            oneSignalNotificationBuilder.setHasLargeIcon(true);
            notificationBuilder.setLargeIcon(largeIcon);
        }
        Bitmap bigPictureIcon = getBitmap(fcmJson.optString("bicon", null));
        if (bigPictureIcon != null) {
            notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bigPictureIcon).setSummaryText(message));
        }
        if (notificationJob.getShownTimeStamp() != null) {
            try {
                Long shownTimeStamp = notificationJob.getShownTimeStamp();
                Intrinsics.checkNotNull(shownTimeStamp);
                notificationBuilder.setWhen(shownTimeStamp.longValue() * 1000);
            } catch (Throwable th3) {
            }
        }
        setAlertnessOptions(fcmJson, notificationBuilder);
        oneSignalNotificationBuilder.setCompatBuilder(notificationBuilder);
        return oneSignalNotificationBuilder;
    }

    private final void setAlertnessOptions(JSONObject fcmJson, NotificationCompat.Builder notifBuilder) {
        int payloadPriority = fcmJson.optInt("pri", 6);
        int androidPriority = convertOSToAndroidPriority(payloadPriority);
        notifBuilder.setPriority(androidPriority);
        boolean lowDisplayPriority = androidPriority < 0;
        if (lowDisplayPriority) {
            return;
        }
        int notificationDefaults = 0;
        if (fcmJson.has("ledc") && fcmJson.optInt("led", 1) == 1) {
            try {
                BigInteger ledColor = new BigInteger(fcmJson.optString("ledc"), 16);
                notifBuilder.setLights(ledColor.intValue(), 2000, 5000);
            } catch (Throwable th) {
                notificationDefaults = 0 | 4;
            }
        } else {
            notificationDefaults = 0 | 4;
        }
        if (fcmJson.optInt("vib", 1) == 1) {
            if (fcmJson.has("vib_pt")) {
                long[] vibrationPattern = NotificationHelper.INSTANCE.parseVibrationPattern(fcmJson);
                if (vibrationPattern != null) {
                    notifBuilder.setVibrate(vibrationPattern);
                }
            } else {
                notificationDefaults |= 2;
            }
        }
        if (isSoundEnabled(fcmJson)) {
            NotificationHelper notificationHelper = NotificationHelper.INSTANCE;
            Context currentContext = getCurrentContext();
            Intrinsics.checkNotNull(currentContext);
            Uri soundUri = notificationHelper.getSoundUri(currentContext, fcmJson.optString("sound", null));
            if (soundUri != null) {
                notifBuilder.setSound(soundUri);
            } else {
                notificationDefaults |= 1;
            }
        }
        notifBuilder.setDefaults(notificationDefaults);
    }

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayBuilder
    public void removeNotifyOptions(NotificationCompat.Builder builder) {
        Intrinsics.checkNotNull(builder);
        builder.setOnlyAlertOnce(true).setDefaults(0).setSound((Uri) null).setVibrate((long[]) null).setTicker((CharSequence) null);
    }

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayBuilder
    public void addXiaomiSettings(OneSignalNotificationBuilder oneSignalNotificationBuilder, Notification notification) {
        Intrinsics.checkNotNullParameter(notification, OneSignalDbContract.NotificationTable.TABLE_NAME);
        Intrinsics.checkNotNull(oneSignalNotificationBuilder);
        if (oneSignalNotificationBuilder.getHasLargeIcon()) {
            try {
                Object miuiNotification = Class.forName("android.app.MiuiNotification").newInstance();
                Field customizedIconField = miuiNotification.getClass().getDeclaredField("customizedIcon");
                customizedIconField.setAccessible(true);
                customizedIconField.set(miuiNotification, true);
                Field extraNotificationField = notification.getClass().getField("extraNotification");
                extraNotificationField.setAccessible(true);
                extraNotificationField.set(notification, miuiNotification);
            } catch (Throwable th) {
            }
        }
    }

    private final Bitmap getLargeIcon(JSONObject fcmJson) {
        Bitmap bitmap = getBitmap(fcmJson.optString("licon"));
        if (bitmap == null) {
            bitmap = getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default");
        }
        if (bitmap == null) {
            return null;
        }
        return resizeBitmapForLargeIconArea(bitmap);
    }

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayBuilder
    public Bitmap getDefaultLargeIcon() {
        Bitmap bitmap = getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default");
        return resizeBitmapForLargeIconArea(bitmap);
    }

    private final Bitmap resizeBitmapForLargeIconArea(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            Resources contextResources = getContextResources();
            Intrinsics.checkNotNull(contextResources);
            int systemLargeIconHeight = (int) contextResources.getDimension(R.dimen.notification_large_icon_height);
            Resources contextResources2 = getContextResources();
            Intrinsics.checkNotNull(contextResources2);
            int systemLargeIconWidth = (int) contextResources2.getDimension(R.dimen.notification_large_icon_width);
            int bitmapHeight = bitmap.getHeight();
            int bitmapWidth = bitmap.getWidth();
            if (bitmapWidth > systemLargeIconWidth || bitmapHeight > systemLargeIconHeight) {
                int newWidth = systemLargeIconWidth;
                int newHeight = systemLargeIconHeight;
                if (bitmapHeight > bitmapWidth) {
                    float ratio = bitmapWidth / bitmapHeight;
                    newWidth = (int) (newHeight * ratio);
                } else if (bitmapWidth > bitmapHeight) {
                    float ratio2 = bitmapHeight / bitmapWidth;
                    newHeight = (int) (newWidth * ratio2);
                }
                return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
            }
        } catch (Throwable th) {
        }
        return bitmap;
    }

    private final Bitmap getBitmapFromAssetsOrResourceName(String bitmapStr) {
        Bitmap bitmap = null;
        try {
            Context currentContext = getCurrentContext();
            Intrinsics.checkNotNull(currentContext);
            bitmap = BitmapFactory.decodeStream(currentContext.getAssets().open(bitmapStr));
        } catch (Throwable th) {
        }
        if (bitmap != null) {
            return bitmap;
        }
        try {
            List<String> imageExtensions = Arrays.asList(".png", ".webp", ".jpg", ".gif", ".bmp");
            for (String extension : imageExtensions) {
                try {
                    Context currentContext2 = getCurrentContext();
                    Intrinsics.checkNotNull(currentContext2);
                    Bitmap bitmap2 = BitmapFactory.decodeStream(currentContext2.getAssets().open(bitmapStr + extension));
                    bitmap = bitmap2;
                } catch (Throwable th2) {
                }
                if (bitmap != null) {
                    return bitmap;
                }
            }
            int bitmapId = getResourceIcon(bitmapStr);
            if (bitmapId != 0) {
                return BitmapFactory.decodeResource(getContextResources(), bitmapId);
            }
            return null;
        } catch (Throwable th3) {
            return null;
        }
    }

    private final Bitmap getBitmapFromURL(String location) {
        try {
            return BitmapFactory.decodeStream(new URL(location).openConnection().getInputStream());
        } catch (Throwable t) {
            Logging.warn("Could not download image!", t);
            return null;
        }
    }

    private final Bitmap getBitmap(String name) {
        if (name == null) {
            return null;
        }
        String $this$trim$iv$iv = name;
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
        boolean startFound$iv$iv = false;
        while (startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            char it = Intrinsics.compare($this$trim$iv$iv.charAt(index$iv$iv), 32) <= 0 ? (char) 1 : (char) 0;
            if (!startFound$iv$iv) {
                if (it == 0) {
                    startFound$iv$iv = true;
                } else {
                    startIndex$iv$iv++;
                }
            } else {
                if (it == 0) {
                    break;
                }
                endIndex$iv$iv--;
            }
        }
        String $this$trim$iv = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
        if (StringsKt.startsWith$default($this$trim$iv, "http://", false, 2, (Object) null) || StringsKt.startsWith$default($this$trim$iv, "https://", false, 2, (Object) null)) {
            return getBitmapFromURL($this$trim$iv);
        }
        return getBitmapFromAssetsOrResourceName(name);
    }

    private final int getResourceIcon(String iconName) {
        if (iconName == null) {
            return 0;
        }
        String $this$trim$iv$iv = iconName;
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
        boolean startFound$iv$iv = false;
        while (startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            char it = Intrinsics.compare($this$trim$iv$iv.charAt(index$iv$iv), 32) <= 0 ? (char) 1 : (char) 0;
            if (!startFound$iv$iv) {
                if (it == 0) {
                    startFound$iv$iv = true;
                } else {
                    startIndex$iv$iv++;
                }
            } else {
                if (it == 0) {
                    break;
                }
                endIndex$iv$iv--;
            }
        }
        String $this$trim$iv = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
        if (!AndroidUtils.INSTANCE.isValidResourceName($this$trim$iv)) {
            return 0;
        }
        int notificationIcon = getDrawableId($this$trim$iv);
        if (notificationIcon != 0) {
            return notificationIcon;
        }
        try {
            return R.drawable.class.getField(iconName).getInt(null);
        } catch (Throwable th) {
            return 0;
        }
    }

    private final int getSmallIconId(JSONObject fcmJson) {
        int notificationIcon = getResourceIcon(fcmJson.optString("sicon", null));
        return notificationIcon != 0 ? notificationIcon : getDefaultSmallIconId();
    }

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayBuilder
    public int getDefaultSmallIconId() {
        int notificationIcon = getDrawableId("ic_stat_onesignal_default");
        if (notificationIcon != 0) {
            return notificationIcon;
        }
        int notificationIcon2 = getDrawableId("corona_statusbar_icon_default");
        if (notificationIcon2 != 0) {
            return notificationIcon2;
        }
        int notificationIcon3 = getDrawableId("ic_os_notification_fallback_white_24dp");
        return notificationIcon3 != 0 ? notificationIcon3 : android.R.drawable.ic_popup_reminder;
    }

    private final int getDrawableId(String name) {
        Resources contextResources = getContextResources();
        Intrinsics.checkNotNull(contextResources);
        return contextResources.getIdentifier(name, "drawable", getPackageName());
    }

    private final boolean isSoundEnabled(JSONObject fcmJson) {
        String sound = fcmJson.optString("sound", null);
        return (Intrinsics.areEqual("null", sound) || Intrinsics.areEqual("nil", sound)) ? false : true;
    }

    private final BigInteger getAccentColor(JSONObject fcmJson) {
        try {
            if (fcmJson.has("bgac")) {
                return new BigInteger(fcmJson.optString("bgac", null), 16);
            }
        } catch (Throwable th) {
        }
        try {
            String defaultColor = AndroidUtils.INSTANCE.getResourceString(this._applicationService.getAppContext(), "onesignal_notification_accent_color", null);
            if (defaultColor != null) {
                return new BigInteger(defaultColor, 16);
            }
        } catch (Throwable th2) {
        }
        try {
            String defaultColor2 = AndroidUtils.INSTANCE.getManifestMeta(this._applicationService.getAppContext(), "com.onesignal.NotificationAccentColor.DEFAULT");
            if (defaultColor2 != null) {
                return new BigInteger(defaultColor2, 16);
            }
        } catch (Throwable th3) {
        }
        return null;
    }

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayBuilder
    public void addNotificationActionButtons(JSONObject fcmJson, IntentGeneratorForAttachingToNotifications intentGenerator, NotificationCompat.Builder mBuilder, int notificationId, String groupSummary) {
        JSONObject jSONObject = fcmJson;
        IntentGeneratorForAttachingToNotifications intentGeneratorForAttachingToNotifications = intentGenerator;
        Intrinsics.checkNotNullParameter(jSONObject, "fcmJson");
        Intrinsics.checkNotNullParameter(intentGeneratorForAttachingToNotifications, "intentGenerator");
        try {
            JSONObject customJson = new JSONObject(jSONObject.optString(NotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM));
            if (customJson.has("a")) {
                JSONObject additionalDataJSON = customJson.getJSONObject("a");
                if (additionalDataJSON.has("actionButtons")) {
                    JSONArray buttons = additionalDataJSON.getJSONArray("actionButtons");
                    int i = 0;
                    int length = buttons.length();
                    while (i < length) {
                        JSONObject button = buttons.optJSONObject(i);
                        JSONObject bundle = new JSONObject(jSONObject.toString());
                        Intent buttonIntent = intentGeneratorForAttachingToNotifications.getNewBaseIntent(notificationId);
                        buttonIntent.setAction("" + i);
                        buttonIntent.putExtra("action_button", true);
                        bundle.put(NotificationConstants.GENERATE_NOTIFICATION_BUNDLE_KEY_ACTION_ID, button.optString(OutcomeConstants.OUTCOME_ID));
                        buttonIntent.putExtra(NotificationConstants.BUNDLE_KEY_ONESIGNAL_DATA, bundle.toString());
                        if (groupSummary != null) {
                            buttonIntent.putExtra("summary", groupSummary);
                        } else if (jSONObject.has("grp")) {
                            buttonIntent.putExtra("grp", jSONObject.optString("grp"));
                        }
                        PendingIntent buttonPIntent = intentGeneratorForAttachingToNotifications.getNewActionPendingIntent(notificationId, buttonIntent);
                        int buttonIcon = 0;
                        if (button.has("icon")) {
                            try {
                                buttonIcon = getResourceIcon(button.optString("icon"));
                            } catch (Throwable th) {
                                t = th;
                            }
                        }
                        Intrinsics.checkNotNull(mBuilder);
                        try {
                            mBuilder.addAction(buttonIcon, button.optString("text"), buttonPIntent);
                            i++;
                            jSONObject = fcmJson;
                            intentGeneratorForAttachingToNotifications = intentGenerator;
                        } catch (Throwable th2) {
                            t = th2;
                        }
                    }
                    return;
                }
                return;
            }
            return;
        } catch (Throwable th3) {
            t = th3;
        }
        t.printStackTrace();
    }

    private final void addAlertButtons(Context context, JSONObject fcmJson, List<String> list, List<String> list2) {
        try {
            addCustomAlertButtons(fcmJson, list, list2);
        } catch (Throwable t) {
            Logging.error("Failed to parse JSON for custom buttons for alert dialog.", t);
        }
        if (list.size() == 0 || list.size() < 3) {
            String resourceString = AndroidUtils.INSTANCE.getResourceString(context, "onesignal_in_app_alert_ok_button_text", "Ok");
            Intrinsics.checkNotNull(resourceString);
            list.add(resourceString);
            list2.add(NotificationBundleProcessor.DEFAULT_ACTION);
        }
    }

    private final void addCustomAlertButtons(JSONObject fcmJson, List<String> list, List<String> list2) throws JSONException {
        JSONObject customJson = new JSONObject(fcmJson.optString(NotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM));
        if (customJson.has("a")) {
            JSONObject additionalDataJSON = customJson.getJSONObject("a");
            if (additionalDataJSON.has("actionButtons")) {
                JSONArray buttons = additionalDataJSON.optJSONArray("actionButtons");
                int length = buttons.length();
                for (int i = 0; i < length; i++) {
                    JSONObject button = buttons.getJSONObject(i);
                    String strOptString = button.optString("text");
                    Intrinsics.checkNotNullExpressionValue(strOptString, "button.optString(\"text\")");
                    list.add(strOptString);
                    String strOptString2 = button.optString(OutcomeConstants.OUTCOME_ID);
                    Intrinsics.checkNotNullExpressionValue(strOptString2, "button.optString(\"id\")");
                    list2.add(strOptString2);
                }
            }
        }
    }

    private final int convertOSToAndroidPriority(int priority) {
        if (priority > 9) {
            return 2;
        }
        if (priority > 7) {
            return 1;
        }
        if (priority > 4) {
            return 0;
        }
        return priority > 2 ? -1 : -2;
    }

    /* JADX INFO: compiled from: NotificationDisplayBuilder.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/onesignal/notifications/internal/display/impl/NotificationDisplayBuilder$OneSignalNotificationBuilder;", "", "()V", "compatBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "getCompatBuilder", "()Landroidx/core/app/NotificationCompat$Builder;", "setCompatBuilder", "(Landroidx/core/app/NotificationCompat$Builder;)V", "hasLargeIcon", "", "getHasLargeIcon", "()Z", "setHasLargeIcon", "(Z)V", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class OneSignalNotificationBuilder {
        private NotificationCompat.Builder compatBuilder;
        private boolean hasLargeIcon;

        public final NotificationCompat.Builder getCompatBuilder() {
            return this.compatBuilder;
        }

        public final void setCompatBuilder(NotificationCompat.Builder builder) {
            this.compatBuilder = builder;
        }

        public final boolean getHasLargeIcon() {
            return this.hasLargeIcon;
        }

        public final void setHasLargeIcon(boolean z) {
            this.hasLargeIcon = z;
        }
    }
}
