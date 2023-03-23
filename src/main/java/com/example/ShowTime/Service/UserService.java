package com.example.ShowTime.Service;

import com.example.ShowTime.Convertors.UserEntryConverter;
import com.example.ShowTime.Entities.UserEntity;
import com.example.ShowTime.EntryDtos.UserEntryDto;
import com.example.ShowTime.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String addUser(UserEntryDto userEntityDto) {
        UserEntity user = UserEntryConverter.entryConverter(userEntityDto);
        userRepository.save(user);
        return "User Added Successfully";
    }
}