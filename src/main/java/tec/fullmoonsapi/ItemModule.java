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

    @JsonProperty("dayUntilWater")
    @Column(nullable = true)
    private Integer dayUntilWater;

    @ManyToOne
    @JoinColumn(name = "admin_login_id", nullable = false)
    private AdminLogin adminLogin; // Reference to AdminLogin

    public ItemModule() {
    }

    public ItemModule(String name, int waterNeeded, boolean isWatered, byte[] image, Integer dayUntilWater, AdminLogin adminLogin) {
        this.name = name;
        this.waterNeeded = waterNeeded;
        this.isWatered = isWatered;
        this.image = image;
        this.dayUntilWater = dayUntilWater;
        this.adminLogin = adminLogin;
    }
    public AdminLogin getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(AdminLogin adminLogin) {
        this.adminLogin = adminLogin;
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

    public Integer getDayUntilWater() {
        return dayUntilWater;
    }

    public void setDayUntilWater(Integer dayUntilWater) {
        this.dayUntilWater = dayUntilWater;
    }
}