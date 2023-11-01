package org.dnlkk.service;

import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.Service;
import com.dnlkk.repository.Pageable;
import org.dnlkk.dto.request.MessageCreateRequestDTO;
import org.dnlkk.model.Message;
import org.dnlkk.repository.MessageRepository;
import org.dnlkk.repository.ThreadRepository;

import java.util.List;

@Service
public class MessageService {

    @AutoInject
    private MessageRepository messageRepository;
    @AutoInject
    private ThreadRepository threadRepository;

    public Message getMessage(Integer id) {
        return messageRepository.findById(id);
    }

    public Message postNewMessage(MessageCreateRequestDTO messageCreateRequestDTO) {
        Message message = new Message();
        message.setBody(messageCreateRequestDTO.getBody());
        message.setThread(threadRepository.findById(messageCreateRequestDTO.getThreadId()));
        messageRepository.save(message);

        return message;
    }

    public List<Message> getAllMessages(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }
}
