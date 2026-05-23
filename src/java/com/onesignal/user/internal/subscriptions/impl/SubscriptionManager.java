package com.onesignal.user.internal.subscriptions.impl;

import android.os.Build;
import com.onesignal.common.AndroidUtils;
import com.onesignal.common.DeviceUtils;
import com.onesignal.common.IDManager;
import com.onesignal.common.OneSignalUtils;
import com.onesignal.common.events.EventProducer;
import com.onesignal.common.modeling.IModelStore;
import com.onesignal.common.modeling.IModelStoreChangeHandler;
import com.onesignal.common.modeling.Model;
import com.onesignal.common.modeling.ModelChangedArgs;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.debug.LogLevel;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.inAppMessages.internal.display.impl.WebViewManager;
import com.onesignal.session.internal.session.ISessionLifecycleHandler;
import com.onesignal.session.internal.session.ISessionService;
import com.onesignal.user.internal.EmailSubscription;
import com.onesignal.user.internal.PushSubscription;
import com.onesignal.user.internal.SmsSubscription;
import com.onesignal.user.internal.Subscription;
import com.onesignal.user.internal.UninitializedPushSubscription;
import com.onesignal.user.internal.subscriptions.ISubscriptionChangedHandler;
import com.onesignal.user.internal.subscriptions.ISubscriptionManager;
import com.onesignal.user.internal.subscriptions.SubscriptionList;
import com.onesignal.user.internal.subscriptions.SubscriptionModel;
import com.onesignal.user.internal.subscriptions.SubscriptionModelStore;
import com.onesignal.user.internal.subscriptions.SubscriptionStatus;
import com.onesignal.user.internal.subscriptions.SubscriptionType;
import com.onesignal.user.subscriptions.IEmailSubscription;
import com.onesignal.user.subscriptions.IPushSubscription;
import com.onesignal.user.subscriptions.IPushSubscriptionObserver;
import com.onesignal.user.subscriptions.ISmsSubscription;
import com.onesignal.user.subscriptions.ISubscription;
import com.onesignal.user.subscriptions.PushSubscriptionChangedState;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubscriptionManager.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u001a\u0010 \u001a\u00020\u001d2\b\u0010!\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u001fH\u0016J$\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001f2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010#H\u0002J\u0010\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u0003H\u0002J\u0010\u0010-\u001a\u00020.2\u0006\u0010,\u001a\u00020\u0003H\u0002J\u0018\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u001fH\u0016J\u0018\u00102\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u001fH\u0016J\u0018\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u0002052\u0006\u00101\u001a\u00020\u001fH\u0016J\b\u00106\u001a\u00020\u001dH\u0016J\u0010\u00107\u001a\u00020\u001d2\u0006\u00108\u001a\u000209H\u0016J\b\u0010:\u001a\u00020\u001dH\u0016J\b\u0010;\u001a\u00020\u001dH\u0002J\u0010\u0010<\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010=\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u001fH\u0016J\u0010\u0010>\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020.H\u0002J\u0010\u0010@\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020.H\u0002J\u0010\u0010A\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020\u000eH\u0016J\u0010\u0010C\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006D"}, d2 = {"Lcom/onesignal/user/internal/subscriptions/impl/SubscriptionManager;", "Lcom/onesignal/user/internal/subscriptions/ISubscriptionManager;", "Lcom/onesignal/common/modeling/IModelStoreChangeHandler;", "Lcom/onesignal/user/internal/subscriptions/SubscriptionModel;", "Lcom/onesignal/session/internal/session/ISessionLifecycleHandler;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_sessionService", "Lcom/onesignal/session/internal/session/ISessionService;", "_subscriptionModelStore", "Lcom/onesignal/user/internal/subscriptions/SubscriptionModelStore;", "(Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/session/internal/session/ISessionService;Lcom/onesignal/user/internal/subscriptions/SubscriptionModelStore;)V", "events", "Lcom/onesignal/common/events/EventProducer;", "Lcom/onesignal/user/internal/subscriptions/ISubscriptionChangedHandler;", "hasSubscribers", "", "getHasSubscribers", "()Z", "pushSubscriptionModel", "getPushSubscriptionModel", "()Lcom/onesignal/user/internal/subscriptions/SubscriptionModel;", "subscriptions", "Lcom/onesignal/user/internal/subscriptions/SubscriptionList;", "getSubscriptions", "()Lcom/onesignal/user/internal/subscriptions/SubscriptionList;", "setSubscriptions", "(Lcom/onesignal/user/internal/subscriptions/SubscriptionList;)V", "addEmailSubscription", "", "email", "", "addOrUpdatePushSubscriptionToken", "pushToken", "pushTokenStatus", "Lcom/onesignal/user/internal/subscriptions/SubscriptionStatus;", "addSmsSubscription", "sms", "addSubscriptionToModels", WebViewManager.EVENT_TYPE_KEY, "Lcom/onesignal/user/internal/subscriptions/SubscriptionType;", "address", "status", "createSubscriptionAndAddToSubscriptionList", "subscriptionModel", "createSubscriptionFromModel", "Lcom/onesignal/user/subscriptions/ISubscription;", "onModelAdded", "model", "tag", "onModelRemoved", "onModelUpdated", "args", "Lcom/onesignal/common/modeling/ModelChangedArgs;", "onSessionActive", "onSessionEnded", "duration", "", "onSessionStarted", "refreshPushSubscriptionState", "removeEmailSubscription", "removeSmsSubscription", "removeSubscriptionFromModels", "subscription", "removeSubscriptionFromSubscriptionList", "subscribe", "handler", "unsubscribe", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubscriptionManager implements ISubscriptionManager, IModelStoreChangeHandler<SubscriptionModel>, ISessionLifecycleHandler {
    private final IApplicationService _applicationService;
    private final ISessionService _sessionService;
    private final SubscriptionModelStore _subscriptionModelStore;
    private final EventProducer<ISubscriptionChangedHandler> events;
    private SubscriptionList subscriptions;

    /* JADX INFO: compiled from: SubscriptionManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SubscriptionType.values().length];
            iArr[SubscriptionType.SMS.ordinal()] = 1;
            iArr[SubscriptionType.EMAIL.ordinal()] = 2;
            iArr[SubscriptionType.PUSH.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    public SubscriptionManager(IApplicationService _applicationService, ISessionService _sessionService, SubscriptionModelStore _subscriptionModelStore) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_sessionService, "_sessionService");
        Intrinsics.checkNotNullParameter(_subscriptionModelStore, "_subscriptionModelStore");
        this._applicationService = _applicationService;
        this._sessionService = _sessionService;
        this._subscriptionModelStore = _subscriptionModelStore;
        this.events = new EventProducer<>();
        this.subscriptions = new SubscriptionList(CollectionsKt.emptyList(), new UninitializedPushSubscription());
        for (TModel subscriptionModel : this._subscriptionModelStore.list()) {
            createSubscriptionAndAddToSubscriptionList(subscriptionModel);
        }
        this._subscriptionModelStore.subscribe((IModelStoreChangeHandler) this);
        this._sessionService.subscribe(this);
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionManager
    public SubscriptionList getSubscriptions() {
        return this.subscriptions;
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionManager
    public void setSubscriptions(SubscriptionList subscriptionList) {
        Intrinsics.checkNotNullParameter(subscriptionList, "<set-?>");
        this.subscriptions = subscriptionList;
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionManager
    public SubscriptionModel getPushSubscriptionModel() {
        IPushSubscription push = getSubscriptions().getPush();
        Intrinsics.checkNotNull(push, "null cannot be cast to non-null type com.onesignal.user.internal.PushSubscription");
        return ((PushSubscription) push).getModel();
    }

    @Override // com.onesignal.session.internal.session.ISessionLifecycleHandler
    public void onSessionStarted() throws Throwable {
        refreshPushSubscriptionState();
    }

    @Override // com.onesignal.session.internal.session.ISessionLifecycleHandler
    public void onSessionActive() {
    }

    @Override // com.onesignal.session.internal.session.ISessionLifecycleHandler
    public void onSessionEnded(long duration) {
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionManager
    public void addEmailSubscription(String email) throws Throwable {
        Intrinsics.checkNotNullParameter(email, "email");
        addSubscriptionToModels$default(this, SubscriptionType.EMAIL, email, null, 4, null);
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionManager
    public void addSmsSubscription(String sms) throws Throwable {
        Intrinsics.checkNotNullParameter(sms, "sms");
        addSubscriptionToModels$default(this, SubscriptionType.SMS, sms, null, 4, null);
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionManager
    public void addOrUpdatePushSubscriptionToken(String pushToken, SubscriptionStatus pushTokenStatus) throws Throwable {
        Intrinsics.checkNotNullParameter(pushTokenStatus, "pushTokenStatus");
        ISubscription push = getSubscriptions().getPush();
        if (push instanceof UninitializedPushSubscription) {
            addSubscriptionToModels(SubscriptionType.PUSH, pushToken == null ? "" : pushToken, pushTokenStatus);
            return;
        }
        Intrinsics.checkNotNull(push, "null cannot be cast to non-null type com.onesignal.user.internal.Subscription");
        SubscriptionModel pushSubModel = ((Subscription) push).getModel();
        if (pushToken != null) {
            pushSubModel.setAddress(pushToken);
        }
        pushSubModel.setStatus(pushTokenStatus);
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionManager
    public void removeEmailSubscription(String email) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(email, "email");
        Iterable $this$firstOrNull$iv = getSubscriptions().getEmails();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                IEmailSubscription it2 = (IEmailSubscription) element$iv;
                if ((it2 instanceof EmailSubscription) && Intrinsics.areEqual(it2.getEmail(), email)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        IEmailSubscription subscriptionToRem = (IEmailSubscription) element$iv;
        if (subscriptionToRem != null) {
            removeSubscriptionFromModels(subscriptionToRem);
        }
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionManager
    public void removeSmsSubscription(String sms) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(sms, "sms");
        Iterable $this$firstOrNull$iv = getSubscriptions().getSmss();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                ISmsSubscription it2 = (ISmsSubscription) element$iv;
                if ((it2 instanceof SmsSubscription) && Intrinsics.areEqual(it2.getNumber(), sms)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        ISmsSubscription subscriptionToRem = (ISmsSubscription) element$iv;
        if (subscriptionToRem != null) {
            removeSubscriptionFromModels(subscriptionToRem);
        }
    }

    static /* synthetic */ void addSubscriptionToModels$default(SubscriptionManager subscriptionManager, SubscriptionType subscriptionType, String str, SubscriptionStatus subscriptionStatus, int i, Object obj) throws Throwable {
        if ((i & 4) != 0) {
            subscriptionStatus = null;
        }
        subscriptionManager.addSubscriptionToModels(subscriptionType, str, subscriptionStatus);
    }

    private final void addSubscriptionToModels(SubscriptionType type, String address, SubscriptionStatus status) throws Throwable {
        Logging.log(LogLevel.DEBUG, "SubscriptionManager.addSubscription(type: " + type + ", address: " + address + ')');
        SubscriptionModel subscriptionModel = new SubscriptionModel();
        subscriptionModel.setId(IDManager.INSTANCE.createLocalId());
        subscriptionModel.setOptedIn(true);
        subscriptionModel.setType(type);
        subscriptionModel.setAddress(address);
        subscriptionModel.setStatus(status == null ? SubscriptionStatus.SUBSCRIBED : status);
        IModelStore.DefaultImpls.add$default(this._subscriptionModelStore, subscriptionModel, null, 2, null);
    }

    private final void removeSubscriptionFromModels(ISubscription subscription) {
        Logging.log(LogLevel.DEBUG, "SubscriptionManager.removeSubscription(subscription: " + subscription + ')');
        IModelStore.DefaultImpls.remove$default(this._subscriptionModelStore, subscription.getId(), null, 2, null);
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public void subscribe(ISubscriptionChangedHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.events.subscribe(handler);
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public void unsubscribe(ISubscriptionChangedHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.events.unsubscribe(handler);
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public boolean getHasSubscribers() {
        return this.events.getHasSubscribers();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @Override // com.onesignal.common.modeling.IModelStoreChangeHandler
    public void onModelAdded(SubscriptionModel model, String tag) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(tag, "tag");
        createSubscriptionAndAddToSubscriptionList(model);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @Override // com.onesignal.common.modeling.IModelStoreChangeHandler
    public void onModelUpdated(final ModelChangedArgs args, String tag) throws NoWhenBranchMatchedException {
        Object element$iv;
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Iterable $this$firstOrNull$iv = getSubscriptions().getCollection();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                ISubscription it2 = (ISubscription) element$iv;
                Model model = args.getModel();
                Intrinsics.checkNotNull(it2, "null cannot be cast to non-null type com.onesignal.user.internal.Subscription");
                if (Intrinsics.areEqual(model, ((Subscription) it2).getModel())) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        final ISubscription subscription = (ISubscription) element$iv;
        if (subscription == null) {
            Model model2 = args.getModel();
            Intrinsics.checkNotNull(model2, "null cannot be cast to non-null type com.onesignal.user.internal.subscriptions.SubscriptionModel");
            createSubscriptionAndAddToSubscriptionList((SubscriptionModel) model2);
        } else {
            if (subscription instanceof PushSubscription) {
                ((PushSubscription) subscription).getChangeHandlersNotifier().fireOnMain(new Function1<IPushSubscriptionObserver, Unit>() { // from class: com.onesignal.user.internal.subscriptions.impl.SubscriptionManager.onModelUpdated.1
                    {
                        super(1);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                        invoke((IPushSubscriptionObserver) p1);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(IPushSubscriptionObserver it3) {
                        Intrinsics.checkNotNullParameter(it3, "it");
                        it3.onPushSubscriptionChange(new PushSubscriptionChangedState(((PushSubscription) subscription).getSavedState(), ((PushSubscription) subscription).refreshState()));
                    }
                });
            }
            this.events.fire(new Function1<ISubscriptionChangedHandler, Unit>() { // from class: com.onesignal.user.internal.subscriptions.impl.SubscriptionManager.onModelUpdated.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((ISubscriptionChangedHandler) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(ISubscriptionChangedHandler it3) {
                    Intrinsics.checkNotNullParameter(it3, "it");
                    it3.onSubscriptionChanged(subscription, args);
                }
            });
        }
    }

    @Override // com.onesignal.common.modeling.IModelStoreChangeHandler
    public void onModelRemoved(SubscriptionModel model, String tag) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Iterable $this$firstOrNull$iv = getSubscriptions().getCollection();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                ISubscription it2 = (ISubscription) element$iv;
                if (Intrinsics.areEqual(it2.getId(), model.getId())) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        ISubscription subscription = (ISubscription) element$iv;
        if (subscription != null) {
            removeSubscriptionFromSubscriptionList(subscription);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    private final void createSubscriptionAndAddToSubscriptionList(SubscriptionModel subscriptionModel) throws NoWhenBranchMatchedException {
        final ISubscription subscription = createSubscriptionFromModel(subscriptionModel);
        List subscriptions = CollectionsKt.toMutableList(getSubscriptions().getCollection());
        if (subscriptionModel.getType() == SubscriptionType.PUSH) {
            IPushSubscription push = getSubscriptions().getPush();
            Intrinsics.checkNotNull(push, "null cannot be cast to non-null type com.onesignal.user.internal.PushSubscription");
            PushSubscription existingPushSubscription = (PushSubscription) push;
            Intrinsics.checkNotNull(subscription, "null cannot be cast to non-null type com.onesignal.user.internal.PushSubscription");
            ((PushSubscription) subscription).getChangeHandlersNotifier().subscribeAll(existingPushSubscription.getChangeHandlersNotifier());
            subscriptions.remove(existingPushSubscription);
        }
        subscriptions.add(subscription);
        setSubscriptions(new SubscriptionList(subscriptions, new UninitializedPushSubscription()));
        this.events.fire(new Function1<ISubscriptionChangedHandler, Unit>() { // from class: com.onesignal.user.internal.subscriptions.impl.SubscriptionManager.createSubscriptionAndAddToSubscriptionList.1
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((ISubscriptionChangedHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(ISubscriptionChangedHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onSubscriptionAdded(subscription);
            }
        });
    }

    private final void removeSubscriptionFromSubscriptionList(final ISubscription subscription) {
        List subscriptions = CollectionsKt.toMutableList(getSubscriptions().getCollection());
        subscriptions.remove(subscription);
        setSubscriptions(new SubscriptionList(subscriptions, new UninitializedPushSubscription()));
        this.events.fire(new Function1<ISubscriptionChangedHandler, Unit>() { // from class: com.onesignal.user.internal.subscriptions.impl.SubscriptionManager.removeSubscriptionFromSubscriptionList.1
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((ISubscriptionChangedHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(ISubscriptionChangedHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onSubscriptionRemoved(subscription);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    private final ISubscription createSubscriptionFromModel(SubscriptionModel subscriptionModel) throws NoWhenBranchMatchedException {
        switch (WhenMappings.$EnumSwitchMapping$0[subscriptionModel.getType().ordinal()]) {
            case 1:
                return new SmsSubscription(subscriptionModel);
            case 2:
                return new EmailSubscription(subscriptionModel);
            case 3:
                return new PushSubscription(subscriptionModel);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final void refreshPushSubscriptionState() throws Throwable {
        ISubscription push = getSubscriptions().getPush();
        if (push instanceof UninitializedPushSubscription) {
            return;
        }
        Intrinsics.checkNotNull(push, "null cannot be cast to non-null type com.onesignal.user.internal.Subscription");
        SubscriptionModel pushSubModel = ((Subscription) push).getModel();
        pushSubModel.setSdk(OneSignalUtils.SDK_VERSION);
        String str = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(str, "RELEASE");
        pushSubModel.setDeviceOS(str);
        String carrier = DeviceUtils.INSTANCE.getCarrierName(this._applicationService.getAppContext());
        if (carrier != null) {
            pushSubModel.setCarrier(carrier);
        }
        String appVersion = AndroidUtils.INSTANCE.getAppVersion(this._applicationService.getAppContext());
        if (appVersion != null) {
            pushSubModel.setAppVersion(appVersion);
        }
    }
}
