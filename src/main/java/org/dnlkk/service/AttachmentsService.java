package org.dnlkk.service;

import com.dnlkk.dependency_injector.annotations.AutoInject;
import org.dnlkk.model.Attachment;
import org.dnlkk.repository.AttachmentRepository;

import java.util.List;

public class AttachmentsService {
    @AutoInject
    private AttachmentRepository attachmentRepository;

    public Attachment postAttachment(byte[] file) {
        Attachment attachment = attachmentRepository.save(new Attachment(file));
        System.out.println(attachment);
        return attachment;
    }

    public List<Attachment> getAttachments(Integer[] ids) {
        return attachmentRepository.findInId(ids);
    }
}
