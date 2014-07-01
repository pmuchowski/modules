package org.motechproject.openmrs18.resource;

import org.motechproject.openmrs18.rest.HttpException;
import org.motechproject.openmrs18.resource.model.RoleListResult;
import org.motechproject.openmrs18.resource.model.User;
import org.motechproject.openmrs18.resource.model.UserListResult;

public interface UserResource {

    UserListResult getAllUsers() throws HttpException;

    UserListResult queryForUsersByUsername(String username) throws HttpException;

    User createUser(User user) throws HttpException;

    void updateUser(User user) throws HttpException;

    RoleListResult getAllRoles() throws HttpException;
}
