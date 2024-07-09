package inventoryapp;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class CreateUserAccountActivity extends AppCompatActivity {
    private EditText userUsername;
    private EditText userPassword;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_account);

        userUsername = findViewById(R.id.userUsername);
        userPassword = findViewById(R.id.userPassword);
        dbHelper = new DBHelper(this);

        findViewById(R.id.createUserAccountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userUsername.getText().toString();
                String password = userPassword.getText().toString();

                if (dbHelper.addUser(username, password)) {
                    Toast.makeText(CreateUserAccountActivity.this, "User account created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateUserAccountActivity.this, UserLoginActivity.class); // Replace with your UserLoginActivity
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CreateUserAccountActivity.this, "Error creating user account", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
