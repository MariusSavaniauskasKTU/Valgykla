package com.springapp.valgykla.Service;


import com.springapp.valgykla.Model.Dish;
import com.springapp.valgykla.Repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService{

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Optional<Dish> findById(int id) {
        return dishRepository.findById(id);
    }

    @Override
    public void Delete(int id) {
        dishRepository.deleteById(id);
    }

    @Override
    public boolean isDuplicate(Dish dish) {
        List<Dish> dishes = getAll();
        for (Dish dis : dishes) {
            if(dis.getName().equals(dish.getName())
                && dis.getMeniuId() == dish.getMeniuId()
                && dis.getQuantity() == dis.getQuantity()){
                return true;
            }
        }
        return false;
    }
}
