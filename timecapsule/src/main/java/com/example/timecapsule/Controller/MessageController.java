package com.example.timecapsule.Controller;

import com.example.timecapsule.Entity.Message;
import com.example.timecapsule.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    //send a new message
    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) throws Exception {
        Message savedMessage = messageService.saveMessage(message);
        System.out.println("postmapping message " + message);
        return ResponseEntity.ok(savedMessage);
    }

    //gets messages by senders name
    @GetMapping("/{name}")
    public ResponseEntity<List<Message>> getMessage(@PathVariable String name) throws Exception {
        List<Message> message = messageService.getMessageByName(name);
        System.out.println("from getmapping" + name);
        return ResponseEntity.ok(message);
    }
}
