package com.example.davidcoverdale.dayback_second;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailLogin extends AppCompatActivity {

    EditText editTextEmailPasswordSignUp, editTextEmailPasswordCheck, editTextEmailSignUp;
    String emailSignUp, emailPasswordSignUp, emailPasswordCheck;
    Button btnEmailSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        editTextEmailSignUp = (EditText) findViewById(R.id.editTextEmailSignUp);
        editTextEmailPasswordSignUp = (EditText) findViewById(R.id.editTextEmailPasswordSignUp);
        editTextEmailPasswordCheck = (EditText) findViewById(R.id.editTextEmailPasswordCheck);

        emailSignUp = editTextEmailSignUp.getText().toString();
        emailPasswordSignUp = editTextEmailPasswordSignUp.getText().toString();
        emailPasswordCheck = editTextEmailPasswordCheck.getText().toString();

        btnEmailSignUp = (Button) findViewById(R.id.btnemailSignUp);

        if(!SignUtil.validateEmail(emailSignUp)) {
            Toast.makeText(this, "유효한 이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show();
        }
        else if(!SignUtil.validatePassword(emailPasswordSignUp)) {
            Toast.makeText(this, "4자리~ 16자리로 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (!emailPasswordSignUp.equals(emailPasswordCheck)) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "가입되었습니다. 환영합니다.", Toast.LENGTH_SHORT).show();
        }


    }
}

    class SignUtil {
    // 이메일정규식
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,10}$", Pattern.CASE_INSENSITIVE);
    //
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    //비밀번호정규식
    public static final Pattern VALID_PASSWOLD_REGEX_ALPHA_NUM = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{6,16}$"); // 4자리 ~ 16자리까지 가능

    public static boolean validatePassword(String pwStr) {
        Matcher matcher = VALID_PASSWOLD_REGEX_ALPHA_NUM.matcher(pwStr);
        return matcher.matches();
    }
}
