package com.hackathon.equityafia.feature_auth.presentation


import android.app.Activity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AuthRepository @Inject constructor() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    @Singleton
    @Provides
    fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), firebaseAuth.currentUser == null)

    @Singleton
    @Provides
    suspend fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String
    ): Boolean{
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            return true
        } catch (e: Exception) {
            println("Exception $e")
            return false
        }
    }

    @Singleton
    @Provides
    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): Boolean {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            return true
        } catch (e: Exception) {
            println("Exception $e")
            return false
        }
    }

    @Singleton
    @Provides
    suspend fun firebaseSignInWithGoogle(activity: Activity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(activity, gso)

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(activity, signInIntent, 1, null)


    }

    @Singleton
    @Provides
    fun signOut(): Boolean {
        try {
            firebaseAuth.signOut()
            return true
        } catch (e: Exception) {
            throw e
        }
    }

    @Singleton
    @Provides
    suspend fun sendPasswordResetEmail(email: String) {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
        } catch (e: Exception) {
            throw e
        }
    }

    @Singleton
    @Provides
    suspend fun updateUserDetails(name: String, phone: String, email: String, password: String, password2: String): Boolean{
        return try {
           firebaseAuth.currentUser?.updateProfile(
                com.google.firebase.auth.UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
            )?.await()
            firebaseAuth.currentUser?.updateEmail(email)?.await()
            if( password != ""){
                firebaseAuth.currentUser?.updatePassword(password)?.await()
            }
            true
        } catch (e: Exception) {
            println(e)
            throw e
        }
    }
}


