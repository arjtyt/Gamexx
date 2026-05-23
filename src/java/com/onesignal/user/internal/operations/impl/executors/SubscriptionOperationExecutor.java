package com.onesignal.user.internal.operations.impl.executors;

import com.onesignal.common.NetworkUtils;
import com.onesignal.common.consistency.models.IConsistencyManager;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.config.ConfigModelStore;
import com.onesignal.core.internal.device.IDeviceService;
import com.onesignal.core.internal.operations.ExecutionResponse;
import com.onesignal.core.internal.operations.IOperationExecutor;
import com.onesignal.core.internal.operations.Operation;
import com.onesignal.debug.LogLevel;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.user.internal.backend.ISubscriptionBackendService;
import com.onesignal.user.internal.backend.SubscriptionObjectType;
import com.onesignal.user.internal.builduser.IRebuildUserService;
import com.onesignal.user.internal.operations.CreateSubscriptionOperation;
import com.onesignal.user.internal.operations.DeleteSubscriptionOperation;
import com.onesignal.user.internal.operations.TransferSubscriptionOperation;
import com.onesignal.user.internal.operations.UpdateSubscriptionOperation;
import com.onesignal.user.internal.operations.impl.states.NewRecordsState;
import com.onesignal.user.internal.subscriptions.SubscriptionModelStore;
import com.onesignal.user.internal.subscriptions.SubscriptionType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubscriptionOperationExecutor.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 /2\u00020\u0001:\u0001/BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J'\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020 0\u0014H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0019\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010%J\u001f\u0010&\u001a\u00020\u001d2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020 0\u0014H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010'J\u0019\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020*H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010+J'\u0010,\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020-2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020 0\u0014H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010.R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Lcom/onesignal/user/internal/operations/impl/executors/SubscriptionOperationExecutor;", "Lcom/onesignal/core/internal/operations/IOperationExecutor;", "_subscriptionBackend", "Lcom/onesignal/user/internal/backend/ISubscriptionBackendService;", "_deviceService", "Lcom/onesignal/core/internal/device/IDeviceService;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_subscriptionModelStore", "Lcom/onesignal/user/internal/subscriptions/SubscriptionModelStore;", "_configModelStore", "Lcom/onesignal/core/internal/config/ConfigModelStore;", "_buildUserService", "Lcom/onesignal/user/internal/builduser/IRebuildUserService;", "_newRecordState", "Lcom/onesignal/user/internal/operations/impl/states/NewRecordsState;", "_consistencyManager", "Lcom/onesignal/common/consistency/models/IConsistencyManager;", "(Lcom/onesignal/user/internal/backend/ISubscriptionBackendService;Lcom/onesignal/core/internal/device/IDeviceService;Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/user/internal/subscriptions/SubscriptionModelStore;Lcom/onesignal/core/internal/config/ConfigModelStore;Lcom/onesignal/user/internal/builduser/IRebuildUserService;Lcom/onesignal/user/internal/operations/impl/states/NewRecordsState;Lcom/onesignal/common/consistency/models/IConsistencyManager;)V", "operations", "", "", "getOperations", "()Ljava/util/List;", "convert", "Lcom/onesignal/user/internal/backend/SubscriptionObjectType;", "subscriptionType", "Lcom/onesignal/user/internal/subscriptions/SubscriptionType;", "createSubscription", "Lcom/onesignal/core/internal/operations/ExecutionResponse;", "createOperation", "Lcom/onesignal/user/internal/operations/CreateSubscriptionOperation;", "Lcom/onesignal/core/internal/operations/Operation;", "(Lcom/onesignal/user/internal/operations/CreateSubscriptionOperation;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSubscription", "op", "Lcom/onesignal/user/internal/operations/DeleteSubscriptionOperation;", "(Lcom/onesignal/user/internal/operations/DeleteSubscriptionOperation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "execute", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transferSubscription", "startingOperation", "Lcom/onesignal/user/internal/operations/TransferSubscriptionOperation;", "(Lcom/onesignal/user/internal/operations/TransferSubscriptionOperation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSubscription", "Lcom/onesignal/user/internal/operations/UpdateSubscriptionOperation;", "(Lcom/onesignal/user/internal/operations/UpdateSubscriptionOperation;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubscriptionOperationExecutor implements IOperationExecutor {
    public static final String CREATE_SUBSCRIPTION = "create-subscription";
    public static final Companion Companion = new Companion(null);
    public static final String DELETE_SUBSCRIPTION = "delete-subscription";
    public static final String TRANSFER_SUBSCRIPTION = "transfer-subscription";
    public static final String UPDATE_SUBSCRIPTION = "update-subscription";
    private final IApplicationService _applicationService;
    private final IRebuildUserService _buildUserService;
    private final ConfigModelStore _configModelStore;
    private final IConsistencyManager _consistencyManager;
    private final IDeviceService _deviceService;
    private final NewRecordsState _newRecordState;
    private final ISubscriptionBackendService _subscriptionBackend;
    private final SubscriptionModelStore _subscriptionModelStore;

    /* JADX INFO: compiled from: SubscriptionOperationExecutor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[NetworkUtils.ResponseStatusType.values().length];
            iArr[NetworkUtils.ResponseStatusType.RETRYABLE.ordinal()] = 1;
            iArr[NetworkUtils.ResponseStatusType.CONFLICT.ordinal()] = 2;
            iArr[NetworkUtils.ResponseStatusType.INVALID.ordinal()] = 3;
            iArr[NetworkUtils.ResponseStatusType.UNAUTHORIZED.ordinal()] = 4;
            iArr[NetworkUtils.ResponseStatusType.MISSING.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[SubscriptionType.values().length];
            iArr2[SubscriptionType.SMS.ordinal()] = 1;
            iArr2[SubscriptionType.EMAIL.ordinal()] = 2;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor$createSubscription$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubscriptionOperationExecutor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor", f = "SubscriptionOperationExecutor.kt", i = {0, 0, 1, 1, 1, 2, 2, 2}, l = {109, 120, 122}, m = "createSubscription", n = {"this", "createOperation", "this", "createOperation", "backendSubscriptionId", "this", "createOperation", "backendSubscriptionId"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionOperationExecutor.this.createSubscription(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor$deleteSubscription$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubscriptionOperationExecutor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor", f = "SubscriptionOperationExecutor.kt", i = {0, 0}, l = {277}, m = "deleteSubscription", n = {"this", "op"}, s = {"L$0", "L$1"})
    static final class C01671 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01671(Continuation<? super C01671> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionOperationExecutor.this.deleteSubscription(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor$transferSubscription$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubscriptionOperationExecutor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor", f = "SubscriptionOperationExecutor.kt", i = {}, l = {241}, m = "transferSubscription", n = {}, s = {})
    static final class C01681 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01681(Continuation<? super C01681> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionOperationExecutor.this.transferSubscription(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor$updateSubscription$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubscriptionOperationExecutor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor", f = "SubscriptionOperationExecutor.kt", i = {0, 0, 0, 1, 1, 2, 2}, l = {191, 194, 196}, m = "updateSubscription", n = {"this", "startingOperation", "lastOperation", "this", "lastOperation", "this", "lastOperation"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1"})
    static final class C01691 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C01691(Continuation<? super C01691> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionOperationExecutor.this.updateSubscription(null, null, (Continuation) this);
        }
    }

    public SubscriptionOperationExecutor(ISubscriptionBackendService _subscriptionBackend, IDeviceService _deviceService, IApplicationService _applicationService, SubscriptionModelStore _subscriptionModelStore, ConfigModelStore _configModelStore, IRebuildUserService _buildUserService, NewRecordsState _newRecordState, IConsistencyManager _consistencyManager) {
        Intrinsics.checkNotNullParameter(_subscriptionBackend, "_subscriptionBackend");
        Intrinsics.checkNotNullParameter(_deviceService, "_deviceService");
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_subscriptionModelStore, "_subscriptionModelStore");
        Intrinsics.checkNotNullParameter(_configModelStore, "_configModelStore");
        Intrinsics.checkNotNullParameter(_buildUserService, "_buildUserService");
        Intrinsics.checkNotNullParameter(_newRecordState, "_newRecordState");
        Intrinsics.checkNotNullParameter(_consistencyManager, "_consistencyManager");
        this._subscriptionBackend = _subscriptionBackend;
        this._deviceService = _deviceService;
        this._applicationService = _applicationService;
        this._subscriptionModelStore = _subscriptionModelStore;
        this._configModelStore = _configModelStore;
        this._buildUserService = _buildUserService;
        this._newRecordState = _newRecordState;
        this._consistencyManager = _consistencyManager;
    }

    @Override // com.onesignal.core.internal.operations.IOperationExecutor
    public List<String> getOperations() {
        return CollectionsKt.listOf(new String[]{CREATE_SUBSCRIPTION, UPDATE_SUBSCRIPTION, DELETE_SUBSCRIPTION, TRANSFER_SUBSCRIPTION});
    }

    @Override // com.onesignal.core.internal.operations.IOperationExecutor
    public Object execute(List<? extends Operation> list, Continuation<? super ExecutionResponse> continuation) throws Exception {
        Logging.log(LogLevel.DEBUG, "SubscriptionOperationExecutor(operations: " + list + ')');
        Operation startingOp = (Operation) CollectionsKt.first(list);
        if (startingOp instanceof CreateSubscriptionOperation) {
            return createSubscription((CreateSubscriptionOperation) startingOp, list, continuation);
        }
        List<? extends Operation> $this$any$iv = list;
        boolean z = false;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object element$iv = it.next();
                Operation it2 = (Operation) element$iv;
                if (it2 instanceof DeleteSubscriptionOperation) {
                    z = true;
                    break;
                }
            }
        }
        if (!z) {
            if (startingOp instanceof UpdateSubscriptionOperation) {
                return updateSubscription((UpdateSubscriptionOperation) startingOp, list, continuation);
            }
            if (!(startingOp instanceof TransferSubscriptionOperation)) {
                throw new Exception("Unrecognized operation: " + startingOp);
            }
            if (list.size() <= 1) {
                return transferSubscription((TransferSubscriptionOperation) startingOp, continuation);
            }
            throw new Exception("TransferSubscriptionOperation only supports one operation! Attempted operations:\n" + list);
        }
        if (list.size() > 1) {
            throw new Exception("Only supports one operation! Attempted operations:\n" + list);
        }
        List<? extends Operation> $this$filterIsInstance$iv = list;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof DeleteSubscriptionOperation) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List deleteSubOps = (List) destination$iv$iv;
        return deleteSubscription((DeleteSubscriptionOperation) CollectionsKt.first(deleteSubOps), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x016b A[Catch: BackendException -> 0x0218, TryCatch #1 {BackendException -> 0x0218, blocks: (B:66:0x0167, B:68:0x016b, B:70:0x017a, B:72:0x0189, B:77:0x01a7), top: B:116:0x0167 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x017a A[Catch: BackendException -> 0x0218, TryCatch #1 {BackendException -> 0x0218, blocks: (B:66:0x0167, B:68:0x016b, B:70:0x017a, B:72:0x0189, B:77:0x01a7), top: B:116:0x0167 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01cc A[Catch: BackendException -> 0x0064, TryCatch #0 {BackendException -> 0x0064, blocks: (B:13:0x003f, B:82:0x01be, B:84:0x01cc, B:85:0x01dc, B:87:0x01f2, B:88:0x01fd, B:16:0x0050, B:19:0x005d), top: B:114:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01f2 A[Catch: BackendException -> 0x0064, TryCatch #0 {BackendException -> 0x0064, blocks: (B:13:0x003f, B:82:0x01be, B:84:0x01cc, B:85:0x01dc, B:87:0x01f2, B:88:0x01fd, B:16:0x0050, B:19:0x005d), top: B:114:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x023a  */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v35, types: [com.onesignal.user.internal.operations.CreateSubscriptionOperation] */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v41 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object createSubscription(com.onesignal.user.internal.operations.CreateSubscriptionOperation r26, java.util.List<? extends com.onesignal.core.internal.operations.Operation> r27, kotlin.coroutines.Continuation<? super com.onesignal.core.internal.operations.ExecutionResponse> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 740
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor.createSubscription(com.onesignal.user.internal.operations.CreateSubscriptionOperation, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00fc A[Catch: BackendException -> 0x0063, TryCatch #0 {BackendException -> 0x0063, blocks: (B:19:0x005d, B:28:0x00f7, B:30:0x00fc, B:35:0x0118), top: B:66:0x005d }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0118 A[Catch: BackendException -> 0x0063, TRY_LEAVE, TryCatch #0 {BackendException -> 0x0063, blocks: (B:19:0x005d, B:28:0x00f7, B:30:0x00fc, B:35:0x0118), top: B:66:0x005d }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    /* JADX WARN: Type inference failed for: r4v8, types: [com.onesignal.user.internal.operations.UpdateSubscriptionOperation] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object updateSubscription(com.onesignal.user.internal.operations.UpdateSubscriptionOperation r24, java.util.List<? extends com.onesignal.core.internal.operations.Operation> r25, kotlin.coroutines.Continuation<? super com.onesignal.core.internal.operations.ExecutionResponse> r26) {
        /*
            Method dump skipped, instruction units count: 540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor.updateSubscription(com.onesignal.user.internal.operations.UpdateSubscriptionOperation, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [com.onesignal.core.internal.operations.ExecutionResponse, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object transferSubscription(com.onesignal.user.internal.operations.TransferSubscriptionOperation r19, kotlin.coroutines.Continuation<? super com.onesignal.core.internal.operations.ExecutionResponse> r20) {
        /*
            r18 = this;
            r0 = r20
            boolean r1 = r0 instanceof com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor.C01681
            if (r1 == 0) goto L18
            r1 = r0
            com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor$transferSubscription$1 r1 = (com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor.C01681) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L18
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r18
            goto L1f
        L18:
            com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor$transferSubscription$1 r1 = new com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor$transferSubscription$1
            r2 = r18
            r1.<init>(r0)
        L1f:
            r8 = r1
            java.lang.Object r1 = r8.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r8.label
            r9 = 1
            switch(r3) {
                case 0: goto L3a;
                case 1: goto L34;
                default: goto L2c;
            }
        L2c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L34:
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: com.onesignal.common.exceptions.BackendException -> L38
            goto L60
        L38:
            r0 = move-exception
            goto L6f
        L3a:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r18
            r4 = r19
            r5 = r3
            com.onesignal.user.internal.backend.ISubscriptionBackendService r3 = r5._subscriptionBackend     // Catch: com.onesignal.common.exceptions.BackendException -> L38
            r5 = r4
            java.lang.String r4 = r5.getAppId()     // Catch: com.onesignal.common.exceptions.BackendException -> L38
            r6 = r5
            java.lang.String r5 = r6.getSubscriptionId()     // Catch: com.onesignal.common.exceptions.BackendException -> L38
            r7 = r6
            java.lang.String r6 = "onesignal_id"
            r10 = r7
            java.lang.String r7 = r10.getOnesignalId()     // Catch: com.onesignal.common.exceptions.BackendException -> L38
            r8.label = r9     // Catch: com.onesignal.common.exceptions.BackendException -> L38
            java.lang.Object r3 = r3.transferSubscription(r4, r5, r6, r7, r8)     // Catch: com.onesignal.common.exceptions.BackendException -> L38
            if (r3 != r0) goto L60
            return r0
        L60:
            com.onesignal.core.internal.operations.ExecutionResponse r9 = new com.onesignal.core.internal.operations.ExecutionResponse
            com.onesignal.core.internal.operations.ExecutionResult r10 = com.onesignal.core.internal.operations.ExecutionResult.SUCCESS
            r14 = 14
            r15 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r9.<init>(r10, r11, r12, r13, r14, r15)
            return r9
        L6f:
            com.onesignal.common.NetworkUtils r3 = com.onesignal.common.NetworkUtils.INSTANCE
            int r4 = r0.getStatusCode()
            com.onesignal.common.NetworkUtils$ResponseStatusType r3 = r3.getResponseStatusType(r4)
            int[] r4 = com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor.WhenMappings.$EnumSwitchMapping$0
            int r3 = r3.ordinal()
            r3 = r4[r3]
            if (r3 != r9) goto L95
            com.onesignal.core.internal.operations.ExecutionResponse r10 = new com.onesignal.core.internal.operations.ExecutionResponse
            com.onesignal.core.internal.operations.ExecutionResult r11 = com.onesignal.core.internal.operations.ExecutionResult.FAIL_RETRY
            java.lang.Integer r14 = r0.getRetryAfterSeconds()
            r15 = 6
            r16 = 0
            r12 = 0
            r13 = 0
            r10.<init>(r11, r12, r13, r14, r15, r16)
            goto La4
        L95:
            com.onesignal.core.internal.operations.ExecutionResponse r11 = new com.onesignal.core.internal.operations.ExecutionResponse
            com.onesignal.core.internal.operations.ExecutionResult r12 = com.onesignal.core.internal.operations.ExecutionResult.FAIL_NORETRY
            r16 = 14
            r17 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r11.<init>(r12, r13, r14, r15, r16, r17)
            r10 = r11
        La4:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor.transferSubscription(com.onesignal.user.internal.operations.TransferSubscriptionOperation, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final SubscriptionObjectType convert(SubscriptionType subscriptionType) {
        switch (WhenMappings.$EnumSwitchMapping$1[subscriptionType.ordinal()]) {
            case 1:
                return SubscriptionObjectType.SMS;
            case 2:
                return SubscriptionObjectType.EMAIL;
            default:
                return SubscriptionObjectType.Companion.fromDeviceType(this._deviceService.getDeviceType());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v7, types: [com.onesignal.user.internal.operations.DeleteSubscriptionOperation] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteSubscription(com.onesignal.user.internal.operations.DeleteSubscriptionOperation r17, kotlin.coroutines.Continuation<? super com.onesignal.core.internal.operations.ExecutionResponse> r18) {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor.deleteSubscription(com.onesignal.user.internal.operations.DeleteSubscriptionOperation, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: compiled from: SubscriptionOperationExecutor.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onesignal/user/internal/operations/impl/executors/SubscriptionOperationExecutor$Companion;", "", "()V", "CREATE_SUBSCRIPTION", "", "DELETE_SUBSCRIPTION", "TRANSFER_SUBSCRIPTION", "UPDATE_SUBSCRIPTION", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
