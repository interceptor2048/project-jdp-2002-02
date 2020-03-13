package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

//    public Group mapToGroup(final GroupDto groupDto) {
//        return new Group(groupDto.getId(), groupDto.getName(),
//                groupDto.getDescription(), groupDto.getProducts());
//    }
//
//    public GroupDto mapToGroupDto(final Group group) {
//        return new GroupDto(group.getId(), group.getName(),
//                group.getDescription(), group.getProducts());
//    }
//
//    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
//        return groupList.stream().map(g -> new GroupDto(g.getId(), g.getName(), g.getDescription(), g.getProducts()))
//                .collect(Collectors.toList());
//    }
}
