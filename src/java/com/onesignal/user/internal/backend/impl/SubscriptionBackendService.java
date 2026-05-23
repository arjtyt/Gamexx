package com.onesignal.user.internal.backend.impl;

import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.http.IHttpClient;
import com.onesignal.user.internal.backend.ISubscriptionBackendService;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubscriptionBackendService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JA\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J-\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00142\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J1\u0010\u0015\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J+\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lcom/onesignal/user/internal/backend/impl/SubscriptionBackendService;", "Lcom/onesignal/user/internal/backend/ISubscriptionBackendService;", "_httpClient", "Lcom/onesignal/core/internal/http/IHttpClient;", "(Lcom/onesignal/core/internal/http/IHttpClient;)V", "createSubscription", "Lkotlin/Pair;", "", "Lcom/onesignal/common/consistency/RywData;", "appId", "aliasLabel", "aliasValue", "subscription", "Lcom/onesignal/user/internal/backend/SubscriptionObject;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/onesignal/user/internal/backend/SubscriptionObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSubscription", "", "subscriptionId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIdentityFromSubscription", "", "transferSubscription", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSubscription", "(Ljava/lang/String;Ljava/lang/String;Lcom/onesignal/user/internal/backend/SubscriptionObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubscriptionBackendService implements ISubscriptionBackendService {
    private final IHttpClient _httpClient;

    /* JADX INFO: renamed from: com.onesignal.user.internal.backend.impl.SubscriptionBackendService$createSubscription$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubscriptionBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.user.internal.backend.impl.SubscriptionBackendService", f = "SubscriptionBackendService.kt", i = {}, l = {27}, m = "createSubscription", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionBackendService.this.createSubscription(null, null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.user.internal.backend.impl.SubscriptionBackendService$deleteSubscription$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubscriptionBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.user.internal.backend.impl.SubscriptionBackendService", f = "SubscriptionBackendService.kt", i = {}, l = {81}, m = "deleteSubscription", n = {}, s = {})
    static final class C01601 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01601(Continuation<? super C01601> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionBackendService.this.deleteSubscription(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.user.internal.backend.impl.SubscriptionBackendService$getIdentityFromSubscription$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubscriptionBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.user.internal.backend.impl.SubscriptionBackendService", f = "SubscriptionBackendService.kt", i = {}, l = {109}, m = "getIdentityFromSubscription", n = {}, s = {})
    static final class C01611 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01611(Continuation<? super C01611> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionBackendService.this.getIdentityFromSubscription(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.user.internal.backend.impl.SubscriptionBackendService$transferSubscription$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubscriptionBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.user.internal.backend.impl.SubscriptionBackendService", f = "SubscriptionBackendService.kt", i = {}, l = {98}, m = "transferSubscription", n = {}, s = {})
    static final class C01621 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01621(Continuation<? super C01621> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionBackendService.this.transferSubscription(null, null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.user.internal.backend.impl.SubscriptionBackendService$updateSubscription$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubscriptionBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.user.internal.backend.impl.SubscriptionBackendService", f = "SubscriptionBackendService.kt", i = {}, l = {59}, m = "updateSubscription", n = {}, s = {})
    static final class C01631 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01631(Continuation<? super C01631> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionBackendService.this.updateSubscription(null, null, null, (Continuation) this);
        }
    }

    public SubscriptionBackendService(IHttpClient _httpClient) {
        Intrinsics.checkNotNullParameter(_httpClient, "_httpClient");
        this._httpClient = _httpClient;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @Override // com.onesignal.user.internal.backend.ISubscriptionBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object createSubscription(java.lang.String r17, java.lang.String r18, java.lang.String r19, com.onesignal.user.internal.backend.SubscriptionObject r20, kotlin.coroutines.Continuation<? super kotlin.Pair<java.lang.String, com.onesignal.common.consistency.RywData>> r21) throws com.onesignal.common.exceptions.BackendException, org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.user.internal.backend.impl.SubscriptionBackendService.createSubscription(java.lang.String, java.lang.String, java.lang.String, com.onesignal.user.internal.backend.SubscriptionObject, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.user.internal.backend.ISubscriptionBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object updateSubscription(java.lang.String r10, java.lang.String r11, com.onesignal.user.internal.backend.SubscriptionObject r12, kotlin.coroutines.Continuation<? super com.onesignal.common.consistency.RywData> r13) throws com.onesignal.common.exceptions.BackendException, org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.user.internal.backend.impl.SubscriptionBackendService.updateSubscription(java.lang.String, java.lang.String, com.onesignal.user.internal.backend.SubscriptionObject, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.user.internal.backend.ISubscriptionBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object deleteSubscription(java.lang.String r9, java.lang.String r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) throws com.onesignal.common.exceptions.BackendException {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.onesignal.user.internal.backend.impl.SubscriptionBackendService.C01601
            if (r0 == 0) goto L14
            r0 = r11
            com.onesignal.user.internal.backend.impl.SubscriptionBackendService$deleteSubscription$1 r0 = (com.onesignal.user.internal.backend.impl.SubscriptionBackendService.C01601) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.onesignal.user.internal.backend.impl.SubscriptionBackendService$deleteSubscription$1 r0 = new com.onesignal.user.internal.backend.impl.SubscriptionBackendService$deleteSubscription$1
            r0.<init>(r11)
        L19:
            r4 = r0
            java.lang.Object r11 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            switch(r1) {
                case 0: goto L32;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2d:
            kotlin.ResultKt.throwOnFailure(r11)
            r9 = r11
            goto L62
        L32:
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r8
            com.onesignal.core.internal.http.IHttpClient r1 = r7._httpClient
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "apps/"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r9)
            java.lang.String r3 = "/subscriptions/"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r10)
            java.lang.String r2 = r2.toString()
            r3 = 1
            r4.label = r3
            r3 = 0
            r5 = 2
            r6 = 0
            java.lang.Object r9 = com.onesignal.core.internal.http.IHttpClient.DefaultImpls.delete$default(r1, r2, r3, r4, r5, r6)
            if (r9 != r0) goto L62
            return r0
        L62:
            com.onesignal.core.internal.http.HttpResponse r9 = (com.onesignal.core.internal.http.HttpResponse) r9
            boolean r10 = r9.isSuccess()
            if (r10 == 0) goto L6d
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L6d:
            com.onesignal.common.exceptions.BackendException r10 = new com.onesignal.common.exceptions.BackendException
            int r0 = r9.getStatusCode()
            java.lang.String r1 = r9.getPayload()
            java.lang.Integer r2 = r9.getRetryAfterSeconds()
            r10.<init>(r0, r1, r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.user.internal.backend.impl.SubscriptionBackendService.deleteSubscription(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.user.internal.backend.ISubscriptionBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object transferSubscription(java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) throws com.onesignal.common.exceptions.BackendException, org.json.JSONException {
        /*
            r9 = this;
            boolean r0 = r14 instanceof com.onesignal.user.internal.backend.impl.SubscriptionBackendService.C01621
            if (r0 == 0) goto L14
            r0 = r14
            com.onesignal.user.internal.backend.impl.SubscriptionBackendService$transferSubscription$1 r0 = (com.onesignal.user.internal.backend.impl.SubscriptionBackendService.C01621) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            com.onesignal.user.internal.backend.impl.SubscriptionBackendService$transferSubscription$1 r0 = new com.onesignal.user.internal.backend.impl.SubscriptionBackendService$transferSubscription$1
            r0.<init>(r14)
        L19:
            r5 = r0
            java.lang.Object r14 = r5.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            switch(r1) {
                case 0: goto L32;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2d:
            kotlin.ResultKt.throwOnFailure(r14)
            r10 = r14
            goto L82
        L32:
            kotlin.ResultKt.throwOnFailure(r14)
            r8 = r9
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            org.json.JSONObject r2 = r2.put(r12, r13)
            java.lang.String r3 = "identity"
            org.json.JSONObject r3 = r1.put(r3, r2)
            com.onesignal.core.internal.http.IHttpClient r1 = r8._httpClient
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "apps/"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r10)
            java.lang.String r13 = "/subscriptions/"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r11)
            java.lang.String r13 = "/owner"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r2 = r12.toString()
            java.lang.String r12 = "requestJSON"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r12)
            r12 = 1
            r5.label = r12
            r4 = 0
            r6 = 4
            r7 = 0
            java.lang.Object r10 = com.onesignal.core.internal.http.IHttpClient.DefaultImpls.patch$default(r1, r2, r3, r4, r5, r6, r7)
            if (r10 != r0) goto L82
            return r0
        L82:
            com.onesignal.core.internal.http.HttpResponse r10 = (com.onesignal.core.internal.http.HttpResponse) r10
            boolean r11 = r10.isSuccess()
            if (r11 == 0) goto L8d
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L8d:
            com.onesignal.common.exceptions.BackendException r11 = new com.onesignal.common.exceptions.BackendException
            int r12 = r10.getStatusCode()
            java.lang.String r13 = r10.getPayload()
            java.lang.Integer r0 = r10.getRetryAfterSeconds()
            r11.<init>(r12, r13, r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.user.internal.backend.impl.SubscriptionBackendService.transferSubscription(java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.user.internal.backend.ISubscriptionBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getIdentityFromSubscription(java.lang.String r10, java.lang.String r11, kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, java.lang.String>> r12) throws com.onesignal.common.exceptions.BackendException {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.user.internal.backend.impl.SubscriptionBackendService.getIdentityFromSubscription(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
