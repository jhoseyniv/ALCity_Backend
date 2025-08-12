package com.alcity.repository.appmember;

import com.alcity.customexception.ResponseObject;

public interface CustomizedUserRepository {
    ResponseObject login(String username, String password);
}
