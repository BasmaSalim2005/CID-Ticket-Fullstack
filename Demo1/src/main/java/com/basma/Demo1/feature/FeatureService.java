package com.basma.Demo1.feature;

import com.basma.Demo1.application.AppRepository;
import com.basma.Demo1.application.Application;
import com.basma.Demo1.feature.FeatureDTO.AddFeatDTO;
import com.basma.Demo1.feature.FeatureDTO.AppFeatureDTO;
import com.basma.Demo1.feature.FeatureDTO.FeatureResponseDTO;
import com.basma.Demo1.feature.FeatureDTO.updateFeatDTO;
import com.basma.Demo1.notifications.smtp.EmailService;
import com.basma.Demo1.user.User;
import com.basma.Demo1.user.UserHelper;
import com.basma.Demo1.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeatureService {
     private final FeatureRepository featureRepository;
     private final AppRepository applicationRepository;
     private final EmailService emailService;
     private  final UserService userservice;
// fix feature add mapper
     public FeatureResponseDTO createFeature(AddFeatDTO dto) {
         User dev = userservice.GetUserByEmail(dto.getDevEmail());
         Application app = applicationRepository.findById(dto.getAppId())
                 .orElseThrow(() -> new RuntimeException("Application not found"));
         Feature feature = FeatureHelper.mapToEntity(dto, app, dev);
         featureRepository.save(feature);
         // Send notification email
         emailService.sendSimpleEmail(
                 "basmanasma2005@gmail.com",
             "Feature Added",
             "A new feature '" + feature.getName() + "' has been added."
         );
         return FeatureHelper.mapToResponse(feature);
     }

     public List<Feature> getAllFeatures() {
         return featureRepository.findAll();
     }

     public FeatureResponseDTO getFeatureById(Long id) {
         Feature feature = featureRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Feature not found"));
         return FeatureHelper.mapToResponse(feature);
     }
    public Feature getFeatById(Long id) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        return feature;
    }

     public List<FeatureResponseDTO> getFeatureByApp(String appName){
         Optional<Application> app = applicationRepository.findByName(appName);
         List<Feature> featuresForApp = featureRepository.findByAppId(app.get().getId());
         return FeatureHelper.mapToResponse(featuresForApp);
     }

     public FeatureResponseDTO updateFeature(Long id, updateFeatDTO dto) {
         Feature feature = featureRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Feature not found"));


         Feature feat = FeatureHelper.mapToEntityUpdate(dto, feature);
         featureRepository.save(feat);
         // Send notification email
         emailService.sendSimpleEmail(
                 "basmanasma2005@gmail.com",
             "Feature Updated",
             "Feature '" + feature.getName() + "' has been updated."
         );
         return FeatureHelper.mapToResponseUpdate(feat);
     }
     public void deleteFeature(Long id) {
         featureRepository.deleteById(id);
         // Send notification email
         emailService.sendSimpleEmail(
                 "basmanasma2005@gmail.com",
             "Feature Deleted",
             "Feature with ID " + id + " has been deleted."
         );
     }


}
