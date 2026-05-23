package com.onesignal.core.internal.backend.impl;

import com.onesignal.common.JSONObjectExtensionsKt;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.backend.IParamsBackendService;
import com.onesignal.core.internal.backend.InfluenceParamsObject;
import com.onesignal.core.internal.http.IHttpClient;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ParamsBackendService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J#\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/onesignal/core/internal/backend/impl/ParamsBackendService;", "Lcom/onesignal/core/internal/backend/IParamsBackendService;", "_http", "Lcom/onesignal/core/internal/http/IHttpClient;", "(Lcom/onesignal/core/internal/http/IHttpClient;)V", "fetchParams", "Lcom/onesignal/core/internal/backend/ParamsObject;", "appId", "", "subscriptionId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processOutcomeJson", "Lcom/onesignal/core/internal/backend/InfluenceParamsObject;", "outcomeJson", "Lorg/json/JSONObject;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ParamsBackendService implements IParamsBackendService {
    private final IHttpClient _http;

    /* JADX INFO: renamed from: com.onesignal.core.internal.backend.impl.ParamsBackendService$fetchParams$1, reason: invalid class name */
    /* JADX INFO: compiled from: ParamsBackendService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.backend.impl.ParamsBackendService", f = "ParamsBackendService.kt", i = {0}, l = {35}, m = "fetchParams", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ParamsBackendService.this.fetchParams(null, null, (Continuation) this);
        }
    }

    public ParamsBackendService(IHttpClient _http) {
        Intrinsics.checkNotNullParameter(_http, "_http");
        this._http = _http;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @Override // com.onesignal.core.internal.backend.IParamsBackendService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object fetchParams(java.lang.String r33, java.lang.String r34, kotlin.coroutines.Continuation<? super com.onesignal.core.internal.backend.ParamsObject> r35) throws com.onesignal.common.exceptions.BackendException, org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.core.internal.backend.impl.ParamsBackendService.fetchParams(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InfluenceParamsObject processOutcomeJson(JSONObject outcomeJson) throws JSONException {
        final Ref.ObjectRef indirectNotificationAttributionWindow = new Ref.ObjectRef();
        final Ref.ObjectRef notificationLimit = new Ref.ObjectRef();
        final Ref.ObjectRef indirectIAMAttributionWindow = new Ref.ObjectRef();
        final Ref.ObjectRef iamLimit = new Ref.ObjectRef();
        final Ref.ObjectRef isDirectEnabled = new Ref.ObjectRef();
        final Ref.ObjectRef isIndirectEnabled = new Ref.ObjectRef();
        final Ref.ObjectRef isUnattributedEnabled = new Ref.ObjectRef();
        JSONObjectExtensionsKt.expandJSONObject(outcomeJson, "direct", new Function1<JSONObject, Unit>() { // from class: com.onesignal.core.internal.backend.impl.ParamsBackendService.processOutcomeJson.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((JSONObject) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(JSONObject it) {
                Intrinsics.checkNotNullParameter(it, "it");
                isDirectEnabled.element = JSONObjectExtensionsKt.safeBool(it, "enabled");
            }
        });
        JSONObjectExtensionsKt.expandJSONObject(outcomeJson, OutcomeConstants.INDIRECT, new Function1<JSONObject, Unit>() { // from class: com.onesignal.core.internal.backend.impl.ParamsBackendService.processOutcomeJson.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) throws JSONException {
                invoke((JSONObject) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(JSONObject indirectJSON) throws JSONException {
                Intrinsics.checkNotNullParameter(indirectJSON, "indirectJSON");
                isIndirectEnabled.element = JSONObjectExtensionsKt.safeBool(indirectJSON, "enabled");
                final Ref.ObjectRef<Integer> objectRef = indirectNotificationAttributionWindow;
                final Ref.ObjectRef<Integer> objectRef2 = notificationLimit;
                JSONObjectExtensionsKt.expandJSONObject(indirectJSON, "notification_attribution", new Function1<JSONObject, Unit>() { // from class: com.onesignal.core.internal.backend.impl.ParamsBackendService.processOutcomeJson.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                        invoke((JSONObject) p1);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(JSONObject it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        objectRef.element = JSONObjectExtensionsKt.safeInt(it, "minutes_since_displayed");
                        objectRef2.element = JSONObjectExtensionsKt.safeInt(it, "limit");
                    }
                });
                final Ref.ObjectRef<Integer> objectRef3 = indirectIAMAttributionWindow;
                final Ref.ObjectRef<Integer> objectRef4 = iamLimit;
                JSONObjectExtensionsKt.expandJSONObject(indirectJSON, "in_app_message_attribution", new Function1<JSONObject, Unit>() { // from class: com.onesignal.core.internal.backend.impl.ParamsBackendService.processOutcomeJson.2.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                        invoke((JSONObject) p1);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(JSONObject it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        objectRef3.element = JSONObjectExtensionsKt.safeInt(it, "minutes_since_displayed");
                        objectRef4.element = JSONObjectExtensionsKt.safeInt(it, "limit");
                    }
                });
            }
        });
        JSONObjectExtensionsKt.expandJSONObject(outcomeJson, "unattributed", new Function1<JSONObject, Unit>() { // from class: com.onesignal.core.internal.backend.impl.ParamsBackendService.processOutcomeJson.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((JSONObject) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(JSONObject it) {
                Intrinsics.checkNotNullParameter(it, "it");
                isUnattributedEnabled.element = JSONObjectExtensionsKt.safeBool(it, "enabled");
            }
        });
        return new InfluenceParamsObject((Integer) indirectNotificationAttributionWindow.element, (Integer) notificationLimit.element, (Integer) indirectIAMAttributionWindow.element, (Integer) iamLimit.element, (Boolean) isDirectEnabled.element, (Boolean) isIndirectEnabled.element, (Boolean) isUnattributedEnabled.element);
    }
}
