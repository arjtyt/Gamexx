package com.onesignal.notifications.internal.generation.impl;

import com.onesignal.common.AndroidUtils;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.config.ConfigModelStore;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.internal.Notification;
import com.onesignal.notifications.internal.NotificationReceivedEvent;
import com.onesignal.notifications.internal.NotificationWillDisplayEvent;
import com.onesignal.notifications.internal.common.NotificationConstants;
import com.onesignal.notifications.internal.common.NotificationFormatHelper;
import com.onesignal.notifications.internal.common.NotificationGenerationJob;
import com.onesignal.notifications.internal.data.INotificationRepository;
import com.onesignal.notifications.internal.display.INotificationDisplayer;
import com.onesignal.notifications.internal.generation.INotificationGenerationProcessor;
import com.onesignal.notifications.internal.lifecycle.INotificationLifecycleService;
import com.onesignal.notifications.internal.summary.INotificationSummaryManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0019\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ)\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010#J\u0019\u0010$\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ+\u0010%\u001a\u0004\u0018\u00010\u00152\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010#J9\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u00152\u0006\u0010.\u001a\u00020/H\u0096@ø\u0001\u0000¢\u0006\u0002\u00100J!\u00101\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001d2\u0006\u00102\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u00103J\u0010\u00104\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u001dH\u0002J\u0010\u00105\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u001dH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00066"}, d2 = {"Lcom/onesignal/notifications/internal/generation/impl/NotificationGenerationProcessor;", "Lcom/onesignal/notifications/internal/generation/INotificationGenerationProcessor;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_notificationDisplayer", "Lcom/onesignal/notifications/internal/display/INotificationDisplayer;", "_configModelStore", "Lcom/onesignal/core/internal/config/ConfigModelStore;", "_dataController", "Lcom/onesignal/notifications/internal/data/INotificationRepository;", "_notificationSummaryManager", "Lcom/onesignal/notifications/internal/summary/INotificationSummaryManager;", "_lifecycleService", "Lcom/onesignal/notifications/internal/lifecycle/INotificationLifecycleService;", "_time", "Lcom/onesignal/core/internal/time/ITime;", "(Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/notifications/internal/display/INotificationDisplayer;Lcom/onesignal/core/internal/config/ConfigModelStore;Lcom/onesignal/notifications/internal/data/INotificationRepository;Lcom/onesignal/notifications/internal/summary/INotificationSummaryManager;Lcom/onesignal/notifications/internal/lifecycle/INotificationLifecycleService;Lcom/onesignal/core/internal/time/ITime;)V", "getCustomJSONObject", "Lorg/json/JSONObject;", "jsonObject", "isDuplicateNotification", "", OneSignalDbContract.NotificationTable.TABLE_NAME, "Lcom/onesignal/notifications/internal/Notification;", "(Lcom/onesignal/notifications/internal/Notification;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isNotificationWithinTTL", "markNotificationAsDismissed", "", "notifiJob", "Lcom/onesignal/notifications/internal/common/NotificationGenerationJob;", "(Lcom/onesignal/notifications/internal/common/NotificationGenerationJob;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postProcessNotification", "notificationJob", "wasOpened", "wasDisplayed", "(Lcom/onesignal/notifications/internal/common/NotificationGenerationJob;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processCollapseKey", "processHandlerResponse", "wantsToDisplay", "isRestoring", "processNotificationData", "context", "Landroid/content/Context;", NotificationConstants.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, "", "jsonPayload", "timestamp", "", "(Landroid/content/Context;ILorg/json/JSONObject;ZJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveNotification", OneSignalDbContract.NotificationTable.COLUMN_NAME_OPENED, "(Lcom/onesignal/notifications/internal/common/NotificationGenerationJob;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldDisplayNotification", "shouldFireForegroundHandlers", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationGenerationProcessor implements INotificationGenerationProcessor {
    private final IApplicationService _applicationService;
    private final ConfigModelStore _configModelStore;
    private final INotificationRepository _dataController;
    private final INotificationLifecycleService _lifecycleService;
    private final INotificationDisplayer _notificationDisplayer;
    private final INotificationSummaryManager _notificationSummaryManager;
    private final ITime _time;

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$markNotificationAsDismissed$1, reason: invalid class name */
    /* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor", f = "NotificationGenerationProcessor.kt", i = {0, 0}, l = {304, 307}, m = "markNotificationAsDismissed", n = {"this", "notifiJob"}, s = {"L$0", "L$1"})
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
            return NotificationGenerationProcessor.this.markNotificationAsDismissed(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$postProcessNotification$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor", f = "NotificationGenerationProcessor.kt", i = {0, 0, 0}, l = {230, 234, 238}, m = "postProcessNotification", n = {"this", "notificationJob", "wasDisplayed"}, s = {"L$0", "L$1", "Z$0"})
    static final class C01161 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C01161(Continuation<? super C01161> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationGenerationProcessor.this.postProcessNotification(null, false, false, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processCollapseKey$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor", f = "NotificationGenerationProcessor.kt", i = {0}, l = {319}, m = "processCollapseKey", n = {"notificationJob"}, s = {"L$0"})
    static final class C01171 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01171(Continuation<? super C01171> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationGenerationProcessor.this.processCollapseKey(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processHandlerResponse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor", f = "NotificationGenerationProcessor.kt", i = {0, 0}, l = {171, 189, 194}, m = "processHandlerResponse", n = {"this", "notificationJob"}, s = {"L$0", "L$1"})
    static final class C01181 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01181(Continuation<? super C01181> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationGenerationProcessor.this.processHandlerResponse(null, false, false, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processNotificationData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor", f = "NotificationGenerationProcessor.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7}, l = {49, 57, 72, 94, 105, 129, 136, 142, 148}, m = "processNotificationData", n = {"this", "context", "jsonPayload", NotificationConstants.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, "isRestoring", "timestamp", "this", "context", "jsonPayload", OneSignalDbContract.NotificationTable.TABLE_NAME, "isRestoring", "timestamp", "this", OneSignalDbContract.NotificationTable.TABLE_NAME, "notificationJob", "wantsToDisplay", "isRestoring", "didDisplay", "this", OneSignalDbContract.NotificationTable.TABLE_NAME, "notificationJob", "wantsToDisplay", "isRestoring", "didDisplay", "this", "notificationJob", "wantsToDisplay", "isRestoring", "didDisplay", "this", "notificationJob", "isRestoring", "didDisplay", "this", "notificationJob", "isRestoring", "isRestoring"}, s = {"L$0", "L$1", "L$2", "I$0", "Z$0", "J$0", "L$0", "L$1", "L$2", "L$3", "Z$0", "J$0", "L$0", "L$1", "L$2", "L$3", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "Z$0", "I$0", "L$0", "L$1", "L$2", "Z$0", "I$0", "L$0", "L$1", "Z$0", "I$0", "L$0", "L$1", "Z$0", "Z$0"})
    static final class C01191 extends ContinuationImpl {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C01191(Continuation<? super C01191> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationGenerationProcessor.this.processNotificationData(null, 0, null, false, 0L, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$saveNotification$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor", f = "NotificationGenerationProcessor.kt", i = {}, l = {279}, m = "saveNotification", n = {}, s = {})
    static final class C01201 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01201(Continuation<? super C01201> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationGenerationProcessor.this.saveNotification(null, false, (Continuation) this);
        }
    }

    public NotificationGenerationProcessor(IApplicationService _applicationService, INotificationDisplayer _notificationDisplayer, ConfigModelStore _configModelStore, INotificationRepository _dataController, INotificationSummaryManager _notificationSummaryManager, INotificationLifecycleService _lifecycleService, ITime _time) {
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_notificationDisplayer, "_notificationDisplayer");
        Intrinsics.checkNotNullParameter(_configModelStore, "_configModelStore");
        Intrinsics.checkNotNullParameter(_dataController, "_dataController");
        Intrinsics.checkNotNullParameter(_notificationSummaryManager, "_notificationSummaryManager");
        Intrinsics.checkNotNullParameter(_lifecycleService, "_lifecycleService");
        Intrinsics.checkNotNullParameter(_time, "_time");
        this._applicationService = _applicationService;
        this._notificationDisplayer = _notificationDisplayer;
        this._configModelStore = _configModelStore;
        this._dataController = _dataController;
        this._notificationSummaryManager = _notificationSummaryManager;
        this._lifecycleService = _lifecycleService;
        this._time = _time;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0301 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01d2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0241 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0248  */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r5v38 */
    /* JADX WARN: Type inference failed for: r5v43 */
    /* JADX WARN: Type inference failed for: r5v47 */
    @Override // com.onesignal.notifications.internal.generation.INotificationGenerationProcessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object processNotificationData(android.content.Context r20, int r21, org.json.JSONObject r22, boolean r23, long r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r26) {
        /*
            Method dump skipped, instruction units count: 928
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.processNotificationData(android.content.Context, int, org.json.JSONObject, boolean, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processNotificationData$2, reason: invalid class name */
    /* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processNotificationData$2", f = "NotificationGenerationProcessor.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Notification $notification;
        final /* synthetic */ NotificationReceivedEvent $notificationReceivedEvent;
        final /* synthetic */ Ref.BooleanRef $wantsToDisplay;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(NotificationReceivedEvent notificationReceivedEvent, Ref.BooleanRef booleanRef, Notification notification, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$notificationReceivedEvent = notificationReceivedEvent;
            this.$wantsToDisplay = booleanRef;
            this.$notification = notification;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NotificationGenerationProcessor.this.new AnonymousClass2(this.$notificationReceivedEvent, this.$wantsToDisplay, this.$notification, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processNotificationData$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
        @DebugMetadata(c = "com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processNotificationData$2$1", f = "NotificationGenerationProcessor.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Notification $notification;
            final /* synthetic */ NotificationReceivedEvent $notificationReceivedEvent;
            final /* synthetic */ Ref.BooleanRef $wantsToDisplay;
            Object L$0;
            int label;
            final /* synthetic */ NotificationGenerationProcessor this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(NotificationGenerationProcessor notificationGenerationProcessor, NotificationReceivedEvent notificationReceivedEvent, Ref.BooleanRef booleanRef, Notification notification, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = notificationGenerationProcessor;
                this.$notificationReceivedEvent = notificationReceivedEvent;
                this.$wantsToDisplay = booleanRef;
                this.$notification = notification;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$notificationReceivedEvent, this.$wantsToDisplay, this.$notification, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object $result) {
                Ref.BooleanRef booleanRef;
                Object $result2;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.this$0._lifecycleService.externalRemoteNotificationReceived(this.$notificationReceivedEvent);
                        if (this.$notificationReceivedEvent.getDiscard()) {
                            this.$wantsToDisplay.element = false;
                        } else if (this.$notificationReceivedEvent.isPreventDefault()) {
                            this.$wantsToDisplay.element = false;
                            booleanRef = this.$wantsToDisplay;
                            this.L$0 = booleanRef;
                            this.label = 1;
                            Object objWaitForWake = this.$notification.getDisplayWaiter().waitForWake((Continuation) this);
                            if (objWaitForWake == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $result2 = $result;
                            $result = objWaitForWake;
                            booleanRef.element = ((Boolean) $result).booleanValue();
                        }
                        return Unit.INSTANCE;
                    case 1:
                        Ref.BooleanRef booleanRef2 = (Ref.BooleanRef) this.L$0;
                        ResultKt.throwOnFailure($result);
                        booleanRef = booleanRef2;
                        $result2 = $result;
                        booleanRef.element = ((Boolean) $result).booleanValue();
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (BuildersKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1(NotificationGenerationProcessor.this, this.$notificationReceivedEvent, this.$wantsToDisplay, this.$notification, null), 2, (Object) null).join((Continuation) this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processNotificationData$3, reason: invalid class name */
    /* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processNotificationData$3", f = "NotificationGenerationProcessor.kt", i = {}, l = {118}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Notification $notification;
        final /* synthetic */ NotificationWillDisplayEvent $notificationWillDisplayEvent;
        final /* synthetic */ Ref.BooleanRef $wantsToDisplay;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(NotificationWillDisplayEvent notificationWillDisplayEvent, Ref.BooleanRef booleanRef, Notification notification, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$notificationWillDisplayEvent = notificationWillDisplayEvent;
            this.$wantsToDisplay = booleanRef;
            this.$notification = notification;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NotificationGenerationProcessor.this.new AnonymousClass3(this.$notificationWillDisplayEvent, this.$wantsToDisplay, this.$notification, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processNotificationData$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: NotificationGenerationProcessor.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
        @DebugMetadata(c = "com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processNotificationData$3$1", f = "NotificationGenerationProcessor.kt", i = {}, l = {116}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Notification $notification;
            final /* synthetic */ NotificationWillDisplayEvent $notificationWillDisplayEvent;
            final /* synthetic */ Ref.BooleanRef $wantsToDisplay;
            Object L$0;
            int label;
            final /* synthetic */ NotificationGenerationProcessor this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(NotificationGenerationProcessor notificationGenerationProcessor, NotificationWillDisplayEvent notificationWillDisplayEvent, Ref.BooleanRef booleanRef, Notification notification, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = notificationGenerationProcessor;
                this.$notificationWillDisplayEvent = notificationWillDisplayEvent;
                this.$wantsToDisplay = booleanRef;
                this.$notification = notification;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$notificationWillDisplayEvent, this.$wantsToDisplay, this.$notification, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object $result) {
                Ref.BooleanRef booleanRef;
                Object $result2;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.this$0._lifecycleService.externalNotificationWillShowInForeground(this.$notificationWillDisplayEvent);
                        if (this.$notificationWillDisplayEvent.getDiscard()) {
                            this.$wantsToDisplay.element = false;
                        } else if (this.$notificationWillDisplayEvent.isPreventDefault()) {
                            this.$wantsToDisplay.element = false;
                            booleanRef = this.$wantsToDisplay;
                            this.L$0 = booleanRef;
                            this.label = 1;
                            Object objWaitForWake = this.$notification.getDisplayWaiter().waitForWake((Continuation) this);
                            if (objWaitForWake == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $result2 = $result;
                            $result = objWaitForWake;
                            booleanRef.element = ((Boolean) $result).booleanValue();
                        }
                        return Unit.INSTANCE;
                    case 1:
                        Ref.BooleanRef booleanRef2 = (Ref.BooleanRef) this.L$0;
                        ResultKt.throwOnFailure($result);
                        booleanRef = booleanRef2;
                        $result2 = $result;
                        booleanRef.element = ((Boolean) $result).booleanValue();
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (BuildersKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1(NotificationGenerationProcessor.this, this.$notificationWillDisplayEvent, this.$wantsToDisplay, this.$notification, null), 2, (Object) null).join((Continuation) this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processHandlerResponse(com.onesignal.notifications.internal.common.NotificationGenerationJob r7, boolean r8, boolean r9, kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.C01181
            if (r0 == 0) goto L14
            r0 = r10
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processHandlerResponse$1 r0 = (com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.C01181) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processHandlerResponse$1 r0 = new com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processHandlerResponse$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            switch(r2) {
                case 0: goto L43;
                case 1: goto L37;
                case 2: goto L33;
                case 3: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2e:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L9f
        L33:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L91
        L37:
            java.lang.Object r7 = r0.L$1
            com.onesignal.notifications.internal.common.NotificationGenerationJob r7 = (com.onesignal.notifications.internal.common.NotificationGenerationJob) r7
            java.lang.Object r8 = r0.L$0
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor r8 = (com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L71
        L43:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r6
            if (r8 == 0) goto L84
            com.onesignal.common.AndroidUtils r8 = com.onesignal.common.AndroidUtils.INSTANCE
            com.onesignal.notifications.internal.Notification r5 = r7.getNotification()
            java.lang.String r5 = r5.getBody()
            boolean r8 = r8.isStringNotEmpty(r5)
            com.onesignal.notifications.internal.Notification r5 = r7.getNotification()
            boolean r5 = r2.isNotificationWithinTTL(r5)
            if (r8 == 0) goto L84
            if (r5 == 0) goto L84
            r0.L$0 = r2
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r2.processCollapseKey(r7, r0)
            if (r8 != r1) goto L70
            return r1
        L70:
            r8 = r2
        L71:
            boolean r8 = r8.shouldDisplayNotification(r7)
            if (r8 == 0) goto L7f
            r7.setNotificationToDisplay(r4)
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r7
        L7f:
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r7
        L84:
            if (r9 == 0) goto L92
            r8 = 2
            r0.label = r8
            java.lang.Object r7 = r2.markNotificationAsDismissed(r7, r0)
            if (r7 != r1) goto L91
            return r1
        L91:
            goto La0
        L92:
            r7.setNotificationToDisplay(r3)
            r8 = 3
            r0.label = r8
            java.lang.Object r7 = r2.postProcessNotification(r7, r4, r3, r0)
            if (r7 != r1) goto L9f
            return r1
        L9f:
        La0:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.processHandlerResponse(com.onesignal.notifications.internal.common.NotificationGenerationJob, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean isNotificationWithinTTL(Notification notification) {
        boolean useTtl = this._configModelStore.getModel().getRestoreTTLFilter();
        if (!useTtl) {
            return true;
        }
        long currentTimeInSeconds = this._time.getCurrentTimeMillis() / ((long) 1000);
        long sentTime = notification.getSentTime();
        int ttl = notification.getTtl();
        return ((long) ttl) + sentTime > currentTimeInSeconds;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object isDuplicateNotification(Notification notification, Continuation<? super Boolean> continuation) {
        return this._dataController.doesNotificationExist(notification.getNotificationId(), continuation);
    }

    private final boolean shouldDisplayNotification(NotificationGenerationJob notificationJob) {
        return notificationJob.hasExtender() || AndroidUtils.INSTANCE.isStringNotEmpty(notificationJob.getJsonPayload().optString("alert"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object postProcessNotification(com.onesignal.notifications.internal.common.NotificationGenerationJob r5, boolean r6, boolean r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.C01161
            if (r0 == 0) goto L14
            r0 = r8
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$postProcessNotification$1 r0 = (com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.C01161) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$postProcessNotification$1 r0 = new com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$postProcessNotification$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L42;
                case 1: goto L34;
                case 2: goto L30;
                case 3: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2c:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L82
        L30:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6f
        L34:
            boolean r5 = r0.Z$0
            java.lang.Object r6 = r0.L$1
            com.onesignal.notifications.internal.common.NotificationGenerationJob r6 = (com.onesignal.notifications.internal.common.NotificationGenerationJob) r6
            java.lang.Object r7 = r0.L$0
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor r7 = (com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5e
        L42:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r4
            r3 = 1
            if (r6 == 0) goto L4b
            r6 = r3
            goto L4c
        L4b:
            r6 = 0
        L4c:
            r0.L$0 = r2
            r0.L$1 = r5
            r0.Z$0 = r7
            r0.label = r3
            java.lang.Object r6 = r2.saveNotification(r5, r6, r0)
            if (r6 != r1) goto L5b
            return r1
        L5b:
            r6 = r5
            r5 = r7
            r7 = r2
        L5e:
            r2 = 0
            if (r5 != 0) goto L72
            r0.L$0 = r2
            r0.L$1 = r2
            r5 = 2
            r0.label = r5
            java.lang.Object r5 = r7.markNotificationAsDismissed(r6, r0)
            if (r5 != r1) goto L6f
            return r1
        L6f:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L72:
            com.onesignal.notifications.internal.lifecycle.INotificationLifecycleService r5 = r7._lifecycleService
            r0.L$0 = r2
            r0.L$1 = r2
            r2 = 3
            r0.label = r2
            java.lang.Object r5 = r5.notificationReceived(r6, r0)
            if (r5 != r1) goto L82
            return r1
        L82:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.postProcessNotification(com.onesignal.notifications.internal.common.NotificationGenerationJob, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveNotification(com.onesignal.notifications.internal.common.NotificationGenerationJob r20, boolean r21, kotlin.coroutines.Continuation<? super kotlin.Unit> r22) {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.saveNotification(com.onesignal.notifications.internal.common.NotificationGenerationJob, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object markNotificationAsDismissed(com.onesignal.notifications.internal.common.NotificationGenerationJob r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r10
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$markNotificationAsDismissed$1 r0 = (com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$markNotificationAsDismissed$1 r0 = new com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$markNotificationAsDismissed$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            switch(r2) {
                case 0: goto L3f;
                case 1: goto L32;
                case 2: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2e:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L91
        L32:
            java.lang.Object r9 = r0.L$1
            com.onesignal.notifications.internal.common.NotificationGenerationJob r9 = (com.onesignal.notifications.internal.common.NotificationGenerationJob) r9
            java.lang.Object r2 = r0.L$0
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor r2 = (com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r5 = r10
            goto L76
        L3f:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r8
            boolean r5 = r9.isNotificationToDisplay()
            if (r5 != 0) goto L4c
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L4c:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Marking restored or disabled notifications as dismissed: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r9)
            java.lang.String r5 = r5.toString()
            com.onesignal.debug.internal.logging.Logging.debug$default(r5, r4, r3, r4)
            com.onesignal.notifications.internal.data.INotificationRepository r5 = r2._dataController
            int r6 = r9.getAndroidId()
            r0.L$0 = r2
            r0.L$1 = r9
            r7 = 1
            r0.label = r7
            java.lang.Object r5 = r5.markAsDismissed(r6, r0)
            if (r5 != r1) goto L76
            return r1
        L76:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L94
            com.onesignal.notifications.internal.summary.INotificationSummaryManager r5 = r2._notificationSummaryManager
            int r6 = r9.getAndroidId()
            r0.L$0 = r4
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r9 = r5.updatePossibleDependentSummaryOnDismiss(r6, r0)
            if (r9 != r1) goto L91
            return r1
        L91:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L94:
            goto L91
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.markNotificationAsDismissed(com.onesignal.notifications.internal.common.NotificationGenerationJob, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processCollapseKey(com.onesignal.notifications.internal.common.NotificationGenerationJob r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.C01171
            if (r0 == 0) goto L14
            r0 = r8
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processCollapseKey$1 r0 = (com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.C01171) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processCollapseKey$1 r0 = new com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor$processCollapseKey$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            java.lang.Object r7 = r0.L$0
            com.onesignal.notifications.internal.common.NotificationGenerationJob r7 = (com.onesignal.notifications.internal.common.NotificationGenerationJob) r7
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r8
            goto L7a
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            boolean r3 = r7.isRestoring()
            if (r3 == 0) goto L42
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L42:
            org.json.JSONObject r3 = r7.getJsonPayload()
            java.lang.String r4 = "collapse_key"
            boolean r3 = r3.has(r4)
            if (r3 == 0) goto L8d
            org.json.JSONObject r3 = r7.getJsonPayload()
            java.lang.String r3 = r3.optString(r4)
            java.lang.String r5 = "do_not_collapse"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r3)
            if (r3 == 0) goto L5f
            goto L8d
        L5f:
            org.json.JSONObject r3 = r7.getJsonPayload()
            java.lang.String r3 = r3.optString(r4)
            com.onesignal.notifications.internal.data.INotificationRepository r4 = r2._dataController
            java.lang.String r5 = "collapseId"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
            r0.L$0 = r7
            r5 = 1
            r0.label = r5
            java.lang.Object r2 = r4.getAndroidIdFromCollapseKey(r3, r0)
            if (r2 != r1) goto L7a
            return r1
        L7a:
            r1 = r2
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 == 0) goto L8a
            com.onesignal.notifications.internal.Notification r2 = r7.getNotification()
            int r3 = r1.intValue()
            r2.setAndroidNotificationId(r3)
        L8a:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L8d:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.generation.impl.NotificationGenerationProcessor.processCollapseKey(com.onesignal.notifications.internal.common.NotificationGenerationJob, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final JSONObject getCustomJSONObject(JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        return new JSONObject(jsonObject.optString(NotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM));
    }

    private final boolean shouldFireForegroundHandlers(NotificationGenerationJob notificationJob) {
        if (!this._applicationService.isInForeground()) {
            Logging.info$default("App is in background, show notification", null, 2, null);
            return false;
        }
        if (notificationJob.isRestoring()) {
            Logging.info$default("Not firing notificationWillShowInForegroundHandler for restored notifications", null, 2, null);
            return false;
        }
        return true;
    }
}
