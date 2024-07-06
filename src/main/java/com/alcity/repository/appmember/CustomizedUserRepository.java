package com.alcity.repository.appmember;

import com.alcity.customexception.ALCityResponseObject;

public interface CustomizedUserRepository {
    ALCityResponseObject login(String username, String password);
}
