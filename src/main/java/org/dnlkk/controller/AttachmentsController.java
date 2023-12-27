package org.dnlkk.controller;

import com.dnlkk.controller.annotations.RequestBody;
import com.dnlkk.controller.annotations.RequestMapping;
import com.dnlkk.controller.annotations.request_method.Get;
import com.dnlkk.controller.annotations.request_method.Post;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.RestController;
import com.dnlkk.doc.annotation.ApiOperation;
import org.dnlkk.dto.AttachmentDTO;
import org.dnlkk.dto.GetAttachmentDTO;
import org.dnlkk.model.Attachment;
import org.dnlkk.model.Message;
import org.dnlkk.service.AttachmentsService;

import java.util.List;

@RestController("/attachments")
public class AttachmentsController {
    @AutoInject
    private AttachmentsService attachmentsService;

    @Post
    @RequestMapping()
    @ApiOperation(
            name = "Add one new image",
            response = Message.class
    )
    public ResponseEntity<Attachment> postAttachment(@RequestBody AttachmentDTO attachmentDTO) {
        return ResponseEntity.ok(attachmentsService.postAttachment(attachmentDTO.getFile()));
    }

    @Get
    @RequestMapping()
    @ApiOperation(
            name = "Get images",
            response = Attachment.class
    )
    public ResponseEntity<List<Attachment>> getAttachment(@RequestBody GetAttachmentDTO attachmentDTO) {
        return ResponseEntity.ok(attachmentsService.getAttachments(attachmentDTO.getIds()));
    }
}
