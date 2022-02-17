package com.wsspring.controller;

import com.wsspring.model.Privilege;
import com.wsspring.model.Role;
import com.wsspring.model.User;
import com.wsspring.repository.UserRepository;
import com.wsspring.service.NotificationService;
import com.wsspring.model.Notification;
import com.wsspring.service.PrivilegeService;
import com.wsspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    // The SimpMessagingTemplate is used to send Stomp over WebSocket messages.
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(NotificationController.class);

    /**
     * GET  /notifications  -> show the notifications page.
     */
    @RequestMapping("/notifications")
    public String notifications() {
        return "notifications";
    }

    /**
     * POST  /some-action  -> do an action.
     * <p>
     * After the action is performed will be notified UserA.
     */
    @RequestMapping(value = "/some-action/{target}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> someAction(@PathVariable String target) {
//    public ResponseEntity<?> someAction(@RequestBody String target) {

        // Do an action here
        // ...

        Notification notification = new Notification("Hello, World!");
        Collection<Role> roles = userService.findUserByUsername(target).getRoles();

        for (Role r : roles){
            for (Privilege p : r.getPrivileges())
                logger.error("----------------ROLE : {}, {}", r.getName(), p.getName());

            }


        // Send the notification to "UserA" (by username)
//        notificationService.notify(
//                notification, // notification object
//                target.getUsername()                    // username
//        );

        messagingTemplate.convertAndSendToUser(
                target,
                "/queue/notify",
                userService.loadUserByUsername(target)
        );

        // Return an http 200 status code
        return new ResponseEntity<>(notification.getContent(), HttpStatus.OK);
    }
}
