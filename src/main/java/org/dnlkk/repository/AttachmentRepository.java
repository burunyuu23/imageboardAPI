package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import org.dnlkk.model.Attachment;

import java.util.List;

@Repository
public interface AttachmentRepository extends DnlkkRepository<Integer, Attachment> {
    List<Attachment> findInId(Integer[] ids);
}

