package com.springapp.valgykla.Service;

import com.springapp.valgykla.Model.Ingredient;
import com.springapp.valgykla.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findById(int id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public void Delete(int id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public boolean isDuplicate(Ingredient ingredient) {
        List<Ingredient> ingredients = getAll();
        for (Ingredient ing : ingredients) {
            if(Objects.equals(ing.getQuantity(), ingredient.getQuantity())
                    && Objects.equals(ing.getCalories(), ingredient.getCalories())
                    && ing.getDishId() == ingredient.getDishId()
                    && ing.getName().equals(ingredient.getName())
                    && ing.getType().equals(ingredient.getType()))  {
                return true;
            }
        }
        return false;
    }
}
