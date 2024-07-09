package inventoryapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class IssuedEquipmentAdapter extends BaseAdapter {
    private Context context;
    private List<IssuedEquipment> issuedEquipments;

    public IssuedEquipmentAdapter(Context context, List<IssuedEquipment> issuedEquipments) {
        this.context = context;
        this.issuedEquipments = issuedEquipments;
    }

    @Override
    public int getCount() {
        return issuedEquipments.size();
    }

    @Override
    public Object getItem(int position) {
        return issuedEquipments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.issued_equipment_item, parent, false);
        }

        IssuedEquipment issuedEquipment = issuedEquipments.get(position);

        TextView usernameTextView = convertView.findViewById(R.id.usernameTextView);
        TextView equipmentIdTextView = convertView.findViewById(R.id.equipmentIdTextView);
        TextView issueDateTextView = convertView.findViewById(R.id.issueDateTextView);
        TextView returnDateTextView = convertView.findViewById(R.id.returnDateTextView);

        usernameTextView.setText("User: " + issuedEquipment.getUsername());
        equipmentIdTextView.setText("Equipment ID: " + issuedEquipment.getEquipmentId());
        issueDateTextView.setText("Issue Date: " + issuedEquipment.getIssueDate());
        returnDateTextView.setText("Return Date: " + issuedEquipment.getReturnDate());

        return convertView;
    }

}
