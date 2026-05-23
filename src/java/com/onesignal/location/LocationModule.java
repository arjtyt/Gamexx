package com.onesignal.location;

import com.onesignal.common.modules.IModule;
import com.onesignal.common.services.IServiceProvider;
import com.onesignal.common.services.ServiceBuilder;
import com.onesignal.common.services.ServiceRegistration;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.background.IBackgroundService;
import com.onesignal.core.internal.device.IDeviceService;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.location.internal.LocationManager;
import com.onesignal.location.internal.background.LocationBackgroundService;
import com.onesignal.location.internal.capture.ILocationCapturer;
import com.onesignal.location.internal.capture.impl.LocationCapturer;
import com.onesignal.location.internal.common.LocationUtils;
import com.onesignal.location.internal.controller.ILocationController;
import com.onesignal.location.internal.controller.impl.FusedLocationApiWrapperImpl;
import com.onesignal.location.internal.controller.impl.GmsLocationController;
import com.onesignal.location.internal.controller.impl.HmsLocationController;
import com.onesignal.location.internal.controller.impl.IFusedLocationApiWrapper;
import com.onesignal.location.internal.controller.impl.NullLocationController;
import com.onesignal.location.internal.permissions.LocationPermissionController;
import com.onesignal.location.internal.preferences.ILocationPreferencesService;
import com.onesignal.location.internal.preferences.impl.LocationPreferencesService;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocationModule.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/onesignal/location/LocationModule;", "Lcom/onesignal/common/modules/IModule;", "()V", "register", "", "builder", "Lcom/onesignal/common/services/ServiceBuilder;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LocationModule implements IModule {
    @Override // com.onesignal.common.modules.IModule
    public void register(ServiceBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        ServiceRegistration this_$iv = builder.register(LocationPermissionController.class);
        this_$iv.provides(LocationPermissionController.class).provides(IStartableService.class);
        ServiceRegistration this_$iv2 = builder.register(FusedLocationApiWrapperImpl.class);
        this_$iv2.provides(IFusedLocationApiWrapper.class);
        ServiceRegistration this_$iv3 = builder.register((Function1) new Function1<IServiceProvider, ILocationController>() { // from class: com.onesignal.location.LocationModule.register.1
            public final ILocationController invoke(IServiceProvider it) {
                Intrinsics.checkNotNullParameter(it, "it");
                IDeviceService deviceService = (IDeviceService) it.getService(IDeviceService.class);
                if (deviceService.isAndroidDeviceType() && LocationUtils.INSTANCE.hasGMSLocationLibrary()) {
                    ILocationController service = new GmsLocationController((IApplicationService) it.getService(IApplicationService.class), (IFusedLocationApiWrapper) it.getService(IFusedLocationApiWrapper.class));
                    return service;
                }
                if (deviceService.isHuaweiDeviceType() && LocationUtils.INSTANCE.hasHMSLocationLibrary()) {
                    ILocationController service2 = new HmsLocationController((IApplicationService) it.getService(IApplicationService.class));
                    return service2;
                }
                ILocationController service3 = new NullLocationController();
                return service3;
            }
        });
        this_$iv3.provides(ILocationController.class);
        ServiceRegistration this_$iv4 = builder.register(LocationPreferencesService.class);
        this_$iv4.provides(ILocationPreferencesService.class);
        ServiceRegistration this_$iv5 = builder.register(LocationCapturer.class);
        this_$iv5.provides(ILocationCapturer.class);
        ServiceRegistration this_$iv6 = builder.register(LocationBackgroundService.class);
        this_$iv6.provides(IBackgroundService.class);
        ServiceRegistration this_$iv7 = builder.register(LocationManager.class);
        this_$iv7.provides(ILocationManager.class).provides(IStartableService.class);
    }
}
