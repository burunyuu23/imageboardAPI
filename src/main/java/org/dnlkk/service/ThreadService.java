package org.dnlkk.service;

import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.Service;
import org.dnlkk.model.Thread;
import org.dnlkk.repository.ThreadRepository;

@Service
public class ThreadService {

    @AutoInject
    private ThreadRepository threadRepository;

    public Thread getThread(Integer id) {
        return threadRepository.findById(id);
    }
}
