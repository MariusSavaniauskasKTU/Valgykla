package com.springapp.valgykla.Service;


import com.springapp.valgykla.Model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    public Ingredient save(Ingredient ingredient);
    public List<Ingredient> getAll();
    Optional< Ingredient > findById(int id);
    public void Delete(int id);
    public boolean isDuplicate(Ingredient ingredient);
}
