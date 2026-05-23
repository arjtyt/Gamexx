package com.onesignal.session;

import com.onesignal.common.modules.IModule;
import com.onesignal.common.services.ServiceBuilder;
import com.onesignal.common.services.ServiceRegistration;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.background.IBackgroundService;
import com.onesignal.core.internal.startup.IBootstrapService;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.session.internal.SessionManager;
import com.onesignal.session.internal.influence.IInfluenceManager;
import com.onesignal.session.internal.influence.impl.InfluenceManager;
import com.onesignal.session.internal.outcomes.IOutcomeEventsController;
import com.onesignal.session.internal.outcomes.impl.IOutcomeEventsBackendService;
import com.onesignal.session.internal.outcomes.impl.IOutcomeEventsPreferences;
import com.onesignal.session.internal.outcomes.impl.IOutcomeEventsRepository;
import com.onesignal.session.internal.outcomes.impl.OutcomeEventsBackendService;
import com.onesignal.session.internal.outcomes.impl.OutcomeEventsController;
import com.onesignal.session.internal.outcomes.impl.OutcomeEventsPreferences;
import com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository;
import com.onesignal.session.internal.session.ISessionService;
import com.onesignal.session.internal.session.SessionModelStore;
import com.onesignal.session.internal.session.impl.SessionListener;
import com.onesignal.session.internal.session.impl.SessionService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SessionModule.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/onesignal/session/SessionModule;", "Lcom/onesignal/common/modules/IModule;", "()V", "register", "", "builder", "Lcom/onesignal/common/services/ServiceBuilder;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SessionModule implements IModule {
    @Override // com.onesignal.common.modules.IModule
    public void register(ServiceBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        ServiceRegistration this_$iv = builder.register(OutcomeEventsPreferences.class);
        this_$iv.provides(IOutcomeEventsPreferences.class);
        ServiceRegistration this_$iv2 = builder.register(OutcomeEventsRepository.class);
        this_$iv2.provides(IOutcomeEventsRepository.class);
        ServiceRegistration this_$iv3 = builder.register(OutcomeEventsBackendService.class);
        this_$iv3.provides(IOutcomeEventsBackendService.class);
        ServiceRegistration this_$iv4 = builder.register(OutcomeEventsController.class);
        this_$iv4.provides(IOutcomeEventsController.class).provides(IStartableService.class);
        ServiceRegistration this_$iv5 = builder.register(InfluenceManager.class);
        this_$iv5.provides(IInfluenceManager.class);
        ServiceRegistration this_$iv6 = builder.register(SessionModelStore.class);
        this_$iv6.provides(SessionModelStore.class);
        ServiceRegistration this_$iv7 = builder.register(SessionService.class);
        this_$iv7.provides(ISessionService.class).provides(IStartableService.class).provides(IBackgroundService.class).provides(IBootstrapService.class);
        ServiceRegistration this_$iv8 = builder.register(SessionListener.class);
        this_$iv8.provides(IStartableService.class);
        ServiceRegistration this_$iv9 = builder.register(SessionManager.class);
        this_$iv9.provides(ISessionManager.class);
    }
}
