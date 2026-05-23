package com.onesignal.inAppMessages.internal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.onesignal.common.AndroidUtils;
import com.onesignal.common.IDManager;
import com.onesignal.common.JSONUtils;
import com.onesignal.common.consistency.models.IConsistencyManager;
import com.onesignal.common.events.EventProducer;
import com.onesignal.common.exceptions.BackendException;
import com.onesignal.common.modeling.IModelStore;
import com.onesignal.common.modeling.ISingletonModelStoreChangeHandler;
import com.onesignal.common.modeling.ModelChangedArgs;
import com.onesignal.common.threading.ThreadUtilsKt;
import com.onesignal.core.internal.application.IApplicationLifecycleHandler;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.config.ConfigModel;
import com.onesignal.core.internal.config.ConfigModelStore;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.core.internal.language.ILanguageContext;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.inAppMessages.BuildConfig;
import com.onesignal.inAppMessages.IInAppMessageClickListener;
import com.onesignal.inAppMessages.IInAppMessageLifecycleListener;
import com.onesignal.inAppMessages.IInAppMessagesManager;
import com.onesignal.inAppMessages.InAppMessageActionUrlType;
import com.onesignal.inAppMessages.R;
import com.onesignal.inAppMessages.internal.backend.IInAppBackendService;
import com.onesignal.inAppMessages.internal.common.InAppHelper;
import com.onesignal.inAppMessages.internal.common.OneSignalChromeTab;
import com.onesignal.inAppMessages.internal.display.IInAppDisplayer;
import com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleEventHandler;
import com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleService;
import com.onesignal.inAppMessages.internal.preferences.IInAppPreferencesController;
import com.onesignal.inAppMessages.internal.prompt.impl.InAppMessagePrompt;
import com.onesignal.inAppMessages.internal.repositories.IInAppRepository;
import com.onesignal.inAppMessages.internal.state.InAppStateService;
import com.onesignal.inAppMessages.internal.triggers.ITriggerController;
import com.onesignal.inAppMessages.internal.triggers.ITriggerHandler;
import com.onesignal.inAppMessages.internal.triggers.TriggerModel;
import com.onesignal.inAppMessages.internal.triggers.TriggerModelStore;
import com.onesignal.session.internal.influence.IInfluenceManager;
import com.onesignal.session.internal.outcomes.IOutcomeEventsController;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import com.onesignal.session.internal.session.ISessionLifecycleHandler;
import com.onesignal.session.internal.session.ISessionService;
import com.onesignal.user.IUserManager;
import com.onesignal.user.internal.backend.IdentityConstants;
import com.onesignal.user.internal.identity.IdentityModel;
import com.onesignal.user.internal.identity.IdentityModelStore;
import com.onesignal.user.internal.subscriptions.ISubscriptionChangedHandler;
import com.onesignal.user.internal.subscriptions.ISubscriptionManager;
import com.onesignal.user.subscriptions.IPushSubscription;
import com.onesignal.user.subscriptions.ISubscription;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: InAppMessagesManager.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000·\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016*\u00018\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u00020\u00050\u00042\u00020\u00062\u00020\u00072\u00020\b2\u00020\tB\u009d\u0001\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\u0006\u0010 \u001a\u00020!\u0012\u0006\u0010\"\u001a\u00020#\u0012\u0006\u0010$\u001a\u00020%\u0012\u0006\u0010&\u001a\u00020'\u0012\u0006\u0010(\u001a\u00020)\u0012\u0006\u0010*\u001a\u00020+\u0012\u0006\u0010,\u001a\u00020-\u0012\u0006\u0010.\u001a\u00020/¢\u0006\u0002\u00100J\u0010\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020BH\u0016J\u0010\u0010T\u001a\u00020R2\u0006\u0010S\u001a\u00020@H\u0016J\u0018\u0010U\u001a\u00020R2\u0006\u0010V\u001a\u0002032\u0006\u0010H\u001a\u000203H\u0016J\u001c\u0010W\u001a\u00020R2\u0012\u0010X\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u0002030YH\u0016J\u0011\u0010Z\u001a\u00020RH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010[J'\u0010\\\u001a\u00020R2\u0006\u0010]\u001a\u00020E2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020`0_H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010aJ\b\u0010b\u001a\u00020RH\u0016J\u0011\u0010c\u001a\u00020RH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010[J\u0019\u0010d\u001a\u00020R2\u0006\u0010e\u001a\u00020fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010gJ\b\u0010h\u001a\u00020RH\u0002J\u0010\u0010i\u001a\u00020R2\u0006\u0010j\u001a\u00020kH\u0002J'\u0010l\u001a\u00020R2\u0006\u0010m\u001a\u0002032\f\u0010n\u001a\b\u0012\u0004\u0012\u00020o0_H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010pJ!\u0010q\u001a\u00020R2\u0006\u0010]\u001a\u00020E2\u0006\u0010j\u001a\u00020kH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010rJ!\u0010s\u001a\u00020R2\u0006\u0010]\u001a\u00020E2\u0006\u0010j\u001a\u00020kH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010rJ!\u0010t\u001a\u00020R2\u0006\u0010]\u001a\u00020E2\u0006\u0010u\u001a\u00020vH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010wJ\u0010\u0010x\u001a\u00020R2\u0006\u0010j\u001a\u00020kH\u0002J\u0010\u0010y\u001a\u00020I2\u0006\u0010]\u001a\u00020EH\u0002J\u0010\u0010z\u001a\u00020R2\u0006\u0010j\u001a\u00020kH\u0002J\u001e\u0010{\u001a\u00020R2\f\u0010|\u001a\b\u0012\u0004\u0012\u0002030}2\u0006\u0010~\u001a\u00020IH\u0002J%\u0010\u007f\u001a\u00020R2\u0006\u0010]\u001a\u00020E2\t\b\u0002\u0010\u0080\u0001\u001a\u00020IH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0081\u0001J\u0012\u0010\u0082\u0001\u001a\u00020R2\u0007\u0010\u0083\u0001\u001a\u00020IH\u0016J\u0019\u0010\u0084\u0001\u001a\u00020R2\u0006\u0010]\u001a\u00020E2\u0006\u0010j\u001a\u00020kH\u0016J\u0019\u0010\u0085\u0001\u001a\u00020R2\u0006\u0010]\u001a\u00020E2\u0006\u0010j\u001a\u00020kH\u0016J\u0019\u0010\u0086\u0001\u001a\u00020R2\u0006\u0010]\u001a\u00020E2\u0006\u0010u\u001a\u00020vH\u0016J\u0011\u0010\u0087\u0001\u001a\u00020R2\u0006\u0010]\u001a\u00020EH\u0016J\u0011\u0010\u0088\u0001\u001a\u00020R2\u0006\u0010]\u001a\u00020EH\u0016J\u0011\u0010\u0089\u0001\u001a\u00020R2\u0006\u0010]\u001a\u00020EH\u0016J\u0011\u0010\u008a\u0001\u001a\u00020R2\u0006\u0010]\u001a\u00020EH\u0016J\u001b\u0010\u008b\u0001\u001a\u00020R2\u0007\u0010\u008c\u0001\u001a\u00020\u00052\u0007\u0010\u008d\u0001\u001a\u000203H\u0016J\u001c\u0010\u008e\u0001\u001a\u00020R2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u008d\u0001\u001a\u000203H\u0016J\t\u0010\u0091\u0001\u001a\u00020RH\u0016J\u0012\u0010\u0092\u0001\u001a\u00020R2\u0007\u0010\u0093\u0001\u001a\u00020<H\u0016J\t\u0010\u0094\u0001\u001a\u00020RH\u0016J\u0013\u0010\u0095\u0001\u001a\u00020R2\b\u0010\u0096\u0001\u001a\u00030\u0097\u0001H\u0016J\u001d\u0010\u0098\u0001\u001a\u00020R2\b\u0010\u0096\u0001\u001a\u00030\u0097\u00012\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001H\u0016J\u0013\u0010\u0099\u0001\u001a\u00020R2\b\u0010\u0096\u0001\u001a\u00030\u0097\u0001H\u0016J\u0012\u0010\u009a\u0001\u001a\u00020R2\u0007\u0010\u009b\u0001\u001a\u000203H\u0016J\u0012\u0010\u009c\u0001\u001a\u00020R2\u0007\u0010\u009d\u0001\u001a\u000203H\u0016J\u0012\u0010\u009e\u0001\u001a\u00020R2\u0007\u0010\u009d\u0001\u001a\u000203H\u0016J\t\u0010\u009f\u0001\u001a\u00020RH\u0016J\u001b\u0010 \u0001\u001a\u00020R2\u0006\u0010]\u001a\u00020EH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¡\u0001J\u001b\u0010¢\u0001\u001a\u00020R2\u0006\u0010]\u001a\u00020EH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¡\u0001J\u0011\u0010£\u0001\u001a\u00020R2\u0006\u0010S\u001a\u00020BH\u0016J\u0011\u0010¤\u0001\u001a\u00020R2\u0006\u0010S\u001a\u00020@H\u0016J\u0011\u0010¥\u0001\u001a\u00020R2\u0006\u0010V\u001a\u000203H\u0016J\u0018\u0010¦\u0001\u001a\u00020R2\r\u0010§\u0001\u001a\b\u0012\u0004\u0012\u0002030}H\u0016J\u0011\u0010¨\u0001\u001a\u00020R2\u0006\u0010]\u001a\u00020EH\u0002J \u0010©\u0001\u001a\u00020R2\u0007\u0010ª\u0001\u001a\u00020E2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020`0_H\u0002J)\u0010«\u0001\u001a\u00020R2\u0007\u0010ª\u0001\u001a\u00020E2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020`0_H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010aJ\t\u0010¬\u0001\u001a\u00020RH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020302X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020302X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0004\n\u0002\u00109R\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020302X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010;\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0004\n\u0002\u0010=R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020@0?X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010A\u001a\b\u0012\u0004\u0012\u00020B0?X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010C\u001a\b\u0012\u0004\u0012\u00020E0DX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010G\u001a\b\u0012\u0004\u0012\u00020E0DX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010J\u001a\u00020I2\u0006\u0010H\u001a\u00020I8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u0014\u0010O\u001a\b\u0012\u0004\u0012\u00020E0DX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010P\u001a\b\u0012\u0004\u0012\u00020302X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u00ad\u0001"}, d2 = {"Lcom/onesignal/inAppMessages/internal/InAppMessagesManager;", "Lcom/onesignal/inAppMessages/IInAppMessagesManager;", "Lcom/onesignal/core/internal/startup/IStartableService;", "Lcom/onesignal/user/internal/subscriptions/ISubscriptionChangedHandler;", "Lcom/onesignal/common/modeling/ISingletonModelStoreChangeHandler;", "Lcom/onesignal/core/internal/config/ConfigModel;", "Lcom/onesignal/inAppMessages/internal/lifecycle/IInAppLifecycleEventHandler;", "Lcom/onesignal/inAppMessages/internal/triggers/ITriggerHandler;", "Lcom/onesignal/session/internal/session/ISessionLifecycleHandler;", "Lcom/onesignal/core/internal/application/IApplicationLifecycleHandler;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_sessionService", "Lcom/onesignal/session/internal/session/ISessionService;", "_influenceManager", "Lcom/onesignal/session/internal/influence/IInfluenceManager;", "_configModelStore", "Lcom/onesignal/core/internal/config/ConfigModelStore;", "_userManager", "Lcom/onesignal/user/IUserManager;", "_identityModelStore", "Lcom/onesignal/user/internal/identity/IdentityModelStore;", "_subscriptionManager", "Lcom/onesignal/user/internal/subscriptions/ISubscriptionManager;", "_outcomeEventsController", "Lcom/onesignal/session/internal/outcomes/IOutcomeEventsController;", "_state", "Lcom/onesignal/inAppMessages/internal/state/InAppStateService;", "_prefs", "Lcom/onesignal/inAppMessages/internal/preferences/IInAppPreferencesController;", "_repository", "Lcom/onesignal/inAppMessages/internal/repositories/IInAppRepository;", "_backend", "Lcom/onesignal/inAppMessages/internal/backend/IInAppBackendService;", "_triggerController", "Lcom/onesignal/inAppMessages/internal/triggers/ITriggerController;", "_triggerModelStore", "Lcom/onesignal/inAppMessages/internal/triggers/TriggerModelStore;", "_displayer", "Lcom/onesignal/inAppMessages/internal/display/IInAppDisplayer;", "_lifecycle", "Lcom/onesignal/inAppMessages/internal/lifecycle/IInAppLifecycleService;", "_languageContext", "Lcom/onesignal/core/internal/language/ILanguageContext;", "_time", "Lcom/onesignal/core/internal/time/ITime;", "_consistencyManager", "Lcom/onesignal/common/consistency/models/IConsistencyManager;", "(Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/session/internal/session/ISessionService;Lcom/onesignal/session/internal/influence/IInfluenceManager;Lcom/onesignal/core/internal/config/ConfigModelStore;Lcom/onesignal/user/IUserManager;Lcom/onesignal/user/internal/identity/IdentityModelStore;Lcom/onesignal/user/internal/subscriptions/ISubscriptionManager;Lcom/onesignal/session/internal/outcomes/IOutcomeEventsController;Lcom/onesignal/inAppMessages/internal/state/InAppStateService;Lcom/onesignal/inAppMessages/internal/preferences/IInAppPreferencesController;Lcom/onesignal/inAppMessages/internal/repositories/IInAppRepository;Lcom/onesignal/inAppMessages/internal/backend/IInAppBackendService;Lcom/onesignal/inAppMessages/internal/triggers/ITriggerController;Lcom/onesignal/inAppMessages/internal/triggers/TriggerModelStore;Lcom/onesignal/inAppMessages/internal/display/IInAppDisplayer;Lcom/onesignal/inAppMessages/internal/lifecycle/IInAppLifecycleService;Lcom/onesignal/core/internal/language/ILanguageContext;Lcom/onesignal/core/internal/time/ITime;Lcom/onesignal/common/consistency/models/IConsistencyManager;)V", "clickedClickIds", "", "", "dismissedMessages", "fetchIAMMutex", "Lkotlinx/coroutines/sync/Mutex;", "identityModelChangeHandler", "com/onesignal/inAppMessages/internal/InAppMessagesManager$identityModelChangeHandler$1", "Lcom/onesignal/inAppMessages/internal/InAppMessagesManager$identityModelChangeHandler$1;", "impressionedMessages", "lastTimeFetchedIAMs", "", "Ljava/lang/Long;", "lifecycleCallback", "Lcom/onesignal/common/events/EventProducer;", "Lcom/onesignal/inAppMessages/IInAppMessageLifecycleListener;", "messageClickCallback", "Lcom/onesignal/inAppMessages/IInAppMessageClickListener;", "messageDisplayQueue", "", "Lcom/onesignal/inAppMessages/internal/InAppMessage;", "messageDisplayQueueMutex", "messages", "value", "", "paused", "getPaused", "()Z", "setPaused", "(Z)V", "redisplayedInAppMessages", "viewedPageIds", "addClickListener", "", "listener", "addLifecycleListener", "addTrigger", "key", "addTriggers", "triggers", "", "attemptToShowInAppMessage", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "beginProcessingPrompts", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "prompts", "", "Lcom/onesignal/inAppMessages/internal/prompt/impl/InAppMessagePrompt;", "(Lcom/onesignal/inAppMessages/internal/InAppMessage;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearTriggers", "evaluateInAppMessages", "fetchMessages", "rywData", "Lcom/onesignal/common/consistency/RywData;", "(Lcom/onesignal/common/consistency/RywData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchMessagesWhenConditionIsMet", "fireClickAction", "action", "Lcom/onesignal/inAppMessages/internal/InAppMessageClickResult;", "fireOutcomesForClick", "messageId", "outcomes", "Lcom/onesignal/inAppMessages/internal/InAppMessageOutcome;", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firePublicClickHandler", "(Lcom/onesignal/inAppMessages/internal/InAppMessage;Lcom/onesignal/inAppMessages/internal/InAppMessageClickResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fireRESTCallForClick", "fireRESTCallForPageChange", "page", "Lcom/onesignal/inAppMessages/internal/InAppMessagePage;", "(Lcom/onesignal/inAppMessages/internal/InAppMessage;Lcom/onesignal/inAppMessages/internal/InAppMessagePage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fireTagCallForClick", "hasMessageTriggerChanged", "logInAppMessagePreviewActions", "makeRedisplayMessagesAvailableWithTriggers", "newTriggersKeys", "", "isNewTriggerAdded", "messageWasDismissed", "failed", "(Lcom/onesignal/inAppMessages/internal/InAppMessage;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onFocus", "firedOnSubscribe", "onMessageActionOccurredOnMessage", "onMessageActionOccurredOnPreview", "onMessagePageChanged", "onMessageWasDismissed", "onMessageWasDisplayed", "onMessageWillDismiss", "onMessageWillDisplay", "onModelReplaced", "model", "tag", "onModelUpdated", "args", "Lcom/onesignal/common/modeling/ModelChangedArgs;", "onSessionActive", "onSessionEnded", "duration", "onSessionStarted", "onSubscriptionAdded", "subscription", "Lcom/onesignal/user/subscriptions/ISubscription;", "onSubscriptionChanged", "onSubscriptionRemoved", "onTriggerChanged", "newTriggerKey", "onTriggerCompleted", "triggerId", "onTriggerConditionChanged", "onUnfocused", "persistInAppMessage", "(Lcom/onesignal/inAppMessages/internal/InAppMessage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queueMessageForDisplay", "removeClickListener", "removeLifecycleListener", "removeTrigger", "removeTriggers", "keys", "setDataForRedisplay", "showAlertDialogMessage", "inAppMessage", "showMultiplePrompts", "start", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InAppMessagesManager implements IInAppMessagesManager, IStartableService, ISubscriptionChangedHandler, ISingletonModelStoreChangeHandler<ConfigModel>, IInAppLifecycleEventHandler, ITriggerHandler, ISessionLifecycleHandler, IApplicationLifecycleHandler {
    private final IApplicationService _applicationService;
    private final IInAppBackendService _backend;
    private final ConfigModelStore _configModelStore;
    private final IConsistencyManager _consistencyManager;
    private final IInAppDisplayer _displayer;
    private final IdentityModelStore _identityModelStore;
    private final IInfluenceManager _influenceManager;
    private final ILanguageContext _languageContext;
    private final IInAppLifecycleService _lifecycle;
    private final IOutcomeEventsController _outcomeEventsController;
    private final IInAppPreferencesController _prefs;
    private final IInAppRepository _repository;
    private final ISessionService _sessionService;
    private final InAppStateService _state;
    private final ISubscriptionManager _subscriptionManager;
    private final ITime _time;
    private final ITriggerController _triggerController;
    private final TriggerModelStore _triggerModelStore;
    private final IUserManager _userManager;
    private final Set<String> clickedClickIds;
    private final Set<String> dismissedMessages;
    private final Mutex fetchIAMMutex;
    private final InAppMessagesManager$identityModelChangeHandler$1 identityModelChangeHandler;
    private final Set<String> impressionedMessages;
    private Long lastTimeFetchedIAMs;
    private final EventProducer<IInAppMessageLifecycleListener> lifecycleCallback;
    private final EventProducer<IInAppMessageClickListener> messageClickCallback;
    private final List<InAppMessage> messageDisplayQueue;
    private final Mutex messageDisplayQueueMutex;
    private List<InAppMessage> messages;
    private final List<InAppMessage> redisplayedInAppMessages;
    private final Set<String> viewedPageIds;

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$attemptToShowInAppMessage$1, reason: invalid class name */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager", f = "InAppMessagesManager.kt", i = {0, 1, 1, 1, 2, 2}, l = {412, 968, 442, 449, 453}, m = "attemptToShowInAppMessage", n = {"this", "this", "messageToDisplay", "$this$withLock_u24default$iv", "this", "messageToDisplay"}, s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessagesManager.this.attemptToShowInAppMessage((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$evaluateInAppMessages$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager", f = "InAppMessagesManager.kt", i = {0}, l = {335}, m = "evaluateInAppMessages", n = {"this"}, s = {"L$0"})
    static final class C00291 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00291(Continuation<? super C00291> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessagesManager.this.evaluateInAppMessages((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$fetchMessages$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager", f = "InAppMessagesManager.kt", i = {0, 0, 0, 0, 0, 1}, l = {968, 308, 312}, m = "fetchMessages", n = {"this", "rywData", "appId", "subscriptionId", "$this$withLock_u24default$iv", "this"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0"})
    static final class C00301 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C00301(Continuation<? super C00301> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessagesManager.this.fetchMessages(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$fireOutcomesForClick$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager", f = "InAppMessagesManager.kt", i = {0, 1, 2}, l = {773, 775, 777}, m = "fireOutcomesForClick", n = {"this", "this", "this"}, s = {"L$0", "L$0", "L$0"})
    static final class C00321 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00321(Continuation<? super C00321> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessagesManager.this.fireOutcomesForClick(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$fireRESTCallForClick$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager", f = "InAppMessagesManager.kt", i = {0, 0, 0}, l = {924}, m = "fireRESTCallForClick", n = {"this", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "clickId"}, s = {"L$0", "L$1", "L$2"})
    static final class C00331 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00331(Continuation<? super C00331> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessagesManager.this.fireRESTCallForClick(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$fireRESTCallForPageChange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager", f = "InAppMessagesManager.kt", i = {0, 0}, l = {887}, m = "fireRESTCallForPageChange", n = {"this", "messagePrefixedPageId"}, s = {"L$0", "L$1"})
    static final class C00341 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00341(Continuation<? super C00341> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessagesManager.this.fireRESTCallForPageChange(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$messageWasDismissed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager", f = "InAppMessagesManager.kt", i = {0, 0}, l = {475, 501, 504}, m = "messageWasDismissed", n = {"this", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE}, s = {"L$0", "L$1"})
    static final class C00351 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00351(Continuation<? super C00351> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessagesManager.this.messageWasDismissed(null, false, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$persistInAppMessage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager", f = "InAppMessagesManager.kt", i = {0, 0}, l = {542}, m = "persistInAppMessage", n = {"this", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE}, s = {"L$0", "L$1"})
    static final class C00471 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00471(Continuation<? super C00471> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessagesManager.this.persistInAppMessage(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$queueMessageForDisplay$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager", f = "InAppMessagesManager.kt", i = {0, 0, 0}, l = {968, 407}, m = "queueMessageForDisplay", n = {"this", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2"})
    static final class C00481 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00481(Continuation<? super C00481> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessagesManager.this.queueMessageForDisplay(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$showMultiplePrompts$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager", f = "InAppMessagesManager.kt", i = {0, 0, 0}, l = {808, 822}, m = "showMultiplePrompts", n = {"this", "inAppMessage", "prompts"}, s = {"L$0", "L$1", "L$2"})
    static final class C00491 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C00491(Continuation<? super C00491> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessagesManager.this.showMultiplePrompts(null, null, (Continuation) this);
        }
    }

    /* JADX WARN: Type inference failed for: r1v21, types: [com.onesignal.inAppMessages.internal.InAppMessagesManager$identityModelChangeHandler$1] */
    public InAppMessagesManager(IApplicationService _applicationService, ISessionService _sessionService, IInfluenceManager _influenceManager, ConfigModelStore _configModelStore, IUserManager _userManager, IdentityModelStore _identityModelStore, ISubscriptionManager _subscriptionManager, IOutcomeEventsController _outcomeEventsController, InAppStateService _state, IInAppPreferencesController _prefs, IInAppRepository _repository, IInAppBackendService _backend, ITriggerController _triggerController, TriggerModelStore _triggerModelStore, IInAppDisplayer _displayer, IInAppLifecycleService _lifecycle, ILanguageContext _languageContext, ITime _time, IConsistencyManager _consistencyManager) {
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_sessionService, "_sessionService");
        Intrinsics.checkNotNullParameter(_influenceManager, "_influenceManager");
        Intrinsics.checkNotNullParameter(_configModelStore, "_configModelStore");
        Intrinsics.checkNotNullParameter(_userManager, "_userManager");
        Intrinsics.checkNotNullParameter(_identityModelStore, "_identityModelStore");
        Intrinsics.checkNotNullParameter(_subscriptionManager, "_subscriptionManager");
        Intrinsics.checkNotNullParameter(_outcomeEventsController, "_outcomeEventsController");
        Intrinsics.checkNotNullParameter(_state, "_state");
        Intrinsics.checkNotNullParameter(_prefs, "_prefs");
        Intrinsics.checkNotNullParameter(_repository, "_repository");
        Intrinsics.checkNotNullParameter(_backend, "_backend");
        Intrinsics.checkNotNullParameter(_triggerController, "_triggerController");
        Intrinsics.checkNotNullParameter(_triggerModelStore, "_triggerModelStore");
        Intrinsics.checkNotNullParameter(_displayer, "_displayer");
        Intrinsics.checkNotNullParameter(_lifecycle, "_lifecycle");
        Intrinsics.checkNotNullParameter(_languageContext, "_languageContext");
        Intrinsics.checkNotNullParameter(_time, "_time");
        Intrinsics.checkNotNullParameter(_consistencyManager, "_consistencyManager");
        this._applicationService = _applicationService;
        this._sessionService = _sessionService;
        this._influenceManager = _influenceManager;
        this._configModelStore = _configModelStore;
        this._userManager = _userManager;
        this._identityModelStore = _identityModelStore;
        this._subscriptionManager = _subscriptionManager;
        this._outcomeEventsController = _outcomeEventsController;
        this._state = _state;
        this._prefs = _prefs;
        this._repository = _repository;
        this._backend = _backend;
        this._triggerController = _triggerController;
        this._triggerModelStore = _triggerModelStore;
        this._displayer = _displayer;
        this._lifecycle = _lifecycle;
        this._languageContext = _languageContext;
        this._time = _time;
        this._consistencyManager = _consistencyManager;
        this.lifecycleCallback = new EventProducer<>();
        this.messageClickCallback = new EventProducer<>();
        this.messages = new ArrayList();
        this.dismissedMessages = new LinkedHashSet();
        this.impressionedMessages = new LinkedHashSet();
        this.viewedPageIds = new LinkedHashSet();
        this.clickedClickIds = new LinkedHashSet();
        this.messageDisplayQueue = new ArrayList();
        this.messageDisplayQueueMutex = MutexKt.Mutex$default(false, 1, (Object) null);
        this.redisplayedInAppMessages = new ArrayList();
        this.fetchIAMMutex = MutexKt.Mutex$default(false, 1, (Object) null);
        this.identityModelChangeHandler = new ISingletonModelStoreChangeHandler<IdentityModel>() { // from class: com.onesignal.inAppMessages.internal.InAppMessagesManager$identityModelChangeHandler$1
            @Override // com.onesignal.common.modeling.ISingletonModelStoreChangeHandler
            public void onModelReplaced(IdentityModel model, String tag) {
                Intrinsics.checkNotNullParameter(model, "model");
                Intrinsics.checkNotNullParameter(tag, "tag");
            }

            @Override // com.onesignal.common.modeling.ISingletonModelStoreChangeHandler
            public void onModelUpdated(ModelChangedArgs args, String tag) {
                Intrinsics.checkNotNullParameter(args, "args");
                Intrinsics.checkNotNullParameter(tag, "tag");
                if (Intrinsics.areEqual(args.getProperty(), IdentityConstants.ONESIGNAL_ID)) {
                    Object oldValue = args.getOldValue();
                    Intrinsics.checkNotNull(oldValue, "null cannot be cast to non-null type kotlin.String");
                    String oldOneSignalId = (String) oldValue;
                    Object newValue = args.getNewValue();
                    Intrinsics.checkNotNull(newValue, "null cannot be cast to non-null type kotlin.String");
                    String newOneSignalId = (String) newValue;
                    if (IDManager.INSTANCE.isLocalId(oldOneSignalId) && !IDManager.INSTANCE.isLocalId(newOneSignalId)) {
                        ThreadUtilsKt.suspendifyOnThread$default(0, new InAppMessagesManager$identityModelChangeHandler$1$onModelUpdated$1(this.this$0, newOneSignalId, null), 1, null);
                    }
                }
            }
        };
    }

    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    public boolean getPaused() {
        return this._state.getPaused();
    }

    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    public void setPaused(boolean value) {
        Logging.debug$default("InAppMessagesManager.setPaused(value: " + value + ')', null, 2, null);
        this._state.setPaused(value);
        if (value && this._state.getInAppMessageIdShowing() != null) {
            BuildersKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new InAppMessagesManager$paused$1(this, null), 2, (Object) null);
        }
        if (!value) {
            ThreadUtilsKt.suspendifyOnThread$default(0, new InAppMessagesManager$paused$2(this, null), 1, null);
        }
    }

    @Override // com.onesignal.core.internal.startup.IStartableService
    public void start() {
        Set<String> dismissedMessagesId = this._prefs.getDismissedMessagesId();
        if (dismissedMessagesId != null) {
            this.dismissedMessages.addAll(dismissedMessagesId);
        }
        Long tempLastTimeInAppDismissed = this._prefs.getLastTimeInAppDismissed();
        if (tempLastTimeInAppDismissed != null) {
            this._state.setLastTimeInAppDismissed(tempLastTimeInAppDismissed);
        }
        this._subscriptionManager.subscribe(this);
        this._configModelStore.subscribe((ISingletonModelStoreChangeHandler) this);
        this._lifecycle.subscribe(this);
        this._triggerController.subscribe(this);
        this._sessionService.subscribe(this);
        this._applicationService.addApplicationLifecycleHandler(this);
        this._identityModelStore.subscribe((ISingletonModelStoreChangeHandler) this.identityModelChangeHandler);
        ThreadUtilsKt.suspendifyOnThread$default(0, new C00501(null), 1, null);
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$start$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$start$1", f = "InAppMessagesManager.kt", i = {}, l = {190, 193}, m = "invokeSuspend", n = {}, s = {})
    static final class C00501 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        C00501(Continuation<? super C00501> continuation) {
            super(1, continuation);
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return InAppMessagesManager.this.new C00501(continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0053 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x006b A[LOOP:0: B:16:0x0065->B:18:0x006b, LOOP_END] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                switch(r1) {
                    case 0: goto L22;
                    case 1: goto L1d;
                    case 2: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L11:
                r0 = r6
                java.lang.Object r1 = r0.L$0
                java.util.List r1 = (java.util.List) r1
                kotlin.ResultKt.throwOnFailure(r7)
                r2 = r1
                r1 = r0
                r0 = r7
                goto L56
            L1d:
                r1 = r6
                kotlin.ResultKt.throwOnFailure(r7)
                goto L39
            L22:
                kotlin.ResultKt.throwOnFailure(r7)
                r1 = r6
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.this
                com.onesignal.inAppMessages.internal.repositories.IInAppRepository r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$get_repository$p(r2)
                r3 = r1
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r4 = 1
                r1.label = r4
                java.lang.Object r2 = r2.cleanCachedInAppMessages(r3)
                if (r2 != r0) goto L39
                return r0
            L39:
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.this
                java.util.List r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$getRedisplayedInAppMessages$p(r2)
                com.onesignal.inAppMessages.internal.InAppMessagesManager r3 = com.onesignal.inAppMessages.internal.InAppMessagesManager.this
                com.onesignal.inAppMessages.internal.repositories.IInAppRepository r3 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$get_repository$p(r3)
                r4 = r1
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r1.L$0 = r2
                r5 = 2
                r1.label = r5
                java.lang.Object r3 = r3.listInAppMessages(r4)
                if (r3 != r0) goto L54
                return r0
            L54:
                r0 = r7
                r7 = r3
            L56:
                java.util.Collection r7 = (java.util.Collection) r7
                r2.addAll(r7)
                com.onesignal.inAppMessages.internal.InAppMessagesManager r7 = com.onesignal.inAppMessages.internal.InAppMessagesManager.this
                java.util.List r7 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$getRedisplayedInAppMessages$p(r7)
                java.util.Iterator r7 = r7.iterator()
            L65:
                boolean r2 = r7.hasNext()
                if (r2 == 0) goto L76
                java.lang.Object r2 = r7.next()
                com.onesignal.inAppMessages.internal.InAppMessage r2 = (com.onesignal.inAppMessages.internal.InAppMessage) r2
                r3 = 0
                r2.setDisplayedInSession(r3)
                goto L65
            L76:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.C00501.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    /* JADX INFO: renamed from: addLifecycleListener */
    public void mo53addLifecycleListener(IInAppMessageLifecycleListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Logging.debug$default("InAppMessagesManager.addLifecycleListener(listener: " + listener + ')', null, 2, null);
        this.lifecycleCallback.subscribe(listener);
    }

    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    /* JADX INFO: renamed from: removeLifecycleListener */
    public void mo58removeLifecycleListener(IInAppMessageLifecycleListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Logging.debug$default("InAppMessagesManager.removeLifecycleListener(listener: " + listener + ')', null, 2, null);
        this.lifecycleCallback.unsubscribe(listener);
    }

    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    /* JADX INFO: renamed from: addClickListener */
    public void mo52addClickListener(IInAppMessageClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Logging.debug$default("InAppMessagesManager.addClickListener(listener: " + listener + ')', null, 2, null);
        this.messageClickCallback.subscribe(listener);
    }

    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    /* JADX INFO: renamed from: removeClickListener */
    public void mo57removeClickListener(IInAppMessageClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Logging.debug$default("InAppMessagesManager.removeClickListener(listener: " + listener + ')', null, 2, null);
        this.messageClickCallback.unsubscribe(listener);
    }

    @Override // com.onesignal.common.modeling.ISingletonModelStoreChangeHandler
    public void onModelUpdated(ModelChangedArgs args, String tag) {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (!Intrinsics.areEqual(args.getProperty(), "appId")) {
            return;
        }
        fetchMessagesWhenConditionIsMet();
    }

    @Override // com.onesignal.common.modeling.ISingletonModelStoreChangeHandler
    public void onModelReplaced(ConfigModel model, String tag) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(tag, "tag");
        fetchMessagesWhenConditionIsMet();
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionChangedHandler
    public void onSubscriptionAdded(ISubscription subscription) {
        Intrinsics.checkNotNullParameter(subscription, "subscription");
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionChangedHandler
    public void onSubscriptionRemoved(ISubscription subscription) {
        Intrinsics.checkNotNullParameter(subscription, "subscription");
    }

    @Override // com.onesignal.user.internal.subscriptions.ISubscriptionChangedHandler
    public void onSubscriptionChanged(ISubscription subscription, ModelChangedArgs args) {
        Intrinsics.checkNotNullParameter(subscription, "subscription");
        Intrinsics.checkNotNullParameter(args, "args");
        if (!(subscription instanceof IPushSubscription) || !Intrinsics.areEqual(args.getPath(), OutcomeConstants.OUTCOME_ID)) {
            return;
        }
        fetchMessagesWhenConditionIsMet();
    }

    @Override // com.onesignal.session.internal.session.ISessionLifecycleHandler
    public void onSessionStarted() {
        for (InAppMessage redisplayInAppMessage : this.redisplayedInAppMessages) {
            redisplayInAppMessage.setDisplayedInSession(false);
        }
        fetchMessagesWhenConditionIsMet();
    }

    @Override // com.onesignal.session.internal.session.ISessionLifecycleHandler
    public void onSessionActive() {
    }

    @Override // com.onesignal.session.internal.session.ISessionLifecycleHandler
    public void onSessionEnded(long duration) {
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$fetchMessagesWhenConditionIsMet$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$fetchMessagesWhenConditionIsMet$1", f = "InAppMessagesManager.kt", i = {}, l = {271, 272, 275}, m = "invokeSuspend", n = {}, s = {})
    static final class C00311 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;

        C00311(Continuation<? super C00311> continuation) {
            super(1, continuation);
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return InAppMessagesManager.this.new C00311(continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0060 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0065  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                switch(r1) {
                    case 0: goto L25;
                    case 1: goto L1e;
                    case 2: goto L17;
                    case 3: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L11:
                r0 = r8
                kotlin.ResultKt.throwOnFailure(r9)
                goto L76
            L17:
                r1 = r8
                kotlin.ResultKt.throwOnFailure(r9)
                r2 = r1
                r1 = r9
                goto L61
            L1e:
                r1 = r8
                kotlin.ResultKt.throwOnFailure(r9)
                r2 = r1
                r1 = r9
                goto L51
            L25:
                kotlin.ResultKt.throwOnFailure(r9)
                r1 = r8
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.this
                com.onesignal.user.IUserManager r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$get_userManager$p(r2)
                java.lang.String r2 = r2.getOnesignalId()
                com.onesignal.inAppMessages.internal.InAppMessagesManager r3 = com.onesignal.inAppMessages.internal.InAppMessagesManager.this
                com.onesignal.common.consistency.models.IConsistencyManager r3 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$get_consistencyManager$p(r3)
                com.onesignal.common.consistency.IamFetchReadyCondition r4 = new com.onesignal.common.consistency.IamFetchReadyCondition
                r4.<init>(r2)
                com.onesignal.common.consistency.models.ICondition r4 = (com.onesignal.common.consistency.models.ICondition) r4
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r6 = 1
                r1.label = r6
                java.lang.Object r2 = r3.getRywDataFromAwaitableCondition(r4, r5)
                if (r2 != r0) goto L4d
                return r0
            L4d:
                r7 = r1
                r1 = r9
                r9 = r2
                r2 = r7
            L51:
                kotlinx.coroutines.CompletableDeferred r9 = (kotlinx.coroutines.CompletableDeferred) r9
                r3 = r2
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r4 = 2
                r2.label = r4
                java.lang.Object r9 = r9.await(r3)
                if (r9 != r0) goto L61
                return r0
            L61:
                com.onesignal.common.consistency.RywData r9 = (com.onesignal.common.consistency.RywData) r9
                if (r9 == 0) goto L78
                com.onesignal.inAppMessages.internal.InAppMessagesManager r3 = com.onesignal.inAppMessages.internal.InAppMessagesManager.this
                r4 = r2
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r5 = 3
                r2.label = r5
                java.lang.Object r9 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$fetchMessages(r3, r9, r4)
                if (r9 != r0) goto L74
                return r0
            L74:
                r9 = r1
                r0 = r2
            L76:
                r1 = r9
                r2 = r0
            L78:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.C00311.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final void fetchMessagesWhenConditionIsMet() {
        ThreadUtilsKt.suspendifyOnThread$default(0, new C00311(null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00d7 A[Catch: all -> 0x0143, TryCatch #1 {all -> 0x0143, blocks: (B:36:0x00cd, B:38:0x00d7, B:40:0x00f2), top: B:64:0x00cd }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0125 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object fetchMessages(com.onesignal.common.consistency.RywData r20, kotlin.coroutines.Continuation<? super kotlin.Unit> r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.fetchMessages(com.onesignal.common.consistency.RywData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object evaluateInAppMessages(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.onesignal.inAppMessages.internal.InAppMessagesManager.C00291
            if (r0 == 0) goto L14
            r0 = r11
            com.onesignal.inAppMessages.internal.InAppMessagesManager$evaluateInAppMessages$1 r0 = (com.onesignal.inAppMessages.internal.InAppMessagesManager.C00291) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.InAppMessagesManager$evaluateInAppMessages$1 r0 = new com.onesignal.inAppMessages.internal.InAppMessagesManager$evaluateInAppMessages$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L2c:
            java.lang.Object r2 = r0.L$1
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r0.L$0
            com.onesignal.inAppMessages.internal.InAppMessagesManager r3 = (com.onesignal.inAppMessages.internal.InAppMessagesManager) r3
            kotlin.ResultKt.throwOnFailure(r11)
            goto La5
        L38:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r10
            java.lang.String r3 = "InAppMessagesManager.evaluateInAppMessages()"
            r4 = 2
            r5 = 0
            com.onesignal.debug.internal.logging.Logging.debug$default(r3, r5, r4, r5)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.List r3 = (java.util.List) r3
            java.util.List<com.onesignal.inAppMessages.internal.InAppMessage> r4 = r2.messages
            monitor-enter(r4)
            r5 = 0
            java.util.List<com.onesignal.inAppMessages.internal.InAppMessage> r6 = r2.messages     // Catch: java.lang.Throwable -> La9
            java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Throwable -> La9
        L54:
            boolean r7 = r6.hasNext()     // Catch: java.lang.Throwable -> La9
            if (r7 == 0) goto L81
            java.lang.Object r7 = r6.next()     // Catch: java.lang.Throwable -> La9
            com.onesignal.inAppMessages.internal.InAppMessage r7 = (com.onesignal.inAppMessages.internal.InAppMessage) r7     // Catch: java.lang.Throwable -> La9
            com.onesignal.inAppMessages.internal.triggers.ITriggerController r8 = r2._triggerController     // Catch: java.lang.Throwable -> La9
            boolean r8 = r8.evaluateMessageTriggers(r7)     // Catch: java.lang.Throwable -> La9
            if (r8 == 0) goto L54
            r2.setDataForRedisplay(r7)     // Catch: java.lang.Throwable -> La9
            java.util.Set<java.lang.String> r8 = r2.dismissedMessages     // Catch: java.lang.Throwable -> La9
            java.lang.String r9 = r7.getMessageId()     // Catch: java.lang.Throwable -> La9
            boolean r8 = r8.contains(r9)     // Catch: java.lang.Throwable -> La9
            if (r8 != 0) goto L54
            boolean r8 = r7.isFinished()     // Catch: java.lang.Throwable -> La9
            if (r8 != 0) goto L54
            r3.add(r7)     // Catch: java.lang.Throwable -> La9
            goto L54
        L81:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> La9
            monitor-exit(r4)
            java.util.Iterator r4 = r3.iterator()
            r3 = r2
            r2 = r4
        L8b:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto La6
            java.lang.Object r4 = r2.next()
            com.onesignal.inAppMessages.internal.InAppMessage r4 = (com.onesignal.inAppMessages.internal.InAppMessage) r4
            r0.L$0 = r3
            r0.L$1 = r2
            r5 = 1
            r0.label = r5
            java.lang.Object r4 = r3.queueMessageForDisplay(r4, r0)
            if (r4 != r1) goto La5
            return r1
        La5:
            goto L8b
        La6:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        La9:
            r1 = move-exception
            monitor-exit(r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.evaluateInAppMessages(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void setDataForRedisplay(InAppMessage message) {
        boolean messageDismissed = this.dismissedMessages.contains(message.getMessageId());
        int index = this.redisplayedInAppMessages.indexOf(message);
        if (messageDismissed && index != -1) {
            InAppMessage savedIAM = this.redisplayedInAppMessages.get(index);
            message.getRedisplayStats().setDisplayStats(savedIAM.getRedisplayStats());
            message.setDisplayedInSession(savedIAM.isDisplayedInSession());
            boolean triggerHasChanged = hasMessageTriggerChanged(message);
            Logging.debug$default("InAppMessagesManager.setDataForRedisplay: " + message + " triggerHasChanged: " + triggerHasChanged, null, 2, null);
            if (triggerHasChanged && message.getRedisplayStats().isDelayTimeSatisfied() && message.getRedisplayStats().shouldDisplayAgain()) {
                Logging.debug$default("InAppMessagesManager.setDataForRedisplay message available for redisplay: " + message.getMessageId(), null, 2, null);
                this.dismissedMessages.remove(message.getMessageId());
                this.impressionedMessages.remove(message.getMessageId());
                this.viewedPageIds.clear();
                this._prefs.setViewPageImpressionedIds(this.viewedPageIds);
                message.clearClickIds();
            }
        }
    }

    private final boolean hasMessageTriggerChanged(InAppMessage message) {
        boolean messageHasOnlyDynamicTrigger = this._triggerController.messageHasOnlyDynamicTriggers(message);
        if (messageHasOnlyDynamicTrigger) {
            return true ^ message.isDisplayedInSession();
        }
        boolean shouldMessageDisplayInSession = !message.isDisplayedInSession() && message.getTriggers().isEmpty();
        return message.isTriggerChanged() || shouldMessageDisplayInSession;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object queueMessageForDisplay(com.onesignal.inAppMessages.internal.InAppMessage r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof com.onesignal.inAppMessages.internal.InAppMessagesManager.C00481
            if (r0 == 0) goto L14
            r0 = r13
            com.onesignal.inAppMessages.internal.InAppMessagesManager$queueMessageForDisplay$1 r0 = (com.onesignal.inAppMessages.internal.InAppMessagesManager.C00481) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.InAppMessagesManager$queueMessageForDisplay$1 r0 = new com.onesignal.inAppMessages.internal.InAppMessagesManager$queueMessageForDisplay$1
            r0.<init>(r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L43;
                case 1: goto L31;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L2c:
            kotlin.ResultKt.throwOnFailure(r13)
            goto Lb4
        L31:
            r12 = 0
            r2 = 0
            java.lang.Object r3 = r0.L$2
            kotlinx.coroutines.sync.Mutex r3 = (kotlinx.coroutines.sync.Mutex) r3
            java.lang.Object r4 = r0.L$1
            com.onesignal.inAppMessages.internal.InAppMessage r4 = (com.onesignal.inAppMessages.internal.InAppMessage) r4
            java.lang.Object r5 = r0.L$0
            com.onesignal.inAppMessages.internal.InAppMessagesManager r5 = (com.onesignal.inAppMessages.internal.InAppMessagesManager) r5
            kotlin.ResultKt.throwOnFailure(r13)
            goto L5d
        L43:
            kotlin.ResultKt.throwOnFailure(r13)
            r5 = r11
            r4 = r12
            kotlinx.coroutines.sync.Mutex r3 = r5.messageDisplayQueueMutex
            r2 = 0
            r12 = 0
            r0.L$0 = r5
            r0.L$1 = r4
            r0.L$2 = r3
            r6 = 1
            r0.label = r6
            java.lang.Object r6 = r3.lock(r2, r0)
            if (r6 != r1) goto L5d
            return r1
        L5d:
            r6 = 0
            java.util.List<com.onesignal.inAppMessages.internal.InAppMessage> r7 = r5.messageDisplayQueue     // Catch: java.lang.Throwable -> Lb7
            boolean r7 = r7.contains(r4)     // Catch: java.lang.Throwable -> Lb7
            r8 = 2
            r9 = 0
            if (r7 != 0) goto L9e
            com.onesignal.inAppMessages.internal.state.InAppStateService r7 = r5._state     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r7 = r7.getInAppMessageIdShowing()     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r10 = r4.getMessageId()     // Catch: java.lang.Throwable -> Lb7
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r10)     // Catch: java.lang.Throwable -> Lb7
            if (r7 != 0) goto L9e
            java.util.List<com.onesignal.inAppMessages.internal.InAppMessage> r7 = r5.messageDisplayQueue     // Catch: java.lang.Throwable -> Lb7
            r7.add(r4)     // Catch: java.lang.Throwable -> Lb7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb7
            r7.<init>()     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r10 = "InAppMessagesManager.queueMessageForDisplay: In app message with id: "
            java.lang.StringBuilder r7 = r7.append(r10)     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r10 = r4.getMessageId()     // Catch: java.lang.Throwable -> Lb7
            java.lang.StringBuilder r7 = r7.append(r10)     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r10 = ", added to the queue"
            java.lang.StringBuilder r7 = r7.append(r10)     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> Lb7
            com.onesignal.debug.internal.logging.Logging.debug$default(r7, r9, r8, r9)     // Catch: java.lang.Throwable -> Lb7
        L9e:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lb7
            r3.unlock(r2)
            r0.L$0 = r9
            r0.L$1 = r9
            r0.L$2 = r9
            r0.label = r8
            java.lang.Object r12 = r5.attemptToShowInAppMessage(r0)
            if (r12 != r1) goto Lb4
            return r1
        Lb4:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        Lb7:
            r1 = move-exception
            r3.unlock(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.queueMessageForDisplay(com.onesignal.inAppMessages.internal.InAppMessage, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cc A[Catch: all -> 0x0181, TryCatch #0 {all -> 0x0181, blocks: (B:28:0x00ae, B:30:0x00cc, B:38:0x010b, B:31:0x00d2, B:33:0x00da, B:34:0x00e0, B:36:0x00e8, B:37:0x00ee), top: B:59:0x00ae }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d2 A[Catch: all -> 0x0181, TryCatch #0 {all -> 0x0181, blocks: (B:28:0x00ae, B:30:0x00cc, B:38:0x010b, B:31:0x00d2, B:33:0x00da, B:34:0x00e0, B:36:0x00e8, B:37:0x00ee), top: B:59:0x00ae }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object attemptToShowInAppMessage(kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.attemptToShowInAppMessage(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object messageWasDismissed(final com.onesignal.inAppMessages.internal.InAppMessage r8, boolean r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.messageWasDismissed(com.onesignal.inAppMessages.internal.InAppMessage, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object messageWasDismissed$default(InAppMessagesManager inAppMessagesManager, InAppMessage inAppMessage, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return inAppMessagesManager.messageWasDismissed(inAppMessage, z, continuation);
    }

    private final void makeRedisplayMessagesAvailableWithTriggers(Collection<String> collection, boolean isNewTriggerAdded) {
        synchronized (this.messages) {
            for (InAppMessage message : this.messages) {
                boolean isMessageDisplayed = this.redisplayedInAppMessages.contains(message);
                boolean isTriggerOnMessage = this._triggerController.isTriggerOnMessage(message, collection);
                boolean isOnlyDynamicTriggers = this._triggerController.messageHasOnlyDynamicTriggers(message);
                if (!message.isTriggerChanged() && isMessageDisplayed && (isTriggerOnMessage || (isNewTriggerAdded && isOnlyDynamicTriggers))) {
                    Logging.debug$default("InAppMessagesManager.makeRedisplayMessagesAvailableWithTriggers: Trigger changed for message: " + message, null, 2, null);
                    message.setTriggerChanged(true);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object persistInAppMessage(com.onesignal.inAppMessages.internal.InAppMessage r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.onesignal.inAppMessages.internal.InAppMessagesManager.C00471
            if (r0 == 0) goto L14
            r0 = r9
            com.onesignal.inAppMessages.internal.InAppMessagesManager$persistInAppMessage$1 r0 = (com.onesignal.inAppMessages.internal.InAppMessagesManager.C00471) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.InAppMessagesManager$persistInAppMessage$1 r0 = new com.onesignal.inAppMessages.internal.InAppMessagesManager$persistInAppMessage$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            java.lang.Object r8 = r0.L$1
            com.onesignal.inAppMessages.internal.InAppMessage r8 = (com.onesignal.inAppMessages.internal.InAppMessage) r8
            java.lang.Object r1 = r0.L$0
            com.onesignal.inAppMessages.internal.InAppMessagesManager r1 = (com.onesignal.inAppMessages.internal.InAppMessagesManager) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L6c
        L38:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
            com.onesignal.core.internal.time.ITime r3 = r2._time
            long r3 = r3.getCurrentTimeMillis()
            r5 = 1000(0x3e8, float:1.401E-42)
            long r5 = (long) r5
            long r3 = r3 / r5
            com.onesignal.inAppMessages.internal.InAppMessageRedisplayStats r5 = r8.getRedisplayStats()
            r5.setLastDisplayTime(r3)
            com.onesignal.inAppMessages.internal.InAppMessageRedisplayStats r3 = r8.getRedisplayStats()
            r3.incrementDisplayQuantity()
            r3 = 0
            r8.setTriggerChanged(r3)
            r3 = 1
            r8.setDisplayedInSession(r3)
            com.onesignal.inAppMessages.internal.repositories.IInAppRepository r4 = r2._repository
            r0.L$0 = r2
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r3 = r4.saveInAppMessage(r8, r0)
            if (r3 != r1) goto L6b
            return r1
        L6b:
            r1 = r2
        L6c:
            com.onesignal.inAppMessages.internal.preferences.IInAppPreferencesController r2 = r1._prefs
            com.onesignal.inAppMessages.internal.state.InAppStateService r3 = r1._state
            java.lang.Long r3 = r3.getLastTimeInAppDismissed()
            r2.setLastTimeInAppDismissed(r3)
            java.util.List<com.onesignal.inAppMessages.internal.InAppMessage> r2 = r1.redisplayedInAppMessages
            int r2 = r2.indexOf(r8)
            r3 = -1
            if (r2 == r3) goto L86
            java.util.List<com.onesignal.inAppMessages.internal.InAppMessage> r3 = r1.redisplayedInAppMessages
            r3.set(r2, r8)
            goto L8b
        L86:
            java.util.List<com.onesignal.inAppMessages.internal.InAppMessage> r2 = r1.redisplayedInAppMessages
            r2.add(r8)
        L8b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "InAppMessagesManager.persistInAppMessage: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r8)
            java.lang.String r3 = " with msg array data: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.util.List<com.onesignal.inAppMessages.internal.InAppMessage> r3 = r1.redisplayedInAppMessages
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r3 = 2
            r4 = 0
            com.onesignal.debug.internal.logging.Logging.debug$default(r2, r4, r3, r4)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.persistInAppMessage(com.onesignal.inAppMessages.internal.InAppMessage, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    /* JADX INFO: renamed from: addTriggers */
    public void mo55addTriggers(Map<String, String> map) throws Throwable {
        Intrinsics.checkNotNullParameter(map, "triggers");
        Logging.debug$default("InAppMessagesManager.addTriggers(triggers: " + map + ')', null, 2, null);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            mo54addTrigger(entry.getKey(), entry.getValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    /* JADX INFO: renamed from: addTrigger */
    public void mo54addTrigger(String key, String value) throws Throwable {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Logging.debug$default("InAppMessagesManager.addTrigger(key: " + key + ", value: " + value + ')', null, 2, null);
        TriggerModel triggerModel = (TriggerModel) this._triggerModelStore.get(key);
        if (triggerModel != null) {
            triggerModel.setValue(value);
            return;
        }
        TriggerModel triggerModel2 = new TriggerModel();
        triggerModel2.setId(key);
        triggerModel2.setKey(key);
        triggerModel2.setValue(value);
        IModelStore.DefaultImpls.add$default(this._triggerModelStore, triggerModel2, null, 2, null);
    }

    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    /* JADX INFO: renamed from: removeTriggers */
    public void mo60removeTriggers(Collection<String> collection) {
        Intrinsics.checkNotNullParameter(collection, "keys");
        Logging.debug$default("InAppMessagesManager.removeTriggers(keys: " + collection + ')', null, 2, null);
        Collection<String> $this$forEach$iv = collection;
        for (Object element$iv : $this$forEach$iv) {
            String it = (String) element$iv;
            mo59removeTrigger(it);
        }
    }

    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    /* JADX INFO: renamed from: removeTrigger */
    public void mo59removeTrigger(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Logging.debug$default("InAppMessagesManager.removeTrigger(key: " + key + ')', null, 2, null);
        IModelStore.DefaultImpls.remove$default(this._triggerModelStore, key, null, 2, null);
    }

    @Override // com.onesignal.inAppMessages.IInAppMessagesManager
    /* JADX INFO: renamed from: clearTriggers */
    public void mo56clearTriggers() {
        Logging.debug$default("InAppMessagesManager.clearTriggers()", null, 2, null);
        IModelStore.DefaultImpls.clear$default(this._triggerModelStore, null, 1, null);
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleEventHandler
    public void onMessageWillDisplay(final InAppMessage message) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        if (!this.lifecycleCallback.getHasSubscribers()) {
            Logging.verbose$default("InAppMessagesManager.onMessageWillDisplay: inAppMessageLifecycleHandler is null", null, 2, null);
        } else {
            this.lifecycleCallback.fireOnMain(new Function1<IInAppMessageLifecycleListener, Unit>() { // from class: com.onesignal.inAppMessages.internal.InAppMessagesManager.onMessageWillDisplay.1
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((IInAppMessageLifecycleListener) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(IInAppMessageLifecycleListener it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.onWillDisplay(new InAppMessageLifecycleEvent(message));
                }
            });
        }
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleEventHandler
    public void onMessageWasDisplayed(final InAppMessage message) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        if (this.lifecycleCallback.getHasSubscribers()) {
            this.lifecycleCallback.fireOnMain(new Function1<IInAppMessageLifecycleListener, Unit>() { // from class: com.onesignal.inAppMessages.internal.InAppMessagesManager.onMessageWasDisplayed.1
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((IInAppMessageLifecycleListener) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(IInAppMessageLifecycleListener it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.onDidDisplay(new InAppMessageLifecycleEvent(message));
                }
            });
        } else {
            Logging.verbose$default("InAppMessagesManager.onMessageWasDisplayed: inAppMessageLifecycleHandler is null", null, 2, null);
        }
        if (message.isPreview() || this.impressionedMessages.contains(message.getMessageId())) {
            return;
        }
        this.impressionedMessages.add(message.getMessageId());
        String variantId = InAppHelper.INSTANCE.variantIdForMessage(message, this._languageContext);
        if (variantId == null) {
            return;
        }
        ThreadUtilsKt.suspendifyOnThread$default(0, new C00422(variantId, message, null), 1, null);
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessageWasDisplayed$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessageWasDisplayed$2", f = "InAppMessagesManager.kt", i = {}, l = {630}, m = "invokeSuspend", n = {}, s = {})
    static final class C00422 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ InAppMessage $message;
        final /* synthetic */ String $variantId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00422(String str, InAppMessage inAppMessage, Continuation<? super C00422> continuation) {
            super(1, continuation);
            this.$variantId = str;
            this.$message = inAppMessage;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return InAppMessagesManager.this.new C00422(this.$variantId, this.$message, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v3, types: [com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessageWasDisplayed$2] */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v5 */
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            C00422 c00422 = this.label;
            try {
                switch (c00422) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        C00422 c004222 = this;
                        c004222.label = 1;
                        Object objSendIAMImpression = InAppMessagesManager.this._backend.sendIAMImpression(InAppMessagesManager.this._configModelStore.getModel().getAppId(), InAppMessagesManager.this._subscriptionManager.getSubscriptions().getPush().getId(), c004222.$variantId, c004222.$message.getMessageId(), (Continuation) c004222);
                        c00422 = c004222;
                        if (objSendIAMImpression == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        c00422 = this;
                        ResultKt.throwOnFailure(obj);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                InAppMessagesManager.this._prefs.setImpressionesMessagesId(InAppMessagesManager.this.impressionedMessages);
            } catch (BackendException e) {
                InAppMessagesManager.this.impressionedMessages.remove(c00422.$message.getMessageId());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessageActionOccurredOnPreview$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessageActionOccurredOnPreview$1", f = "InAppMessagesManager.kt", i = {}, l = {652, 653}, m = "invokeSuspend", n = {}, s = {})
    static final class C00381 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ InAppMessageClickResult $action;
        final /* synthetic */ InAppMessage $message;
        int label;
        final /* synthetic */ InAppMessagesManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00381(InAppMessageClickResult inAppMessageClickResult, InAppMessage inAppMessage, InAppMessagesManager inAppMessagesManager, Continuation<? super C00381> continuation) {
            super(1, continuation);
            this.$action = inAppMessageClickResult;
            this.$message = inAppMessage;
            this.this$0 = inAppMessagesManager;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C00381(this.$action, this.$message, this.this$0, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0053 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                switch(r1) {
                    case 0: goto L1b;
                    case 1: goto L16;
                    case 2: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L11:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L55
            L16:
                r1 = r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L3d
            L1b:
                kotlin.ResultKt.throwOnFailure(r8)
                r1 = r7
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r2 = r1.$action
                com.onesignal.inAppMessages.internal.InAppMessage r3 = r1.$message
                boolean r3 = r3.takeActionAsUnique()
                r2.setFirstClick(r3)
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = r1.this$0
                com.onesignal.inAppMessages.internal.InAppMessage r3 = r1.$message
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r4 = r1.$action
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r6 = 1
                r1.label = r6
                java.lang.Object r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$firePublicClickHandler(r2, r3, r4, r5)
                if (r2 != r0) goto L3d
                return r0
            L3d:
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = r1.this$0
                com.onesignal.inAppMessages.internal.InAppMessage r3 = r1.$message
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r4 = r1.$action
                java.util.List r4 = r4.getPrompts()
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r6 = 2
                r1.label = r6
                java.lang.Object r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$beginProcessingPrompts(r2, r3, r4, r5)
                if (r2 != r0) goto L54
                return r0
            L54:
                r0 = r1
            L55:
                com.onesignal.inAppMessages.internal.InAppMessagesManager r1 = r0.this$0
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r2 = r0.$action
                com.onesignal.inAppMessages.internal.InAppMessagesManager.access$fireClickAction(r1, r2)
                com.onesignal.inAppMessages.internal.InAppMessagesManager r1 = r0.this$0
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r2 = r0.$action
                com.onesignal.inAppMessages.internal.InAppMessagesManager.access$logInAppMessagePreviewActions(r1, r2)
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.C00381.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleEventHandler
    public void onMessageActionOccurredOnPreview(InAppMessage message, InAppMessageClickResult action) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        Intrinsics.checkNotNullParameter(action, "action");
        ThreadUtilsKt.suspendifyOnThread$default(0, new C00381(action, message, this, null), 1, null);
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessageActionOccurredOnMessage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessageActionOccurredOnMessage$1", f = "InAppMessagesManager.kt", i = {}, l = {665, 666, 668, 670}, m = "invokeSuspend", n = {}, s = {})
    static final class C00371 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ InAppMessageClickResult $action;
        final /* synthetic */ InAppMessage $message;
        int label;
        final /* synthetic */ InAppMessagesManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00371(InAppMessageClickResult inAppMessageClickResult, InAppMessage inAppMessage, InAppMessagesManager inAppMessagesManager, Continuation<? super C00371> continuation) {
            super(1, continuation);
            this.$action = inAppMessageClickResult;
            this.$message = inAppMessage;
            this.this$0 = inAppMessagesManager;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C00371(this.$action, this.$message, this.this$0, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x005e A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0078 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x009a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x009b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                switch(r1) {
                    case 0: goto L26;
                    case 1: goto L21;
                    case 2: goto L1c;
                    case 3: goto L17;
                    case 4: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L11:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L9c
            L17:
                r1 = r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L79
            L1c:
                r1 = r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L5f
            L21:
                r1 = r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L48
            L26:
                kotlin.ResultKt.throwOnFailure(r8)
                r1 = r7
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r2 = r1.$action
                com.onesignal.inAppMessages.internal.InAppMessage r3 = r1.$message
                boolean r3 = r3.takeActionAsUnique()
                r2.setFirstClick(r3)
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = r1.this$0
                com.onesignal.inAppMessages.internal.InAppMessage r3 = r1.$message
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r4 = r1.$action
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r6 = 1
                r1.label = r6
                java.lang.Object r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$firePublicClickHandler(r2, r3, r4, r5)
                if (r2 != r0) goto L48
                return r0
            L48:
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = r1.this$0
                com.onesignal.inAppMessages.internal.InAppMessage r3 = r1.$message
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r4 = r1.$action
                java.util.List r4 = r4.getPrompts()
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r6 = 2
                r1.label = r6
                java.lang.Object r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$beginProcessingPrompts(r2, r3, r4, r5)
                if (r2 != r0) goto L5f
                return r0
            L5f:
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = r1.this$0
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r3 = r1.$action
                com.onesignal.inAppMessages.internal.InAppMessagesManager.access$fireClickAction(r2, r3)
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = r1.this$0
                com.onesignal.inAppMessages.internal.InAppMessage r3 = r1.$message
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r4 = r1.$action
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r6 = 3
                r1.label = r6
                java.lang.Object r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$fireRESTCallForClick(r2, r3, r4, r5)
                if (r2 != r0) goto L79
                return r0
            L79:
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = r1.this$0
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r3 = r1.$action
                com.onesignal.inAppMessages.internal.InAppMessagesManager.access$fireTagCallForClick(r2, r3)
                com.onesignal.inAppMessages.internal.InAppMessagesManager r2 = r1.this$0
                com.onesignal.inAppMessages.internal.InAppMessage r3 = r1.$message
                java.lang.String r3 = r3.getMessageId()
                com.onesignal.inAppMessages.internal.InAppMessageClickResult r4 = r1.$action
                java.util.List r4 = r4.getOutcomes()
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r6 = 4
                r1.label = r6
                java.lang.Object r2 = com.onesignal.inAppMessages.internal.InAppMessagesManager.access$fireOutcomesForClick(r2, r3, r4, r5)
                if (r2 != r0) goto L9b
                return r0
            L9b:
                r0 = r1
            L9c:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.C00371.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleEventHandler
    public void onMessageActionOccurredOnMessage(InAppMessage message, InAppMessageClickResult action) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        Intrinsics.checkNotNullParameter(action, "action");
        ThreadUtilsKt.suspendifyOnThread$default(0, new C00371(action, message, this, null), 1, null);
    }

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleEventHandler
    public void onMessagePageChanged(InAppMessage message, InAppMessagePage page) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        Intrinsics.checkNotNullParameter(page, "page");
        if (message.isPreview()) {
            return;
        }
        ThreadUtilsKt.suspendifyOnThread$default(0, new C00391(message, page, null), 1, null);
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessagePageChanged$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessagePageChanged$1", f = "InAppMessagesManager.kt", i = {}, l = {683}, m = "invokeSuspend", n = {}, s = {})
    static final class C00391 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ InAppMessage $message;
        final /* synthetic */ InAppMessagePage $page;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00391(InAppMessage inAppMessage, InAppMessagePage inAppMessagePage, Continuation<? super C00391> continuation) {
            super(1, continuation);
            this.$message = inAppMessage;
            this.$page = inAppMessagePage;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return InAppMessagesManager.this.new C00391(this.$message, this.$page, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (InAppMessagesManager.this.fireRESTCallForPageChange(this.$message, this.$page, (Continuation) this) == coroutine_suspended) {
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

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleEventHandler
    public void onMessageWillDismiss(final InAppMessage message) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        if (!this.lifecycleCallback.getHasSubscribers()) {
            Logging.verbose$default("InAppMessagesManager.onMessageWillDismiss: inAppMessageLifecycleHandler is null", null, 2, null);
        } else {
            this.lifecycleCallback.fireOnMain(new Function1<IInAppMessageLifecycleListener, Unit>() { // from class: com.onesignal.inAppMessages.internal.InAppMessagesManager.onMessageWillDismiss.1
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    invoke((IInAppMessageLifecycleListener) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(IInAppMessageLifecycleListener it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.onWillDismiss(new InAppMessageLifecycleEvent(message));
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessageWasDismissed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$onMessageWasDismissed$1", f = "InAppMessagesManager.kt", i = {}, l = {697}, m = "invokeSuspend", n = {}, s = {})
    static final class C00401 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ InAppMessage $message;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00401(InAppMessage inAppMessage, Continuation<? super C00401> continuation) {
            super(1, continuation);
            this.$message = inAppMessage;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return InAppMessagesManager.this.new C00401(this.$message, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (InAppMessagesManager.messageWasDismissed$default(InAppMessagesManager.this, this.$message, false, (Continuation) this, 2, null) == coroutine_suspended) {
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

    @Override // com.onesignal.inAppMessages.internal.lifecycle.IInAppLifecycleEventHandler
    public void onMessageWasDismissed(InAppMessage message) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        ThreadUtilsKt.suspendifyOnThread$default(0, new C00401(message, null), 1, null);
    }

    @Override // com.onesignal.inAppMessages.internal.triggers.ITriggerHandler
    public void onTriggerCompleted(String triggerId) {
        Intrinsics.checkNotNullParameter(triggerId, "triggerId");
        Logging.debug$default("InAppMessagesManager.onTriggerCompleted: called with triggerId: " + triggerId, null, 2, null);
        Set triggerIds = new HashSet();
        triggerIds.add(triggerId);
    }

    @Override // com.onesignal.inAppMessages.internal.triggers.ITriggerHandler
    public void onTriggerConditionChanged(String triggerId) {
        Intrinsics.checkNotNullParameter(triggerId, "triggerId");
        Logging.debug$default("InAppMessagesManager.onTriggerConditionChanged()", null, 2, null);
        makeRedisplayMessagesAvailableWithTriggers(CollectionsKt.listOf(triggerId), false);
        ThreadUtilsKt.suspendifyOnThread$default(0, new C00461(null), 1, null);
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$onTriggerConditionChanged$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$onTriggerConditionChanged$1", f = "InAppMessagesManager.kt", i = {}, l = {733}, m = "invokeSuspend", n = {}, s = {})
    static final class C00461 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;

        C00461(Continuation<? super C00461> continuation) {
            super(1, continuation);
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return InAppMessagesManager.this.new C00461(continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (InAppMessagesManager.this.evaluateInAppMessages((Continuation) this) == coroutine_suspended) {
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

    @Override // com.onesignal.inAppMessages.internal.triggers.ITriggerHandler
    public void onTriggerChanged(String newTriggerKey) {
        Intrinsics.checkNotNullParameter(newTriggerKey, "newTriggerKey");
        Logging.debug$default("InAppMessagesManager.onTriggerChanged(newTriggerKey: " + newTriggerKey + ')', null, 2, null);
        makeRedisplayMessagesAvailableWithTriggers(CollectionsKt.listOf(newTriggerKey), true);
        ThreadUtilsKt.suspendifyOnThread$default(0, new C00451(null), 1, null);
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$onTriggerChanged$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$onTriggerChanged$1", f = "InAppMessagesManager.kt", i = {}, l = {745}, m = "invokeSuspend", n = {}, s = {})
    static final class C00451 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;

        C00451(Continuation<? super C00451> continuation) {
            super(1, continuation);
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return InAppMessagesManager.this.new C00451(continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (InAppMessagesManager.this.evaluateInAppMessages((Continuation) this) == coroutine_suspended) {
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
    public final Object beginProcessingPrompts(InAppMessage message, List<? extends InAppMessagePrompt> list, Continuation<? super Unit> continuation) {
        if (list.isEmpty()) {
            return Unit.INSTANCE;
        }
        Logging.debug$default("InAppMessagesManager.beginProcessingPrompts: IAM showing prompts from IAM: " + message, null, 2, null);
        this._displayer.dismissCurrentInAppMessage();
        Object objShowMultiplePrompts = showMultiplePrompts(message, list, continuation);
        return objShowMultiplePrompts == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objShowMultiplePrompts : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object fireOutcomesForClick(java.lang.String r8, java.util.List<com.onesignal.inAppMessages.internal.InAppMessageOutcome> r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof com.onesignal.inAppMessages.internal.InAppMessagesManager.C00321
            if (r0 == 0) goto L14
            r0 = r10
            com.onesignal.inAppMessages.internal.InAppMessagesManager$fireOutcomesForClick$1 r0 = (com.onesignal.inAppMessages.internal.InAppMessagesManager.C00321) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.InAppMessagesManager$fireOutcomesForClick$1 r0 = new com.onesignal.inAppMessages.internal.InAppMessagesManager$fireOutcomesForClick$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L51;
                case 1: goto L45;
                case 2: goto L39;
                case 3: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            java.lang.Object r8 = r0.L$1
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r0.L$0
            com.onesignal.inAppMessages.internal.InAppMessagesManager r9 = (com.onesignal.inAppMessages.internal.InAppMessagesManager) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto Lb4
        L39:
            java.lang.Object r8 = r0.L$1
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r0.L$0
            com.onesignal.inAppMessages.internal.InAppMessagesManager r9 = (com.onesignal.inAppMessages.internal.InAppMessagesManager) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto La3
        L45:
            java.lang.Object r8 = r0.L$1
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r0.L$0
            com.onesignal.inAppMessages.internal.InAppMessagesManager r9 = (com.onesignal.inAppMessages.internal.InAppMessagesManager) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L85
        L51:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r7
            com.onesignal.session.internal.influence.IInfluenceManager r3 = r2._influenceManager
            r3.onDirectInfluenceFromIAM(r8)
            java.util.Iterator r8 = r9.iterator()
            r9 = r2
        L5f:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto Lb5
            java.lang.Object r2 = r8.next()
            com.onesignal.inAppMessages.internal.InAppMessageOutcome r2 = (com.onesignal.inAppMessages.internal.InAppMessageOutcome) r2
            java.lang.String r3 = r2.getName()
            boolean r4 = r2.isUnique()
            if (r4 == 0) goto L86
            com.onesignal.session.internal.outcomes.IOutcomeEventsController r2 = r9._outcomeEventsController
            r0.L$0 = r9
            r0.L$1 = r8
            r4 = 1
            r0.label = r4
            java.lang.Object r2 = r2.sendUniqueOutcomeEvent(r3, r0)
            if (r2 != r1) goto L85
            return r1
        L85:
            goto L5f
        L86:
            float r4 = r2.getWeight()
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto La4
            com.onesignal.session.internal.outcomes.IOutcomeEventsController r4 = r9._outcomeEventsController
            float r5 = r2.getWeight()
            r0.L$0 = r9
            r0.L$1 = r8
            r6 = 2
            r0.label = r6
            java.lang.Object r2 = r4.sendOutcomeEventWithValue(r3, r5, r0)
            if (r2 != r1) goto La3
            return r1
        La3:
            goto L5f
        La4:
            com.onesignal.session.internal.outcomes.IOutcomeEventsController r2 = r9._outcomeEventsController
            r0.L$0 = r9
            r0.L$1 = r8
            r4 = 3
            r0.label = r4
            java.lang.Object r2 = r2.sendOutcomeEvent(r3, r0)
            if (r2 != r1) goto Lb4
            return r1
        Lb4:
            goto L5f
        Lb5:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.fireOutcomesForClick(java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fireTagCallForClick(InAppMessageClickResult action) {
        if (action.getTags() != null) {
            InAppMessageTag tags = action.getTags();
            if ((tags != null ? tags.getTagsToAdd() : null) != null) {
                JSONUtils jSONUtils = JSONUtils.INSTANCE;
                JSONObject tagsToAdd = tags.getTagsToAdd();
                Intrinsics.checkNotNull(tagsToAdd);
                this._userManager.addTags(jSONUtils.newStringMapFromJSONObject(tagsToAdd));
            }
            if ((tags != null ? tags.getTagsToRemove() : null) != null) {
                JSONUtils jSONUtils2 = JSONUtils.INSTANCE;
                JSONArray tagsToRemove = tags != null ? tags.getTagsToRemove() : null;
                Intrinsics.checkNotNull(tagsToRemove);
                this._userManager.removeTags(jSONUtils2.newStringSetFromJSONArray(tagsToRemove));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00b3 -> B:23:0x00ba). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object showMultiplePrompts(com.onesignal.inAppMessages.internal.InAppMessage r13, java.util.List<? extends com.onesignal.inAppMessages.internal.prompt.impl.InAppMessagePrompt> r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.showMultiplePrompts(com.onesignal.inAppMessages.internal.InAppMessage, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fireClickAction(InAppMessageClickResult action) {
        if (action.getUrl() != null) {
            if (action.getUrl().length() > 0) {
                if (action.getUrlTarget() == InAppMessageActionUrlType.BROWSER) {
                    AndroidUtils.INSTANCE.openURLInBrowser(this._applicationService.getAppContext(), action.getUrl());
                } else if (action.getUrlTarget() == InAppMessageActionUrlType.IN_APP_WEBVIEW) {
                    OneSignalChromeTab.INSTANCE.open$com_onesignal_inAppMessages(action.getUrl(), true, this._applicationService.getAppContext());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logInAppMessagePreviewActions(InAppMessageClickResult action) {
        if (action.getTags() != null) {
            Logging.debug$default("InAppMessagesManager.logInAppMessagePreviewActions: Tags detected inside of the action click payload, ignoring because action came from IAM preview:: " + action.getTags(), null, 2, null);
        }
        if (action.getOutcomes().size() > 0) {
            Logging.debug$default("InAppMessagesManager.logInAppMessagePreviewActions: Outcomes detected inside of the action click payload, ignoring because action came from IAM preview: " + action.getOutcomes(), null, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object firePublicClickHandler(InAppMessage message, InAppMessageClickResult action, Continuation<? super Unit> continuation) {
        if (!this.messageClickCallback.getHasSubscribers()) {
            return Unit.INSTANCE;
        }
        this._influenceManager.onDirectInfluenceFromIAM(message.getMessageId());
        InAppMessageClickEvent result = new InAppMessageClickEvent(message, action);
        Object objSuspendingFireOnMain = this.messageClickCallback.suspendingFireOnMain(new AnonymousClass2(result, null), continuation);
        return objSuspendingFireOnMain == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSuspendingFireOnMain : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.InAppMessagesManager$firePublicClickHandler$2, reason: invalid class name */
    /* JADX INFO: compiled from: InAppMessagesManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lcom/onesignal/inAppMessages/IInAppMessageClickListener;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.InAppMessagesManager$firePublicClickHandler$2", f = "InAppMessagesManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<IInAppMessageClickListener, Continuation<? super Unit>, Object> {
        final /* synthetic */ InAppMessageClickEvent $result;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(InAppMessageClickEvent inAppMessageClickEvent, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$result = inAppMessageClickEvent;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$result, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(IInAppMessageClickListener iInAppMessageClickListener, Continuation<? super Unit> continuation) {
            return create(iInAppMessageClickListener, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    IInAppMessageClickListener it = (IInAppMessageClickListener) this.L$0;
                    it.onClick(this.$result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object fireRESTCallForPageChange(com.onesignal.inAppMessages.internal.InAppMessage r10, com.onesignal.inAppMessages.internal.InAppMessagePage r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.fireRESTCallForPageChange(com.onesignal.inAppMessages.internal.InAppMessage, com.onesignal.inAppMessages.internal.InAppMessagePage, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object fireRESTCallForClick(com.onesignal.inAppMessages.internal.InAppMessage r12, com.onesignal.inAppMessages.internal.InAppMessageClickResult r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.InAppMessagesManager.fireRESTCallForClick(com.onesignal.inAppMessages.internal.InAppMessage, com.onesignal.inAppMessages.internal.InAppMessageClickResult, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void showAlertDialogMessage(final InAppMessage inAppMessage, final List<? extends InAppMessagePrompt> list) {
        String messageTitle = this._applicationService.getAppContext().getString(R.string.location_permission_missing_title);
        Intrinsics.checkNotNullExpressionValue(messageTitle, "_applicationService.appC…permission_missing_title)");
        String message = this._applicationService.getAppContext().getString(R.string.location_permission_missing_message);
        Intrinsics.checkNotNullExpressionValue(message, "_applicationService.appC…rmission_missing_message)");
        new AlertDialog.Builder(this._applicationService.getCurrent()).setTitle(messageTitle).setMessage(message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.onesignal.inAppMessages.internal.InAppMessagesManager$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                InAppMessagesManager.m50showAlertDialogMessage$lambda7(this.f$0, inAppMessage, list, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showAlertDialogMessage$lambda-7, reason: not valid java name */
    public static final void m50showAlertDialogMessage$lambda7(InAppMessagesManager this$0, InAppMessage $inAppMessage, List $prompts, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter($inAppMessage, "$inAppMessage");
        Intrinsics.checkNotNullParameter($prompts, "$prompts");
        ThreadUtilsKt.suspendifyOnThread$default(0, new InAppMessagesManager$showAlertDialogMessage$1$1(this$0, $inAppMessage, $prompts, null), 1, null);
    }

    @Override // com.onesignal.core.internal.application.IApplicationLifecycleHandler
    public void onFocus(boolean firedOnSubscribe) {
    }

    @Override // com.onesignal.core.internal.application.IApplicationLifecycleHandler
    public void onUnfocused() {
    }
}
