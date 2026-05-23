package com.onesignal.core.internal.backend;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;

/* JADX INFO: compiled from: IParamsBackendService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b.\u0018\u00002\u00020\u0001B±\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\r\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u001f\u0010\u0019\"\u0004\b \u0010\u001bR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b%\u0010\u0019\"\u0004\b&\u0010\u001bR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b/\u0010\u0019\"\u0004\b0\u0010\u001bR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001e\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b:\u0010\u0019\"\u0004\b;\u0010\u001bR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b<\u0010\u0019\"\u0004\b=\u0010\u001bR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b>\u0010\u0019\"\u0004\b?\u0010\u001bR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b@\u0010\u0019\"\u0004\bA\u0010\u001bR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\bB\u0010\u0019\"\u0004\bC\u0010\u001b¨\u0006D"}, d2 = {"Lcom/onesignal/core/internal/backend/ParamsObject;", "", "googleProjectNumber", "", "enterprise", "", "useIdentityVerification", "notificationChannels", "Lorg/json/JSONArray;", "firebaseAnalytics", "restoreTTLFilter", "clearGroupOnSummaryClick", "receiveReceiptEnabled", "disableGMSMissingPrompt", "unsubscribeWhenNotificationsDisabled", "locationShared", "requiresUserPrivacyConsent", "opRepoExecutionInterval", "", "influenceParams", "Lcom/onesignal/core/internal/backend/InfluenceParamsObject;", "fcmParams", "Lcom/onesignal/core/internal/backend/FCMParamsObject;", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lorg/json/JSONArray;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Long;Lcom/onesignal/core/internal/backend/InfluenceParamsObject;Lcom/onesignal/core/internal/backend/FCMParamsObject;)V", "getClearGroupOnSummaryClick", "()Ljava/lang/Boolean;", "setClearGroupOnSummaryClick", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getDisableGMSMissingPrompt", "setDisableGMSMissingPrompt", "getEnterprise", "setEnterprise", "getFcmParams", "()Lcom/onesignal/core/internal/backend/FCMParamsObject;", "setFcmParams", "(Lcom/onesignal/core/internal/backend/FCMParamsObject;)V", "getFirebaseAnalytics", "setFirebaseAnalytics", "getGoogleProjectNumber", "()Ljava/lang/String;", "setGoogleProjectNumber", "(Ljava/lang/String;)V", "getInfluenceParams", "()Lcom/onesignal/core/internal/backend/InfluenceParamsObject;", "setInfluenceParams", "(Lcom/onesignal/core/internal/backend/InfluenceParamsObject;)V", "getLocationShared", "setLocationShared", "getNotificationChannels", "()Lorg/json/JSONArray;", "setNotificationChannels", "(Lorg/json/JSONArray;)V", "getOpRepoExecutionInterval", "()Ljava/lang/Long;", "setOpRepoExecutionInterval", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getReceiveReceiptEnabled", "setReceiveReceiptEnabled", "getRequiresUserPrivacyConsent", "setRequiresUserPrivacyConsent", "getRestoreTTLFilter", "setRestoreTTLFilter", "getUnsubscribeWhenNotificationsDisabled", "setUnsubscribeWhenNotificationsDisabled", "getUseIdentityVerification", "setUseIdentityVerification", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ParamsObject {
    private Boolean clearGroupOnSummaryClick;
    private Boolean disableGMSMissingPrompt;
    private Boolean enterprise;
    private FCMParamsObject fcmParams;
    private Boolean firebaseAnalytics;
    private String googleProjectNumber;
    private InfluenceParamsObject influenceParams;
    private Boolean locationShared;
    private JSONArray notificationChannels;
    private Long opRepoExecutionInterval;
    private Boolean receiveReceiptEnabled;
    private Boolean requiresUserPrivacyConsent;
    private Boolean restoreTTLFilter;
    private Boolean unsubscribeWhenNotificationsDisabled;
    private Boolean useIdentityVerification;

    public ParamsObject(String googleProjectNumber, Boolean enterprise, Boolean useIdentityVerification, JSONArray notificationChannels, Boolean firebaseAnalytics, Boolean restoreTTLFilter, Boolean clearGroupOnSummaryClick, Boolean receiveReceiptEnabled, Boolean disableGMSMissingPrompt, Boolean unsubscribeWhenNotificationsDisabled, Boolean locationShared, Boolean requiresUserPrivacyConsent, Long opRepoExecutionInterval, InfluenceParamsObject influenceParams, FCMParamsObject fcmParams) {
        Intrinsics.checkNotNullParameter(influenceParams, "influenceParams");
        Intrinsics.checkNotNullParameter(fcmParams, "fcmParams");
        this.googleProjectNumber = googleProjectNumber;
        this.enterprise = enterprise;
        this.useIdentityVerification = useIdentityVerification;
        this.notificationChannels = notificationChannels;
        this.firebaseAnalytics = firebaseAnalytics;
        this.restoreTTLFilter = restoreTTLFilter;
        this.clearGroupOnSummaryClick = clearGroupOnSummaryClick;
        this.receiveReceiptEnabled = receiveReceiptEnabled;
        this.disableGMSMissingPrompt = disableGMSMissingPrompt;
        this.unsubscribeWhenNotificationsDisabled = unsubscribeWhenNotificationsDisabled;
        this.locationShared = locationShared;
        this.requiresUserPrivacyConsent = requiresUserPrivacyConsent;
        this.opRepoExecutionInterval = opRepoExecutionInterval;
        this.influenceParams = influenceParams;
        this.fcmParams = fcmParams;
    }

    public /* synthetic */ ParamsObject(String str, Boolean bool, Boolean bool2, JSONArray jSONArray, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, Long l, InfluenceParamsObject influenceParamsObject, FCMParamsObject fCMParamsObject, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : bool, (i & 4) != 0 ? null : bool2, (i & 8) != 0 ? null : jSONArray, (i & 16) != 0 ? null : bool3, (i & 32) != 0 ? null : bool4, (i & 64) != 0 ? null : bool5, (i & 128) != 0 ? null : bool6, (i & 256) != 0 ? null : bool7, (i & 512) != 0 ? null : bool8, (i & 1024) != 0 ? null : bool9, (i & 2048) != 0 ? null : bool10, (i & 4096) != 0 ? null : l, influenceParamsObject, fCMParamsObject);
    }

    public final String getGoogleProjectNumber() {
        return this.googleProjectNumber;
    }

    public final void setGoogleProjectNumber(String str) {
        this.googleProjectNumber = str;
    }

    public final Boolean getEnterprise() {
        return this.enterprise;
    }

    public final void setEnterprise(Boolean bool) {
        this.enterprise = bool;
    }

    public final Boolean getUseIdentityVerification() {
        return this.useIdentityVerification;
    }

    public final void setUseIdentityVerification(Boolean bool) {
        this.useIdentityVerification = bool;
    }

    public final JSONArray getNotificationChannels() {
        return this.notificationChannels;
    }

    public final void setNotificationChannels(JSONArray jSONArray) {
        this.notificationChannels = jSONArray;
    }

    public final Boolean getFirebaseAnalytics() {
        return this.firebaseAnalytics;
    }

    public final void setFirebaseAnalytics(Boolean bool) {
        this.firebaseAnalytics = bool;
    }

    public final Boolean getRestoreTTLFilter() {
        return this.restoreTTLFilter;
    }

    public final void setRestoreTTLFilter(Boolean bool) {
        this.restoreTTLFilter = bool;
    }

    public final Boolean getClearGroupOnSummaryClick() {
        return this.clearGroupOnSummaryClick;
    }

    public final void setClearGroupOnSummaryClick(Boolean bool) {
        this.clearGroupOnSummaryClick = bool;
    }

    public final Boolean getReceiveReceiptEnabled() {
        return this.receiveReceiptEnabled;
    }

    public final void setReceiveReceiptEnabled(Boolean bool) {
        this.receiveReceiptEnabled = bool;
    }

    public final Boolean getDisableGMSMissingPrompt() {
        return this.disableGMSMissingPrompt;
    }

    public final void setDisableGMSMissingPrompt(Boolean bool) {
        this.disableGMSMissingPrompt = bool;
    }

    public final Boolean getUnsubscribeWhenNotificationsDisabled() {
        return this.unsubscribeWhenNotificationsDisabled;
    }

    public final void setUnsubscribeWhenNotificationsDisabled(Boolean bool) {
        this.unsubscribeWhenNotificationsDisabled = bool;
    }

    public final Boolean getLocationShared() {
        return this.locationShared;
    }

    public final void setLocationShared(Boolean bool) {
        this.locationShared = bool;
    }

    public final Boolean getRequiresUserPrivacyConsent() {
        return this.requiresUserPrivacyConsent;
    }

    public final void setRequiresUserPrivacyConsent(Boolean bool) {
        this.requiresUserPrivacyConsent = bool;
    }

    public final Long getOpRepoExecutionInterval() {
        return this.opRepoExecutionInterval;
    }

    public final void setOpRepoExecutionInterval(Long l) {
        this.opRepoExecutionInterval = l;
    }

    public final InfluenceParamsObject getInfluenceParams() {
        return this.influenceParams;
    }

    public final void setInfluenceParams(InfluenceParamsObject influenceParamsObject) {
        Intrinsics.checkNotNullParameter(influenceParamsObject, "<set-?>");
        this.influenceParams = influenceParamsObject;
    }

    public final FCMParamsObject getFcmParams() {
        return this.fcmParams;
    }

    public final void setFcmParams(FCMParamsObject fCMParamsObject) {
        Intrinsics.checkNotNullParameter(fCMParamsObject, "<set-?>");
        this.fcmParams = fCMParamsObject;
    }
}
