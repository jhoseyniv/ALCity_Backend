package com.alcity.repository.appmember;

import com.alcity.service.customexception.ALCityResponseObject;

public interface CustomizedUserRepository {
    ALCityResponseObject login(String username, String password);
}
