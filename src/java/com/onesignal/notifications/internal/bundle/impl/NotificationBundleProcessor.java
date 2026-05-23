package com.onesignal.notifications.internal.bundle.impl;

import android.content.Context;
import android.os.Bundle;
import com.onesignal.common.JSONUtils;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.internal.bundle.INotificationBundleProcessor;
import com.onesignal.notifications.internal.common.NotificationConstants;
import com.onesignal.notifications.internal.common.NotificationFormatHelper;
import com.onesignal.notifications.internal.generation.INotificationGenerationWorkManager;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NotificationBundleProcessor.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onesignal/notifications/internal/bundle/impl/NotificationBundleProcessor;", "Lcom/onesignal/notifications/internal/bundle/INotificationBundleProcessor;", "_workManager", "Lcom/onesignal/notifications/internal/generation/INotificationGenerationWorkManager;", "_time", "Lcom/onesignal/core/internal/time/ITime;", "(Lcom/onesignal/notifications/internal/generation/INotificationGenerationWorkManager;Lcom/onesignal/core/internal/time/ITime;)V", "maximizeButtonsFromBundle", "", "fcmBundle", "Landroid/os/Bundle;", "processBundleFromReceiver", "Lcom/onesignal/notifications/internal/bundle/INotificationBundleProcessor$ProcessedBundleResult;", "context", "Landroid/content/Context;", "bundle", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationBundleProcessor implements INotificationBundleProcessor {
    private static final String ANDROID_NOTIFICATION_ID = "android_notif_id";
    public static final Companion Companion = new Companion(null);
    public static final String DEFAULT_ACTION = "__DEFAULT__";
    public static final String PUSH_ADDITIONAL_DATA_KEY = "a";
    public static final String PUSH_MINIFIED_BUTTONS_LIST = "o";
    public static final String PUSH_MINIFIED_BUTTON_ICON = "p";
    public static final String PUSH_MINIFIED_BUTTON_ID = "i";
    public static final String PUSH_MINIFIED_BUTTON_TEXT = "n";
    private final ITime _time;
    private final INotificationGenerationWorkManager _workManager;

    public NotificationBundleProcessor(INotificationGenerationWorkManager _workManager, ITime _time) {
        Intrinsics.checkNotNullParameter(_workManager, "_workManager");
        Intrinsics.checkNotNullParameter(_time, "_time");
        this._workManager = _workManager;
        this._time = _time;
    }

    private final void maximizeButtonsFromBundle(Bundle fcmBundle) {
        JSONObject additionalDataJSON;
        String buttonId;
        if (fcmBundle.containsKey(PUSH_MINIFIED_BUTTONS_LIST)) {
            try {
                JSONObject customJSON = new JSONObject(fcmBundle.getString(NotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM));
                if (customJSON.has("a")) {
                    additionalDataJSON = customJSON.getJSONObject("a");
                    Intrinsics.checkNotNullExpressionValue(additionalDataJSON, "{\n                    cu…      )\n                }");
                } else {
                    additionalDataJSON = new JSONObject();
                }
                JSONArray buttons = new JSONArray(fcmBundle.getString(PUSH_MINIFIED_BUTTONS_LIST));
                fcmBundle.remove(PUSH_MINIFIED_BUTTONS_LIST);
                int length = buttons.length();
                for (int i = 0; i < length; i++) {
                    JSONObject button = buttons.getJSONObject(i);
                    String buttonText = button.getString(PUSH_MINIFIED_BUTTON_TEXT);
                    button.remove(PUSH_MINIFIED_BUTTON_TEXT);
                    if (button.has("i")) {
                        buttonId = button.getString("i");
                        button.remove("i");
                    } else {
                        buttonId = buttonText;
                    }
                    button.put(OutcomeConstants.OUTCOME_ID, buttonId);
                    button.put("text", buttonText);
                    if (button.has(PUSH_MINIFIED_BUTTON_ICON)) {
                        button.put("icon", button.getString(PUSH_MINIFIED_BUTTON_ICON));
                        button.remove(PUSH_MINIFIED_BUTTON_ICON);
                    }
                }
                additionalDataJSON.put("actionButtons", buttons);
                additionalDataJSON.put(NotificationConstants.GENERATE_NOTIFICATION_BUNDLE_KEY_ACTION_ID, DEFAULT_ACTION);
                if (!customJSON.has("a")) {
                    customJSON.put("a", additionalDataJSON);
                }
                fcmBundle.putString(NotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM, customJSON.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.onesignal.notifications.internal.bundle.INotificationBundleProcessor
    public INotificationBundleProcessor.ProcessedBundleResult processBundleFromReceiver(Context context, Bundle bundle) {
        int androidNotificationId;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        INotificationBundleProcessor.ProcessedBundleResult bundleResult = new INotificationBundleProcessor.ProcessedBundleResult();
        if (!NotificationFormatHelper.INSTANCE.isOneSignalBundle(bundle)) {
            return bundleResult;
        }
        bundleResult.setOneSignalPayload(true);
        maximizeButtonsFromBundle(bundle);
        JSONObject jsonPayload = JSONUtils.INSTANCE.bundleAsJSONObject(bundle);
        long timestamp = this._time.getCurrentTimeMillis() / 1000;
        boolean isRestoring = bundle.getBoolean("is_restoring", false);
        String string = bundle.getString("pri", "0");
        Intrinsics.checkNotNullExpressionValue(string, "bundle.getString(\"pri\", \"0\")");
        boolean isHighPriority = Integer.parseInt(string) > 9;
        String osNotificationId = NotificationFormatHelper.INSTANCE.getOSNotificationIdFromJson(jsonPayload);
        if (!bundle.containsKey(ANDROID_NOTIFICATION_ID)) {
            androidNotificationId = 0;
        } else {
            int androidNotificationId2 = bundle.getInt(ANDROID_NOTIFICATION_ID);
            androidNotificationId = androidNotificationId2;
        }
        INotificationGenerationWorkManager iNotificationGenerationWorkManager = this._workManager;
        Intrinsics.checkNotNull(osNotificationId);
        boolean processed = iNotificationGenerationWorkManager.beginEnqueueingWork(context, osNotificationId, androidNotificationId, jsonPayload, timestamp, isRestoring, isHighPriority);
        bundleResult.setWorkManagerProcessing(processed);
        return bundleResult;
    }

    /* JADX INFO: compiled from: NotificationBundleProcessor.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onesignal/notifications/internal/bundle/impl/NotificationBundleProcessor$Companion;", "", "()V", "ANDROID_NOTIFICATION_ID", "", "DEFAULT_ACTION", "PUSH_ADDITIONAL_DATA_KEY", "PUSH_MINIFIED_BUTTONS_LIST", "PUSH_MINIFIED_BUTTON_ICON", "PUSH_MINIFIED_BUTTON_ID", "PUSH_MINIFIED_BUTTON_TEXT", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
