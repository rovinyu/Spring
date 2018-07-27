package com.rovin.jpa;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    @ApiIgnore
    @GetMapping("/{username}")
    public User getUserName(@PathVariable("username")String username) {
        return userService.findUserByName(username);
    }
    */

    @ApiOperation(value="UserList", notes="User List")
    @RequestMapping(value={"/all"}, method=RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users = userService.findAll();
        return users;
    }

    @ApiIgnore
    @GetMapping
    public ModelAndView list(Model model) {
        model.addAttribute("userList", this.getUsers());
        model.addAttribute("title", "UserManagement");
        return new ModelAndView("users/list", "userModel", model);
    }

    //@ApiOperation(value="CreateUser", notes="Create a new user")
    //@RequestMapping(value="", method=RequestMethod.POST)
    public User postUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @ApiIgnore
    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "CreateUser");
        return new ModelAndView("users/form", "userModel", model);
    }

    //@ApiOperation(value="GetUser", notes="Get one user information")
    //@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @ApiIgnore
    @GetMapping("{id}")
    public ModelAndView getUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", this.getUser(id));
        model.addAttribute("title", "ViewUser");
        return new ModelAndView("users/view", "userModel", model);
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

    @ApiIgnore
    @PostMapping
    public ModelAndView putUser(User user) {
        user = this.postUser(user);
        return new ModelAndView("redirect:/users");
    }

    @ApiOperation(value="DeleteUser", notes="Delete one user")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "success";
    }

    @ApiIgnore
    @GetMapping(value = "delete/{id}")
    public ModelAndView delete (@PathVariable Long id) {
        this.deleteUser(id);
        return new ModelAndView("redirect:/users");
    }

    @ApiIgnore
    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable Long id, Model model) {
        User user = this.getUser(id);

        model.addAttribute("user", user);
        model.addAttribute("title", "UpdateUser");
        return new ModelAndView("users/form", "userModel", model);
    }
}
