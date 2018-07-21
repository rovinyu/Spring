package com.rovin.jpa;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    @ApiIgnore
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username")String username) {
        return userService.findUserByName(username);
    }
    */

    @ApiOperation(value="UserList", notes="User List")
    @RequestMapping(value={""}, method=RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users = userService.findAll();
        return users;
    }

    @ApiOperation(value="CreateUser", notes="Create a new user")
    @RequestMapping(value="", method=RequestMethod.POST)
    public User postUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @ApiOperation(value="GetUser", notes="Get one user information")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @ApiOperation(value="UpdateUser", notes="Update one user's information by id")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public User putUser(@PathVariable Long id, @RequestBody User user) {
        User tmpUser = new User();
        tmpUser.setUsername(user.getUsername());
        tmpUser.setPassword(user.getPassword());
        tmpUser.setId(id);
        return userService.updateUser(tmpUser);
    }

    @ApiOperation(value="DeleteUser", notes="Delete one user")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "success";
    }
}
