package com.kodilla.ecommercee.service.impl;

import com.kodilla.ecommercee.dao.GroupDao;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    GroupDao groupRepository;

    public GroupServiceImpl(GroupDao groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroup(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }
}
