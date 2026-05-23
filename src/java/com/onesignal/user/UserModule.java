package com.onesignal.user;

import com.onesignal.common.consistency.impl.ConsistencyManager;
import com.onesignal.common.consistency.models.IConsistencyManager;
import com.onesignal.common.modules.IModule;
import com.onesignal.common.services.ServiceBuilder;
import com.onesignal.common.services.ServiceRegistration;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.operations.IOperationExecutor;
import com.onesignal.core.internal.startup.IBootstrapService;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.user.internal.UserManager;
import com.onesignal.user.internal.backend.IIdentityBackendService;
import com.onesignal.user.internal.backend.ISubscriptionBackendService;
import com.onesignal.user.internal.backend.IUserBackendService;
import com.onesignal.user.internal.backend.impl.IdentityBackendService;
import com.onesignal.user.internal.backend.impl.SubscriptionBackendService;
import com.onesignal.user.internal.backend.impl.UserBackendService;
import com.onesignal.user.internal.builduser.IRebuildUserService;
import com.onesignal.user.internal.builduser.impl.RebuildUserService;
import com.onesignal.user.internal.identity.IdentityModelStore;
import com.onesignal.user.internal.migrations.RecoverConfigPushSubscription;
import com.onesignal.user.internal.migrations.RecoverFromDroppedLoginBug;
import com.onesignal.user.internal.operations.impl.executors.IdentityOperationExecutor;
import com.onesignal.user.internal.operations.impl.executors.LoginUserFromSubscriptionOperationExecutor;
import com.onesignal.user.internal.operations.impl.executors.LoginUserOperationExecutor;
import com.onesignal.user.internal.operations.impl.executors.RefreshUserOperationExecutor;
import com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor;
import com.onesignal.user.internal.operations.impl.executors.UpdateUserOperationExecutor;
import com.onesignal.user.internal.operations.impl.listeners.IdentityModelStoreListener;
import com.onesignal.user.internal.operations.impl.listeners.PropertiesModelStoreListener;
import com.onesignal.user.internal.operations.impl.listeners.SubscriptionModelStoreListener;
import com.onesignal.user.internal.operations.impl.states.NewRecordsState;
import com.onesignal.user.internal.properties.PropertiesModelStore;
import com.onesignal.user.internal.service.UserRefreshService;
import com.onesignal.user.internal.subscriptions.ISubscriptionManager;
import com.onesignal.user.internal.subscriptions.SubscriptionModelStore;
import com.onesignal.user.internal.subscriptions.impl.SubscriptionManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserModule.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/onesignal/user/UserModule;", "Lcom/onesignal/common/modules/IModule;", "()V", "register", "", "builder", "Lcom/onesignal/common/services/ServiceBuilder;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class UserModule implements IModule {
    @Override // com.onesignal.common.modules.IModule
    public void register(ServiceBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        ServiceRegistration this_$iv = builder.register(ConsistencyManager.class);
        this_$iv.provides(IConsistencyManager.class);
        ServiceRegistration this_$iv2 = builder.register(PropertiesModelStore.class);
        this_$iv2.provides(PropertiesModelStore.class);
        ServiceRegistration this_$iv3 = builder.register(PropertiesModelStoreListener.class);
        this_$iv3.provides(IBootstrapService.class);
        ServiceRegistration this_$iv4 = builder.register(IdentityModelStore.class);
        this_$iv4.provides(IdentityModelStore.class);
        ServiceRegistration this_$iv5 = builder.register(IdentityModelStoreListener.class);
        this_$iv5.provides(IBootstrapService.class);
        ServiceRegistration this_$iv6 = builder.register(IdentityBackendService.class);
        this_$iv6.provides(IIdentityBackendService.class);
        ServiceRegistration this_$iv7 = builder.register(IdentityOperationExecutor.class);
        this_$iv7.provides(IdentityOperationExecutor.class).provides(IOperationExecutor.class);
        ServiceRegistration this_$iv8 = builder.register(SubscriptionModelStore.class);
        this_$iv8.provides(SubscriptionModelStore.class);
        ServiceRegistration this_$iv9 = builder.register(SubscriptionModelStoreListener.class);
        this_$iv9.provides(IBootstrapService.class);
        ServiceRegistration this_$iv10 = builder.register(SubscriptionBackendService.class);
        this_$iv10.provides(ISubscriptionBackendService.class);
        ServiceRegistration this_$iv11 = builder.register(SubscriptionOperationExecutor.class);
        this_$iv11.provides(SubscriptionOperationExecutor.class).provides(IOperationExecutor.class);
        ServiceRegistration this_$iv12 = builder.register(SubscriptionManager.class);
        this_$iv12.provides(ISubscriptionManager.class);
        ServiceRegistration this_$iv13 = builder.register(RebuildUserService.class);
        this_$iv13.provides(IRebuildUserService.class);
        ServiceRegistration this_$iv14 = builder.register(UserBackendService.class);
        this_$iv14.provides(IUserBackendService.class);
        ServiceRegistration this_$iv15 = builder.register(UpdateUserOperationExecutor.class);
        this_$iv15.provides(UpdateUserOperationExecutor.class).provides(IOperationExecutor.class);
        ServiceRegistration this_$iv16 = builder.register(LoginUserOperationExecutor.class);
        this_$iv16.provides(IOperationExecutor.class);
        ServiceRegistration this_$iv17 = builder.register(LoginUserFromSubscriptionOperationExecutor.class);
        this_$iv17.provides(IOperationExecutor.class);
        ServiceRegistration this_$iv18 = builder.register(RefreshUserOperationExecutor.class);
        this_$iv18.provides(IOperationExecutor.class);
        ServiceRegistration this_$iv19 = builder.register(UserManager.class);
        this_$iv19.provides(IUserManager.class);
        ServiceRegistration this_$iv20 = builder.register(UserRefreshService.class);
        this_$iv20.provides(IStartableService.class);
        ServiceRegistration this_$iv21 = builder.register(RecoverFromDroppedLoginBug.class);
        this_$iv21.provides(IStartableService.class);
        ServiceRegistration this_$iv22 = builder.register(RecoverConfigPushSubscription.class);
        this_$iv22.provides(IStartableService.class);
        ServiceRegistration this_$iv23 = builder.register(NewRecordsState.class);
        this_$iv23.provides(NewRecordsState.class);
    }
}
