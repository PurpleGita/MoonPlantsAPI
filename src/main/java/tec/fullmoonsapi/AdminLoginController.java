package tec.fullmoonsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adminlogins")
public class AdminLoginController {

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @GetMapping
    public List<AdminLogin> getAllAdminLogins() {
        return adminLoginRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminLogin> getAdminLoginById(@PathVariable Long id) {
        Optional<AdminLogin> adminLogin = adminLoginRepository.findById(id);
        if (adminLogin.isPresent()) {
            return ResponseEntity.ok(adminLogin.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public AdminLogin createAdminLogin(@RequestBody AdminLogin adminLogin) {
        return adminLoginRepository.save(adminLogin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminLogin> updateAdminLogin(@PathVariable Long id, @RequestBody AdminLogin adminLoginDetails) {
        Optional<AdminLogin> adminLoginOptional = adminLoginRepository.findById(id);
        if (adminLoginOptional.isPresent()) {
            AdminLogin adminLogin = adminLoginOptional.get();
            adminLogin.setUsername(adminLoginDetails.getUsername());
            adminLogin.setPassword(adminLoginDetails.getPassword());
            return ResponseEntity.ok(adminLoginRepository.save(adminLogin));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminLogin(@PathVariable Long id) {
        Optional<AdminLogin> adminLoginOptional = adminLoginRepository.findById(id);
        if (adminLoginOptional.isPresent()) {
            adminLoginRepository.delete(adminLoginOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/checkIfAdminExists")
    public ResponseEntity<Boolean> checkIfAdminExists(@RequestBody AdminLogin adminLogin) {
        Optional<AdminLogin> adminLoginOptional = adminLoginRepository.findByUsernameAndPassword(adminLogin.getUsername(), adminLogin.getPassword());
        return ResponseEntity.ok(adminLoginOptional.isPresent());
    }
}