package com.onesignal.core.internal.operations.impl;

import com.onesignal.common.modeling.ModelStore;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.operations.Operation;
import com.onesignal.core.internal.preferences.IPreferencesService;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.user.internal.operations.CreateSubscriptionOperation;
import com.onesignal.user.internal.operations.DeleteAliasOperation;
import com.onesignal.user.internal.operations.DeleteSubscriptionOperation;
import com.onesignal.user.internal.operations.DeleteTagOperation;
import com.onesignal.user.internal.operations.LoginUserFromSubscriptionOperation;
import com.onesignal.user.internal.operations.LoginUserOperation;
import com.onesignal.user.internal.operations.RefreshUserOperation;
import com.onesignal.user.internal.operations.SetAliasOperation;
import com.onesignal.user.internal.operations.SetPropertyOperation;
import com.onesignal.user.internal.operations.SetTagOperation;
import com.onesignal.user.internal.operations.TrackPurchaseOperation;
import com.onesignal.user.internal.operations.TrackSessionEndOperation;
import com.onesignal.user.internal.operations.TrackSessionStartOperation;
import com.onesignal.user.internal.operations.TransferSubscriptionOperation;
import com.onesignal.user.internal.operations.UpdateSubscriptionOperation;
import com.onesignal.user.internal.operations.impl.executors.IdentityOperationExecutor;
import com.onesignal.user.internal.operations.impl.executors.LoginUserFromSubscriptionOperationExecutor;
import com.onesignal.user.internal.operations.impl.executors.LoginUserOperationExecutor;
import com.onesignal.user.internal.operations.impl.executors.RefreshUserOperationExecutor;
import com.onesignal.user.internal.operations.impl.executors.SubscriptionOperationExecutor;
import com.onesignal.user.internal.operations.impl.executors.UpdateUserOperationExecutor;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: OperationModelStore.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcom/onesignal/core/internal/operations/impl/OperationModelStore;", "Lcom/onesignal/common/modeling/ModelStore;", "Lcom/onesignal/core/internal/operations/Operation;", "prefs", "Lcom/onesignal/core/internal/preferences/IPreferencesService;", "(Lcom/onesignal/core/internal/preferences/IPreferencesService;)V", "create", "jsonObject", "Lorg/json/JSONObject;", "isValidOperation", "", "loadOperations", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OperationModelStore extends ModelStore<Operation> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OperationModelStore(IPreferencesService prefs) {
        super("operations", prefs);
        Intrinsics.checkNotNullParameter(prefs, "prefs");
    }

    public final void loadOperations() {
        load();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.onesignal.common.modeling.IModelStore
    public Operation create(JSONObject jsonObject) throws Exception {
        DeleteAliasOperation operation;
        if (jsonObject == null) {
            Logging.error$default("null jsonObject sent to OperationModelStore.create", null, 2, null);
            return null;
        }
        if (!isValidOperation(jsonObject)) {
            return null;
        }
        String operationName = jsonObject.getString("name");
        if (operationName != null) {
            switch (operationName.hashCode()) {
                case -1865677906:
                    if (operationName.equals(IdentityOperationExecutor.DELETE_ALIAS)) {
                        operation = new DeleteAliasOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case -1793763409:
                    if (operationName.equals(LoginUserOperationExecutor.LOGIN_USER)) {
                        operation = new LoginUserOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case -1606689981:
                    if (operationName.equals(UpdateUserOperationExecutor.TRACK_PURCHASE)) {
                        operation = new TrackPurchaseOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case -1188793632:
                    if (operationName.equals(UpdateUserOperationExecutor.SET_PROPERTY)) {
                        operation = new SetPropertyOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case -1080179201:
                    if (operationName.equals(SubscriptionOperationExecutor.DELETE_SUBSCRIPTION)) {
                        operation = new DeleteSubscriptionOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case -828599391:
                    if (operationName.equals(SubscriptionOperationExecutor.UPDATE_SUBSCRIPTION)) {
                        operation = new UpdateSubscriptionOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case -516221659:
                    if (operationName.equals(IdentityOperationExecutor.SET_ALIAS)) {
                        operation = new SetAliasOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case -92337283:
                    if (operationName.equals(RefreshUserOperationExecutor.REFRESH_USER)) {
                        operation = new RefreshUserOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case 532599746:
                    if (operationName.equals(LoginUserFromSubscriptionOperationExecutor.LOGIN_USER_FROM_SUBSCRIPTION_USER)) {
                        operation = new LoginUserFromSubscriptionOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case 846157390:
                    if (operationName.equals(SubscriptionOperationExecutor.CREATE_SUBSCRIPTION)) {
                        operation = new CreateSubscriptionOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case 1707031487:
                    if (operationName.equals(SubscriptionOperationExecutor.TRANSFER_SUBSCRIPTION)) {
                        operation = new TransferSubscriptionOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case 1763437688:
                    if (operationName.equals(UpdateUserOperationExecutor.DELETE_TAG)) {
                        operation = new DeleteTagOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case 1852485538:
                    if (operationName.equals(UpdateUserOperationExecutor.TRACK_SESSION_END)) {
                        operation = new TrackSessionEndOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case 1983836079:
                    if (operationName.equals(UpdateUserOperationExecutor.SET_TAG)) {
                        operation = new SetTagOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
                case 2135250281:
                    if (operationName.equals(UpdateUserOperationExecutor.TRACK_SESSION_START)) {
                        operation = new TrackSessionStartOperation();
                        operation.initializeFromJson(jsonObject);
                        return operation;
                    }
                    break;
            }
        }
        throw new Exception("Unrecognized operation: " + operationName);
    }

    private final boolean isValidOperation(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.has("name")) {
            Logging.error$default("jsonObject must have 'name' attribute", null, 2, null);
            return false;
        }
        String operationName = jsonObject.getString("name");
        Set excluded = SetsKt.setOf(new String[]{LoginUserOperationExecutor.LOGIN_USER, LoginUserFromSubscriptionOperationExecutor.LOGIN_USER_FROM_SUBSCRIPTION_USER});
        if (jsonObject.has("onesignalId") || excluded.contains(operationName)) {
            return true;
        }
        Logging.error$default(operationName + " jsonObject must have 'onesignalId' attribute", null, 2, null);
        return false;
    }
}
