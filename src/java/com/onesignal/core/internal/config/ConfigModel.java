package com.onesignal.core.internal.config;

import com.onesignal.common.modeling.Model;
import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: ConfigModel.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010v\u001a\u0004\u0018\u00010\u00012\u0006\u0010w\u001a\u00020\u00042\u0006\u0010x\u001a\u00020yH\u0014R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR$\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R(\u0010\u0019\u001a\u0004\u0018\u00010\u00132\b\u0010\u0003\u001a\u0004\u0018\u00010\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR(\u0010\u001e\u001a\u0004\u0018\u00010\u00132\b\u0010\u0003\u001a\u0004\u0018\u00010\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR$\u0010!\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\"\u0010\u0016\"\u0004\b#\u0010\u0018R$\u0010$\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b%\u0010\u0016\"\u0004\b&\u0010\u0018R\u0011\u0010'\u001a\u00020(8F¢\u0006\u0006\u001a\u0004\b)\u0010*R$\u0010+\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b,\u0010\u0010\"\u0004\b-\u0010\u0012R$\u0010.\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b/\u0010\u0016\"\u0004\b0\u0010\u0018R$\u00101\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b2\u0010\u0010\"\u0004\b3\u0010\u0012R(\u00104\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b5\u0010\u0007\"\u0004\b6\u0010\tR$\u00108\u001a\u0002072\u0006\u0010\u0003\u001a\u0002078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R$\u0010=\u001a\u0002072\u0006\u0010\u0003\u001a\u0002078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b>\u0010:\"\u0004\b?\u0010<R$\u0010@\u001a\u0002072\u0006\u0010\u0003\u001a\u0002078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bA\u0010:\"\u0004\bB\u0010<R\u0011\u0010C\u001a\u00020D8F¢\u0006\u0006\u001a\u0004\bE\u0010FR$\u0010G\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bG\u0010\u0016\"\u0004\bH\u0010\u0018R$\u0010I\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bJ\u0010\u0016\"\u0004\bK\u0010\u0018R(\u0010M\u001a\u0004\u0018\u00010L2\b\u0010\u0003\u001a\u0004\u0018\u00010L8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR$\u0010R\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bS\u0010\u0010\"\u0004\bT\u0010\u0012R$\u0010U\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bV\u0010\u0010\"\u0004\bW\u0010\u0012R$\u0010X\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bY\u0010\u0010\"\u0004\bZ\u0010\u0012R$\u0010[\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\\\u0010\u0010\"\u0004\b]\u0010\u0012R$\u0010^\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b_\u0010\u0010\"\u0004\b`\u0010\u0012R(\u0010a\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bb\u0010\u0007\"\u0004\bc\u0010\tR$\u0010d\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\be\u0010\u0016\"\u0004\bf\u0010\u0018R$\u0010g\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bh\u0010\u0016\"\u0004\bi\u0010\u0018R$\u0010j\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bk\u0010\u0010\"\u0004\bl\u0010\u0012R$\u0010m\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bn\u0010\u0016\"\u0004\bo\u0010\u0018R$\u0010p\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bq\u0010\u0016\"\u0004\br\u0010\u0018R$\u0010s\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bt\u0010\u0016\"\u0004\bu\u0010\u0018¨\u0006z"}, d2 = {"Lcom/onesignal/core/internal/config/ConfigModel;", "Lcom/onesignal/common/modeling/Model;", "()V", "value", "", "apiUrl", "getApiUrl", "()Ljava/lang/String;", "setApiUrl", "(Ljava/lang/String;)V", "appId", "getAppId", "setAppId", "", "backgroundFetchNotificationPermissionInterval", "getBackgroundFetchNotificationPermissionInterval", "()J", "setBackgroundFetchNotificationPermissionInterval", "(J)V", "", "clearGroupOnSummaryClick", "getClearGroupOnSummaryClick", "()Z", "setClearGroupOnSummaryClick", "(Z)V", "consentGiven", "getConsentGiven", "()Ljava/lang/Boolean;", "setConsentGiven", "(Ljava/lang/Boolean;)V", "consentRequired", "getConsentRequired", "setConsentRequired", "disableGMSMissingPrompt", "getDisableGMSMissingPrompt", "setDisableGMSMissingPrompt", "enterprise", "getEnterprise", "setEnterprise", "fcmParams", "Lcom/onesignal/core/internal/config/FCMConfigModel;", "getFcmParams", "()Lcom/onesignal/core/internal/config/FCMConfigModel;", "fetchIAMMinInterval", "getFetchIAMMinInterval", "setFetchIAMMinInterval", "firebaseAnalytics", "getFirebaseAnalytics", "setFirebaseAnalytics", "foregroundFetchNotificationPermissionInterval", "getForegroundFetchNotificationPermissionInterval", "setForegroundFetchNotificationPermissionInterval", "googleProjectNumber", "getGoogleProjectNumber", "setGoogleProjectNumber", "", "httpGetTimeout", "getHttpGetTimeout", "()I", "setHttpGetTimeout", "(I)V", "httpRetryAfterParseFailFallback", "getHttpRetryAfterParseFailFallback", "setHttpRetryAfterParseFailFallback", "httpTimeout", "getHttpTimeout", "setHttpTimeout", "influenceParams", "Lcom/onesignal/core/internal/config/InfluenceConfigModel;", "getInfluenceParams", "()Lcom/onesignal/core/internal/config/InfluenceConfigModel;", "isInitializedWithRemote", "setInitializedWithRemote", "locationShared", "getLocationShared", "setLocationShared", "Lorg/json/JSONArray;", "notificationChannels", "getNotificationChannels", "()Lorg/json/JSONArray;", "setNotificationChannels", "(Lorg/json/JSONArray;)V", "opRepoDefaultFailRetryBackoff", "getOpRepoDefaultFailRetryBackoff", "setOpRepoDefaultFailRetryBackoff", "opRepoExecutionInterval", "getOpRepoExecutionInterval", "setOpRepoExecutionInterval", "opRepoPostCreateDelay", "getOpRepoPostCreateDelay", "setOpRepoPostCreateDelay", "opRepoPostCreateRetryUpTo", "getOpRepoPostCreateRetryUpTo", "setOpRepoPostCreateRetryUpTo", "opRepoPostWakeDelay", "getOpRepoPostWakeDelay", "setOpRepoPostWakeDelay", "pushSubscriptionId", "getPushSubscriptionId", "setPushSubscriptionId", "receiveReceiptEnabled", "getReceiveReceiptEnabled", "setReceiveReceiptEnabled", "restoreTTLFilter", "getRestoreTTLFilter", "setRestoreTTLFilter", "sessionFocusTimeout", "getSessionFocusTimeout", "setSessionFocusTimeout", "unsubscribeWhenNotificationsDisabled", "getUnsubscribeWhenNotificationsDisabled", "setUnsubscribeWhenNotificationsDisabled", "useIdentityVerification", "getUseIdentityVerification", "setUseIdentityVerification", "userRejectedGMSUpdate", "getUserRejectedGMSUpdate", "setUserRejectedGMSUpdate", "createModelForProperty", "property", "jsonObject", "Lorg/json/JSONObject;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ConfigModel extends Model {
    public ConfigModel() {
        super(null, null, 3, null);
    }

    public final boolean isInitializedWithRemote() {
        return getBooleanProperty("isInitializedWithRemote", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel.isInitializedWithRemote.2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m19invoke() {
                return false;
            }
        });
    }

    public final void setInitializedWithRemote(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "isInitializedWithRemote", value, null, false, 12, null);
    }

    public final String getAppId() {
        return Model.getStringProperty$default(this, "appId", null, 2, null);
    }

    public final void setAppId(String value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        Model.setStringProperty$default(this, "appId", value, null, false, 12, null);
    }

    public final String getPushSubscriptionId() {
        return Model.getOptStringProperty$default(this, "pushSubscriptionId", null, 2, null);
    }

    public final void setPushSubscriptionId(String value) throws Throwable {
        Model.setOptStringProperty$default(this, "pushSubscriptionId", value, null, false, 12, null);
    }

    public final String getApiUrl() {
        return getStringProperty("apiUrl", new Function0<String>() { // from class: com.onesignal.core.internal.config.ConfigModel$apiUrl$2
            public final String invoke() {
                return "https://api.onesignal.com/";
            }
        });
    }

    public final void setApiUrl(String value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        Model.setStringProperty$default(this, "apiUrl", value, null, false, 12, null);
    }

    public final Boolean getConsentRequired() {
        return Model.getOptBooleanProperty$default(this, "consentRequired", null, 2, null);
    }

    public final void setConsentRequired(Boolean value) throws Throwable {
        Model.setOptBooleanProperty$default(this, "consentRequired", value, null, false, 12, null);
    }

    public final Boolean getConsentGiven() {
        return Model.getOptBooleanProperty$default(this, "consentGiven", null, 2, null);
    }

    public final void setConsentGiven(Boolean value) throws Throwable {
        Model.setOptBooleanProperty$default(this, "consentGiven", value, null, false, 12, null);
    }

    public final boolean getLocationShared() {
        return getBooleanProperty("locationShared", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel$locationShared$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m20invoke() {
                return false;
            }
        });
    }

    public final void setLocationShared(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "locationShared", value, null, false, 12, null);
    }

    public final boolean getDisableGMSMissingPrompt() {
        return getBooleanProperty("disableGMSMissingPrompt", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel$disableGMSMissingPrompt$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m11invoke() {
                return false;
            }
        });
    }

    public final void setDisableGMSMissingPrompt(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "disableGMSMissingPrompt", value, null, false, 12, null);
    }

    public final boolean getUserRejectedGMSUpdate() {
        return getBooleanProperty("userRejectedGMSUpdate", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel$userRejectedGMSUpdate$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m32invoke() {
                return false;
            }
        });
    }

    public final void setUserRejectedGMSUpdate(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "userRejectedGMSUpdate", value, null, false, 12, null);
    }

    public final boolean getUnsubscribeWhenNotificationsDisabled() {
        return getBooleanProperty("unsubscribeWhenNotificationsDisabled", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel$unsubscribeWhenNotificationsDisabled$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m30invoke() {
                return false;
            }
        });
    }

    public final void setUnsubscribeWhenNotificationsDisabled(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "unsubscribeWhenNotificationsDisabled", value, null, false, 12, null);
    }

    public final int getHttpTimeout() {
        return getIntProperty("httpTimeout", new Function0<Integer>() { // from class: com.onesignal.core.internal.config.ConfigModel$httpTimeout$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Integer m18invoke() {
                return 120000;
            }
        });
    }

    public final void setHttpTimeout(int value) throws Throwable {
        Model.setIntProperty$default(this, "httpTimeout", value, null, false, 12, null);
    }

    public final int getHttpGetTimeout() {
        return getIntProperty("httpGetTimeout", new Function0<Integer>() { // from class: com.onesignal.core.internal.config.ConfigModel$httpGetTimeout$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Integer m16invoke() {
                return 60000;
            }
        });
    }

    public final void setHttpGetTimeout(int value) throws Throwable {
        Model.setIntProperty$default(this, "httpGetTimeout", value, null, false, 12, null);
    }

    public final int getHttpRetryAfterParseFailFallback() {
        return getIntProperty("httpRetryAfterParseFailFallback", new Function0<Integer>() { // from class: com.onesignal.core.internal.config.ConfigModel$httpRetryAfterParseFailFallback$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Integer m17invoke() {
                return 60;
            }
        });
    }

    public final void setHttpRetryAfterParseFailFallback(int value) throws Throwable {
        Model.setIntProperty$default(this, "httpRetryAfterParseFailFallback", value, null, false, 12, null);
    }

    public final long getSessionFocusTimeout() {
        return getLongProperty("sessionFocusTimeout", new Function0<Long>() { // from class: com.onesignal.core.internal.config.ConfigModel$sessionFocusTimeout$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m29invoke() {
                return 30000L;
            }
        });
    }

    public final void setSessionFocusTimeout(long value) throws Throwable {
        Model.setLongProperty$default(this, "sessionFocusTimeout", value, null, false, 12, null);
    }

    public final long getOpRepoExecutionInterval() {
        return getLongProperty("opRepoExecutionInterval", new Function0<Long>() { // from class: com.onesignal.core.internal.config.ConfigModel$opRepoExecutionInterval$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m23invoke() {
                return 5000L;
            }
        });
    }

    public final void setOpRepoExecutionInterval(long value) throws Throwable {
        Model.setLongProperty$default(this, "opRepoExecutionInterval", value, null, false, 12, null);
    }

    public final long getOpRepoPostWakeDelay() {
        return getLongProperty("opRepoPostWakeDelay", new Function0<Long>() { // from class: com.onesignal.core.internal.config.ConfigModel$opRepoPostWakeDelay$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m26invoke() {
                return 200L;
            }
        });
    }

    public final void setOpRepoPostWakeDelay(long value) throws Throwable {
        Model.setLongProperty$default(this, "opRepoPostWakeDelay", value, null, false, 12, null);
    }

    public final long getOpRepoPostCreateDelay() {
        return getLongProperty("opRepoPostCreateDelay", new Function0<Long>() { // from class: com.onesignal.core.internal.config.ConfigModel$opRepoPostCreateDelay$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m24invoke() {
                return 5000L;
            }
        });
    }

    public final void setOpRepoPostCreateDelay(long value) throws Throwable {
        Model.setLongProperty$default(this, "opRepoPostCreateDelay", value, null, false, 12, null);
    }

    public final long getOpRepoPostCreateRetryUpTo() {
        return getLongProperty("opRepoPostCreateRetryUpTo", new Function0<Long>() { // from class: com.onesignal.core.internal.config.ConfigModel$opRepoPostCreateRetryUpTo$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m25invoke() {
                return 60000L;
            }
        });
    }

    public final void setOpRepoPostCreateRetryUpTo(long value) throws Throwable {
        Model.setLongProperty$default(this, "opRepoPostCreateRetryUpTo", value, null, false, 12, null);
    }

    public final long getOpRepoDefaultFailRetryBackoff() {
        return getLongProperty("opRepoDefaultFailRetryBackoff", new Function0<Long>() { // from class: com.onesignal.core.internal.config.ConfigModel$opRepoDefaultFailRetryBackoff$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m22invoke() {
                return 15000L;
            }
        });
    }

    public final void setOpRepoDefaultFailRetryBackoff(long value) throws Throwable {
        Model.setLongProperty$default(this, "opRepoDefaultFailRetryBackoff", value, null, false, 12, null);
    }

    public final long getFetchIAMMinInterval() {
        return getLongProperty("fetchIAMMinInterval", new Function0<Long>() { // from class: com.onesignal.core.internal.config.ConfigModel$fetchIAMMinInterval$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m13invoke() {
                return 30000L;
            }
        });
    }

    public final void setFetchIAMMinInterval(long value) throws Throwable {
        Model.setLongProperty$default(this, "fetchIAMMinInterval", value, null, false, 12, null);
    }

    public final long getForegroundFetchNotificationPermissionInterval() {
        return getLongProperty("foregroundFetchNotificationPermissionInterval", new Function0<Long>() { // from class: com.onesignal.core.internal.config.ConfigModel$foregroundFetchNotificationPermissionInterval$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m15invoke() {
                return 1000L;
            }
        });
    }

    public final void setForegroundFetchNotificationPermissionInterval(long value) throws Throwable {
        Model.setLongProperty$default(this, "foregroundFetchNotificationPermissionInterval", value, null, false, 12, null);
    }

    public final long getBackgroundFetchNotificationPermissionInterval() {
        return getLongProperty("backgroundFetchNotificationPermissionInterval", new Function0<Long>() { // from class: com.onesignal.core.internal.config.ConfigModel$backgroundFetchNotificationPermissionInterval$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m9invoke() {
                return 86400000L;
            }
        });
    }

    public final void setBackgroundFetchNotificationPermissionInterval(long value) throws Throwable {
        Model.setLongProperty$default(this, "backgroundFetchNotificationPermissionInterval", value, null, false, 12, null);
    }

    public final String getGoogleProjectNumber() {
        return Model.getOptStringProperty$default(this, "googleProjectNumber", null, 2, null);
    }

    public final void setGoogleProjectNumber(String value) throws Throwable {
        Model.setOptStringProperty$default(this, "googleProjectNumber", value, null, false, 12, null);
    }

    public final boolean getEnterprise() {
        return getBooleanProperty("enterprise", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel$enterprise$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m12invoke() {
                return false;
            }
        });
    }

    public final void setEnterprise(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "enterprise", value, null, false, 12, null);
    }

    public final boolean getUseIdentityVerification() {
        return getBooleanProperty("useIdentityVerification", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel$useIdentityVerification$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m31invoke() {
                return false;
            }
        });
    }

    public final void setUseIdentityVerification(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "useIdentityVerification", value, null, false, 12, null);
    }

    public final JSONArray getNotificationChannels() {
        String optStringProperty = getOptStringProperty("notificationChannels", new Function0<String>() { // from class: com.onesignal.core.internal.config.ConfigModel$notificationChannels$2
            public final String invoke() {
                return null;
            }
        });
        if (optStringProperty == null) {
            optStringProperty = "[]";
        }
        return new JSONArray(optStringProperty);
    }

    public final void setNotificationChannels(JSONArray value) throws Throwable {
        Model.setOptStringProperty$default(this, "notificationChannels", value != null ? value.toString() : null, null, false, 12, null);
    }

    public final boolean getFirebaseAnalytics() {
        return getBooleanProperty("firebaseAnalytics", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel$firebaseAnalytics$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m14invoke() {
                return false;
            }
        });
    }

    public final void setFirebaseAnalytics(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "firebaseAnalytics", value, null, false, 12, null);
    }

    public final boolean getRestoreTTLFilter() {
        return getBooleanProperty("restoreTTLFilter", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel$restoreTTLFilter$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m28invoke() {
                return true;
            }
        });
    }

    public final void setRestoreTTLFilter(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "restoreTTLFilter", value, null, false, 12, null);
    }

    public final boolean getReceiveReceiptEnabled() {
        return getBooleanProperty("receiveReceiptEnabled", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel$receiveReceiptEnabled$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m27invoke() {
                return false;
            }
        });
    }

    public final void setReceiveReceiptEnabled(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "receiveReceiptEnabled", value, null, false, 12, null);
    }

    public final boolean getClearGroupOnSummaryClick() {
        return getBooleanProperty("clearGroupOnSummaryClick", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.ConfigModel$clearGroupOnSummaryClick$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m10invoke() {
                return true;
            }
        });
    }

    public final void setClearGroupOnSummaryClick(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "clearGroupOnSummaryClick", value, null, false, 12, null);
    }

    public final InfluenceConfigModel getInfluenceParams() {
        Object anyProperty = getAnyProperty("influenceParams", new Function0<Object>() { // from class: com.onesignal.core.internal.config.ConfigModel$influenceParams$2
            {
                super(0);
            }

            public final Object invoke() {
                return new InfluenceConfigModel(this.this$0, "influenceParams");
            }
        });
        Intrinsics.checkNotNull(anyProperty, "null cannot be cast to non-null type com.onesignal.core.internal.config.InfluenceConfigModel");
        return (InfluenceConfigModel) anyProperty;
    }

    public final FCMConfigModel getFcmParams() {
        Object anyProperty = getAnyProperty("fcmParams", new Function0<Object>() { // from class: com.onesignal.core.internal.config.ConfigModel$fcmParams$2
            {
                super(0);
            }

            public final Object invoke() {
                return new FCMConfigModel(this.this$0, "fcmParams");
            }
        });
        Intrinsics.checkNotNull(anyProperty, "null cannot be cast to non-null type com.onesignal.core.internal.config.FCMConfigModel");
        return (FCMConfigModel) anyProperty;
    }

    @Override // com.onesignal.common.modeling.Model
    protected Model createModelForProperty(String property, JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(property, "property");
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        if (Intrinsics.areEqual(property, "influenceParams")) {
            InfluenceConfigModel model = new InfluenceConfigModel(this, "influenceParams");
            model.initializeFromJson(jsonObject);
            return model;
        }
        if (Intrinsics.areEqual(property, "fcmParams")) {
            FCMConfigModel model2 = new FCMConfigModel(this, "influenceParams");
            model2.initializeFromJson(jsonObject);
            return model2;
        }
        return null;
    }
}
