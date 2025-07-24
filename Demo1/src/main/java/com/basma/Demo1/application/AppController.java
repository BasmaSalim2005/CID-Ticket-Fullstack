package com.basma.Demo1.application;

import com.basma.Demo1.application.AppDTO.AppKPIsDTO;
import com.basma.Demo1.application.AppDTO.AppResponseDTO;
import com.basma.Demo1.application.AppDTO.addAppDTO;
import com.basma.Demo1.application.AppDTO.updateAppDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/demo1/applications")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @PostMapping("/add")
    public AppResponseDTO addApp(@RequestBody addAppDTO dto) {
        return appService.addApp(dto);
    }

    @GetMapping("/apps")
    public ResponseEntity<Page<Application>> getAllApps(
            @RequestParam int page,
            @RequestParam int size
    ) {
        Page<Application> result = appService.getAllApps(page, size);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/all")
    public List<Application> getAll(){
        return appService.getAll();
    }

    @GetMapping("/getbyname/{name}")
    public AppResponseDTO getAppByName(@PathVariable String name) {
        return appService.getAppByName(name);
    }

    @PutMapping("/update/{id}")
    public AppResponseDTO updateApp(@PathVariable Long id, @RequestBody updateAppDTO dto) {
        return appService.updateApp(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteApp(@PathVariable Long id) {
        appService.deleteApp(id);
    }

    @PutMapping("/activate/{name}")
    public AppResponseDTO activateApp(@PathVariable String name){return appService.ActiveApp(name);}

    @PutMapping("/disactivate/{name}")
    public AppResponseDTO disactivateApp(@PathVariable String name){return appService.DisactiveApp(name);}

    @PutMapping("/inmaintenance/{name}")
    public AppResponseDTO maintenanceApp(@PathVariable String name){return appService.MaintenanceApp(name);}

    //test
    @GetMapping("/counttotal")
    public Integer getTotalApp(){
        return appService.getTotalApp();
    }
    @GetMapping("/counttotalbydev/{email}")
    public Integer getAppByDev(@PathVariable String email){
        return appService.getAppKPIs(email);
    }
    @GetMapping("/countactive")
    public Integer getTotalActive(){
        return appService.getTotalAppAtive();
    }
    @GetMapping("/countdisactive")
    public Integer getTotalDisactive(){
        return appService.getTotalAppDisactive();
    }
    @GetMapping("/countmaintenance")
    public Integer getTotalMaintenance(){
        return appService.getTotalAppMaintemance();
    }

    @GetMapping("/byuser/{email}")
    public List<AppResponseDTO> getByUser(@PathVariable String email){
        return appService.getByUser(email);
    }

    @GetMapping("/app/by-user")
    public ResponseEntity<Page<AppResponseDTO>> getAppByUserPage(
            @RequestParam String email,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Page<AppResponseDTO> result = appService.getAppByUser(email, page, size);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/user/add")
    public AppResponseDTO addUserToApp(@RequestParam String email, @RequestParam String name){
        return appService.addUserToApp(email, name);
    }
    @GetMapping("/active/byuser/")
    public Integer getTotalActiveByUser(@RequestParam String email){
        return appService.getTotalAppByActiveForUser(email);
    }
    @GetMapping("/disactive/byuser")
    public Integer getTotalDisactiveByUser(@RequestParam String email){
        return appService.getTotalAppByDisactiveForUser(email);
    }
    @GetMapping("/maintenance/byuser")
    public Integer getTotalMaintenanceByUser(@RequestParam String email){
        return appService.getTotalAppByMaintForUser(email);
    }

//    @GetMapping("/getall")
//    public Page<Application> getAllApps(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return appService.getAllApps(page, size);
//    }
}
