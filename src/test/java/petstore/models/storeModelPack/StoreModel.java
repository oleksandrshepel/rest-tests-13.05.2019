package petstore.models.storeModelPack;

import java.util.Date;
import java.text.SimpleDateFormat;

public class StoreModel {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private String complete;

    public StoreModel(int id, int petId, int quantity, String status, String complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.status = status;
        this.complete = complete;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public int getPetId() {
        return petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getShipDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        shipDate = dateFormat.format( new Date() );
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println(shipDate);
        return shipDate;
    }

    public String getStatus() {
        return status;
    }

    public String isComplete() {
        return complete;
    }
}
