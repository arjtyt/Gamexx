package com.onesignal.core.internal.operations.impl;

import com.onesignal.common.modeling.IModelStore;
import com.onesignal.common.threading.OSPrimaryCoroutineScope;
import com.onesignal.common.threading.WaiterWithValue;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.config.ConfigModelStore;
import com.onesignal.core.internal.operations.ExecutionResult;
import com.onesignal.core.internal.operations.GroupComparisonType;
import com.onesignal.core.internal.operations.IOperationExecutor;
import com.onesignal.core.internal.operations.IOperationRepo;
import com.onesignal.core.internal.operations.Operation;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.debug.LogLevel;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.inAppMessages.internal.display.impl.WebViewManager;
import com.onesignal.user.internal.operations.impl.states.NewRecordsState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: compiled from: OperationRepo.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u001d\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0002OPB3\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0011\u0010'\u001a\u00020\u001bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010(J \u0010)\u001a\u00020\u001d\"\b\b\u0000\u0010**\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u0002H*0-H\u0016J#\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u00020\u00122\b\u00100\u001a\u0004\u0018\u00010\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u00101J\u0019\u00102\u001a\u00020\u001b2\u0006\u00103\u001a\u000204H\u0086@ø\u0001\u0000¢\u0006\u0002\u00105J\u0018\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u00020+2\u0006\u00108\u001a\u00020\u001dH\u0016J!\u00109\u001a\u00020\u001d2\u0006\u00107\u001a\u00020+2\u0006\u00108\u001a\u00020\u001dH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010:J!\u0010;\u001a\u00020\u001b2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020 0\u0004H\u0080@ø\u0001\u0000¢\u0006\u0004\b=\u0010>J\b\u0010?\u001a\u00020\u001bH\u0016J\u0016\u0010@\u001a\b\u0012\u0004\u0012\u00020 0\u00042\u0006\u0010A\u001a\u00020 H\u0002J\u001d\u0010B\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u00042\u0006\u0010C\u001a\u00020\u0012H\u0000¢\u0006\u0002\bDJ1\u0010E\u001a\u00020\u001b2\u0006\u0010F\u001a\u00020 2\u0006\u00108\u001a\u00020\u001d2\u0006\u0010G\u001a\u00020\u001d2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u0012H\u0002¢\u0006\u0002\u0010IJ\r\u0010J\u001a\u00020\u001bH\u0000¢\u0006\u0002\bKJ\u0011\u0010L\u001a\u00020\u001bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J\b\u0010M\u001a\u00020\u001bH\u0016J\u0011\u0010N\u001a\u00020\u001bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020%0$X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Q"}, d2 = {"Lcom/onesignal/core/internal/operations/impl/OperationRepo;", "Lcom/onesignal/core/internal/operations/IOperationRepo;", "Lcom/onesignal/core/internal/startup/IStartableService;", "executors", "", "Lcom/onesignal/core/internal/operations/IOperationExecutor;", "_operationModelStore", "Lcom/onesignal/core/internal/operations/impl/OperationModelStore;", "_configModelStore", "Lcom/onesignal/core/internal/config/ConfigModelStore;", "_time", "Lcom/onesignal/core/internal/time/ITime;", "_newRecordState", "Lcom/onesignal/user/internal/operations/impl/states/NewRecordsState;", "(Ljava/util/List;Lcom/onesignal/core/internal/operations/impl/OperationModelStore;Lcom/onesignal/core/internal/config/ConfigModelStore;Lcom/onesignal/core/internal/time/ITime;Lcom/onesignal/user/internal/operations/impl/states/NewRecordsState;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "enqueueIntoBucket", "", "executeBucket", "getExecuteBucket", "()I", "executorsMap", "", "", "initialized", "Lkotlinx/coroutines/CompletableDeferred;", "", "paused", "", "queue", "", "Lcom/onesignal/core/internal/operations/impl/OperationRepo$OperationQueueItem;", "getQueue$com_onesignal_core", "()Ljava/util/List;", "retryWaiter", "Lcom/onesignal/common/threading/WaiterWithValue;", "Lcom/onesignal/core/internal/operations/impl/OperationRepo$LoopWaiterMessage;", "waiter", "awaitInitialized", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "containsInstanceOf", "T", "Lcom/onesignal/core/internal/operations/Operation;", WebViewManager.EVENT_TYPE_KEY, "Lkotlin/reflect/KClass;", "delayBeforeNextExecution", "retries", "retryAfterSeconds", "(ILjava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delayForPostCreate", "postCreateDelay", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enqueue", "operation", "flush", "enqueueAndWait", "(Lcom/onesignal/core/internal/operations/Operation;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeOperations", "ops", "executeOperations$com_onesignal_core", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "forceExecuteOperations", "getGroupableOperations", "startingOp", "getNextOps", "bucketFilter", "getNextOps$com_onesignal_core", "internalEnqueue", "queueItem", "addToStore", "index", "(Lcom/onesignal/core/internal/operations/impl/OperationRepo$OperationQueueItem;ZZLjava/lang/Integer;)V", "loadSavedOperations", "loadSavedOperations$com_onesignal_core", "processQueueForever", "start", "waitForNewOperationAndExecutionInterval", "LoopWaiterMessage", "OperationQueueItem", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OperationRepo implements IOperationRepo, IStartableService {
    private final ConfigModelStore _configModelStore;
    private final NewRecordsState _newRecordState;
    private final OperationModelStore _operationModelStore;
    private final ITime _time;
    private CoroutineScope coroutineScope;
    private int enqueueIntoBucket;
    private final Map<String, IOperationExecutor> executorsMap;
    private final CompletableDeferred<Unit> initialized;
    private boolean paused;
    private final List<OperationQueueItem> queue;
    private final WaiterWithValue<LoopWaiterMessage> retryWaiter;
    private final WaiterWithValue<LoopWaiterMessage> waiter;

    /* JADX INFO: compiled from: OperationRepo.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ExecutionResult.values().length];
            iArr[ExecutionResult.SUCCESS.ordinal()] = 1;
            iArr[ExecutionResult.FAIL_UNAUTHORIZED.ordinal()] = 2;
            iArr[ExecutionResult.FAIL_NORETRY.ordinal()] = 3;
            iArr[ExecutionResult.FAIL_CONFLICT.ordinal()] = 4;
            iArr[ExecutionResult.SUCCESS_STARTING_ONLY.ordinal()] = 5;
            iArr[ExecutionResult.FAIL_RETRY.ordinal()] = 6;
            iArr[ExecutionResult.FAIL_PAUSE_OPREPO.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.onesignal.core.internal.operations.impl.OperationRepo$delayForPostCreate$1, reason: invalid class name */
    /* JADX INFO: compiled from: OperationRepo.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.operations.impl.OperationRepo", f = "OperationRepo.kt", i = {0, 0}, l = {360}, m = "delayForPostCreate", n = {"this", "postCreateDelay"}, s = {"L$0", "J$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OperationRepo.this.delayForPostCreate(0L, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.core.internal.operations.impl.OperationRepo$processQueueForever$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OperationRepo.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.operations.impl.OperationRepo", f = "OperationRepo.kt", i = {0, 1, 2, 3}, l = {174, 186, 189, 191}, m = "processQueueForever", n = {"this", "this", "this", "this"}, s = {"L$0", "L$0", "L$0", "L$0"})
    static final class C00261 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00261(Continuation<? super C00261> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OperationRepo.this.processQueueForever((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.core.internal.operations.impl.OperationRepo$waitForNewOperationAndExecutionInterval$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OperationRepo.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.operations.impl.OperationRepo", f = "OperationRepo.kt", i = {0, 0, 1, 1}, l = {218, 226}, m = "waitForNewOperationAndExecutionInterval", n = {"this", "wakeMessage", "this", "wakeMessage"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C00281 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00281(Continuation<? super C00281> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OperationRepo.this.waitForNewOperationAndExecutionInterval((Continuation) this);
        }
    }

    public OperationRepo(List<? extends IOperationExecutor> list, OperationModelStore _operationModelStore, ConfigModelStore _configModelStore, ITime _time, NewRecordsState _newRecordState) {
        Intrinsics.checkNotNullParameter(list, "executors");
        Intrinsics.checkNotNullParameter(_operationModelStore, "_operationModelStore");
        Intrinsics.checkNotNullParameter(_configModelStore, "_configModelStore");
        Intrinsics.checkNotNullParameter(_time, "_time");
        Intrinsics.checkNotNullParameter(_newRecordState, "_newRecordState");
        this._operationModelStore = _operationModelStore;
        this._configModelStore = _configModelStore;
        this._time = _time;
        this._newRecordState = _newRecordState;
        this.queue = new ArrayList();
        this.waiter = new WaiterWithValue<>();
        this.retryWaiter = new WaiterWithValue<>();
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(ThreadPoolDispatcherKt.newSingleThreadContext("OpRepo"));
        this.initialized = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        Map executorsMap = new LinkedHashMap();
        for (IOperationExecutor executor : list) {
            for (String operation : executor.getOperations()) {
                executorsMap.put(operation, executor);
            }
        }
        this.executorsMap = executorsMap;
    }

    /* JADX INFO: compiled from: OperationRepo.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/onesignal/core/internal/operations/impl/OperationRepo$OperationQueueItem;", "", "operation", "Lcom/onesignal/core/internal/operations/Operation;", "waiter", "Lcom/onesignal/common/threading/WaiterWithValue;", "", "bucket", "", "retries", "(Lcom/onesignal/core/internal/operations/Operation;Lcom/onesignal/common/threading/WaiterWithValue;II)V", "getBucket", "()I", "getOperation", "()Lcom/onesignal/core/internal/operations/Operation;", "getRetries", "setRetries", "(I)V", "getWaiter", "()Lcom/onesignal/common/threading/WaiterWithValue;", "toString", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class OperationQueueItem {
        private final int bucket;
        private final Operation operation;
        private int retries;
        private final WaiterWithValue<Boolean> waiter;

        public OperationQueueItem(Operation operation, WaiterWithValue<Boolean> waiterWithValue, int bucket, int retries) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            this.operation = operation;
            this.waiter = waiterWithValue;
            this.bucket = bucket;
            this.retries = retries;
        }

        public /* synthetic */ OperationQueueItem(Operation operation, WaiterWithValue waiterWithValue, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(operation, (i3 & 2) != 0 ? null : waiterWithValue, i, (i3 & 8) != 0 ? 0 : i2);
        }

        public final Operation getOperation() {
            return this.operation;
        }

        public final WaiterWithValue<Boolean> getWaiter() {
            return this.waiter;
        }

        public final int getBucket() {
            return this.bucket;
        }

        public final int getRetries() {
            return this.retries;
        }

        public final void setRetries(int i) {
            this.retries = i;
        }

        public String toString() {
            return "bucket:" + this.bucket + ", retries:" + this.retries + ", operation:" + this.operation + '\n';
        }
    }

    /* JADX INFO: compiled from: OperationRepo.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/onesignal/core/internal/operations/impl/OperationRepo$LoopWaiterMessage;", "", "force", "", "previousWaitedTime", "", "(ZJ)V", "getForce", "()Z", "getPreviousWaitedTime", "()J", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class LoopWaiterMessage {
        private final boolean force;
        private final long previousWaitedTime;

        public LoopWaiterMessage(boolean force, long previousWaitedTime) {
            this.force = force;
            this.previousWaitedTime = previousWaitedTime;
        }

        public /* synthetic */ LoopWaiterMessage(boolean z, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? 0L : j);
        }

        public final boolean getForce() {
            return this.force;
        }

        public final long getPreviousWaitedTime() {
            return this.previousWaitedTime;
        }
    }

    public final List<OperationQueueItem> getQueue$com_onesignal_core() {
        return this.queue;
    }

    @Override // com.onesignal.core.internal.operations.IOperationRepo
    public Object awaitInitialized(Continuation<? super Unit> continuation) {
        Object objAwait = this.initialized.await(continuation);
        return objAwait == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwait : Unit.INSTANCE;
    }

    private final int getExecuteBucket() {
        if (this.enqueueIntoBucket == 0) {
            return 0;
        }
        return this.enqueueIntoBucket - 1;
    }

    @Override // com.onesignal.core.internal.operations.IOperationRepo
    public <T extends Operation> boolean containsInstanceOf(KClass<T> kClass) {
        boolean z;
        Intrinsics.checkNotNullParameter(kClass, WebViewManager.EVENT_TYPE_KEY);
        synchronized (this.queue) {
            Iterable $this$any$iv = this.queue;
            z = false;
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object element$iv = it.next();
                    OperationQueueItem it2 = (OperationQueueItem) element$iv;
                    if (kClass.isInstance(it2.getOperation())) {
                        z = true;
                        break;
                    }
                }
            }
        }
        return z;
    }

    /* JADX INFO: renamed from: com.onesignal.core.internal.operations.impl.OperationRepo$start$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OperationRepo.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.operations.impl.OperationRepo$start$1", f = "OperationRepo.kt", i = {}, l = {102}, m = "invokeSuspend", n = {}, s = {})
    static final class C00271 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00271(Continuation<? super C00271> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OperationRepo.this.new C00271(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) throws Exception {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    OperationRepo.this.loadSavedOperations$com_onesignal_core();
                    this.label = 1;
                    if (OperationRepo.this.processQueueForever((Continuation) this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.onesignal.core.internal.startup.IStartableService
    public void start() {
        this.paused = false;
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00271(null), 3, (Object) null);
    }

    @Override // com.onesignal.core.internal.operations.IOperationRepo
    public void enqueue(Operation operation, boolean flush) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        Logging.log(LogLevel.DEBUG, "OperationRepo.enqueue(operation: " + operation + ", flush: " + flush + ')');
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        operation.setId(string);
        OSPrimaryCoroutineScope.INSTANCE.execute(new C00251(operation, flush, null));
    }

    /* JADX INFO: renamed from: com.onesignal.core.internal.operations.impl.OperationRepo$enqueue$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OperationRepo.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.operations.impl.OperationRepo$enqueue$1", f = "OperationRepo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00251 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $flush;
        final /* synthetic */ Operation $operation;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00251(Operation operation, boolean z, Continuation<? super C00251> continuation) {
            super(1, continuation);
            this.$operation = operation;
            this.$flush = z;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return OperationRepo.this.new C00251(this.$operation, this.$flush, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) throws Exception {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    OperationRepo.internalEnqueue$default(OperationRepo.this, new OperationQueueItem(this.$operation, null, OperationRepo.this.enqueueIntoBucket, 0, 10, null), this.$flush, true, null, 8, null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // com.onesignal.core.internal.operations.IOperationRepo
    public Object enqueueAndWait(Operation operation, boolean flush, Continuation<? super Boolean> continuation) throws Exception {
        Logging.log(LogLevel.DEBUG, "OperationRepo.enqueueAndWait(operation: " + operation + ", force: " + flush + ')');
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        operation.setId(string);
        WaiterWithValue waiter = new WaiterWithValue();
        internalEnqueue$default(this, new OperationQueueItem(operation, waiter, this.enqueueIntoBucket, 0, 8, null), flush, true, null, 8, null);
        return waiter.waitForWake(continuation);
    }

    static /* synthetic */ void internalEnqueue$default(OperationRepo operationRepo, OperationQueueItem operationQueueItem, boolean z, boolean z2, Integer num, int i, Object obj) throws Exception {
        if ((i & 8) != 0) {
            num = null;
        }
        operationRepo.internalEnqueue(operationQueueItem, z, z2, num);
    }

    private final void internalEnqueue(OperationQueueItem queueItem, boolean flush, boolean addToStore, Integer index) throws Exception {
        synchronized (this.queue) {
            Iterable $this$any$iv = this.queue;
            boolean hasExisting = false;
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object element$iv = it.next();
                    OperationQueueItem it2 = (OperationQueueItem) element$iv;
                    if (Intrinsics.areEqual(it2.getOperation().getId(), queueItem.getOperation().getId())) {
                        hasExisting = true;
                        break;
                    }
                }
            }
            if (hasExisting) {
                Logging.debug$default("OperationRepo: internalEnqueue - operation.id: " + queueItem.getOperation().getId() + " already exists in the queue.", null, 2, null);
                return;
            }
            if (index != null) {
                this.queue.add(index.intValue(), queueItem);
                Unit unit = Unit.INSTANCE;
            } else {
                Boolean.valueOf(this.queue.add(queueItem));
            }
            if (addToStore) {
                IModelStore.DefaultImpls.add$default(this._operationModelStore, queueItem.getOperation(), null, 2, null);
            }
            this.waiter.wake(new LoopWaiterMessage(flush, 0L));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00b2 -> B:20:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00bf -> B:36:0x00c2). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processQueueForever(kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.core.internal.operations.impl.OperationRepo.processQueueForever(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.onesignal.core.internal.operations.IOperationRepo
    public void forceExecuteOperations() throws Exception {
        int i = 2;
        DefaultConstructorMarker defaultConstructorMarker = null;
        long j = 0;
        this.retryWaiter.wake(new LoopWaiterMessage(true, j, i, defaultConstructorMarker));
        this.waiter.wake(new LoopWaiterMessage(false, j, i, defaultConstructorMarker));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00a9 -> B:25:0x00b0). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object waitForNewOperationAndExecutionInterval(kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.core.internal.operations.impl.OperationRepo.waitForNewOperationAndExecutionInterval(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x02cc A[Catch: all -> 0x043e, TryCatch #8 {all -> 0x043e, blocks: (B:38:0x00e3, B:40:0x0105, B:41:0x010d, B:43:0x0113, B:44:0x012a, B:45:0x012d, B:52:0x015a, B:53:0x015b, B:54:0x016a, B:56:0x0170, B:59:0x0180, B:60:0x0181, B:61:0x0182, B:62:0x0193, B:142:0x038c, B:144:0x0392, B:145:0x0394, B:152:0x03f6, B:155:0x03f9, B:156:0x03fa, B:157:0x03fb, B:64:0x0198, B:65:0x01b5, B:72:0x01de, B:75:0x01e2, B:76:0x01e3, B:77:0x01e4, B:78:0x01fe, B:88:0x0241, B:91:0x0245, B:92:0x0246, B:93:0x0247, B:95:0x025e, B:96:0x0269, B:97:0x026b, B:113:0x02c6, B:116:0x02ca, B:117:0x02cb, B:118:0x02cc, B:119:0x02ec, B:121:0x02f2, B:122:0x030c, B:123:0x0315, B:125:0x031b, B:127:0x0329, B:130:0x0337, B:131:0x033f, B:133:0x0345, B:134:0x035f, B:135:0x0368, B:137:0x036e, B:139:0x037c, B:67:0x01b7, B:68:0x01c5, B:70:0x01cb, B:71:0x01da, B:80:0x0200, B:81:0x020e, B:83:0x0214, B:85:0x022f, B:86:0x0235, B:87:0x023d, B:147:0x0396, B:148:0x03a4, B:150:0x03aa, B:151:0x03f3, B:99:0x026d, B:100:0x027d, B:102:0x0283, B:107:0x0297, B:108:0x029b, B:109:0x02ad, B:111:0x02b3, B:112:0x02c2, B:47:0x012f, B:48:0x0138, B:50:0x013e, B:51:0x0156), top: B:203:0x00e3, inners: #0, #3, #6, #7, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0337 A[Catch: all -> 0x043e, TryCatch #8 {all -> 0x043e, blocks: (B:38:0x00e3, B:40:0x0105, B:41:0x010d, B:43:0x0113, B:44:0x012a, B:45:0x012d, B:52:0x015a, B:53:0x015b, B:54:0x016a, B:56:0x0170, B:59:0x0180, B:60:0x0181, B:61:0x0182, B:62:0x0193, B:142:0x038c, B:144:0x0392, B:145:0x0394, B:152:0x03f6, B:155:0x03f9, B:156:0x03fa, B:157:0x03fb, B:64:0x0198, B:65:0x01b5, B:72:0x01de, B:75:0x01e2, B:76:0x01e3, B:77:0x01e4, B:78:0x01fe, B:88:0x0241, B:91:0x0245, B:92:0x0246, B:93:0x0247, B:95:0x025e, B:96:0x0269, B:97:0x026b, B:113:0x02c6, B:116:0x02ca, B:117:0x02cb, B:118:0x02cc, B:119:0x02ec, B:121:0x02f2, B:122:0x030c, B:123:0x0315, B:125:0x031b, B:127:0x0329, B:130:0x0337, B:131:0x033f, B:133:0x0345, B:134:0x035f, B:135:0x0368, B:137:0x036e, B:139:0x037c, B:67:0x01b7, B:68:0x01c5, B:70:0x01cb, B:71:0x01da, B:80:0x0200, B:81:0x020e, B:83:0x0214, B:85:0x022f, B:86:0x0235, B:87:0x023d, B:147:0x0396, B:148:0x03a4, B:150:0x03aa, B:151:0x03f3, B:99:0x026d, B:100:0x027d, B:102:0x0283, B:107:0x0297, B:108:0x029b, B:109:0x02ad, B:111:0x02b3, B:112:0x02c2, B:47:0x012f, B:48:0x0138, B:50:0x013e, B:51:0x0156), top: B:203:0x00e3, inners: #0, #3, #6, #7, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0392 A[Catch: all -> 0x043e, TryCatch #8 {all -> 0x043e, blocks: (B:38:0x00e3, B:40:0x0105, B:41:0x010d, B:43:0x0113, B:44:0x012a, B:45:0x012d, B:52:0x015a, B:53:0x015b, B:54:0x016a, B:56:0x0170, B:59:0x0180, B:60:0x0181, B:61:0x0182, B:62:0x0193, B:142:0x038c, B:144:0x0392, B:145:0x0394, B:152:0x03f6, B:155:0x03f9, B:156:0x03fa, B:157:0x03fb, B:64:0x0198, B:65:0x01b5, B:72:0x01de, B:75:0x01e2, B:76:0x01e3, B:77:0x01e4, B:78:0x01fe, B:88:0x0241, B:91:0x0245, B:92:0x0246, B:93:0x0247, B:95:0x025e, B:96:0x0269, B:97:0x026b, B:113:0x02c6, B:116:0x02ca, B:117:0x02cb, B:118:0x02cc, B:119:0x02ec, B:121:0x02f2, B:122:0x030c, B:123:0x0315, B:125:0x031b, B:127:0x0329, B:130:0x0337, B:131:0x033f, B:133:0x0345, B:134:0x035f, B:135:0x0368, B:137:0x036e, B:139:0x037c, B:67:0x01b7, B:68:0x01c5, B:70:0x01cb, B:71:0x01da, B:80:0x0200, B:81:0x020e, B:83:0x0214, B:85:0x022f, B:86:0x0235, B:87:0x023d, B:147:0x0396, B:148:0x03a4, B:150:0x03aa, B:151:0x03f3, B:99:0x026d, B:100:0x027d, B:102:0x0283, B:107:0x0297, B:108:0x029b, B:109:0x02ad, B:111:0x02b3, B:112:0x02c2, B:47:0x012f, B:48:0x0138, B:50:0x013e, B:51:0x0156), top: B:203:0x00e3, inners: #0, #3, #6, #7, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0413 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x041d A[Catch: all -> 0x0058, TRY_LEAVE, TryCatch #5 {all -> 0x0058, blocks: (B:18:0x0053, B:161:0x0417, B:163:0x041d), top: B:197:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x048b A[LOOP:0: B:175:0x0485->B:177:0x048b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x04b4  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0105 A[Catch: all -> 0x043e, TryCatch #8 {all -> 0x043e, blocks: (B:38:0x00e3, B:40:0x0105, B:41:0x010d, B:43:0x0113, B:44:0x012a, B:45:0x012d, B:52:0x015a, B:53:0x015b, B:54:0x016a, B:56:0x0170, B:59:0x0180, B:60:0x0181, B:61:0x0182, B:62:0x0193, B:142:0x038c, B:144:0x0392, B:145:0x0394, B:152:0x03f6, B:155:0x03f9, B:156:0x03fa, B:157:0x03fb, B:64:0x0198, B:65:0x01b5, B:72:0x01de, B:75:0x01e2, B:76:0x01e3, B:77:0x01e4, B:78:0x01fe, B:88:0x0241, B:91:0x0245, B:92:0x0246, B:93:0x0247, B:95:0x025e, B:96:0x0269, B:97:0x026b, B:113:0x02c6, B:116:0x02ca, B:117:0x02cb, B:118:0x02cc, B:119:0x02ec, B:121:0x02f2, B:122:0x030c, B:123:0x0315, B:125:0x031b, B:127:0x0329, B:130:0x0337, B:131:0x033f, B:133:0x0345, B:134:0x035f, B:135:0x0368, B:137:0x036e, B:139:0x037c, B:67:0x01b7, B:68:0x01c5, B:70:0x01cb, B:71:0x01da, B:80:0x0200, B:81:0x020e, B:83:0x0214, B:85:0x022f, B:86:0x0235, B:87:0x023d, B:147:0x0396, B:148:0x03a4, B:150:0x03aa, B:151:0x03f3, B:99:0x026d, B:100:0x027d, B:102:0x0283, B:107:0x0297, B:108:0x029b, B:109:0x02ad, B:111:0x02b3, B:112:0x02c2, B:47:0x012f, B:48:0x0138, B:50:0x013e, B:51:0x0156), top: B:203:0x00e3, inners: #0, #3, #6, #7, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0198 A[Catch: all -> 0x043e, TryCatch #8 {all -> 0x043e, blocks: (B:38:0x00e3, B:40:0x0105, B:41:0x010d, B:43:0x0113, B:44:0x012a, B:45:0x012d, B:52:0x015a, B:53:0x015b, B:54:0x016a, B:56:0x0170, B:59:0x0180, B:60:0x0181, B:61:0x0182, B:62:0x0193, B:142:0x038c, B:144:0x0392, B:145:0x0394, B:152:0x03f6, B:155:0x03f9, B:156:0x03fa, B:157:0x03fb, B:64:0x0198, B:65:0x01b5, B:72:0x01de, B:75:0x01e2, B:76:0x01e3, B:77:0x01e4, B:78:0x01fe, B:88:0x0241, B:91:0x0245, B:92:0x0246, B:93:0x0247, B:95:0x025e, B:96:0x0269, B:97:0x026b, B:113:0x02c6, B:116:0x02ca, B:117:0x02cb, B:118:0x02cc, B:119:0x02ec, B:121:0x02f2, B:122:0x030c, B:123:0x0315, B:125:0x031b, B:127:0x0329, B:130:0x0337, B:131:0x033f, B:133:0x0345, B:134:0x035f, B:135:0x0368, B:137:0x036e, B:139:0x037c, B:67:0x01b7, B:68:0x01c5, B:70:0x01cb, B:71:0x01da, B:80:0x0200, B:81:0x020e, B:83:0x0214, B:85:0x022f, B:86:0x0235, B:87:0x023d, B:147:0x0396, B:148:0x03a4, B:150:0x03aa, B:151:0x03f3, B:99:0x026d, B:100:0x027d, B:102:0x0283, B:107:0x0297, B:108:0x029b, B:109:0x02ad, B:111:0x02b3, B:112:0x02c2, B:47:0x012f, B:48:0x0138, B:50:0x013e, B:51:0x0156), top: B:203:0x00e3, inners: #0, #3, #6, #7, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01e4 A[Catch: all -> 0x043e, TryCatch #8 {all -> 0x043e, blocks: (B:38:0x00e3, B:40:0x0105, B:41:0x010d, B:43:0x0113, B:44:0x012a, B:45:0x012d, B:52:0x015a, B:53:0x015b, B:54:0x016a, B:56:0x0170, B:59:0x0180, B:60:0x0181, B:61:0x0182, B:62:0x0193, B:142:0x038c, B:144:0x0392, B:145:0x0394, B:152:0x03f6, B:155:0x03f9, B:156:0x03fa, B:157:0x03fb, B:64:0x0198, B:65:0x01b5, B:72:0x01de, B:75:0x01e2, B:76:0x01e3, B:77:0x01e4, B:78:0x01fe, B:88:0x0241, B:91:0x0245, B:92:0x0246, B:93:0x0247, B:95:0x025e, B:96:0x0269, B:97:0x026b, B:113:0x02c6, B:116:0x02ca, B:117:0x02cb, B:118:0x02cc, B:119:0x02ec, B:121:0x02f2, B:122:0x030c, B:123:0x0315, B:125:0x031b, B:127:0x0329, B:130:0x0337, B:131:0x033f, B:133:0x0345, B:134:0x035f, B:135:0x0368, B:137:0x036e, B:139:0x037c, B:67:0x01b7, B:68:0x01c5, B:70:0x01cb, B:71:0x01da, B:80:0x0200, B:81:0x020e, B:83:0x0214, B:85:0x022f, B:86:0x0235, B:87:0x023d, B:147:0x0396, B:148:0x03a4, B:150:0x03aa, B:151:0x03f3, B:99:0x026d, B:100:0x027d, B:102:0x0283, B:107:0x0297, B:108:0x029b, B:109:0x02ad, B:111:0x02b3, B:112:0x02c2, B:47:0x012f, B:48:0x0138, B:50:0x013e, B:51:0x0156), top: B:203:0x00e3, inners: #0, #3, #6, #7, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0247 A[Catch: all -> 0x043e, TryCatch #8 {all -> 0x043e, blocks: (B:38:0x00e3, B:40:0x0105, B:41:0x010d, B:43:0x0113, B:44:0x012a, B:45:0x012d, B:52:0x015a, B:53:0x015b, B:54:0x016a, B:56:0x0170, B:59:0x0180, B:60:0x0181, B:61:0x0182, B:62:0x0193, B:142:0x038c, B:144:0x0392, B:145:0x0394, B:152:0x03f6, B:155:0x03f9, B:156:0x03fa, B:157:0x03fb, B:64:0x0198, B:65:0x01b5, B:72:0x01de, B:75:0x01e2, B:76:0x01e3, B:77:0x01e4, B:78:0x01fe, B:88:0x0241, B:91:0x0245, B:92:0x0246, B:93:0x0247, B:95:0x025e, B:96:0x0269, B:97:0x026b, B:113:0x02c6, B:116:0x02ca, B:117:0x02cb, B:118:0x02cc, B:119:0x02ec, B:121:0x02f2, B:122:0x030c, B:123:0x0315, B:125:0x031b, B:127:0x0329, B:130:0x0337, B:131:0x033f, B:133:0x0345, B:134:0x035f, B:135:0x0368, B:137:0x036e, B:139:0x037c, B:67:0x01b7, B:68:0x01c5, B:70:0x01cb, B:71:0x01da, B:80:0x0200, B:81:0x020e, B:83:0x0214, B:85:0x022f, B:86:0x0235, B:87:0x023d, B:147:0x0396, B:148:0x03a4, B:150:0x03aa, B:151:0x03f3, B:99:0x026d, B:100:0x027d, B:102:0x0283, B:107:0x0297, B:108:0x029b, B:109:0x02ad, B:111:0x02b3, B:112:0x02c2, B:47:0x012f, B:48:0x0138, B:50:0x013e, B:51:0x0156), top: B:203:0x00e3, inners: #0, #3, #6, #7, #9 }] */
    /*  JADX ERROR: UnsupportedOperationException in pass: o
        java.lang.UnsupportedOperationException
        	at j$.util.m.add(SourceFile:172)
        	at ba.j.z(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:99)
        	at aa.f.H0(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:36)
        	at aa.e.accept(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:1104)
        	at j$.lang.Iterable$-EL.forEach(SourceFile:665)
        	at aa.f.H0(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:33)
        	at aa.e.accept(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:1104)
        	at j$.util.Collection$-EL.a(SourceFile:665)
        	at j$.util.m.forEach(SourceFile:202)
        	at j$.lang.Iterable$-EL.forEach(Unknown Source:6)
        	at aa.f.H0(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:33)
        	at aa.e.accept(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:1104)
        	at j$.lang.Iterable$-EL.forEach(SourceFile:665)
        	at aa.f.H0(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:33)
        	at aa.j.z(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:93)
        	at aa.f.H0(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:36)
        	at aa.e.accept(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:1104)
        	at j$.lang.Iterable$-EL.forEach(SourceFile:665)
        	at aa.f.H0(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:33)
        	at aa.o.c(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:782)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object executeOperations$com_onesignal_core(java.util.List<com.onesignal.core.internal.operations.impl.OperationRepo.OperationQueueItem> r28, kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            Method dump skipped, instruction units count: 1264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.core.internal.operations.impl.OperationRepo.executeOperations$com_onesignal_core(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object delayBeforeNextExecution(int retries, Integer retryAfterSeconds, Continuation<? super Unit> continuation) {
        Logging.debug$default("retryAfterSeconds: " + retryAfterSeconds, null, 2, null);
        long retryAfterSecondsNonNull = retryAfterSeconds != null ? retryAfterSeconds.intValue() : 0L;
        long delayForOnRetries = ((long) retries) * this._configModelStore.getModel().getOpRepoDefaultFailRetryBackoff();
        long delayFor = Math.max(delayForOnRetries, ((long) 1000) * retryAfterSecondsNonNull);
        if (delayFor < 1) {
            return Unit.INSTANCE;
        }
        Logging.error$default("Operations being delay for: " + delayFor + " ms", null, 2, null);
        Object objWithTimeoutOrNull = TimeoutKt.withTimeoutOrNull(delayFor, new AnonymousClass2(null), continuation);
        return objWithTimeoutOrNull == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithTimeoutOrNull : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.onesignal.core.internal.operations.impl.OperationRepo$delayBeforeNextExecution$2, reason: invalid class name */
    /* JADX INFO: compiled from: OperationRepo.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/onesignal/core/internal/operations/impl/OperationRepo$LoopWaiterMessage;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.operations.impl.OperationRepo$delayBeforeNextExecution$2", f = "OperationRepo.kt", i = {}, l = {346}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LoopWaiterMessage>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OperationRepo.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super LoopWaiterMessage> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objWaitForWake = OperationRepo.this.retryWaiter.waitForWake((Continuation) this);
                    return objWaitForWake == coroutine_suspended ? coroutine_suspended : objWaitForWake;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object delayForPostCreate(long r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof com.onesignal.core.internal.operations.impl.OperationRepo.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r10
            com.onesignal.core.internal.operations.impl.OperationRepo$delayForPostCreate$1 r0 = (com.onesignal.core.internal.operations.impl.OperationRepo.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.onesignal.core.internal.operations.impl.OperationRepo$delayForPostCreate$1 r0 = new com.onesignal.core.internal.operations.impl.OperationRepo$delayForPostCreate$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            long r8 = r0.J$0
            java.lang.Object r1 = r0.L$0
            com.onesignal.core.internal.operations.impl.OperationRepo r1 = (com.onesignal.core.internal.operations.impl.OperationRepo) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L49
        L36:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r7
            r0.L$0 = r2
            r0.J$0 = r8
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r8, r0)
            if (r3 != r1) goto L48
            return r1
        L48:
            r1 = r2
        L49:
            java.util.List<com.onesignal.core.internal.operations.impl.OperationRepo$OperationQueueItem> r2 = r1.queue
            monitor-enter(r2)
            r3 = 0
            java.util.List<com.onesignal.core.internal.operations.impl.OperationRepo$OperationQueueItem> r4 = r1.queue     // Catch: java.lang.Throwable -> L69
            java.util.Collection r4 = (java.util.Collection) r4     // Catch: java.lang.Throwable -> L69
            boolean r4 = r4.isEmpty()     // Catch: java.lang.Throwable -> L69
            if (r4 != 0) goto L62
            com.onesignal.common.threading.WaiterWithValue<com.onesignal.core.internal.operations.impl.OperationRepo$LoopWaiterMessage> r4 = r1.waiter     // Catch: java.lang.Throwable -> L69
            com.onesignal.core.internal.operations.impl.OperationRepo$LoopWaiterMessage r5 = new com.onesignal.core.internal.operations.impl.OperationRepo$LoopWaiterMessage     // Catch: java.lang.Throwable -> L69
            r6 = 0
            r5.<init>(r6, r8)     // Catch: java.lang.Throwable -> L69
            r4.wake(r5)     // Catch: java.lang.Throwable -> L69
        L62:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L69
            monitor-exit(r2)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L69:
            r8 = move-exception
            monitor-exit(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.core.internal.operations.impl.OperationRepo.delayForPostCreate(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final List<OperationQueueItem> getNextOps$com_onesignal_core(int bucketFilter) {
        List<OperationQueueItem> groupableOperations;
        Object element$iv;
        synchronized (this.queue) {
            Iterable $this$firstOrNull$iv = this.queue;
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                groupableOperations = null;
                if (it.hasNext()) {
                    element$iv = it.next();
                    OperationQueueItem it2 = (OperationQueueItem) element$iv;
                    if (it2.getOperation().getCanStartExecute() && this._newRecordState.canAccess(it2.getOperation().getApplyToRecordId()) && it2.getBucket() <= bucketFilter) {
                        break;
                    }
                } else {
                    element$iv = null;
                    break;
                }
            }
            OperationQueueItem startingOp = (OperationQueueItem) element$iv;
            if (startingOp != null) {
                this.queue.remove(startingOp);
                groupableOperations = getGroupableOperations(startingOp);
            }
        }
        return groupableOperations;
    }

    private final List<OperationQueueItem> getGroupableOperations(OperationQueueItem startingOp) throws Exception {
        String startingKey;
        String itemKey;
        List<OperationQueueItem> listMutableListOf = CollectionsKt.mutableListOf(new OperationQueueItem[]{startingOp});
        if (startingOp.getOperation().getGroupComparisonType() == GroupComparisonType.NONE) {
            return listMutableListOf;
        }
        if (startingOp.getOperation().getGroupComparisonType() == GroupComparisonType.CREATE) {
            startingKey = startingOp.getOperation().getCreateComparisonKey();
        } else {
            startingKey = startingOp.getOperation().getModifyComparisonKey();
        }
        for (OperationQueueItem item : CollectionsKt.toList(this.queue)) {
            if (startingOp.getOperation().getGroupComparisonType() == GroupComparisonType.CREATE) {
                itemKey = item.getOperation().getCreateComparisonKey();
            } else {
                itemKey = item.getOperation().getModifyComparisonKey();
            }
            if (Intrinsics.areEqual(itemKey, "") && Intrinsics.areEqual(startingKey, "")) {
                throw new Exception("Both comparison keys can not be blank!");
            }
            if (this._newRecordState.canAccess(item.getOperation().getApplyToRecordId()) && Intrinsics.areEqual(itemKey, startingKey)) {
                this.queue.remove(item);
                listMutableListOf.add(item);
            }
        }
        return listMutableListOf;
    }

    public final void loadSavedOperations$com_onesignal_core() throws Exception {
        this._operationModelStore.loadOperations();
        for (Operation operation : CollectionsKt.reversed(this._operationModelStore.list())) {
            internalEnqueue(new OperationQueueItem(operation, null, this.enqueueIntoBucket, 0, 10, null), false, false, 0);
        }
        this.initialized.complete(Unit.INSTANCE);
    }
}
