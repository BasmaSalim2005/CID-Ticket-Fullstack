package com.basma.Demo1.feature;

import com.basma.Demo1.feature.FeatureDTO.AddFeatDTO;
import com.basma.Demo1.feature.FeatureDTO.FeatureResponseDTO;
import com.basma.Demo1.feature.FeatureDTO.updateFeatDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/demo1/features")
@RequiredArgsConstructor
public class FeatureController {
    private final FeatureService featService;

    @PostMapping("/add")
    protected FeatureResponseDTO addFeature(@RequestBody @Valid AddFeatDTO dto) {
        return featService.createFeature(dto);
    }

    @PutMapping("/update/{id}")
    public FeatureResponseDTO updateFeature(@PathVariable Long id, @RequestBody @Valid updateFeatDTO dto) {
        return featService.updateFeature(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFeature(@PathVariable Long id) {
        featService.deleteFeature(id);
    }

    @GetMapping("/getall")
    public List<Feature> getAll() {
        return featService.getAllFeatures();
    }

    @GetMapping("/getbyid/{id}")
    public FeatureResponseDTO getById(@PathVariable Long id) {
        return featService.getFeatureById(id);
    }

    @GetMapping("/getbyapp/")
    public List<FeatureResponseDTO> getByApp(@RequestParam String appName){return featService.getFeatureByApp(appName);}
}

