package br.com.diego.learningspring.controller;

import br.com.diego.learningspring.dto.CreateDepositDto;
import br.com.diego.learningspring.dto.UserDto;
import br.com.diego.learningspring.model.User;
import br.com.diego.learningspring.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<User> createUser(
    @Valid @RequestBody final UserDto userData
  ) {
    final User createUser = userService.createUser(userData);

    return new ResponseEntity<User>(createUser, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<User>> readUsers() {
    final List<User> allUsers = userService.readUsers();

    return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> retrieveUser(@PathVariable String id) {
    final User user = userService.retrieveUser(Long.parseLong(id));

    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(
    @Valid @RequestBody final UserDto userData,
    @PathVariable String id
  ) {
    final User user = userService.updateUser(userData, Long.parseLong(id));

    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    userService.deleteUser(Long.parseLong(id));

    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/{id}/deposit")
  public ResponseEntity<User> updateUser(
    @Valid @RequestBody final CreateDepositDto depositData,
    @PathVariable String id
  ) {
    final User user = userService.createDeposit(
      depositData,
      Long.parseLong(id)
    );

    return new ResponseEntity<User>(user, HttpStatus.CREATED);
  }
}
