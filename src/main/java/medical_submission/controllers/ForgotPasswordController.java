package medical_submission.controllers;

import medical_submission.configs.Utility;
import medical_submission.exception.UserNotFoundException;
import medical_submission.models.User;
import medical_submission.payload.response.MessageResponse;
import medical_submission.service.UserService;
import net.bytebuddy.utility.RandomString;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping("/resetPassword")
public class ForgotPasswordController {

        @Autowired
        private JavaMailSender mailSender;

        @Autowired
        private UserService userService;

        @GetMapping("/forgot_password")
        public String showForgotPasswordForm() {
            return "forgot_password_form";
        }

    @PostMapping("/forgotPasswordSendMail")
    public ResponseEntity<MessageResponse> processForgotPassword(HttpServletRequest request, Model model) {

        String email = request.getParameter("email");
        String token = RandomString.make(30);
        String message = "";

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/resetPassword/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
        message = "Reset Mail Sent successfully,Check Your Mail";
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@shopme.com", "USSL");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/reset_password")
    public ResponseEntity showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        String message = "";
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            message = "message";
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:8081/resetpasswordpage/" + token);
        return new ResponseEntity(headers, HttpStatus.FOUND);
    }


    @PostMapping("/reset_password")
    public ResponseEntity processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String message = "";

        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

//        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
//
//        } else {

            userService.updatePassword(user, password);
            message = "You have successfully changed your password.";
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));

//            model.addAttribute("message", "You have successfully changed your password.");

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Location", "http://localhost:8081");
//        return new ResponseEntity(headers, HttpStatus.FOUND);
    }

}
