package com.onesignal.core.internal.startup;

import com.onesignal.common.services.ServiceProvider;
import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StartupService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onesignal/core/internal/startup/StartupService;", "", "services", "Lcom/onesignal/common/services/ServiceProvider;", "(Lcom/onesignal/common/services/ServiceProvider;)V", "bootstrap", "", "scheduleStart", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class StartupService {
    private final ServiceProvider services;

    public StartupService(ServiceProvider services) {
        Intrinsics.checkNotNullParameter(services, "services");
        this.services = services;
    }

    public final void bootstrap() {
        ServiceProvider this_$iv = this.services;
        Iterable $this$forEach$iv = this_$iv.getAllServices(IBootstrapService.class);
        for (Object element$iv : $this$forEach$iv) {
            IBootstrapService it = (IBootstrapService) element$iv;
            it.bootstrap();
        }
    }

    public final void scheduleStart() {
        new Thread(new Runnable() { // from class: com.onesignal.core.internal.startup.StartupService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                StartupService.m49scheduleStart$lambda2(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: scheduleStart$lambda-2, reason: not valid java name */
    public static final void m49scheduleStart$lambda2(StartupService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ServiceProvider this_$iv = this$0.services;
        Iterable $this$forEach$iv = this_$iv.getAllServices(IStartableService.class);
        for (Object element$iv : $this$forEach$iv) {
            IStartableService it = (IStartableService) element$iv;
            it.start();
        }
    }
}
