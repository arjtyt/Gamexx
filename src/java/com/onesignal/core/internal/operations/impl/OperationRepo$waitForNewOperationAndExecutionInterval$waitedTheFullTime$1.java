package com.onesignal.core.internal.operations.impl;

import com.onesignal.core.internal.operations.impl.OperationRepo;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: OperationRepo.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.onesignal.core.internal.operations.impl.OperationRepo$waitForNewOperationAndExecutionInterval$waitedTheFullTime$1", f = "OperationRepo.kt", i = {}, l = {227}, m = "invokeSuspend", n = {}, s = {})
final class OperationRepo$waitForNewOperationAndExecutionInterval$waitedTheFullTime$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<OperationRepo.LoopWaiterMessage> $wakeMessage;
    Object L$0;
    int label;
    final /* synthetic */ OperationRepo this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OperationRepo$waitForNewOperationAndExecutionInterval$waitedTheFullTime$1(Ref.ObjectRef<OperationRepo.LoopWaiterMessage> objectRef, OperationRepo operationRepo, Continuation<? super OperationRepo$waitForNewOperationAndExecutionInterval$waitedTheFullTime$1> continuation) {
        super(2, continuation);
        this.$wakeMessage = objectRef;
        this.this$0 = operationRepo;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OperationRepo$waitForNewOperationAndExecutionInterval$waitedTheFullTime$1(this.$wakeMessage, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        Ref.ObjectRef<OperationRepo.LoopWaiterMessage> objectRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                objectRef = this.$wakeMessage;
                this.L$0 = objectRef;
                this.label = 1;
                Object objWaitForWake = this.this$0.waiter.waitForWake((Continuation) this);
                if (objWaitForWake == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = objWaitForWake;
                break;
            case 1:
                Ref.ObjectRef<OperationRepo.LoopWaiterMessage> objectRef2 = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure($result);
                objectRef = objectRef2;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        objectRef.element = $result;
        return Unit.INSTANCE;
    }
}
