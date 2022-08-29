package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class GroupMapper {

    public Group mapToGroup(GroupDto groupDto) {
        return new Group(
                groupDto.getName(),
                groupDto.getDescription()
        );
    }

    public GroupDto mapToGroupDto(Group group) {
        return new GroupDto(
                group.getId(),
                group.getName(),
                group.getDescription()
        );
    }
    public List<GroupDto> mapToTaskDtoList(List<Group> groupList){
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
