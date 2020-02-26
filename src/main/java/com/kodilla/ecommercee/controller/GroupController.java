package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee")
public class GroupController {

    @GetMapping( value = "groups", consumes = "application/json")
    public List<GroupDto> getAllProductGroups() {
        return new ArrayList<GroupDto>() ;
    }

    @GetMapping(value = "groups", consumes = "application/json")
    public GroupDto getGroup(@RequestParam Long groupId) {
        return new GroupDto();
    }

    @PostMapping(value = "groups", consumes = "application/json")
    public void addGroup(@RequestBody GroupDto groupDto) {
    }

    @PutMapping(value = "groups", consumes = "application/json")
    public GroupDto groupUpdate(@RequestBody GroupDto groupDto) {
        return new GroupDto();
    }
}
