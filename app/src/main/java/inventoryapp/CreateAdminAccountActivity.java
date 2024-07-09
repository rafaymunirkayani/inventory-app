package inventoryapp;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class CreateAdminAccountActivity extends AppCompatActivity {
    private EditText adminUsername;
    private EditText adminPassword;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin_account);

        adminUsername = findViewById(R.id.adminUsername);
        adminPassword = findViewById(R.id.adminPassword);
        dbHelper = new DBHelper(this);

        findViewById(R.id.createAdminAccountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = adminUsername.getText().toString();
                String password = adminPassword.getText().toString();

                if (dbHelper.addAdmin(username, password)) {
                    Toast.makeText(CreateAdminAccountActivity.this, "Admin account created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateAdminAccountActivity.this, AdminLoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CreateAdminAccountActivity.this, "Error creating admin account", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
