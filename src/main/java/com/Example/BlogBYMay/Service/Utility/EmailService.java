package com.example.BlogBYMay.Service.Utility;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    @Autowired
    private final JavaMailSender javaMailSender;

    public void sendEmail(String to,String subject, String text){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    public void sendStandardEmail(String to,String subject, String text) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text,true);

        javaMailSender.send(mimeMessage);
    }

    public String registerEmail(String userName){
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>Welcome to Our Blog!</title>" +
                "    <style>" +
                "        body {" +
                "            font-family: 'Inter', sans-serif;" +
                "            background-color: #f4f4f4;" +
                "            margin: 0;" +
                "            padding: 0;" +
                "            -webkit-text-size-adjust: 100%;" +
                "            -ms-text-size-adjust: 100%;" +
                "        }" +
                "        .container {" +
                "            max-width: 600px;" +
                "            margin: 20px auto;" +
                "            background-color: #ffffff;" +
                "            border-radius: 12px;" +
                "            overflow: hidden;" +
                "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);" +
                "        }" +
                "        .header {" +
                "            background-color: #4F46E5;" +
                "            color: #ffffff;" +
                "            padding: 30px 20px;" +
                "            text-align: center;" +
                "            border-top-left-radius: 12px;" +
                "            border-top-right-radius: 12px;" +
                "        }" +
                "        .header h1 {" +
                "            margin: 0;" +
                "            font-size: 28px;" +
                "            font-weight: 700;" +
                "        }" +
                "        .content {" +
                "            padding: 30px 20px;" +
                "            color: #333333;" +
                "            line-height: 1.6;" +
                "        }" +
                "        .content p {" +
                "            margin-bottom: 15px;" +
                "        }" +
                "        .button-container {" +
                "            text-align: center;" +
                "            padding: 20px;" +
                "        }" +
                "        .button {" +
                "            display: inline-block;" +
                "            background-color: #4F46E5;" +
                "            color: #ffffff;" +
                "            padding: 12px 25px;" +
                "            border-radius: 8px;" +
                "            text-decoration: none;" +
                "            font-weight: 600;" +
                "            font-size: 16px;" +
                "            transition: background-color 0.3s ease;" +
                "        }" +
                "        .button:hover {" +
                "            background-color: #6366F1;" +
                "        }" +
                "        .footer {" +
                "            background-color: #f0f0f0;" +
                "            color: #777777;" +
                "            text-align: center;" +
                "            padding: 20px;" +
                "            font-size: 12px;" +
                "            border-bottom-left-radius: 12px;" +
                "            border-bottom-right-radius: 12px;" +
                "        }" +
                "        .footer a {" +
                "            color: #4F46E5;" +
                "            text-decoration: none;" +
                "        }" +
                "        @media only screen and (max-width: 600px) {" +
                "            .container {" +
                "                width: 100%;" +
                "                margin: 0;" +
                "                border-radius: 0;" +
                "            }" +
                "            .header, .content, .button-container, .footer {" +
                "                padding: 20px 15px;" +
                "            }" +
                "            .header h1 {" +
                "                font-size: 24px;" +
                "            }" +
                "            .button {" +
                "                padding: 10px 20px;" +
                "                font-size: 14px;" +
                "            }" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class=\"container\">" +
                "        <div class=\"header\">" +
                "            <h1>Welcome to Our Blog!</h1>" +
                "        </div>" +
                "        <div class=\"content\">" +
                "            <p>Dear " + userName + ",</p>" +
                "            <p>A warm welcome to our blog platform! We are thrilled to have you join our community of passionate readers and writers.</p>" +
                "            <p>Here, you'll find a diverse range of articles, insights, and discussions on various topics. We encourage you to explore, learn, and share your own thoughts.</p>" +
                "            <p>To get started, why not check out some of our latest posts or customize your profile?</p>" +
                "        </div>" +
                "        <div class=\"button-container\">" +
                "            <a href=\"[Link to Blog Homepage/Latest Posts]\" class=\"button\">Explore Our Blog</a>" +
                "        </div>" +
                "        <div class=\"content\">" +
                "            <p>If you have any questions or need assistance, feel free to reply to this email. Our team is always happy to help.</p>" +
                "            <p>Happy reading and writing!</p>" +
                "            <p>Best regards,</p>" +
                "            <p>The [Your Blog Name] Team</p>" +
                "        </div>" +
                "        <div class=\"footer\">" +
                "            <p>&copy; 2025 [Your Blog Name]. All rights reserved.</p>" +
                "            <p><a href=\"[Link to Privacy Policy]\">Privacy Policy</a> | <a href=\"[Link to Terms of Service]\">Terms of Service</a></p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }

    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime(){
        System.out.println("Blog Current time: "+System.currentTimeMillis());
    }

    @Scheduled(cron = "0 10 23 * * *")
    public void sendEmail(){
        String to="prasharaaryan9@gmail.com";
        String subject="Greetings from BlogByMay!!";
        String text="This is a test email";
        sendEmail(to,subject,text);
        System.out.println("Email sent");
    }
}
