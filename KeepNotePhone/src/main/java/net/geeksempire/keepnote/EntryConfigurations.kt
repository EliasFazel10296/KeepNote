package net.geeksempire.keepnote

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import net.geeksempire.keepnote.AccountManager.SignInProcess.SetupAccount
import net.geeksempire.keepnote.Notes.Taking.TakeNote
import net.geeksempire.keepnote.Overview.UserInterface.KeepNoteOverview
import net.geeksempire.keepnote.Utils.UI.NotifyUser.SnackbarActionHandlerInterface
import net.geeksempire.keepnote.Utils.UI.NotifyUser.SnackbarBuilder
import net.geeksempire.keepnote.databinding.EntryConfigurationLayoutBinding

class EntryConfigurations : AppCompatActivity() {

    private val setupAccount = SetupAccount()

    lateinit var entryConfigurationLayoutBinding: EntryConfigurationLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entryConfigurationLayoutBinding = EntryConfigurationLayoutBinding.inflate(layoutInflater)
        setContentView(entryConfigurationLayoutBinding.root)

        if (setupAccount.firebaseUser == null) {

            entryConfigurationLayoutBinding.blurryBackground.visibility = View.VISIBLE

            entryConfigurationLayoutBinding.waitingView.visibility = View.VISIBLE
            entryConfigurationLayoutBinding.waitingInformationView.visibility = View.VISIBLE

            val signInAnonymously = {
                setupAccount.signInAnonymously()
            }

            signInAnonymously.invoke().addOnSuccessListener {

                entryConfigurationLayoutBinding.waitingView.visibility = View.GONE
                entryConfigurationLayoutBinding.waitingInformationView.visibility = View.GONE

                openTakeNoteActivity()

            }.addOnFailureListener {

                SnackbarBuilder(applicationContext).show (
                    rootView = entryConfigurationLayoutBinding.rootView,
                    messageText= getString(R.string.anonymouslySignInError),
                    messageDuration = Snackbar.LENGTH_INDEFINITE,
                    actionButtonText = R.string.retryText,
                    snackbarActionHandlerInterface = object : SnackbarActionHandlerInterface {

                        override fun onActionButtonClicked(snackbar: Snackbar) {
                            super.onActionButtonClicked(snackbar)

                            signInAnonymously.invoke()

                        }

                    }
                )

            }

        } else {

//            openOverviewActivity()

            openTakeNoteActivity()

        }

    }

    private fun openOverviewActivity() {

        startActivity(Intent(applicationContext, KeepNoteOverview::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }, ActivityOptions.makeCustomAnimation(applicationContext, R.anim.fade_in, 0).toBundle())

    }

    private fun openTakeNoteActivity() {

        startActivity(Intent(applicationContext, TakeNote::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }, ActivityOptions.makeCustomAnimation(applicationContext, R.anim.fade_in, 0).toBundle())

    }

}