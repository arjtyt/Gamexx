package com.onesignal;

import android.content.Context;
import com.onesignal.common.services.IServiceProvider;
import com.onesignal.debug.IDebugManager;
import com.onesignal.inAppMessages.IInAppMessagesManager;
import com.onesignal.internal.OneSignalImp;
import com.onesignal.location.ILocationManager;
import com.onesignal.notifications.INotificationsManager;
import com.onesignal.session.ISessionManager;
import com.onesignal.user.IUserManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OneSignal.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010B\u001a\u0002HC\"\n\b\u0000\u0010C\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0002\u0010DJ\u001c\u0010E\u001a\u0004\u0018\u0001HC\"\n\b\u0000\u0010C\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0002\u0010DJ\u0010\u0010F\u001a\u00020\"2\u0006\u0010G\u001a\u00020HH\u0007J\u0018\u0010F\u001a\u00020I2\u0006\u0010G\u001a\u00020H2\u0006\u0010J\u001a\u00020:H\u0007J\u0010\u0010K\u001a\u00020I2\u0006\u0010L\u001a\u00020:H\u0007J\u001c\u0010K\u001a\u00020I2\u0006\u0010L\u001a\u00020:2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010:H\u0007J\b\u0010N\u001a\u00020IH\u0007R\u001a\u0010\u0003\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\t8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000e8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u00138FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u0002\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0002\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001d8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\u0002\u001a\u0004\b\u001f\u0010 R*\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b$\u0010\u0002\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R*\u0010)\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b*\u0010\u0002\u001a\u0004\b+\u0010&\"\u0004\b,\u0010(R*\u0010-\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b.\u0010\u0002\u001a\u0004\b/\u0010&\"\u0004\b0\u0010(R\u001a\u00101\u001a\u00020\"8FX\u0087\u0004¢\u0006\f\u0012\u0004\b2\u0010\u0002\u001a\u0004\b1\u0010&R\u001b\u00103\u001a\u0002048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b5\u00106R\u001a\u00109\u001a\u00020:8FX\u0087\u0004¢\u0006\f\u0012\u0004\b;\u0010\u0002\u001a\u0004\b<\u0010=R\u0011\u0010>\u001a\u00020?8F¢\u0006\u0006\u001a\u0004\b@\u0010A¨\u0006O"}, d2 = {"Lcom/onesignal/OneSignal;", "", "()V", "Debug", "Lcom/onesignal/debug/IDebugManager;", "getDebug$annotations", "getDebug", "()Lcom/onesignal/debug/IDebugManager;", "InAppMessages", "Lcom/onesignal/inAppMessages/IInAppMessagesManager;", "getInAppMessages$annotations", "getInAppMessages", "()Lcom/onesignal/inAppMessages/IInAppMessagesManager;", "Location", "Lcom/onesignal/location/ILocationManager;", "getLocation$annotations", "getLocation", "()Lcom/onesignal/location/ILocationManager;", "Notifications", "Lcom/onesignal/notifications/INotificationsManager;", "getNotifications$annotations", "getNotifications", "()Lcom/onesignal/notifications/INotificationsManager;", "Session", "Lcom/onesignal/session/ISessionManager;", "getSession$annotations", "getSession", "()Lcom/onesignal/session/ISessionManager;", "User", "Lcom/onesignal/user/IUserManager;", "getUser$annotations", "getUser", "()Lcom/onesignal/user/IUserManager;", "value", "", "consentGiven", "getConsentGiven$annotations", "getConsentGiven", "()Z", "setConsentGiven", "(Z)V", "consentRequired", "getConsentRequired$annotations", "getConsentRequired", "setConsentRequired", "disableGMSMissingPrompt", "getDisableGMSMissingPrompt$annotations", "getDisableGMSMissingPrompt", "setDisableGMSMissingPrompt", "isInitialized", "isInitialized$annotations", "oneSignal", "Lcom/onesignal/IOneSignal;", "getOneSignal", "()Lcom/onesignal/IOneSignal;", "oneSignal$delegate", "Lkotlin/Lazy;", "sdkVersion", "", "getSdkVersion$annotations", "getSdkVersion", "()Ljava/lang/String;", "services", "Lcom/onesignal/common/services/IServiceProvider;", "getServices", "()Lcom/onesignal/common/services/IServiceProvider;", "getService", "T", "()Ljava/lang/Object;", "getServiceOrNull", "initWithContext", "context", "Landroid/content/Context;", "", "appId", "login", "externalId", "jwtBearerToken", "logout", com.onesignal.core.BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OneSignal {
    public static final OneSignal INSTANCE = new OneSignal();
    private static final Lazy oneSignal$delegate = LazyKt.lazy(new Function0<OneSignalImp>() { // from class: com.onesignal.OneSignal$oneSignal$2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final OneSignalImp m0invoke() {
            return new OneSignalImp();
        }
    });

    @JvmStatic
    public static /* synthetic */ void getConsentGiven$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getConsentRequired$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getDebug$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getDisableGMSMissingPrompt$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getInAppMessages$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getLocation$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getNotifications$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getSdkVersion$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getSession$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getUser$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void isInitialized$annotations() {
    }

    private OneSignal() {
    }

    public static final boolean isInitialized() {
        return INSTANCE.getOneSignal().isInitialized();
    }

    public static final String getSdkVersion() {
        return INSTANCE.getOneSignal().getSdkVersion();
    }

    public static final IUserManager getUser() {
        return INSTANCE.getOneSignal().getUser();
    }

    public static final ISessionManager getSession() {
        return INSTANCE.getOneSignal().getSession();
    }

    public static final INotificationsManager getNotifications() {
        return INSTANCE.getOneSignal().getNotifications();
    }

    public static final ILocationManager getLocation() {
        return INSTANCE.getOneSignal().getLocation();
    }

    public static final IInAppMessagesManager getInAppMessages() {
        return INSTANCE.getOneSignal().getInAppMessages();
    }

    public static final IDebugManager getDebug() {
        return INSTANCE.getOneSignal().getDebug();
    }

    public static final boolean getConsentRequired() {
        return INSTANCE.getOneSignal().getConsentRequired();
    }

    public static final void setConsentRequired(boolean value) {
        INSTANCE.getOneSignal().setConsentRequired(value);
    }

    public static final boolean getConsentGiven() {
        return INSTANCE.getOneSignal().getConsentGiven();
    }

    public static final void setConsentGiven(boolean value) {
        INSTANCE.getOneSignal().setConsentGiven(value);
    }

    public static final boolean getDisableGMSMissingPrompt() {
        return INSTANCE.getOneSignal().getDisableGMSMissingPrompt();
    }

    public static final void setDisableGMSMissingPrompt(boolean value) {
        INSTANCE.getOneSignal().setDisableGMSMissingPrompt(value);
    }

    @JvmStatic
    public static final void initWithContext(Context context, String appId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appId, "appId");
        INSTANCE.getOneSignal().initWithContext(context, appId);
    }

    @JvmStatic
    public static final void login(String externalId) {
        Intrinsics.checkNotNullParameter(externalId, "externalId");
        INSTANCE.getOneSignal().login(externalId);
    }

    public static /* synthetic */ void login$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        login(str, str2);
    }

    @JvmStatic
    public static final void login(String externalId, String jwtBearerToken) {
        Intrinsics.checkNotNullParameter(externalId, "externalId");
        INSTANCE.getOneSignal().login(externalId, jwtBearerToken);
    }

    @JvmStatic
    public static final void logout() {
        INSTANCE.getOneSignal().logout();
    }

    private final IOneSignal getOneSignal() {
        return (IOneSignal) oneSignal$delegate.getValue();
    }

    @JvmStatic
    public static final boolean initWithContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.getOneSignal().initWithContext(context, null);
    }

    public final IServiceProvider getServices() {
        IOneSignal oneSignal = getOneSignal();
        Intrinsics.checkNotNull(oneSignal, "null cannot be cast to non-null type com.onesignal.common.services.IServiceProvider");
        return (IServiceProvider) oneSignal;
    }

    public final /* synthetic */ <T> T getService() {
        IServiceProvider services = getServices();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) services.getService(Object.class);
    }

    public final /* synthetic */ <T> T getServiceOrNull() {
        IServiceProvider services = getServices();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) services.getServiceOrNull(Object.class);
    }
}
