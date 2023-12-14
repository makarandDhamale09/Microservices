package com.myproject;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ResourceService {

    List<Resource> resources = new ArrayList<>();

    public List<Resource> getResources() {
        return resources;
    }

    public Resource addResource(Resource resource) {
        resource.setId(UUID.randomUUID());
        resources.add(resource);
        return resource;
    }
}
