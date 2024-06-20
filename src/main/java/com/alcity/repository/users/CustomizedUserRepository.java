package com.alcity.repository.users;

import com.alcity.customexception.ALCityResponseObject;

public interface CustomizedUserRepository {
    ALCityResponseObject login(String username, String password);
}
