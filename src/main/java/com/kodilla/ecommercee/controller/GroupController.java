package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/groups")
@RequiredArgsConstructor
public class GroupController {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapToTaskDtoList(groups));
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) throws GroupNotFoundException {
        Group group = groupService.getGroupById(groupId);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(group));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        groupService.saveGroup(group);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(groupService.saveGroup(group)));
    }
}