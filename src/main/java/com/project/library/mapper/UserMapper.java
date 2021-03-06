package com.project.library.mapper;
import com.project.library.controller.Exception.BookNotFoundException;
import com.project.library.controller.Exception.UserNotFoundException;
import com.project.library.domain.User;
import com.project.library.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private BookMapper bookMapper;

    public User mapToUser(final UserDto userDto) throws BookNotFoundException, UserNotFoundException {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLastName(),
                userDto.getCreatedAccount(),
                bookMapper.mapToBookHireList(userDto.getBookHires())
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getCreatedAccount(),
                bookMapper.mapToBookHireDtoList(user.getBooksHire())
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
