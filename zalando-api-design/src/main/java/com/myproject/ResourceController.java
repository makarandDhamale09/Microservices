package com.myproject;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @GetMapping("/resources")
    public List<Resource> getAllResources() {
        return service.getResources();
    }

    @PostMapping("/resources")
    public Resource addResource(@RequestBody Resource resource) {
        Resource addedResource = service.addResource(resource);
        return addedResource;
    }

    @PutMapping("/resources/{id}")
    public Resource updateResource(){
        return null;
    }
}
