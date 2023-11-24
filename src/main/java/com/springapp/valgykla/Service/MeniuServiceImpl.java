package com.springapp.valgykla.Service;

import com.springapp.valgykla.Model.Meniu;
import com.springapp.valgykla.Repository.MeniuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeniuServiceImpl implements MeniuService{

    @Autowired
    private MeniuRepository meniuRepository;

    @Override
    public Meniu save(Meniu meniu) {
        return meniuRepository.save(meniu);
    }

    @Override
    public List<Meniu> getAll() {
        return meniuRepository.findAll();
    }

    @Override
    public Optional<Meniu> findById(int id) {
        return meniuRepository.findById(id);
    }

    @Override
    public void Delete(int id) {
        meniuRepository.deleteById(id);
    }

    @Override
    public boolean isDuplicate(Meniu meniu) {
        List<Meniu> menius = getAll();
        for (Meniu men : menius) {
            if (men.getDate().equals(meniu.getDate())
                && men.getType().equals(meniu.getType())) {
                return true;
            }
        }
        return false;
    }
}
