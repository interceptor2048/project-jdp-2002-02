package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee/groups")
public class GroupController {

    @GetMapping(path = "/groups")
    public List<GroupDto> getAllGroups() {
        return new ArrayList<GroupDto>();
    }

    @GetMapping(path = "/groups")
    public GroupDto getGroup(@RequestParam Long groupId) {
        return new GroupDto();
    }

    @PostMapping(path = "/groups", consumes = "application/json")
    public void addGroup(@RequestBody GroupDto groupDto) {
    }

    @PutMapping(path = "/groups")
    public void updateGroup(@RequestBody GroupDto groupDto) {
    }
}
