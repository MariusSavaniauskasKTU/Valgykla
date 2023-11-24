package com.springapp.valgykla.Controller;

import com.springapp.valgykla.Model.Ingredient;
import com.springapp.valgykla.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping()
    public String add(@RequestBody Ingredient ingredient){
        if(!ingredientService.isDuplicate(ingredient)){
            ingredientService.save(ingredient);
            return "Naujas ingridientas idetas";
        }
        return "Naujo ingridiento duomenys netinkami";
    }

    @PatchMapping()
    public String Update(@RequestBody Ingredient ingredient)
    {
        ingredientService.save(ingredient);
        return "Ingridiento duomenys atnaujinti";
    }

    @GetMapping("/{id}")
    public Ingredient get(@PathVariable("id") int id)
    {
        Optional<Ingredient> optionalIngredient = ingredientService.findById(id);

        if (optionalIngredient.isEmpty()) {
            throw new RuntimeException("Ingredientas nerastas");
        }

        return optionalIngredient.get();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        if (ingredientService.findById(id).isEmpty())
        {
            return "Ingridientas nerastas";
        }
        ingredientService.Delete(id);
        return "Ingridientas istrintas";
    }

    @GetMapping()
    public List<Ingredient> getAll()
    {
        return ingredientService.getAll();
    }
}
