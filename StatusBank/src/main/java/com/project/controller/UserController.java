package com.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.project.UserService;
import com.project.entity.User;
import com.project.entity.UserCredits;
import com.project.entity.UserDeposits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/")
    public String viewWelcomePage() {

        return "index";
    }

    @RequestMapping(value = "accounts")
    public String viewAccountsPage() {

        return "accounts_plan";
    }

    @RequestMapping("/home")
    public String viewHomePage(Model model) {
        List<User> listUsers = service.listUsers();
        model.addAttribute("listUsers", listUsers);

        return "home";
    }

    @RequestMapping("/personal/{id}")
    public ModelAndView viewPersonalPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("personal_page");
        User user = service.get(id);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/new")
    public String showNewUserPage(Model model) {
        User user = new User();
        user.setCreditdate("No credit");
        user.setCreditvalue(0);
        model.addAttribute("user", user);

        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        service.save(user);

        return "redirect:/home";
    }

    @RequestMapping(value = "/deletecredit", method = RequestMethod.POST)
    public String deleteCredit(@ModelAttribute("user") User user) {
        user.setBalance(user.getBalance() - ((user.getCreditvalue() * (user.getCreditpercent() / 100)) + user.getCreditvalue()));
        user.setCreditdate("No credit");
        user.setCreditvalue(0);
        user.setCreditpercent(0);
        user.setCreditname("No credit");
        user.setTreatynumbercredit("No credit");
        service.save(user);

        return "redirect:/personal/" + user.getId();
    }

    @RequestMapping(value = "/deletedeposit", method = RequestMethod.POST)
    public String deleteDeposit(@ModelAttribute("user") User user) {
        user.setBalance(user.getBalance() + ((user.getDepositvalue() * (user.getDepositpercent() / 100)) + user.getDepositvalue()));
        user.setDepositdate("No deposit");
        user.setDepositvalue(0);
        user.setDepositpercent(0);
        user.setDepositname("No deposit");
        user.setTreatynumberdeposit("No deposit");
        service.save(user);

        return "redirect:/personal/" + user.getId();
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = service.get(id);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/home";
    }

    @RequestMapping("/addcredit/{id}")
    public ModelAndView addCredit(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("add_credit");
        User user = service.get(id);
        mav.addObject("user", user);
        List<UserCredits> userCredits = service.listUsersCredits();
        mav.addObject("userCredits", userCredits);

        return mav;
    }

    @RequestMapping(value = "/savecredit/{id}", method = RequestMethod.POST)
    public String saveCredit(@ModelAttribute("user") User user, @PathVariable(name = "id") int id) {
        UserCredits userCredits = service.getUserCredits(id);
        user.setCreditpercent(userCredits.getPercent());
        user.setCreditname(userCredits.getName());
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        user.setCreditdate(currentDate);
        service.save(user);

        return "redirect:/personal/" + user.getId();
    }

    @RequestMapping("/adddeposit/{id}")
    public ModelAndView addDeposit(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("add_deposit");
        User user = service.get(id);
        mav.addObject("user", user);
        List<UserDeposits> userDeposits = service.listUserDeposits();
        mav.addObject("userDeposits", userDeposits);

        return mav;
    }

    @RequestMapping(value = "/savedeposit/{id}", method = RequestMethod.POST)
    public String saveDeposit(@ModelAttribute("user") User user, @PathVariable(name = "id") int id) {
        UserDeposits userDeposits = service.getUserDeposits(id);
        user.setDepositpercent(userDeposits.getPercent());
        user.setDepositname(userDeposits.getName());
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        user.setDepositdate(currentDate);
        service.save(user);

        return "redirect:/personal/" + user.getId();
    }

    @RequestMapping("/showcredits")
    public ModelAndView showCredits() {
        ModelAndView mav = new ModelAndView("view_credits");
        List<UserCredits> userCreditsList = service.listUsersCredits();
        mav.addObject("userCreditsList", userCreditsList);

        return mav;
    }

    @RequestMapping("/showdeposits")
    public ModelAndView showDeposits() {
        ModelAndView mav = new ModelAndView("view_deposits");
        List<UserDeposits> userDepositsList = service.listUserDeposits();
        mav.addObject("userDepositsList", userDepositsList);

        return mav;
    }
}
