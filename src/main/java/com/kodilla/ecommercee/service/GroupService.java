package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public interface GroupService {

    public List<Group> getAllGroups();

    public Optional<Group> getGroup(Long groupId);

    public Group saveGroup(Group group);

    public Group getGroupById(Long id);
}
