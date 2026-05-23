package com.onesignal.inAppMessages.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: InAppMessagesManager.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$identityModelChangeHandler$1$onModelUpdated$1", f = "InAppMessagesManager.kt", i = {}, l = {139, 140, 142}, m = "invokeSuspend", n = {}, s = {})
final class InAppMessagesManager$identityModelChangeHandler$1$onModelUpdated$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ String $newOneSignalId;
    int label;
    final /* synthetic */ InAppMessagesManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    InAppMessagesManager$identityModelChangeHandler$1$onModelUpdated$1(InAppMessagesManager inAppMessagesManager, String str, Continuation<? super InAppMessagesManager$identityModelChangeHandler$1$onModelUpdated$1> continuation) {
        super(1, continuation);
        this.this$0 = inAppMessagesManager;
        this.$newOneSignalId = str;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new InAppMessagesManager$identityModelChangeHandler$1$onModelUpdated$1(this.this$0, this.$newOneSignalId, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return create(continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0057 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            switch(r1) {
                case 0: goto L24;
                case 1: goto L1d;
                case 2: goto L16;
                case 3: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L11:
            r0 = r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6d
        L16:
            r1 = r7
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r1
            r1 = r8
            goto L58
        L1d:
            r1 = r7
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r1
            r1 = r8
            goto L48
        L24:
            kotlin.ResultKt.throwOnFailure(r8)
            r1 = r7
            com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = r1.this$0
            com.onesignal.common.consistency.models.IConsistencyManager r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$get_consistencyManager$p(r2)
            com.onesignal.common.consistency.IamFetchReadyCondition r3 = new com.onesignal.common.consistency.IamFetchReadyCondition
            java.lang.String r4 = r1.$newOneSignalId
            r3.<init>(r4)
            com.onesignal.common.consistency.models.ICondition r3 = (com.onesignal.common.consistency.models.ICondition) r3
            r4 = r1
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5 = 1
            r1.label = r5
            java.lang.Object r2 = r2.getRywDataFromAwaitableCondition(r3, r4)
            if (r2 != r0) goto L44
            return r0
        L44:
            r6 = r1
            r1 = r8
            r8 = r2
            r2 = r6
        L48:
            kotlinx.coroutines.CompletableDeferred r8 = (kotlinx.coroutines.CompletableDeferred) r8
            r3 = r2
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 2
            r2.label = r4
            java.lang.Object r8 = r8.await(r3)
            if (r8 != r0) goto L58
            return r0
        L58:
            com.onesignal.common.consistency.RywData r8 = (com.onesignal.common.consistency.RywData) r8
            if (r8 == 0) goto L6f
            com.onesignal.inAppMessages.internal.InAppMessagesManager r3 = r2.this$0
            r4 = r2
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5 = 3
            r2.label = r5
            java.lang.Object r8 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$fetchMessages(r3, r8, r4)
            if (r8 != r0) goto L6b
            return r0
        L6b:
            r8 = r1
            r0 = r2
        L6d:
            r1 = r8
            r2 = r0
        L6f:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager$identityModelChangeHandler$1$onModelUpdated$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
