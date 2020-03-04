package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee/groups")
public class GroupController {
    @Autowired
    GroupService service;

    @Autowired
    GroupMapper mapper;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupDto> getAllGroups() {
        return mapper.mapToGroupDtoList(service.getAllGroups());
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto getGroup(@PathVariable Long groupId) throws NotFoundException {
        return mapper.mapToGroupDto(service.getGroup(groupId).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addGroup(@RequestBody GroupDto groupDto) {
        service.saveGroup(mapper.mapToGroup(groupDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@PathVariable Long id, @RequestBody(required = false) GroupDto groupDto) {
        return mapper.mapToGroupDto(service.saveGroup(mapper.mapToGroup(groupDto)));
    }
}
