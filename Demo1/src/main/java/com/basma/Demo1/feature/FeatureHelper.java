package com.basma.Demo1.feature;

import com.basma.Demo1.application.AppDTO.AppResponseDTO;
import com.basma.Demo1.application.AppRepository;
import com.basma.Demo1.application.AppService;
import com.basma.Demo1.application.Application;
import com.basma.Demo1.exceptions.FeatureAlreadyExistsException;
import com.basma.Demo1.exceptions.FeatureNotFoundException;
import com.basma.Demo1.feature.FeatureDTO.AddFeatDTO;
import com.basma.Demo1.feature.FeatureDTO.FeatureResponseDTO;
import com.basma.Demo1.feature.FeatureDTO.updateFeatDTO;
import com.basma.Demo1.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeatureHelper {

    protected static void displayFeatureNotFoundException(Feature existingFeature) {
        if (existingFeature == null) {
            String message = "Feature Not Found!";
            throw new FeatureNotFoundException(message);
        }
    }

    protected static void displayFeatureAlreadyExistsException(Feature newFeature) {
        if (newFeature != null) {
            String message = "Feature Already Exists!";
            throw new FeatureAlreadyExistsException(message);
        }
    }
    public static Feature mapToEntity(AddFeatDTO dto, Application app, User dev) {
        Feature feature = new Feature();
        feature.setName(dto.getName());
        feature.setDescription(dto.getDescription());
        LocalDateTime now = LocalDateTime.now();

        feature.setCreatedAt(now);
        feature.setApp(app);
        feature.setDeveloper(dev);
        return feature;
    }

    public static FeatureResponseDTO mapToResponse(Feature feature) {
        FeatureResponseDTO response = new FeatureResponseDTO();
        response.setId(feature.getId());
        response.setName(feature.getName());
        response.setDescription(feature.getDescription());
        response.setAppName(feature.getApp().getName());
        response.setCreatedAt(feature.getCreatedAt());

        if (feature.getDeveloper() != null) {
            response.setDevFname(feature.getDeveloper().getFirstname());
            response.setDevLname(feature.getDeveloper().getLastname());
        }
        return response;
    }
    public static Feature mapToEntityUpdate(updateFeatDTO dto, Feature feature) {
            feature.setName(dto.getName());
            feature.setDescription(dto.getDescription());
            feature.setUpdatedAt(LocalDateTime.now());
        return feature;
    }

    protected static List<FeatureResponseDTO> mapToResponse(List<Feature> feats){
        List<FeatureResponseDTO> formattedFeats =new ArrayList<>();
        for (int i=0 ; i<feats.size(); i++) {
            FeatureResponseDTO response = mapToResponse(feats.get(i));
            formattedFeats.add(response);
        }
        return formattedFeats;
    }

    public static FeatureResponseDTO mapToResponseUpdate(Feature feature) {
        FeatureResponseDTO response = new FeatureResponseDTO();
        response.setName(feature.getName());

        response.setAppName(feature.getApp().getName());
        response.setId(feature.getId());
        response.setDescription(feature.getDescription());
        response.setUpdatedAT(feature.getUpdatedAt());
        response.setCreatedAt(feature.getCreatedAt());
        return response;
    }
}
