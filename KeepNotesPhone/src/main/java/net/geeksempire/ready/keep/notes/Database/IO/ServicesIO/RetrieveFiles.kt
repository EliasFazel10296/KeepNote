package net.geeksempire.ready.keep.notes.Database.IO.ServicesIO

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.geeksempire.ready.keep.notes.Database.NetworkEndpoints.DatabaseEndpoints
import net.geeksempire.ready.keep.notes.R
import net.geeksempire.ready.keep.notes.Utils.Extensions.println
import net.geeksempire.ready.keep.notes.Utils.UI.NotifyUser.NotificationBuilder

class RetrieveFiles : Service() {

    private val databaseEndpoints = DatabaseEndpoints()

    object Foreground {
        const val NotificationId = 357
    }

    companion object {
        const val BaseDirectory = "BaseDirectory"

        /**
         * @param baseDirectory = externalMediaDirs[0].path
         **/
        fun startProcess(context: Context,
                         baseDirectory: String) {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                context.startForegroundService(Intent(context, RetrieveFiles::class.java).apply {
                    putExtra(RetrieveFiles.BaseDirectory, baseDirectory)
                })

            } else {

                context.startService(Intent(context, RetrieveFiles::class.java).apply {
                    putExtra(RetrieveFiles.BaseDirectory, baseDirectory)
                })

            }

        }

    }

    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Firebase.auth.currentUser?.let { firebaseUser ->

            intent?.let { inputData ->

                if (inputData.hasExtra(RetrieveFiles.BaseDirectory)) {

                    startForeground(RetrieveFiles.Foreground.NotificationId,
                        NotificationBuilder(applicationContext)
                            .create(notificationId = RetrieveFiles.Foreground.NotificationId, notificationChannelId = this@RetrieveFiles.javaClass.simpleName,
                                notificationTitle = getString(R.string.applicationName), notificationContent = getString(R.string.retrievingFilesText)))

                    val firebaseStorage = Firebase.storage

                    val baseDirectory: String = inputData.getStringExtra(RetrieveFiles.BaseDirectory)!!

                    firebaseStorage.getReference(databaseEndpoints.generalEndpoints(firebaseUser.uid))
                        .listAll()
                        .addOnSuccessListener { allNotesReference ->

                            allNotesReference.prefixes.forEach { aNoteReference ->

                                aNoteReference
                                    .listAll()
                                    .addOnSuccessListener { allFolders ->

                                        // Get Handwriting Snapshot File
                                        // Get Audio Recording Files
                                        // Get Images Files

                                        allFolders.prefixes.forEach { aFolder ->

                                            aFolder.name.println()


                                        }

                                    }

                            }

                        }
//
//                    val audioRecordingFile = AudioRecordingLocalFile(baseDirectory)
//
//                    firebaseStorage.getReference(databaseEndpoints.voiceRecordingEndpoint(firebaseUser.uid))
//                        .listAll()
//                        .addOnSuccessListener { listResult ->
//
//                            val audioRecordingDirectoryPath = File(audioRecordingFile.getAudioRecordingDirectoryPath(firebaseUser.uid))
//
//                            if (!audioRecordingDirectoryPath.exists()) {
//
//                                audioRecordingDirectoryPath.mkdirs()
//
//                            }
//
//                            listResult.items.forEach { storageReference ->
//
//                                storageReference
//                                    .getFile(File(audioRecordingFile.getAudioRecordingFilePath(firebaseUser.uid, storageReference.name)))
//
//                            }
//
//                        }
//
//                    val imagingFile = ImagingLocalFile(baseDirectory)
//
//                    firebaseStorage.getReference(databaseEndpoints.imageEndpoint(firebaseUser.uid))
//                        .listAll()
//                        .addOnSuccessListener { listResult ->
//
//                            val imagingDirectoryPath = File(imagingFile.getImagingDirectoryPath(firebaseUser.uid))
//
//                            if (!imagingDirectoryPath.exists()) {
//
//                                imagingDirectoryPath.mkdirs()
//
//                            }
//
//                            listResult.items.forEach { storageReference ->
//
//                                storageReference
//                                    .getFile(File(imagingFile.getImagingFilePath(firebaseUser.uid, storageReference.name)))
//
//                            }
//
//                        }

                }

            }

        }

        return Service.START_STICKY
    }

}