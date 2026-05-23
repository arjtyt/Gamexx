package com.onesignal.inAppMessages.internal.backend.impl;

import com.onesignal.core.internal.device.IDeviceService;
import com.onesignal.core.internal.http.IHttpClient;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.inAppMessages.BuildConfig;
import com.onesignal.inAppMessages.internal.InAppMessage;
import com.onesignal.inAppMessages.internal.InAppMessagePage;
import com.onesignal.inAppMessages.internal.backend.IInAppBackendService;
import com.onesignal.inAppMessages.internal.hydrators.InAppHydrator;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: InAppBackendService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ7\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J/\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J+\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ#\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\"J$\u0010#\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001c\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0002J\u0018\u0010$\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010%\u001a\u00020&H\u0002J?\u0010'\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010)J\"\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010\u000fH\u0002J\u0018\u0010/\u001a\u00020+2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u000fH\u0002JE\u00100\u001a\u00020+2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001c\u001a\u00020\u000f2\b\u00101\u001a\u0004\u0018\u00010\u000f2\u0006\u00102\u001a\u000203H\u0096@ø\u0001\u0000¢\u0006\u0002\u00104J3\u00105\u001a\u00020+2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001c\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u00106J=\u00107\u001a\u00020+2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001c\u001a\u00020\u000f2\b\u00108\u001a\u0004\u0018\u00010\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u00109R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006:"}, d2 = {"Lcom/onesignal/inAppMessages/internal/backend/impl/InAppBackendService;", "Lcom/onesignal/inAppMessages/internal/backend/IInAppBackendService;", "_httpClient", "Lcom/onesignal/core/internal/http/IHttpClient;", "_deviceService", "Lcom/onesignal/core/internal/device/IDeviceService;", "_hydrator", "Lcom/onesignal/inAppMessages/internal/hydrators/InAppHydrator;", "(Lcom/onesignal/core/internal/http/IHttpClient;Lcom/onesignal/core/internal/device/IDeviceService;Lcom/onesignal/inAppMessages/internal/hydrators/InAppHydrator;)V", "htmlNetworkRequestAttemptCount", "", "attemptFetchWithRetries", "", "Lcom/onesignal/inAppMessages/internal/InAppMessage;", "baseUrl", "", "rywData", "Lcom/onesignal/common/consistency/RywData;", "sessionDurationProvider", "Lkotlin/Function0;", "", "(Ljava/lang/String;Lcom/onesignal/common/consistency/RywData;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchInAppMessagesWithoutRywToken", "url", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIAMData", "Lcom/onesignal/inAppMessages/internal/backend/GetIAMDataResponse;", "appId", "messageId", "variantId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIAMPreviewData", "Lcom/onesignal/inAppMessages/internal/InAppMessageContent;", "previewUUID", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "htmlPathForMessage", "hydrateInAppMessages", "jsonResponse", "Lorg/json/JSONObject;", "listInAppMessages", "subscriptionId", "(Ljava/lang/String;Ljava/lang/String;Lcom/onesignal/common/consistency/RywData;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "printHttpErrorForInAppMessageRequest", "", "requestType", "statusCode", "response", "printHttpSuccessForInAppMessageRequest", "sendIAMClick", "clickId", "isFirstClick", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendIAMImpression", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendIAMPageImpression", InAppMessagePage.PAGE_ID, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InAppBackendService implements IInAppBackendService {
    private final IDeviceService _deviceService;
    private final IHttpClient _httpClient;
    private final InAppHydrator _hydrator;
    private int htmlNetworkRequestAttemptCount;

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$attemptFetchWithRetries$1, reason: invalid class name */
    /* JADX INFO: compiled from: InAppBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService", f = "InAppBackendService.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {224, 235, 247}, m = "attemptFetchWithRetries", n = {"this", "baseUrl", "rywData", "sessionDurationProvider", "attempts", "retryLimit", "this", "baseUrl", "rywData", "sessionDurationProvider", "attempts", "retryLimit"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "I$0", "I$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppBackendService.this.attemptFetchWithRetries(null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$fetchInAppMessagesWithoutRywToken$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService", f = "InAppBackendService.kt", i = {0}, l = {255}, m = "fetchInAppMessagesWithoutRywToken", n = {"this"}, s = {"L$0"})
    static final class C00511 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00511(Continuation<? super C00511> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppBackendService.this.fetchInAppMessagesWithoutRywToken(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$getIAMData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService", f = "InAppBackendService.kt", i = {0}, l = {49}, m = "getIAMData", n = {"this"}, s = {"L$0"})
    static final class C00521 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00521(Continuation<? super C00521> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppBackendService.this.getIAMData(null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$getIAMPreviewData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService", f = "InAppBackendService.kt", i = {0}, l = {79}, m = "getIAMPreviewData", n = {"this"}, s = {"L$0"})
    static final class C00531 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00531(Continuation<? super C00531> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppBackendService.this.getIAMPreviewData(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$listInAppMessages$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService", f = "InAppBackendService.kt", i = {0, 0, 0, 0, 0}, l = {34, 37}, m = "listInAppMessages", n = {"this", "appId", "subscriptionId", "rywData", "sessionDurationProvider"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C00541 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C00541(Continuation<? super C00541> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppBackendService.this.listInAppMessages(null, null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMClick$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService", f = "InAppBackendService.kt", i = {0}, l = {110}, m = "sendIAMClick", n = {"this"}, s = {"L$0"})
    static final class C00551 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00551(Continuation<? super C00551> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppBackendService.this.sendIAMClick(null, null, null, null, null, false, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMImpression$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService", f = "InAppBackendService.kt", i = {0}, l = {170}, m = "sendIAMImpression", n = {"this"}, s = {"L$0"})
    static final class C00561 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00561(Continuation<? super C00561> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppBackendService.this.sendIAMImpression(null, null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMPageImpression$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService", f = "InAppBackendService.kt", i = {0}, l = {143}, m = "sendIAMPageImpression", n = {"this"}, s = {"L$0"})
    static final class C00571 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00571(Continuation<? super C00571> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppBackendService.this.sendIAMPageImpression(null, null, null, null, null, (Continuation) this);
        }
    }

    public InAppBackendService(IHttpClient _httpClient, IDeviceService _deviceService, InAppHydrator _hydrator) {
        Intrinsics.checkNotNullParameter(_httpClient, "_httpClient");
        Intrinsics.checkNotNullParameter(_deviceService, "_deviceService");
        Intrinsics.checkNotNullParameter(_hydrator, "_hydrator");
        this._httpClient = _httpClient;
        this._deviceService = _deviceService;
        this._hydrator = _hydrator;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.inAppMessages.internal.backend.IInAppBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object listInAppMessages(java.lang.String r8, java.lang.String r9, com.onesignal.common.consistency.RywData r10, kotlin.jvm.functions.Function0<java.lang.Long> r11, kotlin.coroutines.Continuation<? super java.util.List<com.onesignal.inAppMessages.internal.InAppMessage>> r12) {
        /*
            r7 = this;
            boolean r0 = r12 instanceof com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00541
            if (r0 == 0) goto L14
            r0 = r12
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$listInAppMessages$1 r0 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00541) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$listInAppMessages$1 r0 = new com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$listInAppMessages$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L4a;
                case 1: goto L32;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r12
            goto Lad
        L32:
            java.lang.Object r8 = r0.L$4
            kotlin.jvm.functions.Function0 r8 = (kotlin.jvm.functions.Function0) r8
            java.lang.Object r9 = r0.L$3
            com.onesignal.common.consistency.RywData r9 = (com.onesignal.common.consistency.RywData) r9
            java.lang.Object r10 = r0.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r0.L$1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r2 = r0.L$0
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService r2 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L75
        L4a:
            kotlin.ResultKt.throwOnFailure(r12)
            r2 = r7
            java.lang.Long r3 = r10.getRywDelay()
            if (r3 == 0) goto L59
            long r3 = r3.longValue()
            goto L5b
        L59:
            r3 = 500(0x1f4, double:2.47E-321)
        L5b:
            r0.L$0 = r2
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r10
            r0.L$4 = r11
            r5 = 1
            r0.label = r5
            java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r3, r0)
            if (r3 != r1) goto L6f
            return r1
        L6f:
            r6 = r11
            r11 = r8
            r8 = r6
            r6 = r10
            r10 = r9
            r9 = r6
        L75:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "apps/"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r11)
            java.lang.String r4 = "/subscriptions/"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r10)
            java.lang.String r4 = "/iams"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r10 = r3.toString()
            r11 = 0
            r0.L$0 = r11
            r0.L$1 = r11
            r0.L$2 = r11
            r0.L$3 = r11
            r0.L$4 = r11
            r11 = 2
            r0.label = r11
            java.lang.Object r8 = r2.attemptFetchWithRetries(r10, r9, r8, r0)
            if (r8 != r1) goto Lad
            return r1
        Lad:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.listInAppMessages(java.lang.String, java.lang.String, com.onesignal.common.consistency.RywData, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.inAppMessages.internal.backend.IInAppBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getIAMData(java.lang.String r12, java.lang.String r13, java.lang.String r14, kotlin.coroutines.Continuation<? super com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse> r15) {
        /*
            r11 = this;
            boolean r0 = r15 instanceof com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00521
            if (r0 == 0) goto L14
            r0 = r15
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$getIAMData$1 r0 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00521) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$getIAMData$1 r0 = new com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$getIAMData$1
            r0.<init>(r15)
        L19:
            r4 = r0
            java.lang.Object r15 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 0
            r8 = 1
            r9 = 0
            switch(r1) {
                case 0: goto L3a;
                case 1: goto L30;
                default: goto L28;
            }
        L28:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L30:
            java.lang.Object r12 = r4.L$0
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService r12 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService) r12
            kotlin.ResultKt.throwOnFailure(r15)
            r10 = r12
            r12 = r15
            goto L5b
        L3a:
            kotlin.ResultKt.throwOnFailure(r15)
            r10 = r11
            java.lang.String r2 = r10.htmlPathForMessage(r13, r14, r12)
            if (r2 != 0) goto L4a
            com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse r12 = new com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse
            r12.<init>(r7, r9)
            return r12
        L4a:
            com.onesignal.core.internal.http.IHttpClient r1 = r10._httpClient
            r4.L$0 = r10
            r4.label = r8
            r3 = 0
            r5 = 2
            r6 = 0
            java.lang.Object r12 = com.onesignal.core.internal.http.IHttpClient.DefaultImpls.get$default(r1, r2, r3, r4, r5, r6)
            if (r12 != r0) goto L5b
            return r0
        L5b:
            com.onesignal.core.internal.http.HttpResponse r12 = (com.onesignal.core.internal.http.HttpResponse) r12
            boolean r13 = r12.isSuccess()
            if (r13 == 0) goto L7d
            r10.htmlNetworkRequestAttemptCount = r9
            org.json.JSONObject r13 = new org.json.JSONObject
            java.lang.String r14 = r12.getPayload()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            r13.<init>(r14)
            com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse r12 = new com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse
            com.onesignal.inAppMessages.internal.hydrators.InAppHydrator r14 = r10._hydrator
            com.onesignal.inAppMessages.internal.InAppMessageContent r14 = r14.hydrateIAMMessageContent(r13)
            r12.<init>(r14, r9)
            return r12
        L7d:
            int r13 = r12.getStatusCode()
            java.lang.String r14 = r12.getPayload()
            java.lang.String r0 = "html"
            r10.printHttpErrorForInAppMessageRequest(r0, r13, r14)
            com.onesignal.common.NetworkUtils r13 = com.onesignal.common.NetworkUtils.INSTANCE
            int r14 = r12.getStatusCode()
            com.onesignal.common.NetworkUtils$ResponseStatusType r13 = r13.getResponseStatusType(r14)
            com.onesignal.common.NetworkUtils$ResponseStatusType r14 = com.onesignal.common.NetworkUtils.ResponseStatusType.RETRYABLE
            if (r13 != r14) goto Lae
            int r12 = r10.htmlNetworkRequestAttemptCount
            com.onesignal.common.NetworkUtils r13 = com.onesignal.common.NetworkUtils.INSTANCE
            int r13 = r13.getMaxNetworkRequestAttemptCount()
            if (r12 < r13) goto La3
            goto Lae
        La3:
            int r12 = r10.htmlNetworkRequestAttemptCount
            int r12 = r12 + r8
            r10.htmlNetworkRequestAttemptCount = r12
            com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse r12 = new com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse
            r12.<init>(r7, r8)
            goto Lb5
        Lae:
            r10.htmlNetworkRequestAttemptCount = r9
            com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse r12 = new com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse
            r12.<init>(r7, r9)
        Lb5:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.getIAMData(java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.inAppMessages.internal.backend.IInAppBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getIAMPreviewData(java.lang.String r9, java.lang.String r10, kotlin.coroutines.Continuation<? super com.onesignal.inAppMessages.internal.InAppMessageContent> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00531
            if (r0 == 0) goto L14
            r0 = r11
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$getIAMPreviewData$1 r0 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00531) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$getIAMPreviewData$1 r0 = new com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$getIAMPreviewData$1
            r0.<init>(r11)
        L19:
            r4 = r0
            java.lang.Object r11 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            switch(r1) {
                case 0: goto L37;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2d:
            java.lang.Object r9 = r4.L$0
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService r9 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService) r9
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r9
            r9 = r11
            goto L69
        L37:
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r8
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "in_app_messages/device_preview?preview_id="
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r10)
            java.lang.String r2 = "&app_id="
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.String r2 = r1.toString()
            com.onesignal.core.internal.http.IHttpClient r1 = r7._httpClient
            r4.L$0 = r7
            r9 = 1
            r4.label = r9
            r3 = 0
            r5 = 2
            r6 = 0
            java.lang.Object r9 = com.onesignal.core.internal.http.IHttpClient.DefaultImpls.get$default(r1, r2, r3, r4, r5, r6)
            if (r9 != r0) goto L69
            return r0
        L69:
            com.onesignal.core.internal.http.HttpResponse r9 = (com.onesignal.core.internal.http.HttpResponse) r9
            boolean r10 = r9.isSuccess()
            if (r10 == 0) goto L84
            org.json.JSONObject r10 = new org.json.JSONObject
            java.lang.String r0 = r9.getPayload()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r10.<init>(r0)
            com.onesignal.inAppMessages.internal.hydrators.InAppHydrator r9 = r7._hydrator
            com.onesignal.inAppMessages.internal.InAppMessageContent r9 = r9.hydrateIAMMessageContent(r10)
            goto L95
        L84:
            int r10 = r9.getStatusCode()
            java.lang.String r0 = r9.getPayload()
            java.lang.String r1 = "html"
            r7.printHttpErrorForInAppMessageRequest(r1, r10, r0)
            r9 = 0
            r10 = r9
            com.onesignal.inAppMessages.internal.InAppMessageContent r10 = (com.onesignal.inAppMessages.internal.InAppMessageContent) r10
        L95:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.getIAMPreviewData(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    @Override // com.onesignal.inAppMessages.internal.backend.IInAppBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object sendIAMClick(final java.lang.String r12, final java.lang.String r13, final java.lang.String r14, java.lang.String r15, final java.lang.String r16, boolean r17, kotlin.coroutines.Continuation<? super kotlin.Unit> r18) throws com.onesignal.common.exceptions.BackendException {
        /*
            r11 = this;
            r0 = r18
            boolean r1 = r0 instanceof com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00551
            if (r1 == 0) goto L16
            r1 = r0
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMClick$1 r1 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00551) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMClick$1 r1 = new com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMClick$1
            r1.<init>(r0)
        L1b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            switch(r3) {
                case 0: goto L38;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L2e:
            java.lang.Object r12 = r1.L$0
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService r12 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService) r12
            kotlin.ResultKt.throwOnFailure(r0)
            r5 = r12
            r12 = r0
            goto L86
        L38:
            kotlin.ResultKt.throwOnFailure(r0)
            r5 = r11
            r6 = r13
            r13 = r17
            r10 = r15
            r4 = r12
            r8 = r14
            r7 = r16
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMClick$json$1 r3 = new com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMClick$json$1
            r12 = 1
            if (r13 == 0) goto L4b
            r9 = r12
            goto L4d
        L4b:
            r13 = 0
            r9 = r13
        L4d:
            r3.<init>(r4, r5, r6, r7, r8, r9)
            org.json.JSONObject r3 = (org.json.JSONObject) r3
            com.onesignal.core.internal.http.IHttpClient r13 = r5._httpClient
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "in_app_messages/"
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.StringBuilder r4 = r4.append(r10)
            java.lang.String r6 = "/click"
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r4 = r4.toString()
            r1.L$0 = r5
            r1.label = r12
            r12 = 0
            r6 = 4
            r7 = 0
            r15 = r12
            r12 = r13
            r16 = r1
            r14 = r3
            r13 = r4
            r17 = r6
            r18 = r7
            java.lang.Object r12 = com.onesignal.core.internal.http.IHttpClient.DefaultImpls.post$default(r12, r13, r14, r15, r16, r17, r18)
            if (r12 != r2) goto L86
            return r2
        L86:
            com.onesignal.core.internal.http.HttpResponse r12 = (com.onesignal.core.internal.http.HttpResponse) r12
            boolean r13 = r12.isSuccess()
            java.lang.String r2 = "engagement"
            if (r13 == 0) goto L9d
            java.lang.String r13 = r12.getPayload()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
            r5.printHttpSuccessForInAppMessageRequest(r2, r13)
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L9d:
            int r13 = r12.getStatusCode()
            java.lang.String r3 = r12.getPayload()
            r5.printHttpErrorForInAppMessageRequest(r2, r13, r3)
            com.onesignal.common.exceptions.BackendException r13 = new com.onesignal.common.exceptions.BackendException
            int r2 = r12.getStatusCode()
            java.lang.String r3 = r12.getPayload()
            java.lang.Integer r4 = r12.getRetryAfterSeconds()
            r13.<init>(r2, r3, r4)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.sendIAMClick(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    @Override // com.onesignal.inAppMessages.internal.backend.IInAppBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object sendIAMPageImpression(final java.lang.String r13, final java.lang.String r14, final java.lang.String r15, java.lang.String r16, final java.lang.String r17, kotlin.coroutines.Continuation<? super kotlin.Unit> r18) throws com.onesignal.common.exceptions.BackendException {
        /*
            r12 = this;
            r0 = r18
            boolean r1 = r0 instanceof com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00571
            if (r1 == 0) goto L16
            r1 = r0
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMPageImpression$1 r1 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00571) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMPageImpression$1 r1 = new com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMPageImpression$1
            r1.<init>(r0)
        L1b:
            r7 = r1
            java.lang.Object r0 = r7.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r7.label
            switch(r3) {
                case 0: goto L38;
                case 1: goto L2f;
                default: goto L27;
            }
        L27:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2f:
            java.lang.Object r1 = r7.L$0
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService r1 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService) r1
            kotlin.ResultKt.throwOnFailure(r0)
            r3 = r0
            goto L89
        L38:
            kotlin.ResultKt.throwOnFailure(r0)
            r3 = r12
            r4 = r14
            r10 = r16
            r5 = r13
            r6 = r15
            r8 = r17
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMPageImpression$json$1 r9 = new com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMPageImpression$json$1
            r17 = r3
            r15 = r4
            r14 = r5
            r16 = r6
            r18 = r8
            r13 = r9
            r13.<init>(r14, r15, r16, r17, r18)
            r8 = r13
            r4 = r14
            r3 = r15
            r5 = r16
            r11 = r17
            r6 = r18
            r5 = r8
            org.json.JSONObject r5 = (org.json.JSONObject) r5
            com.onesignal.core.internal.http.IHttpClient r3 = r11._httpClient
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "in_app_messages/"
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.StringBuilder r4 = r4.append(r10)
            java.lang.String r6 = "/pageImpression"
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r4 = r4.toString()
            r7.L$0 = r11
            r6 = 1
            r7.label = r6
            r6 = 0
            r8 = 4
            r9 = 0
            java.lang.Object r3 = com.onesignal.core.internal.http.IHttpClient.DefaultImpls.post$default(r3, r4, r5, r6, r7, r8, r9)
            if (r3 != r1) goto L88
            return r1
        L88:
            r1 = r11
        L89:
            com.onesignal.core.internal.http.HttpResponse r3 = (com.onesignal.core.internal.http.HttpResponse) r3
            boolean r4 = r3.isSuccess()
            java.lang.String r5 = "page impression"
            if (r4 == 0) goto La0
            java.lang.String r4 = r3.getPayload()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            r1.printHttpSuccessForInAppMessageRequest(r5, r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        La0:
            int r4 = r3.getStatusCode()
            java.lang.String r6 = r3.getPayload()
            r1.printHttpErrorForInAppMessageRequest(r5, r4, r6)
            com.onesignal.common.exceptions.BackendException r4 = new com.onesignal.common.exceptions.BackendException
            int r5 = r3.getStatusCode()
            java.lang.String r6 = r3.getPayload()
            java.lang.Integer r8 = r3.getRetryAfterSeconds()
            r4.<init>(r5, r6, r8)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.sendIAMPageImpression(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.inAppMessages.internal.backend.IInAppBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object sendIAMImpression(final java.lang.String r10, final java.lang.String r11, final java.lang.String r12, java.lang.String r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) throws com.onesignal.common.exceptions.BackendException {
        /*
            r9 = this;
            boolean r0 = r14 instanceof com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00561
            if (r0 == 0) goto L14
            r0 = r14
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMImpression$1 r0 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00561) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMImpression$1 r0 = new com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMImpression$1
            r0.<init>(r14)
        L19:
            r5 = r0
            java.lang.Object r14 = r5.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            switch(r1) {
                case 0: goto L37;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2d:
            java.lang.Object r10 = r5.L$0
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService r10 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService) r10
            kotlin.ResultKt.throwOnFailure(r14)
            r8 = r10
            r10 = r14
            goto L6e
        L37:
            kotlin.ResultKt.throwOnFailure(r14)
            r8 = r9
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMImpression$json$1 r1 = new com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$sendIAMImpression$json$1
            r1.<init>(r10, r11, r12, r8)
            r3 = r1
            org.json.JSONObject r3 = (org.json.JSONObject) r3
            com.onesignal.core.internal.http.IHttpClient r1 = r8._httpClient
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "in_app_messages/"
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.StringBuilder r10 = r10.append(r13)
            java.lang.String r11 = "/impression"
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r2 = r10.toString()
            r5.L$0 = r8
            r10 = 1
            r5.label = r10
            r4 = 0
            r6 = 4
            r7 = 0
            java.lang.Object r10 = com.onesignal.core.internal.http.IHttpClient.DefaultImpls.post$default(r1, r2, r3, r4, r5, r6, r7)
            if (r10 != r0) goto L6e
            return r0
        L6e:
            com.onesignal.core.internal.http.HttpResponse r10 = (com.onesignal.core.internal.http.HttpResponse) r10
            boolean r11 = r10.isSuccess()
            java.lang.String r12 = "impression"
            if (r11 == 0) goto L85
            java.lang.String r11 = r10.getPayload()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            r8.printHttpSuccessForInAppMessageRequest(r12, r11)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L85:
            int r11 = r10.getStatusCode()
            java.lang.String r13 = r10.getPayload()
            r8.printHttpErrorForInAppMessageRequest(r12, r11, r13)
            com.onesignal.common.exceptions.BackendException r11 = new com.onesignal.common.exceptions.BackendException
            int r12 = r10.getStatusCode()
            java.lang.String r13 = r10.getPayload()
            java.lang.Integer r0 = r10.getRetryAfterSeconds()
            r11.<init>(r12, r13, r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.sendIAMImpression(java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String htmlPathForMessage(String messageId, String variantId, String appId) {
        if (variantId == null) {
            Logging.error$default("Unable to find a variant for in-app message " + messageId, null, 2, null);
            return null;
        }
        return "in_app_messages/" + messageId + "/variants/" + variantId + "/html?app_id=" + appId;
    }

    private final void printHttpSuccessForInAppMessageRequest(String requestType, String response) {
        Logging.debug$default("Successful post for in-app message " + requestType + " request: " + response, null, 2, null);
    }

    private final void printHttpErrorForInAppMessageRequest(String requestType, int statusCode, String response) {
        Logging.error$default("Encountered a " + statusCode + " error while attempting in-app message " + requestType + " request: " + response, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x014d -> B:55:0x0154). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0159 -> B:57:0x0162). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object attemptFetchWithRetries(java.lang.String r22, com.onesignal.common.consistency.RywData r23, kotlin.jvm.functions.Function0<java.lang.Long> r24, kotlin.coroutines.Continuation<? super java.util.List<com.onesignal.inAppMessages.internal.InAppMessage>> r25) {
        /*
            Method dump skipped, instruction units count: 396
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.attemptFetchWithRetries(java.lang.String, com.onesignal.common.consistency.RywData, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object fetchInAppMessagesWithoutRywToken(java.lang.String r12, kotlin.jvm.functions.Function0<java.lang.Long> r13, kotlin.coroutines.Continuation<? super java.util.List<com.onesignal.inAppMessages.internal.InAppMessage>> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00511
            if (r0 == 0) goto L14
            r0 = r14
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$fetchInAppMessagesWithoutRywToken$1 r0 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.C00511) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$fetchInAppMessagesWithoutRywToken$1 r0 = new com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService$fetchInAppMessagesWithoutRywToken$1
            r0.<init>(r14)
        L19:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L2c:
            java.lang.Object r12 = r0.L$0
            com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService r12 = (com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService) r12
            kotlin.ResultKt.throwOnFailure(r14)
            r2 = r12
            r12 = r14
            goto L5a
        L36:
            kotlin.ResultKt.throwOnFailure(r14)
            r2 = r11
            com.onesignal.core.internal.http.IHttpClient r3 = r2._httpClient
            com.onesignal.core.internal.http.impl.OptionalHeaders r4 = new com.onesignal.core.internal.http.impl.OptionalHeaders
            java.lang.Object r5 = r13.invoke()
            r8 = r5
            java.lang.Long r8 = (java.lang.Long) r8
            r9 = 7
            r10 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r0.L$0 = r2
            r13 = 1
            r0.label = r13
            java.lang.Object r12 = r3.get(r12, r4, r0)
            if (r12 != r1) goto L5a
            return r1
        L5a:
            com.onesignal.core.internal.http.HttpResponse r12 = (com.onesignal.core.internal.http.HttpResponse) r12
            boolean r13 = r12.isSuccess()
            r1 = 0
            if (r13 == 0) goto L7b
            java.lang.String r12 = r12.getPayload()
            if (r12 == 0) goto L71
            r13 = 0
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>(r12)
            goto L72
        L71:
            r3 = r1
        L72:
            if (r3 == 0) goto L7a
            r12 = 0
            java.util.List r1 = r2.hydrateInAppMessages(r3)
        L7a:
            return r1
        L7b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.backend.impl.InAppBackendService.fetchInAppMessagesWithoutRywToken(java.lang.String, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final List<InAppMessage> hydrateInAppMessages(JSONObject jsonResponse) throws JSONException {
        if (jsonResponse.has("in_app_messages")) {
            JSONArray iamMessagesAsJSON = jsonResponse.getJSONArray("in_app_messages");
            InAppHydrator inAppHydrator = this._hydrator;
            Intrinsics.checkNotNullExpressionValue(iamMessagesAsJSON, "iamMessagesAsJSON");
            return inAppHydrator.hydrateIAMMessages(iamMessagesAsJSON);
        }
        return null;
    }
}
