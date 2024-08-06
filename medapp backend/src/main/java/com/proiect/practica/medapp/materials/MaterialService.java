package com.proiect.practica.medapp.materials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialsRepository;

    public List<Material> getAllMaterials() {
        return materialsRepository.findAll();
    }

    public Optional<Material> getMaterialById(Integer id) {
        return materialsRepository.findById(id);
    }

    public Material saveMaterial(Material material) {
        return materialsRepository.save(material);
    }

    public void deleteMaterial(Integer id) {
        materialsRepository.deleteById(id);
    }
}
