package com.onesignal.notifications;

import com.onesignal.common.modules.IModule;
import com.onesignal.common.services.IServiceProvider;
import com.onesignal.common.services.ServiceBuilder;
import com.onesignal.common.services.ServiceRegistration;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.config.ConfigModelStore;
import com.onesignal.core.internal.device.IDeviceService;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.notifications.internal.INotificationActivityOpener;
import com.onesignal.notifications.internal.NotificationsManager;
import com.onesignal.notifications.internal.analytics.IAnalyticsTracker;
import com.onesignal.notifications.internal.analytics.impl.FirebaseAnalyticsTracker;
import com.onesignal.notifications.internal.analytics.impl.NoAnalyticsTracker;
import com.onesignal.notifications.internal.backend.INotificationBackendService;
import com.onesignal.notifications.internal.backend.impl.NotificationBackendService;
import com.onesignal.notifications.internal.badges.IBadgeCountUpdater;
import com.onesignal.notifications.internal.badges.impl.BadgeCountUpdater;
import com.onesignal.notifications.internal.bundle.INotificationBundleProcessor;
import com.onesignal.notifications.internal.bundle.impl.NotificationBundleProcessor;
import com.onesignal.notifications.internal.channels.INotificationChannelManager;
import com.onesignal.notifications.internal.channels.impl.NotificationChannelManager;
import com.onesignal.notifications.internal.data.INotificationQueryHelper;
import com.onesignal.notifications.internal.data.INotificationRepository;
import com.onesignal.notifications.internal.data.impl.NotificationQueryHelper;
import com.onesignal.notifications.internal.data.impl.NotificationRepository;
import com.onesignal.notifications.internal.display.INotificationDisplayBuilder;
import com.onesignal.notifications.internal.display.INotificationDisplayer;
import com.onesignal.notifications.internal.display.ISummaryNotificationDisplayer;
import com.onesignal.notifications.internal.display.impl.NotificationDisplayBuilder;
import com.onesignal.notifications.internal.display.impl.NotificationDisplayer;
import com.onesignal.notifications.internal.display.impl.SummaryNotificationDisplayer;
import com.onesignal.notifications.internal.generation.INotificationGenerationProcessor;
import com.onesignal.notifications.internal.generation.INotificationGenerationWorkManager;
import com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor;
import com.onesignal.notifications.internal.generation.impl.NotificationGenerationWorkManager;
import com.onesignal.notifications.internal.lifecycle.INotificationLifecycleService;
import com.onesignal.notifications.internal.lifecycle.impl.NotificationLifecycleService;
import com.onesignal.notifications.internal.limiting.INotificationLimitManager;
import com.onesignal.notifications.internal.limiting.impl.NotificationLimitManager;
import com.onesignal.notifications.internal.listeners.DeviceRegistrationListener;
import com.onesignal.notifications.internal.open.INotificationOpenedProcessor;
import com.onesignal.notifications.internal.open.INotificationOpenedProcessorHMS;
import com.onesignal.notifications.internal.open.impl.NotificationOpenedProcessor;
import com.onesignal.notifications.internal.open.impl.NotificationOpenedProcessorHMS;
import com.onesignal.notifications.internal.permissions.INotificationPermissionController;
import com.onesignal.notifications.internal.permissions.impl.NotificationPermissionController;
import com.onesignal.notifications.internal.pushtoken.IPushTokenManager;
import com.onesignal.notifications.internal.pushtoken.PushTokenManager;
import com.onesignal.notifications.internal.receivereceipt.IReceiveReceiptProcessor;
import com.onesignal.notifications.internal.receivereceipt.IReceiveReceiptWorkManager;
import com.onesignal.notifications.internal.receivereceipt.impl.ReceiveReceiptProcessor;
import com.onesignal.notifications.internal.receivereceipt.impl.ReceiveReceiptWorkManager;
import com.onesignal.notifications.internal.registration.IPushRegistrator;
import com.onesignal.notifications.internal.registration.impl.GooglePlayServicesUpgradePrompt;
import com.onesignal.notifications.internal.registration.impl.IPushRegistratorCallback;
import com.onesignal.notifications.internal.registration.impl.PushRegistratorADM;
import com.onesignal.notifications.internal.registration.impl.PushRegistratorFCM;
import com.onesignal.notifications.internal.registration.impl.PushRegistratorHMS;
import com.onesignal.notifications.internal.registration.impl.PushRegistratorNone;
import com.onesignal.notifications.internal.restoration.INotificationRestoreProcessor;
import com.onesignal.notifications.internal.restoration.INotificationRestoreWorkManager;
import com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor;
import com.onesignal.notifications.internal.restoration.impl.NotificationRestoreWorkManager;
import com.onesignal.notifications.internal.summary.INotificationSummaryManager;
import com.onesignal.notifications.internal.summary.impl.NotificationSummaryManager;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationsModule.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/onesignal/notifications/NotificationsModule;", "Lcom/onesignal/common/modules/IModule;", "()V", "register", "", "builder", "Lcom/onesignal/common/services/ServiceBuilder;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationsModule implements IModule {
    @Override // com.onesignal.common.modules.IModule
    public void register(ServiceBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        ServiceRegistration this_$iv = builder.register(NotificationBackendService.class);
        this_$iv.provides(INotificationBackendService.class);
        ServiceRegistration this_$iv2 = builder.register(NotificationRestoreWorkManager.class);
        this_$iv2.provides(INotificationRestoreWorkManager.class);
        ServiceRegistration this_$iv3 = builder.register(NotificationQueryHelper.class);
        this_$iv3.provides(INotificationQueryHelper.class);
        ServiceRegistration this_$iv4 = builder.register(BadgeCountUpdater.class);
        this_$iv4.provides(IBadgeCountUpdater.class);
        ServiceRegistration this_$iv5 = builder.register(NotificationRepository.class);
        this_$iv5.provides(INotificationRepository.class);
        ServiceRegistration this_$iv6 = builder.register(NotificationGenerationWorkManager.class);
        this_$iv6.provides(INotificationGenerationWorkManager.class);
        ServiceRegistration this_$iv7 = builder.register(NotificationBundleProcessor.class);
        this_$iv7.provides(INotificationBundleProcessor.class);
        ServiceRegistration this_$iv8 = builder.register(NotificationChannelManager.class);
        this_$iv8.provides(INotificationChannelManager.class);
        ServiceRegistration this_$iv9 = builder.register(NotificationLimitManager.class);
        this_$iv9.provides(INotificationLimitManager.class);
        ServiceRegistration this_$iv10 = builder.register(NotificationDisplayer.class);
        this_$iv10.provides(INotificationDisplayer.class);
        ServiceRegistration this_$iv11 = builder.register(SummaryNotificationDisplayer.class);
        this_$iv11.provides(ISummaryNotificationDisplayer.class);
        ServiceRegistration this_$iv12 = builder.register(NotificationDisplayBuilder.class);
        this_$iv12.provides(INotificationDisplayBuilder.class);
        ServiceRegistration this_$iv13 = builder.register(NotificationGenerationProcessor.class);
        this_$iv13.provides(INotificationGenerationProcessor.class);
        ServiceRegistration this_$iv14 = builder.register(NotificationRestoreProcessor.class);
        this_$iv14.provides(INotificationRestoreProcessor.class);
        ServiceRegistration this_$iv15 = builder.register(NotificationSummaryManager.class);
        this_$iv15.provides(INotificationSummaryManager.class);
        ServiceRegistration this_$iv16 = builder.register(NotificationOpenedProcessor.class);
        this_$iv16.provides(INotificationOpenedProcessor.class);
        ServiceRegistration this_$iv17 = builder.register(NotificationOpenedProcessorHMS.class);
        this_$iv17.provides(INotificationOpenedProcessorHMS.class);
        ServiceRegistration this_$iv18 = builder.register(NotificationPermissionController.class);
        this_$iv18.provides(INotificationPermissionController.class);
        ServiceRegistration this_$iv19 = builder.register(NotificationLifecycleService.class);
        this_$iv19.provides(INotificationLifecycleService.class).provides(INotificationActivityOpener.class);
        ServiceRegistration this_$iv20 = builder.register((Function1) new Function1<IServiceProvider, IAnalyticsTracker>() { // from class: com.onesignal.notifications.NotificationsModule.register.1
            public final IAnalyticsTracker invoke(IServiceProvider it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (FirebaseAnalyticsTracker.Companion.canTrack()) {
                    return new FirebaseAnalyticsTracker((IApplicationService) it.getService(IApplicationService.class), (ConfigModelStore) it.getService(ConfigModelStore.class), (ITime) it.getService(ITime.class));
                }
                return new NoAnalyticsTracker();
            }
        });
        this_$iv20.provides(IAnalyticsTracker.class);
        ServiceRegistration this_$iv21 = builder.register((Function1) new Function1<IServiceProvider, Object>() { // from class: com.onesignal.notifications.NotificationsModule.register.2
            public final Object invoke(IServiceProvider it) {
                Intrinsics.checkNotNullParameter(it, "it");
                IDeviceService deviceService = (IDeviceService) it.getService(IDeviceService.class);
                if (deviceService.isFireOSDeviceType()) {
                    Object service = new PushRegistratorADM((IApplicationService) it.getService(IApplicationService.class));
                    return service;
                }
                if (deviceService.isAndroidDeviceType()) {
                    if (deviceService.getHasFCMLibrary()) {
                        Object service2 = new PushRegistratorFCM((ConfigModelStore) it.getService(ConfigModelStore.class), (IApplicationService) it.getService(IApplicationService.class), (GooglePlayServicesUpgradePrompt) it.getService(GooglePlayServicesUpgradePrompt.class), deviceService);
                        return service2;
                    }
                    Object service3 = new PushRegistratorNone();
                    return service3;
                }
                Object service4 = new PushRegistratorHMS(deviceService, (IApplicationService) it.getService(IApplicationService.class));
                return service4;
            }
        });
        this_$iv21.provides(IPushRegistrator.class).provides(IPushRegistratorCallback.class);
        ServiceRegistration this_$iv22 = builder.register(GooglePlayServicesUpgradePrompt.class);
        this_$iv22.provides(GooglePlayServicesUpgradePrompt.class);
        ServiceRegistration this_$iv23 = builder.register(PushTokenManager.class);
        this_$iv23.provides(IPushTokenManager.class);
        ServiceRegistration this_$iv24 = builder.register(ReceiveReceiptWorkManager.class);
        this_$iv24.provides(IReceiveReceiptWorkManager.class);
        ServiceRegistration this_$iv25 = builder.register(ReceiveReceiptProcessor.class);
        this_$iv25.provides(IReceiveReceiptProcessor.class);
        ServiceRegistration this_$iv26 = builder.register(DeviceRegistrationListener.class);
        this_$iv26.provides(IStartableService.class);
        ServiceRegistration this_$iv27 = builder.register(NotificationsManager.class);
        this_$iv27.provides(INotificationsManager.class);
    }
}
