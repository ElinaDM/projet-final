package co.simplon.exercise.web;

import co.simplon.exercise.core.model.Mail;
import co.simplon.exercise.core.service.MailService;
import co.simplon.exercise.core.service.UserService;
import co.simplon.exercise.core.service.mailing.EmailAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Autowired
    EmailAPI emailAPI;

    @RequestMapping
    public ModelAndView getMailList(ModelMap model){
        List<Mail>mailList=mailService.getAll();
        model.addAttribute("mailList",mailList);
        return new ModelAndView("mail/contact-form",model);
    }

    /**
     * Display the form to send a mail
     *
     * @return
     */
    @RequestMapping(value = "/formSend", method = RequestMethod.GET)
    public ModelAndView getFormSendMail(ModelMap model) {

        return new ModelAndView("/mail/send-mail", model);

    }

    @RequestMapping(path="/sendAndSaveMail")
    public ModelAndView sendAndSaveMail (@RequestParam ("msgBody") String msgBody, @RequestParam String subject  ){
        String actualFromAdress = SecurityContextHolder.getContext().getAuthentication().getName();
        String toAdress="simplon.company@gmail.com";
        Date createdAt = new Date();
        Mail mail = new Mail(actualFromAdress,createdAt, 0, null, subject, msgBody);
        mailService.addOrUpdate(mail);
        emailAPI.sendEmail(toAdress,actualFromAdress, subject, msgBody);
        return new ModelAndView("mail/done-mail");
    }
}