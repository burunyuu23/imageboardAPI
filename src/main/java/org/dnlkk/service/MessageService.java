package org.dnlkk.service;

import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.Service;
import com.dnlkk.repository.helper.Pageable;
import org.dnlkk.dto.request.MessageCreateRequestDTO;
import org.dnlkk.model.*;
import org.dnlkk.model.Thread;
import org.dnlkk.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {

    @AutoInject
    private MessageRepository messageRepository;
    @AutoInject
    private ThreadRepository threadRepository;
    @AutoInject
    private BoardRepository boardRepository;
    @AutoInject
    private AttachmentRepository attachmentRepository;
    @AutoInject
    private ReplyRepository replyRepository;

    public Message getMessage(Integer id) {
        return messageRepository.findById(id);
    }
    public Message getRandomMessage(Pageable pageable) {
        return messageRepository.find(pageable);
    }

    public Message getRandomMessageByThreadId(Pageable pageable, Integer threadId) {
        return messageRepository.findByThread(threadId, pageable).get(0);
    }

    public Message getRandomMessageByBoardId(Pageable pageable, String boardId) {
        Thread thread = threadRepository.findByBoardIgnoredBoardAndMessages(boardId, pageable);
        return getRandomMessageByThreadId(pageable, thread.getId());
    }

    public Message getRandomMessageByThemeId(Pageable pageable, Integer themeId) {
        Board board = boardRepository.findByThemeIgnoredBannerAndThreads(themeId, pageable);
        Thread thread = threadRepository.findByBoardIgnoredBoardAndMessages(board.getId(), pageable);
        return getRandomMessageByThreadId(pageable, thread.getId());
    }

    public Message postNewMessage(MessageCreateRequestDTO messageCreateRequestDTO) {
        List<Attachment> attachments = new ArrayList<>();

        String messageBody = messageCreateRequestDTO.getBody();

        Message message = new Message();
        message.setBody(messageBody);

        Thread thread = new Thread();
        thread.setId(messageCreateRequestDTO.getThreadId());

        message.setThread(thread);
        messageRepository.save(message);

        for (byte[] attachment : messageCreateRequestDTO.getAttachments()) {
            Attachment attachmentTemporary = new Attachment(message, attachment);
            attachments.add(attachmentTemporary);
        }
        message.setAttachments(attachments);
        attachmentRepository.saveAll(message.getAttachments());

        message.setReplies(parseRepliesFromMessageBody(messageBody, message));
        replyRepository.saveAll(message.getReplies());

        return message;
    }

    private List<Reply> parseRepliesFromMessageBody(String messageBody, Message fromMessage) {
        List<String> repliesString = new ArrayList<>();

        Pattern pattern = Pattern.compile(">>\\s*\\d+\\s*");
        Matcher matcher = pattern.matcher(messageBody);
        while (matcher.find())
            repliesString.add(matcher.group().trim());

        List<Reply> replies = new ArrayList<>();
        for (String replyString : repliesString) {
            Integer replyTo = Integer.parseInt(replyString.substring(2).trim());
            Message toMessage = new Message();
            toMessage.setId(replyTo);

            Reply reply = new Reply(toMessage, fromMessage);
            replies.add(reply);
        }
        return replies;
    }

    public List<Message> getAllMessages(Integer threadId, Pageable pageable) {
        if (threadId != null)
            return messageRepository.findByThread(threadId, pageable);
        return messageRepository.findAll(pageable);
    }

    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }
}
