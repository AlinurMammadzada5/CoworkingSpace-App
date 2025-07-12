package com.example.demo.service;

import com.example.demo.entity.Spaces;
import com.example.demo.repo.SpacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceService {

    private final SpacesRepository repository;

    @Autowired
    public SpaceService(SpacesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void addSpace() {
        Spaces space = new Spaces();
        space.setReserved(false);
        space.setUsername("-");
        repository.save(space);
    }

    @Transactional
    public void deleteSpace(int id) {
        repository.deleteById(id);
    }

    public List<Spaces> getAllSpaces() {
        return repository.findAll();
    }

    public List<Spaces> getAvailableSpaces() {
        return repository.findAvailableSpaces();
    }

    @Transactional
    public String reserveSpace(int id, String username) {
        long userReservations = repository.countByUsername(username);
        if (userReservations >= 2) {
            return "Maximum reservations reached.";
        }

        Optional<Spaces> optSpace = repository.findById(id);
        if (optSpace.isEmpty()) {
            return "Space not found.";
        }

        Spaces space = optSpace.get();
        if (space.isReserved()) {
            return "Space already reserved.";
        }

        space.setReserved(true);
        space.setUsername(username);
        repository.update(space);
        return "Reserved!";
    }

    @Transactional
    public String cancelReservation(int id, String username) {
        Optional<Spaces> optSpace = repository.findById(id);
        if (optSpace.isEmpty()) {
            return "Space not found.";
        }

        Spaces space = optSpace.get();

        if (space.isReserved() && username.equals(space.getUsername())) {
            space.setReserved(false);
            space.setUsername("-");
            repository.update(space);
            return "Reservation cancelled.";
        }
        return "No reservation found for user.";
    }

    public List<Spaces> myReservedSpaces(String username) {
        return repository.findByUsername(username);
    }
}
