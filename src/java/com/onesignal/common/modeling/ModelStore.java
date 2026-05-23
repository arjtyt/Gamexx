package com.onesignal.common.modeling;

import com.onesignal.common.events.EventProducer;
import com.onesignal.common.events.IEventNotifier;
import com.onesignal.common.modeling.Model;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.preferences.IPreferencesService;
import com.onesignal.core.internal.preferences.PreferenceOneSignalKeys;
import com.onesignal.core.internal.preferences.PreferenceStores;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;

/* JADX INFO: compiled from: ModelStore.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00052\u00020\u0006B\u001d\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u001d\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\u001bJ%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\u001eJ)\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0002\u0010 J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\bH\u0016J\u0017\u0010\"\u001a\u0004\u0018\u00018\u00002\u0006\u0010#\u001a\u00020\bH\u0016¢\u0006\u0002\u0010$J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000&H\u0016J\b\u0010'\u001a\u00020\u0018H\u0004J\u0018\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001a\u001a\u00020\bH\u0016J\u0006\u0010+\u001a\u00020\u0018J\u0018\u0010,\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\bH\u0016J\u001d\u0010-\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\u001bJ\u001e\u0010.\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000/2\u0006\u0010\u001a\u001a\u00020\bH\u0016J\u0016\u00100\u001a\u00020\u00182\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016J\u0016\u00102\u001a\u00020\u00182\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u00063"}, d2 = {"Lcom/onesignal/common/modeling/ModelStore;", "TModel", "Lcom/onesignal/common/modeling/Model;", "Lcom/onesignal/common/events/IEventNotifier;", "Lcom/onesignal/common/modeling/IModelStoreChangeHandler;", "Lcom/onesignal/common/modeling/IModelStore;", "Lcom/onesignal/common/modeling/IModelChangedHandler;", "name", "", "_prefs", "Lcom/onesignal/core/internal/preferences/IPreferencesService;", "(Ljava/lang/String;Lcom/onesignal/core/internal/preferences/IPreferencesService;)V", "changeSubscription", "Lcom/onesignal/common/events/EventProducer;", "hasLoadedFromCache", "", "hasSubscribers", "getHasSubscribers", "()Z", "models", "", "getName", "()Ljava/lang/String;", "add", "", "model", "tag", "(Lcom/onesignal/common/modeling/Model;Ljava/lang/String;)V", "index", "", "(ILcom/onesignal/common/modeling/Model;Ljava/lang/String;)V", "addItem", "(Lcom/onesignal/common/modeling/Model;Ljava/lang/String;Ljava/lang/Integer;)V", "clear", "get", OutcomeConstants.OUTCOME_ID, "(Ljava/lang/String;)Lcom/onesignal/common/modeling/Model;", "list", "", "load", "onChanged", "args", "Lcom/onesignal/common/modeling/ModelChangedArgs;", "persist", "remove", "removeItem", "replaceAll", "", "subscribe", "handler", "unsubscribe", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class ModelStore<TModel extends Model> implements IEventNotifier<IModelStoreChangeHandler<TModel>>, IModelStore<TModel>, IModelChangedHandler {
    private final IPreferencesService _prefs;
    private final EventProducer<IModelStoreChangeHandler<TModel>> changeSubscription;
    private boolean hasLoadedFromCache;
    private final List<TModel> models;
    private final String name;

    /* JADX WARN: Multi-variable type inference failed */
    public ModelStore() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public ModelStore(String name, IPreferencesService _prefs) {
        this.name = name;
        this._prefs = _prefs;
        this.changeSubscription = new EventProducer<>();
        this.models = new ArrayList();
    }

    public /* synthetic */ ModelStore(String str, IPreferencesService iPreferencesService, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : iPreferencesService);
    }

    public final String getName() {
        return this.name;
    }

    @Override // com.onesignal.common.modeling.IModelStore
    public void add(TModel tmodel, String tag) throws Throwable {
        Object element$iv;
        Intrinsics.checkNotNullParameter(tmodel, "model");
        Intrinsics.checkNotNullParameter(tag, "tag");
        synchronized (this.models) {
            try {
                Iterable $this$firstOrNull$iv = this.models;
                Iterator it = $this$firstOrNull$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    try {
                        element$iv = it.next();
                        Model it2 = (Model) element$iv;
                        if (Intrinsics.areEqual(it2.getId(), tmodel.getId())) {
                            break;
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                Model oldModel = (Model) element$iv;
                if (oldModel != null) {
                    removeItem(oldModel, tag);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                addItem$default(this, tmodel, tag, null, 4, null);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    @Override // com.onesignal.common.modeling.IModelStore
    public void add(int index, TModel tmodel, String tag) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(tmodel, "model");
        Intrinsics.checkNotNullParameter(tag, "tag");
        synchronized (this.models) {
            Iterable $this$firstOrNull$iv = this.models;
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    element$iv = it.next();
                    Model it2 = (Model) element$iv;
                    if (Intrinsics.areEqual(it2.getId(), tmodel.getId())) {
                        break;
                    }
                } else {
                    element$iv = null;
                    break;
                }
            }
            Model oldModel = (Model) element$iv;
            if (oldModel != null) {
                removeItem(oldModel, tag);
            }
            addItem(tmodel, tag, Integer.valueOf(index));
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.onesignal.common.modeling.IModelStore
    public Collection<TModel> list() {
        List list;
        synchronized (this.models) {
            list = CollectionsKt.toList(this.models);
        }
        return list;
    }

    @Override // com.onesignal.common.modeling.IModelStore
    public TModel get(String id) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(id, OutcomeConstants.OUTCOME_ID);
        List<TModel> $this$firstOrNull$iv = this.models;
        Iterator<T> it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                Model it2 = (Model) element$iv;
                if (Intrinsics.areEqual(it2.getId(), id)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        return (TModel) element$iv;
    }

    @Override // com.onesignal.common.modeling.IModelStore
    public void remove(String id, String tag) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(id, OutcomeConstants.OUTCOME_ID);
        Intrinsics.checkNotNullParameter(tag, "tag");
        synchronized (this.models) {
            Iterable $this$firstOrNull$iv = this.models;
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    element$iv = it.next();
                    Model it2 = (Model) element$iv;
                    if (Intrinsics.areEqual(it2.getId(), id)) {
                        break;
                    }
                } else {
                    element$iv = null;
                    break;
                }
            }
            Model model = (Model) element$iv;
            if (model == null) {
                return;
            }
            removeItem(model, tag);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.onesignal.common.modeling.IModelChangedHandler
    public void onChanged(final ModelChangedArgs args, final String tag) {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(tag, "tag");
        persist();
        this.changeSubscription.fire(new Function1<IModelStoreChangeHandler<TModel>, Unit>() { // from class: com.onesignal.common.modeling.ModelStore.onChanged.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IModelStoreChangeHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IModelStoreChangeHandler<TModel> iModelStoreChangeHandler) {
                Intrinsics.checkNotNullParameter(iModelStoreChangeHandler, "it");
                iModelStoreChangeHandler.onModelUpdated(args, tag);
            }
        });
    }

    @Override // com.onesignal.common.modeling.IModelStore
    public void replaceAll(List<? extends TModel> list, String tag) {
        Intrinsics.checkNotNullParameter(list, "models");
        Intrinsics.checkNotNullParameter(tag, "tag");
        synchronized (list) {
            clear(tag);
            Iterator<? extends TModel> it = list.iterator();
            while (it.hasNext()) {
                add(it.next(), tag);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.onesignal.common.modeling.IModelStore
    public void clear(final String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        List<Model> localList = CollectionsKt.toList(this.models);
        synchronized (this.models) {
            this.models.clear();
            persist();
            Unit unit = Unit.INSTANCE;
        }
        for (final Model item : localList) {
            item.unsubscribe((IModelChangedHandler) this);
            this.changeSubscription.fire(new Function1<IModelStoreChangeHandler<TModel>, Unit>() { // from class: com.onesignal.common.modeling.ModelStore.clear.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((IModelStoreChangeHandler) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(IModelStoreChangeHandler<TModel> iModelStoreChangeHandler) {
                    Intrinsics.checkNotNullParameter(iModelStoreChangeHandler, "it");
                    iModelStoreChangeHandler.onModelRemoved(item, tag);
                }
            });
        }
    }

    static /* synthetic */ void addItem$default(ModelStore modelStore, Model model, String str, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addItem");
        }
        if ((i & 4) != 0) {
            num = null;
        }
        modelStore.addItem(model, str, num);
    }

    private final void addItem(final TModel tmodel, final String tag, Integer index) {
        synchronized (this.models) {
            if (index != null) {
                this.models.add(index.intValue(), tmodel);
            } else {
                this.models.add(tmodel);
            }
            tmodel.subscribe(this);
            persist();
            Unit unit = Unit.INSTANCE;
        }
        this.changeSubscription.fire(new Function1<IModelStoreChangeHandler<TModel>, Unit>() { // from class: com.onesignal.common.modeling.ModelStore.addItem.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IModelStoreChangeHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IModelStoreChangeHandler<TModel> iModelStoreChangeHandler) {
                Intrinsics.checkNotNullParameter(iModelStoreChangeHandler, "it");
                iModelStoreChangeHandler.onModelAdded(tmodel, tag);
            }
        });
    }

    private final void removeItem(final TModel tmodel, final String tag) {
        synchronized (this.models) {
            this.models.remove(tmodel);
            tmodel.unsubscribe(this);
            persist();
            Unit unit = Unit.INSTANCE;
        }
        this.changeSubscription.fire(new Function1<IModelStoreChangeHandler<TModel>, Unit>() { // from class: com.onesignal.common.modeling.ModelStore.removeItem.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IModelStoreChangeHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IModelStoreChangeHandler<TModel> iModelStoreChangeHandler) {
                Intrinsics.checkNotNullParameter(iModelStoreChangeHandler, "it");
                iModelStoreChangeHandler.onModelRemoved(tmodel, tag);
            }
        });
    }

    protected final void load() {
        boolean hasExisting;
        if (this.name != null && this._prefs != null) {
            String str = this._prefs.getString(PreferenceStores.ONESIGNAL, PreferenceOneSignalKeys.MODEL_STORE_PREFIX + this.name, "[]");
            JSONArray jsonArray = new JSONArray(str);
            synchronized (this.models) {
                boolean shouldRePersist = !this.models.isEmpty();
                for (int index = jsonArray.length() - 1; -1 < index; index--) {
                    TModel tmodelCreate = create(jsonArray.getJSONObject(index));
                    if (tmodelCreate != null) {
                        Iterable $this$any$iv = this.models;
                        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                            Iterator it = $this$any$iv.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    Object element$iv = it.next();
                                    Model it2 = (Model) element$iv;
                                    if (Intrinsics.areEqual(it2.getId(), tmodelCreate.getId())) {
                                        hasExisting = true;
                                        break;
                                    }
                                } else {
                                    hasExisting = false;
                                    break;
                                }
                            }
                        } else {
                            hasExisting = false;
                        }
                        if (hasExisting) {
                            Logging.debug$default("ModelStore<" + this.name + ">: load - operation.id: " + tmodelCreate.getId() + " already exists in the store.", null, 2, null);
                        } else {
                            this.models.add(0, tmodelCreate);
                            tmodelCreate.subscribe(this);
                        }
                    }
                }
                this.hasLoadedFromCache = true;
                if (shouldRePersist) {
                    persist();
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void persist() {
        if (this.name == null || this._prefs == null || !this.hasLoadedFromCache) {
            return;
        }
        JSONArray jsonArray = new JSONArray();
        synchronized (this.models) {
            for (Model model : this.models) {
                jsonArray.put(model.toJSON());
            }
            Unit unit = Unit.INSTANCE;
        }
        this._prefs.saveString(PreferenceStores.ONESIGNAL, PreferenceOneSignalKeys.MODEL_STORE_PREFIX + this.name, jsonArray.toString());
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public void subscribe(IModelStoreChangeHandler<TModel> iModelStoreChangeHandler) {
        Intrinsics.checkNotNullParameter(iModelStoreChangeHandler, "handler");
        this.changeSubscription.subscribe(iModelStoreChangeHandler);
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public void unsubscribe(IModelStoreChangeHandler<TModel> iModelStoreChangeHandler) {
        Intrinsics.checkNotNullParameter(iModelStoreChangeHandler, "handler");
        this.changeSubscription.unsubscribe(iModelStoreChangeHandler);
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public boolean getHasSubscribers() {
        return this.changeSubscription.getHasSubscribers();
    }
}
