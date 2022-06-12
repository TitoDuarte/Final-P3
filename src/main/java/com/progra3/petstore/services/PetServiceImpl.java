package com.progra3.petstore.services;

import com.progra3.petstore.dao.PetDao;
import com.progra3.petstore.entities.Pet;
import com.progra3.petstore.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDao petDao;

    @Override
    public List<Pet> listAll() {
        return (List<Pet>)petDao.findAll();
    }

    @Override
    public Pet findById(Long id) {
        if(!petDao.existsById(id)) {
            throw new NotFoundException("no se encontro la mascota");
        }
        return petDao.findById(id).orElse(null);
    }

    @Override
    public Pet createPet(Pet pet) {
        return petDao.save(pet);
    }

    @Override
    public Pet updatePet(Long id, Pet pet) {
        if(!petDao.existsById(id)) {
            throw new NotFoundException("no se encontro la mascota");

        }
        Optional<Pet> updatePet = petDao.findById(id);
        updatePet.get().setId(id);
        updatePet.get().setName(pet.getName());
        updatePet.get().setPrice(pet.getPrice());
        updatePet.get().setBirthDay(pet.getBirthDay());

        return petDao.save(updatePet.get());

    }

    @Override
    public void deletePet(Long id) {
        if (!petDao.existsById(id)) {
            throw new NotFoundException("no se encontro la mascota");
        }
        petDao.deleteById(id);
    }
}
