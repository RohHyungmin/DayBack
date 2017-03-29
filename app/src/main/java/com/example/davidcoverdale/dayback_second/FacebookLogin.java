package com.example.davidcoverdale.dayback_second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;


public class FacebookLogin extends AppCompatActivity {

    LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook_login);

        callbackManager = CallbackManager.Factory.create();  //로그인 응답을 처리할 콜백 관리자
        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.setReadPermissions("public_profile", "user_friends","email");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) { //로그인 성공시 호출되는 메소드
                Log.e("토큰",loginResult.getAccessToken().getToken());
                Log.e("유저아이디",loginResult.getAccessToken().getUserId());
                Log.e("퍼미션 리스트",loginResult.getAccessToken().getPermissions()+"");

                //loginResult.getAccessToken() 정보를 가지고 유저 정보를 가져올수 있습니다.
                GraphRequest request =GraphRequest.newMeRequest(loginResult.getAccessToken() ,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    Log.e("user profile",object.toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                request.executeAsync();
            }

            @Override
            public void onError(FacebookException error) { }

            @Override
            public void onCancel() { }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
