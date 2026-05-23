package com.primexop.webview.baseHelpers;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.primexop.webview.projectHelpers.config;

/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
public class PermissionHandler {
    private final Activity mActivity;

    public PermissionHandler(Activity activity) {
        this.mActivity = activity;
    }

    public boolean checkPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(this.mActivity, permission);
        return result == 0;
    }

    public void requestPermission(final String permission, final int requestCode, String permissionNameForAlert) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.mActivity, permission)) {
            new AlertDialog.Builder(this.mActivity).setTitle("Permission Denied").setMessage("You cant use this feature because you already denied for " + permissionNameForAlert + "Permission").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.primexop.webview.baseHelpers.PermissionHandler$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    PermissionHandler.lambda$requestPermission$0(dialogInterface, i);
                }
            }).create().show();
        } else {
            new AlertDialog.Builder(this.mActivity).setTitle(permissionNameForAlert + " Permission Needed").setMessage("This App needs the " + permissionNameForAlert + " Permission, Please accept it to use this functionality.").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.primexop.webview.baseHelpers.PermissionHandler$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.m101lambda$requestPermission$1$comprimexopwebviewbaseHelpersPermissionHandler(permission, requestCode, dialogInterface, i);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.primexop.webview.baseHelpers.PermissionHandler$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    PermissionHandler.lambda$requestPermission$2(dialogInterface, i);
                }
            }).create().show();
        }
    }

    static /* synthetic */ void lambda$requestPermission$0(DialogInterface dialogInterface, int i) {
    }

    /* JADX INFO: renamed from: lambda$requestPermission$1$com-primexop-webview-baseHelpers-PermissionHandler, reason: not valid java name */
    /* synthetic */ void m101lambda$requestPermission$1$comprimexopwebviewbaseHelpersPermissionHandler(String permission, int requestCode, DialogInterface dialogInterface, int i) {
        ActivityCompat.requestPermissions(this.mActivity, new String[]{permission}, requestCode);
    }

    static /* synthetic */ void lambda$requestPermission$2(DialogInterface dialogInterface, int i) {
    }

    public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, int myRequestCode) {
        if (requestCode != myRequestCode) {
            return false;
        }
        boolean allPermissionsGranted = true;
        int i = 0;
        int length = grantResults.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            int result = grantResults[i2];
            if (result == 0) {
                i++;
                i2++;
            } else {
                allPermissionsGranted = false;
                if (config.isLocalEnvironment.booleanValue()) {
                    Toast.makeText(this.mActivity, permissions[i] + " Permission denied .. ", 0).show();
                }
            }
        }
        if (!allPermissionsGranted) {
            return false;
        }
        if (config.isLocalEnvironment.booleanValue()) {
            Toast.makeText(this.mActivity, "All Permission granted", 0).show();
            return true;
        }
        return true;
    }

    public boolean isPermissionGranted(String permission, int requestCode, String permissionNameForAlert) {
        if (!checkPermission(permission)) {
            requestPermission(permission, requestCode, permissionNameForAlert);
            return false;
        }
        return true;
    }
}
