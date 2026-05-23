package com.onesignal.common.modeling;

import com.onesignal.common.events.IEventNotifier;
import com.onesignal.common.modeling.Model;
import com.onesignal.core.BuildConfig;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.json.JSONObject;

/* JADX INFO: compiled from: IModelStore.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003J\u001f\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u00002\b\b\u0002\u0010\b\u001a\u00020\tH&¢\u0006\u0002\u0010\nJ'\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00028\u00002\b\b\u0002\u0010\b\u001a\u00020\tH&¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tH&J\u001b\u0010\u000f\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&¢\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0014\u001a\u00020\tH&¢\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H&J\u001a\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\t2\b\b\u0002\u0010\b\u001a\u00020\tH&J \u0010\u0019\u001a\u00020\u00062\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b2\b\b\u0002\u0010\b\u001a\u00020\tH&¨\u0006\u001c"}, d2 = {"Lcom/onesignal/common/modeling/IModelStore;", "TModel", "Lcom/onesignal/common/modeling/Model;", "Lcom/onesignal/common/events/IEventNotifier;", "Lcom/onesignal/common/modeling/IModelStoreChangeHandler;", "add", "", "model", "tag", "", "(Lcom/onesignal/common/modeling/Model;Ljava/lang/String;)V", "index", "", "(ILcom/onesignal/common/modeling/Model;Ljava/lang/String;)V", "clear", "create", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)Lcom/onesignal/common/modeling/Model;", "get", OutcomeConstants.OUTCOME_ID, "(Ljava/lang/String;)Lcom/onesignal/common/modeling/Model;", "list", "", "remove", "replaceAll", "models", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface IModelStore<TModel extends Model> extends IEventNotifier<IModelStoreChangeHandler<TModel>> {
    void add(int i, TModel tmodel, String str);

    void add(TModel tmodel, String str);

    void clear(String str);

    TModel create(JSONObject jSONObject);

    TModel get(String str);

    Collection<TModel> list();

    void remove(String str, String str2);

    void replaceAll(List<? extends TModel> list, String str);

    /* JADX INFO: compiled from: IModelStore.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Model create$default(IModelStore iModelStore, JSONObject jSONObject, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: create");
            }
            if ((i & 1) != 0) {
                jSONObject = null;
            }
            return iModelStore.create(jSONObject);
        }

        public static /* synthetic */ void add$default(IModelStore iModelStore, Model model, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: add");
            }
            if ((i & 2) != 0) {
                str = ModelChangeTags.NORMAL;
            }
            iModelStore.add(model, str);
        }

        public static /* synthetic */ void add$default(IModelStore iModelStore, int i, Model model, String str, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: add");
            }
            if ((i2 & 4) != 0) {
                str = ModelChangeTags.NORMAL;
            }
            iModelStore.add(i, model, str);
        }

        public static /* synthetic */ void remove$default(IModelStore iModelStore, String str, String str2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: remove");
            }
            if ((i & 2) != 0) {
                str2 = ModelChangeTags.NORMAL;
            }
            iModelStore.remove(str, str2);
        }

        public static /* synthetic */ void clear$default(IModelStore iModelStore, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clear");
            }
            if ((i & 1) != 0) {
                str = ModelChangeTags.NORMAL;
            }
            iModelStore.clear(str);
        }

        public static /* synthetic */ void replaceAll$default(IModelStore iModelStore, List list, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: replaceAll");
            }
            if ((i & 2) != 0) {
                str = ModelChangeTags.NORMAL;
            }
            iModelStore.replaceAll(list, str);
        }
    }
}
