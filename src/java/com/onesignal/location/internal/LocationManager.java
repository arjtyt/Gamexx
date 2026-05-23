package com.onesignal.location.internal;

import android.content.pm.PackageManager;
import android.os.Build;
import com.onesignal.common.AndroidUtils;
import com.onesignal.common.threading.ThreadUtilsKt;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.preferences.IPreferencesService;
import com.onesignal.core.internal.preferences.PreferenceOneSignalKeys;
import com.onesignal.core.internal.preferences.PreferenceStores;
import com.onesignal.core.internal.startup.IStartableService;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.location.BuildConfig;
import com.onesignal.location.ILocationManager;
import com.onesignal.location.internal.capture.ILocationCapturer;
import com.onesignal.location.internal.common.LocationConstants;
import com.onesignal.location.internal.common.LocationUtils;
import com.onesignal.location.internal.controller.ILocationController;
import com.onesignal.location.internal.permissions.ILocationPermissionChangedHandler;
import com.onesignal.location.internal.permissions.LocationPermissionController;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LocationManager.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010H\u0016J\u0011\u0010\u001c\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001aH\u0016J\u0011\u0010\u001f\u001a\u00020\u001aH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00108V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lcom/onesignal/location/internal/LocationManager;", "Lcom/onesignal/location/ILocationManager;", "Lcom/onesignal/core/internal/startup/IStartableService;", "Lcom/onesignal/location/internal/permissions/ILocationPermissionChangedHandler;", "_applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "_capturer", "Lcom/onesignal/location/internal/capture/ILocationCapturer;", "_locationController", "Lcom/onesignal/location/internal/controller/ILocationController;", "_locationPermissionController", "Lcom/onesignal/location/internal/permissions/LocationPermissionController;", "_prefs", "Lcom/onesignal/core/internal/preferences/IPreferencesService;", "(Lcom/onesignal/core/internal/application/IApplicationService;Lcom/onesignal/location/internal/capture/ILocationCapturer;Lcom/onesignal/location/internal/controller/ILocationController;Lcom/onesignal/location/internal/permissions/LocationPermissionController;Lcom/onesignal/core/internal/preferences/IPreferencesService;)V", "_isShared", "", "value", "isShared", "()Z", "setShared", "(Z)V", "backgroundLocationPermissionLogic", "fallbackToSettings", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onLocationPermissionChanged", "", "enabled", "requestPermission", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "startGetLocation", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LocationManager implements ILocationManager, IStartableService, ILocationPermissionChangedHandler {
    private final IApplicationService _applicationService;
    private final ILocationCapturer _capturer;
    private boolean _isShared;
    private final ILocationController _locationController;
    private final LocationPermissionController _locationPermissionController;
    private final IPreferencesService _prefs;

    /* JADX INFO: renamed from: com.onesignal.location.internal.LocationManager$requestPermission$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LocationManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.location.internal.LocationManager", f = "LocationManager.kt", i = {0}, l = {79}, m = "requestPermission", n = {"result"}, s = {"L$0"})
    static final class C00821 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00821(Continuation<? super C00821> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LocationManager.this.requestPermission((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.location.internal.LocationManager$startGetLocation$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LocationManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.location.internal.LocationManager", f = "LocationManager.kt", i = {}, l = {195}, m = "startGetLocation", n = {}, s = {})
    static final class C00841 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C00841(Continuation<? super C00841> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LocationManager.this.startGetLocation((Continuation) this);
        }
    }

    public LocationManager(IApplicationService _applicationService, ILocationCapturer _capturer, ILocationController _locationController, LocationPermissionController _locationPermissionController, IPreferencesService _prefs) {
        Intrinsics.checkNotNullParameter(_applicationService, "_applicationService");
        Intrinsics.checkNotNullParameter(_capturer, "_capturer");
        Intrinsics.checkNotNullParameter(_locationController, "_locationController");
        Intrinsics.checkNotNullParameter(_locationPermissionController, "_locationPermissionController");
        Intrinsics.checkNotNullParameter(_prefs, "_prefs");
        this._applicationService = _applicationService;
        this._capturer = _capturer;
        this._locationController = _locationController;
        this._locationPermissionController = _locationPermissionController;
        this._prefs = _prefs;
        Boolean bool = this._prefs.getBool(PreferenceStores.ONESIGNAL, PreferenceOneSignalKeys.PREFS_OS_LOCATION_SHARED, false);
        Intrinsics.checkNotNull(bool);
        this._isShared = bool.booleanValue();
    }

    @Override // com.onesignal.location.ILocationManager
    public boolean isShared() {
        return this._isShared;
    }

    @Override // com.onesignal.location.ILocationManager
    public void setShared(boolean value) {
        Logging.debug$default("LocationManager.setIsShared(value: " + value + ')', null, 2, null);
        this._prefs.saveBool(PreferenceStores.ONESIGNAL, PreferenceOneSignalKeys.PREFS_OS_LOCATION_SHARED, Boolean.valueOf(value));
        this._isShared = value;
        onLocationPermissionChanged(value);
    }

    @Override // com.onesignal.core.internal.startup.IStartableService
    public void start() {
        this._locationPermissionController.subscribe((ILocationPermissionChangedHandler) this);
        if (LocationUtils.INSTANCE.hasLocationPermission(this._applicationService.getAppContext())) {
            ThreadUtilsKt.suspendifyOnThread$default(0, new C00831(null), 1, null);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.location.internal.LocationManager$start$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LocationManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.location.internal.LocationManager$start$1", f = "LocationManager.kt", i = {}, l = {45}, m = "invokeSuspend", n = {}, s = {})
    static final class C00831 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;

        C00831(Continuation<? super C00831> continuation) {
            super(1, continuation);
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return LocationManager.this.new C00831(continuation);
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
                    if (LocationManager.this.startGetLocation((Continuation) this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.onesignal.location.internal.LocationManager$onLocationPermissionChanged$1, reason: invalid class name */
    /* JADX INFO: compiled from: LocationManager.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.location.internal.LocationManager$onLocationPermissionChanged$1", f = "LocationManager.kt", i = {}, l = {53}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return LocationManager.this.new AnonymousClass1(continuation);
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
                    if (LocationManager.this.startGetLocation((Continuation) this) == coroutine_suspended) {
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

    @Override // com.onesignal.location.internal.permissions.ILocationPermissionChangedHandler
    public void onLocationPermissionChanged(boolean enabled) {
        if (enabled) {
            ThreadUtilsKt.suspendifyOnThread$default(0, new AnonymousClass1(null), 1, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.location.ILocationManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object requestPermission(kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.onesignal.location.internal.LocationManager.C00821
            if (r0 == 0) goto L14
            r0 = r8
            com.onesignal.location.internal.LocationManager$requestPermission$1 r0 = (com.onesignal.location.internal.LocationManager.C00821) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.onesignal.location.internal.LocationManager$requestPermission$1 r0 = new com.onesignal.location.internal.LocationManager$requestPermission$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L2c:
            java.lang.Object r1 = r0.L$0
            kotlin.jvm.internal.Ref$BooleanRef r1 = (kotlin.jvm.internal.Ref.BooleanRef) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5f
        L34:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r7
            com.onesignal.debug.LogLevel r3 = com.onesignal.debug.LogLevel.DEBUG
            java.lang.String r4 = "LocationManager.requestPermission()"
            com.onesignal.debug.internal.logging.Logging.log(r3, r4)
            kotlin.jvm.internal.Ref$BooleanRef r3 = new kotlin.jvm.internal.Ref$BooleanRef
            r3.<init>()
            kotlinx.coroutines.MainCoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getMain()
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            com.onesignal.location.internal.LocationManager$requestPermission$2 r5 = new com.onesignal.location.internal.LocationManager$requestPermission$2
            r6 = 0
            r5.<init>(r3, r6)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r0.L$0 = r3
            r6 = 1
            r0.label = r6
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r4, r5, r0)
            if (r2 != r1) goto L5e
            return r1
        L5e:
            r1 = r3
        L5f:
            boolean r2 = r1.element
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.location.internal.LocationManager.requestPermission(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.onesignal.location.internal.LocationManager$requestPermission$2, reason: invalid class name */
    /* JADX INFO: compiled from: LocationManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.location.internal.LocationManager$requestPermission$2", f = "LocationManager.kt", i = {}, l = {109, 150, 155, 158}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
        final /* synthetic */ Ref.BooleanRef $result;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Ref.BooleanRef booleanRef, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$result = booleanRef;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LocationManager.this.new AnonymousClass2(this.$result, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<Object> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) throws PackageManager.NameNotFoundException {
            Object $result2;
            Object $result3;
            Ref.BooleanRef booleanRef;
            Ref.BooleanRef booleanRef2;
            Object $result4;
            Ref.BooleanRef booleanRef3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            boolean zBooleanValue = true;
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $result2 = $result;
                    if (!LocationManager.this.isShared()) {
                        Logging.warn$default("Requesting location permission, but location sharing must also be enabled by setting isShared to true", null, 2, null);
                    }
                    boolean hasFinePermissionGranted = AndroidUtils.INSTANCE.hasPermission(LocationConstants.ANDROID_FINE_LOCATION_PERMISSION_STRING, true, LocationManager.this._applicationService);
                    boolean hasCoarsePermissionGranted = false;
                    if (!hasFinePermissionGranted) {
                        hasCoarsePermissionGranted = AndroidUtils.INSTANCE.hasPermission(LocationConstants.ANDROID_COARSE_LOCATION_PERMISSION_STRING, true, LocationManager.this._applicationService);
                        LocationManager.this._capturer.setLocationCoarse(true);
                    }
                    boolean hasBackgroundPermissionGranted = Build.VERSION.SDK_INT >= 29 ? AndroidUtils.INSTANCE.hasPermission(LocationConstants.ANDROID_BACKGROUND_LOCATION_PERMISSION_STRING, true, LocationManager.this._applicationService) : false;
                    if (hasFinePermissionGranted) {
                        if (Build.VERSION.SDK_INT < 29 || hasBackgroundPermissionGranted) {
                            this.$result.element = true;
                            this.label = 4;
                            return LocationManager.this.startGetLocation((Continuation) this) == coroutine_suspended ? coroutine_suspended : Unit.INSTANCE;
                        }
                        Ref.BooleanRef booleanRef4 = this.$result;
                        this.L$0 = booleanRef4;
                        this.label = 3;
                        Object objBackgroundLocationPermissionLogic = LocationManager.this.backgroundLocationPermissionLogic(true, (Continuation) this);
                        if (objBackgroundLocationPermissionLogic == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result3 = objBackgroundLocationPermissionLogic;
                        booleanRef = booleanRef4;
                        booleanRef.element = ((Boolean) $result3).booleanValue();
                    }
                    String requestPermission = (String) null;
                    List<String> listFilterManifestPermissions = AndroidUtils.INSTANCE.filterManifestPermissions(CollectionsKt.listOf(new String[]{LocationConstants.ANDROID_FINE_LOCATION_PERMISSION_STRING, LocationConstants.ANDROID_COARSE_LOCATION_PERMISSION_STRING, LocationConstants.ANDROID_BACKGROUND_LOCATION_PERMISSION_STRING}), LocationManager.this._applicationService);
                    if (listFilterManifestPermissions.contains(LocationConstants.ANDROID_FINE_LOCATION_PERMISSION_STRING)) {
                        requestPermission = LocationConstants.ANDROID_FINE_LOCATION_PERMISSION_STRING;
                    } else if (!listFilterManifestPermissions.contains(LocationConstants.ANDROID_COARSE_LOCATION_PERMISSION_STRING)) {
                        Logging.info$default("Location permissions not added on AndroidManifest file >= M", null, 2, null);
                    } else if (!hasCoarsePermissionGranted) {
                        requestPermission = LocationConstants.ANDROID_COARSE_LOCATION_PERMISSION_STRING;
                    } else if (Build.VERSION.SDK_INT >= 29 && listFilterManifestPermissions.contains(LocationConstants.ANDROID_BACKGROUND_LOCATION_PERMISSION_STRING)) {
                        requestPermission = LocationConstants.ANDROID_BACKGROUND_LOCATION_PERMISSION_STRING;
                    }
                    booleanRef2 = this.$result;
                    if (requestPermission == null) {
                        if (!hasCoarsePermissionGranted) {
                            zBooleanValue = false;
                        }
                        booleanRef2.element = zBooleanValue;
                    }
                    this.L$0 = booleanRef2;
                    this.label = 2;
                    Object objPrompt = LocationManager.this._locationPermissionController.prompt(true, requestPermission, (Continuation) this);
                    if (objPrompt == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result4 = objPrompt;
                    booleanRef3 = booleanRef2;
                    booleanRef2 = booleanRef3;
                    zBooleanValue = ((Boolean) $result4).booleanValue();
                    booleanRef2.element = zBooleanValue;
                case 1:
                    ResultKt.throwOnFailure($result);
                    this.$result.element = true;
                case 2:
                    $result4 = $result;
                    booleanRef3 = (Ref.BooleanRef) this.L$0;
                    ResultKt.throwOnFailure($result4);
                    $result2 = $result4;
                    booleanRef2 = booleanRef3;
                    zBooleanValue = ((Boolean) $result4).booleanValue();
                    booleanRef2.element = zBooleanValue;
                case 3:
                    $result3 = $result;
                    booleanRef = (Ref.BooleanRef) this.L$0;
                    ResultKt.throwOnFailure($result3);
                    $result2 = $result3;
                    booleanRef.element = ((Boolean) $result3).booleanValue();
                case 4:
                    ResultKt.throwOnFailure($result);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object backgroundLocationPermissionLogic(boolean fallbackToSettings, Continuation<? super Boolean> continuation) {
        boolean hasManifestPermission = AndroidUtils.INSTANCE.hasPermission(LocationConstants.ANDROID_BACKGROUND_LOCATION_PERMISSION_STRING, false, this._applicationService);
        if (hasManifestPermission) {
            return this._locationPermissionController.prompt(fallbackToSettings, LocationConstants.ANDROID_BACKGROUND_LOCATION_PERMISSION_STRING, continuation);
        }
        return Boxing.boxBoolean(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object startGetLocation(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.onesignal.location.internal.LocationManager.C00841
            if (r0 == 0) goto L14
            r0 = r8
            com.onesignal.location.internal.LocationManager$startGetLocation$1 r0 = (com.onesignal.location.internal.LocationManager.C00841) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.onesignal.location.internal.LocationManager$startGetLocation$1 r0 = new com.onesignal.location.internal.LocationManager$startGetLocation$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L2e:
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L33
            r5 = r8
            goto L54
        L33:
            r1 = move-exception
            goto L62
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r7
            boolean r5 = r2.isShared()
            if (r5 != 0) goto L42
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L42:
            java.lang.String r5 = "LocationManager.startGetLocation()"
            com.onesignal.debug.internal.logging.Logging.debug$default(r5, r4, r3, r4)
            com.onesignal.location.internal.controller.ILocationController r5 = r2._locationController     // Catch: java.lang.Throwable -> L33
            r6 = 1
            r0.label = r6     // Catch: java.lang.Throwable -> L33
            java.lang.Object r5 = r5.start(r0)     // Catch: java.lang.Throwable -> L33
            if (r5 != r1) goto L54
            return r1
        L54:
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Throwable -> L33
            boolean r1 = r5.booleanValue()     // Catch: java.lang.Throwable -> L33
            if (r1 != 0) goto L69
            java.lang.String r1 = "LocationManager.startGetLocation: not possible, no location dependency found"
            com.onesignal.debug.internal.logging.Logging.warn$default(r1, r4, r3, r4)     // Catch: java.lang.Throwable -> L33
            goto L69
        L62:
            java.lang.String r2 = "LocationManager.startGetLocation: Location permission exists but there was an error initializing: "
            com.onesignal.debug.internal.logging.Logging.warn(r2, r1)
        L69:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.location.internal.LocationManager.startGetLocation(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
