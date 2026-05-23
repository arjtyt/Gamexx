package com.onesignal.core.internal.application.impl;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import com.onesignal.common.AndroidUtils;
import com.onesignal.common.events.EventProducer;
import com.onesignal.common.threading.Waiter;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.application.ActivityLifecycleHandlerBase;
import com.onesignal.core.internal.application.AppEntryAction;
import com.onesignal.core.internal.application.IActivityLifecycleHandler;
import com.onesignal.core.internal.application.IApplicationLifecycleHandler;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.debug.internal.logging.Logging;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApplicationService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u000bH\u0016J\u0010\u0010+\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0012H\u0016J\u0016\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020/J\b\u00100\u001a\u00020)H\u0002J\b\u00101\u001a\u00020)H\u0002J\u001a\u00102\u001a\u00020)2\u0006\u0010-\u001a\u00020\b2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0010\u00105\u001a\u00020)2\u0006\u0010-\u001a\u00020\bH\u0016J\u0010\u00106\u001a\u00020)2\u0006\u0010-\u001a\u00020\bH\u0016J\u0010\u00107\u001a\u00020)2\u0006\u0010-\u001a\u00020\bH\u0016J\u0018\u00108\u001a\u00020)2\u0006\u00109\u001a\u00020\b2\u0006\u0010:\u001a\u000204H\u0016J\u0010\u0010;\u001a\u00020)2\u0006\u0010-\u001a\u00020\bH\u0016J\u0010\u0010<\u001a\u00020)2\u0006\u0010-\u001a\u00020\bH\u0016J\b\u0010=\u001a\u00020)H\u0016J\u0018\u0010>\u001a\u00020)2\u0006\u0010?\u001a\u00020\r2\u0006\u0010-\u001a\u00020\bH\u0002J\u0010\u0010@\u001a\u00020)2\u0006\u0010*\u001a\u00020\u000bH\u0016J\u0010\u0010A\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0012H\u0016J\u000e\u0010B\u001a\u00020)2\u0006\u0010C\u001a\u00020\u0006J\u0011\u0010D\u001a\u00020 H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010EJ\u0011\u0010F\u001a\u00020 H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010ER\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\nX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0014\u001a\u0004\u0018\u00010\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\u00020 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006G"}, d2 = {"Lcom/onesignal/core/internal/application/impl/ApplicationService;", "Lcom/onesignal/core/internal/application/IApplicationService;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "()V", "_appContext", "Landroid/content/Context;", "_current", "Landroid/app/Activity;", "activityLifecycleNotifier", "Lcom/onesignal/common/events/EventProducer;", "Lcom/onesignal/core/internal/application/IActivityLifecycleHandler;", "activityReferences", "", "appContext", "getAppContext", "()Landroid/content/Context;", "applicationLifecycleNotifier", "Lcom/onesignal/core/internal/application/IApplicationLifecycleHandler;", "value", "current", "getCurrent", "()Landroid/app/Activity;", "setCurrent", "(Landroid/app/Activity;)V", "entryState", "Lcom/onesignal/core/internal/application/AppEntryAction;", "getEntryState", "()Lcom/onesignal/core/internal/application/AppEntryAction;", "setEntryState", "(Lcom/onesignal/core/internal/application/AppEntryAction;)V", "isActivityChangingConfigurations", "", "isInForeground", "()Z", "nextResumeIsFirstActivity", "systemConditionNotifier", "Lcom/onesignal/core/internal/application/impl/ISystemConditionHandler;", "wasInBackground", "getWasInBackground", "addActivityLifecycleHandler", "", "handler", "addApplicationLifecycleHandler", "decorViewReady", "activity", "runnable", "Ljava/lang/Runnable;", "handleFocus", "handleLostFocus", "onActivityCreated", "bundle", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "p0", "p1", "onActivityStarted", "onActivityStopped", "onGlobalLayout", "onOrientationChanged", "orientation", "removeActivityLifecycleHandler", "removeApplicationLifecycleHandler", "start", "context", "waitUntilActivityReady", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitUntilSystemConditionsAvailable", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ApplicationService implements IApplicationService, Application.ActivityLifecycleCallbacks, ViewTreeObserver.OnGlobalLayoutListener {
    private Context _appContext;
    private Activity _current;
    private int activityReferences;
    private boolean isActivityChangingConfigurations;
    private boolean nextResumeIsFirstActivity;
    private final EventProducer<IActivityLifecycleHandler> activityLifecycleNotifier = new EventProducer<>();
    private final EventProducer<IApplicationLifecycleHandler> applicationLifecycleNotifier = new EventProducer<>();
    private final EventProducer<ISystemConditionHandler> systemConditionNotifier = new EventProducer<>();
    private AppEntryAction entryState = AppEntryAction.APP_CLOSE;

    /* JADX INFO: renamed from: com.onesignal.core.internal.application.impl.ApplicationService$waitUntilActivityReady$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ApplicationService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.application.impl.ApplicationService", f = "ApplicationService.kt", i = {}, l = {309}, m = "waitUntilActivityReady", n = {}, s = {})
    static final class C00161 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C00161(Continuation<? super C00161> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationService.this.waitUntilActivityReady((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.core.internal.application.impl.ApplicationService$waitUntilSystemConditionsAvailable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ApplicationService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.application.impl.ApplicationService", f = "ApplicationService.kt", i = {0, 0, 1, 1, 2, 2}, l = {238, 269, 296}, m = "waitUntilSystemConditionsAvailable", n = {"this", "waitForActivityRetryCount", "this", "currentActivity", "this", "systemConditionHandler"}, s = {"L$0", "I$0", "L$0", "L$1", "L$0", "L$1"})
    static final class C00171 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00171(Continuation<? super C00171> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationService.this.waitUntilSystemConditionsAvailable((Continuation) this);
        }
    }

    @Override // com.onesignal.core.internal.application.IApplicationService
    public boolean isInForeground() {
        return getEntryState().isAppOpen() || getEntryState().isNotificationClick();
    }

    @Override // com.onesignal.core.internal.application.IApplicationService
    public AppEntryAction getEntryState() {
        return this.entryState;
    }

    @Override // com.onesignal.core.internal.application.IApplicationService
    public void setEntryState(AppEntryAction appEntryAction) {
        Intrinsics.checkNotNullParameter(appEntryAction, "<set-?>");
        this.entryState = appEntryAction;
    }

    @Override // com.onesignal.core.internal.application.IApplicationService
    public Context getAppContext() {
        Context context = this._appContext;
        Intrinsics.checkNotNull(context);
        return context;
    }

    @Override // com.onesignal.core.internal.application.IApplicationService
    public Activity getCurrent() {
        return this._current;
    }

    public void setCurrent(final Activity value) {
        this._current = value;
        Logging.debug$default("ApplicationService: current activity=" + getCurrent(), null, 2, null);
        if (value != null) {
            this.activityLifecycleNotifier.fire(new Function1<IActivityLifecycleHandler, Unit>() { // from class: com.onesignal.core.internal.application.impl.ApplicationService$current$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((IActivityLifecycleHandler) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(IActivityLifecycleHandler it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.onActivityAvailable(value);
                }
            });
            try {
                value.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private final boolean getWasInBackground() {
        return !isInForeground() || this.nextResumeIsFirstActivity;
    }

    public final void start(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this._appContext = context;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
        Application application = (Application) applicationContext;
        application.registerActivityLifecycleCallbacks(this);
        application.registerComponentCallbacks(new ComponentCallbacks() { // from class: com.onesignal.core.internal.application.impl.ApplicationService$start$configuration$1
            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(Configuration newConfig) {
                Intrinsics.checkNotNullParameter(newConfig, "newConfig");
                if (this.this$0.getCurrent() != null) {
                    AndroidUtils androidUtils = AndroidUtils.INSTANCE;
                    Activity current = this.this$0.getCurrent();
                    Intrinsics.checkNotNull(current);
                    if (androidUtils.hasConfigChangeFlag(current, 128)) {
                        ApplicationService applicationService = this.this$0;
                        int i = newConfig.orientation;
                        Activity current2 = this.this$0.getCurrent();
                        Intrinsics.checkNotNull(current2);
                        applicationService.onOrientationChanged(i, current2);
                    }
                }
            }

            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
            }
        });
        boolean isContextActivity = context instanceof Activity;
        boolean isCurrentActivityNull = getCurrent() == null;
        if (!isCurrentActivityNull || isContextActivity) {
            setEntryState(AppEntryAction.APP_OPEN);
            if (isCurrentActivityNull && isContextActivity) {
                setCurrent((Activity) context);
                this.activityReferences = 1;
                this.nextResumeIsFirstActivity = false;
            }
        } else {
            this.nextResumeIsFirstActivity = true;
            setEntryState(AppEntryAction.APP_CLOSE);
        }
        Logging.debug$default("ApplicationService.init: entryState=" + getEntryState(), null, 2, null);
    }

    @Override // com.onesignal.core.internal.application.IApplicationService
    public void addApplicationLifecycleHandler(IApplicationLifecycleHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.applicationLifecycleNotifier.subscribe(handler);
        if (getCurrent() != null) {
            handler.onFocus(true);
        }
    }

    @Override // com.onesignal.core.internal.application.IApplicationService
    public void removeApplicationLifecycleHandler(IApplicationLifecycleHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.applicationLifecycleNotifier.unsubscribe(handler);
    }

    @Override // com.onesignal.core.internal.application.IApplicationService
    public void addActivityLifecycleHandler(IActivityLifecycleHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.activityLifecycleNotifier.subscribe(handler);
        if (getCurrent() != null) {
            Activity current = getCurrent();
            Intrinsics.checkNotNull(current);
            handler.onActivityAvailable(current);
        }
    }

    @Override // com.onesignal.core.internal.application.IApplicationService
    public void removeActivityLifecycleHandler(IActivityLifecycleHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.activityLifecycleNotifier.unsubscribe(handler);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logging.debug$default("ApplicationService.onActivityCreated(" + this.activityReferences + ',' + getEntryState() + "): " + activity, null, 2, null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logging.debug$default("ApplicationService.onActivityStarted(" + this.activityReferences + ',' + getEntryState() + "): " + activity, null, 2, null);
        if (Intrinsics.areEqual(getCurrent(), activity)) {
            return;
        }
        setCurrent(activity);
        if (getWasInBackground() && !this.isActivityChangingConfigurations) {
            this.activityReferences = 1;
            handleFocus();
        } else {
            this.activityReferences++;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logging.debug$default("ApplicationService.onActivityResumed(" + this.activityReferences + ',' + getEntryState() + "): " + activity, null, 2, null);
        if (!Intrinsics.areEqual(getCurrent(), activity)) {
            setCurrent(activity);
        }
        if (getWasInBackground() && !this.isActivityChangingConfigurations) {
            this.activityReferences = 1;
            handleFocus();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logging.debug$default("ApplicationService.onActivityPaused(" + this.activityReferences + ',' + getEntryState() + "): " + activity, null, 2, null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(final Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logging.debug$default("ApplicationService.onActivityStopped(" + this.activityReferences + ',' + getEntryState() + "): " + activity, null, 2, null);
        this.isActivityChangingConfigurations = activity.isChangingConfigurations();
        if (!this.isActivityChangingConfigurations) {
            this.activityReferences--;
            if (this.activityReferences <= 0) {
                setCurrent(null);
                this.activityReferences = 0;
                handleLostFocus();
            }
        }
        this.activityLifecycleNotifier.fire(new Function1<IActivityLifecycleHandler, Unit>() { // from class: com.onesignal.core.internal.application.impl.ApplicationService.onActivityStopped.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IActivityLifecycleHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IActivityLifecycleHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onActivityStopped(activity);
            }
        });
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity p0, Bundle p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logging.debug$default("ApplicationService.onActivityDestroyed(" + this.activityReferences + ',' + getEntryState() + "): " + activity, null, 2, null);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.systemConditionNotifier.fire(new Function1<ISystemConditionHandler, Unit>() { // from class: com.onesignal.core.internal.application.impl.ApplicationService.onGlobalLayout.1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((ISystemConditionHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(ISystemConditionHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.systemConditionChanged();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0090 -> B:33:0x0091). Please report as a decompilation issue!!! */
    @Override // com.onesignal.core.internal.application.IApplicationService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object waitUntilSystemConditionsAvailable(kotlin.coroutines.Continuation<? super java.lang.Boolean> r13) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.core.internal.application.impl.ApplicationService.waitUntilSystemConditionsAvailable(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.core.internal.application.IApplicationService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object waitUntilActivityReady(kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.onesignal.core.internal.application.impl.ApplicationService.C00161
            if (r0 == 0) goto L14
            r0 = r8
            com.onesignal.core.internal.application.impl.ApplicationService$waitUntilActivityReady$1 r0 = (com.onesignal.core.internal.application.impl.ApplicationService.C00161) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.onesignal.core.internal.application.impl.ApplicationService$waitUntilActivityReady$1 r0 = new com.onesignal.core.internal.application.impl.ApplicationService$waitUntilActivityReady$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L31;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L2d:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L57
        L31:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r7
            android.app.Activity r4 = r2.getCurrent()
            if (r4 != 0) goto L41
            r1 = 0
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)
            return r1
        L41:
            com.onesignal.common.threading.Waiter r5 = new com.onesignal.common.threading.Waiter
            r5.<init>()
            com.onesignal.core.internal.application.impl.ApplicationService$$ExternalSyntheticLambda1 r6 = new com.onesignal.core.internal.application.impl.ApplicationService$$ExternalSyntheticLambda1
            r6.<init>()
            r2.decorViewReady(r4, r6)
            r0.label = r3
            java.lang.Object r2 = r5.waitForWake(r0)
            if (r2 != r1) goto L57
            return r1
        L57:
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.core.internal.application.impl.ApplicationService.waitUntilActivityReady(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: waitUntilActivityReady$lambda-0, reason: not valid java name */
    public static final void m7waitUntilActivityReady$lambda0(Waiter $waiter) throws Exception {
        Intrinsics.checkNotNullParameter($waiter, "$waiter");
        $waiter.wake();
    }

    public final void decorViewReady(Activity activity, final Runnable runnable) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        String str = "decorViewReady:" + runnable;
        activity.getWindow().getDecorView().post(new Runnable() { // from class: com.onesignal.core.internal.application.impl.ApplicationService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ApplicationService.m6decorViewReady$lambda1(this.f$0, runnable, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: decorViewReady$lambda-1, reason: not valid java name */
    public static final void m6decorViewReady$lambda1(final ApplicationService $self, final Runnable $runnable, final ApplicationService this$0) {
        Intrinsics.checkNotNullParameter($self, "$self");
        Intrinsics.checkNotNullParameter($runnable, "$runnable");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        $self.addActivityLifecycleHandler(new ActivityLifecycleHandlerBase() { // from class: com.onesignal.core.internal.application.impl.ApplicationService$decorViewReady$1$1
            @Override // com.onesignal.core.internal.application.ActivityLifecycleHandlerBase, com.onesignal.core.internal.application.IActivityLifecycleHandler
            public void onActivityAvailable(Activity currentActivity) {
                Intrinsics.checkNotNullParameter(currentActivity, "currentActivity");
                this.$self.removeActivityLifecycleHandler(this);
                if (AndroidUtils.INSTANCE.isActivityFullyReady(currentActivity)) {
                    $runnable.run();
                } else {
                    this$0.decorViewReady(currentActivity, $runnable);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onOrientationChanged(int orientation, final Activity activity) {
        switch (orientation) {
            case 1:
                Logging.debug$default("ApplicationService.onOrientationChanged: Configuration Orientation Change: PORTRAIT (" + orientation + ") on activity: " + activity, null, 2, null);
                break;
            case 2:
                Logging.debug$default("ApplicationService.onOrientationChanged: Configuration Orientation Change: LANDSCAPE (" + orientation + ") on activity: " + activity, null, 2, null);
                break;
        }
        handleLostFocus();
        this.activityLifecycleNotifier.fire(new Function1<IActivityLifecycleHandler, Unit>() { // from class: com.onesignal.core.internal.application.impl.ApplicationService.onOrientationChanged.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IActivityLifecycleHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IActivityLifecycleHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onActivityStopped(activity);
            }
        });
        this.activityLifecycleNotifier.fire(new Function1<IActivityLifecycleHandler, Unit>() { // from class: com.onesignal.core.internal.application.impl.ApplicationService.onOrientationChanged.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IActivityLifecycleHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IActivityLifecycleHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onActivityAvailable(activity);
            }
        });
        activity.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
        handleFocus();
    }

    private final void handleLostFocus() {
        if (isInForeground()) {
            Logging.debug$default("ApplicationService.handleLostFocus: application is now out of focus", null, 2, null);
            setEntryState(AppEntryAction.APP_CLOSE);
            this.applicationLifecycleNotifier.fire(new Function1<IApplicationLifecycleHandler, Unit>() { // from class: com.onesignal.core.internal.application.impl.ApplicationService.handleLostFocus.1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((IApplicationLifecycleHandler) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(IApplicationLifecycleHandler it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.onUnfocused();
                }
            });
            return;
        }
        Logging.debug$default("ApplicationService.handleLostFocus: application already out of focus", null, 2, null);
    }

    private final void handleFocus() {
        if (getWasInBackground()) {
            Logging.debug$default("ApplicationService.handleFocus: application is now in focus, nextResumeIsFirstActivity=" + this.nextResumeIsFirstActivity, null, 2, null);
            this.nextResumeIsFirstActivity = false;
            if (getEntryState() != AppEntryAction.NOTIFICATION_CLICK) {
                setEntryState(AppEntryAction.APP_OPEN);
            }
            this.applicationLifecycleNotifier.fire(new Function1<IApplicationLifecycleHandler, Unit>() { // from class: com.onesignal.core.internal.application.impl.ApplicationService.handleFocus.1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((IApplicationLifecycleHandler) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(IApplicationLifecycleHandler it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.onFocus(false);
                }
            });
            return;
        }
        Logging.debug$default("ApplicationService.handleFocus: application never lost focus", null, 2, null);
    }
}
