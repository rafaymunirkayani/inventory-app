package inventoryapp;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.List;

public class UserDashboardActivity extends AppCompatActivity {
    private Spinner equipmentSpinner;
    private DBHelper dbHelper;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        equipmentSpinner = findViewById(R.id.equipmentSpinner);
        dbHelper = new DBHelper(this);

        List<String> availableEquipments = dbHelper.getAvailableEquipments();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, availableEquipments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        equipmentSpinner.setAdapter(adapter);

        username = getIntent().getStringExtra("username");

        findViewById(R.id.issueEquipmentButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String equipment = equipmentSpinner.getSelectedItem().toString();
                if (dbHelper.issueEquipment(username, equipment)) {
                    Toast.makeText(UserDashboardActivity.this, "Equipment issued successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UserDashboardActivity.this, "Error issuing equipment", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
