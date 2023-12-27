package org.dnlkk.service;

import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.Service;
import com.dnlkk.repository.helper.Pageable;
import org.dnlkk.dto.MessageDTO;
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
    @AutoInject
    private AttachmentsService attachmentService;

    public Message getMessage(Integer id, Pageable pageable) {
        return messageRepository.findById(id, pageable);
    }

    public Long getMessagePosition(Integer id) {
        return messageRepository.positionById(id);
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
        String messageBody = messageCreateRequestDTO.getBody();

        Message message = new Message();
        message.setBody(messageBody);

        Thread thread = new Thread();
        thread.setId(messageCreateRequestDTO.getThreadId());

        message.setThread(thread);
        messageRepository.save(message);

        List<Attachment> attachments = attachmentService.getAttachments(messageCreateRequestDTO.getAttachmentIds());
        for (Attachment attachment : attachments) attachment.setMessage(message);
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

    public List<MessageDTO> getMessagesInList(Integer[] ids) {
        return messageRepository.findInId(ids).stream()
                .map(message -> new MessageDTO(message, this.getMessagePosition(message.getId())))
                .toList();
    }

    public List<MessageDTO> getAllMessages(Integer threadId, Pageable pageable) {
        List<MessageDTO> messageDTOList = new ArrayList<>();
        List<Message> messages;
        if (threadId != null) {
            messages = messageRepository.findByThread(threadId, pageable);
            pageable.setTotalPages((messageRepository.countByThread(threadId) - pageable.getOffset() - 1) / pageable.getLimit());
        }
        else messages = messageRepository.findAll(pageable);
        for (Message message:messages)
            messageDTOList.add(new MessageDTO(message, this.getMessagePosition(message.getId())));

        return messageDTOList;
    }

    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }
}
