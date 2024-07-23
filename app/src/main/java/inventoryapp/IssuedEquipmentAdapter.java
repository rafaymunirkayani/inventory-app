// IssuedEquipmentAdapter.java
package inventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.text.DateFormat;
import java.util.Date;
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
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.issued_equipment_item, parent, false);
            holder = new ViewHolder();
            holder.usernameTextView = convertView.findViewById(R.id.usernameTextView);
            holder.userIdTextView = convertView.findViewById(R.id.userIdTextView);
            holder.equipmentIdTextView = convertView.findViewById(R.id.equipmentIdTextView);
            holder.issueDateTextView = convertView.findViewById(R.id.issueDateTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        IssuedEquipment issuedEquipment = issuedEquipments.get(position);

        holder.usernameTextView.setText("User: " + issuedEquipment.getUsername());
        holder.userIdTextView.setText("User ID: " + issuedEquipment.getUserId());
        holder.equipmentIdTextView.setText("Equipment ID: " + issuedEquipment.getEquipmentId());

        // Convert issueDate from timestamp to readable format
        long timestamp = Long.parseLong(issuedEquipment.getIssueDate());
        String issueDateStr = DateFormat.getDateTimeInstance().format(new Date(timestamp));
        holder.issueDateTextView.setText("Issue Date: " + issueDateStr);

        return convertView;
    }

    static class ViewHolder {
        TextView usernameTextView;
        TextView userIdTextView;
        TextView equipmentIdTextView;
        TextView issueDateTextView;
    }
}
