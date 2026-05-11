package com.romzz.music;

import android.content.Context;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.JSObject;
import java.util.concurrent.Executors;

@CapacitorPlugin(name = "GoogleSignIn")
public class GoogleSignInPlugin extends Plugin {

    @PluginMethod
    public void signIn(PluginCall call) {
        Context context = getContext();
        String webClientId = call.getString("webClientId", "");

        GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(webClientId)
            .setAutoSelectEnabled(true)
            .build();

        GetCredentialRequest request = new GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build();

        CredentialManager credentialManager = CredentialManager.create(context);

        credentialManager.getCredentialAsync(
            getActivity(),
            request,
            null,
            Executors.newSingleThreadExecutor(),
            new CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() {
                @Override
                public void onResult(GetCredentialResponse result) {
                    try {
                        GoogleIdTokenCredential credential = GoogleIdTokenCredential
                            .createFrom(result.getCredential().getData());
                        JSObject ret = new JSObject();
                        ret.put("idToken", credential.getIdToken());
                        ret.put("email", credential.getId());
                        ret.put("displayName", credential.getDisplayName());
                        call.resolve(ret);
                    } catch (Exception e) {
                        call.reject("Failed to get credential: " + e.getMessage());
                    }
                }

                @Override
                public void onError(GetCredentialException e) {
                    call.reject("Sign in failed: " + e.getMessage());
                }
            }
        );
    }
}
