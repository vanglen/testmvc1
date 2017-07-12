package blog.web;

import blog.data.AccountRepository;
import blog.ex.AccountException;
import blog.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("account", new Account());
        return "account/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid Account account, Errors errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            return "redirect:account/register";
        }
        attributes.addFlashAttribute(account);
        accountRepository.register(account);
        return "redirect:/account/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "/account/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Account account) {
        if (account.getName().equals("van") && !account.getPwd().equals("van")) {
            throw new AccountException();
        }
        accountRepository.login(account);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public Account show(@PathVariable int id) {
        Account account = new Account();
        account.setId(1);
        account.setName("abc");
        return account;
    }

    /**
     * show2
     *
     * @param name name
     * @return return Account
     */
    @ResponseBody
    @RequestMapping(value = "/show2/{name}", method = RequestMethod.GET)
    public Account show2(@PathVariable String name) {
        Account account = new Account();
        account.setId(2);
        account.setName("abc");
        return account;
    }
}
