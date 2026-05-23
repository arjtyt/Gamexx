package com.onesignal.notifications.internal.backend.impl;

import com.onesignal.core.internal.http.IHttpClient;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.internal.backend.INotificationBackendService;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationBackendService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J1\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\rJ1\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/onesignal/notifications/internal/backend/impl/NotificationBackendService;", "Lcom/onesignal/notifications/internal/backend/INotificationBackendService;", "_httpClient", "Lcom/onesignal/core/internal/http/IHttpClient;", "(Lcom/onesignal/core/internal/http/IHttpClient;)V", "updateNotificationAsOpened", "", "appId", "", "notificationId", "subscriptionId", "deviceType", "Lcom/onesignal/core/internal/device/IDeviceService$DeviceType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/onesignal/core/internal/device/IDeviceService$DeviceType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNotificationAsReceived", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationBackendService implements INotificationBackendService {
    private final IHttpClient _httpClient;

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.backend.impl.NotificationBackendService$updateNotificationAsOpened$1, reason: invalid class name */
    /* JADX INFO: compiled from: NotificationBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.backend.impl.NotificationBackendService", f = "NotificationBackendService.kt", i = {}, l = {43}, m = "updateNotificationAsOpened", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationBackendService.this.updateNotificationAsOpened(null, null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.backend.impl.NotificationBackendService$updateNotificationAsReceived$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NotificationBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.backend.impl.NotificationBackendService", f = "NotificationBackendService.kt", i = {}, l = {24}, m = "updateNotificationAsReceived", n = {}, s = {})
    static final class C00951 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C00951(Continuation<? super C00951> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationBackendService.this.updateNotificationAsReceived(null, null, null, null, (Continuation) this);
        }
    }

    public NotificationBackendService(IHttpClient _httpClient) {
        Intrinsics.checkNotNullParameter(_httpClient, "_httpClient");
        this._httpClient = _httpClient;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.notifications.internal.backend.INotificationBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object updateNotificationAsReceived(java.lang.String r10, java.lang.String r11, java.lang.String r12, com.onesignal.core.internal.device.IDeviceService.DeviceType r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) throws com.onesignal.common.exceptions.BackendException, org.json.JSONException {
        /*
            r9 = this;
            boolean r0 = r14 instanceof com.onesignal.notifications.internal.backend.impl.NotificationBackendService.C00951
            if (r0 == 0) goto L14
            r0 = r14
            com.onesignal.notifications.internal.backend.impl.NotificationBackendService$updateNotificationAsReceived$1 r0 = (com.onesignal.notifications.internal.backend.impl.NotificationBackendService.C00951) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            com.onesignal.notifications.internal.backend.impl.NotificationBackendService$updateNotificationAsReceived$1 r0 = new com.onesignal.notifications.internal.backend.impl.NotificationBackendService$updateNotificationAsReceived$1
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
            goto L7f
        L32:
            kotlin.ResultKt.throwOnFailure(r14)
            r8 = r9
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r2 = "app_id"
            org.json.JSONObject r10 = r1.put(r2, r10)
            java.lang.String r1 = "player_id"
            org.json.JSONObject r10 = r10.put(r1, r12)
            java.lang.String r12 = "device_type"
            int r1 = r13.getValue()
            org.json.JSONObject r3 = r10.put(r12, r1)
            java.lang.String r10 = "JSONObject()\n           …_type\", deviceType.value)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r10)
            com.onesignal.core.internal.http.IHttpClient r1 = r8._httpClient
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r12 = "notifications/"
            java.lang.StringBuilder r10 = r10.append(r12)
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r12 = "/report_received"
            java.lang.StringBuilder r10 = r10.append(r12)
            java.lang.String r2 = r10.toString()
            r10 = 1
            r5.label = r10
            r4 = 0
            r6 = 4
            r7 = 0
            java.lang.Object r10 = com.onesignal.core.internal.http.IHttpClient.DefaultImpls.put$default(r1, r2, r3, r4, r5, r6, r7)
            if (r10 != r0) goto L7f
            return r0
        L7f:
            com.onesignal.core.internal.http.HttpResponse r10 = (com.onesignal.core.internal.http.HttpResponse) r10
            boolean r11 = r10.isSuccess()
            if (r11 == 0) goto L8a
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L8a:
            com.onesignal.common.exceptions.BackendException r11 = new com.onesignal.common.exceptions.BackendException
            int r12 = r10.getStatusCode()
            java.lang.String r13 = r10.getPayload()
            java.lang.Integer r0 = r10.getRetryAfterSeconds()
            r11.<init>(r12, r13, r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.backend.impl.NotificationBackendService.updateNotificationAsReceived(java.lang.String, java.lang.String, java.lang.String, com.onesignal.core.internal.device.IDeviceService$DeviceType, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.notifications.internal.backend.INotificationBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object updateNotificationAsOpened(java.lang.String r10, java.lang.String r11, java.lang.String r12, com.onesignal.core.internal.device.IDeviceService.DeviceType r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) throws com.onesignal.common.exceptions.BackendException, org.json.JSONException {
        /*
            r9 = this;
            boolean r0 = r14 instanceof com.onesignal.notifications.internal.backend.impl.NotificationBackendService.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r14
            com.onesignal.notifications.internal.backend.impl.NotificationBackendService$updateNotificationAsOpened$1 r0 = (com.onesignal.notifications.internal.backend.impl.NotificationBackendService.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            com.onesignal.notifications.internal.backend.impl.NotificationBackendService$updateNotificationAsOpened$1 r0 = new com.onesignal.notifications.internal.backend.impl.NotificationBackendService$updateNotificationAsOpened$1
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
            goto L75
        L32:
            kotlin.ResultKt.throwOnFailure(r14)
            r8 = r9
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r1 = "app_id"
            r3.put(r1, r10)
            java.lang.String r10 = "player_id"
            r3.put(r10, r12)
            java.lang.String r10 = "opened"
            r12 = 1
            r3.put(r10, r12)
            java.lang.String r10 = "device_type"
            int r1 = r13.getValue()
            r3.put(r10, r1)
            com.onesignal.core.internal.http.IHttpClient r1 = r8._httpClient
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r13 = "notifications/"
            java.lang.StringBuilder r10 = r10.append(r13)
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r2 = r10.toString()
            r5.label = r12
            r4 = 0
            r6 = 4
            r7 = 0
            java.lang.Object r10 = com.onesignal.core.internal.http.IHttpClient.DefaultImpls.put$default(r1, r2, r3, r4, r5, r6, r7)
            if (r10 != r0) goto L75
            return r0
        L75:
            com.onesignal.core.internal.http.HttpResponse r10 = (com.onesignal.core.internal.http.HttpResponse) r10
            boolean r11 = r10.isSuccess()
            if (r11 == 0) goto L80
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L80:
            com.onesignal.common.exceptions.BackendException r11 = new com.onesignal.common.exceptions.BackendException
            int r12 = r10.getStatusCode()
            java.lang.String r13 = r10.getPayload()
            java.lang.Integer r0 = r10.getRetryAfterSeconds()
            r11.<init>(r12, r13, r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.backend.impl.NotificationBackendService.updateNotificationAsOpened(java.lang.String, java.lang.String, java.lang.String, com.onesignal.core.internal.device.IDeviceService$DeviceType, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
