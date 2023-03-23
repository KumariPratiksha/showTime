package com.example.ShowTime.Convertors;

import com.example.ShowTime.Entities.UserEntity;
import com.example.ShowTime.EntryDtos.UserEntryDto;

public class UserEntryConverter {
    public static UserEntity entryConverter(UserEntryDto userEntityDto) {
        return UserEntity.builder().address(userEntityDto.getAddress()).age(userEntityDto.getAge()).mobNo(userEntityDto.getMobNo()).email(userEntityDto.getEmail()).userName(userEntityDto.getUserName()).build();
    }
}