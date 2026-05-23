package com.onesignal.inAppMessages.internal.lifecycle.impl;

import com.onesignal.common.events.EventProducer;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.inAppMessages.BuildConfig;
import com.onesignal.inAppMessages.internal.InAppMessage;
import com.onesignal.inAppMessages.internal.InAppMessageClickResult;
import com.onesignal.inAppMessages.internal.InAppMessagePage;
import com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleEventHandler;
import com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IAMLifecycleService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u0013"}, d2 = {"Lcom/onesignal/inAppMessages/internal/lifecycle/impl/IAMLifecycleService;", "Lcom/onesignal/common/events/EventProducer;", "Lcom/onesignal/inAppMessages/internal/lifecycle/IInAppLifecycleEventHandler;", "Lcom/onesignal/inAppMessages/internal/lifecycle/IInAppLifecycleService;", "()V", "messageActionOccurredOnMessage", "", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "Lcom/onesignal/inAppMessages/internal/InAppMessage;", "action", "Lcom/onesignal/inAppMessages/internal/InAppMessageClickResult;", "messageActionOccurredOnPreview", "messagePageChanged", "page", "Lcom/onesignal/inAppMessages/internal/InAppMessagePage;", "messageWasDismissed", "messageWasDisplayed", "messageWillDismiss", "messageWillDisplay", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class IAMLifecycleService extends EventProducer<IInAppLifecycleEventHandler> implements IInAppLifecycleService {
    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService
    public void messageWillDisplay(final InAppMessage message) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        fire(new Function1<IInAppLifecycleEventHandler, Unit>() { // from class: com.onesignal.inAppMessages.internal.lifecycle.impl.IAMLifecycleService.messageWillDisplay.1
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IInAppLifecycleEventHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IInAppLifecycleEventHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onMessageWillDisplay(message);
            }
        });
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService
    public void messageWasDisplayed(final InAppMessage message) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        fire(new Function1<IInAppLifecycleEventHandler, Unit>() { // from class: com.onesignal.inAppMessages.internal.lifecycle.impl.IAMLifecycleService.messageWasDisplayed.1
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IInAppLifecycleEventHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IInAppLifecycleEventHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onMessageWasDisplayed(message);
            }
        });
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService
    public void messageActionOccurredOnPreview(final InAppMessage message, final InAppMessageClickResult action) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        Intrinsics.checkNotNullParameter(action, "action");
        fire(new Function1<IInAppLifecycleEventHandler, Unit>() { // from class: com.onesignal.inAppMessages.internal.lifecycle.impl.IAMLifecycleService.messageActionOccurredOnPreview.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IInAppLifecycleEventHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IInAppLifecycleEventHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onMessageActionOccurredOnPreview(message, action);
            }
        });
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService
    public void messageActionOccurredOnMessage(final InAppMessage message, final InAppMessageClickResult action) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        Intrinsics.checkNotNullParameter(action, "action");
        fire(new Function1<IInAppLifecycleEventHandler, Unit>() { // from class: com.onesignal.inAppMessages.internal.lifecycle.impl.IAMLifecycleService.messageActionOccurredOnMessage.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IInAppLifecycleEventHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IInAppLifecycleEventHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onMessageActionOccurredOnMessage(message, action);
            }
        });
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService
    public void messagePageChanged(final InAppMessage message, final InAppMessagePage page) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        Intrinsics.checkNotNullParameter(page, "page");
        fire(new Function1<IInAppLifecycleEventHandler, Unit>() { // from class: com.onesignal.inAppMessages.internal.lifecycle.impl.IAMLifecycleService.messagePageChanged.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IInAppLifecycleEventHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IInAppLifecycleEventHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onMessagePageChanged(message, page);
            }
        });
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService
    public void messageWillDismiss(final InAppMessage message) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        fire(new Function1<IInAppLifecycleEventHandler, Unit>() { // from class: com.onesignal.inAppMessages.internal.lifecycle.impl.IAMLifecycleService.messageWillDismiss.1
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IInAppLifecycleEventHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IInAppLifecycleEventHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onMessageWillDismiss(message);
            }
        });
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService
    public void messageWasDismissed(final InAppMessage message) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        fire(new Function1<IInAppLifecycleEventHandler, Unit>() { // from class: com.onesignal.inAppMessages.internal.lifecycle.impl.IAMLifecycleService.messageWasDismissed.1
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IInAppLifecycleEventHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IInAppLifecycleEventHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onMessageWasDismissed(message);
            }
        });
    }
}
