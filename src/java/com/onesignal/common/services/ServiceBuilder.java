package com.onesignal.common.services;

import com.onesignal.core.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceBuilder.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0005\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\nH\u0086\bJ(\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0005\"\u0004\b\u0000\u0010\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u0002H\t0\fH\u0016J!\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0005\"\u0004\b\u0000\u0010\t2\u0006\u0010\u000e\u001a\u0002H\tH\u0016¢\u0006\u0002\u0010\u000fJ\"\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0005\"\u0004\b\u0000\u0010\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\t0\u0011H\u0016R\u0018\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/onesignal/common/services/ServiceBuilder;", "Lcom/onesignal/common/services/IServiceBuilder;", "()V", "registrations", "", "Lcom/onesignal/common/services/ServiceRegistration;", "build", "Lcom/onesignal/common/services/ServiceProvider;", "register", "T", "", "create", "Lkotlin/Function1;", "Lcom/onesignal/common/services/IServiceProvider;", "obj", "(Ljava/lang/Object;)Lcom/onesignal/common/services/ServiceRegistration;", "c", "Ljava/lang/Class;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ServiceBuilder implements IServiceBuilder {
    private final List<ServiceRegistration<?>> registrations = new ArrayList();

    public final /* synthetic */ <T> ServiceRegistration<T> register() {
        Intrinsics.reifiedOperationMarker(4, "T");
        return register((Class) Object.class);
    }

    @Override // com.onesignal.common.services.IServiceBuilder
    public <T> ServiceRegistration<T> register(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "c");
        ServiceRegistrationReflection registration = new ServiceRegistrationReflection(cls);
        this.registrations.add(registration);
        return registration;
    }

    @Override // com.onesignal.common.services.IServiceBuilder
    public <T> ServiceRegistration<T> register(Function1<? super IServiceProvider, ? extends T> function1) {
        Intrinsics.checkNotNullParameter(function1, "create");
        ServiceRegistrationLambda registration = new ServiceRegistrationLambda(function1);
        this.registrations.add(registration);
        return registration;
    }

    @Override // com.onesignal.common.services.IServiceBuilder
    public <T> ServiceRegistration<T> register(T t) {
        ServiceRegistrationSingleton registration = new ServiceRegistrationSingleton(t);
        this.registrations.add(registration);
        return registration;
    }

    @Override // com.onesignal.common.services.IServiceBuilder
    public ServiceProvider build() {
        return new ServiceProvider(this.registrations);
    }
}
