package inventoryapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AdminLoginActivity extends AppCompatActivity {
    private EditText adminUsername;
    private EditText adminPassword;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminUsername = findViewById(R.id.adminUsername);
        adminPassword = findViewById(R.id.adminPassword);
        dbHelper = new DBHelper(this);

        findViewById(R.id.adminLoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = adminUsername.getText().toString();
                String password = adminPassword.getText().toString();

                if (dbHelper.checkAdminCredentials(username, password)) {
                    Intent intent = new Intent(AdminLoginActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AdminLoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.createAdminAccountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLoginActivity.this, CreateAdminAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
