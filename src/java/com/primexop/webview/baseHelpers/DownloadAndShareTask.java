package com.primexop.webview.baseHelpers;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: compiled from: JavaScriptInterface.java */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
class DownloadAndShareTask extends AsyncTask<String, Void, File> {
    private Context context;

    public DownloadAndShareTask(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public File doInBackground(String... urls) {
        String imageUrl = urls[0];
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            File file = new File(this.context.getCacheDir(), "shared_qr_code.png");
            FileOutputStream out = new FileOutputStream(file);
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.close();
                return file;
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(File file) {
        if (file != null) {
            Intent shareIntent = new Intent("android.intent.action.SEND");
            shareIntent.setType("image/png");
            shareIntent.setPackage("com.google.android.apps.nbu.paisa.user");
            Uri imageUri = FileProvider.getUriForFile(this.context, this.context.getPackageName() + ".fileprovider", file);
            shareIntent.putExtra("android.intent.extra.STREAM", imageUri);
            shareIntent.addFlags(1);
            try {
                this.context.startActivity(shareIntent);
            } catch (ActivityNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
