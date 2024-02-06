package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.AuthenticationAcceptedDTO;
import com.devgrp.fitnesswebapp.dto.AuthenticationDTO;
import com.devgrp.fitnesswebapp.dto.UserDTO;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.entity.types.UserType;
import com.devgrp.fitnesswebapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Optional<AuthenticationAcceptedDTO> authenticate(final AuthenticationDTO dto) {
        try {
            // Search for the user with the given email
            var user = userRepository.findUserByEmail(dto.getEmail()).orElse(null);
            if ( user == null ) return Optional.empty();

            // Check whether the password sent to us matches with the user's password
            if ( user.getPassword().equals(dto.getPassword()) ) {

                // Passwords match
                // Create an access token and send it
                // Since at the moment we haven't set up JWT yet,
                // We are just sending user's roles and id
                AuthenticationAcceptedDTO result = new AuthenticationAcceptedDTO();
                result.setUser(user.getId());
                result.setAccessToken("");

                ArrayList<UserType> roles = new ArrayList<>();
                roles.add(UserType.USER);
                if ( user.getType().equals(UserType.ADMIN)) {
                    roles.add(UserType.ADMIN);
                }

                UserType[] rolesArray = new UserType[roles.size()];
                rolesArray = roles.toArray(rolesArray);
                result.setRoles(rolesArray);

                return Optional.of(result);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return Optional.empty();
    }

    public Optional<AuthenticationAcceptedDTO> register(final UserDTO dto) {
        try {
            var user = modelMapper.map(dto, User.class);
            userRepository.save(user);


            // Search for the user with the given email
            user = userRepository.findUserByEmail(dto.getEmail()).orElse(null);
            if ( user == null ) return Optional.empty();

            // Check whether the password sent to us matches with the user's password
            if ( user.getPassword().equals(dto.getPassword()) ) {

                // Passwords match
                // Create an access token and send it
                // Since at the moment we haven't set up JWT yet,
                // We are just sending user's roles and id
                AuthenticationAcceptedDTO result = new AuthenticationAcceptedDTO();
                result.setUser(user.getId());
                result.setAccessToken("");

                ArrayList<UserType> roles = new ArrayList<>();
                roles.add(UserType.USER);
                if ( user.getType().equals(UserType.ADMIN)) {
                    roles.add(UserType.ADMIN);
                }

                UserType[] rolesArray = new UserType[roles.size()];
                rolesArray = roles.toArray(rolesArray);
                result.setRoles(rolesArray);

                return Optional.of(result);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return Optional.empty();
    }

}
