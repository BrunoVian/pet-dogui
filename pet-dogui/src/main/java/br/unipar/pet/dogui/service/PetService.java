package br.unipar.pet.dogui.service;

import br.unipar.pet.dogui.model.Pet;
import br.unipar.pet.dogui.service.repository.PetRepository;

import java.util.ArrayList;

public class PetService {

    private PetRepository repository = new PetRepository();

    public Pet insert(Pet pet) throws Exception {

        validaPet(pet);

        return repository.insert(pet);

    }

    private void validaPet(Pet pet) throws Exception {

        if (pet.getNome() == null
                && pet.getNome().isEmpty()) {
            throw new Exception("Nome não informado");
        }

        if (pet.getDono() == null) {
            throw new Exception("Dono não informado");
        }

        if (pet.getGenero() == null) {
            throw new Exception("Gênero não informado");
        }

        if (pet.getPorte() == null) {
            throw new Exception("Porte não informado");
        }

    }

    public Pet update(Pet pet) throws Exception {

        validaPet(pet);

        return repository.update(pet);

    }

 public void delete(int id) throws Exception {
    
        repository.delete(id);
    
    }

    public Pet findById(int id) throws Exception {

        return repository.findById(id);

    }

    public ArrayList<Pet> findByAll() throws Exception {

        return repository.findAll();

    }

    public ArrayList<Pet> findWithParameteres(String descricao) throws Exception {

        return repository.findWithParameters(descricao);

    }
}
