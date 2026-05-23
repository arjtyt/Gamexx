package com.onesignal.core.internal.config.impl;

import com.onesignal.common.modeling.ISingletonModelStoreChangeHandler;
import com.onesignal.common.modeling.ModelChangeTags;
import com.onesignal.common.modeling.ModelChangedArgs;
import com.onesignal.common.threading.ThreadUtilsKt;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.backend.IParamsBackendService;
import com.onesignal.core.internal.config.ConfigModel;
import com.onesignal.core.internal.config.ConfigModelStore;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.user.internal.subscriptions.ISubscriptionManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConfigModelStoreListener.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00152\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0015B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/onesignal/core/internal/config/impl/ConfigModelStoreListener;", "Lcom/onesignal/core/internal/startup/IStartableService;", "Lcom/onesignal/common/modeling/ISingletonModelStoreChangeHandler;", "Lcom/onesignal/core/internal/config/ConfigModel;", "_configModelStore", "Lcom/onesignal/core/internal/config/ConfigModelStore;", "_paramsBackendService", "Lcom/onesignal/core/internal/backend/IParamsBackendService;", "_subscriptionManager", "Lcom/onesignal/user/internal/subscriptions/ISubscriptionManager;", "(Lcom/onesignal/core/internal/config/ConfigModelStore;Lcom/onesignal/core/internal/backend/IParamsBackendService;Lcom/onesignal/user/internal/subscriptions/ISubscriptionManager;)V", "fetchParams", "", "onModelReplaced", "model", "tag", "", "onModelUpdated", "args", "Lcom/onesignal/common/modeling/ModelChangedArgs;", "start", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ConfigModelStoreListener implements IStartableService, ISingletonModelStoreChangeHandler<ConfigModel> {
    public static final Companion Companion = new Companion(null);
    private static final int INCREASE_BETWEEN_RETRIES = 10000;
    private static final int MAX_WAIT_BETWEEN_RETRIES = 90000;
    private static final int MIN_WAIT_BETWEEN_RETRIES = 30000;
    private final ConfigModelStore _configModelStore;
    private final IParamsBackendService _paramsBackendService;
    private final ISubscriptionManager _subscriptionManager;

    public ConfigModelStoreListener(ConfigModelStore _configModelStore, IParamsBackendService _paramsBackendService, ISubscriptionManager _subscriptionManager) {
        Intrinsics.checkNotNullParameter(_configModelStore, "_configModelStore");
        Intrinsics.checkNotNullParameter(_paramsBackendService, "_paramsBackendService");
        Intrinsics.checkNotNullParameter(_subscriptionManager, "_subscriptionManager");
        this._configModelStore = _configModelStore;
        this._paramsBackendService = _paramsBackendService;
        this._subscriptionManager = _subscriptionManager;
    }

    @Override // com.onesignal.core.internal.startup.IStartableService
    public void start() {
        this._configModelStore.subscribe((ISingletonModelStoreChangeHandler) this);
        fetchParams();
    }

    @Override // com.onesignal.common.modeling.ISingletonModelStoreChangeHandler
    public void onModelUpdated(ModelChangedArgs args, String tag) {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (!Intrinsics.areEqual(args.getProperty(), "appId")) {
            return;
        }
        fetchParams();
    }

    @Override // com.onesignal.common.modeling.ISingletonModelStoreChangeHandler
    public void onModelReplaced(ConfigModel model, String tag) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (!Intrinsics.areEqual(tag, ModelChangeTags.NORMAL)) {
            return;
        }
        fetchParams();
    }

    private final void fetchParams() {
        String appId = this._configModelStore.getModel().getAppId();
        if (appId.length() == 0) {
            return;
        }
        ThreadUtilsKt.suspendifyOnThread$default(0, new AnonymousClass1(appId, this, null), 1, null);
    }

    /* JADX INFO: renamed from: com.onesignal.core.internal.config.impl.ConfigModelStoreListener$fetchParams$1, reason: invalid class name */
    /* JADX INFO: compiled from: ConfigModelStoreListener.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.config.impl.ConfigModelStoreListener$fetchParams$1", f = "ConfigModelStoreListener.kt", i = {0, 0, 1, 1}, l = {70, 120}, m = "invokeSuspend", n = {"androidParamsRetries", "success", "androidParamsRetries", "success"}, s = {"I$0", "I$1", "I$0", "I$1"})
    static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ String $appId;
        int I$0;
        int I$1;
        int label;
        final /* synthetic */ ConfigModelStoreListener this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, ConfigModelStoreListener configModelStoreListener, Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
            this.$appId = str;
            this.this$0 = configModelStoreListener;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass1(this.$appId, this.this$0, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:100:0x01dd A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:103:0x01f5 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:106:0x020d A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:109:0x0225 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:116:0x0240 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:123:0x025b A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:135:0x0294  */
        /* JADX WARN: Removed duplicated region for block: B:137:0x029c  */
        /* JADX WARN: Removed duplicated region for block: B:145:0x02da  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0072  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x008a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x008b  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00ef A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0102 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0115 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0128 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:52:0x013b A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:59:0x014e A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0161 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0174 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:80:0x0187 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:87:0x019a A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:94:0x01b1 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:97:0x01c5 A[Catch: BackendException -> 0x0282, TryCatch #0 {BackendException -> 0x0282, blocks: (B:22:0x0092, B:24:0x00ef, B:28:0x00f9, B:29:0x00fc, B:31:0x0102, B:35:0x010c, B:36:0x010f, B:38:0x0115, B:42:0x011f, B:43:0x0122, B:45:0x0128, B:49:0x0132, B:50:0x0135, B:52:0x013b, B:56:0x0145, B:57:0x0148, B:59:0x014e, B:63:0x0158, B:64:0x015b, B:66:0x0161, B:70:0x016b, B:71:0x016e, B:73:0x0174, B:77:0x017e, B:78:0x0181, B:80:0x0187, B:84:0x0191, B:85:0x0194, B:87:0x019a, B:91:0x01a4, B:92:0x01ab, B:94:0x01b1, B:95:0x01bb, B:97:0x01c5, B:98:0x01d3, B:100:0x01dd, B:101:0x01eb, B:103:0x01f5, B:104:0x0203, B:106:0x020d, B:107:0x021b, B:109:0x0225, B:113:0x0233, B:114:0x0236, B:116:0x0240, B:120:0x024e, B:121:0x0251, B:123:0x025b, B:127:0x0269, B:128:0x026c), top: B:147:0x0092 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:129:0x027a -> B:144:0x02d8). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:141:0x02d4 -> B:143:0x02d7). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 744
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.core.internal.config.impl.ConfigModelStoreListener.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: compiled from: ConfigModelStoreListener.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onesignal/core/internal/config/impl/ConfigModelStoreListener$Companion;", "", "()V", "INCREASE_BETWEEN_RETRIES", "", "MAX_WAIT_BETWEEN_RETRIES", "MIN_WAIT_BETWEEN_RETRIES", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
