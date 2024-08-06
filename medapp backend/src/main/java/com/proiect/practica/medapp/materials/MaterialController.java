package com.proiect.practica.medapp.materials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialsService;

    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterials() {
        List<Material> materials = materialsService.getAllMaterials();
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Integer id) {
        Optional<Material> material = materialsService.getMaterialById(id);
        return material.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        Material savedMaterial = materialsService.saveMaterial(material);
        return new ResponseEntity<>(savedMaterial, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable Integer id, @RequestBody Material materialDetails) {
        Optional<Material> existingMaterial = materialsService.getMaterialById(id);

        if (existingMaterial.isPresent()) {
            Material material = existingMaterial.get();
            material.setTitle(materialDetails.getTitle());
            material.setDescription(materialDetails.getDescription());
            material.setUrl(materialDetails.getUrl());
            Material updatedMaterial = materialsService.saveMaterial(material);
            return new ResponseEntity<>(updatedMaterial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMaterial(@PathVariable Integer id) {
        materialsService.deleteMaterial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
