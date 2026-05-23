package com.onesignal.internal;

import android.os.Build;
import com.onesignal.IOneSignal;
import com.onesignal.common.AndroidUtils;
import com.onesignal.common.DeviceUtils;
import com.onesignal.common.IDManager;
import com.onesignal.common.OneSignalUtils;
import com.onesignal.common.modeling.IModelStore;
import com.onesignal.common.modeling.ISingletonModelStore;
import com.onesignal.common.modeling.ModelChangeTags;
import com.onesignal.common.modules.IModule;
import com.onesignal.common.services.IServiceProvider;
import com.onesignal.common.services.ServiceBuilder;
import com.onesignal.common.services.ServiceProvider;
import com.onesignal.common.threading.ThreadUtilsKt;
import com.onesignal.core.CoreModule;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.config.ConfigModel;
import com.onesignal.core.internal.operations.IOperationRepo;
import com.onesignal.core.internal.preferences.IPreferencesService;
import com.onesignal.core.internal.preferences.PreferenceOneSignalKeys;
import com.onesignal.core.internal.preferences.PreferenceStores;
import com.onesignal.debug.IDebugManager;
import com.onesignal.debug.LogLevel;
import com.onesignal.debug.internal.DebugManager;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.inAppMessages.IInAppMessagesManager;
import com.onesignal.inAppMessages.internal.prompt.InAppMessagePromptTypes;
import com.onesignal.location.ILocationManager;
import com.onesignal.notifications.INotificationsManager;
import com.onesignal.session.ISessionManager;
import com.onesignal.session.SessionModule;
import com.onesignal.session.internal.outcomes.impl.OutcomeEventsTable;
import com.onesignal.session.internal.session.SessionModel;
import com.onesignal.user.IUserManager;
import com.onesignal.user.UserModule;
import com.onesignal.user.internal.identity.IdentityModel;
import com.onesignal.user.internal.identity.IdentityModelStore;
import com.onesignal.user.internal.operations.LoginUserOperation;
import com.onesignal.user.internal.operations.TransferSubscriptionOperation;
import com.onesignal.user.internal.properties.PropertiesModel;
import com.onesignal.user.internal.properties.PropertiesModelStore;
import com.onesignal.user.internal.subscriptions.SubscriptionModel;
import com.onesignal.user.internal.subscriptions.SubscriptionModelStore;
import com.onesignal.user.internal.subscriptions.SubscriptionStatus;
import com.onesignal.user.internal.subscriptions.SubscriptionType;
import com.primexop.webview.BuildConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: OneSignalImp.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003JN\u0010P\u001a\u00020Q2\b\b\u0002\u0010R\u001a\u00020\u00052:\b\u0002\u0010S\u001a4\u0012\u0013\u0012\u00110U¢\u0006\f\bV\u0012\b\bW\u0012\u0004\b\b(X\u0012\u0013\u0012\u00110Y¢\u0006\f\bV\u0012\b\bW\u0012\u0004\b\b(Z\u0012\u0004\u0012\u00020Q\u0018\u00010TH\u0002J\"\u0010[\u001a\b\u0012\u0004\u0012\u0002H\\0(\"\u0004\b\u0000\u0010\\2\f\u0010]\u001a\b\u0012\u0004\u0012\u0002H\\0^H\u0016J\n\u0010_\u001a\u0004\u0018\u00010)H\u0002J!\u0010`\u001a\u0002H\\\"\u0004\b\u0000\u0010\\2\f\u0010]\u001a\b\u0012\u0004\u0012\u0002H\\0^H\u0016¢\u0006\u0002\u0010aJ#\u0010b\u001a\u0004\u0018\u0001H\\\"\u0004\b\u0000\u0010\\2\f\u0010]\u001a\b\u0012\u0004\u0012\u0002H\\0^H\u0016¢\u0006\u0002\u0010aJ\u001c\u0010c\u001a\u00020\u0005\"\u0004\b\u0000\u0010\\2\f\u0010]\u001a\b\u0012\u0004\u0012\u0002H\\0^H\u0016J\u001a\u0010d\u001a\u00020\u00052\u0006\u0010e\u001a\u00020f2\b\u0010g\u001a\u0004\u0018\u00010)H\u0016J\u001a\u0010h\u001a\u00020Q2\u0006\u0010i\u001a\u00020)2\b\u0010j\u001a\u0004\u0018\u00010)H\u0016J\b\u0010k\u001a\u00020QH\u0016R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u0012\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00058V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00058V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00058V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u0014\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000e\"\u0004\b&\u0010\u0010R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\u00020+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\u0002008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\u0002068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u0014\u00109\u001a\u00020:8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<R\u0014\u0010=\u001a\u00020)X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u000e\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010B\u001a\u00020C8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0010\u0010F\u001a\u0004\u0018\u00010GX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010H\u001a\u00020I8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020M8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u0010O¨\u0006l"}, d2 = {"Lcom/onesignal/internal/OneSignalImp;", "Lcom/onesignal/IOneSignal;", "Lcom/onesignal/common/services/IServiceProvider;", "()V", "_consentGiven", "", "Ljava/lang/Boolean;", "_consentRequired", "_disableGMSMissingPrompt", "configModel", "Lcom/onesignal/core/internal/config/ConfigModel;", "value", "consentGiven", "getConsentGiven", "()Z", "setConsentGiven", "(Z)V", "consentRequired", "getConsentRequired", "setConsentRequired", BuildConfig.BUILD_TYPE, "Lcom/onesignal/debug/IDebugManager;", "getDebug", "()Lcom/onesignal/debug/IDebugManager;", "disableGMSMissingPrompt", "getDisableGMSMissingPrompt", "setDisableGMSMissingPrompt", "identityModelStore", "Lcom/onesignal/user/internal/identity/IdentityModelStore;", "getIdentityModelStore", "()Lcom/onesignal/user/internal/identity/IdentityModelStore;", "inAppMessages", "Lcom/onesignal/inAppMessages/IInAppMessagesManager;", "getInAppMessages", "()Lcom/onesignal/inAppMessages/IInAppMessagesManager;", "initLock", "", "isInitialized", "setInitialized", "listOfModules", "", "", InAppMessagePromptTypes.LOCATION_PROMPT_KEY, "Lcom/onesignal/location/ILocationManager;", "getLocation", "()Lcom/onesignal/location/ILocationManager;", "loginLock", "notifications", "Lcom/onesignal/notifications/INotificationsManager;", "getNotifications", "()Lcom/onesignal/notifications/INotificationsManager;", "operationRepo", "Lcom/onesignal/core/internal/operations/IOperationRepo;", "preferencesService", "Lcom/onesignal/core/internal/preferences/IPreferencesService;", "getPreferencesService", "()Lcom/onesignal/core/internal/preferences/IPreferencesService;", "propertiesModelStore", "Lcom/onesignal/user/internal/properties/PropertiesModelStore;", "getPropertiesModelStore", "()Lcom/onesignal/user/internal/properties/PropertiesModelStore;", "sdkVersion", "getSdkVersion", "()Ljava/lang/String;", "services", "Lcom/onesignal/common/services/ServiceProvider;", OutcomeEventsTable.COLUMN_NAME_SESSION, "Lcom/onesignal/session/ISessionManager;", "getSession", "()Lcom/onesignal/session/ISessionManager;", "sessionModel", "Lcom/onesignal/session/internal/session/SessionModel;", "subscriptionModelStore", "Lcom/onesignal/user/internal/subscriptions/SubscriptionModelStore;", "getSubscriptionModelStore", "()Lcom/onesignal/user/internal/subscriptions/SubscriptionModelStore;", "user", "Lcom/onesignal/user/IUserManager;", "getUser", "()Lcom/onesignal/user/IUserManager;", "createAndSwitchToNewUser", "", "suppressBackendOperation", "modify", "Lkotlin/Function2;", "Lcom/onesignal/user/internal/identity/IdentityModel;", "Lkotlin/ParameterName;", "name", "identityModel", "Lcom/onesignal/user/internal/properties/PropertiesModel;", "propertiesModel", "getAllServices", "T", "c", "Ljava/lang/Class;", "getLegacyAppId", "getService", "(Ljava/lang/Class;)Ljava/lang/Object;", "getServiceOrNull", "hasService", "initWithContext", "context", "Landroid/content/Context;", "appId", "login", "externalId", "jwtBearerToken", "logout", com.onesignal.core.BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OneSignalImp implements IOneSignal, IServiceProvider {
    private Boolean _consentGiven;
    private Boolean _consentRequired;
    private Boolean _disableGMSMissingPrompt;
    private ConfigModel configModel;
    private boolean isInitialized;
    private IOperationRepo operationRepo;
    private final ServiceProvider services;
    private SessionModel sessionModel;
    private final String sdkVersion = OneSignalUtils.SDK_VERSION;
    private final IDebugManager debug = new DebugManager();
    private final Object initLock = new Object();
    private final Object loginLock = new Object();
    private final List<String> listOfModules = CollectionsKt.listOf(new String[]{"com.onesignal.notifications.NotificationsModule", "com.onesignal.inAppMessages.InAppMessagesModule", "com.onesignal.location.LocationModule"});

    public OneSignalImp() throws IllegalAccessException, InstantiationException {
        ServiceBuilder serviceBuilder = new ServiceBuilder();
        List<IModule> modules = new ArrayList();
        modules.add(new CoreModule());
        modules.add(new SessionModule());
        modules.add(new UserModule());
        for (String moduleClassName : this.listOfModules) {
            try {
                Object objNewInstance = Class.forName(moduleClassName).newInstance();
                Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type com.onesignal.common.modules.IModule");
                IModule moduleInstance = (IModule) objNewInstance;
                modules.add(moduleInstance);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        for (IModule module : modules) {
            module.register(serviceBuilder);
        }
        this.services = serviceBuilder.build();
    }

    @Override // com.onesignal.IOneSignal
    public void login(String externalId) {
        IOneSignal.DefaultImpls.login(this, externalId);
    }

    @Override // com.onesignal.IOneSignal
    public String getSdkVersion() {
        return this.sdkVersion;
    }

    @Override // com.onesignal.IOneSignal
    public boolean isInitialized() {
        return this.isInitialized;
    }

    public void setInitialized(boolean z) {
        this.isInitialized = z;
    }

    @Override // com.onesignal.IOneSignal
    public boolean getConsentRequired() {
        Boolean consentRequired;
        ConfigModel configModel = this.configModel;
        return (configModel == null || (consentRequired = configModel.getConsentRequired()) == null) ? Intrinsics.areEqual(this._consentRequired, true) : consentRequired.booleanValue();
    }

    @Override // com.onesignal.IOneSignal
    public void setConsentRequired(boolean value) throws Throwable {
        this._consentRequired = Boolean.valueOf(value);
        ConfigModel configModel = this.configModel;
        if (configModel == null) {
            return;
        }
        configModel.setConsentRequired(Boolean.valueOf(value));
    }

    @Override // com.onesignal.IOneSignal
    public boolean getConsentGiven() {
        Boolean consentGiven;
        ConfigModel configModel = this.configModel;
        return (configModel == null || (consentGiven = configModel.getConsentGiven()) == null) ? Intrinsics.areEqual(this._consentGiven, true) : consentGiven.booleanValue();
    }

    @Override // com.onesignal.IOneSignal
    public void setConsentGiven(boolean value) throws Throwable {
        IOperationRepo iOperationRepo;
        Boolean oldValue = this._consentGiven;
        this._consentGiven = Boolean.valueOf(value);
        ConfigModel configModel = this.configModel;
        if (configModel != null) {
            configModel.setConsentGiven(Boolean.valueOf(value));
        }
        if (Intrinsics.areEqual(oldValue, Boolean.valueOf(value)) || !value || (iOperationRepo = this.operationRepo) == null) {
            return;
        }
        iOperationRepo.forceExecuteOperations();
    }

    @Override // com.onesignal.IOneSignal
    public boolean getDisableGMSMissingPrompt() {
        ConfigModel configModel = this.configModel;
        return configModel != null ? configModel.getDisableGMSMissingPrompt() : Intrinsics.areEqual(this._disableGMSMissingPrompt, true);
    }

    @Override // com.onesignal.IOneSignal
    public void setDisableGMSMissingPrompt(boolean value) throws Throwable {
        this._disableGMSMissingPrompt = Boolean.valueOf(value);
        ConfigModel configModel = this.configModel;
        if (configModel == null) {
            return;
        }
        configModel.setDisableGMSMissingPrompt(value);
    }

    @Override // com.onesignal.IOneSignal
    public IDebugManager getDebug() {
        return this.debug;
    }

    @Override // com.onesignal.IOneSignal
    public ISessionManager getSession() throws Exception {
        if (isInitialized()) {
            ServiceProvider this_$iv = this.services;
            return (ISessionManager) this_$iv.getService(ISessionManager.class);
        }
        throw new Exception("Must call 'initWithContext' before use");
    }

    @Override // com.onesignal.IOneSignal
    public INotificationsManager getNotifications() throws Exception {
        if (isInitialized()) {
            ServiceProvider this_$iv = this.services;
            return (INotificationsManager) this_$iv.getService(INotificationsManager.class);
        }
        throw new Exception("Must call 'initWithContext' before use");
    }

    @Override // com.onesignal.IOneSignal
    public ILocationManager getLocation() throws Exception {
        if (isInitialized()) {
            ServiceProvider this_$iv = this.services;
            return (ILocationManager) this_$iv.getService(ILocationManager.class);
        }
        throw new Exception("Must call 'initWithContext' before use");
    }

    @Override // com.onesignal.IOneSignal
    public IInAppMessagesManager getInAppMessages() throws Exception {
        if (isInitialized()) {
            ServiceProvider this_$iv = this.services;
            return (IInAppMessagesManager) this_$iv.getService(IInAppMessagesManager.class);
        }
        throw new Exception("Must call 'initWithContext' before use");
    }

    @Override // com.onesignal.IOneSignal
    public IUserManager getUser() throws Exception {
        if (isInitialized()) {
            ServiceProvider this_$iv = this.services;
            return (IUserManager) this_$iv.getService(IUserManager.class);
        }
        throw new Exception("Must call 'initWithContext' before use");
    }

    private final IdentityModelStore getIdentityModelStore() {
        ServiceProvider this_$iv = this.services;
        return (IdentityModelStore) this_$iv.getService(IdentityModelStore.class);
    }

    private final PropertiesModelStore getPropertiesModelStore() {
        ServiceProvider this_$iv = this.services;
        return (PropertiesModelStore) this_$iv.getService(PropertiesModelStore.class);
    }

    private final SubscriptionModelStore getSubscriptionModelStore() {
        ServiceProvider this_$iv = this.services;
        return (SubscriptionModelStore) this_$iv.getService(SubscriptionModelStore.class);
    }

    private final IPreferencesService getPreferencesService() {
        ServiceProvider this_$iv = this.services;
        return (IPreferencesService) this_$iv.getService(IPreferencesService.class);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01a4 A[Catch: all -> 0x033b, TryCatch #0 {, blocks: (B:5:0x0037, B:7:0x003e, B:10:0x0048, B:12:0x00a8, B:14:0x00b5, B:16:0x00bb, B:19:0x00c3, B:21:0x00e4, B:23:0x00f1, B:26:0x0101, B:27:0x0109, B:29:0x010d, B:30:0x011a, B:32:0x011e, B:33:0x012b, B:35:0x012f, B:36:0x0140, B:38:0x014c, B:41:0x0162, B:80:0x0331, B:42:0x018d, B:44:0x01a4, B:45:0x01f1, B:47:0x0220, B:53:0x024d, B:61:0x0260, B:64:0x026e, B:66:0x0273, B:68:0x027f, B:69:0x0281, B:71:0x028a, B:74:0x02b5, B:77:0x02d1, B:79:0x02ef, B:70:0x0285, B:56:0x0256, B:50:0x0246), top: B:86:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01f1 A[Catch: all -> 0x033b, TryCatch #0 {, blocks: (B:5:0x0037, B:7:0x003e, B:10:0x0048, B:12:0x00a8, B:14:0x00b5, B:16:0x00bb, B:19:0x00c3, B:21:0x00e4, B:23:0x00f1, B:26:0x0101, B:27:0x0109, B:29:0x010d, B:30:0x011a, B:32:0x011e, B:33:0x012b, B:35:0x012f, B:36:0x0140, B:38:0x014c, B:41:0x0162, B:80:0x0331, B:42:0x018d, B:44:0x01a4, B:45:0x01f1, B:47:0x0220, B:53:0x024d, B:61:0x0260, B:64:0x026e, B:66:0x0273, B:68:0x027f, B:69:0x0281, B:71:0x028a, B:74:0x02b5, B:77:0x02d1, B:79:0x02ef, B:70:0x0285, B:56:0x0256, B:50:0x0246), top: B:86:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0273 A[Catch: all -> 0x033b, TryCatch #0 {, blocks: (B:5:0x0037, B:7:0x003e, B:10:0x0048, B:12:0x00a8, B:14:0x00b5, B:16:0x00bb, B:19:0x00c3, B:21:0x00e4, B:23:0x00f1, B:26:0x0101, B:27:0x0109, B:29:0x010d, B:30:0x011a, B:32:0x011e, B:33:0x012b, B:35:0x012f, B:36:0x0140, B:38:0x014c, B:41:0x0162, B:80:0x0331, B:42:0x018d, B:44:0x01a4, B:45:0x01f1, B:47:0x0220, B:53:0x024d, B:61:0x0260, B:64:0x026e, B:66:0x0273, B:68:0x027f, B:69:0x0281, B:71:0x028a, B:74:0x02b5, B:77:0x02d1, B:79:0x02ef, B:70:0x0285, B:56:0x0256, B:50:0x0246), top: B:86:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0285 A[Catch: all -> 0x033b, TryCatch #0 {, blocks: (B:5:0x0037, B:7:0x003e, B:10:0x0048, B:12:0x00a8, B:14:0x00b5, B:16:0x00bb, B:19:0x00c3, B:21:0x00e4, B:23:0x00f1, B:26:0x0101, B:27:0x0109, B:29:0x010d, B:30:0x011a, B:32:0x011e, B:33:0x012b, B:35:0x012f, B:36:0x0140, B:38:0x014c, B:41:0x0162, B:80:0x0331, B:42:0x018d, B:44:0x01a4, B:45:0x01f1, B:47:0x0220, B:53:0x024d, B:61:0x0260, B:64:0x026e, B:66:0x0273, B:68:0x027f, B:69:0x0281, B:71:0x028a, B:74:0x02b5, B:77:0x02d1, B:79:0x02ef, B:70:0x0285, B:56:0x0256, B:50:0x0246), top: B:86:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02cf  */
    @Override // com.onesignal.IOneSignal
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean initWithContext(android.content.Context r23, java.lang.String r24) {
        /*
            Method dump skipped, instruction units count: 830
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.internal.OneSignalImp.initWithContext(android.content.Context, java.lang.String):boolean");
    }

    @Override // com.onesignal.IOneSignal
    public void login(final String externalId, String jwtBearerToken) throws Exception {
        Intrinsics.checkNotNullParameter(externalId, "externalId");
        Logging.log(LogLevel.DEBUG, "login(externalId: " + externalId + ", jwtBearerToken: " + jwtBearerToken + ')');
        if (!isInitialized()) {
            throw new Exception("Must call 'initWithContext' before 'login'");
        }
        Ref.ObjectRef currentIdentityExternalId = new Ref.ObjectRef();
        Ref.ObjectRef currentIdentityOneSignalId = new Ref.ObjectRef();
        Ref.ObjectRef newIdentityOneSignalId = new Ref.ObjectRef();
        newIdentityOneSignalId.element = "";
        synchronized (this.loginLock) {
            IdentityModelStore identityModelStore = getIdentityModelStore();
            Intrinsics.checkNotNull(identityModelStore);
            currentIdentityExternalId.element = identityModelStore.getModel().getExternalId();
            IdentityModelStore identityModelStore2 = getIdentityModelStore();
            Intrinsics.checkNotNull(identityModelStore2);
            currentIdentityOneSignalId.element = identityModelStore2.getModel().getOnesignalId();
            if (Intrinsics.areEqual(currentIdentityExternalId.element, externalId)) {
                return;
            }
            createAndSwitchToNewUser$default(this, false, new Function2<IdentityModel, PropertiesModel, Unit>() { // from class: com.onesignal.internal.OneSignalImp$login$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) throws Throwable {
                    invoke((IdentityModel) p1, (PropertiesModel) p2);
                    return Unit.INSTANCE;
                }

                public final void invoke(IdentityModel identityModel, PropertiesModel propertiesModel) throws Throwable {
                    Intrinsics.checkNotNullParameter(identityModel, "identityModel");
                    Intrinsics.checkNotNullParameter(propertiesModel, "<anonymous parameter 1>");
                    identityModel.setExternalId(externalId);
                }
            }, 1, null);
            IdentityModelStore identityModelStore3 = getIdentityModelStore();
            Intrinsics.checkNotNull(identityModelStore3);
            newIdentityOneSignalId.element = identityModelStore3.getModel().getOnesignalId();
            Unit unit = Unit.INSTANCE;
            ThreadUtilsKt.suspendifyOnThread$default(0, new AnonymousClass2(newIdentityOneSignalId, externalId, currentIdentityExternalId, currentIdentityOneSignalId, null), 1, null);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.internal.OneSignalImp$login$2, reason: invalid class name */
    /* JADX INFO: compiled from: OneSignalImp.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.internal.OneSignalImp$login$2", f = "OneSignalImp.kt", i = {}, l = {388}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<String> $currentIdentityExternalId;
        final /* synthetic */ Ref.ObjectRef<String> $currentIdentityOneSignalId;
        final /* synthetic */ String $externalId;
        final /* synthetic */ Ref.ObjectRef<String> $newIdentityOneSignalId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Ref.ObjectRef<String> objectRef, String str, Ref.ObjectRef<String> objectRef2, Ref.ObjectRef<String> objectRef3, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$newIdentityOneSignalId = objectRef;
            this.$externalId = str;
            this.$currentIdentityExternalId = objectRef2;
            this.$currentIdentityOneSignalId = objectRef3;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return OneSignalImp.this.new AnonymousClass2(this.$newIdentityOneSignalId, this.$externalId, this.$currentIdentityExternalId, this.$currentIdentityOneSignalId, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    IOperationRepo iOperationRepo = OneSignalImp.this.operationRepo;
                    Intrinsics.checkNotNull(iOperationRepo);
                    ConfigModel configModel = OneSignalImp.this.configModel;
                    Intrinsics.checkNotNull(configModel);
                    String appId = configModel.getAppId();
                    String str = (String) this.$newIdentityOneSignalId.element;
                    String str2 = this.$externalId;
                    String str3 = this.$currentIdentityExternalId.element == null ? (String) this.$currentIdentityOneSignalId.element : null;
                    this.label = 1;
                    Object objEnqueueAndWait$default = IOperationRepo.DefaultImpls.enqueueAndWait$default(iOperationRepo, new LoginUserOperation(appId, str, str2, str3), false, (Continuation) this, 2, null);
                    if (objEnqueueAndWait$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objEnqueueAndWait$default;
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean result = ((Boolean) $result).booleanValue();
            if (!result) {
                Logging.log(LogLevel.ERROR, "Could not login user");
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.onesignal.IOneSignal
    public void logout() throws Exception {
        Logging.log(LogLevel.DEBUG, "logout()");
        if (!isInitialized()) {
            throw new Exception("Must call 'initWithContext' before 'logout'");
        }
        synchronized (this.loginLock) {
            IdentityModelStore identityModelStore = getIdentityModelStore();
            Intrinsics.checkNotNull(identityModelStore);
            if (identityModelStore.getModel().getExternalId() == null) {
                return;
            }
            createAndSwitchToNewUser$default(this, false, null, 3, null);
            IOperationRepo iOperationRepo = this.operationRepo;
            Intrinsics.checkNotNull(iOperationRepo);
            ConfigModel configModel = this.configModel;
            Intrinsics.checkNotNull(configModel);
            String appId = configModel.getAppId();
            IdentityModelStore identityModelStore2 = getIdentityModelStore();
            Intrinsics.checkNotNull(identityModelStore2);
            String onesignalId = identityModelStore2.getModel().getOnesignalId();
            IdentityModelStore identityModelStore3 = getIdentityModelStore();
            Intrinsics.checkNotNull(identityModelStore3);
            IOperationRepo.DefaultImpls.enqueue$default(iOperationRepo, new LoginUserOperation(appId, onesignalId, identityModelStore3.getModel().getExternalId(), null, 8, null), false, 2, null);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final String getLegacyAppId() {
        return IPreferencesService.DefaultImpls.getString$default(getPreferencesService(), PreferenceStores.ONESIGNAL, PreferenceOneSignalKeys.PREFS_LEGACY_APP_ID, null, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void createAndSwitchToNewUser$default(OneSignalImp oneSignalImp, boolean z, Function2 function2, int i, Object obj) throws Throwable {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            function2 = null;
        }
        oneSignalImp.createAndSwitchToNewUser(z, function2);
    }

    private final void createAndSwitchToNewUser(boolean suppressBackendOperation, Function2<? super IdentityModel, ? super PropertiesModel, Unit> function2) throws Throwable {
        Object element$iv;
        String strCreateLocalId;
        String address;
        SubscriptionStatus status;
        Logging.debug$default("createAndSwitchToNewUser()", null, 2, null);
        String sdkId = IDManager.INSTANCE.createLocalId();
        IdentityModel identityModel = new IdentityModel();
        identityModel.setOnesignalId(sdkId);
        PropertiesModel propertiesModel = new PropertiesModel();
        propertiesModel.setOnesignalId(sdkId);
        if (function2 != null) {
            function2.invoke(identityModel, propertiesModel);
        }
        List subscriptions = new ArrayList();
        SubscriptionModelStore subscriptionModelStore = getSubscriptionModelStore();
        Intrinsics.checkNotNull(subscriptionModelStore);
        Iterable $this$firstOrNull$iv = subscriptionModelStore.list();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                SubscriptionModel it2 = (SubscriptionModel) element$iv;
                String id = it2.getId();
                ConfigModel configModel = this.configModel;
                Intrinsics.checkNotNull(configModel);
                if (Intrinsics.areEqual(id, configModel.getPushSubscriptionId())) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        SubscriptionModel currentPushSubscription = (SubscriptionModel) element$iv;
        SubscriptionModel newPushSubscription = new SubscriptionModel();
        if (currentPushSubscription == null || (strCreateLocalId = currentPushSubscription.getId()) == null) {
            strCreateLocalId = IDManager.INSTANCE.createLocalId();
        }
        newPushSubscription.setId(strCreateLocalId);
        newPushSubscription.setType(SubscriptionType.PUSH);
        newPushSubscription.setOptedIn(currentPushSubscription != null ? currentPushSubscription.getOptedIn() : true);
        if (currentPushSubscription == null || (address = currentPushSubscription.getAddress()) == null) {
            address = "";
        }
        newPushSubscription.setAddress(address);
        if (currentPushSubscription == null || (status = currentPushSubscription.getStatus()) == null) {
            status = SubscriptionStatus.NO_PERMISSION;
        }
        newPushSubscription.setStatus(status);
        newPushSubscription.setSdk(OneSignalUtils.SDK_VERSION);
        String str = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(str, "RELEASE");
        newPushSubscription.setDeviceOS(str);
        DeviceUtils deviceUtils = DeviceUtils.INSTANCE;
        ServiceProvider this_$iv = this.services;
        String carrierName = deviceUtils.getCarrierName(((IApplicationService) this_$iv.getService(IApplicationService.class)).getAppContext());
        if (carrierName == null) {
            carrierName = "";
        }
        newPushSubscription.setCarrier(carrierName);
        AndroidUtils androidUtils = AndroidUtils.INSTANCE;
        ServiceProvider this_$iv2 = this.services;
        String appVersion = androidUtils.getAppVersion(((IApplicationService) this_$iv2.getService(IApplicationService.class)).getAppContext());
        newPushSubscription.setAppVersion(appVersion != null ? appVersion : "");
        ConfigModel configModel2 = this.configModel;
        Intrinsics.checkNotNull(configModel2);
        configModel2.setPushSubscriptionId(newPushSubscription.getId());
        subscriptions.add(newPushSubscription);
        SubscriptionModelStore subscriptionModelStore2 = getSubscriptionModelStore();
        Intrinsics.checkNotNull(subscriptionModelStore2);
        subscriptionModelStore2.clear(ModelChangeTags.NO_PROPOGATE);
        IdentityModelStore identityModelStore = getIdentityModelStore();
        Intrinsics.checkNotNull(identityModelStore);
        ISingletonModelStore.DefaultImpls.replace$default(identityModelStore, identityModel, null, 2, null);
        PropertiesModelStore propertiesModelStore = getPropertiesModelStore();
        Intrinsics.checkNotNull(propertiesModelStore);
        ISingletonModelStore.DefaultImpls.replace$default(propertiesModelStore, propertiesModel, null, 2, null);
        if (suppressBackendOperation) {
            SubscriptionModelStore subscriptionModelStore3 = getSubscriptionModelStore();
            Intrinsics.checkNotNull(subscriptionModelStore3);
            subscriptionModelStore3.replaceAll(subscriptions, ModelChangeTags.NO_PROPOGATE);
        } else {
            if (currentPushSubscription != null) {
                IOperationRepo iOperationRepo = this.operationRepo;
                Intrinsics.checkNotNull(iOperationRepo);
                ConfigModel configModel3 = this.configModel;
                Intrinsics.checkNotNull(configModel3);
                IOperationRepo.DefaultImpls.enqueue$default(iOperationRepo, new TransferSubscriptionOperation(configModel3.getAppId(), currentPushSubscription.getId(), sdkId), false, 2, null);
                SubscriptionModelStore subscriptionModelStore4 = getSubscriptionModelStore();
                Intrinsics.checkNotNull(subscriptionModelStore4);
                subscriptionModelStore4.replaceAll(subscriptions, ModelChangeTags.NO_PROPOGATE);
                return;
            }
            SubscriptionModelStore subscriptionModelStore5 = getSubscriptionModelStore();
            Intrinsics.checkNotNull(subscriptionModelStore5);
            IModelStore.DefaultImpls.replaceAll$default(subscriptionModelStore5, subscriptions, null, 2, null);
        }
    }

    @Override // com.onesignal.common.services.IServiceProvider
    public <T> boolean hasService(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "c");
        return this.services.hasService(cls);
    }

    @Override // com.onesignal.common.services.IServiceProvider
    public <T> T getService(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "c");
        return (T) this.services.getService(cls);
    }

    @Override // com.onesignal.common.services.IServiceProvider
    public <T> T getServiceOrNull(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "c");
        return (T) this.services.getServiceOrNull(cls);
    }

    @Override // com.onesignal.common.services.IServiceProvider
    public <T> List<T> getAllServices(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "c");
        return this.services.getAllServices(cls);
    }
}
