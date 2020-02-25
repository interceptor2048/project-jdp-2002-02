package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET, value = "allProductGroups")
    public List<GroupDto> getAllProductGroups(){
        return new ArrayList<GroupDto>() ;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup", consumes = "application/json")
    public GroupDto getGroup(@RequestParam Long groupId){
        return new GroupDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addGroup", consumes = "application/json")
    public void addGroup(@RequestBody GroupDto groupDto){
    }

    @RequestMapping(method = RequestMethod.PUT, value = "groupUpdate", consumes = "application/json")
    public GroupDto groupUpdate(@RequestBody GroupDto groupDto){
        return new GroupDto();
    }



}
