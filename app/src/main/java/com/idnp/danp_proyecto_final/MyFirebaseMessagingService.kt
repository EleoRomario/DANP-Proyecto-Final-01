package com.idnp.danp_proyecto_final

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.idnp.danp_proyecto_final.navegation.AppNavigation
import com.idnp.danp_proyecto_final.navegation.AppScreens

class MyFirebaseMessagingService : FirebaseMessagingService(){


    override fun onNewToken(token: String) {
        Log.d("TOKEN","$token")

    }

}

@Composable
fun CustomNotification(){
    val context = LocalContext.current
    val channelId = "MyTestChannel"
    val notificationId = 0
    val myBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.arequipa)
    val bigText = "Vive la experiencia de viajar a Arequipa la ciudad blanca y disfrutar de sus espectaculares paisajes."

    LaunchedEffect(Unit) {
        createNotificationChannel(channelId, context)
    }
    ShowBigPictureWithThumbnailNotification(
            context,
            channelId,
            notificationId,
            "Nuevo destino",
            bigText,
            myBitmap
        )
}

fun createNotificationChannel(channelId: String, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "MyTestChannel"
        val descriptionText = "My important test channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

@Composable
fun ShowBigPictureWithThumbnailNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    bigImage: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {

    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val detailDestinoIntent = Intent(
        Intent.ACTION_VIEW,
        "https://example.com/".toUri(),
        context,
        MainActivity::class.java
    )

    val pending: PendingIntent = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(detailDestinoIntent)
        getPendingIntent(notificationId, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_logo)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(bigImage)
        .setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(bigImage)
                .bigLargeIcon(null)
        )
        .setPriority(priority)
        .addAction(R.drawable.ic_heart,"favorite",pendingIntent)
        .addAction(R.drawable.ic_heart,"ver",pending)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }

}