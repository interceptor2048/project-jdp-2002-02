package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/v1/ecommercee")
public class GroupController {
    @Autowired
    GroupService service;

    @Autowired
    GroupMapper mapper;

    //@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET, value = "groups")
    public List<GroupDto> getAllGroups() {
        return mapper.mapToGroupDtoList(service.getAllGroups());
    }

    //@GetMapping(path = "/{id}", value = "getGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET, value = "group")
    public GroupDto getGroup(Long groupId) throws NotFoundException {
        return mapper.mapToGroupDto(service.getGroup(groupId).orElseThrow(NotFoundException::new));
    }

    //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST, value = "addGroup", consumes = APPLICATION_JSON_VALUE)
    public void addGroup(@RequestBody GroupDto groupDto) {
        service.saveGroup(mapper.mapToGroup(groupDto));
    }

    //@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup", consumes = APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return mapper.mapToGroupDto(service.saveGroup(mapper.mapToGroup(groupDto)));
    }
}
