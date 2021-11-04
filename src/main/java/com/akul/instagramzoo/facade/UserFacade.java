package com.akul.instagramzoo.facade;

import com.akul.instagramzoo.dto.UserDTO;
import com.akul.instagramzoo.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserFacade {

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getName());
        userDTO.setFirstname(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setBio(user.getBio());
        return userDTO;
    }
}
