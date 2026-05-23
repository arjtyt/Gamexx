package com.onesignal.inAppMessages.internal.display.impl;

import android.app.Activity;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.config.ConfigModelStore;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.core.internal.language.ILanguageContext;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.inAppMessages.BuildConfig;
import com.onesignal.inAppMessages.internal.InAppMessageContent;
import com.onesignal.inAppMessages.internal.backend.IInAppBackendService;
import com.onesignal.inAppMessages.internal.display.IInAppDisplayer;
import com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService;
import com.onesignal.inAppMessages.internal.prompt.IInAppMessagePromptFactory;
import com.onesignal.session.internal.influence.IInfluenceManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: InAppDisplayer.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 (2\u00020\u0001:\u0001(BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0019\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ)\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010%J!\u0010&\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010'R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lcom/onesignal/inAppMessages/internal/display/impl/InAppDisplayer;", "Lcom/onesignal/inAppMessages/internal/display/IInAppDisplayer;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_lifecycle", "Lcom/onesignal/inAppMessages/internal/lifecycle/IInAppLifecycleService;", "_promptFactory", "Lcom/onesignal/inAppMessages/internal/prompt/IInAppMessagePromptFactory;", "_backend", "Lcom/onesignal/inAppMessages/internal/backend/IInAppBackendService;", "_influenceManager", "Lcom/onesignal/session/internal/influence/IInfluenceManager;", "_configModelStore", "Lcom/onesignal/core/internal/config/ConfigModelStore;", "_languageContext", "Lcom/onesignal/core/internal/language/ILanguageContext;", "_time", "Lcom/onesignal/core/internal/time/ITime;", "(Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/inAppMessages/internal/lifecycle/IInAppLifecycleService;Lcom/onesignal/inAppMessages/internal/prompt/IInAppMessagePromptFactory;Lcom/onesignal/inAppMessages/internal/backend/IInAppBackendService;Lcom/onesignal/session/internal/influence/IInfluenceManager;Lcom/onesignal/core/internal/config/ConfigModelStore;Lcom/onesignal/core/internal/language/ILanguageContext;Lcom/onesignal/core/internal/time/ITime;)V", "lastInstance", "Lcom/onesignal/inAppMessages/internal/display/impl/WebViewManager;", "dismissCurrentInAppMessage", "", "displayMessage", "", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "Lcom/onesignal/inAppMessages/internal/InAppMessage;", "(Lcom/onesignal/inAppMessages/internal/InAppMessage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "displayPreviewMessage", "previewUUID", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initInAppMessage", "currentActivity", "Landroid/app/Activity;", "content", "Lcom/onesignal/inAppMessages/internal/InAppMessageContent;", "(Landroid/app/Activity;Lcom/onesignal/inAppMessages/internal/InAppMessage;Lcom/onesignal/inAppMessages/internal/InAppMessageContent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showMessageContent", "(Lcom/onesignal/inAppMessages/internal/InAppMessage;Lcom/onesignal/inAppMessages/internal/InAppMessageContent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InAppDisplayer implements IInAppDisplayer {
    public static final Companion Companion = new Companion(null);
    private static final int IN_APP_MESSAGE_INIT_DELAY = 200;
    private final IApplicationService _applicationService;
    private final IInAppBackendService _backend;
    private final ConfigModelStore _configModelStore;
    private final IInfluenceManager _influenceManager;
    private final ILanguageContext _languageContext;
    private final IInAppLifecycleService _lifecycle;
    private final IInAppMessagePromptFactory _promptFactory;
    private final ITime _time;
    private WebViewManager lastInstance;

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$displayMessage$1, reason: invalid class name */
    /* JADX INFO: compiled from: InAppDisplayer.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer", f = "InAppDisplayer.kt", i = {0, 0}, l = {48, 57}, m = "displayMessage", n = {"this", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppDisplayer.this.displayMessage(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$displayPreviewMessage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppDisplayer.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer", f = "InAppDisplayer.kt", i = {0, 0}, l = {73, 79}, m = "displayPreviewMessage", n = {"this", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE}, s = {"L$0", "L$1"})
    static final class C00581 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00581(Continuation<? super C00581> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppDisplayer.this.displayPreviewMessage(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$initInAppMessage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppDisplayer.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer", f = "InAppDisplayer.kt", i = {}, l = {145}, m = "initInAppMessage", n = {}, s = {})
    static final class C00591 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C00591(Continuation<? super C00591> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppDisplayer.this.initInAppMessage(null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$showMessageContent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppDisplayer.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer", f = "InAppDisplayer.kt", i = {0, 0, 0, 0, 3, 3, 3}, l = {105, 107, 109, 114, 115}, m = "showMessageContent", n = {"this", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "content", "currentActivity", "this", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "content"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2"})
    static final class C00601 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C00601(Continuation<? super C00601> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppDisplayer.this.showMessageContent(null, null, (Continuation) this);
        }
    }

    public InAppDisplayer(IApplicationService _applicationService, IInAppLifecycleService _lifecycle, IInAppMessagePromptFactory _promptFactory, IInAppBackendService _backend, IInfluenceManager _influenceManager, ConfigModelStore _configModelStore, ILanguageContext _languageContext, ITime _time) {
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_lifecycle, "_lifecycle");
        Intrinsics.checkNotNullParameter(_promptFactory, "_promptFactory");
        Intrinsics.checkNotNullParameter(_backend, "_backend");
        Intrinsics.checkNotNullParameter(_influenceManager, "_influenceManager");
        Intrinsics.checkNotNullParameter(_configModelStore, "_configModelStore");
        Intrinsics.checkNotNullParameter(_languageContext, "_languageContext");
        Intrinsics.checkNotNullParameter(_time, "_time");
        this._applicationService = _applicationService;
        this._lifecycle = _lifecycle;
        this._promptFactory = _promptFactory;
        this._backend = _backend;
        this._influenceManager = _influenceManager;
        this._configModelStore = _configModelStore;
        this._languageContext = _languageContext;
        this._time = _time;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.inAppMessages.internal.display.IInAppDisplayer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object displayMessage(com.onesignal.inAppMessages.internal.InAppMessage r10, kotlin.coroutines.Continuation<? super java.lang.Boolean> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$displayMessage$1 r0 = (com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$displayMessage$1 r0 = new com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$displayMessage$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L3f;
                case 1: goto L32;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2d:
            kotlin.ResultKt.throwOnFailure(r11)
            goto La7
        L32:
            java.lang.Object r10 = r0.L$1
            com.onesignal.inAppMessages.internal.InAppMessage r10 = (com.onesignal.inAppMessages.internal.InAppMessage) r10
            java.lang.Object r2 = r0.L$0
            com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer r2 = (com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer) r2
            kotlin.ResultKt.throwOnFailure(r11)
            r4 = r11
            goto L6a
        L3f:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r9
            com.onesignal.inAppMessages.internal.backend.IInAppBackendService r4 = r2._backend
            com.onesignal.core.internal.config.ConfigModelStore r5 = r2._configModelStore
            com.onesignal.common.modeling.Model r5 = r5.getModel()
            com.onesignal.core.internal.config.ConfigModel r5 = (com.onesignal.core.internal.config.ConfigModel) r5
            java.lang.String r5 = r5.getAppId()
            java.lang.String r6 = r10.getMessageId()
            com.onesignal.inAppMessages.internal.common.InAppHelper r7 = com.onesignal.inAppMessages.internal.common.InAppHelper.INSTANCE
            com.onesignal.core.internal.language.ILanguageContext r8 = r2._languageContext
            java.lang.String r7 = r7.variantIdForMessage(r10, r8)
            r0.L$0 = r2
            r0.L$1 = r10
            r0.label = r3
            java.lang.Object r4 = r4.getIAMData(r5, r6, r7, r0)
            if (r4 != r1) goto L6a
            return r1
        L6a:
            com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse r4 = (com.onesignal.inAppMessages.internal.backend.GetIAMDataResponse) r4
            com.onesignal.inAppMessages.internal.InAppMessageContent r5 = r4.getContent()
            r6 = 0
            if (r5 == 0) goto Lac
            com.onesignal.inAppMessages.internal.InAppMessageContent r5 = r4.getContent()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            java.lang.Double r5 = r5.getDisplayDuration()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            double r7 = r5.doubleValue()
            r10.setDisplayDuration(r7)
            com.onesignal.session.internal.influence.IInfluenceManager r5 = r2._influenceManager
            java.lang.String r7 = r10.getMessageId()
            r5.onInAppMessageDisplayed(r7)
            com.onesignal.inAppMessages.internal.InAppMessageContent r5 = r4.getContent()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r0.L$0 = r6
            r0.L$1 = r6
            r6 = 2
            r0.label = r6
            java.lang.Object r10 = r2.showMessageContent(r10, r5, r0)
            if (r10 != r1) goto La7
            return r1
        La7:
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r10
        Lac:
            boolean r10 = r4.getShouldRetry()
            if (r10 == 0) goto Lb6
            r10 = r6
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto Lbb
        Lb6:
            r10 = 0
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r10)
        Lbb:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.displayMessage(com.onesignal.inAppMessages.internal.InAppMessage, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.inAppMessages.internal.display.IInAppDisplayer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object displayPreviewMessage(java.lang.String r8, kotlin.coroutines.Continuation<? super java.lang.Boolean> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.C00581
            if (r0 == 0) goto L14
            r0 = r9
            com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$displayPreviewMessage$1 r0 = (com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.C00581) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$displayPreviewMessage$1 r0 = new com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$displayPreviewMessage$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L3f;
                case 1: goto L31;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2d:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L88
        L31:
            java.lang.Object r8 = r0.L$1
            com.onesignal.inAppMessages.internal.InAppMessage r8 = (com.onesignal.inAppMessages.internal.InAppMessage) r8
            java.lang.Object r2 = r0.L$0
            com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer r2 = (com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer) r2
            kotlin.ResultKt.throwOnFailure(r9)
            r4 = r8
            r8 = r9
            goto L65
        L3f:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
            com.onesignal.inAppMessages.internal.InAppMessage r4 = new com.onesignal.inAppMessages.internal.InAppMessage
            com.onesignal.core.internal.time.ITime r5 = r2._time
            r4.<init>(r3, r5)
            com.onesignal.inAppMessages.internal.backend.IInAppBackendService r5 = r2._backend
            com.onesignal.core.internal.config.ConfigModelStore r6 = r2._configModelStore
            com.onesignal.common.modeling.Model r6 = r6.getModel()
            com.onesignal.core.internal.config.ConfigModel r6 = (com.onesignal.core.internal.config.ConfigModel) r6
            java.lang.String r6 = r6.getAppId()
            r0.L$0 = r2
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r8 = r5.getIAMPreviewData(r6, r8, r0)
            if (r8 != r1) goto L65
            return r1
        L65:
            com.onesignal.inAppMessages.internal.InAppMessageContent r8 = (com.onesignal.inAppMessages.internal.InAppMessageContent) r8
            if (r8 != 0) goto L6b
            r3 = 0
            goto L89
        L6b:
            java.lang.Double r5 = r8.getDisplayDuration()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            double r5 = r5.doubleValue()
            r4.setDisplayDuration(r5)
            r5 = 0
            r0.L$0 = r5
            r0.L$1 = r5
            r5 = 2
            r0.label = r5
            java.lang.Object r8 = r2.showMessageContent(r4, r8, r0)
            if (r8 != r1) goto L88
            return r1
        L88:
        L89:
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.displayPreviewMessage(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ef A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object showMessageContent(com.onesignal.inAppMessages.internal.InAppMessage r10, com.onesignal.inAppMessages.internal.InAppMessageContent r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.showMessageContent(com.onesignal.inAppMessages.internal.InAppMessage, com.onesignal.inAppMessages.internal.InAppMessageContent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.onesignal.inAppMessages.internal.display.IInAppDisplayer
    public void dismissCurrentInAppMessage() {
        Logging.debug$default("WebViewManager IAM dismissAndAwaitNextMessage lastInstance: " + this.lastInstance, null, 2, null);
        if (this.lastInstance != null) {
            WebViewManager webViewManager = this.lastInstance;
            Intrinsics.checkNotNull(webViewManager);
            webViewManager.backgroundDismissAndAwaitNextMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object initInAppMessage(android.app.Activity r12, com.onesignal.inAppMessages.internal.InAppMessage r13, com.onesignal.inAppMessages.internal.InAppMessageContent r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            r11 = this;
            boolean r0 = r15 instanceof com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.C00591
            if (r0 == 0) goto L14
            r0 = r15
            com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$initInAppMessage$1 r0 = (com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.C00591) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$initInAppMessage$1 r0 = new com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$initInAppMessage$1
            r0.<init>(r15)
        L19:
            r15 = r0
            java.lang.Object r1 = r15.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r15.label
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L2d:
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.io.UnsupportedEncodingException -> L32
            goto L95
        L32:
            r0 = move-exception
            r12 = r0
            goto L96
        L35:
            kotlin.ResultKt.throwOnFailure(r1)
            r2 = r11
            r4 = r13
            r5 = r12
            r6 = r14
            java.lang.String r12 = r6.getContentHtml()     // Catch: java.io.UnsupportedEncodingException -> L32
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)     // Catch: java.io.UnsupportedEncodingException -> L32
            java.lang.String r13 = "UTF-8"
            java.nio.charset.Charset r13 = java.nio.charset.Charset.forName(r13)     // Catch: java.io.UnsupportedEncodingException -> L32
            java.lang.String r14 = "forName(charsetName)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r14)     // Catch: java.io.UnsupportedEncodingException -> L32
            byte[] r12 = r12.getBytes(r13)     // Catch: java.io.UnsupportedEncodingException -> L32
            java.lang.String r13 = "this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r13)     // Catch: java.io.UnsupportedEncodingException -> L32
            r13 = 2
            java.lang.String r8 = android.util.Base64.encodeToString(r12, r13)     // Catch: java.io.UnsupportedEncodingException -> L32
            r12 = r8
            com.onesignal.inAppMessages.internal.display.impl.WebViewManager r3 = new com.onesignal.inAppMessages.internal.display.impl.WebViewManager     // Catch: java.io.UnsupportedEncodingException -> L32
            com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService r7 = r2._lifecycle     // Catch: java.io.UnsupportedEncodingException -> L32
            com.onesignal.core.internal.application.IApplicationService r8 = r2._applicationService     // Catch: java.io.UnsupportedEncodingException -> L32
            com.onesignal.inAppMessages.internal.prompt.IInAppMessagePromptFactory r9 = r2._promptFactory     // Catch: java.io.UnsupportedEncodingException -> L32
            r3.<init>(r4, r5, r6, r7, r8, r9)     // Catch: java.io.UnsupportedEncodingException -> L32
            r2.lastInstance = r3     // Catch: java.io.UnsupportedEncodingException -> L32
            boolean r13 = r6.isFullBleed()     // Catch: java.io.UnsupportedEncodingException -> L32
            if (r13 == 0) goto L76
            r3.setContentSafeAreaInsets(r6, r5)     // Catch: java.io.UnsupportedEncodingException -> L32
        L76:
            kotlinx.coroutines.MainCoroutineDispatcher r13 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.io.UnsupportedEncodingException -> L32
            kotlin.coroutines.CoroutineContext r13 = (kotlin.coroutines.CoroutineContext) r13     // Catch: java.io.UnsupportedEncodingException -> L32
            r7 = r5
            com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$initInAppMessage$2 r5 = new com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$initInAppMessage$2     // Catch: java.io.UnsupportedEncodingException -> L32
            r10 = 0
            r8 = r12
            r9 = r6
            r6 = r3
            r5.<init>(r6, r7, r8, r9, r10)     // Catch: java.io.UnsupportedEncodingException -> L32
            r12 = r5
            r5 = r7
            r6 = r9
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12     // Catch: java.io.UnsupportedEncodingException -> L32
            r14 = 1
            r15.label = r14     // Catch: java.io.UnsupportedEncodingException -> L32
            java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r13, r12, r15)     // Catch: java.io.UnsupportedEncodingException -> L32
            if (r12 != r0) goto L95
            return r0
        L95:
            goto L9e
        L96:
            java.lang.String r13 = "Catch on initInAppMessage: "
            r14 = r12
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            com.onesignal.debug.internal.logging.Logging.error(r13, r14)
        L9e:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.initInAppMessage(android.app.Activity, com.onesignal.inAppMessages.internal.InAppMessage, com.onesignal.inAppMessages.internal.InAppMessageContent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$initInAppMessage$2, reason: invalid class name */
    /* JADX INFO: compiled from: InAppDisplayer.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer$initInAppMessage$2", f = "InAppDisplayer.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $base64Str;
        final /* synthetic */ InAppMessageContent $content;
        final /* synthetic */ Activity $currentActivity;
        final /* synthetic */ WebViewManager $webViewManager;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(WebViewManager webViewManager, Activity activity, String str, InAppMessageContent inAppMessageContent, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$webViewManager = webViewManager;
            this.$currentActivity = activity;
            this.$base64Str = str;
            this.$content = inAppMessageContent;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$webViewManager, this.$currentActivity, this.$base64Str, this.$content, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0047  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Exception {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                switch(r1) {
                    case 0: goto L18;
                    case 1: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L11:
                r0 = r9
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L16
                goto L3c
            L16:
                r1 = move-exception
                goto L41
            L18:
                kotlin.ResultKt.throwOnFailure(r10)
                r1 = r9
                com.onesignal.inAppMessages.internal.display.impl.WebViewManager r2 = r1.$webViewManager     // Catch: java.lang.Exception -> L3d
                android.app.Activity r3 = r1.$currentActivity     // Catch: java.lang.Exception -> L3d
                java.lang.String r4 = r1.$base64Str     // Catch: java.lang.Exception -> L3d
                java.lang.String r5 = "base64Str"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch: java.lang.Exception -> L3d
                com.onesignal.inAppMessages.internal.InAppMessageContent r5 = r1.$content     // Catch: java.lang.Exception -> L3d
                boolean r5 = r5.isFullBleed()     // Catch: java.lang.Exception -> L3d
                r6 = r1
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L3d
                r7 = 1
                r1.label = r7     // Catch: java.lang.Exception -> L3d
                java.lang.Object r2 = r2.setupWebView(r3, r4, r5, r6)     // Catch: java.lang.Exception -> L3d
                if (r2 != r0) goto L3b
                return r0
            L3b:
                r0 = r1
            L3c:
                goto L65
            L3d:
                r0 = move-exception
                r8 = r1
                r1 = r0
                r0 = r8
            L41:
                java.lang.String r2 = r1.getMessage()
                if (r2 == 0) goto L68
                java.lang.String r2 = r1.getMessage()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                java.lang.String r3 = "No WebView installed"
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                r4 = 2
                r5 = 0
                r6 = 0
                boolean r2 = kotlin.text.StringsKt.contains$default(r2, r3, r6, r4, r5)
                if (r2 == 0) goto L68
                java.lang.String r2 = "Error setting up WebView: "
                r3 = r1
                java.lang.Throwable r3 = (java.lang.Throwable) r3
                com.onesignal.debug.internal.logging.Logging.error(r2, r3)
            L65:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            L68:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.display.impl.InAppDisplayer.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: compiled from: InAppDisplayer.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onesignal/inAppMessages/internal/display/impl/InAppDisplayer$Companion;", "", "()V", "IN_APP_MESSAGE_INIT_DELAY", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
