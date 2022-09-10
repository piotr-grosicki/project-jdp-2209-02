package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping("/v1/shop/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupMapper groupMapper;
    private final GroupDbService groupDbService;

    @ApiOperation(value = "get list all groups",
            response = Root.class,
            notes = "This method takes all groups from the repository and returns them as a list. If not found, it is returned empty list.")
    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<Group> groups = groupDbService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapToTaskDtoList(groups));
    }

    @ApiOperation(value = "get group by id",
            response = Root.class,
            notes = "This method looks for a group by id in the repository, then returns it. If not found, return BAD REQUEST (400).")
    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) throws GroupNotFoundException {
        Group group = groupDbService.getGroupById(groupId);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(group));
    }

    @ApiOperation(value = "add group",
            response = Root.class,
            notes = "This method creates a group and adds it to the repository.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        groupDbService.saveGroup(groupMapper.mapToNewGroup(groupDto));
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "update group data",
            response = Root.class,
            notes = "This method updates the fields in the group. If not found group to change, return BAD REQUEST (400).")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) throws GroupNotFoundException {
        return ResponseEntity.ok(
                groupMapper.mapToGroupDto(
                        groupDbService.updateGroup(
                                groupMapper.mapToGroup(groupDto)
                        )
                )
        );
    }
}