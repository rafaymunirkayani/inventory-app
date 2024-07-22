package inventoryapp;

public class IssuedEquipment {
    private String username;
    private String equipmentId;
    private String issueDate;

    // Constructor
    public IssuedEquipment(String username, String equipmentId, String issueDate) {
        this.username = username;
        this.equipmentId = equipmentId;
        this.issueDate = issueDate;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
}
