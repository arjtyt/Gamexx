package com.onesignal.common.consistency.impl;

import com.onesignal.common.consistency.RywData;
import com.onesignal.common.consistency.models.ICondition;
import com.onesignal.common.consistency.models.IConsistencyKeyEnum;
import com.onesignal.common.consistency.models.IConsistencyManager;
import com.onesignal.core.BuildConfig;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: ConsistencyManager.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J!\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\u0012\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J)\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001aR(\u0010\u0003\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\t\u001a\u001a\u0012\u0004\u0012\u00020\u000b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lcom/onesignal/common/consistency/impl/ConsistencyManager;", "Lcom/onesignal/common/consistency/models/IConsistencyManager;", "()V", "conditions", "", "Lkotlin/Pair;", "Lcom/onesignal/common/consistency/models/ICondition;", "Lkotlinx/coroutines/CompletableDeferred;", "Lcom/onesignal/common/consistency/RywData;", "indexedTokens", "", "", "Lcom/onesignal/common/consistency/models/IConsistencyKeyEnum;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "checkConditionsAndComplete", "", "getRywDataFromAwaitableCondition", "condition", "(Lcom/onesignal/common/consistency/models/ICondition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveConditionsWithID", OutcomeConstants.OUTCOME_ID, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setRywData", "key", "value", "(Ljava/lang/String;Lcom/onesignal/common/consistency/models/IConsistencyKeyEnum;Lcom/onesignal/common/consistency/RywData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ConsistencyManager implements IConsistencyManager {
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, (Object) null);
    private final Map<String, Map<IConsistencyKeyEnum, RywData>> indexedTokens = new LinkedHashMap();
    private final List<Pair<ICondition, CompletableDeferred<RywData>>> conditions = new ArrayList();

    /* JADX INFO: renamed from: com.onesignal.common.consistency.impl.ConsistencyManager$getRywDataFromAwaitableCondition$1, reason: invalid class name */
    /* JADX INFO: compiled from: ConsistencyManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.common.consistency.impl.ConsistencyManager", f = "ConsistencyManager.kt", i = {0, 0, 0}, l = {100}, m = "getRywDataFromAwaitableCondition", n = {"this", "condition", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2"})
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
            return ConsistencyManager.this.getRywDataFromAwaitableCondition(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.common.consistency.impl.ConsistencyManager$setRywData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ConsistencyManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.common.consistency.impl.ConsistencyManager", f = "ConsistencyManager.kt", i = {0, 0, 0, 0, 0}, l = {100}, m = "setRywData", n = {"this", OutcomeConstants.OUTCOME_ID, "key", "value", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C00061 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C00061(Continuation<? super C00061> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ConsistencyManager.this.setRywData(null, null, null, (Continuation) this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.common.consistency.models.IConsistencyManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object setRywData(java.lang.String r11, com.onesignal.common.consistency.models.IConsistencyKeyEnum r12, com.onesignal.common.consistency.RywData r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.onesignal.common.consistency.impl.ConsistencyManager.C00061
            if (r0 == 0) goto L14
            r0 = r14
            com.onesignal.common.consistency.impl.ConsistencyManager$setRywData$1 r0 = (com.onesignal.common.consistency.impl.ConsistencyManager.C00061) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            com.onesignal.common.consistency.impl.ConsistencyManager$setRywData$1 r0 = new com.onesignal.common.consistency.impl.ConsistencyManager$setRywData$1
            r0.<init>(r14)
        L19:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L46;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L2c:
            r11 = 0
            r12 = 0
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.sync.Mutex r13 = (kotlinx.coroutines.sync.Mutex) r13
            java.lang.Object r1 = r0.L$3
            com.onesignal.common.consistency.RywData r1 = (com.onesignal.common.consistency.RywData) r1
            java.lang.Object r2 = r0.L$2
            com.onesignal.common.consistency.models.IConsistencyKeyEnum r2 = (com.onesignal.common.consistency.models.IConsistencyKeyEnum) r2
            java.lang.Object r3 = r0.L$1
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r0.L$0
            com.onesignal.common.consistency.impl.ConsistencyManager r4 = (com.onesignal.common.consistency.impl.ConsistencyManager) r4
            kotlin.ResultKt.throwOnFailure(r14)
            goto L68
        L46:
            kotlin.ResultKt.throwOnFailure(r14)
            r4 = r10
            r2 = r12
            r3 = r11
            kotlinx.coroutines.sync.Mutex r11 = r4.mutex
            r12 = 0
            r5 = 0
            r0.L$0 = r4
            r0.L$1 = r3
            r0.L$2 = r2
            r0.L$3 = r13
            r0.L$4 = r11
            r6 = 1
            r0.label = r6
            java.lang.Object r6 = r11.lock(r12, r0)
            if (r6 != r1) goto L65
            return r1
        L65:
            r1 = r13
            r13 = r11
            r11 = r5
        L68:
            r5 = 0
            java.util.Map<java.lang.String, java.util.Map<com.onesignal.common.consistency.models.IConsistencyKeyEnum, com.onesignal.common.consistency.RywData>> r6 = r4.indexedTokens     // Catch: java.lang.Throwable -> L96
            r7 = 0
            java.lang.Object r8 = r6.get(r3)     // Catch: java.lang.Throwable -> L96
            if (r8 != 0) goto L81
            r8 = 0
            java.util.LinkedHashMap r9 = new java.util.LinkedHashMap     // Catch: java.lang.Throwable -> L96
            r9.<init>()     // Catch: java.lang.Throwable -> L96
            java.util.Map r9 = (java.util.Map) r9     // Catch: java.lang.Throwable -> L96
            r8 = r9
            r6.put(r3, r8)     // Catch: java.lang.Throwable -> L96
            goto L82
        L81:
        L82:
            java.util.Map r8 = (java.util.Map) r8     // Catch: java.lang.Throwable -> L96
            r8.put(r2, r1)     // Catch: java.lang.Throwable -> L96
            r4.checkConditionsAndComplete()     // Catch: java.lang.Throwable -> L96
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L96
            r13.unlock(r12)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L96:
            r1 = move-exception
            r13.unlock(r12)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.common.consistency.impl.ConsistencyManager.setRywData(java.lang.String, com.onesignal.common.consistency.models.IConsistencyKeyEnum, com.onesignal.common.consistency.RywData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.common.consistency.models.IConsistencyManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getRywDataFromAwaitableCondition(com.onesignal.common.consistency.models.ICondition r10, kotlin.coroutines.Continuation<? super kotlinx.coroutines.CompletableDeferred<com.onesignal.common.consistency.RywData>> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.onesignal.common.consistency.impl.ConsistencyManager.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            com.onesignal.common.consistency.impl.ConsistencyManager$getRywDataFromAwaitableCondition$1 r0 = (com.onesignal.common.consistency.impl.ConsistencyManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.onesignal.common.consistency.impl.ConsistencyManager$getRywDataFromAwaitableCondition$1 r0 = new com.onesignal.common.consistency.impl.ConsistencyManager$getRywDataFromAwaitableCondition$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            switch(r2) {
                case 0: goto L40;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2e:
            r10 = 0
            java.lang.Object r1 = r0.L$2
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r2 = r0.L$1
            com.onesignal.common.consistency.models.ICondition r2 = (com.onesignal.common.consistency.models.ICondition) r2
            java.lang.Object r5 = r0.L$0
            com.onesignal.common.consistency.impl.ConsistencyManager r5 = (com.onesignal.common.consistency.impl.ConsistencyManager) r5
            kotlin.ResultKt.throwOnFailure(r11)
            r6 = r4
            goto L5b
        L40:
            kotlin.ResultKt.throwOnFailure(r11)
            r5 = r9
            r2 = r10
            kotlinx.coroutines.sync.Mutex r10 = r5.mutex
            r6 = 0
            r7 = 0
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r10
            r0.label = r3
            java.lang.Object r8 = r10.lock(r6, r0)
            if (r8 != r1) goto L59
            return r1
        L59:
            r1 = r10
            r10 = r7
        L5b:
            r7 = 0
            kotlinx.coroutines.CompletableDeferred r3 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r4, r3, r4)     // Catch: java.lang.Throwable -> L73
            kotlin.Pair r4 = new kotlin.Pair     // Catch: java.lang.Throwable -> L73
            r4.<init>(r2, r3)     // Catch: java.lang.Throwable -> L73
            java.util.List<kotlin.Pair<com.onesignal.common.consistency.models.ICondition, kotlinx.coroutines.CompletableDeferred<com.onesignal.common.consistency.RywData>>> r2 = r5.conditions     // Catch: java.lang.Throwable -> L73
            r2.add(r4)     // Catch: java.lang.Throwable -> L73
            r5.checkConditionsAndComplete()     // Catch: java.lang.Throwable -> L73
            r1.unlock(r6)
            return r3
        L73:
            r2 = move-exception
            r1.unlock(r6)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.common.consistency.impl.ConsistencyManager.getRywDataFromAwaitableCondition(com.onesignal.common.consistency.models.ICondition, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.onesignal.common.consistency.models.IConsistencyManager
    public Object resolveConditionsWithID(String id, Continuation<? super Unit> continuation) {
        List completedConditions = new ArrayList();
        for (Pair<ICondition, CompletableDeferred<RywData>> pair : this.conditions) {
            ICondition condition = (ICondition) pair.component1();
            CompletableDeferred deferred = (CompletableDeferred) pair.component2();
            if (Intrinsics.areEqual(condition.getId(), id) && !deferred.isCompleted()) {
                deferred.complete((Object) null);
            }
            completedConditions.add(new Pair(condition, deferred));
        }
        this.conditions.removeAll(completedConditions);
        return Unit.INSTANCE;
    }

    private final void checkConditionsAndComplete() {
        List completedConditions = new ArrayList();
        for (Pair<ICondition, CompletableDeferred<RywData>> pair : this.conditions) {
            ICondition condition = (ICondition) pair.component1();
            CompletableDeferred deferred = (CompletableDeferred) pair.component2();
            if (condition.isMet(this.indexedTokens)) {
                RywData rywDataForNewestToken = condition.getRywData(this.indexedTokens);
                if (!deferred.isCompleted()) {
                    deferred.complete(rywDataForNewestToken);
                }
                completedConditions.add(new Pair(condition, deferred));
            }
        }
        this.conditions.removeAll(completedConditions);
    }
}
