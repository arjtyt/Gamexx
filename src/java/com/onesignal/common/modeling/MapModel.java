package com.onesignal.common.modeling;

import com.onesignal.core.BuildConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableMap;

/* JADX INFO: compiled from: MapModel.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0016J\u0015\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u0004H\u0096\u0002¢\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u001aH\u0016J\u001d\u0010\"\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010#J\u001e\u0010$\u001a\u00020\u00182\u0014\u0010%\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000&H\u0016J\u0015\u0010'\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u0004H\u0016¢\u0006\u0002\u0010 R&\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006("}, d2 = {"Lcom/onesignal/common/modeling/MapModel;", "V", "Lcom/onesignal/common/modeling/Model;", "", "", "parentModel", "parentProperty", "(Lcom/onesignal/common/modeling/Model;Ljava/lang/String;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "clear", "", "containsKey", "", "key", "containsValue", "value", "(Ljava/lang/Object;)Z", "get", "(Ljava/lang/String;)Ljava/lang/Object;", "isEmpty", "put", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "from", "", "remove", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public class MapModel<V> extends Model implements Map<String, V>, KMutableMap {
    /* JADX WARN: Multi-variable type inference failed */
    public MapModel() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public /* synthetic */ MapModel(Model model, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : model, (i & 2) != 0 ? null : str);
    }

    @Override // java.util.Map
    public final /* bridge */ boolean containsKey(Object key) {
        if (key instanceof String) {
            return containsKey((String) key);
        }
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<String, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public final /* bridge */ V get(Object key) {
        if (key instanceof String) {
            return get((String) key);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<String> keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public final /* bridge */ V remove(Object key) {
        if (key instanceof String) {
            return remove((String) key);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    public MapModel(Model parentModel, String parentProperty) {
        super(parentModel, parentProperty);
    }

    public int getSize() {
        return getData().size();
    }

    public Set<Map.Entry<String, V>> getEntries() {
        Iterable $this$filterIsInstance$iv = getData().entrySet();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (TypeIntrinsics.isMutableMapEntry(element$iv$iv)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return CollectionsKt.toMutableSet((List) destination$iv$iv);
    }

    public Set<String> getKeys() {
        return getData().keySet();
    }

    public Collection<V> getValues() {
        Iterable $this$map$iv = getData().values();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(item$iv$iv);
        }
        return CollectionsKt.toMutableList((List) destination$iv$iv);
    }

    public boolean containsKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getData().containsKey(key);
    }

    @Override // java.util.Map
    public boolean containsValue(V v) {
        return getData().containsValue(v);
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return getData().isEmpty();
    }

    public V get(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        return (V) Model.getOptAnyProperty$default(this, str, null, 2, null);
    }

    @Override // java.util.Map
    public void clear() throws Throwable {
        for (String property : getData().keySet()) {
            Model.setOptAnyProperty$default(this, property, null, null, false, 12, null);
        }
    }

    @Override // java.util.Map
    public V put(String key, V v) throws Throwable {
        Intrinsics.checkNotNullParameter(key, "key");
        Model.setOptAnyProperty$default(this, key, v, null, false, 12, null);
        return v;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends V> map) throws Throwable {
        Intrinsics.checkNotNullParameter(map, "from");
        for (Map.Entry<? extends String, ? extends V> entry : map.entrySet()) {
            Model.setOptAnyProperty$default(this, entry.getKey(), entry.getValue(), null, false, 12, null);
        }
    }

    public V remove(String str) throws Throwable {
        Intrinsics.checkNotNullParameter(str, "key");
        V v = (V) Model.getOptAnyProperty$default(this, str, null, 2, null);
        Model.setOptAnyProperty$default(this, str, null, null, false, 12, null);
        return v;
    }
}
