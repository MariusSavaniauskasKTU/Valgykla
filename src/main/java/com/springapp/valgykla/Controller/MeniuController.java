package com.springapp.valgykla.Controller;


import com.springapp.valgykla.Model.Dish;
import com.springapp.valgykla.Model.Meniu;
import com.springapp.valgykla.Service.DishService;
import com.springapp.valgykla.Service.MeniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meniu")
public class MeniuController {

    @Autowired
    private MeniuService meniuService;

    @Autowired
    private DishService dishService;

    @PostMapping()
    public String add(@RequestBody Meniu meniu){
        if(!meniuService.isDuplicate(meniu)){
            meniuService.save(meniu);
            return "Naujas Meniu idetas";
        }
        return "Naujo Meniu duomenys netinkami";
    }

    @PatchMapping()
    public String Update(@RequestBody Meniu meniu)
    {
        meniuService.save(meniu);
        return "Meniu duomenys atnaujinti";
    }

    @GetMapping("/{id}")
    public Meniu get(@PathVariable("id") int id)
    {
        Optional<Meniu> optionalDish = meniuService.findById(id);

        if (optionalDish.isEmpty()) {
            throw new RuntimeException("Meniu nerastas");
        }

        return optionalDish.get();
    }

    @GetMapping()
    public List<Meniu> getAll()
    {
        return meniuService.getAll();
    }

    @GetMapping("/{id}/ingredients")
    public List<Dish> getIngredients(@PathVariable("id") int id) {
        Optional<Meniu> optionalMeniu = meniuService.findById(id);

        if (optionalMeniu.isEmpty()) {
            throw new RuntimeException("Meniu nerastas");
        }

        Meniu meniu = optionalMeniu.get();
        List<Dish> allDishes = dishService.getAll();
        List<Dish> dishesIngredients = new ArrayList<>();

        for (Dish dish : allDishes) {
            if (dish.getMeniuId() == meniu.getId()) {
                dishesIngredients.add(dish);
            }
        }

        return dishesIngredients;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        if (meniuService.findById(id).isEmpty())
        {
            return "Meniu nerastas";
        }
        meniuService.Delete(id);
        return "Meniu istrintas";
    }
}
