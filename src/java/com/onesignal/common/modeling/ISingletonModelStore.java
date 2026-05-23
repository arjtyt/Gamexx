package com.onesignal.common.modeling;

import com.onesignal.common.events.IEventNotifier;
import com.onesignal.common.modeling.Model;
import com.onesignal.core.BuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: ISingletonModelStore.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003J\u001f\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00028\u00002\b\b\u0002\u0010\n\u001a\u00020\u000bH&¢\u0006\u0002\u0010\fR\u0012\u0010\u0005\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/onesignal/common/modeling/ISingletonModelStore;", "TModel", "Lcom/onesignal/common/modeling/Model;", "Lcom/onesignal/common/events/IEventNotifier;", "Lcom/onesignal/common/modeling/ISingletonModelStoreChangeHandler;", "model", "getModel", "()Lcom/onesignal/common/modeling/Model;", "replace", "", "tag", "", "(Lcom/onesignal/common/modeling/Model;Ljava/lang/String;)V", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ISingletonModelStore<TModel extends Model> extends IEventNotifier<ISingletonModelStoreChangeHandler<TModel>> {
    TModel getModel();

    void replace(TModel tmodel, String str);

    /* JADX INFO: compiled from: ISingletonModelStore.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void replace$default(ISingletonModelStore iSingletonModelStore, Model model, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: replace");
            }
            if ((i & 2) != 0) {
                str = ModelChangeTags.NORMAL;
            }
            iSingletonModelStore.replace(model, str);
        }
    }
}
