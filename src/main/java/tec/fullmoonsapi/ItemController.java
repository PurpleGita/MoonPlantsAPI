package tec.fullmoonsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<ItemModule> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemModule> getItemById(@PathVariable Long id) {
        Optional<ItemModule> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/admin/{adminId}")
    public List<ItemModule> getItemsByAdminLogin(@PathVariable Long adminId) {
        return itemRepository.findAll().stream()
                .filter(item -> item.getAdminLogin() != null && item.getAdminLogin().getId().equals(adminId))
                .toList();
    }

    @PostMapping
    public ItemModule createItem(@RequestBody ItemModule item) {
        // Ensure the adminLogin is valid before saving
        if (item.getAdminLogin() == null || item.getAdminLogin().getId() == null) {
            throw new IllegalArgumentException("AdminLogin must be provided for the item.");
        }
        return itemRepository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemModule> updateItem(@PathVariable Long id, @RequestBody ItemModule itemDetails) {
        Optional<ItemModule> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            ItemModule item = itemOptional.get();
            item.setName(itemDetails.getName());
            item.setWaterNeeded(itemDetails.getWaterNeeded());
            item.setWatered(itemDetails.isWatered());
            item.setImage(itemDetails.getImage());
            item.setDayUntilWater(itemDetails.getDayUntilWater());
            item.setAdminLogin(itemDetails.getAdminLogin()); // Update the adminLogin
            return ResponseEntity.ok(itemRepository.save(item));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/dayPassed")
    public ResponseEntity<Void> dayPassed() {
        List<ItemModule> items = itemRepository.findAll();
        for (ItemModule item : items) {
            item.setWatered(false);
            if (item.getDayUntilWater() > 0) {
                item.setDayUntilWater(item.getDayUntilWater() - 1);
            }
        }
        itemRepository.saveAll(items);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        Optional<ItemModule> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            itemRepository.delete(itemOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}