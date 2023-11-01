package org.dnlkk.service;

import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.Service;
import org.dnlkk.model.Board;
import org.dnlkk.model.Message;
import org.dnlkk.repository.BoardRepository;
import org.dnlkk.repository.MessageRepository;
import org.dnlkk.repository.ThreadRepository;

@Service
public class TestService {

    @AutoInject
    private BoardRepository boardRepository;
    @AutoInject
    private ThreadRepository threadRepository;

    @AutoInject
    private MessageRepository messageRepository;

    public Board getBoard(String id) {
        return boardRepository.findById(id);
    }

    public Message getMsg(Integer id) {
        return messageRepository.findById(id);
    }

    public Message postNewMessage(String body) {
        Message message = new Message();
        message.setBody(body);
        message.setThread(threadRepository.findById(1));
        messageRepository.save(message);

        return message;
    }
}
