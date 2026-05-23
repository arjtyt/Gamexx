package com.onesignal.notifications.internal.display.impl;

import android.R;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.onesignal.common.AndroidUtils;
import com.onesignal.common.exceptions.MainThreadException;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.inAppMessages.internal.prompt.InAppMessagePromptTypes;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.R;
import com.onesignal.notifications.internal.common.NotificationConstants;
import com.onesignal.notifications.internal.common.NotificationGenerationJob;
import com.onesignal.notifications.internal.display.INotificationDisplayBuilder;
import com.onesignal.notifications.internal.display.INotificationDisplayer;
import com.onesignal.notifications.internal.display.ISummaryNotificationDisplayer;
import com.onesignal.notifications.internal.limiting.INotificationLimitManager;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: NotificationDisplayer.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001a\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\u001a\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u001eH\u0002J*\u0010#\u001a\u00020$2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020)H\u0002J\u0019\u0010*\u001a\u00020+2\u0006\u0010 \u001a\u00020!H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010,J\u0014\u0010-\u001a\u0004\u0018\u00010.2\b\u0010/\u001a\u0004\u0018\u00010\u0017H\u0002J\u0012\u00100\u001a\u0004\u0018\u00010.2\u0006\u00101\u001a\u00020\u0017H\u0002J\u0012\u00102\u001a\u0004\u0018\u00010.2\u0006\u00103\u001a\u00020\u0017H\u0002J\u0010\u00104\u001a\u00020)2\u0006\u0010/\u001a\u00020\u0017H\u0002J\u0012\u00105\u001a\u00020)2\b\u00106\u001a\u0004\u0018\u00010\u0017H\u0002J!\u00107\u001a\u0004\u0018\u00010)2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u00108\u001a\u00020\u0017H\u0002¢\u0006\u0002\u00109J2\u0010:\u001a\u00020\u00142\u0006\u0010;\u001a\u00020<2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010=\u001a\u00020)2\u0006\u0010>\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u0017H\u0002J\u0019\u0010@\u001a\u00020+2\u0006\u0010 \u001a\u00020!H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010,R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006A"}, d2 = {"Lcom/onesignal/notifications/internal/display/impl/NotificationDisplayer;", "Lcom/onesignal/notifications/internal/display/INotificationDisplayer;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_notificationLimitManager", "Lcom/onesignal/notifications/internal/limiting/INotificationLimitManager;", "_summaryNotificationDisplayer", "Lcom/onesignal/notifications/internal/display/ISummaryNotificationDisplayer;", "_notificationDisplayBuilder", "Lcom/onesignal/notifications/internal/display/INotificationDisplayBuilder;", "(Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/notifications/internal/limiting/INotificationLimitManager;Lcom/onesignal/notifications/internal/display/ISummaryNotificationDisplayer;Lcom/onesignal/notifications/internal/display/INotificationDisplayBuilder;)V", "contextResources", "Landroid/content/res/Resources;", "getContextResources", "()Landroid/content/res/Resources;", "currentContext", "Landroid/content/Context;", "getCurrentContext", "()Landroid/content/Context;", "isRunningOnMainThreadCheck", "", "()Lkotlin/Unit;", "packageName", "", "getPackageName", "()Ljava/lang/String;", "addBackgroundImage", "fcmJson", "Lorg/json/JSONObject;", "notifBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "applyNotificationExtender", "notificationJob", "Lcom/onesignal/notifications/internal/common/NotificationGenerationJob;", "notificationBuilder", "createGenericPendingIntentsForNotif", "Landroid/app/Notification;", "intentGenerator", "Lcom/onesignal/notifications/internal/display/impl/IntentGeneratorForAttachingToNotifications;", "gcmBundle", "notificationId", "", "displayNotification", "", "(Lcom/onesignal/notifications/internal/common/NotificationGenerationJob;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBitmap", "Landroid/graphics/Bitmap;", "name", "getBitmapFromAssetsOrResourceName", "bitmapStr", "getBitmapFromURL", InAppMessagePromptTypes.LOCATION_PROMPT_KEY, "getDrawableId", "getResourceIcon", "iconName", "safeGetColorFromHex", "colorKey", "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Integer;", "setTextColor", "customView", "Landroid/widget/RemoteViews;", "viewId", "colorPayloadKey", "colorDefaultResource", "showNotification", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationDisplayer implements INotificationDisplayer {
    private final IApplicationService _applicationService;
    private final INotificationDisplayBuilder _notificationDisplayBuilder;
    private final INotificationLimitManager _notificationLimitManager;
    private final ISummaryNotificationDisplayer _summaryNotificationDisplayer;

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.display.impl.NotificationDisplayer$showNotification$1, reason: invalid class name */
    /* JADX INFO: compiled from: NotificationDisplayer.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.display.impl.NotificationDisplayer", f = "NotificationDisplayer.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {118, 133, 140}, m = "showNotification", n = {"this", "notificationJob", "fcmJson", "group", "intentGenerator", "grouplessNotifs", "oneSignalNotificationBuilder", "notifBuilder", "notificationId", "this", "oneSignalNotificationBuilder", OneSignalDbContract.NotificationTable.TABLE_NAME, "notificationId", "this", "oneSignalNotificationBuilder", OneSignalDbContract.NotificationTable.TABLE_NAME, "notificationId"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationDisplayer.this.showNotification(null, (Continuation) this);
        }
    }

    public NotificationDisplayer(IApplicationService _applicationService, INotificationLimitManager _notificationLimitManager, ISummaryNotificationDisplayer _summaryNotificationDisplayer, INotificationDisplayBuilder _notificationDisplayBuilder) {
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_notificationLimitManager, "_notificationLimitManager");
        Intrinsics.checkNotNullParameter(_summaryNotificationDisplayer, "_summaryNotificationDisplayer");
        Intrinsics.checkNotNullParameter(_notificationDisplayBuilder, "_notificationDisplayBuilder");
        this._applicationService = _applicationService;
        this._notificationLimitManager = _notificationLimitManager;
        this._summaryNotificationDisplayer = _summaryNotificationDisplayer;
        this._notificationDisplayBuilder = _notificationDisplayBuilder;
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

    @Override // com.onesignal.notifications.internal.display.INotificationDisplayer
    public Object displayNotification(NotificationGenerationJob notificationJob, Continuation<? super Boolean> continuation) {
        isRunningOnMainThreadCheck();
        return showNotification(notificationJob, continuation);
    }

    public final Unit isRunningOnMainThreadCheck() {
        if (AndroidUtils.INSTANCE.isRunningOnMainThread()) {
            throw new MainThreadException("Process for showing a notification should never been done on Main Thread!");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object showNotification(com.onesignal.notifications.internal.common.NotificationGenerationJob r24, kotlin.coroutines.Continuation<? super java.lang.Boolean> r25) {
        /*
            Method dump skipped, instruction units count: 534
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.display.impl.NotificationDisplayer.showNotification(com.onesignal.notifications.internal.common.NotificationGenerationJob, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Notification createGenericPendingIntentsForNotif(NotificationCompat.Builder notifBuilder, IntentGeneratorForAttachingToNotifications intentGenerator, JSONObject gcmBundle, int notificationId) {
        Random random = new SecureRandom();
        int iNextInt = random.nextInt();
        Intent intentPutExtra = intentGenerator.getNewBaseIntent(notificationId).putExtra(NotificationConstants.BUNDLE_KEY_ONESIGNAL_DATA, gcmBundle.toString());
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "intentGenerator.getNewBa…TA, gcmBundle.toString())");
        PendingIntent contentIntent = intentGenerator.getNewActionPendingIntent(iNextInt, intentPutExtra);
        Intrinsics.checkNotNull(notifBuilder);
        notifBuilder.setContentIntent(contentIntent);
        PendingIntent deleteIntent = this._notificationDisplayBuilder.getNewDismissActionPendingIntent(random.nextInt(), this._notificationDisplayBuilder.getNewBaseDismissIntent(notificationId));
        notifBuilder.setDeleteIntent(deleteIntent);
        Notification notificationBuild = notifBuilder.build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "notifBuilder.build()");
        return notificationBuild;
    }

    private final void applyNotificationExtender(NotificationGenerationJob notificationJob, NotificationCompat.Builder notificationBuilder) {
        if (notificationJob.hasExtender()) {
            try {
                Field mNotificationField = NotificationCompat.Builder.class.getDeclaredField("mNotification");
                mNotificationField.setAccessible(true);
                Object obj = mNotificationField.get(notificationBuilder);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.app.Notification");
                Notification mNotification = (Notification) obj;
                notificationJob.setOrgFlags(Integer.valueOf(mNotification.flags));
                notificationJob.setOrgSound(mNotification.sound);
                Intrinsics.checkNotNull(notificationBuilder);
                com.onesignal.notifications.internal.Notification notification = notificationJob.getNotification();
                Intrinsics.checkNotNull(notification);
                NotificationCompat.Extender notificationExtender = notification.getNotificationExtender();
                Intrinsics.checkNotNull(notificationExtender);
                notificationBuilder.extend(notificationExtender);
                Object obj2 = mNotificationField.get(notificationBuilder);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type android.app.Notification");
                Notification mNotification2 = (Notification) obj2;
                Field mContentTextField = NotificationCompat.Builder.class.getDeclaredField("mContentText");
                mContentTextField.setAccessible(true);
                CharSequence mContentText = (CharSequence) mContentTextField.get(notificationBuilder);
                Field mContentTitleField = NotificationCompat.Builder.class.getDeclaredField("mContentTitle");
                mContentTitleField.setAccessible(true);
                CharSequence mContentTitle = (CharSequence) mContentTitleField.get(notificationBuilder);
                notificationJob.setOverriddenBodyFromExtender(mContentText);
                notificationJob.setOverriddenTitleFromExtender(mContentTitle);
                if (!notificationJob.isRestoring()) {
                    notificationJob.setOverriddenFlags(Integer.valueOf(mNotification2.flags));
                    notificationJob.setOverriddenSound(mNotification2.sound);
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private final void addBackgroundImage(JSONObject fcmJson, NotificationCompat.Builder notifBuilder) throws Throwable {
        JSONObject jsonBgImage;
        if (Build.VERSION.SDK_INT >= 31) {
            Logging.verbose$default("Cannot use background images in notifications for device on version: " + Build.VERSION.SDK_INT, null, 2, null);
            return;
        }
        Bitmap bgImage = null;
        String jsonStrBgImage = fcmJson.optString("bg_img", null);
        if (jsonStrBgImage == null) {
            jsonBgImage = null;
        } else {
            JSONObject jsonBgImage2 = new JSONObject(jsonStrBgImage);
            bgImage = getBitmap(jsonBgImage2.optString("img", null));
            jsonBgImage = jsonBgImage2;
        }
        if (bgImage == null) {
            bgImage = getBitmapFromAssetsOrResourceName("onesignal_bgimage_default_image");
        }
        if (bgImage != null) {
            Context currentContext = getCurrentContext();
            Intrinsics.checkNotNull(currentContext);
            RemoteViews customView = new RemoteViews(currentContext.getPackageName(), R.layout.onesignal_bgimage_notif_layout);
            customView.setTextViewText(R.id.os_bgimage_notif_title, this._notificationDisplayBuilder.getTitle(fcmJson));
            customView.setTextViewText(R.id.os_bgimage_notif_body, fcmJson.optString("alert"));
            setTextColor(customView, jsonBgImage, R.id.os_bgimage_notif_title, "tc", "onesignal_bgimage_notif_title_color");
            setTextColor(customView, jsonBgImage, R.id.os_bgimage_notif_body, "bc", "onesignal_bgimage_notif_body_color");
            String alignSetting = null;
            if (jsonBgImage != null && jsonBgImage.has("img_align")) {
                alignSetting = jsonBgImage.getString("img_align");
            } else {
                Resources contextResources = getContextResources();
                Intrinsics.checkNotNull(contextResources);
                int iAlignSetting = contextResources.getIdentifier("onesignal_bgimage_notif_image_align", "string", getPackageName());
                if (iAlignSetting != 0) {
                    Resources contextResources2 = getContextResources();
                    Intrinsics.checkNotNull(contextResources2);
                    alignSetting = contextResources2.getString(iAlignSetting);
                }
            }
            if (Intrinsics.areEqual("right", alignSetting)) {
                customView.setViewPadding(R.id.os_bgimage_notif_bgimage_align_layout, -5000, 0, 0, 0);
                customView.setImageViewBitmap(R.id.os_bgimage_notif_bgimage_right_aligned, bgImage);
                customView.setViewVisibility(R.id.os_bgimage_notif_bgimage_right_aligned, 0);
                customView.setViewVisibility(R.id.os_bgimage_notif_bgimage, 8);
            } else {
                customView.setImageViewBitmap(R.id.os_bgimage_notif_bgimage, bgImage);
            }
            Intrinsics.checkNotNull(notifBuilder);
            notifBuilder.setContent(customView);
            notifBuilder.setStyle((NotificationCompat.Style) null);
        }
    }

    private final void setTextColor(RemoteViews customView, JSONObject fcmJson, int viewId, String colorPayloadKey, String colorDefaultResource) {
        Integer color = safeGetColorFromHex(fcmJson, colorPayloadKey);
        if (color != null) {
            customView.setTextColor(viewId, color.intValue());
            return;
        }
        Resources contextResources = getContextResources();
        Intrinsics.checkNotNull(contextResources);
        int colorId = contextResources.getIdentifier(colorDefaultResource, "color", getPackageName());
        if (colorId != 0) {
            customView.setTextColor(viewId, ContextCompat.getColor(getCurrentContext(), colorId));
        }
    }

    private final Integer safeGetColorFromHex(JSONObject fcmJson, String colorKey) {
        if (fcmJson != null) {
            try {
                if (fcmJson.has(colorKey)) {
                    return Integer.valueOf(new BigInteger(fcmJson.optString(colorKey), 16).intValue());
                }
                return null;
            } catch (Throwable th) {
                return null;
            }
        }
        return null;
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

    private final int getDrawableId(String name) {
        Resources contextResources = getContextResources();
        Intrinsics.checkNotNull(contextResources);
        return contextResources.getIdentifier(name, "drawable", getPackageName());
    }
}
