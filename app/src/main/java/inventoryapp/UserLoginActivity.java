package inventoryapp;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class UserLoginActivity extends AppCompatActivity {
    private EditText userUsername;
    private EditText userPassword;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        userUsername = findViewById(R.id.userUsername);
        userPassword = findViewById(R.id.userPassword);
        dbHelper = new DBHelper(this);

        findViewById(R.id.userLoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userUsername.getText().toString();
                String password = userPassword.getText().toString();

                if (dbHelper.checkUserCredentials(username, password)) {
                    Intent intent = new Intent(UserLoginActivity.this, UserDashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UserLoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.usercreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLoginActivity.this, CreateUserAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
