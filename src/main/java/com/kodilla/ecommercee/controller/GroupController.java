package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee/groups")
public class GroupController {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupDto> getAllGroups() {
        return new ArrayList<GroupDto>();
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto getGroup(@PathVariable Long groupId) {
        return new GroupDto();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addGroup(@RequestBody GroupDto groupDto) {
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateGroup(@PathVariable Long id, @RequestBody(required = false) GroupDto groupDto) {
    }
}
