package com.onesignal.core.internal.purchases.impl;

import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.PurchasingService;
import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserDataResponse;
import com.onesignal.common.threading.ThreadUtilsKt;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.application.IApplicationLifecycleHandler;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.config.ConfigModelStore;
import com.onesignal.core.internal.operations.IOperationRepo;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.user.internal.identity.IdentityModelStore;
import com.onesignal.user.internal.operations.PurchaseInfo;
import com.onesignal.user.internal.operations.TrackPurchaseOperation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: TrackAmazonPurchase.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 \u001f2\u00020\u00012\u00020\u0002:\u0002\u001f B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\rH\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0018\u00010\u0013R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/onesignal/core/internal/purchases/impl/TrackAmazonPurchase;", "Lcom/onesignal/core/internal/startup/IStartableService;", "Lcom/onesignal/core/internal/application/IApplicationLifecycleHandler;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_operationRepo", "Lcom/onesignal/core/internal/operations/IOperationRepo;", "_configModelStore", "Lcom/onesignal/core/internal/config/ConfigModelStore;", "_identityModelStore", "Lcom/onesignal/user/internal/identity/IdentityModelStore;", "(Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/core/internal/operations/IOperationRepo;Lcom/onesignal/core/internal/config/ConfigModelStore;Lcom/onesignal/user/internal/identity/IdentityModelStore;)V", "canTrack", "", "listenerHandlerField", "Ljava/lang/reflect/Field;", "listenerHandlerObject", "", "osPurchasingListener", "Lcom/onesignal/core/internal/purchases/impl/TrackAmazonPurchase$OSPurchasingListener;", "registerListenerOnMainThread", "logAmazonIAPListenerError", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onFocus", "firedOnSubscribe", "onUnfocused", "setListener", "start", "Companion", "OSPurchasingListener", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class TrackAmazonPurchase implements IStartableService, IApplicationLifecycleHandler {
    public static final Companion Companion = new Companion(null);
    private final IApplicationService _applicationService;
    private final ConfigModelStore _configModelStore;
    private final IdentityModelStore _identityModelStore;
    private final IOperationRepo _operationRepo;
    private boolean canTrack;
    private Field listenerHandlerField;
    private Object listenerHandlerObject;
    private OSPurchasingListener osPurchasingListener;
    private boolean registerListenerOnMainThread;

    public TrackAmazonPurchase(IApplicationService _applicationService, IOperationRepo _operationRepo, ConfigModelStore _configModelStore, IdentityModelStore _identityModelStore) {
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_operationRepo, "_operationRepo");
        Intrinsics.checkNotNullParameter(_configModelStore, "_configModelStore");
        Intrinsics.checkNotNullParameter(_identityModelStore, "_identityModelStore");
        this._applicationService = _applicationService;
        this._operationRepo = _operationRepo;
        this._configModelStore = _configModelStore;
        this._identityModelStore = _identityModelStore;
    }

    @Override // com.onesignal.core.internal.startup.IStartableService
    public void start() {
        if (!Companion.canTrack()) {
            return;
        }
        try {
            try {
                Class<?> cls = Class.forName("com.amazon.device.iap.internal.d");
                try {
                    this.listenerHandlerObject = cls.getMethod("d", new Class[0]).invoke(null, new Object[0]);
                } catch (NullPointerException e) {
                    try {
                        this.listenerHandlerObject = cls.getMethod("e", new Class[0]).invoke(null, new Object[0]);
                        this.registerListenerOnMainThread = true;
                    } catch (NullPointerException e2) {
                        try {
                            this.listenerHandlerObject = cls.getMethod("g", new Class[0]).invoke(null, new Object[0]);
                            this.registerListenerOnMainThread = true;
                        } catch (NoSuchMethodException e3) {
                            this.listenerHandlerObject = cls.getMethod("f", new Class[0]).invoke(null, new Object[0]);
                            this.registerListenerOnMainThread = true;
                        }
                    }
                }
                Field locListenerHandlerField = cls.getDeclaredField("f");
                locListenerHandlerField.setAccessible(true);
                this.osPurchasingListener = new OSPurchasingListener(this, this._operationRepo, this._configModelStore, this._identityModelStore);
                OSPurchasingListener oSPurchasingListener = this.osPurchasingListener;
                Intrinsics.checkNotNull(oSPurchasingListener);
                oSPurchasingListener.setOrgPurchasingListener((PurchasingListener) locListenerHandlerField.get(this.listenerHandlerObject));
                this.listenerHandlerField = locListenerHandlerField;
                this.canTrack = true;
                setListener();
            } catch (NoSuchMethodException e4) {
                logAmazonIAPListenerError(e4);
            }
        } catch (ClassCastException e5) {
            logAmazonIAPListenerError(e5);
        } catch (ClassNotFoundException e6) {
            logAmazonIAPListenerError(e6);
        } catch (IllegalAccessException e7) {
            logAmazonIAPListenerError(e7);
        } catch (NoSuchFieldException e8) {
            logAmazonIAPListenerError(e8);
        } catch (InvocationTargetException e9) {
            logAmazonIAPListenerError(e9);
        }
        this._applicationService.addApplicationLifecycleHandler(this);
    }

    private final void logAmazonIAPListenerError(Exception e) {
        Logging.error("Error adding Amazon IAP listener.", e);
        e.printStackTrace();
    }

    @Override // com.onesignal.core.internal.application.IApplicationLifecycleHandler
    public void onFocus(boolean firedOnSubscribe) {
    }

    @Override // com.onesignal.core.internal.application.IApplicationLifecycleHandler
    public void onUnfocused() {
        if (this.canTrack) {
            try {
                Field field = this.listenerHandlerField;
                Intrinsics.checkNotNull(field);
                PurchasingListener curPurchasingListener = (PurchasingListener) field.get(this.listenerHandlerObject);
                if (curPurchasingListener != this.osPurchasingListener) {
                    OSPurchasingListener oSPurchasingListener = this.osPurchasingListener;
                    Intrinsics.checkNotNull(oSPurchasingListener);
                    oSPurchasingListener.setOrgPurchasingListener(curPurchasingListener);
                    setListener();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: renamed from: com.onesignal.core.internal.purchases.impl.TrackAmazonPurchase$setListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: TrackAmazonPurchase.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.core.internal.purchases.impl.TrackAmazonPurchase$setListener$1", f = "TrackAmazonPurchase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return TrackAmazonPurchase.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    PurchasingService.registerListener(TrackAmazonPurchase.this._applicationService.getAppContext(), TrackAmazonPurchase.this.osPurchasingListener);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final void setListener() {
        if (this.registerListenerOnMainThread) {
            ThreadUtilsKt.suspendifyOnMain(new AnonymousClass1(null));
        } else {
            PurchasingService.registerListener(this._applicationService.getAppContext(), this.osPurchasingListener);
        }
    }

    /* JADX INFO: compiled from: TrackAmazonPurchase.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u0012\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u001dH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/onesignal/core/internal/purchases/impl/TrackAmazonPurchase$OSPurchasingListener;", "Lcom/amazon/device/iap/PurchasingListener;", "_operationRepo", "Lcom/onesignal/core/internal/operations/IOperationRepo;", "_configModelStore", "Lcom/onesignal/core/internal/config/ConfigModelStore;", "_identityModelStore", "Lcom/onesignal/user/internal/identity/IdentityModelStore;", "(Lcom/onesignal/core/internal/purchases/impl/TrackAmazonPurchase;Lcom/onesignal/core/internal/operations/IOperationRepo;Lcom/onesignal/core/internal/config/ConfigModelStore;Lcom/onesignal/user/internal/identity/IdentityModelStore;)V", "currentMarket", "", "lastRequestId", "Lcom/amazon/device/iap/model/RequestId;", "orgPurchasingListener", "getOrgPurchasingListener", "()Lcom/amazon/device/iap/PurchasingListener;", "setOrgPurchasingListener", "(Lcom/amazon/device/iap/PurchasingListener;)V", "marketToCurrencyCode", "market", "onProductDataResponse", "", "response", "Lcom/amazon/device/iap/model/ProductDataResponse;", "onPurchaseResponse", "Lcom/amazon/device/iap/model/PurchaseResponse;", "onPurchaseUpdatesResponse", "Lcom/amazon/device/iap/model/PurchaseUpdatesResponse;", "onUserDataResponse", "Lcom/amazon/device/iap/model/UserDataResponse;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    private final class OSPurchasingListener implements PurchasingListener {
        private final ConfigModelStore _configModelStore;
        private final IdentityModelStore _identityModelStore;
        private final IOperationRepo _operationRepo;
        private String currentMarket;
        private RequestId lastRequestId;
        private PurchasingListener orgPurchasingListener;
        final /* synthetic */ TrackAmazonPurchase this$0;

        /* JADX INFO: compiled from: TrackAmazonPurchase.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ProductDataResponse.RequestStatus.values().length];
                iArr[ProductDataResponse.RequestStatus.SUCCESSFUL.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public OSPurchasingListener(TrackAmazonPurchase this$0, IOperationRepo _operationRepo, ConfigModelStore _configModelStore, IdentityModelStore _identityModelStore) {
            Intrinsics.checkNotNullParameter(_operationRepo, "_operationRepo");
            Intrinsics.checkNotNullParameter(_configModelStore, "_configModelStore");
            Intrinsics.checkNotNullParameter(_identityModelStore, "_identityModelStore");
            this.this$0 = this$0;
            this._operationRepo = _operationRepo;
            this._configModelStore = _configModelStore;
            this._identityModelStore = _identityModelStore;
        }

        public final PurchasingListener getOrgPurchasingListener() {
            return this.orgPurchasingListener;
        }

        public final void setOrgPurchasingListener(PurchasingListener purchasingListener) {
            this.orgPurchasingListener = purchasingListener;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        private final String marketToCurrencyCode(String market) {
            if (market == null) {
                return "";
            }
            switch (market.hashCode()) {
                case 2100:
                    return !market.equals("AU") ? "" : "AUD";
                case 2128:
                    return !market.equals("BR") ? "" : "BRL";
                case 2142:
                    return !market.equals("CA") ? "" : "CDN";
                case 2177:
                    return !market.equals("DE") ? "" : "EUR";
                case 2222:
                    return !market.equals("ES") ? "" : "EUR";
                case 2252:
                    return !market.equals("FR") ? "" : "EUR";
                case 2267:
                    return !market.equals("GB") ? "" : "GBP";
                case 2347:
                    return !market.equals("IT") ? "" : "EUR";
                case 2374:
                    return !market.equals("JP") ? "" : "JPY";
                case 2718:
                    return !market.equals("US") ? "" : "USD";
                default:
                    return "";
            }
        }

        public void onProductDataResponse(ProductDataResponse response) {
            Intrinsics.checkNotNullParameter(response, "response");
            if (this.lastRequestId != null && Intrinsics.areEqual(String.valueOf(this.lastRequestId), response.getRequestId().toString())) {
                ProductDataResponse.RequestStatus requestStatus = response.getRequestStatus();
                if ((requestStatus == null ? -1 : WhenMappings.$EnumSwitchMapping$0[requestStatus.ordinal()]) == 1) {
                    List purchasesToReport = new ArrayList();
                    Map products = response.getProductData();
                    BigDecimal amountSpent = new BigDecimal(0);
                    BigDecimal amountSpent2 = amountSpent;
                    for (String key : products.keySet()) {
                        Product product = (Product) products.get(key);
                        Intrinsics.checkNotNull(product);
                        String sku = product.getSku();
                        String iso = marketToCurrencyCode(this.currentMarket);
                        String priceStr = product.getPrice();
                        Intrinsics.checkNotNullExpressionValue(priceStr, "priceStr");
                        if (!new Regex("^[0-9]").matches(priceStr)) {
                            Intrinsics.checkNotNullExpressionValue(priceStr, "priceStr");
                            String strSubstring = priceStr.substring(1);
                            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                            priceStr = strSubstring;
                        }
                        BigDecimal price = new BigDecimal(priceStr);
                        BigDecimal bigDecimalAdd = amountSpent2.add(price);
                        Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
                        amountSpent2 = bigDecimalAdd;
                        Intrinsics.checkNotNullExpressionValue(sku, "sku");
                        purchasesToReport.add(new PurchaseInfo(sku, iso, price));
                    }
                    IOperationRepo.DefaultImpls.enqueue$default(this._operationRepo, new TrackPurchaseOperation(this._configModelStore.getModel().getAppId(), this._identityModelStore.getModel().getOnesignalId(), false, amountSpent2, purchasesToReport), false, 2, null);
                    return;
                }
                return;
            }
            if (this.orgPurchasingListener != null) {
                PurchasingListener purchasingListener = this.orgPurchasingListener;
                Intrinsics.checkNotNull(purchasingListener);
                purchasingListener.onProductDataResponse(response);
            }
        }

        public void onPurchaseResponse(PurchaseResponse response) {
            Intrinsics.checkNotNullParameter(response, "response");
            PurchaseResponse.RequestStatus status = response.getRequestStatus();
            if (status == PurchaseResponse.RequestStatus.SUCCESSFUL) {
                this.currentMarket = response.getUserData().getMarketplace();
                Set productSkus = new HashSet();
                String sku = response.getReceipt().getSku();
                Intrinsics.checkNotNullExpressionValue(sku, "response.receipt.sku");
                productSkus.add(sku);
                this.lastRequestId = PurchasingService.getProductData(productSkus);
            }
            if (this.orgPurchasingListener != null) {
                PurchasingListener purchasingListener = this.orgPurchasingListener;
                Intrinsics.checkNotNull(purchasingListener);
                purchasingListener.onPurchaseResponse(response);
            }
        }

        public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse response) {
            Intrinsics.checkNotNullParameter(response, "response");
            if (this.orgPurchasingListener != null) {
                PurchasingListener purchasingListener = this.orgPurchasingListener;
                Intrinsics.checkNotNull(purchasingListener);
                purchasingListener.onPurchaseUpdatesResponse(response);
            }
        }

        public void onUserDataResponse(UserDataResponse response) {
            Intrinsics.checkNotNullParameter(response, "response");
            if (this.orgPurchasingListener != null) {
                PurchasingListener purchasingListener = this.orgPurchasingListener;
                Intrinsics.checkNotNull(purchasingListener);
                purchasingListener.onUserDataResponse(response);
            }
        }
    }

    /* JADX INFO: compiled from: TrackAmazonPurchase.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/onesignal/core/internal/purchases/impl/TrackAmazonPurchase$Companion;", "", "()V", "canTrack", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean canTrack() {
            try {
                Class.forName("com.amazon.device.iap.PurchasingListener");
                return true;
            } catch (ClassNotFoundException e) {
                return false;
            }
        }
    }
}
