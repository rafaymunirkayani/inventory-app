// CreateUserAccountActivity.java
package inventoryapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class CreateUserAccountActivity extends AppCompatActivity {
    private EditText userUsername;
    private EditText userPassword;
    private EditText userId;
    private DBHelper dbHelper;
    private static final String TAG = "CreateUserAccount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_account);

        userUsername = findViewById(R.id.userUsername);
        userPassword = findViewById(R.id.userPassword);
        userId = findViewById(R.id.userId);
        dbHelper = new DBHelper(this);

        findViewById(R.id.createUserAccountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userUsername.getText().toString();
                String password = userPassword.getText().toString();
                String id = userId.getText().toString();

                Log.d(TAG, "Create button clicked with: " +
                        "username=" + username + ", password=" + password + ", userId=" + id);

                if (username.isEmpty() || password.isEmpty() || id.isEmpty()) {
                    Toast.makeText(CreateUserAccountActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isSuccess = dbHelper.addUser(username, password, id);
                    if (isSuccess) {
                        Toast.makeText(CreateUserAccountActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CreateUserAccountActivity.this, "Error creating account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
