package com.onesignal.core;

import com.onesignal.common.modules.IModule;
import com.onesignal.common.services.ServiceBuilder;
import com.onesignal.common.services.ServiceRegistration;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.application.impl.ApplicationService;
import com.onesignal.core.internal.backend.IParamsBackendService;
import com.onesignal.core.internal.backend.impl.ParamsBackendService;
import com.onesignal.core.internal.background.IBackgroundManager;
import com.onesignal.core.internal.background.impl.BackgroundManager;
import com.onesignal.core.internal.config.ConfigModelStore;
import com.onesignal.core.internal.config.impl.ConfigModelStoreListener;
import com.onesignal.core.internal.database.IDatabaseProvider;
import com.onesignal.core.internal.database.impl.DatabaseProvider;
import com.onesignal.core.internal.device.IDeviceService;
import com.onesignal.core.internal.device.IInstallIdService;
import com.onesignal.core.internal.device.impl.DeviceService;
import com.onesignal.core.internal.device.impl.InstallIdService;
import com.onesignal.core.internal.http.IHttpClient;
import com.onesignal.core.internal.http.impl.HttpClient;
import com.onesignal.core.internal.http.impl.HttpConnectionFactory;
import com.onesignal.core.internal.http.impl.IHttpConnectionFactory;
import com.onesignal.core.internal.language.ILanguageContext;
import com.onesignal.core.internal.language.impl.LanguageContext;
import com.onesignal.core.internal.operations.IOperationRepo;
import com.onesignal.core.internal.operations.impl.OperationModelStore;
import com.onesignal.core.internal.operations.impl.OperationRepo;
import com.onesignal.core.internal.permissions.IRequestPermissionService;
import com.onesignal.core.internal.permissions.impl.RequestPermissionService;
import com.onesignal.core.internal.preferences.IPreferencesService;
import com.onesignal.core.internal.preferences.impl.PreferencesService;
import com.onesignal.core.internal.purchases.impl.TrackAmazonPurchase;
import com.onesignal.core.internal.purchases.impl.TrackGooglePurchase;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.core.internal.time.impl.Time;
import com.onesignal.inAppMessages.IInAppMessagesManager;
import com.onesignal.inAppMessages.internal.MisconfiguredIAMManager;
import com.onesignal.location.ILocationManager;
import com.onesignal.location.internal.MisconfiguredLocationManager;
import com.onesignal.notifications.INotificationsManager;
import com.onesignal.notifications.internal.MisconfiguredNotificationsManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoreModule.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/onesignal/core/CoreModule;", "Lcom/onesignal/common/modules/IModule;", "()V", "register", "", "builder", "Lcom/onesignal/common/services/ServiceBuilder;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class CoreModule implements IModule {
    @Override // com.onesignal.common.modules.IModule
    public void register(ServiceBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        ServiceRegistration this_$iv = builder.register(PreferencesService.class);
        this_$iv.provides(IPreferencesService.class).provides(IStartableService.class);
        ServiceRegistration this_$iv2 = builder.register(HttpConnectionFactory.class);
        this_$iv2.provides(IHttpConnectionFactory.class);
        ServiceRegistration this_$iv3 = builder.register(HttpClient.class);
        this_$iv3.provides(IHttpClient.class);
        ServiceRegistration this_$iv4 = builder.register(ApplicationService.class);
        this_$iv4.provides(IApplicationService.class);
        ServiceRegistration this_$iv5 = builder.register(DeviceService.class);
        this_$iv5.provides(IDeviceService.class);
        ServiceRegistration this_$iv6 = builder.register(Time.class);
        this_$iv6.provides(ITime.class);
        ServiceRegistration this_$iv7 = builder.register(DatabaseProvider.class);
        this_$iv7.provides(IDatabaseProvider.class);
        ServiceRegistration this_$iv8 = builder.register(InstallIdService.class);
        this_$iv8.provides(IInstallIdService.class);
        ServiceRegistration this_$iv9 = builder.register(ConfigModelStore.class);
        this_$iv9.provides(ConfigModelStore.class);
        ServiceRegistration this_$iv10 = builder.register(ParamsBackendService.class);
        this_$iv10.provides(IParamsBackendService.class);
        ServiceRegistration this_$iv11 = builder.register(ConfigModelStoreListener.class);
        this_$iv11.provides(IStartableService.class);
        ServiceRegistration this_$iv12 = builder.register(OperationModelStore.class);
        this_$iv12.provides(OperationModelStore.class);
        ServiceRegistration this_$iv13 = builder.register(OperationRepo.class);
        this_$iv13.provides(IOperationRepo.class).provides(IStartableService.class);
        ServiceRegistration this_$iv14 = builder.register(RequestPermissionService.class);
        this_$iv14.provides(RequestPermissionService.class).provides(IRequestPermissionService.class);
        ServiceRegistration this_$iv15 = builder.register(LanguageContext.class);
        this_$iv15.provides(ILanguageContext.class);
        ServiceRegistration this_$iv16 = builder.register(BackgroundManager.class);
        this_$iv16.provides(IBackgroundManager.class).provides(IStartableService.class);
        ServiceRegistration this_$iv17 = builder.register(TrackAmazonPurchase.class);
        this_$iv17.provides(IStartableService.class);
        ServiceRegistration this_$iv18 = builder.register(TrackGooglePurchase.class);
        this_$iv18.provides(IStartableService.class);
        ServiceRegistration this_$iv19 = builder.register(MisconfiguredNotificationsManager.class);
        this_$iv19.provides(INotificationsManager.class);
        ServiceRegistration this_$iv20 = builder.register(MisconfiguredIAMManager.class);
        this_$iv20.provides(IInAppMessagesManager.class);
        ServiceRegistration this_$iv21 = builder.register(MisconfiguredLocationManager.class);
        this_$iv21.provides(ILocationManager.class);
    }
}
