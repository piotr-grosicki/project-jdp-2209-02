package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/shop/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping(value = "groupId")
    public GroupDto getGroupById(@PathVariable Long groupId) {
        return new GroupDto(1L,"group name","group description");
    }

    @PostMapping
    public void createGroup(GroupDto groupDto) {

    }

    @PutMapping
    public GroupDto updateGroup(GroupDto groupDto) {
        return new GroupDto(1L,"Updated group name","Updated group description");
    }
}
