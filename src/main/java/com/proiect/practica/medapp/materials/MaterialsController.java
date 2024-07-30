package com.proiect.practica.medapp.materials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class MaterialsController {

    @Autowired
    private MaterialsService materialsService;

    @GetMapping
    public ResponseEntity<List<Materials>> getAllMaterials() {
        List<Materials> materials = materialsService.getAllMaterials();
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materials> getMaterialById(@PathVariable Integer id) {
        Optional<Materials> material = materialsService.getMaterialById(id);
        return material.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Materials> createMaterial(@RequestBody Materials material) {
        Materials savedMaterial = materialsService.saveMaterial(material);
        return new ResponseEntity<>(savedMaterial, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materials> updateMaterial(@PathVariable Integer id, @RequestBody Materials materialDetails) {
        Optional<Materials> existingMaterial = materialsService.getMaterialById(id);

        if (existingMaterial.isPresent()) {
            Materials material = existingMaterial.get();
            material.setTitle(materialDetails.getTitle());
            material.setDescription(materialDetails.getDescription());
            material.setUrl(materialDetails.getUrl());
            Materials updatedMaterial = materialsService.saveMaterial(material);
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
