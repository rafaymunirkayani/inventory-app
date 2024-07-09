package inventoryapp;



import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AddEquipmentActivity extends AppCompatActivity {
    private EditText equipmentName;
    private EditText equipmentId;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);

        equipmentName = findViewById(R.id.equipmentName);
        equipmentId = findViewById(R.id.equipmentId);
        dbHelper = new DBHelper(this);

        findViewById(R.id.addEquipmentButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = equipmentName.getText().toString();
                String id = equipmentId.getText().toString();

                if (dbHelper.addEquipment(name, id)) {
                    Toast.makeText(AddEquipmentActivity.this, "Equipment added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddEquipmentActivity.this, "Error adding equipment", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
