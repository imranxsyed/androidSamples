package com.isyed.samples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.squareup.picasso.Picasso

const val GOOGLE_LOGIN = 109
class LoginActivity : AppCompatActivity() {
    lateinit var  callbackManager : CallbackManager
    lateinit var mGoogleSignInClient : GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Facebook Login Starts
        callbackManager  = CallbackManager.Factory.create()

        val loginButton = findViewById<View>(R.id.fb_login_button) as LoginButton
        loginButton.setReadPermissions(arrayListOf("email"))
        // If you are using in a fragment, call loginButton.setFragment(this);

        loginButton.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    println("User Info")
                    val userId = result!!.accessToken.userId
                    postSuccessLogin(userId)


                }

                override fun onCancel() {
                    Toast.makeText(this@LoginActivity, "user cancelled", Toast.LENGTH_LONG).show()
                }

                override fun onError(error: FacebookException?) {
                    Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_LONG).show()

                }

            })

        //Facebook Login Ends

        //Google Login Starts

        //Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        checkIfUserAlreadyLoggedIn()

        (findViewById<View>(R.id.google_sigin_button) as SignInButton).setOnClickListener{
            performGoogleSignin()
        }
        //Google Login Ends

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Handle Google Login
        if (requestCode == GOOGLE_LOGIN){

            // The Task returned from this call is always completed, no need to attach a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
        else{
            //Handle Facebook Login
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "Google Sigin Success", Toast.LENGTH_LONG).show()
            postSuccessLogin(account?.email!!)
            println(account.email)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(this, "Google Sigin Failed  ${e.getStatusCode()}", Toast.LENGTH_LONG).show()
            //updateUI(null)
        }
    }

    private fun checkIfUserAlreadyLoggedIn(){
        //Facebook Starts
        val  accessToken : AccessToken? = AccessToken.getCurrentAccessToken();
        val  isLoggedIn = accessToken != null && !accessToken.isExpired()
        if (isLoggedIn){
            postSuccessLogin(accessToken!!.userId)
            Toast.makeText(this , "Facebook User Already Logged In", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Facebook User Not Logged In", Toast.LENGTH_LONG).show()
        }
        //Facebook Ends

        //Google Starts

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null){
            Toast.makeText(this , "Google User Already Logged In", Toast.LENGTH_LONG).show()
            postSuccessLogin(account.email!!)
        }
        else{
            Toast.makeText(this , "Google User Not Logged In", Toast.LENGTH_LONG).show()
        }

        //Google Ends
    }

    private fun postSuccessLogin(userId: String){
        val intent  = Intent()
        intent.setClass(this, PrimaryActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun logUserOut(){
        println("----CLICKED")
        //facebook begin
        LoginManager.getInstance().logOut()
        //facebook ends
    }

    private fun performGoogleSignin(){
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, GOOGLE_LOGIN_IN)
    }
}