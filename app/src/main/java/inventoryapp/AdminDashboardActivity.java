package inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private ListView issuedEquipmentListView;
    private IssuedEquipmentAdapter issuedEquipmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        dbHelper = new DBHelper(this);
        issuedEquipmentListView = findViewById(R.id.issuedEquipmentListView);

        // Get list of issued equipments
        List<IssuedEquipment> issuedEquipments = dbHelper.getIssuedEquipments();

        // Set up the adapter
        issuedEquipmentAdapter = new IssuedEquipmentAdapter(this, issuedEquipments);

        // Set the adapter to the ListView
        issuedEquipmentListView.setAdapter(issuedEquipmentAdapter);
        findViewById(R.id.addEquipmentButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboardActivity.this, AddEquipmentActivity.class);
                startActivity(intent);
            }
        });
    }
}
