package com.onesignal.session.internal.session.impl;

import com.onesignal.common.events.EventProducer;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.application.IApplicationLifecycleHandler;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.background.IBackgroundService;
import com.onesignal.core.internal.config.ConfigModel;
import com.onesignal.core.internal.config.ConfigModelStore;
import com.onesignal.core.internal.startup.IBootstrapService;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.debug.LogLevel;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.session.internal.outcomes.impl.OutcomeEventsTable;
import com.onesignal.session.internal.session.ISessionLifecycleHandler;
import com.onesignal.session.internal.session.ISessionService;
import com.onesignal.session.internal.session.SessionModel;
import com.onesignal.session.internal.session.SessionModelStore;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SessionService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B%\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0011\u0010#\u001a\u00020$H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010%J\b\u0010&\u001a\u00020$H\u0016J\b\u0010'\u001a\u00020$H\u0002J\u0010\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\u0012H\u0016J\b\u0010*\u001a\u00020$H\u0016J\b\u0010+\u001a\u00020$H\u0016J\u0010\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020\u001eH\u0016J\u0010\u0010.\u001a\u00020$2\u0006\u0010-\u001a\u00020\u001eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, d2 = {"Lcom/onesignal/session/internal/session/impl/SessionService;", "Lcom/onesignal/session/internal/session/ISessionService;", "Lcom/onesignal/core/internal/startup/IBootstrapService;", "Lcom/onesignal/core/internal/startup/IStartableService;", "Lcom/onesignal/core/internal/background/IBackgroundService;", "Lcom/onesignal/core/internal/application/IApplicationLifecycleHandler;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_configModelStore", "Lcom/onesignal/core/internal/config/ConfigModelStore;", "_sessionModelStore", "Lcom/onesignal/session/internal/session/SessionModelStore;", "_time", "Lcom/onesignal/core/internal/time/ITime;", "(Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/core/internal/config/ConfigModelStore;Lcom/onesignal/session/internal/session/SessionModelStore;Lcom/onesignal/core/internal/time/ITime;)V", "config", "Lcom/onesignal/core/internal/config/ConfigModel;", "hasFocused", "", "hasSubscribers", "getHasSubscribers", "()Z", "scheduleBackgroundRunIn", "", "getScheduleBackgroundRunIn", "()Ljava/lang/Long;", OutcomeEventsTable.COLUMN_NAME_SESSION, "Lcom/onesignal/session/internal/session/SessionModel;", "sessionLifeCycleNotifier", "Lcom/onesignal/common/events/EventProducer;", "Lcom/onesignal/session/internal/session/ISessionLifecycleHandler;", "shouldFireOnSubscribe", "startTime", "getStartTime", "()J", "backgroundRun", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bootstrap", "endSession", "onFocus", "firedOnSubscribe", "onUnfocused", "start", "subscribe", "handler", "unsubscribe", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SessionService implements ISessionService, IBootstrapService, IStartableService, IBackgroundService, IApplicationLifecycleHandler {
    private final IApplicationService _applicationService;
    private final ConfigModelStore _configModelStore;
    private final SessionModelStore _sessionModelStore;
    private final ITime _time;
    private ConfigModel config;
    private boolean hasFocused;
    private SessionModel session;
    private final EventProducer<ISessionLifecycleHandler> sessionLifeCycleNotifier;
    private boolean shouldFireOnSubscribe;

    public SessionService(IApplicationService _applicationService, ConfigModelStore _configModelStore, SessionModelStore _sessionModelStore, ITime _time) {
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_configModelStore, "_configModelStore");
        Intrinsics.checkNotNullParameter(_sessionModelStore, "_sessionModelStore");
        Intrinsics.checkNotNullParameter(_time, "_time");
        this._applicationService = _applicationService;
        this._configModelStore = _configModelStore;
        this._sessionModelStore = _sessionModelStore;
        this._time = _time;
        this.sessionLifeCycleNotifier = new EventProducer<>();
    }

    @Override // com.onesignal.session.internal.session.ISessionService
    public long getStartTime() {
        SessionModel sessionModel = this.session;
        Intrinsics.checkNotNull(sessionModel);
        return sessionModel.getStartTime();
    }

    @Override // com.onesignal.core.internal.background.IBackgroundService
    public Long getScheduleBackgroundRunIn() {
        SessionModel sessionModel = this.session;
        Intrinsics.checkNotNull(sessionModel);
        if (!sessionModel.isValid()) {
            return null;
        }
        ConfigModel configModel = this.config;
        Intrinsics.checkNotNull(configModel);
        return Long.valueOf(configModel.getSessionFocusTimeout());
    }

    @Override // com.onesignal.core.internal.startup.IBootstrapService
    public void bootstrap() {
        this.session = this._sessionModelStore.getModel();
        this.config = this._configModelStore.getModel();
    }

    @Override // com.onesignal.core.internal.startup.IStartableService
    public void start() {
        this._applicationService.addApplicationLifecycleHandler(this);
    }

    @Override // com.onesignal.core.internal.background.IBackgroundService
    public Object backgroundRun(Continuation<? super Unit> continuation) throws Throwable {
        endSession();
        return Unit.INSTANCE;
    }

    private final void endSession() throws Throwable {
        SessionModel sessionModel = this.session;
        Intrinsics.checkNotNull(sessionModel);
        if (sessionModel.isValid()) {
            SessionModel sessionModel2 = this.session;
            Intrinsics.checkNotNull(sessionModel2);
            final long activeDuration = sessionModel2.getActiveDuration();
            Logging.debug$default("SessionService.backgroundRun: Session ended. activeDuration: " + activeDuration, null, 2, null);
            SessionModel sessionModel3 = this.session;
            Intrinsics.checkNotNull(sessionModel3);
            sessionModel3.setValid(false);
            this.sessionLifeCycleNotifier.fire(new Function1<ISessionLifecycleHandler, Unit>() { // from class: com.onesignal.session.internal.session.impl.SessionService.endSession.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((ISessionLifecycleHandler) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(ISessionLifecycleHandler it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.onSessionEnded(activeDuration);
                }
            });
            SessionModel sessionModel4 = this.session;
            Intrinsics.checkNotNull(sessionModel4);
            sessionModel4.setActiveDuration(0L);
        }
    }

    @Override // com.onesignal.core.internal.application.IApplicationLifecycleHandler
    public void onFocus(boolean firedOnSubscribe) throws Throwable {
        Logging.log(LogLevel.DEBUG, "SessionService.onFocus() - fired from start: " + firedOnSubscribe);
        if (!this.hasFocused) {
            this.hasFocused = true;
            endSession();
        }
        SessionModel sessionModel = this.session;
        Intrinsics.checkNotNull(sessionModel);
        if (!sessionModel.isValid()) {
            this.shouldFireOnSubscribe = firedOnSubscribe;
            SessionModel sessionModel2 = this.session;
            Intrinsics.checkNotNull(sessionModel2);
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
            sessionModel2.setSessionId(string);
            SessionModel sessionModel3 = this.session;
            Intrinsics.checkNotNull(sessionModel3);
            sessionModel3.setStartTime(this._time.getCurrentTimeMillis());
            SessionModel sessionModel4 = this.session;
            Intrinsics.checkNotNull(sessionModel4);
            SessionModel sessionModel5 = this.session;
            Intrinsics.checkNotNull(sessionModel5);
            sessionModel4.setFocusTime(sessionModel5.getStartTime());
            SessionModel sessionModel6 = this.session;
            Intrinsics.checkNotNull(sessionModel6);
            sessionModel6.setValid(true);
            StringBuilder sbAppend = new StringBuilder().append("SessionService: New session started at ");
            SessionModel sessionModel7 = this.session;
            Intrinsics.checkNotNull(sessionModel7);
            Logging.debug$default(sbAppend.append(sessionModel7.getStartTime()).toString(), null, 2, null);
            this.sessionLifeCycleNotifier.fire(new Function1<ISessionLifecycleHandler, Unit>() { // from class: com.onesignal.session.internal.session.impl.SessionService.onFocus.1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((ISessionLifecycleHandler) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(ISessionLifecycleHandler it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.onSessionStarted();
                }
            });
            return;
        }
        SessionModel sessionModel8 = this.session;
        Intrinsics.checkNotNull(sessionModel8);
        sessionModel8.setFocusTime(this._time.getCurrentTimeMillis());
        this.sessionLifeCycleNotifier.fire(new Function1<ISessionLifecycleHandler, Unit>() { // from class: com.onesignal.session.internal.session.impl.SessionService.onFocus.2
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((ISessionLifecycleHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(ISessionLifecycleHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onSessionActive();
            }
        });
    }

    @Override // com.onesignal.core.internal.application.IApplicationLifecycleHandler
    public void onUnfocused() throws Throwable {
        long currentTimeMillis = this._time.getCurrentTimeMillis();
        SessionModel sessionModel = this.session;
        Intrinsics.checkNotNull(sessionModel);
        long dt = currentTimeMillis - sessionModel.getFocusTime();
        SessionModel sessionModel2 = this.session;
        Intrinsics.checkNotNull(sessionModel2);
        sessionModel2.setActiveDuration(sessionModel2.getActiveDuration() + dt);
        LogLevel logLevel = LogLevel.DEBUG;
        StringBuilder sbAppend = new StringBuilder().append("SessionService.onUnfocused adding time ").append(dt).append(" for total: ");
        SessionModel sessionModel3 = this.session;
        Intrinsics.checkNotNull(sessionModel3);
        Logging.log(logLevel, sbAppend.append(sessionModel3.getActiveDuration()).toString());
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public void subscribe(ISessionLifecycleHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.sessionLifeCycleNotifier.subscribe(handler);
        if (this.shouldFireOnSubscribe) {
            handler.onSessionStarted();
        }
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public void unsubscribe(ISessionLifecycleHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.sessionLifeCycleNotifier.unsubscribe(handler);
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public boolean getHasSubscribers() {
        return this.sessionLifeCycleNotifier.getHasSubscribers();
    }
}
