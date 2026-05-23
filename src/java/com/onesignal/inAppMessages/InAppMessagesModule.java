package com.onesignal.inAppMessages;

import com.onesignal.common.modules.IModule;
import com.onesignal.common.services.ServiceBuilder;
import com.onesignal.common.services.ServiceRegistration;
import com.onesignal.core.internal.startup.IBootstrapService;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.inAppMessages.internal.InAppMessagesManager;
import com.onesignal.inAppMessages.internal.backend.IInAppBackendService;
import com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService;
import com.onesignal.inAppMessages.internal.display.IInAppDisplayer;
import com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer;
import com.onesignal.inAppMessages.internal.hydrators.InAppHydrator;
import com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService;
import com.onesignal.inAppMessages.internal.lifecycle.impl.IAMLifecycleService;
import com.onesignal.inAppMessages.internal.preferences.IInAppPreferencesController;
import com.onesignal.inAppMessages.internal.preferences.impl.InAppPreferencesController;
import com.onesignal.inAppMessages.internal.preview.InAppMessagePreviewHandler;
import com.onesignal.inAppMessages.internal.prompt.IInAppMessagePromptFactory;
import com.onesignal.inAppMessages.internal.prompt.impl.InAppMessagePromptFactory;
import com.onesignal.inAppMessages.internal.repositories.IInAppRepository;
import com.onesignal.inAppMessages.internal.repositories.impl.InAppRepository;
import com.onesignal.inAppMessages.internal.state.InAppStateService;
import com.onesignal.inAppMessages.internal.triggers.ITriggerController;
import com.onesignal.inAppMessages.internal.triggers.TriggerModelStore;
import com.onesignal.inAppMessages.internal.triggers.impl.DynamicTriggerController;
import com.onesignal.inAppMessages.internal.triggers.impl.TriggerController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InAppMessagesModule.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/onesignal/inAppMessages/InAppMessagesModule;", "Lcom/onesignal/common/modules/IModule;", "()V", "register", "", "builder", "Lcom/onesignal/common/services/ServiceBuilder;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InAppMessagesModule implements IModule {
    @Override // com.onesignal.common.modules.IModule
    public void register(ServiceBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        ServiceRegistration this_$iv = builder.register(InAppStateService.class);
        this_$iv.provides(InAppStateService.class);
        ServiceRegistration this_$iv2 = builder.register(InAppHydrator.class);
        this_$iv2.provides(InAppHydrator.class);
        ServiceRegistration this_$iv3 = builder.register(InAppPreferencesController.class);
        this_$iv3.provides(IInAppPreferencesController.class);
        ServiceRegistration this_$iv4 = builder.register(InAppRepository.class);
        this_$iv4.provides(IInAppRepository.class);
        ServiceRegistration this_$iv5 = builder.register(InAppBackendService.class);
        this_$iv5.provides(IInAppBackendService.class);
        ServiceRegistration this_$iv6 = builder.register(IAMLifecycleService.class);
        this_$iv6.provides(IInAppLifecycleService.class);
        ServiceRegistration this_$iv7 = builder.register(TriggerModelStore.class);
        this_$iv7.provides(TriggerModelStore.class);
        ServiceRegistration this_$iv8 = builder.register(TriggerController.class);
        this_$iv8.provides(ITriggerController.class);
        ServiceRegistration this_$iv9 = builder.register(DynamicTriggerController.class);
        this_$iv9.provides(DynamicTriggerController.class);
        ServiceRegistration this_$iv10 = builder.register(InAppDisplayer.class);
        this_$iv10.provides(IInAppDisplayer.class);
        ServiceRegistration this_$iv11 = builder.register(InAppMessagePreviewHandler.class);
        this_$iv11.provides(IBootstrapService.class);
        ServiceRegistration this_$iv12 = builder.register(InAppMessagePromptFactory.class);
        this_$iv12.provides(IInAppMessagePromptFactory.class);
        ServiceRegistration this_$iv13 = builder.register(InAppMessagesManager.class);
        this_$iv13.provides(IInAppMessagesManager.class).provides(IStartableService.class);
    }
}
