package com.springapp.valgykla.Controller;

import com.springapp.valgykla.Model.Ingredient;
import com.springapp.valgykla.Service.DishService;
import com.springapp.valgykla.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springapp.valgykla.Model.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dish")
@CrossOrigin
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private IngredientService ingredientService;

    @PostMapping()
    public String add(@RequestBody Dish dish){
        if(!dishService.isDuplicate(dish)){
            dishService.save(dish);
            return "Naujas patiekalas idetas";
        }
        return "Naujo patiekalo duomenys netinkami";
    }

    @PatchMapping()
    public String Update(@RequestBody Dish dish)
    {
        dishService.save(dish);
        return "patiekalo duomenys atnaujinti";
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id)
    {
        Optional<Dish> optionalDish = dishService.findById(id);

        if (optionalDish.isEmpty()) {
            throw new RuntimeException("patiekalas nerastas");
        }

        return optionalDish.get();
    }

    @GetMapping()
    public List<Dish> getAll()
    {
        return dishService.getAll();
    }

    @GetMapping("/{id}/ingredients")
    public List<Ingredient> getIngredients(@PathVariable("id") int id) {
        Optional<Dish> optionalDish = dishService.findById(id);

        if (optionalDish.isEmpty()) {
            throw new RuntimeException("patiekalas nerastas");
        }

        Dish dish = optionalDish.get();
        List<Ingredient> allIngredients = ingredientService.getAll();
        List<Ingredient> dishesIngredients = new ArrayList<>();

        for (Ingredient ingredient : allIngredients) {
            if (ingredient.getDishId() == dish.getId()) {
                dishesIngredients.add(ingredient);
            }
        }

        return dishesIngredients;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        if (dishService.findById(id).isEmpty())
        {
            return "patiekalas nerastas";
        }
        dishService.Delete(id);
        return "patiekalas istrintas";
    }
}
