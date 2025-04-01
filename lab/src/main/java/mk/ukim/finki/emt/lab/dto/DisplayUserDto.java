package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enumerations.Role;

public record DisplayUserDto(String username, Role role) {
    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(user.getUsername(), user.getRole());
    }

    public User toUser(){
        return new User(username, role.name());
    }
}
