package com.onesignal.core.internal.application;

import android.app.Activity;
import android.content.Context;
import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: IApplicationService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0018H&J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0018H&J\u0011\u0010\u001b\u001a\u00020\u0011H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u0011\u0010\u001d\u001a\u00020\u0011H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001cR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u00020\u000bX¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lcom/onesignal/core/internal/application/IApplicationService;", "", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "current", "Landroid/app/Activity;", "getCurrent", "()Landroid/app/Activity;", "entryState", "Lcom/onesignal/core/internal/application/AppEntryAction;", "getEntryState", "()Lcom/onesignal/core/internal/application/AppEntryAction;", "setEntryState", "(Lcom/onesignal/core/internal/application/AppEntryAction;)V", "isInForeground", "", "()Z", "addActivityLifecycleHandler", "", "handler", "Lcom/onesignal/core/internal/application/IActivityLifecycleHandler;", "addApplicationLifecycleHandler", "Lcom/onesignal/core/internal/application/IApplicationLifecycleHandler;", "removeActivityLifecycleHandler", "removeApplicationLifecycleHandler", "waitUntilActivityReady", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitUntilSystemConditionsAvailable", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface IApplicationService {
    void addActivityLifecycleHandler(IActivityLifecycleHandler iActivityLifecycleHandler);

    void addApplicationLifecycleHandler(IApplicationLifecycleHandler iApplicationLifecycleHandler);

    Context getAppContext();

    Activity getCurrent();

    AppEntryAction getEntryState();

    boolean isInForeground();

    void removeActivityLifecycleHandler(IActivityLifecycleHandler iActivityLifecycleHandler);

    void removeApplicationLifecycleHandler(IApplicationLifecycleHandler iApplicationLifecycleHandler);

    void setEntryState(AppEntryAction appEntryAction);

    Object waitUntilActivityReady(Continuation<? super Boolean> continuation);

    Object waitUntilSystemConditionsAvailable(Continuation<? super Boolean> continuation);
}
