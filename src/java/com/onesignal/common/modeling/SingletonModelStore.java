package com.onesignal.common.modeling;

import com.onesignal.common.events.EventProducer;
import com.onesignal.common.modeling.IModelStore;
import com.onesignal.common.modeling.Model;
import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SingletonModelStore.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u001d\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0015H\u0016¢\u0006\u0002\u0010\u001bJ\u001d\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0015H\u0016¢\u0006\u0002\u0010\u001bJ\u0018\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u0015H\u0016J\u001d\u0010 \u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0015H\u0016¢\u0006\u0002\u0010\u001bJ\u0016\u0010!\u001a\u00020\u00192\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016J\u0016\u0010#\u001a\u00020\u00192\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lcom/onesignal/common/modeling/SingletonModelStore;", "TModel", "Lcom/onesignal/common/modeling/Model;", "Lcom/onesignal/common/modeling/ISingletonModelStore;", "Lcom/onesignal/common/modeling/IModelStoreChangeHandler;", "store", "Lcom/onesignal/common/modeling/ModelStore;", "(Lcom/onesignal/common/modeling/ModelStore;)V", "changeSubscription", "Lcom/onesignal/common/events/EventProducer;", "Lcom/onesignal/common/modeling/ISingletonModelStoreChangeHandler;", "hasSubscribers", "", "getHasSubscribers", "()Z", "model", "getModel", "()Lcom/onesignal/common/modeling/Model;", "replaceLock", "", "singletonId", "", "getStore", "()Lcom/onesignal/common/modeling/ModelStore;", "onModelAdded", "", "tag", "(Lcom/onesignal/common/modeling/Model;Ljava/lang/String;)V", "onModelRemoved", "onModelUpdated", "args", "Lcom/onesignal/common/modeling/ModelChangedArgs;", "replace", "subscribe", "handler", "unsubscribe", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public class SingletonModelStore<TModel extends Model> implements ISingletonModelStore<TModel>, IModelStoreChangeHandler<TModel> {
    private final EventProducer<ISingletonModelStoreChangeHandler<TModel>> changeSubscription;
    private final Object replaceLock;
    private final String singletonId;
    private final ModelStore<TModel> store;

    public SingletonModelStore(ModelStore<TModel> modelStore) {
        Intrinsics.checkNotNullParameter(modelStore, "store");
        this.store = modelStore;
        this.changeSubscription = new EventProducer<>();
        this.singletonId = "-singleton-";
        this.replaceLock = new Object();
        this.store.subscribe((IModelStoreChangeHandler) this);
    }

    public final ModelStore<TModel> getStore() {
        return this.store;
    }

    @Override // com.onesignal.common.modeling.ISingletonModelStore
    public TModel getModel() {
        synchronized (this) {
            TModel tmodel = (TModel) this.store.get(this.singletonId);
            if (tmodel != null) {
                return tmodel;
            }
            TModel tmodel2 = (TModel) IModelStore.DefaultImpls.create$default(this.store, null, 1, null);
            if (tmodel2 == null) {
                throw new Exception("Unable to initialize model from store " + this.store);
            }
            tmodel2.setId(this.singletonId);
            IModelStore.DefaultImpls.add$default(this.store, tmodel2, null, 2, null);
            return tmodel2;
        }
    }

    @Override // com.onesignal.common.modeling.ISingletonModelStore
    public void replace(TModel tmodel, final String tag) {
        Intrinsics.checkNotNullParameter(tmodel, "model");
        Intrinsics.checkNotNullParameter(tag, "tag");
        synchronized (this.replaceLock) {
            final Model existingModel = getModel();
            existingModel.initializeFromModel(this.singletonId, tmodel);
            this.store.persist();
            this.changeSubscription.fire(new Function1<ISingletonModelStoreChangeHandler<TModel>, Unit>() { // from class: com.onesignal.common.modeling.SingletonModelStore$replace$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Incorrect types in method signature: (TTModel;Ljava/lang/String;)V */
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((ISingletonModelStoreChangeHandler) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(ISingletonModelStoreChangeHandler<TModel> iSingletonModelStoreChangeHandler) {
                    Intrinsics.checkNotNullParameter(iSingletonModelStoreChangeHandler, "it");
                    iSingletonModelStoreChangeHandler.onModelReplaced(existingModel, tag);
                }
            });
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public void subscribe(ISingletonModelStoreChangeHandler<TModel> iSingletonModelStoreChangeHandler) {
        Intrinsics.checkNotNullParameter(iSingletonModelStoreChangeHandler, "handler");
        this.changeSubscription.subscribe(iSingletonModelStoreChangeHandler);
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public void unsubscribe(ISingletonModelStoreChangeHandler<TModel> iSingletonModelStoreChangeHandler) {
        Intrinsics.checkNotNullParameter(iSingletonModelStoreChangeHandler, "handler");
        this.changeSubscription.unsubscribe(iSingletonModelStoreChangeHandler);
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public boolean getHasSubscribers() {
        return this.changeSubscription.getHasSubscribers();
    }

    @Override // com.onesignal.common.modeling.IModelStoreChangeHandler
    public void onModelAdded(TModel tmodel, String tag) {
        Intrinsics.checkNotNullParameter(tmodel, "model");
        Intrinsics.checkNotNullParameter(tag, "tag");
    }

    @Override // com.onesignal.common.modeling.IModelStoreChangeHandler
    public void onModelUpdated(final ModelChangedArgs args, final String tag) {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.changeSubscription.fire(new Function1<ISingletonModelStoreChangeHandler<TModel>, Unit>() { // from class: com.onesignal.common.modeling.SingletonModelStore.onModelUpdated.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((ISingletonModelStoreChangeHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(ISingletonModelStoreChangeHandler<TModel> iSingletonModelStoreChangeHandler) {
                Intrinsics.checkNotNullParameter(iSingletonModelStoreChangeHandler, "it");
                iSingletonModelStoreChangeHandler.onModelUpdated(args, tag);
            }
        });
    }

    @Override // com.onesignal.common.modeling.IModelStoreChangeHandler
    public void onModelRemoved(TModel tmodel, String tag) {
        Intrinsics.checkNotNullParameter(tmodel, "model");
        Intrinsics.checkNotNullParameter(tag, "tag");
    }
}
