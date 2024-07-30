package com.proiect.practica.medapp.materials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialsService {

    @Autowired
    private MaterialsRepository materialsRepository;

    public List<Materials> getAllMaterials() {
        return materialsRepository.findAll();
    }

    public Optional<Materials> getMaterialById(Integer id) {
        return materialsRepository.findById(id);
    }

    public Materials saveMaterial(Materials material) {
        return materialsRepository.save(material);
    }

    public void deleteMaterial(Integer id) {
        materialsRepository.deleteById(id);
    }
}
