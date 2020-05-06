package com.project;

import java.util.List;

import com.project.entity.User;
import com.project.entity.UserCredits;
import com.project.entity.UserDeposits;
import com.project.repository.UserCreditsRepository;
import com.project.repository.UserDepositsRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> listUsers() {
        return repository.findAll();
    }

    public void save(User user) {
        repository.save(user);
    }

    public User get(long id) {
        return repository.getOne(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    @Autowired
    UserCreditsRepository ucRepository;

    public List<UserCredits> listUsersCredits() {
        return ucRepository.findAll();
    }

    public void saveUserCredits(UserCredits userCredits) {
        ucRepository.save(userCredits);
    }

    public UserCredits getUserCredits(long id) {
        return ucRepository.getOne(id);
    }

    public void deleteUserCredits(long id) {
        ucRepository.deleteById(id);
    }

    @Autowired
    UserDepositsRepository udRepository;

    public List<UserDeposits> listUserDeposits() {
        return udRepository.findAll();
    }

    public void saveUserDeposits(UserDeposits userDeposits) {
        udRepository.save(userDeposits);
    }

    public UserDeposits getUserDeposits(long id) {
        return udRepository.getOne(id);
    }

    public void deleteUserDeposits(long id) {
        udRepository.deleteById(id);
    }

}
