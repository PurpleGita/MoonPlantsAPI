package tec.fullmoonsapi;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ItemModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonProperty("waterNeeded")
    private int waterNeeded;

    @JsonProperty("isWatered")
    private boolean isWatered;

    @Lob
    @JsonProperty("image")
    private byte[] image;

    public ItemModule() {
    }

    public ItemModule(String name, int waterNeeded, boolean isWatered, byte[] image) {
        this.name = name;
        this.waterNeeded = waterNeeded;
        this.isWatered = isWatered;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public void setWaterNeeded(int waterNeeded) {
        this.waterNeeded = waterNeeded;
    }

    public boolean isWatered() {
        return isWatered;
    }

    public void setWatered(boolean watered) {
        isWatered = watered;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}