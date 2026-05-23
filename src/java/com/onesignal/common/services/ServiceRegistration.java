package com.onesignal.common.services;

import com.onesignal.core.BuildConfig;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceRegistration.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000\"\n\b\u0001\u0010\n\u0018\u0001*\u00020\u0002H\u0086\bJ \u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000\"\u0004\b\u0001\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\u0006J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u000eH&R\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/onesignal/common/services/ServiceRegistration;", "T", "", "()V", "services", "", "Ljava/lang/Class;", "getServices", "()Ljava/util/Set;", "provides", "TService", "c", "resolve", "provider", "Lcom/onesignal/common/services/IServiceProvider;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class ServiceRegistration<T> {
    private final Set<Class<?>> services = new LinkedHashSet();

    public abstract Object resolve(IServiceProvider iServiceProvider);

    public final Set<Class<?>> getServices() {
        return this.services;
    }

    public final /* synthetic */ <TService> ServiceRegistration<T> provides() {
        Intrinsics.reifiedOperationMarker(4, "TService");
        return provides(Object.class);
    }

    public final <TService> ServiceRegistration<T> provides(Class<TService> cls) {
        Intrinsics.checkNotNullParameter(cls, "c");
        this.services.add(cls);
        return this;
    }
}
