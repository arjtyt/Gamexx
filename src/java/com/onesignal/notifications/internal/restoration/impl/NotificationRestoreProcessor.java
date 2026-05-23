package com.onesignal.notifications.internal.restoration.impl;

import android.service.notification.StatusBarNotification;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.internal.badges.IBadgeCountUpdater;
import com.onesignal.notifications.internal.common.NotificationHelper;
import com.onesignal.notifications.internal.data.INotificationRepository;
import com.onesignal.notifications.internal.generation.INotificationGenerationWorkManager;
import com.onesignal.notifications.internal.restoration.INotificationRestoreProcessor;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DelayKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: NotificationRestoreProcessor.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0002J\u0011\u0010\u000e\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lcom/onesignal/notifications/internal/restoration/impl/NotificationRestoreProcessor;", "Lcom/onesignal/notifications/internal/restoration/INotificationRestoreProcessor;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_workManager", "Lcom/onesignal/notifications/internal/generation/INotificationGenerationWorkManager;", "_dataController", "Lcom/onesignal/notifications/internal/data/INotificationRepository;", "_badgeCountUpdater", "Lcom/onesignal/notifications/internal/badges/IBadgeCountUpdater;", "(Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/notifications/internal/generation/INotificationGenerationWorkManager;Lcom/onesignal/notifications/internal/data/INotificationRepository;Lcom/onesignal/notifications/internal/badges/IBadgeCountUpdater;)V", "getVisibleNotifications", "", "", "process", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processNotification", OneSignalDbContract.NotificationTable.TABLE_NAME, "Lcom/onesignal/notifications/internal/data/INotificationRepository$NotificationData;", "delay", "(Lcom/onesignal/notifications/internal/data/INotificationRepository$NotificationData;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationRestoreProcessor implements INotificationRestoreProcessor {
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_TTL_IF_NOT_IN_PAYLOAD = 259200;
    private static final int DELAY_BETWEEN_NOTIFICATION_RESTORES_MS = 200;
    private final IApplicationService _applicationService;
    private final IBadgeCountUpdater _badgeCountUpdater;
    private final INotificationRepository _dataController;
    private final INotificationGenerationWorkManager _workManager;

    /* JADX INFO: renamed from: com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor$process$1, reason: invalid class name */
    /* JADX INFO: compiled from: NotificationRestoreProcessor.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor", f = "NotificationRestoreProcessor.kt", i = {0, 1}, l = {25, 28}, m = "process", n = {"this", "this"}, s = {"L$0", "L$0"})
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
            return NotificationRestoreProcessor.this.process((Continuation) this);
        }
    }

    public NotificationRestoreProcessor(IApplicationService _applicationService, INotificationGenerationWorkManager _workManager, INotificationRepository _dataController, IBadgeCountUpdater _badgeCountUpdater) {
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_workManager, "_workManager");
        Intrinsics.checkNotNullParameter(_dataController, "_dataController");
        Intrinsics.checkNotNullParameter(_badgeCountUpdater, "_badgeCountUpdater");
        this._applicationService = _applicationService;
        this._workManager = _workManager;
        this._dataController = _dataController;
        this._badgeCountUpdater = _badgeCountUpdater;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006e A[Catch: all -> 0x008a, TryCatch #0 {all -> 0x008a, blocks: (B:13:0x0035, B:23:0x0068, B:25:0x006e, B:29:0x0084, B:16:0x003d, B:22:0x005f, B:19:0x004d), top: B:35:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0084 A[Catch: all -> 0x008a, TRY_LEAVE, TryCatch #0 {all -> 0x008a, blocks: (B:13:0x0035, B:23:0x0068, B:25:0x006e, B:29:0x0084, B:16:0x003d, B:22:0x005f, B:19:0x004d), top: B:35:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0080 -> B:28:0x0083). Please report as a decompilation issue!!! */
    @Override // com.onesignal.notifications.internal.restoration.INotificationRestoreProcessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object process(kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r9
            com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor$process$1 r0 = (com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor$process$1 r0 = new com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor$process$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            switch(r2) {
                case 0: goto L42;
                case 1: goto L39;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L2d:
            java.lang.Object r2 = r0.L$1
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r4 = r0.L$0
            com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor r4 = (com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor) r4
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L8a
            goto L83
        L39:
            java.lang.Object r2 = r0.L$0
            com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor r2 = (com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L8a
            r5 = r9
            goto L5f
        L42:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r8
            java.lang.String r4 = "Restoring notifications"
            r5 = 0
            com.onesignal.debug.internal.logging.Logging.info$default(r4, r5, r3, r5)
            java.util.List r4 = r2.getVisibleNotifications()     // Catch: java.lang.Throwable -> L8a
            com.onesignal.notifications.internal.data.INotificationRepository r5 = r2._dataController     // Catch: java.lang.Throwable -> L8a
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L8a
            r6 = 1
            r0.label = r6     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r5 = r5.listNotificationsForOutstanding(r4, r0)     // Catch: java.lang.Throwable -> L8a
            if (r5 != r1) goto L5f
            return r1
        L5f:
            java.util.List r5 = (java.util.List) r5     // Catch: java.lang.Throwable -> L8a
            java.util.Iterator r4 = r5.iterator()     // Catch: java.lang.Throwable -> L8a
            r7 = r4
            r4 = r2
            r2 = r7
        L68:
            boolean r5 = r2.hasNext()     // Catch: java.lang.Throwable -> L8a
            if (r5 == 0) goto L84
            java.lang.Object r5 = r2.next()     // Catch: java.lang.Throwable -> L8a
            com.onesignal.notifications.internal.data.INotificationRepository$NotificationData r5 = (com.onesignal.notifications.internal.data.INotificationRepository.NotificationData) r5     // Catch: java.lang.Throwable -> L8a
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L8a
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L8a
            r0.label = r3     // Catch: java.lang.Throwable -> L8a
            r6 = 200(0xc8, float:2.8E-43)
            java.lang.Object r6 = r4.processNotification(r5, r6, r0)     // Catch: java.lang.Throwable -> L8a
            if (r6 != r1) goto L83
            return r1
        L83:
            goto L68
        L84:
            com.onesignal.notifications.internal.badges.IBadgeCountUpdater r1 = r4._badgeCountUpdater     // Catch: java.lang.Throwable -> L8a
            r1.update()     // Catch: java.lang.Throwable -> L8a
            goto L90
        L8a:
            r1 = move-exception
            java.lang.String r2 = "Error restoring notification records! "
            com.onesignal.debug.internal.logging.Logging.error(r2, r1)
        L90:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.notifications.internal.restoration.impl.NotificationRestoreProcessor.process(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.onesignal.notifications.internal.restoration.INotificationRestoreProcessor
    public Object processNotification(INotificationRepository.NotificationData notification, int delay, Continuation<? super Unit> continuation) {
        Object objDelay;
        this._workManager.beginEnqueueingWork(this._applicationService.getAppContext(), notification.getId(), notification.getAndroidId(), new JSONObject(notification.getFullData()), notification.getCreatedAt(), true, false);
        return (delay <= 0 || (objDelay = DelayKt.delay((long) delay, continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objDelay;
    }

    private final List<Integer> getVisibleNotifications() {
        StatusBarNotification[] activeNotifs = NotificationHelper.INSTANCE.getActiveNotifications(this._applicationService.getAppContext());
        if (activeNotifs.length == 0) {
            return null;
        }
        List activeNotifIds = new ArrayList();
        for (StatusBarNotification activeNotif : activeNotifs) {
            activeNotifIds.add(Integer.valueOf(activeNotif.getId()));
        }
        return activeNotifIds;
    }

    /* JADX INFO: compiled from: NotificationRestoreProcessor.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onesignal/notifications/internal/restoration/impl/NotificationRestoreProcessor$Companion;", "", "()V", "DEFAULT_TTL_IF_NOT_IN_PAYLOAD", "", "DELAY_BETWEEN_NOTIFICATION_RESTORES_MS", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
