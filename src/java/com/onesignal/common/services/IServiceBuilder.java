package com.onesignal.common.services;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: IServiceBuilder.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J(\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u00060\bH&J!\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\u0006\u0010\n\u001a\u0002H\u0006H&¢\u0006\u0002\u0010\u000bJ\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00060\rH&¨\u0006\u000e"}, d2 = {"Lcom/onesignal/common/services/IServiceBuilder;", "", "build", "Lcom/onesignal/common/services/ServiceProvider;", "register", "Lcom/onesignal/common/services/ServiceRegistration;", "T", "create", "Lkotlin/Function1;", "Lcom/onesignal/common/services/IServiceProvider;", "obj", "(Ljava/lang/Object;)Lcom/onesignal/common/services/ServiceRegistration;", "c", "Ljava/lang/Class;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface IServiceBuilder {
    ServiceProvider build();

    <T> ServiceRegistration<T> register(Class<T> cls);

    <T> ServiceRegistration<T> register(T t);

    <T> ServiceRegistration<T> register(Function1<? super IServiceProvider, ? extends T> function1);
}
