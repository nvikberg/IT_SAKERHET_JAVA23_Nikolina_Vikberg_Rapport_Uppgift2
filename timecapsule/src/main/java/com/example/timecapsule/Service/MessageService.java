package com.example.timecapsule.Service;

import com.example.timecapsule.Entity.Message;
import com.example.timecapsule.Repository.MessageRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private EncryptionService encryptionService;

    private SecretKey aesKey;

    //aes key would not generate without this -  init method to generate the AES key once the MessageService bean is fully initialized
    @PostConstruct
    public void init() {
        try {
            aesKey = encryptionService.generateAESKey();
            System.out.println("aesKey generated " + aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SecretKey getAESKey() {
        System.out.println(aesKey + " 1");
        return aesKey;
    }

    //save messages after encrypting
    public Message saveMessage(Message message) {
        //enkrypting the message before saving
        try {
            String encryptedText = encryptionService.encrypt(message.getMessage(), aesKey);
            message.setMessage(encryptedText);
            System.out.println("encrypted message " + message + " "+aesKey);
            return messageRepository.save(message);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with encryption message");
            return null;
        }
    }
    //gets and decrypt messages by senders name
    public List<Message> getMessageByName(String name) {
        List<Message> messages = messageRepository.findByName(name);
        List<Message> decryptedMessages = new ArrayList<>();

        for (Message msg : messages) {
            try {
                String decryptedText = encryptionService.decrypt(msg.getMessage(), aesKey);
                msg.setMessage(decryptedText);
                decryptedMessages.add(msg);
                System.out.println("encryption success" + " " + aesKey);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("decryption failed" + msg.getMessage());
            }
        }

        return decryptedMessages;
    }

}
